package com.example.demo.Lock;

import static java.lang.System.currentTimeMillis;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;

/**
 * Created on 2017/8/11.
 */
public class DistributeLock<T> {
    private static Logger logger = LoggerFactory.getLogger(DistributeLock.class);
    private StringRedisTemplate stringRedisTemplate;
    // 锁名称
    private final String lockName;
    // 锁的过期时间(单位为秒)
    private final long expireTime;
    // 锁占用失败等待时间
    private final int waitInterval;
    // 锁占用失败超时时间
    private final int waitTimeout;

    private final boolean returnOnError;

    public T lock(Supplier<T> redisLoader) {
        String uuid = UUID.randomUUID().toString();
        long startTime = currentTimeMillis();
        synchronized (lockName) {
            try {
                while (true) {
                    if (currentTimeMillis() > startTime + waitTimeout) {
                        throw new RuntimeException("操作超时");
                    }
                    boolean locked = tryLock(uuid);
                    if (locked) {
                        return redisLoader.get();
                    }
                    try {
                        Thread.sleep(waitInterval);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            } finally {
                unLock(uuid);
                logger.debug("lock info. name: " + lockName + " time: " + (currentTimeMillis() - startTime) + "ms");
            }
        }
    }

    private boolean tryLock(String uuid) {
        try {
            return Optional.ofNullable(stringRedisTemplate.execute(connection -> connection
                    .set(serialize(lockName), serialize(uuid), Expiration.from(expireTime, TimeUnit.SECONDS),
                            SetOption.SET_IF_ABSENT), false)).orElse(false);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return returnOnError;
        }
    }

    private byte[] serialize(String string) {
        return stringRedisTemplate.getStringSerializer().serialize(string);
    }

    private void unLock(String uuid) {
        try {
            if (uuid.equals(stringRedisTemplate.opsForValue().get(lockName))) {
                stringRedisTemplate.delete(lockName);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public DistributeLock(final StringRedisTemplate stringRedisTemplate, final String lockName, final long expireTime, final int waitInterval, final int waitTimeout, final boolean returnOnError) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.lockName = lockName;
        this.expireTime = expireTime;
        this.waitInterval = waitInterval;
        this.waitTimeout = waitTimeout;
        this.returnOnError = returnOnError;
    }

}