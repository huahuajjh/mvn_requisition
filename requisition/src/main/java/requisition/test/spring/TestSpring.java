package com.tq.requisition.test.spring;

import java.util.UUID;

import org.hibernate.SQLQuery;
import org.junit.Assert;
import org.junit.Test;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.infrastructure.Repository.HbRepositoryContext;
import com.tq.requisition.infrastructure.Repository.HibernateUtils;
import com.tq.requisition.infrastructure.serviceLocator.IServiceLocator;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestSpring {
	HbRepositoryContext context;
	IServiceLocator serviceLocator;

	@Test
	public void init() {
		serviceLocator = ServiceLocator.instance();
		context = (HbRepositoryContext) serviceLocator
				.getService("accountRepository",IRepository.class).setAggregatorRootClass(Account.class);
	}

	@Test
	public void springTest() {
		Assert.assertNotNull(context);
		context.commited(true);
		Assert.assertEquals(true, context.commited());
		SQLQuery query = context.session().createSQLQuery("show tables");
		System.out.println(query.list());

	}

	@Test
	public void hbRepository() {
		IRepository<Account> repository = serviceLocator.getService("accountRepository",
				IRepository.class).setAggregatorRootClass(Account.class);
		// Assert.assertNotNull(repository);
		Account account = repository.getByKey(Account.class,
				UUID.fromString("fe622cad-45ea-4833-ad9b-1b48f981cbf8"));
		System.out.println(account);
	}

	@Test
	public void classPath() {
//		ApplicationContext context = new FileSystemXmlApplicationContext(
//				"WebRoot/WEB-INF/config/spring.xml");
		//ApplicationContext springContext = new ClassPathXmlApplicationContext("../config/spring.xml");
	}

	@Test
	public void hibernateTest() {
		HibernateUtils.session().createSQLQuery("show tables");
	}

	
}
