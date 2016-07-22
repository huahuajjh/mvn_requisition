package com.tq.requisition.test.infrastructure.repository;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

import com.tq.requisition.infrastructure.Repository.HibernateUtils;

public class TestHibernateUtil {
	@Test
	public void sessionTest() {
		Assert.assertNotNull(HibernateUtils.session());
		Session session = HibernateUtils.session();
		SQLQuery query = session.createSQLQuery("show tables");
		Object object = query.list();
		System.out.println(object);
		
	}
}
