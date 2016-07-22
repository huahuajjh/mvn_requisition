package com.tq.requisition.presentation.dto.share;

import java.util.UUID;

/**
 * 户口性质聚合根，维护户口类型数据字典上下文
 * @author jjh
 * @time 2015-12-18 16:37
 */
public class HouseholdTypeDto{
	private UUID id;
	/**类型名称*/
	private String name;
	
	/*constructors*/
	public HouseholdTypeDto(){}
	public HouseholdTypeDto(UUID id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	/*getters and setters*/
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

	/*public methods*/
	public static HouseholdTypeDto obtain(UUID id, String name) {
		return new HouseholdTypeDto(id, name);
	}
	
}
