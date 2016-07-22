package com.tq.requisition.presentation.actions.management;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.sysMgt.RoleDto;
import com.tq.requisition.presentation.serviceContract.sysManagement.IPermissionService;
import com.tq.requisition.presentation.serviceContract.sysManagement.IRoleService;

@SuppressWarnings("serial")
public class SysPermission extends BaseAction {

	private IPermissionService permissionService;
	private IRoleService roleService;

	private List<RoleDto> roleDtos;
	
	private int pageNum = 10;
	private int pageIndex = 1;
	private String roleName = "";
	private String id;
	private String pmsIds;
	
	public void setPageNum(int pageNum) {
		if(pageNum < 1) return;
		this.pageNum = pageNum;
	}

	public void setPageIndex(int pageIndex) {
		if(pageIndex < 1) return;
		this.pageIndex = pageIndex;
	}

	public void setRoleName(String roleName) {
		if(roleName == null) return;
		this.roleName = roleName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPmsIds(String pmsIds) {
		this.pmsIds = pmsIds;
	}

	public List<RoleDto> getRoleDtos() {
		return roleDtos;
	}

	public SysPermission() {
		permissionService = getService("pmsService", IPermissionService.class);
		roleService = getService("roleService", IRoleService.class);
	}

	@Override
	public String execute() throws Exception {
		this.roleDtos = this.roleService.getRoleList();
		return SUCCESS;
	}

	/**
	 * 获取角色列表
	 * @throws IOException
	 */
	public String list() throws IOException {
		String list = this.roleService
				.getListJson(roleName, pageIndex, pageNum);
		response().getWriter().write(list);
		return null;
	}
	/**
	 * 根据角色ID获取角色的授权信息
	 * @throws IOException
	 */
	public String getPMS() throws IOException{
		String pms = this.permissionService.getAllRescourses(UUID.fromString(id));
		response().getWriter().write(pms);
		return null;
	}
	
	/**
	 * 根据角色编号写入角色的授权信息
	 * @throws IOException 
	 */
	public String pms() throws IOException{
		List<UUID> pmsIdList = new ArrayList<UUID>();
		if(pmsIds!=null && !pmsIds .equals("")){
			String[] spilIds = pmsIds.split(",");
			for (String sid : spilIds) {
				pmsIdList.add(UUID.fromString(sid));
			}
		}
		UUID[] pmsUUIDs = pmsIdList.toArray(new UUID[pmsIdList.size()]);
		//String pmsString = this.permissionService.assignResForRole(UUID.fromString(id),pmsUUIDs);
		permissionService.assignRes4Role(UUID.fromString(id),pmsUUIDs);
		response().getWriter().write(Serialization.toJson(Formater.obtain("成功", null, Formater.OperationResult.SUCCESS)));
		return null;
	}
}
