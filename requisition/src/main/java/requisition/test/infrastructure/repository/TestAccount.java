package com.tq.requisition.test.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.Specification.ISpecification;
import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.domain.model.account.IAccountRepository;
import com.tq.requisition.exception.ChangePwdException;
import com.tq.requisition.infrastructure.Repository.HbRepository;
import com.tq.requisition.infrastructure.Repository.HbRepositoryContext;
import com.tq.requisition.infrastructure.serviceLocator.IServiceLocator;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestAccount {
	IServiceLocator serviceLocator;
	HbRepository<Account> repository;
	private OperationType type;
	
	@Before
	public void init() {
		serviceLocator = ServiceLocator.instance();
		repository = (HbRepository<Account>) serviceLocator.getService("accountRepository",IRepository.class).setAggregatorRootClass(Account.class);
	}
	
	@Test
	public void accountAddTest() {
//		for (int i = 0; i < 100; i++) {
//			Account account = new Account(UUID.randomUUID(),"jjh"+i,"huahuajjh3","jjh",AccountState.ENABLE);
//			repository.add(account);
//		}
//		repository.context().commit();
//		Account account = new Account(UUID.randomUUID(),"jjhhh","huahuajjh3","jjh",AccountState.ENABLE);
//		repository.add(account);
//		repository.context().commit();
	}
	
	@Test
	public void accountRepositoryAdd() {		
//		repository.context().beginTransaction();
//		Account account = new Account(UUID.randomUUID(),"2859014312","huahaujjh3","name",AccountState.ENABLE);
//		repository.add(account);
//		repository.context().commit();
	}
	
	@Test
	public void accountRepositoryUpdate() {
		Account account = repository.getByKey(Account.class, UUID.fromString("e78d5d99-e5da-4edf-8ce8-c991c1891662"));
		account.setName("习近平");
		repository.update(account);
		repository.context().commit();
	}	
	
	@Test
	public void accountRepositoryRemove() {		
		Account account = repository.getByKey(Account.class, UUID.fromString("35c1bbaa-8226-426e-b149-a655ef82c579"));
		if(account != null)
		{
			repository.remove(account);
			repository.context().commit();
		}
	}
	
	@Test
	public void userExit() {
//		 IAccountRepository userRepository = (IAccountRepository) serviceLocator.getService(IAccountRepository.class).setAggregatorRootClass(Account.class);
//		 Account account = new Account(UUID.randomUUID(), "703825021", "234", "jjh", AccountState.ENABLE);
//		 List<Account> list = userRepository.getAll(new UserExistsSpecification(Account.class, account));	
//		 for (Account account2 : list) {
//			System.out.println(account2.toString());
//		}
	}
	
	@Test
	public void accountCriteriaQuery() {
//		List<Account> accounts = repository.getAll(new UserExistsSpecification(Account.class,//
//				new Account(UUID.randomUUID(), "703825021", "huahuajjh", "a",AccountState.ENABLE)),//
//				0, 1, 25);
//		for (Account account : accounts) {
//			System.out.println(account);
//		}
	}
	
	@Test
	public void accountHqlQuery() {		
		IAccountRepository repository = (IAccountRepository) serviceLocator.getService("accountRepository", IAccountRepository.class).setAggregatorRootClass(Account.class);
		List<Account> list = repository.getAll(
				new SpecificationExt<Account>(Account.class) {
					@Override
					public String getAbsHql() {
						return "from Account a where a.account=? and a.pwd=?";
					}
					@Override
					public String getAbsSql() {
						return "select * from tb_account where account=? and pwd=?";
					}
					@Override
					public Object[] getAbsParameters() {
						return new Object[]{"703825021","huahuajjh"};
					}
					@Override
					public OperationType getAbsType() {
						return OperationType.HQL;
					}
				});
		
		for (Account account : list) {
			System.out.println(account.toString());
		}
	}
	
	@Test
	public void changePwd() {
		Account account = repository.getByKey(Account.class, UUID.fromString("fe622cad-45ea-4833-ad9b-1b48f981cbf8"));
		try {
			account.changePwd("huahaujjh5", "huahaujjh6");
		} catch (ChangePwdException e) {
			System.err.println(e.getMessage());
		}
		repository.update(account);
		repository.context().commit();
	}
	
	@Test
	public void paramTest() {
		HbRepositoryContext context = (HbRepositoryContext) serviceLocator.getService("hbRepositoryContext", IRepositoryContext.class);
		List<Account> list = context.session().createSQLQuery("select * from tb_permission p left join tb_role r on p.role_id = r.id").addEntity(Account.class)//
			.setParameter(0, "jjh1").list();
		for (Account account : list) {
			System.out.println(account.toString());
		}
	}
	
	@Test
	public void paramHqlTest() {
		List<Account> list = repository.getAll(new SpecificationExt<Account>(Account.class) {
			@Override
			public String getAbsHql() {
				return "from Account where account=? and pwd=?";
			}

			@Override
			public String getAbsSql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{"703825021","huahuajjh"};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.HQL;
			}
		});
		for (Account account : list) {
			System.out.println(account.toString());
		}
		
	}
	
	@Test
	public void typeTest() {
		Assert.assertNull(type);
		ISpecification<Account> specification = new SpecificationExt<Account>(Account.class) {

			@Override
			public String getAbsHql() {
				return "hql";
			}

			@Override
			public String getAbsSql() {
				return "sql";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{1,2,3};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.CRITERIA;
			}
			
		};
		IHqlExpression expression = specification.getHqlExpression();
		System.out.println(expression.getHql());
		System.out.println(expression.getSql());		
		System.out.println(expression.getType());
		for (int i = 0; i < expression.getParameters().length; i++) {
			System.out.println(expression.getParameters()[i]);
		}
	}
	
	@Test
	public void login() {
		IAccountRepository rep = (IAccountRepository) repository;
		System.out.println(rep.login("test123", "1234567"));
	}
	
	@Test
	public void existsTest() {
		IAccountRepository rep = (IAccountRepository) repository;
		boolean r = rep.exists(new SpecificationExt<Account>(Account.class) {
			@Override
			public String getAbsHql() {
				return "hql";
			}
			@Override
			public String getAbsSql() {
				return "select count(1) from tb_account where account=? and pwd=?";
			}
			@Override
			public Object[] getAbsParameters() {
				return new Object[]{"jjh1","huahuajjh3"};
			}
			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		//Assert.assertEquals(true, r);
		System.out.println(r);
		
//		HbRepositoryContext context = (HbRepositoryContext) serviceLocator.getService("hbRepositoryContext",IRepositoryContext.class);
//		Query query = context.session().createSQLQuery("select count(1) from tb_account where account=? and pwd=?").setParameter(0, "jjsh1").setParameter(1, "huahuajjh3");
//		List<?> list = query.list();
//		for (Object object : list) {
//			System.out.println(object);
//		}
		
	}
	
	@Test
	public void modifyAccount() {
		repository.executeUpdate(new SpecificationExt<Account>(Account.class) {

			@Override
			public String getAbsHql() {
				return "update Account set pwd=? where account=?";
			}

			@Override
			public String getAbsSql() {
				return "update tb_account set pwd=? where account=?";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{"234","703825021"};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.HQL;
			}
		});
		repository.context().commit();
	}
	
}
