package com.tq.requisition.test.infrastructure.repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.model.transferHouseholdInfo.ITransferInfoRepository;
import com.tq.requisition.domain.model.transferHouseholdInfo.TransferHouseholdInfo;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.transferMgt.TransferInfoDto4Table;
import com.tq.requisition.presentation.dto.transferMgt.TransferInfoQueryModel;

public class TestTransferMgt {
	private ITransferInfoRepository repository;
	
	@Before
	public void init() {
		repository = ServiceLocator.instance().getService("transferRepository", ITransferInfoRepository.class);
	}
	
	@Test
	public void queryFuzzy() {
//		repository.context().beginTransaction();
//		TransferInfoQueryModel queryModel = new TransferInfoQueryModel();
//		queryModel.setCommunityId(null);
//		queryModel.setProId(null);
//		queryModel.setStreetId(null);
//		
//		PageModel pageModel = new PageModel();
//		pageModel.pageIndex = 1;
//		pageModel.pageSize = 2;
//		List<TransferInfoDto> list = repository.queryByFuzzy(queryModel, pageModel);
//		for (TransferInfoDto transferInfoDto : list) {
//			System.out.println(transferInfoDto.getProId());
//		}
	}
	
	@Test
	public void add() {
		repository.context().beginTransaction();
		TransferHouseholdInfo t = new TransferHouseholdInfo();
		t.setAddress("address");
		t.setId(UUID.randomUUID());
		t.setDel(false);
		t.setCommunityId(UUID.randomUUID());
		t.setStreetId(UUID.randomUUID());
		t.setFmlItemId(UUID.fromString("059a6c0c-8273-42e7-9df1-34854a5f9fd5"));
		t.setHouseHoldTypeId(UUID.fromString("c7952870-b5e3-4235-be40-48379659356d"));
		t.setOprDate(new Date());
		t.setTransferDate(new Date());
		t.setOprUserId(UUID.randomUUID());
		t.setHouseHoldTypeStr("str");
		repository.add(t);
		repository.context().commit();
	}
	
	@Test
	public void queryByKey() {
		repository.context().beginTransaction();
		TransferHouseholdInfo t = repository.getByKey(TransferHouseholdInfo.class, UUID.fromString("d5e41a83-7d5d-4fc0-8730-64a05d5b1de7"));
		System.out.println(t.getAddress());
	}

	@Test
	public void queryBuFuzzy() {
		TransferInfoQueryModel queryModel = new TransferInfoQueryModel();
		queryModel.setCommunityId(null);
		queryModel.setStreetId(null);
		
		PageModel pageModel = new PageModel();
		pageModel.pageIndex = 1;
		pageModel.pageSize = 2;
		PageFormater page = repository.queryByFuzzy(queryModel, pageModel);
		List<TransferInfoDto4Table> list = (List<TransferInfoDto4Table>) page.getDatas();
		for (TransferInfoDto4Table transferInfoDto4Table : list) {
			System.out.println(transferInfoDto4Table);
		}
	}
}
