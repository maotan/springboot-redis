package com.example.demo.Cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames="blackdata11")
public class CacheData {

    @Cacheable(key = "'aaaa'")
    public Integer getData(){
        return 111;
    }
}
