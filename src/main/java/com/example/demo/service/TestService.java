package com.example.demo.service;

import com.example.demo.Bean.BeanTest;
import com.example.demo.Bean.Student;
import com.example.demo.Cache.CacheData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TestService {
    private static Logger logger = LoggerFactory.getLogger(TestService.class);
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CacheData cacheData;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private Student student;

    public void test(){
        /*DistributeLock<Boolean> lock = new DistributeLock<>(stringRedisTemplate,
                "test-lock", 1000,
                100, 100, false);

        lock.lock(() ->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("11111111111");
            return false;
        });

        lock.lock(() ->{
            logger.info("222222");
            return false;
        });*/
        //Integer bb = cacheData.getData(6L);
        Integer aa = cacheData.cacheData(6L);
        //System.out.println(aa);

        //redisTemplate.opsForHash().put("aaaa", "bbbb", "cccc");
        //System.out.println(redisTemplate.opsForHash().get("aaaa", "bbbb"));
        //stringRedisTemplate.opsForHash().put("myhash", "kkk1", "22222");
        //stringRedisTemplate.expire("myhash", 100, TimeUnit.SECONDS);

    }
}
