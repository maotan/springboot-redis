package com.example.demo.config.id;

import com.example.demo.service.IdBuilder;
import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.tomcat.util.net.IPv6Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author maotan
 * @version 1.0 2019/3/29 17:19 Exp$
 * @Description
 */
@Configuration
public class IdConfiguration {

    @Bean
    public LoadingCache getLoadingCache(){
        final int initial = 0;
        final int interval = 1;
        return CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.SECONDS)
                .build(new CacheLoader<Integer, IdBuilder>(){
                    @Override
                    public IdBuilder load(Integer type) throws Exception{
                        LongAdder longAdder = new LongAdder();
                        longAdder.add(initial);
                        IdBuilder idBuilder = new IdBuilder(String.valueOf(ZonedDateTime.now().toEpochSecond()),
                                longAdder, getIpLastSegment(), getTypeString(type),interval);
                        return idBuilder;
                    }
                });
    }

    private String getIpLastSegment(){
        return "123";
    }
    private String getTypeString(Integer type){
        return Strings.padStart(String.valueOf(type%100),2, '0');
    }
}
