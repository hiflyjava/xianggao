package cc.mrbird.common.redis;


import cc.mrbird.common.util.DateUtil;
import cc.mrbird.common.websocket.SpringUtils;
import cc.mrbird.web.utils.MyDateUtils;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.Session;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/13 09:57
 * @Description:
 */
@Component
public class SubscribeListener  implements MessageListener {


    public static SubscribeListener filter;
   private javax.websocket.Session session;

   private Long userId;
   private String topic;


    @Autowired
    RedisService redisService;

    @PostConstruct
    public void init() {
        filter = this;
        filter.redisService = this.redisService;

    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Session getSession(){
        return  session;
    }

    public  void  setSession(Session session){

        this.session=session;
    }

    /**
     * 订阅接收发布者的消息
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 缓存消息是序列化的，需要反序列化。然而new String()可以反序列化，但静态方法valueOf()不可以
            String pannel=new String(pattern);//管道
        String messages= null;//消息
        try {
            messages = new String(message.getBody(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(pannel + "主题发布fafaf：" + messages);

        if(null !=session && session.isOpen()){

            List messageOfflist =new ArrayList();
//                      if(filter.redisService.exists(pannel)){
//                         messageOfflist = filter.redisService.get(pannel, List.class);
//                      }

            //用户在线而且订阅了主题
            try {
                synchronized (session) {
                    JSONObject jsonObject =new JSONObject();
                    jsonObject.put("fromUsername", userId);
                    jsonObject.put("message", messages);
                    jsonObject.put("createDate", DateUtil.getDateTime());
                    messageOfflist.add(jsonObject);

                    session.getBasicRemote().sendText(new Gson().toJson(messageOfflist));

                }
            } catch (IOException e) {
                System.out.println("发送消息异常");
            }
            filter.redisService.delete(pannel);
        }else if(userId !=null){
            //用户不在线但是订阅了主题
            System.out.println("用户:  " + userId + "  当前不在线，但是他已经订阅了，所以我们无法给他实时推出数据");
            doLiXian(userId);
        }else {
            List<JSONObject> jsonObjects =new ArrayList<>();
              JSONObject jsonObject =new JSONObject();
              jsonObject.put("fromUsername", userId);
              jsonObject.put("message", messages);
              jsonObject.put("createDate", DateUtil.getDateTime());
              jsonObjects.add(jsonObject);
            //String resp = jsonObject.toString();
           // boolean exists = filter.redisService.hasKey(pannel);
            boolean exists = filter.redisService.exists(pannel);
            if(exists){//如果存在的话
                List<JSONObject> list = filter.redisService.get(pannel, List.class);
                list.add(jsonObject);
                filter.redisService.set(pannel, list);
                System.out.println(list.get(0));

            }else {//如果不存在的话
                filter.redisService.set(pannel,jsonObjects);
            }

        }

    }

    public void doLiXian(Long userId) {
        System.out.println(userId + "我们可以根据用户的ID来给用户发送一些消息，都在这个方法里完成，比如发邮件、发短信之类的");
    }

}
