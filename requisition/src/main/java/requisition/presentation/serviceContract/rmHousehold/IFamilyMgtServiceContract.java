package com.tq.requisition.presentation.serviceContract.rmHousehold;

import java.util.UUID;

import com.tq.requisition.domain.model.removeFamily.Family;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyDto;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemDto;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 拆迁户管理业务接口契约
 * 
 * @author jjh
 * @time 2015-12-28 18:04
 */
public interface IFamilyMgtServiceContract {
	/**
	 * 录入拆迁户家庭信息，并且返回新增后的家庭户数据，格式为json
	 * @param fml
	 * @return json String
	 */
	String addFamily(FamilyDto fml);
	
	/**
	 * 默认的家庭户信息查询，返回list数据，基于查询model和分页model
	 * @return
	 */
	PageFormater queryFmlsList(FamilyQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 基于分页model和查询model查询户信息数据，返回json数据
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 */
	String queryFmlByFuzzy(FamilyQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 修改家庭户信息
	 * @param fml
	 * 		户信息model
	 * @return json String
	 */
	String editFml(FamilyDto fml);
	
	/**
	 * 删除户信息
	 * @param id
	 * 		家庭户信息id
	 * @return json String
	 */
	String deleteFml(UUID id);	
	
	/**
	 * 导入拆迁户信息
	 * @param dto
	 * 		拆迁户entity
	 * @return String
	 * 		json
	 */
	String importFml(Family dto);
	
	/**
	 * 打印拆迁户
	 * @param uuids
	 * 		拆迁户id字符串，格式-'id','id','id'
	 * @return String
	 * 		json
	 */
	String getFml4Print(String uuids);
	
	/**
	 * 根据拆迁户id获取拆迁户信息,包含其他成员信息
	 * @param id
	 * 		成员id
	 * @return String
	 * 		json
	 */
	String getFmlByItemId(UUID id);
	
	/**
	 * 获取项目基本信息
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return String
	 * 		json
	 */
	String getFml4HPT(FamilyQueryModel queryModel,PageModel pageModel);
}

