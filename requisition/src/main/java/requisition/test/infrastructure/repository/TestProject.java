package com.tq.requisition.test.infrastructure.repository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.project.Announcement;
import com.tq.requisition.domain.model.project.IProjectRepository;
import com.tq.requisition.domain.model.project.Project;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.presentation.dto.project.ProNameDto;

public class TestProject {
	private IProjectRepository repository;

	@Before
	public void init() {
		repository = ServiceLocator.instance().getService("proRepository",
				IProjectRepository.class);
	}

	@Test
	public void add() {
//		repository.context().beginTransaction();
//		for (int i = 0; i < 500; i++) {
//			Project pro = Project.obtain("pro name2", "003", 15.0f, 10, 10, 15.0f,
//					12.0f, 500, 152012.0f, 12554.0f, new Date(), false,
//					ProjectType.EARLIER.toValue(), UUID.randomUUID(), UUID.randomUUID(),
//					"address",-1);
//			pro.setProName("name"+i);
//			pro.setApprovalNumber("ap"+i);
//			repository.add(pro);
//		}
//		repository.context().commit();
	}

	@Test
	public void query() {
		repository.context().beginTransaction();
		List<Project> list = repository.getAll(new SpecificationExt<Project>(Project.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_project order by pro_name limit 3,10";
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
		});
		
		for (Project project : list) {
			System.out.println(project.getProName());
		}
	}

	@Test
	public void edit() {
		repository.context().beginTransaction();
		Project pro = repository.getByKey(Project.class, UUID.fromString("26b2def7-2c05-4ad4-91ca-fcac9e0920d8"));
		pro.setProName("name1");
		repository.context().commit();
	}

	@Test
	public void remove() {
		repository.context().beginTransaction();	
//		repository.remove(repository.getByKey(Project.class, UUID.fromString("24de87c8-3be3-4ffc-a7a6-cc81892f8f36")));
		repository.remove(repository.getByKey(Project.class, UUID.fromString("26b2def7-2c05-4ad4-91ca-fcac9e0920d8")));
		repository.remove(repository.getByKey(Project.class, UUID.fromString("3b807f38-26ba-4621-985f-e89e9357b710")));
//		repository.removeByKey(Project.class, UUID.fromString("24de87c8-3be3-4ffc-a7a6-cc81892f8f36"));
//		repository.removeByKey(Project.class, UUID.fromString("26b2def7-2c05-4ad4-91ca-fcac9e0920d8"));
//		repository.removeByKey(Project.class, UUID.fromString("3b807f38-26ba-4621-985f-e89e9357b710"));
		repository.context().commit();
	}

	@Test
	public void addAnnounce() {
		repository.context().beginTransaction();
		Announcement announcement = Announcement.obtain("0011", "d:/1.1.txt", new Date(), 1, UUID.fromString("00167457-81d6-49f1-9771-b9835828569d"));
		Project an = repository.addAnnouncement(announcement);
		repository.context().commit();
		System.out.println(an);
	}

	@Test
	public void getAnnounceByProId() {
		repository.context().beginTransaction();
		List<Announcement> list = repository.getAnnouncementsByProId(UUID.fromString("dfd35541-0261-4f9e-b48f-6e09caeefc91"));
		for (Announcement announcement : list) {
			System.out.println(announcement);
		}
	}

	@Test
	public void addItem() {
//		ProjectItem item = ProjectItem.obtain(
//				null,
//				new Date(),
//				1.5f, 
//				12,
//				10,
//				1.5f,
//				1.5f,
//				12, 
//				1.5f, 
//				12,
//				12,
//				12, 
//				"remark", 
//				false, 
//				UUID.fromString("00167457-81d6-49f1-9771-b9835828569d"));
//		repository.context().beginTransaction();
//		repository.addProItem(item, UUID.fromString("00167457-81d6-49f1-9771-b9835828569d"));
//		repository.context().commit();
	}

	@Test
	public void queryProName() {
		repository.context().beginTransaction();
		List<ProNameDto> str = repository.getProNameByFuzzy("4");
		for (ProNameDto proNameDto : str) {
			System.out.println(proNameDto.getId());
		}
	}

	@Test
	public void export() throws ParseException {
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		List<Project> pros = repository.exportByMonth(df.parse("2016-01-01"));
//		System.out.println(Serialization.toJson(pros));
	}

	@Test
	public void getId() {
		UUID id = repository.getIdByName("火星拆迁计划1");
		System.out.println(id);
	}
}
