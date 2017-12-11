package com.niicta.spring.awesome.logger.impl;

import com.niicta.spring.awesome.Event;
import com.niicta.spring.awesome.EventType;
import com.niicta.spring.awesome.logger.EventLogger;

import java.util.List;
import java.util.Map;

/**
 * Created by niict on 09.12.2017.
 */
public class CombinedEventLogger implements EventLogger {
    private Map<EventType, List<EventLogger>> loggers;
    private EventLogger defaultLogger;

    public CombinedEventLogger(Map<EventType, List<EventLogger>> loggers, EventLogger defaultLogger) {
        this.loggers = loggers;
        this.defaultLogger = defaultLogger;
    }

    public void logEvent(Event event) {
        EventType eventType = event.getEventType();
        if (eventType == null){
            defaultLogger.logEvent(event);
        }
        else {
            List<EventLogger> eventLoggers = loggers.get(eventType);
            if(eventLoggers == null){
                defaultLogger.logEvent(event);
            }
            else {
                for (EventLogger eventLogger: eventLoggers) {
                    eventLogger.logEvent(event);
                }
            }
        }
    }
}
