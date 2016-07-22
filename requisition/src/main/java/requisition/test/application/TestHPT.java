package com.tq.requisition.test.application;

import java.util.Date;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.model.share.TicketState;
import com.tq.requisition.domain.model.share.UseType;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.presentation.dto.hpt.HPTExchangeInfoDto;
import com.tq.requisition.presentation.dto.hpt.HPTFuzzyQueryModel;
import com.tq.requisition.presentation.dto.hpt.HPTLossInfoDto;
import com.tq.requisition.presentation.dto.hpt.HPTMendInfoDto;
import com.tq.requisition.presentation.dto.hpt.HPTQueryModel;
import com.tq.requisition.presentation.dto.hpt.HPTRecevieInfoDto;
import com.tq.requisition.presentation.dto.hpt.HPTUseAndCashInfoDto;
import com.tq.requisition.presentation.dto.hpt.HousePuraseTicketDto;
import com.tq.requisition.presentation.dto.hpt.HptUseAndCashQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.serviceContract.hptMgt.IHPTMgtServiceContract;

public class TestHPT {
	private IHPTMgtServiceContract service;
	
	@Before
	public void init() {
		service = ServiceLocator.instance().getService("hptService", IHPTMgtServiceContract.class);
	}
	
	@Test
	public void add() {
		HousePuraseTicketDto dto = new HousePuraseTicketDto();
		dto.setBonus(12.542425f);
		dto.setDel(false);
		dto.setFmlItemId(UUID.fromString("068ad639-de3c-48df-9f8d-3a0de8ec6653"));
		dto.setId(UUID.randomUUID());
		dto.setIdNumber("44444444444455555555");
		dto.setMakeDate(new Date());
		dto.setName("14");
		dto.setState(TicketState.NORMAL);
		dto.setTicketNumber("123131");
		String str = service.add(dto);
		System.out.println(str);
	}

	@Test
	public void query() {
		String str = service.queryByIdNumber("52555555555200000000");
		System.out.println(str);
	}
	
	@Test
	public void queryByIdnumAndName() {
	}
	
	@Test
	public void queryByQueryModel() {
		HPTQueryModel queryModel = new HPTQueryModel();
		queryModel.setIdNumber("12222222222222222222");
		queryModel.setTicketNumber(null);
		String str = service.queryExchangeInfo(queryModel);
		System.out.println(str);
	}
	
	@Test
	public void loss() {
		HPTLossInfoDto dto = new HPTLossInfoDto();
		dto.setDel(false);
		dto.setFmlItemId(UUID.randomUUID());
		dto.setId(UUID.randomUUID());
		dto.setOprDate(new Date());
		dto.setOprUserId(UUID.randomUUID());
		dto.setReportOfLossDate(new Date());
		dto.setTicketId(UUID.fromString("198f365d-52cd-47a2-960a-44d7045d5c25"));
		String str = service.lossOfReport(dto);
		System.out.println(str);
	}
	
	@Test
	public void provide() {
		HPTRecevieInfoDto dto = new HPTRecevieInfoDto();
		dto.setDel(false);
		dto.setEvidenceOfGetting("path");
		dto.setGettingDate(new Date());
		dto.setId(UUID.randomUUID());
		dto.setIdNumber("52555555555200000000");
		dto.setOprDate(new Date());
		dto.setName("js");
		dto.setOprUserId(UUID.randomUUID());
		dto.setOwnerId(UUID.randomUUID());
		dto.setTicketId(UUID.fromString("c019b73e-e7f7-4dd6-8820-de2d6b92221f"));
		String str = service.provide(dto);
		System.out.println(str);
	}
	
	@Test
	public void mend() {
		HPTMendInfoDto mendDto = new HPTMendInfoDto();
		mendDto.setDel(false);
		mendDto.setId(UUID.randomUUID());
		mendDto.setMendDate(new Date());
		mendDto.setOnwerId(UUID.randomUUID());
		mendDto.setOprDate(new Date());
		mendDto.setOprUserId(UUID.randomUUID());
		mendDto.setTicketId(UUID.fromString("198f365d-52cd-47a2-960a-44d7045d5c25"));
		
		HousePuraseTicketDto dto = new HousePuraseTicketDto();
		dto.setBonus(12.542425f);
		dto.setDel(false);
		dto.setFmlItemId(UUID.fromString("068ad639-de3c-48df-9f8d-3a0de8ec6653"));
		dto.setId(UUID.randomUUID());
		dto.setIdNumber("44444444444455555555");
		dto.setMakeDate(new Date());
		dto.setName("14");
		dto.setState(TicketState.NORMAL);
		dto.setTicketNumber("123131");
		String str = service.mend(mendDto, dto);
		System.out.println(str);
	}

	@Test
	public void exchange() {
		HousePuraseTicketDto dto = new HousePuraseTicketDto();
		dto.setBonus(12.542425f);
		dto.setDel(false);
		dto.setFmlItemId(UUID.fromString("068ad639-de3c-48df-9f8d-3a0de8ec6653"));
		dto.setId(UUID.randomUUID());
		dto.setIdNumber("44444444444455555555");
		dto.setMakeDate(new Date());
		dto.setName("14");
		dto.setState(TicketState.NORMAL);
		dto.setTicketNumber("123131");

		HPTExchangeInfoDto ex = new HPTExchangeInfoDto();
		ex.setDel(false);
		ex.setEvidencePath("path");
		ex.setExchengeDate(new Date());
		ex.setGettingUser("get");
		ex.setId(UUID.randomUUID());
		ex.setNewTicketId(UUID.randomUUID());
		ex.setOldTicketId(UUID.fromString("b52d20a0-09d6-4b2e-8b4c-07786c646801"));
		ex.setOprDate(new Date());
		ex.setOprUserId(UUID.randomUUID());
		ex.setOwnerId(UUID.randomUUID());
		
		String str = service.exchange(ex, dto);
		System.out.println(str);
	}
	
	@Test
	public void use() {
		HPTUseAndCashInfoDto dto = new HPTUseAndCashInfoDto();
		dto.setDel(false);
		dto.setEvidencePath("path");
		dto.setId(UUID.randomUUID());
		dto.setOprDate(new Date());
		dto.setOprUserId(UUID.randomUUID());
		dto.setOwnerId(UUID.randomUUID());
		dto.setSituationExplain("s");
		dto.setTicketId(UUID.fromString("8933fffa-fffd-463d-b632-089038ffe120"));
		dto.setUsingDate(new Date());
		dto.setUsingToWhere("w");
		dto.setUsingType(UseType.CASH);
		String str = service.use(dto);
		System.out.println(str);
	}

	@Test
	public void provideTable() {
		HPTFuzzyQueryModel queryModel = new HPTFuzzyQueryModel();
		
		PageModel pageModel = new PageModel();
		pageModel.pageIndex = 1;
		pageModel.pageSize = 10;
		
		String str = service.queryProvideTable(queryModel, pageModel);
		System.out.println(str);
	}
	
	@Test
	public void useTable() {
		HPTFuzzyQueryModel queryModel = new HPTFuzzyQueryModel();
		
		PageModel pageModel = new PageModel();
		pageModel.pageIndex = 1;
		pageModel.pageSize = 10;
		
		String str = service.queryUseTable(queryModel, pageModel);
		System.out.println(str);
	}

	@Test
	public void queryFml() {
		String str = service.queryByFml("44444444444444444444");
		System.out.println(str);
	}
	
	@Test
	public void queryHPT(){
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(1);
		pageModel.setPageSize(30);
		
		HptUseAndCashQueryModel  queryModel = new HptUseAndCashQueryModel();
		String str = service.queryByPage4Json(queryModel, pageModel);
		System.out.println(str);
	}
}
