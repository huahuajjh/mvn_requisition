package com.tq.requisition.presentation.serviceContract.rmHousehold;

import java.util.UUID;

import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemDto;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

public interface IFamilyItemServiceContract {
	/**
	 * 根据模糊条件查询model查询拆迁户人员信息，返回json数据
	 * @param queryModel
	 * 		模糊条件查询model
	 * @param pageModel
	 * 		分页model
	 * @return json String
	 */
	String queryFamilyItemsByFuzzy(FamilyItemQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 默认获取拆迁户人员信息列表集合,基于分页model，查询model
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 * 		拆迁户人员集合
	 */
	PageFormater queryFamilyItemListByFuzzy(FamilyItemQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 编辑成员信息
	 * @param item
	 * 		成员信息model
	 * @return
	 */
	String editFmlItem(FamilyItemDto item);
	
	/**
	 * 删除拆迁户成员信息
	 * @param id
	 * 		待删除的拆迁户成员信息
	 * @return json
	 */
	String deleteFmlItem(UUID id);
	
	/**
	 * 根据身份证获取拆迁户人员信息
	 * @param idNumber
	 * 		身份证
	 * @return
	 */
	String queryByIdNumber(String idNumber);
	
	/**
	 * 根据拆迁户id获取拆迁户成员列表
	 * @param id
	 * 		拆迁户id
	 * @return
	 */
	String queryByFmlId(UUID id);
	
	/**
	 * 新增拆迁户人员信息
	 * @param dto
	 * 		待新增的拆迁户人员信息
	 * @return
	 * 		新增后的拆迁户人员信息
	 */
	String addFmlItem(FamilyItemDto dto);
	
	/**
	 * 根据身份证和姓名查询拆迁人员信息
	 * @param idNumber
	 * 		身份证
	 * @param name
	 * 		姓名
	 * @return
	 * 		拆迁人员信息
	 */
	String queryByIdNumberAndName(String idNumber,String name);
}
