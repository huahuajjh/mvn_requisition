package com.tqmars.requisition.test.utils;

import java.util.List;

import javax.management.Query;

import org.junit.Test;

import com.tqmars.requisition.infrastructure.Repository.HibernateUtils;

public class TestHql {
	@Test
	public void testHql() {
		HibernateUtils.session().beginTransaction();
		Query query = HibernateUtils.session().createQuery("select new com.tqmars.requisition.test.utils.FI(f.headName,i.birthday) from Family f,FamilyItem i where f.id = i.fmlId");
		query.setFirstResult(1);
		query.setMaxResults(2);
		List<FI> list = query.list();
		for (FI fi : list) {
			System.out.println(fi.toString());
		}
	}
}
