package com.example.todo.infrastructure.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * Configures Redis caching for the Todo service.
 * This configuration sets up the cache manager and defines the default cache settings.
 */
@Configuration
@EnableCaching
public class TodoCacheConfig {

    /**
     * Configures the Redis cache manager with default settings.
     *
     * @param redisConnectionFactory the Redis connection factory
     * @return the Redis cache configuration
     */
    @Bean
    public RedisCacheConfiguration cacheConfiguration(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(10))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
    }

    /**
     * Configures the Redis template for interacting with Redis.
     *
     * @param redisConnectionFactory the Redis connection factory
     * @return the Redis template
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }
}