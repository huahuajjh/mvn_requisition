package com.tq.requisition.test.infrastructure.repository;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.domain.model.department.Department;
import com.tq.requisition.domain.model.department.IDepartmentRepository;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestDepartmentRepository {
	private IRepository<Department> deptRepository;
	
	@Before
	public void init() {
		deptRepository = ServiceLocator.instance().getService("deptRepository",IDepartmentRepository.class).setAggregatorRootClass(Department.class);
	}
	
	@Test
	public void addDept() {
		Department d = new Department();
		d.setDel(false);
		d.setDeptName("组织部");
		d.setId(UUID.randomUUID());
		d.setOrgId(UUID.fromString("1597ef9d-cdc1-4838-a058-5917ebf232ef"));
		deptRepository.add(d);
		deptRepository.context().commit();
	}
	
	@Test
	public void queryDept() {
		Department d = deptRepository.getByKey(Department.class, UUID.fromString("1597ef9d-cdc1-4838-a058-5917ebf232ef"));
		System.out.println(d.toString());
	}
	
}
