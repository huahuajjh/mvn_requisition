package com.tq.requisition.domain.model.relationshipType;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.exception.DomainException;

public interface IRelationshipTypeRepository extends IRepository<RelationshipType>{
	/**
	 * 新增关系类型
	 * @param entity
	 * 		待新增的关系类型实体
	 * @return RelationshipType
	 * 		新增后的关系类型实体
	 */
	RelationshipType addType(RelationshipType entity) throws DomainException;
	
	List<RelationshipType> getAllType();
	
	/**
	 * 编辑与户主关系类型
	 * @param id
	 * 		关系id
	 * @param name
	 * 		关系名称
	 */
	void editType(UUID id,String name) throws DomainException;
	
	/**
	 * 根据名称获取id
	 * @param name
	 * 		关系名称
	 * @return UUID
	 * 		UUID
	 */
	UUID getIdByName(String name);
}
