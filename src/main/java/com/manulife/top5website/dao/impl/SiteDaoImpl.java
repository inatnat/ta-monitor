package com.manulife.top5website.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.manulife.top5website.dao.SiteDao;
import com.manulife.top5website.model.Site;
@Repository
public class SiteDaoImpl implements SiteDao{

	private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	
	

	@Override
	public void save(List<Site> sites) {
		String hql = String.format("delete from Site");
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Query query = (Query) session.createQuery(hql);
		query.executeUpdate();
		for (Site site : sites) {
			session.persist(site);
		}
		tx.commit();
		session.close();
		
	}



	@Override
	public void save(Site site) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(site);
		tx.commit();
		session.close();
	}

}
