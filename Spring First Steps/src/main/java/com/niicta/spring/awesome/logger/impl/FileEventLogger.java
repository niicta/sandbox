package com.niicta.spring.awesome.logger.impl;

import com.niicta.spring.awesome.Event;
import com.niicta.spring.awesome.logger.EventLogger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger
{
    private String fileName;

    public FileEventLogger(String fileName){
        this.fileName = fileName;
    }

    private File getLogFile(){
        File file = new File(fileName);
        return file;
    }

    public void logEvent(Event event){
        File logFile = getLogFile();
        try
        {
            FileUtils.writeStringToFile(logFile, event.toString(), true);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void init() throws IOException{
        File file = getLogFile();
        if (!file.canWrite()){
            throw new IOException("can't initialize FileLogger. Can't write to file "+ fileName);
        }
    }

}
