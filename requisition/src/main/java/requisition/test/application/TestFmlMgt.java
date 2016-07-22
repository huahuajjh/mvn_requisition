package com.tq.requisition.test.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.model.share.Gender;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyDto;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemDto;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.serviceContract.rmHousehold.IFamilyItemServiceContract;
import com.tq.requisition.presentation.serviceContract.rmHousehold.IFamilyMgtServiceContract;

public class TestFmlMgt {
	private IFamilyItemServiceContract service;
	private IFamilyMgtServiceContract fmlService;

	@Before
	public void init() {
		fmlService = ServiceLocator.instance().getService("fmlService", IFamilyMgtServiceContract.class);
	}

	@Test
	public void add() {
		FamilyItemDto dto = new FamilyItemDto();
		dto.setAddress("address");
		dto.setBirthday(new Date());
		dto.setCommunityId(UUID.randomUUID());
		dto.setCurrentEducationSituation("受教育情况");
		dto.setEducationLevel("受教育程度");
		dto.setFarmingTime("20");
		dto.setFmlId(UUID.randomUUID());
		dto.setGender(Gender.MALE);
		dto.setGroupId(UUID.randomUUID());
		dto.setHalf(false);
		dto.setHouseholdId(UUID.randomUUID());
		dto.setHouseholdStr("户口类型");
		dto.setIdNumber("21512451445");
		dto.setName("name");
		dto.setOnlyChildNumber("12255");
		dto.setProId(UUID.randomUUID());
		dto.setProName("pro name");
		dto.setRelationshipId(UUID.randomUUID());
		dto.setRelationshipStr("relationship str");
		dto.setRemark("remark");
		dto.setRemoved(false);
		dto.setServeArmySituation("v");
		dto.setSS(false);
		dto.setSocialsecurityStr(null);
		dto.setSocialsecurityTypeId(null);
		dto.setStreetId(UUID.randomUUID());
		dto.setTel("tel");
		dto.setTransfer(false);
		
		List<FamilyItemDto> items = new ArrayList<FamilyItemDto>();
		items.add(dto);
		
		FamilyDto fml = FamilyDto.obtain(//
				UUID.randomUUID(), //
				"head name", //
				UUID.randomUUID(), //
				UUID.randomUUID(),//
				UUID.randomUUID(), //
				UUID.randomUUID(),//
				"address",//
				5,//
				0, //
				0, //
				"description",//
				"deal", //
				"union suggest", //
				"remark", //
				"img path",//
				items,//
				"proname",//
				UUID.randomUUID(),
				"union path");

		String json = fmlService.addFamily(fml);
		System.out.println(json);
	}

	@Test
	public void query() {
		String json = service.queryByFmlId(UUID.fromString("1bc05f79-67b2-43cb-804d-2d9cd0b5d7c7"));
		System.out.println(json);
	}

	@Test
	public void edit() {
		FamilyItemDto dto = FamilyItemDto.obtain(//
				null, //
				"name", //
				"id numer", //
				new Date(), //
				Gender.FEMALE, //
				"only children number",//
				true, //
				"address", //
				"relationshipStr", //
				"householdStr",//
				"socialsecurityStr", //
				UUID.randomUUID(), //
				UUID.randomUUID(), //
				UUID.randomUUID(), //
				UUID.randomUUID(), //
				UUID.randomUUID(), //
				UUID.randomUUID(),//
				UUID.randomUUID(), //
				"proName",//
				true,//
				true,//
				true);	
		String json = service.editFmlItem(dto);
		System.out.println(json);
	}

	@Test
	public void query4Print() {
		String json = fmlService.getFml4Print("'08c785aa-2db4-4a5c-96e4-48e4e57078a8','268304e4-c9e8-4812-9f07-728cb05481d3'");
		System.out.println(json);
	}

	@Test
	public void queryByItemId() {
		String json = fmlService.getFmlByItemId(UUID.fromString("08c785aa-2db4-4a5c-96e4-48e4e57078a8"));
		System.out.println(json);
	}

	@Test
	public void queryBasic() {
		FamilyQueryModel queryModel = new FamilyQueryModel();
		queryModel.setCommunityId(UUID.randomUUID());
		queryModel.setGroupId(UUID.randomUUID());
//		queryModel.setIdNumber(UUID.randomUUID());
		//queryModel.setProId(UUID.randomUUID());
		queryModel.setStreetId(UUID.randomUUID());
		
		PageModel pageModel = new PageModel();
		pageModel.pageIndex = 1;
		pageModel.pageSize = 10;
		String json = fmlService.getFml4HPT(queryModel, pageModel);
		System.out.println(json);
	}
}
