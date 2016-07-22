package com.tq.requisition.presentation.actions.management;

import java.io.IOException;
import java.util.UUID;

import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.sysMgt.DeptDto;
import com.tq.requisition.presentation.serviceContract.sysManagement.IDeptMgtService;

public class DeptMgt extends BaseAction {
	
	public DeptMgt(){
		deptService =  getService("deptService", IDeptMgtService.class);
	}
	
	private IDeptMgtService deptService;
	
	private String id = "";
	private String name = "";
	private String orgId = "";
	
	public void setOrgId(String orgId) {
		if(orgId == null) return;
		this.orgId = orgId;
	}

	public void setId(String id) {
		if(id== null) return;
		this.id = id;
	}

	public void setName(String name) {
		if(name == null) return;
		this.name = name;
	}
	
	public String list() throws IOException{
		String jsonData = deptService.getDeptDtoListByOrgIdToJson(UUID.fromString(orgId));
		response().getWriter().write(jsonData);
		return null;
	}
	
	public String add() throws IOException{
		DeptDto dto = new DeptDto(name, UUID.randomUUID(), UUID.fromString(orgId));
		String optState = deptService.createDept(dto);
		response().getWriter().write(optState);
		return null;
	}
	
	public String edit() throws IOException{
		DeptDto dto = new DeptDto(name, UUID.fromString(id), UUID.fromString(orgId));
		String optState = deptService.edit(dto);
		response().getWriter().write(optState);
		return null;
	}
	
	public String delete() throws IOException{
		String optState = deptService.delete(UUID.fromString(id));
		response().getWriter().write(optState);
		return null;
	}
}
