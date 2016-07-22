package com.tq.requisition.presentation.serviceContract.share;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.presentation.dto.share.HouseholdTypeDto;
import com.tq.requisition.presentation.dto.share.RelationshipTypeDto;
import com.tq.requisition.presentation.dto.share.SocialsecurityTypeDto;

/**
 * 字段类型接口
 * @author jjh
 * @time 2015-12-29 17:53
 *
 */
public interface IShareTypeServiceContract {
	/**
	 * 获取所有户口类型，返回json数据
	 * @return
	 */
	String getAllHouseholdType();
	
	/**
	 * 获取所有与户主关系集合，返回json数据
	 * @return
	 */
	String getAllRelationshipType();
	
	/**
	 * 获取所有社保类型集合，返回json数据
	 * @return
	 */
	String getAllSocialsecurityType();
		
	/**
	 * 获取所有户口类型集合
	 * @return
	 */
	List<HouseholdTypeDto> getAllHouseholdTypeList();
	
	/**
	 * 获取所有与户主关系集合
	 * @return
	 */
	List<RelationshipTypeDto> getAllRelationshipTypeList();
	
	/**
	 * 获取所有社保类型集合
	 * @return
	 */
	List<SocialsecurityTypeDto> getAllSocialsecurityTypeList();
	
	/**
	 * 删除社保类型
	 * @param id
	 * 		社保id
	 * @return String
	 * 		json
	 */
	String delSSType(UUID id);
	
	/**
	 * 删除与户主关系类型
	 * @param id
	 * 		户主id
	 * @return String
	 * 		json
	 */
	String delRelationshipType(UUID id);
	
	/**
	 * 删除户口类型
	 * @param id
	 * 		户口id
	 * @return String
	 * 		json
	 */
	String delHouseholdType(UUID id);
	
	/**
	 * 编辑社保类型
	 * @param id
	 * 		社保id
	 * @param name
	 * 		类型名称
	 * @return String
	 * 		json
	 */
	String editSSType(UUID id,String name);
	
	/**
	 * 编辑与户主关系类型
	 * @param id
	 * 		关系id
	 * @param name
	 * 		类型名称
	 * @return String
	 * 		json
	 */
	String editRelationshipType(UUID id,String name);
	
	/**
	 * 编辑户口类型
	 * @param id
	 * 		户口id
	 * @param name
	 * 		类型名称
	 * @return String
	 * 		json
	 */
	String editHouseholdType(UUID id,String name);
	
	/**
	 * 新增社保类型
 	 * @param name
 	 * 		社保名称
	 * @return String
	 * 		json
	 */
	String addSSType(String name);
	
	/**
	 * 新增户口类型
 	 * @param name
 	 * 		户口名称
	 * @return String
	 * 		json
	 */
	String addHouseholdType(String name);
	
	/**
	 * 新增与户主关系类型
 	 * @param name
 	 * 		关系名称
	 * @return String
	 * 		json
	 */
	String addRelationshipType(String name);
}
