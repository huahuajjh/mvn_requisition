package com.tq.requisition.presentation.dto.share;

import java.util.UUID;


/**
 * 社保类型上下文
 * @author jjh
 * @time 2015-12-18 16:39
 */
public class SocialsecurityTypeDto{
	private UUID id;
	/**社保类型名称*/
	private String name;
		
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

	/*constructors*/
	public SocialsecurityTypeDto(){}
	public SocialsecurityTypeDto(UUID id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	/*public methods*/
	public static SocialsecurityTypeDto obtain(UUID id, String name) {
		return new SocialsecurityTypeDto(id, name);
	}
	
}
