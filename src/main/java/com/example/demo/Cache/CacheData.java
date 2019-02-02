package com.example.demo.Cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames="my-cache-name")
public class CacheData {

    @Cacheable(key = "'cccc'", value = "kkkk")
    public Integer getData(Long id){
        return 111;
    }
}
