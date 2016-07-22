package com.tq.requisition.test.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.model.address.Address;
import com.tq.requisition.domain.model.address.IAddressRepository;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestAddress {
	private IAddressRepository repository;
	
	@Before
	public void init(){
		repository = ServiceLocator.instance().getService("addressRepository", IAddressRepository.class);
	}
	
	@Test
	public void add() {		
		repository.context().beginTransaction();
		for (int i = 0; i < 3; i++) {
			Address address = Address.obtain("a"+i, null);
			repository.add(address);
			
			for (int j = 0; j < 3; j++) {
				Address address1 = Address.obtain("a-s"+j, address.getId());
				repository.add(address1);
				
				for (int k = 0; k < 3; k++) {
					Address address2 = Address.obtain("a-s-s"+k, address1.getId());
					repository.add(address2);
				}
			}
		}
		repository.context().commit();
	}

	@Test
	public void queryByPid() {
		repository.context().beginTransaction();
		List<Address> addresses = repository.getTopAddresses();
		System.out.println(addresses.size());
		List<Address> list = repository.getChildrenAddressByPid(UUID.fromString("48801d7d-ff51-4c8a-b448-bf7da42012ed"));
		for (Address address : list) {
			System.out.println(address);
		}
	}

	@Test
	public void queryAllTop() {
		repository.context().beginTransaction();
		List<Address> list = repository.getTopAddresses();
		for (Address address : list) {
			System.out.println(address);
		}
	}
}
