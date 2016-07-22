package com.tq.requisition.test.infrastructure.repository;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.model.householdType.HouseholdType;
import com.tq.requisition.domain.model.householdType.IHouseholdTypeRepository;
import com.tq.requisition.domain.model.relationshipType.IRelationshipTypeRepository;
import com.tq.requisition.domain.model.relationshipType.RelationshipType;
import com.tq.requisition.domain.model.socialsecurityType.ISocialsecurityTypeRepository;
import com.tq.requisition.domain.model.socialsecurityType.SocialsecurityType;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestShareType {
	private IHouseholdTypeRepository householdTypeRepository;
	private ISocialsecurityTypeRepository socialsecurityTypeRepository;
	private IRelationshipTypeRepository relationshipTypeRepository;
	
	@Before
	public void init() {
		householdTypeRepository = ServiceLocator.instance().getService("householdReposotory", IHouseholdTypeRepository.class);
		socialsecurityTypeRepository = ServiceLocator.instance().getService("socialsecurityRepository", ISocialsecurityTypeRepository.class);
		relationshipTypeRepository = ServiceLocator.instance().getService("relationshipRepository", IRelationshipTypeRepository.class);
	}
	
	@Test
	public void houseAdd() {
		householdTypeRepository.context().beginTransaction();
		householdTypeRepository.add(HouseholdType.obtain(null, "name"));
		householdTypeRepository.context().commit();
	}
	
	@Test
	public void relationshipAdd() {
		relationshipTypeRepository.context().beginTransaction();
		relationshipTypeRepository.add(RelationshipType.obtain(null, "name"));
		relationshipTypeRepository.context().commit();
	}
	
	@Test
	public void socialsecurityAdd() {
		socialsecurityTypeRepository.context().beginTransaction();
		socialsecurityTypeRepository.add(SocialsecurityType.obtain(null, "name"));
		socialsecurityTypeRepository.context().commit();
	}
	
	@Test
	public void houseQuery() {
		householdTypeRepository.context().beginTransaction();
		HouseholdType h = householdTypeRepository.getByKey(HouseholdType.class, UUID.fromString("c7952870-b5e3-4235-be40-48379659356d"));
		System.out.println(h.getName());
	}
	
	@Test
	public void relationshipQuery() {
		relationshipTypeRepository.context().beginTransaction();
		RelationshipType r = relationshipTypeRepository.getByKey(RelationshipType.class, UUID.fromString("11f5554a-ed35-4b3a-9595-b13d208c1ee3"));
		System.out.println(r.getName());
	}
	
	@Test
	public void socialsecurityQuery() {
		socialsecurityTypeRepository.context().beginTransaction();
		SocialsecurityType s = socialsecurityTypeRepository.getByKey(SocialsecurityType.class, UUID.fromString("318369f1-aa83-4119-9856-f79920a77abd"));
		System.out.println(s.getName());
	}
	
	@Test
	public void addSS() {
//		SocialsecurityType ss = SocialsecurityType.obtain(UUID.randomUUID(), "基本养老保险", 1);
//		SocialsecurityType ss1 = SocialsecurityType.obtain(UUID.randomUUID(), "基本医疗保险", 2);
//		SocialsecurityType ss2 = SocialsecurityType.obtain(UUID.randomUUID(), "失业保险", 3);
//		SocialsecurityType ss3 = SocialsecurityType.obtain(UUID.randomUUID(), "工伤保险", 4);
//		SocialsecurityType ss4 = SocialsecurityType.obtain(UUID.randomUUID(), "生育保险", 5);
//		socialsecurityTypeRepository.context().beginTransaction();
//		socialsecurityTypeRepository.add(ss);
//		socialsecurityTypeRepository.add(ss1);
//		socialsecurityTypeRepository.add(ss2);
//		socialsecurityTypeRepository.add(ss3);
//		socialsecurityTypeRepository.add(ss4);
//		socialsecurityTypeRepository.context().commit();
	}

	@Test
	public void getId4Relationship() {
		UUID id = relationshipTypeRepository.getIdByName("妻子");
		System.out.println(id);
	}
	
	@Test
	public void getId4Socialsecurity() {
		UUID id = socialsecurityTypeRepository.getIdByName("妻子");
		System.out.println(id);
	}
}
