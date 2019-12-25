package cc.mrbird.common.controller;

import cc.mrbird.common.redis.PublishService;
import cc.mrbird.common.redis.RedisService;
import cc.mrbird.common.redis.SubscribeListener;
import cc.mrbird.common.util.DateUtil;
import cc.mrbird.common.util.RespBean;
import cc.mrbird.common.util.StringUtils;
import cc.mrbird.web.domain.XgUserFensi;
import cc.mrbird.web.utils.XgCodeUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/13 09:40
 * @Description:
 */

@RestController
public class testController {


    @Autowired
    RedisService redisService;

    @Autowired
    PublishService publishService;

    @Autowired
    RedisMessageListenerContainer redisMessageListenerContainer;


    /**
     * 发送系统消息测试
     * @param xgUserFensi
     * @return
     */

    @RequestMapping("/test2")
    public RespBean teset(@RequestBody XgUserFensi xgUserFensi){
          //请求进来默认是离线的
     //   redisService.hset(null, null, null);
        String s = redisService.get(XgCodeUtil.WEB_STATUS + xgUserFensi.getExtend1(),String.class);
        System.out.println(s);
        if(StringUtils.isEmptyOrNull(s)){//离线状态
            List<JSONObject> jsonObjects =new ArrayList<>();
            JSONObject jsonObject =new JSONObject();
            jsonObject.put("fromUsername", xgUserFensi.getExtend1());
            jsonObject.put("message", xgUserFensi.getExtend2());
            jsonObject.put("createDate", DateUtil.getDateTime());
            jsonObjects.add(jsonObject);
            if(redisService.exists(xgUserFensi.getExtend1())){//有老消息
                List<JSONObject> list = redisService.get(xgUserFensi.getExtend1(), List.class);
               // list.add(jsonObject);
                jsonObjects.addAll(list);
            }
            redisService.set(xgUserFensi.getExtend1(), jsonObjects);
          //  redisMessageListenerContainer.addMessageListener(new SubscribeListener(), new PatternTopic(xgUserFensi.getExtend1()));
        }else {

            if(XgCodeUtil.WEB_STATUS_OFF.equals(s)){//离线的
                List<JSONObject> jsonObjects =new ArrayList<>();
                JSONObject jsonObject =new JSONObject();
                jsonObject.put("fromUsername", xgUserFensi.getExtend1());
                jsonObject.put("message", xgUserFensi.getExtend2());
                jsonObject.put("createDate", DateUtil.getDateTime());
                jsonObjects.add(jsonObject);
                if(redisService.exists(xgUserFensi.getExtend1())){//有老消息
                    List<JSONObject> list = redisService.get(xgUserFensi.getExtend1(), List.class);
                    // list.add(jsonObject);
                    jsonObjects.addAll(list);
                }

                redisService.set(xgUserFensi.getExtend1(), jsonObjects);
               // redisMessageListenerContainer.addMessageListener(new SubscribeListener(), new PatternTopic(xgUserFensi.getExtend1()));
            }else {
            }
        }

        //
        //没关闭连接的时候正常
        publishService.publish(xgUserFensi.getExtend1(), xgUserFensi.getExtend1()+" 发布一条消息来了");
        //判断有没有关闭连接
      //  publishService.publish("test2-topic", "test2发布一条消息来了");
        return null;
    }


    @RequestMapping("/test1")
    public void tesetet(){

        redisService.setForever("aaa", "bbbb");
        long aaa = redisService.getExpire("aaa");
        System.out.println(aaa);
    }


}
