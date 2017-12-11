package com.niicta.spring.awesome.logger.impl;

import com.niicta.spring.awesome.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger
{
    private int maxCacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String fileName, int maxCacheSize){
        super(fileName);
        this.maxCacheSize = maxCacheSize;
        cache = new ArrayList<Event>();
    }

    @Override
    public void logEvent(Event event){
        if(cache.size() < maxCacheSize)
        {
            cache.add(event);
        }
        else
        {
            flushCacheToFile();
            cache.clear();
        }
    }

    private void destroy(){
        flushCacheToFile();
    }

    private void flushCacheToFile(){
        for(Event cachedEvent: cache){
            super.logEvent(cachedEvent);
        }
    }

    @Override
    public void init() throws IOException{
        super.init();
    }
}
