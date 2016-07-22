package com.tq.requisition.presentation.dto.share;

import java.util.UUID;


/**
 * 与户主关系聚合根
 * @author jjh
 * @time 2015-12-18 16:38
 */
public class RelationshipTypeDto{
	public UUID id;	
	/**关系名称*/
	private String name;

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
	public RelationshipTypeDto(){}
	public RelationshipTypeDto(UUID id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	/*public methods*/
	public static RelationshipTypeDto obtain(UUID id, String name) {
		return new RelationshipTypeDto(id, name);
	}
	
}
