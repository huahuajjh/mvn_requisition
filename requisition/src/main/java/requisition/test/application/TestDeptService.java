package com.tq.requisition.test.application;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.presentation.dto.sysMgt.DeptDto;
import com.tq.requisition.presentation.serviceContract.sysManagement.IDeptMgtService;

public class TestDeptService {
	private IDeptMgtService service;

	@Before
	public void init() {
		service = ServiceLocator.instance().getService("deptService",
				IDeptMgtService.class);
	}

	@Test
	public void createDept() throws DomainException {
		DeptDto dto = new DeptDto("组织部", UUID.randomUUID(), UUID.randomUUID());
		String json = service.createDept(dto);
		System.out.println(json);
	}

	@Test
	public void editDept() throws DomainException {
		DeptDto dto = new DeptDto("新宣部", UUID.fromString("2582672b-a338-42d7-a7f9-435291e87538"), UUID.randomUUID());
		String json = service.edit(dto);
		System.out.println(json);
	}
	
	@Test
	public void getDeptListByOrgId() {
		String json = service.getDeptDtoListByOrgIdToJson(UUID.fromString("1027cbbe-9d2f-4dd8-95e8-9ff5162c3e94"));
		System.out.println(json);
		
	}
	
	@Test
	public void delDept() {
		String json = service.delete(UUID.fromString("cefa2519-8397-465b-9438-8b9c7938daeb"));
		System.out.println(json);
	}
}
