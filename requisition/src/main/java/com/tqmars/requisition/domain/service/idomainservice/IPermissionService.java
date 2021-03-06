package com.tqmars.requisition.domain.service.idomainservice;

import java.util.UUID;

import com.tqmars.requisition.exception.InvalidOperationException;

/**
 * 授权管理服务接口
 * @author jjh
 * @time 2015-12-24 22:55
 */
public interface IPermissionService {
	/**
	 * 为指定的账户指派角色
	 * @param id
	 * 		账户id
	 * @param rids
	 * 		角色id数组
	 */
	void assignRole4User(UUID id,UUID...rIds) throws InvalidOperationException;
	
	/**
	 * 为指定的角色分配资源
	 * @param rid
	 * 		角色id
	 * @param resIds
	 * 		资源id数组
	 */
	void assignRes4Role(UUID rid,UUID...resIds) throws InvalidOperationException;
	
	/**
	 * 获取所有资源，同时根据角色id标注该角色具有的权限的资源
	 * Json：
	 * [PermissionAtRoleDto...]
	 * @param roleId
	 * 		角色id
	 * @return
	 */
	String getAllRescourses(UUID roleId);
}
