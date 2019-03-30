package com.example.demo.service;

import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author maotan
 * @version 1.0 2019/3/30 9:01 Exp$
 * @Description
 */
@Service
public class IdService {
    @Autowired
    private LoadingCache<Integer, IdBuilder> loadingCache;

    public String genId(Integer type) throws Exception{
        return loadingCache.get(type).next();
    }
}
