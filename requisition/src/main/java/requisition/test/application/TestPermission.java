package com.tq.requisition.test.application;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.presentation.dto.sysMgt.ResDto;
import com.tq.requisition.presentation.serviceContract.sysManagement.IGetResService;

public class TestPermission {
	private IGetResService service;
	
	@Before
	public void init() {
		service = ServiceLocator.instance().getService("getResService", IGetResService.class);
	}
	
	@Test
	public void res() {
		List<ResDto> list = service.getResByUserId(UUID.fromString("a8a1c6fe-4b62-48b9-bc79-1b9ae74deda9"));
		for (ResDto resDto : list) {
			System.out.println(resDto.getName());
		}
	}
}
