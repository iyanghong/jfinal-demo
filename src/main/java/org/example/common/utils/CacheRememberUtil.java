package org.example.common.utils;

import com.jfinal.plugin.redis.Redis;

public class CacheRememberUtil<T> {
    private T data = null;

    public CacheRememberUtil(String key, Integer expir, RememberHandlerInterface<T> handler) {
        T entity = Redis.use().get(key);
        if (entity != null) data = entity;
        data = handler.handler();
        if (expir > 0) {
            Redis.use().setex(key, expir, data);
        } else {
            Redis.use().set(key, data);
        }
    }

    public T get() {
        return data;
    }

}
