package com.tq.requisition.test.utils;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.Test;

import com.tq.requisition.infrastructure.Repository.HibernateUtils;

public class TestHibernate {
	@Test
	public void session() {
		Session session = HibernateUtils.session();
		session.beginTransaction();
		System.out.println(session.isConnected());
	}

	@Test
	public void fuzzyQuery() {
		Session session = HibernateUtils.session();
		session.beginTransaction();
		SQLQuery sql = session.createSQLQuery("select pro_name from tb_project where pro_name like '%n%'");
		sql.setFirstResult(1);
		sql.setMaxResults(1);
		List<String> list = sql.list();		
		for (String string : list) {
			System.out.println(string);
		}
	}

	@Test
	public void init() {
		HibernateUtils.session();
	}
}
