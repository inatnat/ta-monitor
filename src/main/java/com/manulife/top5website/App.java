package com.manulife.top5website;

import java.io.IOException;

import org.apache.log4j.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.manulife.BeanFactory;
import com.manulife.top5website.service.MonitorService;



public class App 
{
    public static void main( String[] args )
    {
    	
    	final Logger logger = Logger.getLogger(App.class);
    	FileMonitor fileMonitor = BeanFactory.createInstance(FileMonitor.class, "fileMonitor");
    	MonitorService monitorService = BeanFactory.createInstance(MonitorService.class, "monitorService");
    	
    	try {
    		logger.info("startMonitor on path " + fileMonitor.getLocation());
    		monitorService.startMonitor(fileMonitor);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			logger.error("stopMonitor error: ", e);
			
		}
        
        
    }
}
