package cc.mrbird.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/13 09:57
 * @Description:
 */

@Component
public class PublishService {

    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 发布方法
     * @param channel 消息发布订阅 主题
     * @param message 消息信息
     */
    public void publish(String channel, Object message) {
        // 该方法封装的 connection.publish(rawChannel, rawMessage);
        redisTemplate.convertAndSend(channel, message);
    }

}
