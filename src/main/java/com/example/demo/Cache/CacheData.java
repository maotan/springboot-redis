package com.example.demo.Cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames="cache-key")
public class CacheData {

    @Cacheable(key = "'eeee'+#id")
    public Integer getData(Long id){
        return 111;
    }


    // Cacheable的value会覆盖 cacheConfig的 cacheNames
    @Cacheable(key = "'eeee'+#id", value = "my-cache-name")
    public Integer cacheData(Long id){
        return 111;
    }
}
