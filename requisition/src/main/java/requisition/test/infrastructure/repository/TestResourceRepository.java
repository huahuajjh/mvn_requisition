package com.tq.requisition.test.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.model.resource.IResourceRepository;
import com.tq.requisition.domain.model.resource.Resource;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestResourceRepository {
	private IResourceRepository repository;
	
	@Before
	public void init() {
		repository = (IResourceRepository) ServiceLocator.instance().getService("resourceRepository", IResourceRepository.class).setAggregatorRootClass(Resource.class);
	}

	@Test
	public void addRes() {
		for (int i = 0; i < 3; i++) {
			Resource res2=new Resource();
			res2.setChildren(false);
			res2.setIcon("icon");
			res2.setId(UUID.randomUUID());
			res2.setLink("link");
			res2.setOrder(1);
			res2.setParentResourceId(UUID.fromString("6a548ffc-009a-4602-b3a8-c21c362f5631"));
			res2.setPath("path");
			res2.setTitle("title3"+i);
//			res2.setType(ResourceType.MENU);
			res2.setVisible(true);
			repository.add(res2);
			
		}
		repository.context().commit();
	}
	
	@Test
	public void queryRes() {
		Resource res = repository.getByKey(Resource.class, UUID.fromString("8343b954-9afd-4b12-98d5-8245bda02550"));
		System.out.println(res);
		
	}

	@Test
	public void getAllRes() {
		List<Resource> list = repository.getAllResources();
		for (Resource resource : list) {
			System.out.println(resource);
		}
	}
}
