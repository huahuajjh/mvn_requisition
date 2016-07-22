package com.tqmars.requisition.infrastructure.Repository.socialsecurityType;

import java.util.List;
import java.util.UUID;

import com.tqmars.requisition.domain.IRepository.IRepositoryContext;
import com.tqmars.requisition.domain.Specification.SpecificationExt;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.socialsecurityType.ISocialsecurityTypeRepository;
import com.tqmars.requisition.domain.model.socialsecurityType.SocialsecurityType;
import com.tqmars.requisition.exception.DomainException;
import com.tqmars.requisition.infrastructure.Repository.HbRepository;
import com.tqmars.requisition.infrastructure.Specifications.share.ExistsByColNameSpecification;

public class SocialsecurityTypeRepository extends HbRepository<SocialsecurityType> implements ISocialsecurityTypeRepository{

	public SocialsecurityTypeRepository(IRepositoryContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public SocialsecurityType addType(SocialsecurityType entity)  throws DomainException{
		checkDuplicate(entity.getName());
		add(entity);
		return entity;
	}
	
	@Override
	public List<SocialsecurityType> getAllType() {
		List<SocialsecurityType> list = getAll(new SpecificationExt<SocialsecurityType>(SocialsecurityType.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_socialsecurity_type";
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
	public void editType(UUID id, String name) throws DomainException {
		if(null == name || name.trim().equals("")){throw new DomainException("关系名称不能为空");}
		SocialsecurityType entity = getByKey(SocialsecurityType.class, id);
		if(null==entity){throw new DomainException("未查询到指定的关系数据");}
		if(entity.getName().equals(name)){return;}
		checkDuplicate(name);
		entity.setName(name);
		update(entity);		
	}
	
	private void checkDuplicate(String name) throws DomainException{
		boolean r = exists(new ExistsByColNameSpecification<>(SocialsecurityType.class, "tb_socialsecurity_type", "type_name", name));
		if(r){throw new DomainException("名称["+name+"]已经存在");}
	}
	
	@Override
	public UUID getIdByName(String name) {
		return getIdByUniqueCol("select id from tb_socialsecurity_type where type_name=?", name);
	}

}
