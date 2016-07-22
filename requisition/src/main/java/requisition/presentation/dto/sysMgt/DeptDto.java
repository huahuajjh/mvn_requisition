package com.tq.requisition.presentation.dto.sysMgt;

import java.util.UUID;

/**
 * 部门dto
 * @author jjh
 * @time 2015-12-23 17:55
 */
public class DeptDto {
	/*private fields*/
	private String name;
	private UUID id;
	private UUID orgId;
	
	/*constructors*/
	public DeptDto(String name, UUID id, UUID orgId) {
		this.name = name;
		this.id = id;
		this.orgId = orgId;
	}

	/*override*/
	@Override
	public String toString() {
		return "OrgDto [name=" + name + ", id=" + id + ", orgId=" + orgId + "]";
	}

	/*getters and setters*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getOrgId() {
		return orgId;
	}

	public void setOrgId(UUID orgId) {
		this.orgId = orgId;
	}	
}
