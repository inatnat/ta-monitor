package com.manulife.top5website.dao;

import java.util.List;

import com.manulife.top5website.model.Site;

public interface SiteDao {
	
	public void save(List<Site> sites);

	public void save(Site site);
}
