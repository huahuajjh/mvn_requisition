package com.tq.requisition.test.application;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.presentation.serviceContract.share.IShareTypeServiceContract;

public class TestShare {
	private IShareTypeServiceContract service;
	
	@Before
	public void init() {
		service = ServiceLocator.instance().getService("shareTypeService", IShareTypeServiceContract.class); 
	}
	
	@Test
	public void editHH() {
		String str = service.editHouseholdType(UUID.fromString("b0b9a45f-29e5-48a4-be1e-4d7359d0d331"), "jjh1w1");
		System.out.println(str);
	}
	
	@Test
	public void add() {
		String str = service.addHouseholdType("jjhw1");
		System.out.println(str);
	}
	
	@Test
	public void del() {
		String str = service.delHouseholdType(UUID.fromString("65f647b6-e295-497e-bb96-cd711dcea4be"));
		System.out.println(str);
	}
}
