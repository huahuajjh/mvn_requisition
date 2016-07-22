package com.tq.requisition.test.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.socialsecurityMgt.NewSocialsecurityDto;
import com.tq.requisition.presentation.dto.socialsecurityMgt.SocialsecurityQueryModel;
import com.tq.requisition.presentation.serviceContract.socialsecurityMgt.ISocialsecurityMgtServiceContract;

public class TestSS {
	private ISocialsecurityMgtServiceContract service;
	
	@Before
	public void init() {
		service = ServiceLocator.instance().getService("ssService", ISocialsecurityMgtServiceContract.class);
	}
	
	@Test
	public void addSS() {
		NewSocialsecurityDto dto = new NewSocialsecurityDto();
		dto.setFmlItemId(UUID.fromString("4da544da-1c64-427c-a3f5-4a7d26bd228b"));
		dto.setOprDate(new Date());
		dto.setOprUserId(UUID.randomUUID());
		dto.setSocialsecurityDate(new Date());
		dto.setSocialsecurityTypeId(UUID.randomUUID());
		dto.setDel(false);
		dto.setEndowmentInsuranceYear(1);
		dto.setIsSocialsecurity(false);
		dto.setJoinWhichMedicalInsurance("joinWhichMed");
		dto.setOprDate(new Date());
		dto.setMedicalInsuranceMonth(10);
		dto.setPrisonTime(10);
		dto.setServeArmyTime(10);
		dto.setSocialsecurityDate(new Date());
		dto.setSocialsecurityTypeId(UUID.randomUUID());
		
		String str = service.addSSInfo(dto);
		System.out.println(str);
	}
	
	@Test
	public void addBatchSS() {
		NewSocialsecurityDto dto = new NewSocialsecurityDto();
		dto.setFmlItemId(UUID.fromString("1370ce36-1d52-4404-ae6b-033aa14ef4b2"));
		dto.setOprDate(new Date());
		dto.setOprUserId(UUID.randomUUID());
		dto.setSocialsecurityDate(new Date());
		dto.setSocialsecurityTypeId(UUID.randomUUID());
		
		NewSocialsecurityDto dto1 = new NewSocialsecurityDto();
		dto1.setFmlItemId(UUID.fromString("5cfc8fef-3c4c-4332-b0b8-2827b9e3d69f"));
		dto1.setOprDate(new Date());
		dto1.setOprUserId(UUID.randomUUID());
		dto1.setSocialsecurityDate(new Date());
		dto1.setSocialsecurityTypeId(UUID.randomUUID());
		
		NewSocialsecurityDto dto2 = new NewSocialsecurityDto();
		dto2.setFmlItemId(UUID.fromString("7f02d3e6-ec3b-44d2-b04d-4720f971a840"));
		dto2.setOprDate(new Date());
		dto2.setOprUserId(UUID.randomUUID());
		dto2.setSocialsecurityDate(new Date());
		dto2.setSocialsecurityTypeId(UUID.randomUUID());
		
		List<NewSocialsecurityDto> list = new ArrayList<NewSocialsecurityDto>();
		list.add(dto);
		list.add(dto1);
		list.add(dto2);
		String str = service.addBatch(list);
		System.out.println(str);
	}
	
	@Test
	public void query() {
		SocialsecurityQueryModel queryModel = new SocialsecurityQueryModel();
		queryModel.setCommunityId(null);
		queryModel.setStreetId(null);
		
		PageModel pageModel = new PageModel();
		pageModel.pageIndex = 1;
		pageModel.pageSize = 10;
		String str = service.query4TableByFuzzy(queryModel, pageModel);
		System.out.println(str);
		
	}

	@Test
	public void query4Add() {
		SocialsecurityQueryModel queryModel = new SocialsecurityQueryModel();
		queryModel.setCommunityId(null);
		queryModel.setStreetId(null);
		
		PageModel pageModel = new PageModel();
		pageModel.pageIndex = 1;
		pageModel.pageSize = 10;
		String str = service.queryFuzzyByAddJson(queryModel, pageModel);
		System.out.println(str);
	}
}
