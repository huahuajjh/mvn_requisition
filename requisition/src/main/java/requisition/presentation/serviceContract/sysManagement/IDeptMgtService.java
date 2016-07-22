package com.tq.requisition.presentation.serviceContract.sysManagement;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.presentation.dto.sysMgt.DeptDto;

/**
 * 部门契约接口
 * 
 * @author Bless
 * @version 1.0
 * @time 2015/12/24 14:15
 */
public interface IDeptMgtService {
	/**
	 * 新增部门
	 * @param dto
	 * 		待新增的部门dto
	 * @return
	 * 		json消息{DeptDto}
	 */
	String createDept(DeptDto dto);
	
	/**
	 * 修改部门
	 * 
	 * @param dept
	 *            待修改的部门实体DTO
	 */
	String edit(DeptDto dept);

	/**
	 * 根据ID来删除部门
	 * 
	 * @param id
	 *            部门标识
	 */
	String delete(UUID id);
	
	/**
	 * 根据组织ID获取部门列表
	 * 
	 * @param orgId
	 *            组织ID
	 * @return 部门列表
	 */
	List<DeptDto> getDeptDtoListByOrgId(UUID orgId);
	
	/**
	 * 根据组织ID获取部门列表
	 * 
	 * @param orgId
	 *            组织ID
	 * @return 部门Json列表
	 */
	String getDeptDtoListByOrgIdToJson(UUID orgId);
}
