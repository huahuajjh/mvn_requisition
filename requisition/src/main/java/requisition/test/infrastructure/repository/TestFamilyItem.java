package com.tq.requisition.test.infrastructure.repository;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.domain.model.familyMember.IFamilyItemRepository;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestFamilyItem {
	private IFamilyItemRepository repository;
	@Before
	public void	init() {
		repository = ServiceLocator.instance().getService("fmlItemRepository", IFamilyItemRepository.class);		
	}
	
	@Test
	public void getById() {
		FamilyItem i = repository.getByKey(FamilyItem.class, UUID.fromString("068ad639-de3c-48df-9f8d-3a0de8ec6653"));
		System.out.println(i.getAddress());
	}

	@Test
	public void getFmlId() {
	}
}
