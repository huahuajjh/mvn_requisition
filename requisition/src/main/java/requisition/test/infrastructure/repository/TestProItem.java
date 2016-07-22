package com.tq.requisition.test.infrastructure.repository;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.model.project.IProjectRepository;
import com.tq.requisition.domain.model.project.ProjectItem;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestProItem {
	private IProjectRepository repository;
	@Before
	public void init() {
		repository = ServiceLocator.instance().getService("proRepository", IProjectRepository.class);
	}
	
	@Test
	public void add() {		
		repository.context().beginTransaction();
//		for (int i = 0; i < 12; i++) {
//			ProjectItem item = ProjectItem.obtain(//
//					null,new Date(), 1.5f, 10,10,1.5f, 1.5, 500, 
//					1.5f,12, 12, 12,"111", false, UUID.fromString("dbc9ea9c-3719-42c8-bf83-6402857c229b"));
//			repository.addProItem(item, null);
//		}
//		repository.context().commit();
	}
	
	@Test
	public void delete() {
		
	}
	
	@Test
	public void edit() {
		
	}
	
	@Test
	public void query() {
		repository.context().beginTransaction();
		List<ProjectItem> list = repository.getProjectItemsByProId(UUID.fromString("dbc9ea9c-3719-42c8-bf83-6402857c229b"));
		for (ProjectItem projectItem : list) {
			System.out.println(projectItem);
		}
	}

	@Test
	public void queryByMonth() {
//		IRepository rep = ServiceLocator.instance().getService("hbRepository", IRepository.class);
//		rep.setAggregatorRootClass(ProjectItem.class);
//		List<ProjectItem> list = rep.getAll(new ProByMonthSpecification(ProjectItem.class, new Date()));
//		for (ProjectItem projectItem : list) {
//			System.out.println(projectItem.getDate());
//		}		
	}
}
