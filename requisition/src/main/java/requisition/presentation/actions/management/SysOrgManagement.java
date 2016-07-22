package com.tq.requisition.presentation.actions.management;

import java.io.IOException;
import java.util.UUID;

import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.sysMgt.OrgDto;
import com.tq.requisition.presentation.serviceContract.sysManagement.IOrgMgtService;

@SuppressWarnings("serial")
public class SysOrgManagement extends BaseAction {

	private IOrgMgtService orgService;

	private String name = "";
	private String orgNumber = "";
	private String id = "";
	
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	public void setId(String id) {
		if (id == null)
			return;
		this.id = id;
	}

	public void setName(String name) {
		if (name == null)
			return;
		this.name = name;
	}

	public SysOrgManagement() {
		super();
		orgService = getService("orgMgtService", IOrgMgtService.class);
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * 获取组织列表
	 * @throws IOException
	 */
	public String getOrgList() throws IOException {
		String orgJson = this.orgService.getOrgListJson();
		response().getWriter().write(orgJson);
		return null;
	}

	/**
	 * 新增组织信息
	 * @throws IOException
	 */
	public String addOrg() throws IOException {
		OrgDto dto = new OrgDto(name, UUID.randomUUID(), orgNumber);
		String optJson = this.orgService.addOrg(dto);
		response().getWriter().write(optJson);
		return null;
	}

	/**
	 * 修改组织信息
	 * @throws IOException
	 */
	public String editOrg() throws IOException {
		OrgDto dto = new OrgDto(name, UUID.fromString(id), orgNumber);
		String optJson = this.orgService.editOrgInfo(dto);
		response().getWriter().write(optJson);
		return null;
	}

	/**
	 * 删除组织
	 * @throws IOException 
	 */
	public String delete() throws IOException {
		String optJson = this.orgService.deleteByOrgKey(UUID.fromString(id));
		response().getWriter().write(optJson);
		return null;
	}
}
