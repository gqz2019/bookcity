package com.gqz.bookcity.config;

import com.alibaba.fastjson.JSONObject;
import com.gqz.bookcity.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisElementReader;
import org.springframework.data.redis.serializer.RedisElementWriter;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.HashSet;

/**
 * <p></p>
 *
 * @author gqz20
 * @create 2021-11-27 10:38
 **/
@Configuration
public class CacheConfig {
    @Autowired
    Environment env;
    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Autowired
    RedisTemplate redisTemplate;

    @Bean("cacheManager")
    public CacheManager cacheManager() {
        // 基本配置
        HashSet<String> cacheNames = new HashSet<>();
        cacheNames.add("shopCart");
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisTemplate.getKeySerializer()))
                .serializeValuesWith(new RedisSerializationContext.SerializationPair<Object>() {
                    @Override
                    public RedisElementReader<Object> getReader() {
                        return buffer -> JSONObject.<Order>parseObject(buffer.array(),Order.class);
                    }

                    @Override
                    public RedisElementWriter<Object> getWriter() {
                        return element -> {
                            byte[] bytes = JSONObject.toJSONBytes(element);
                            return ByteBuffer.wrap(bytes);
                        };
                    }
                })
                .entryTtl(Duration.ofHours(1));
        return RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration)
                .transactionAware()
                .initialCacheNames(cacheNames)
                .build();
    }
}
