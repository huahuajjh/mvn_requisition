package com.tq.requisition.test.infrastructure.repository;

import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.domain.model.account.IAccountRepository;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestHql {
	private IAccountRepository repository;
	
	@Before
	public void init() {
		repository = (IAccountRepository) ServiceLocator.instance().getService("accountRepository", IAccountRepository.class).setAggregatorRootClass(Account.class);
	}
	
	@Test
	public void hqlQuery() throws Exception, InvocationTargetException, InstantiationException {
//		List<Account> list = repository.getAll(new SpecificationExt<Account>(Account.class) {
//			
//			@Override
//			public String getAbsHql() {
//				return "select a.id,a.account from Account a";
//			}
//
//			@Override
//			public String getAbsSql() {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public Object[] getAbsParameters() {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public OperationType getAbsType() {
//				return OperationType.HQL;
//			}
//		});
//		
//		for (Account account : list) {
//			System.out.println(account);
//		}
		
		//------------------------------------------
//		Session session = HibernateUtils.session();
//		Query query = session.createQuery("select new map(a.account as acc,a.pwd as pwd) from Account a");
//		List<Map<String, Object>> l = query.list();
//		List<Acc> list = new ArrayList<Acc>();
//		for (Map<String, Object> object : l) {			
//			list.add((Acc) Hql2Pojo.toPojo(object, Acc.class));
//		}
//		for (Acc acc : list) {
//			System.out.println(acc.toString());
//		}
	}
}
