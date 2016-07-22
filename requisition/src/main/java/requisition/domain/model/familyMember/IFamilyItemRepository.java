package com.tq.requisition.domain.model.familyMember;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.exception.SpecifiedObjectDoesNotExistsException;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemDto;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

public interface IFamilyItemRepository extends IRepository<FamilyItem>{
	
	/**
	 * 录入拆迁户家庭人员信息
	 * @param fid
	 * @return
	 */
	FamilyItem addFamilyItem(FamilyItem item);
	
	/**
	 * 模糊查询
	 * @param queryModel
	 * 		模糊查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 */
	PageFormater queryByFuzzy(FamilyItemQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 编辑拆迁户成员信息
	 * @param item
	 * 		拆迁户成员实体
	 */
	FamilyItem editFamilyItem(FamilyItem item);
	
	/**
	 * 删除拆迁户成员信息
	 * @param id
	 * 		待删除的数据实体id
	 */
	void deleteFamilyItem(UUID id);
	
	/**
	 * 根据身份证获取拆迁户人员信息
	 * @param idNumber
	 * 		身份证
	 * @return
	 */
	FamilyItem queryByIdNumber(String idNumber) throws SpecifiedObjectDoesNotExistsException;

	/**
	 * 根据身份证和姓名查询是否存在该拆迁户人员记录
	 * @param idNumber
	 * 		身份证
	 * @param name
	 * 		姓名
	 * @return
	 * 		返回一个Boolean值，当存在该记录时返回true，否则返回false
	 */
	boolean existsByIdNumAndName(String idNumber,String name);

	/**
	 * 根据拆迁户id查询拆迁户成员信息
	 * @param fmlId
	 * 		拆迁户id
	 * @return
	 * 		拆迁户成员集合数据
	 */
	List<FamilyItem> queryItemsByFmlId(UUID fmlId);
	
	/**
	 * 根据项目ID获取人员列表
	 * @param proId
	 * 		项目ID
	 * @return
	 */
	List<FamilyItem> queryItemsByProName(String proName);
	
	/**
	 * 根据身份证和姓名查询拆迁人员信息
	 * @param idNumber
	 * 		身份证
	 * @param name
	 * 		姓名
	 * @return FamilyItem
	 * 		拆迁人员信息
	 */
	FamilyItem queryByIdNumberAndName(String idNumber,String name);
	
	/**
	 * 根据身份证查询id
	 * @param idnumber
	 * 		身份证
	 * @return UUID
	 * 		id
	 */
	UUID getIdByIdNumber(String idnumber);

}
