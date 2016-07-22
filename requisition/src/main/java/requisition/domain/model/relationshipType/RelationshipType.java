package com.tq.requisition.domain.model.relationshipType;

import java.util.UUID;

import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 与户主关系聚合根
 * @author jjh
 * @time 2015-12-18 16:38
 */
public class RelationshipType extends AggregateRoot{
	/**关系名称*/
	private String name;
	
	/*constructors*/
	public RelationshipType(){
		this.id = UUID.randomUUID();		
	}
	public RelationshipType(UUID id,String name) {
		this();
		this.name = name;
	}
	
	/*getters and setters*/
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return this.id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	/*public methods*/
	public static RelationshipType obtain(UUID id,String name) {
		return new RelationshipType(UUID.randomUUID(), name);
	}
	
}
