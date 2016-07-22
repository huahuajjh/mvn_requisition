package com.tq.requisition.test.application;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;

public class TestAddress {
	private IAddressServiceContract service;
	
	@Before
	public void init() {
		service = ServiceLocator.instance().getService("addressService", IAddressServiceContract.class);
	}
	
	@Test
	public void getAll() {
		String str = service.getAddress(null);
		System.out.println(str);
	}
	
	@Test
	public void add() {
		AddressDto dto = new AddressDto();
		dto.setId(UUID.randomUUID());
		dto.setName("E");
		dto.setPid(UUID.fromString("f47eef93-7553-4bec-ae6a-368726a523e8"));
		String str = service.addNewAddress(dto);
		System.out.println(str);
	}
	
	@Test
	public void edit() {
		AddressDto dto = new AddressDto();
		dto.setId(UUID.fromString("83e21407-286a-41c6-ad00-bdcbf53762e0"));
		dto.setName("海淀区1");
		service.editAddress(dto);
	}
	
	@Test
	public void del() {
		String str = service.deleteAddress(UUID.fromString("825ec4dc-a332-4a58-83cf-9d7ec77d5a68"));
		System.out.println(str);
	}
	
}
