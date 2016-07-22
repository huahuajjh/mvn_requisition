package com.tq.requisition.test.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.resRole.IResRoleRepository;
import com.tq.requisition.domain.model.resRole.ResRole;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestResRole {
	private IResRoleRepository repository;
	
	@Before
	public void init() {
		repository = (IResRoleRepository) ServiceLocator.instance().getService("resRoleRepository", IResRoleRepository.class).setAggregatorRootClass(ResRole.class);
	}
	
	@Test
	public void addResRole() {
		ResRole rr = new ResRole(UUID.randomUUID(),UUID.fromString("a00b5e82-7e0e-48d4-99a8-9ff7a2f9db2b"),UUID.fromString("8343b954-9afd-4b12-98d5-8245bda02550"));
		repository.add(rr);
		repository.context().commit();
	}

	@Test
	public void obtain() {
		ResRole rr = ResRole.obtain(UUID.randomUUID(), UUID.randomUUID());
		System.out.println(rr.toString());
	}

	@Test
	public void assignResource() {
		repository.assignRes4Role(UUID.fromString("a00b5e82-7e0e-48d4-99a8-9ff7a2f9db2b"),UUID.fromString("8343b954-9afd-4b12-98d5-8245bda02550"));
		repository.context().commit();
	}
	
	@Test
	public void modify() {
		ResRole rr = repository.getByKey(ResRole.class, UUID.fromString("2a246e53-ec29-485d-9c09-85ebef086e7e"));
		rr.setRoleId(UUID.fromString("a00b5e82-7e0e-48d4-99a8-9ff7a2f9db2b"));
		System.out.println(rr);
		repository.update(rr);
		repository.context().commit();
	}
	
	@Test
	public void delete() {
		repository.removeAllPermissionByRId(UUID.fromString("a00b5e82-7e0e-48d4-99a8-9ff7a2f9db2b"));		
	}
	
	@Test
	public void exists() {
		boolean r = repository.exists(new SpecificationExt<ResRole>(ResRole.class) {
			@Override
			public String getAbsHql() {
				throw new NotImplementedException("未实现的方法getAbsHql()");
			}
			@Override
			public String getAbsSql() {
				return "select count(1) from tb_permission where role_id=? and res_id=?";
			}
			@Override
			public Object[] getAbsParameters() {
				return new Object[]{UUID.fromString("a00b5e82-7e0e-48d4-99a8-9ff7a2f9db2b").toString(),//
						UUID.fromString("8343b954-9afd-4b12-98d5-8245bda02550").toString()};				
			}
			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		System.out.println(r);
	}
	
	@Test
	public void getResourcesIds() {
		List<UUID> list = repository.getResourcesByRid(UUID.fromString("a00b5e82-7e0e-48d4-99a8-9ff7a2f9db2b"));
		for (UUID uuid : list) {
			System.out.println(uuid);
		}
	}
}
