package com.tq.requisition.test.domain.service;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.service.idomainservice.IPermissionService;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestPermission {
	private IPermissionService pmsService;
	@Before
	public void init() {
		pmsService = ServiceLocator.instance().getService("pmsServiceDomain", IPermissionService.class);
	}
	
	@Test
	public void assignRole4User() {
		pmsService.assignRole4User(UUID.fromString("d5e82153-2733-45fd-b457-32993cc659f9"),//
				UUID.fromString("ac7b18d9-4ea4-476f-9924-5f567a8ca5b1"),//
						UUID.fromString("a00b5e82-7e0e-48d4-99a8-9ff7a2f9db2b")
				);
	}
	
	@Test
	public void assignRes4Role() {
		pmsService.assignRes4Role(UUID.fromString("ac7b18d9-4ea4-476f-9924-5f567a8ca5b1"), //
				new UUID[]{
			UUID.fromString("8343b954-9afd-4b12-98d5-8245bda02550"),
			UUID.fromString("8343b954-9afd-4b12-98d5-8245bda02550"),
		});
	}	

	@Test
	public void getAllResource() {
		String json = pmsService.getAllRescourses(UUID.fromString("ac7b18d9-4ea4-476f-9924-5f567a8ca5b1"));
		System.out.println(json);
	}
}
