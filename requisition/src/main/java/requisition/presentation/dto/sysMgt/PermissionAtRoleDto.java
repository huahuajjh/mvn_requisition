package com.tq.requisition.presentation.dto.sysMgt;

import java.util.UUID;

public class PermissionAtRoleDto {
	private UUID id;
	private String name;
	private boolean isPermission;
	private boolean isExpansion;
	private UUID pid;	
	private Integer order;
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPermission() {
		return isPermission;
	}

	public void setPermission(boolean isPermission) {
		this.isPermission = isPermission;
	}

	public boolean isExpansion() {
		return isExpansion;
	}

	public void setExpansion(boolean isExpansion) {
		this.isExpansion = isExpansion;
	}

	public UUID getPid() {
		return pid;
	}

	public void setPid(UUID pid) {
		this.pid = pid;
	}

}
