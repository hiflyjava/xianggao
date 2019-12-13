package cc.mrbird.common.websocket;

import cc.mrbird.common.redis.RedisService;
import cc.mrbird.common.redis.SubscribeListener;
import cc.mrbird.web.utils.XgCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/13 10:25
 * @Description:
 */

@Component
@ServerEndpoint("/websocket/server/{userId}/{topic}")
public class WebSocketServer {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);

    /**

     * 因为@ServerEndpoint不支持注入，所以使用SpringUtils获取IOC实例

     */

    private RedisMessageListenerContainer redisMessageListenerContainer = SpringUtils.getBean(RedisMessageListenerContainer.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。

    private static AtomicInteger onlineCount=new AtomicInteger(0);

    //concurrent包的线程安全Set，用来存放每个客户端对应的webSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识

    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据

    private Session session;

    private SubscribeListener subscribeListener =SpringUtils.getBean(SubscribeListener.class);


    private RedisService redisService =SpringUtils.getBean(RedisService.class);
    /**

     * 连接建立成功调用的方法

     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据

     */

    @OnOpen

    public void onOpen(Session session ,@PathParam("userId") Long userId, @PathParam("topic") String topic){

       // LOGGER.info("打开了Socket链接Open a html. userId={}, name={}", userId, topic);
           redisService.set(XgCodeUtil.WEB_STATUS+topic, XgCodeUtil.WEB_STATUS_ON);//将topic设为在线
           subscribeListener.setUserId(userId);
               subscribeListener.setTopic(topic);
        this.session = session;

        webSocketSet.add(this);     //加入set中

        addOnlineCount();           //在线数加1

        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());

        subscribeListener = new SubscribeListener();

        subscribeListener.setSession(session);
       //  subscribeListener.setRedisService(redisService);
        //设置订阅topic

        //redisMessageListenerContainer.addMessageListener(subscribeListener, new ChannelTopic("test-topic"));
        redisMessageListenerContainer.addMessageListener(subscribeListener, new ChannelTopic(topic));

        if(redisService.exists(topic)){
            String s = redisService.get(topic);
            try {
                sendMessage(s);
                redisService.delete(topic);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**

     * 连接关闭调用的方法

     */

    @OnClose

    public void onClose(@PathParam("userId") Long userId, @PathParam("topic") String topic) throws IOException {

        webSocketSet.remove(this);  //从set中删除

        subOnlineCount();           //在线数减1

        redisMessageListenerContainer.removeMessageListener(subscribeListener);

        redisService.set(XgCodeUtil.WEB_STATUS+topic, XgCodeUtil.WEB_STATUS_OFF);//将topic设为离线
        System.out.println(topic+"连接关闭！当前在线人数为" + getOnlineCount());

    }



    /**

     * 收到客户端消息后调用的方法

     * @param message 客户端发送过来的消息

     * @param session 可选的参数

     */

    @OnMessage

    public void onMessage(String message, Session session) {

        System.out.println("来自客户端的消息:" + message);

        //群发消息

        for(WebSocketServer item: webSocketSet){

            try {

                item.sendMessage(message);

            } catch (IOException e) {

                e.printStackTrace();

                continue;

            }

        }

    }



    /**

     * 发生错误时调用

     * @param session

     * @param error

     */

    @OnError

    public void onError(Session session, Throwable error){

        System.out.println("发生错误");

        error.printStackTrace();

    }



    /**

     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。

     * @param message

     * @throws IOException

     */

    public void sendMessage(String message) throws IOException {

        System.out.println("发送给浏览器的信息:"+message);
        this.session.getBasicRemote().sendText(message);

    }



    public   int getOnlineCount() {

        return onlineCount.get();

    }



    public   void addOnlineCount() {

        WebSocketServer.onlineCount.getAndIncrement();

    }



    public   void subOnlineCount() {

        WebSocketServer.onlineCount.getAndDecrement();

    }

}
