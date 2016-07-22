package com.tq.requisition.test.application;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.presentation.dto.sysMgt.AccountSafeDto;
import com.tq.requisition.presentation.serviceContract.userAssociated.IUserService;

public class TestUserService {
	private IUserService userService;
	
	@Before
	public void init() {
		userService = ServiceLocator.instance().getService("userService", IUserService.class);
	}
	
	@Test
	public void login() {
		boolean r = userService.login("703825021", "huahuajjh");
		
		System.out.println(r);
	}
	
	@Test
	public void changePwd() {
//		try {
//			userService.changePwd(UUID.fromString("042952b7-327f-45df-91b7-95c053df5b4a"), "newpwd1", "huahuajjh1");
//		} catch (DomainException e) {
//			// TODO Auto-generated catch block
//			System.out.println(e.getMessage());
//		}
	}
	
	@Test
	public void getById() {
		 AccountSafeDto dto = userService.getUserById(UUID.fromString("00ec4f44-431b-4a4d-993f-b72ff66e1fbc"));
		 System.out.println(dto.getName());
	}
}
