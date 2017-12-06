package com.niicta.spring.awesome.logger.impl;

import com.niicta.spring.awesome.Event;
import com.niicta.spring.awesome.logger.EventLogger;

public class ConsoleEventLogger implements EventLogger
{
    public void logEvent(Event event){
        System.out.println(event);
    }
}
