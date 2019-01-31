package com.example.demo.config;

import java.time.Duration;

import com.example.demo.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.lang.Nullable;

/**
 * 自定义的redis cache writer的类， 如果连不上redis或者命令 超时 会直接进行后续逻辑
 * Created on 2018/2/1.
 */
public class CustomRedisCacheWriter implements RedisCacheWriter {
    private static Logger logger = LoggerFactory.getLogger(TestService.class);

    private RedisCacheWriter delegate;

    public CustomRedisCacheWriter(RedisCacheWriter delegate) {
        this.delegate = delegate;
    }

    @Override
    public void put(String s, byte[] bytes, byte[] bytes1, @Nullable Duration duration) {
        try {
            delegate.put(s, bytes, bytes1, duration);
        } catch (Exception e) {
            handleException(e);
        }
    }

    @Nullable
    @Override
    public byte[] get(String s, byte[] bytes) {
        try {
            return delegate.get(s, bytes);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Nullable
    @Override
    public byte[] putIfAbsent(String s, byte[] bytes, byte[] bytes1, @Nullable Duration duration) {
        try {
            return delegate.putIfAbsent(s, bytes, bytes1, duration);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    @Override
    public void remove(String s, byte[] bytes) {
        try {
            delegate.remove(s, bytes);
        } catch (Exception e) {
            handleException(e);
        }
    }

    @Override
    public void clean(String s, byte[] bytes) {
        try {
            delegate.clean(s, bytes);
        } catch (Exception e) {
            handleException(e);
        }
    }

    private <T> T handleException(Exception e) {
        logger.error("handleException", e);
        return null;
    }
}
