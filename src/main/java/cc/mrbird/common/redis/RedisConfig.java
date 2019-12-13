package cc.mrbird.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * @Auther: Harden Yan
 * @Date: 2019/12/13 09:36
 * @Description:
 */
@Configuration
public class RedisConfig {



    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired




    @Bean
    public RedisTemplate<String, String> redisTemplate(){
        StringRedisTemplate redisTemplate = new StringRedisTemplate(redisConnectionFactory);
        return redisTemplate;
    }



    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        // 添加订阅者监听类，数量不限.PatternTopic定义监听主题,这里监听test-topic主题
        //container.addMessageListener(new SubscribeListener(), new PatternTopic("test-topic"));
        //container.addMessageListener(new SubscribeListener(), new PatternTopic("test2-topic"));
        return container;
    }


}
