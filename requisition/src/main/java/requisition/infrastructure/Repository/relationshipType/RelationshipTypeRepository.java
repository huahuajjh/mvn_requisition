package com.tq.requisition.infrastructure.Repository.relationshipType;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.relationshipType.IRelationshipTypeRepository;
import com.tq.requisition.domain.model.relationshipType.RelationshipType;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.Repository.HbRepository;
import com.tq.requisition.infrastructure.Specifications.share.ExistsByColNameSpecification;

public class RelationshipTypeRepository extends HbRepository<RelationshipType> implements IRelationshipTypeRepository{

	public RelationshipTypeRepository(IRepositoryContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public RelationshipType addType(RelationshipType entity)  throws DomainException{
		checkDuplicate(entity.getName());
		add(entity);
		return entity;
	}
	
	@Override
	public List<RelationshipType> getAllType() {
		List<RelationshipType> list = getAll(new SpecificationExt<RelationshipType>(RelationshipType.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_relationship_type";
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
	public void editType(UUID id, String name) throws DomainException{
		if(null == name || name.trim().equals("")){throw new DomainException("关系名称不能为空");}
		RelationshipType entity = getByKey(RelationshipType.class, id);
		if(null==entity){throw new DomainException("未查询到指定的关系数据");}
		if(entity.getName().equals(name)){return;}
		checkDuplicate(name);
		entity.setName(name);
		update(entity);
	}

	private void checkDuplicate(String name) throws DomainException{
		boolean r = exists(new ExistsByColNameSpecification<>(RelationshipType.class, "tb_relationship_type", "type_name", name));
		if(r){throw new DomainException("名称["+name+"]已经存在");}
	}
	
	@Override
	public UUID getIdByName(String name) {		 
		UUID id = getIdByUniqueCol("select id from tb_relationship_type where type_name=?", name);
		 return id;
	}
}
