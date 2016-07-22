package com.tqmars.requisition.test.spring;

import java.util.UUID;

import org.junit.Test;

import com.tqmars.requisition.domain.IRepository.IRepository;
import com.tqmars.requisition.domain.model.account.Account;
import com.tqmars.requisition.infrastructure.Repository.HbRepositoryContext;
import com.tqmars.requisition.infrastructure.Repository.HibernateUtils;
import com.tqmars.requisition.infrastructure.serviceLocator.IServiceLocator;
import com.tqmars.requisition.infrastructure.serviceLocator.ServiceLocator;

import junit.framework.Assert;

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
