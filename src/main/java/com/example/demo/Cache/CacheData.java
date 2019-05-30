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


    /** Cacheable的value会覆盖 cacheConfig的 cacheNames
     *  cacheNames仅仅用来控制key的前缀（如果前缀开户）以及缓存时间
     * @param id
     * @return
     */
    @Cacheable(key = "'ffff:'+#id", value = "my-cache-name")
    public Integer cacheData(Long id){
        return 111;
    }
}
