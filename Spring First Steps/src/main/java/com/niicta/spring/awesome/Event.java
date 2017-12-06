package com.niicta.spring.awesome;

import java.text.DateFormat;
import java.util.Date;

public class Event
{
    private int id;
    private String message;
    private Date date;
    private DateFormat df;

    public Event(){
        this.id = (int)(Math.random()*1000);
    }

    public Event(Date date, DateFormat df){
        this();
        this.date = date;
        this.df = df;
    }

    public void setMessage(String msg){
        this.message = msg;
    }

    public String getMessage(){
        return message;
    }

    @Override
    public String toString(){
        StringBuffer string = new StringBuffer("Event ");
        string.append("ID = ").append(id)
                .append(" date = ").append(df.format(date))
                .append(" message = ").append(message)
                .append('\n');
        return string.toString();
    }
}
