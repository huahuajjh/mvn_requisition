package com.tq.requisition.presentation.dto.sysMgt;

import java.util.UUID;

/**
 * 角色dto
 * @author jjh
 * @time 2015-12-24 9:39
 */
public class RoleDto {
	/*private fields*/
	private String name;
	private UUID id;
	
	/*constructors*/
	public RoleDto(){}
	public RoleDto(String name, UUID id) {
		super();
		this.name = name;
		this.id = id;
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
	
	/*Override*/
	@Override
	public String toString() {
		return "RoleDto [name=" + name + ", id=" + id + "]";
	}
	
}
