package com.manulife.top5website.tests.model;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.manulife.BeanFactory;
import com.manulife.top5website.dao.SiteDao;
import com.manulife.top5website.model.Site;


public class ModelTest {
	@Test
	public void siteModelTest() {
		Site site = new Site();
		site.setWebsite("facebook.com");
		site.setVisits(10000);
		
		
		
		
	}
	
}
