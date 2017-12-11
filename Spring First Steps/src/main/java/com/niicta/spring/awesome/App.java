package com.niicta.spring.awesome;

import com.niicta.spring.awesome.logger.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    private static ConfigurableApplicationContext applicationContext;
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
        Event event3 = applicationContext.getBean(Event.class);
        event3.setEventType(EventType.ERROR);
        event3.setMessage("Error for user 1");
        Event event4 = applicationContext.getBean(Event.class);
        event4.setEventType(EventType.ERROR);
        event4.setMessage("Error for user 2");
        Event event5 = applicationContext.getBean(Event.class);
        event5.setEventType(EventType.INFO);
        event5.setMessage("Info for user 1");
        Event event6 = applicationContext.getBean(Event.class);
        event6.setEventType(EventType.INFO);
        event6.setMessage("Info for user 2");

        app.logEvent(event1);
        app.logEvent(event2);
        app.logEvent(event3);
        app.logEvent(event4);
        app.logEvent(event5);
        app.logEvent(event6);
        applicationContext.close();
    }

}
