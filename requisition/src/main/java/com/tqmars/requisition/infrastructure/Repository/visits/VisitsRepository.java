package com.tqmars.requisition.infrastructure.Repository.visits;

import java.util.List;
import java.util.UUID;

import com.tqmars.requisition.domain.IRepository.IRepositoryContext;
import com.tqmars.requisition.domain.model.visits.IVisitsRepository;
import com.tqmars.requisition.domain.model.visits.Visits;
import com.tqmars.requisition.infrastructure.Repository.HbRepository;
import com.tqmars.requisition.infrastructure.Specifications.visits.VisitsCountSpecification;
import com.tqmars.requisition.infrastructure.Specifications.visits.VisitsQuerySpecification;
import com.tqmars.requisition.infrastructure.utils.PageFormater;
import com.tqmars.requisition.presentation.dto.share.PageModel;
import com.tqmars.requisition.presentation.dto.visits.VisitsQueryModel;

public class VisitsRepository extends HbRepository<Visits> implements IVisitsRepository{

	public VisitsRepository(IRepositoryContext context) {
		super(context);		
	}

	@Override
	public void delById(UUID id) {
		removeBySql("delete from tb_visit_info where id=?", id.toString());		
	}

	
	@Override
	public PageFormater queryByPage(VisitsQueryModel queryModel,
			PageModel pageModel) {
		int count = getTotalCount(new VisitsCountSpecification(Visits.class,queryModel));
		if(count==0){return null;}
		
		List<Visits> list = getAll(new VisitsQuerySpecification(Visits.class,queryModel,pageModel));
		return PageFormater.obtain(list, count);
	}
	
}
