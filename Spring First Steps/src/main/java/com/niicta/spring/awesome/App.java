package com.niicta.spring.awesome;

import com.niicta.spring.awesome.logger.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    private static ApplicationContext applicationContext;
    private Client client;
    private EventLogger eventLogger;


    public App(Client client, EventLogger eventLogger){
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public void logEvent(Event event){
        String message = event.getMessage().replaceAll(client.getId(), client.getFullName());
        event.setMessage(message);
        eventLogger.logEvent(event);
    }

    public static void main(String[] args) throws InterruptedException{
        applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        App app = applicationContext.getBean(App.class);
        Event event1 = applicationContext.getBean(Event.class);
        event1.setMessage("Event for user 1");
        Thread.sleep(1000);
        Event event2 = applicationContext.getBean(Event.class);
        event2.setMessage("Event for user 2");
        app.logEvent(event1);
        app.logEvent(event2);
    }

}
