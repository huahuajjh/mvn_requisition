package com.tq.requisition.test.infrastructure.repository;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.domain.model.roleAccount.IRoleAccountRepository;
import com.tq.requisition.domain.model.roleAccount.RoleAccount;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestRoleAccountRepository {
	private IRepository<RoleAccount> roleAccRepository;
	
	@Before
	public void init() {
		roleAccRepository = ServiceLocator.instance().getService("roleAccRepository",IRoleAccountRepository.class).setAggregatorRootClass(RoleAccount.class);
	}
	
	@Test
	public void addRoleAcc() {
		RoleAccount rc = new RoleAccount();
		rc.setAccountId(UUID.fromString("e78d5d99-e5da-4edf-8ce8-c991c1891662"));
		rc.setId(UUID.randomUUID());
		rc.setRoleId(UUID.fromString("a00b5e82-7e0e-48d4-99a8-9ff7a2f9db2b"));
		roleAccRepository.add(rc);
		roleAccRepository.context().commit();
	}
	
	@Test
	public void queryRoleAcc() {
		RoleAccount rc = roleAccRepository.getByKey(RoleAccount.class, UUID.fromString("8a7dc951-a086-4571-b06b-3733514fee8f"));
		System.out.println(rc.toString());
	}
	
}
