package com.tq.requisition.domain.model.householdType;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.exception.DomainException;

/**
 * 户口性质聚合根仓储接口
 * @author jjh
 * @time 2015-12-28 0:54
 */
public interface IHouseholdTypeRepository extends IRepository<HouseholdType>{
	/**
	 * 新增户口类型
	 * @param model
	 * 		待新增的户口类型
	 * @return HouseholdType
	 * 		新增后的户口类型实体
	 */
	HouseholdType addHouseholdType(HouseholdType entity) throws DomainException ;
	
	List<HouseholdType> getAllType();
	
	/**
	 * 编辑户口类型
	 * @param name
	 * 		户口类型名称
	 * @param id
	 * 		户口类型id
	 */
	void editType(UUID id,String name) throws DomainException ;	
}
