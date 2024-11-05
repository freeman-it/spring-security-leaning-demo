package com.ilxqx.springsecuritylearning.config;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * Redis配置
 *
 * @author venus
 * @version 1.0.0
 * @since 2022/7/9 23:07
 */
@Configuration(proxyBeanMethods = false)
public class RedisConfig implements BeanClassLoaderAware {

    private ClassLoader classLoader;

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());

        RedisSerializer<Object> objectRedisSerializer = RedisSerializer.java(this.classLoader);
        redisTemplate.setValueSerializer(objectRedisSerializer);
        redisTemplate.setHashValueSerializer(objectRedisSerializer);
//
        return redisTemplate;
    }

    /**
     *   设置bean 的类加载器
     * @author xzm
     * @date 2024/11/05 15:49
     * @param classLoader
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
