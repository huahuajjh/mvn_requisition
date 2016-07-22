package com.tq.requisition.presentation.serviceContract.sysManagement;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.presentation.dto.sysMgt.RoleDto;

/**
 * 角色管理契约接口
 * @author jjh
 * @time 2015-12-24 9:38
 */
public interface IRoleService {	
	/**
	 * 创建角色 -
	 * @param dto
	 * 角色dto
	 */
	String addRole(RoleDto dto);
	
	/**
	 * 修改角色
	 * @param dto
	 * 		待修改的角色dto
	 */
	String updateRole(RoleDto dto);
	
	/**
	 * 获取角色列表
	 * * 		{
	 * 			datas:[roledto...],
	 * 			totalCount:总记录数
	 * 		}
	 * @param name 角色名称，空或者null表示查询所有角色
	 * @param pageIndex 页码
	 * @param pageNum 每页显示的数量
	 * @return String
	 * 		json格式数据
	 */
	String getListJson(String name,int pageIndex,int pageNum);
	
	/**
	 * 删除角色
	 * @param roleId 角色编号
	 * @return
	 */
	String deleteRole(UUID roleId);

	/**
	 * 获取角色列表
	 * @return String
	 * 		json数据结果
	 */
	String getRoleListJson();
	
	/**
	 * 获取角色列表
	 * @return List<RoleDto>
	 * 		角色dto集合
	 */
	List<RoleDto> getRoleList();
}
