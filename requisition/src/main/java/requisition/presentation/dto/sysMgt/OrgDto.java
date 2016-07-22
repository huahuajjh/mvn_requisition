package com.tq.requisition.presentation.dto.sysMgt;

import java.util.UUID;

/**
 * 组织dto
 * 
 * @author jjh
 * @time 2015-12-23 17:52
 */
public class OrgDto {
	/* private fields */
	private String name;
	private String orgNumber;
	private UUID id;

	/* constructors */
	public OrgDto(String name, UUID id,String orgNumber) {
		this.name = name;
		this.id = id;
		this.orgNumber = orgNumber;
	}

	/* getters and setters */
	public String getName() {
		return name;
	}

	public String getOrgNumber() {
		return orgNumber;
	}

	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
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
}
