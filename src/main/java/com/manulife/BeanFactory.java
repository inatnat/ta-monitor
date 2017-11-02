package com.manulife;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class BeanFactory {
	
	@SuppressWarnings("unchecked")
	public static <T> T createInstance(Class<T> cls, String beanName) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
		return cls.cast(context.getBean(beanName));
	}
}
