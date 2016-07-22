package com.tq.requisition.test.application;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.presentation.dto.sysMgt.RoleDto;
import com.tq.requisition.presentation.serviceContract.sysManagement.IRoleService;

public class TestRoleServiceImpl {
	private IRoleService roleService;

	@Before
	public void init() {
		roleService = ServiceLocator.instance().getService("roleService",
				IRoleService.class);
	}

	@Test
	public void createRole() {
		RoleDto dto = new RoleDto("管理员", UUID.randomUUID());
		String json = roleService.addRole(dto);
		System.out.println(json);
	}

	@Test
	public void modifyRole() {
		RoleDto dto = new RoleDto("普通1",
				UUID.fromString("13873e76-836a-4c1b-b975-72b0f7fc87e2"));
		String json = roleService.updateRole(dto);
		System.out.println(json);
	}

	@Test
	public void getAllRoleJson() {
		String json = roleService.getListJson("v", 1, 2);
		System.out.println(json);
	}

}
