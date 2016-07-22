package com.tq.requisition.test.application;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.presentation.serviceContract.rmHousehold.IFamilyItemServiceContract;

public class TestFmlItem {
	private IFamilyItemServiceContract service;
	
	@Before
	public void init() {
		service = ServiceLocator.instance().getService("fmlItemService", IFamilyItemServiceContract.class);
	}
	
	@Test
	public void getById() {
		String str = service.queryByIdNumber("44444444444455555555");
		System.out.println(str);
	}

	@Test
	public void getByIdnumAndName() {
		String str = service.queryByIdNumberAndName("12222222222222222222", "小明");
		System.out.println(str);
	}
}
