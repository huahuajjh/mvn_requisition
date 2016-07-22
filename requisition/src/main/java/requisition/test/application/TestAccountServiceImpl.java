package com.tq.requisition.test.application;


import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.exception.AccountOperationException;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.presentation.serviceContract.sysManagement.IAccountService;

public class TestAccountServiceImpl {
	private IAccountService service;
	
	@Before
	public void init() {
		service = ServiceLocator.instance().getService("accountService", IAccountService.class);
	}
	
	@Test
	public void createAccount() throws AccountOperationException {
//		AccountDto accountDto = new AccountDto(UUID.randomUUID(), "703825021", "huahuajjh", "js", UUID.fromString("4e96541f-8569-4784-a852-cea3b1374a03"), UUID.fromString("1597ef9d-cdc1-4838-a058-5917ebf232ef"), UUID.fromString("a00b5e82-7e0e-48d4-99a8-9ff7a2f9db2b"), AccountState.ENABLE);
//		service.createAccount(accountDto);
	}

	@Test
	public void queryByPage() {
		String str = service.getAccountList("huahuajjh", null, UUID.fromString("d8801dc7-8743-4431-8c1a-c51f665bff60"), UUID.fromString("c8a7381b-233d-439e-ad69-21935694d89b"), 1, 20);
		System.out.println(str);
	}
	
	@Test
	public void existsByAccount(){
//		Boolean state = service.checkAccountExists("admin1");
//		if (state) {
//			System.out.println("1");
//		} else {
//			System.out.println("0");
//		}
	}
}
