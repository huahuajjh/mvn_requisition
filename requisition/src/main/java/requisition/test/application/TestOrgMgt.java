package com.tq.requisition.test.application;


import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.presentation.dto.sysMgt.OrgDto;
import com.tq.requisition.presentation.serviceContract.sysManagement.IOrgMgtService;

public class TestOrgMgt {
	private IOrgMgtService service;
	@Before
	public void init() {
		service = ServiceLocator.instance().getService("orgMgtService", IOrgMgtService.class);
	}
	
	@Test
	public void createOrg() {
		String json = service.addOrg(new OrgDto("国土局", UUID.randomUUID(), "#001"));
		System.out.println(json);
		
	}
	
	@Test
	public void modifyOrg() {
		String json = service.editOrgInfo(new OrgDto("国安局", UUID.fromString("3465302d-94ca-4da1-a860-b28581b70a1b"), "#007"));
		System.out.println(json);
	}
	
	@Test
	public void deleteOrg() {
		String json = service.deleteByOrgKey(UUID.fromString("de391b3b-2dd6-45e3-a6e1-43057d2ec92b"));
		System.out.println(json);
	}

	@Test
	public void query() {
		String str = service.getOrgListJson();
		System.out.println(str);
	}
}
