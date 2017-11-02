package com.manulife.top5website.tests.dao;

import java.util.Date;

import org.junit.Test;

import com.manulife.BeanFactory;
import com.manulife.top5website.dao.SiteDao;
import com.manulife.top5website.model.Site;

public class DaoTest {
	@Test
	public void siteAddTest() {
		Site site = new Site();
		site.setWebsite("facebook.com");
		site.setVisits(10000);
		site.setDate(new Date());
		
		SiteDao siteDao = BeanFactory.createInstance(SiteDao.class, "siteDao");
    	siteDao.save(site);
	}
}
