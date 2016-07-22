package com.tq.requisition.test.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.role.IRoleRepository;
import com.tq.requisition.domain.model.role.Role;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestRoleRepository {
	private IRoleRepository roleRepository;
	@Before
	public void init() {
		roleRepository = (IRoleRepository) ServiceLocator.instance().getService("roleRepository", IRoleRepository.class).setAggregatorRootClass(Role.class);
	}
	
	@Test
	public void addRole() {
		roleRepository.createRole(Role.obtain("admin1"));
		roleRepository.context().commit();
	}
	
	@Test
	public void modifyRole() {
		roleRepository.modifyRole(roleRepository.getByKey(Role.class, UUID.fromString("37df2e29-de72-4fd9-87a7-fe4d7a67981a")));		
	}
	
	@Test
	public void getAll() {
		List<Role> list = roleRepository.getAllRole();
		for (Role role : list) {
			System.out.println(role.getRoleName());
		}
	}
	
	@Test
	public void getPage() {
		System.out.println(roleRepository.getTotalCount(new SpecificationExt<Role>(Role.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select count(1) from tb_role where role_name like '%n%'";
			}

			@Override
			public Object[] getAbsParameters() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		}));
				
	}

	@Test
	public void getRolePage() {
		List<Role> list = roleRepository.getRoleList("n", 1, 1, null);
		for (Role role : list) {
			System.out.println(role);
		}
	}
	
}
