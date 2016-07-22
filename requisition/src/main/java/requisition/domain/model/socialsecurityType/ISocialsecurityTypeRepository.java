package com.tq.requisition.domain.model.socialsecurityType;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.exception.DomainException;

public interface ISocialsecurityTypeRepository extends IRepository<SocialsecurityType>{
	/**
	 * 新增社保类型
	 * @param entity
	 * 		待新增的社保类型
	 * @return SocialsecurityType
	 * 		新增后的社保实体数据
	 */
	SocialsecurityType addType(SocialsecurityType entity) throws DomainException;
	
	List<SocialsecurityType> getAllType();
	
	/**
	 * 编辑社保类型数据
	 * @param id
	 * 		社保id
	 * @param name
	 * 		社保名称
	 * @throws DomainException
	 * 		业务异常
	 */
	void editType(UUID id,String name) throws DomainException;

	/**
	 * 根据唯一约束字段获取记录的id
	 * @param name
	 * 		字段名称
	 * @return UUID
	 * 		主键id
	 */
	UUID getIdByName(String name);
}
