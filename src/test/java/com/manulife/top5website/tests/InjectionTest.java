package com.manulife.top5website.tests;
import static org.junit.Assert.*;
import org.junit.Test;
import com.manulife.BeanFactory;
import com.manulife.top5website.FileMonitor;
import com.manulife.top5website.service.MonitorService;

public class InjectionTest {

	@Test
	public void createBeanTest() {
		FileMonitor fileMonitor = BeanFactory.createInstance(FileMonitor.class, "fileMonitor");
    	MonitorService monitorService = BeanFactory.createInstance(MonitorService.class, "monitorService");
    	
    	assertNotNull(fileMonitor);
    	assertNotNull(monitorService);
    	
    	
	}
	
}
