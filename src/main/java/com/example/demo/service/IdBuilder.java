package com.example.demo.service;

import com.google.common.base.Strings;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author maotan
 * @version 1.0 2019/3/29 17:29 Exp$
 * @Description
 */
public class IdBuilder {
    private String time;
    private LongAdder seq;
    private String machineId;
    private String type;
    private int interval;

    public String next() throws Exception{
        seq.add(interval);
        return time + seqStringCache.get(seq.sum()) + type + machineId;
    }

    public static LoadingCache<Long, String> seqStringCache = CacheBuilder.newBuilder()
            .build(new CacheLoader<Long, String>() {
                @Override
                public String load(Long key) throws Exception {
                    return Strings.padStart(String.valueOf(key % 10000), 4, '0');
                }
            });


    public IdBuilder(String time, LongAdder seq, String machineId, String type, int interval){
        this.time = time;
        this.seq = seq;
        this.machineId = machineId;
        this.type = type;
        this.interval = interval;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LongAdder getSeq() {
        return seq;
    }

    public void setSeq(LongAdder seq) {
        this.seq = seq;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
