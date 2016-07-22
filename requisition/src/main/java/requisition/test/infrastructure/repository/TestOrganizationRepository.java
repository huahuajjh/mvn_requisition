package com.tq.requisition.test.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.model.organization.IOrganizationRepository;
import com.tq.requisition.domain.model.organization.Organization;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestOrganizationRepository {
	private IOrganizationRepository orgRepository;
	
	@Before
	public void init() {
		orgRepository = (IOrganizationRepository) ServiceLocator.instance().getService("orgRepository",IOrganizationRepository.class).setAggregatorRootClass(Organization.class);
	}
	
	@Test
	public void addOrg() {
		orgRepository.context().beginTransaction();
		Organization org = Organization.obtain("征地办1","001");
		try {
			orgRepository.createOrg(org);
		} catch (DomainException e) {
			System.out.println(e.getMessage());
		}
		orgRepository.context().commit();
	}
	
	@Test
	public void queryOrg() {
		orgRepository.context().beginTransaction();
		Organization o = orgRepository.getByKey(Organization.class, UUID.fromString("1597ef9d-cdc1-4838-a058-5917ebf232ef"));
		System.out.println(o);
	}
	
	@Test
	public void queryBatch() {
		List<Organization> list = orgRepository.getOrgList();
		for (Organization organization : list) {
			System.out.println(organization);
		}
	}
	
	@Test
	public void edit() throws DomainException {
		Organization org = new Organization();
		org.setId(UUID.fromString(""));
		org.setOrgName("国土局");
		org.setDel(false);
		orgRepository.editOrg(org);
	}
	
}
