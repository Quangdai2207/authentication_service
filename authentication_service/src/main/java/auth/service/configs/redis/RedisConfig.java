package auth.service.configs.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class RedisConfig implements WebMvcConfigurer {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // Tao doi tuong format KEY redis
        RedisSerializer<String> stringSerializer = RedisSerializer.string();

        // Tao doi tuong format value redis
        RedisSerializer<Object> jsonSerializer = RedisSerializer.json();

        // format Key dang String
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);

        // Format value dang json() Object
        template.setValueSerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer);

        template.afterPropertiesSet();

        return template;
    }
}
