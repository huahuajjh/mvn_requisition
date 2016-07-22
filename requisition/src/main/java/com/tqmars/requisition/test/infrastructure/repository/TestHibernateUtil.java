package com.tqmars.requisition.test.infrastructure.repository;

import org.junit.Test;

import com.tqmars.requisition.infrastructure.Repository.HibernateUtils;

import junit.framework.Assert;

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
