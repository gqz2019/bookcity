/*
package com.gqz.bookcity.advice;

import java.io.Serializable;
import java.util.concurrent.Callable;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

*/
/**
 * @author gqz20
 *//*

public class MyRedisCache extends RedisCache implements Cache{

    private RedisTemplate<String, Object> redisTemplate;
    private String name;

    */
/**
     * Create new {@link RedisCache}.
     *
     * @param name        must not be {@literal null}.
     * @param cacheWriter must not be {@literal null}.
     * @param cacheConfig must not be {@literal null}.
     *//*

    protected MyRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
        super(name, cacheWriter, cacheConfig);
    }

    @Override
    public void clear() {
        System.out.println("-------緩存清理------");
        redisTemplate.execute((RedisCallback<String>) connection -> {
            connection.flushDb();
            return "ok";
        });
    }

    @Override
    public void evict(Object key) {
        System.out.println("-------緩存刪除------");
        final String keyf = key.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(keyf.getBytes());
            }

        });

    }

    @Override
    public boolean evictIfPresent(Object key) {
        return Cache.super.evictIfPresent(key);
    }

    @Override
    public ValueWrapper get(Object key) {
        System.out.println("------缓存获取-------" + key.toString());
        final String keyf = key.toString();
        Object object = null;
        object = redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] key1 = keyf.getBytes();
            byte[] value = connection.get(key1);
            if (value == null) {
                System.out.println("------缓存不存在-------");
                return null;
            }
            return SerializationUtils.deserialize(value);
        });
        ValueWrapper obj = (object != null ? new SimpleValueWrapper(object) : null);
        System.out.println("------获取到内容-------" + obj.toString());
        return obj;
    }

    @Override
    public void put(Object key, Object value) {
        System.out.println("-------加入缓存------");
        System.out.println("key----:" + key);
        System.out.println("key----:" + value);
        final String keyString = key.toString();
        final Object valuef = value;
        final long liveTime = 86400;
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyb = keyString.getBytes();
                byte[] valueb = SerializationUtils.serialize((Serializable) valuef);
                connection.set(keyb, valueb);
                if (liveTime > 0) {
                    connection.expire(keyb, liveTime);
                }
                return 1L;
            }
        });

    }

    @Override
    public <T> T get(Object arg0, Class<T> arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }

    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public ValueWrapper putIfAbsent(Object arg0, Object arg1) {
        // TODO Auto-generated method stub
        return null;
    }

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }
}*/
