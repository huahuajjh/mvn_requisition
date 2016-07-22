package com.tq.requisition.presentation.actions.management;


import java.io.IOException;
import java.util.UUID;

import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.sysMgt.RoleDto;
import com.tq.requisition.presentation.serviceContract.sysManagement.IRoleService;

@SuppressWarnings("serial")
public class SysRoleManage extends BaseAction {

	private IRoleService roleService;

	private int pageNum = 30;
	private int pageIndex = 1;
	private String roleName = "";
	private String id;

	public void setPageNum(int pageNum) {
		if(pageNum<1) return;
		this.pageNum = pageNum;
	}

	public void setPageIndex(int pageIndex) {
		if(pageIndex<1) return;
		this.pageIndex = pageIndex;
	}

	public void setRoleName(String roleName) {
		if(roleName== null) return;
		this.roleName = roleName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SysRoleManage() {
		roleService = getService("roleService", IRoleService.class);
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}


	public String list() throws IOException {
		String list = this.roleService
				.getListJson(roleName, pageIndex, pageNum);
		response().getWriter().write(list);
		return null;

	}

	public String add() throws IOException {
		RoleDto dto = new RoleDto(roleName, UUID.randomUUID());
		String addState = this.roleService.addRole(dto);
		response().getWriter().write(addState);
		return null;
	}

	public String remove() throws IOException {
		String removeState = this.roleService.deleteRole(UUID.fromString(id));
		response().getWriter().write(removeState);
		return null;
	}

	public String edit() throws IOException {
		RoleDto dto = new RoleDto(roleName, UUID.fromString(id));
		String editState = this.roleService.updateRole(dto);
		response().getWriter().write(editState);
		return null;
	}

}
