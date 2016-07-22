package com.tq.requisition.infrastructure.Repository.householdType;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.householdType.HouseholdType;
import com.tq.requisition.domain.model.householdType.IHouseholdTypeRepository;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.Repository.HbRepository;
import com.tq.requisition.infrastructure.Specifications.share.ExistsByColNameSpecification;

public class HouseholdTypeRepository extends HbRepository<HouseholdType> implements IHouseholdTypeRepository{

	public HouseholdTypeRepository(IRepositoryContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public HouseholdType addHouseholdType(HouseholdType entity) throws DomainException {		
		entity.check(entity.getName(), "户口类型名称不能为空");		
		boolean r = exists(new ExistsByColNameSpecification<>(HouseholdType.class, "tb_household_type", "type_name", entity.getName()));
		if(r){throw new DomainException("名称["+entity.getName()+"]已经存在");}
		add(entity);
		return entity;
	}

	@Override
	public List<HouseholdType> getAllType() {
		List<HouseholdType> list = getAll(new SpecificationExt<HouseholdType>(HouseholdType.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_household_type";
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
		return list;		
	}
	
	@Override
	public void editType(UUID id,String name) throws DomainException {		
		if(null==name || name.trim().equals("")){throw new DomainException("户口类型名称不能为空");}
		HouseholdType entity = getByKey(HouseholdType.class, id);
		if(null==entity){throw new DomainException("未查询到指定的户口类型数据");}
		if(entity.getName().equals(name)){return;}
		boolean r = exists(new ExistsByColNameSpecification<>(HouseholdType.class, "tb_household_type", "type_name", name));
		if(r){throw new DomainException("名称["+name+"]已经存在");}		
		entity.setName(name);
		update(entity);		
	}
	
}
