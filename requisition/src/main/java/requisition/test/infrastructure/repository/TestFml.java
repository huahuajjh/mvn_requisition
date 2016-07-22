package com.tq.requisition.test.infrastructure.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.domain.model.removeFamily.Family;
import com.tq.requisition.domain.model.removeFamily.IFamilyRepository;
import com.tq.requisition.domain.model.share.Gender;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestFml {
	private IFamilyRepository repository;
	
	@Before
	public void init() {
		repository = ServiceLocator.instance().getService("fmlRepository", IFamilyRepository.class);
	}
	
	@Test
	public void add() {
		List<FamilyItem> list = new ArrayList<FamilyItem>();
		list.add(FamilyItem.obtain(//
				"name",//
				"id number2",//
				new Date(),//
				Gender.MALE, //
				"only child",//
				true, //
				"address", //
				"relationship str",//
				"household str", //
				"social str",//
				UUID.randomUUID(), //
				UUID.randomUUID(), //
				UUID.randomUUID(), //
				UUID.randomUUID(), //
				UUID.randomUUID(), //
				UUID.randomUUID(),//
				UUID.randomUUID(),//
				"pro name"));
		
		Family fml = Family.obtain(//
				"户主名字",//
				UUID.randomUUID(), //
				UUID.randomUUID(), //
				UUID.randomUUID(), //
				UUID.randomUUID(), //
				"address",//
				12,//
				1.6f,//
				1.6f,//
				"des", //
				"deal", //
				"union", //
				"remark", //
				"path", //
				list,//
				"pro name",//
				null,//
				"path",null,null,null);		
		
		repository.context().beginTransaction();
		Family f = repository.addFamily(fml);
		repository.context().commit();
		System.out.println(f);
	}

	@Test
	public void query() {
		repository.context().beginTransaction();
		Family fml = repository.getByKey(Family.class, UUID.fromString("1bc05f79-67b2-43cb-804d-2d9cd0b5d7c7"));
		System.out.println(fml.getAddress());
	}
	
	@Test
	public void queryFuzzy() {
//		repository.context().beginTransaction();	
//		FamilyQueryModel queryModel = new FamilyQueryModel();
//		queryModel.setCommunityId(null);
//		queryModel.setIdNumber(null);
//		queryModel.setProId(null);
//		queryModel.setStreetId(null);
//		
//		PageModel pageModel = new PageModel();
//		pageModel.pageIndex = 1;
//		pageModel.pageSize = 3;
//		List<Family> fmls = repository.getListByFuzzy(queryModel, pageModel);
//		for (Family family : fmls) {
//			System.out.println(family.getAddress());
//		}
	}

	@Test
	public void getByIds() {
		List<Family> list = repository.getFml4Print("'08c785aa-2db4-4a5c-96e4-48e4e57078a8','787531b0-b2c7-4888-97b6-6fca1754f5a5'");
		for (Family family : list) {
			System.out.println(family.getHeadName());
		}
	}
}
