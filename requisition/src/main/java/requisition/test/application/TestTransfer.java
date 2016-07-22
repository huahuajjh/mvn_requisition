package com.tq.requisition.test.application;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.transferMgt.NewAndEditTransferHouseholdInfoDto;
import com.tq.requisition.presentation.dto.transferMgt.TransferInfoQueryModel;
import com.tq.requisition.presentation.serviceContract.transferMgt.ITransferMgtServiceContract;

public class TestTransfer {
	private ITransferMgtServiceContract service;
	
	@Before
	public void init() {
		service = ServiceLocator.instance().getService("transferService", ITransferMgtServiceContract.class);
	}
	
	@Test
	public void queryTable() {
		TransferInfoQueryModel queryModel = new TransferInfoQueryModel();
		queryModel.setCommunityId(null);
		queryModel.setIsTransfered(null);
		queryModel.setStreetId(null);
		
		PageModel pageModel = new PageModel();
		pageModel.pageIndex = 1;
		pageModel.pageSize = 10;
		String str = service.query4AddByFuzzy(queryModel, pageModel);
		System.out.println(str);
	}

	@Test
	public void queryTable4() {
		TransferInfoQueryModel queryModel = new TransferInfoQueryModel();
		queryModel.setCommunityId(null);
		queryModel.setIsTransfered(null);
		queryModel.setStreetId(UUID.fromString("7c7a8692-914f-44cc-b4c2-1b31971e61a4"));
		
		PageModel pageModel = new PageModel();
		pageModel.pageIndex = 1;
		pageModel.pageSize = 10;
		String str = service.queryJsonByFuzzy(queryModel, pageModel);
		System.out.println(str);
	}
	
	@Test
	public void queryItems() {
		TransferInfoQueryModel queryModel = new TransferInfoQueryModel();
		queryModel.setCommunityId(null);
		queryModel.setIsTransfered(null);
		queryModel.setStreetId(null);
		
		PageModel pageModel = new PageModel();
		pageModel.pageIndex = 1;
		pageModel.pageSize = 10;
		
		String str = service.query4AddByFuzzy(queryModel, pageModel);
		System.out.println(str);
	}
	
	@Test
	public void addTransfer() {
		NewAndEditTransferHouseholdInfoDto dto = new NewAndEditTransferHouseholdInfoDto();
		dto.setAddress("address");
		dto.setCommunityId(UUID.randomUUID());
		dto.setFmlItemId(UUID.fromString("068ad639-de3c-48df-9f8d-3a0de8ec6653"));
		dto.setHouseHoldTypeId(UUID.fromString("c7952870-b5e3-4235-be40-48379659356d"));
		dto.setOprDate(new Date());
		dto.setOprUserId(UUID.randomUUID());
		dto.setStreetId(UUID.fromString("7c7a8692-914f-44cc-b4c2-1b31971e61a4"));
		dto.setTransferDate(new Date());
		String str = service.addTransferInfo(dto);
		System.out.println(str);
	}
	
	@Test
	public void addBatch() {
		NewAndEditTransferHouseholdInfoDto dto = new NewAndEditTransferHouseholdInfoDto();
		dto.setAddress("address");
		dto.setCommunityId(UUID.randomUUID());
		dto.setFmlItemId(UUID.fromString("bafcc270-3303-40bd-b3d8-58c95fce717c"));
		dto.setHouseHoldTypeId(UUID.fromString("c7952870-b5e3-4235-be40-48379659356d"));
		dto.setOprDate(new Date());
		dto.setOprUserId(UUID.randomUUID());
		dto.setStreetId(UUID.fromString("7c7a8692-914f-44cc-b4c2-1b31971e61a4"));
		dto.setTransferDate(new Date());
		
		NewAndEditTransferHouseholdInfoDto dto1 = new NewAndEditTransferHouseholdInfoDto();
		dto1.setAddress("address");
		dto1.setCommunityId(UUID.randomUUID());
		dto1.setFmlItemId(UUID.fromString("a3061596-ec99-4cae-a86d-14f273ee1f48"));
		dto1.setHouseHoldTypeId(UUID.fromString("c7952870-b5e3-4235-be40-48379659356d"));
		dto1.setOprDate(new Date());
		dto1.setOprUserId(UUID.randomUUID());
		dto1.setStreetId(UUID.fromString("7c7a8692-914f-44cc-b4c2-1b31971e61a4"));
		dto1.setTransferDate(new Date());
		
		NewAndEditTransferHouseholdInfoDto dto2 = new NewAndEditTransferHouseholdInfoDto();
		dto2.setAddress("address");
		dto2.setCommunityId(UUID.randomUUID());
		dto2.setFmlItemId(UUID.fromString("5cfc8fef-3c4c-4332-b0b8-2827b9e3d69f"));
		dto2.setHouseHoldTypeId(UUID.fromString("c7952870-b5e3-4235-be40-48379659356d"));
		dto2.setOprDate(new Date());
		dto2.setOprUserId(UUID.randomUUID());
		dto2.setStreetId(UUID.fromString("7c7a8692-914f-44cc-b4c2-1b31971e61a4"));
		dto2.setTransferDate(new Date());
		
		List<NewAndEditTransferHouseholdInfoDto> list = new ArrayList<NewAndEditTransferHouseholdInfoDto>();
		list.add(dto);
		list.add(dto1);
		list.add(dto2);
		String str = service.addBatchTransferInfo(list);
		System.out.println(str);
	}	
}
