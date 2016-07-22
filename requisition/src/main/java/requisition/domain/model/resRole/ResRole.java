package com.tq.requisition.domain.model.resRole;

import java.util.UUID;

import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 角色资源聚合根
 * @author jjh
 * @time 2015-12-19 15:46
 */
public class ResRole extends AggregateRoot{
	/*private fields*/
	/**角色id*/
	private UUID roleId;
	/**资源id*/
	private UUID resId;
	
	/*getters and setters*/
	public UUID getRoleId() {
		return roleId;
	}
	public void setRoleId(UUID roleId) {
		this.roleId = roleId;
	}
	public UUID getResId() {
		return resId;
	}
	public void setResId(UUID resId) {
		this.resId = resId;
	}
	public void setId(UUID _id) {
		id = _id;
	}
	public UUID getId() {
		return id;
	}
	
	/*constructors*/
	public ResRole(){}
	public ResRole(UUID id,UUID roleId,UUID resId){
		this.id = id;
		this.roleId = roleId;
		this.resId = resId;
	}
	
	/*override toString*/
	@Override
	public String toString() {
		return "ResRole [roleId=" + roleId + ", resId=" + resId + ", id=" + id
				+ "]";
	}
	
	/*public methods*/	
	public static ResRole obtain(UUID rid,UUID resId) {
		if(rid==null || resId == null)
		{
			throw new NullPointerException("指定的角色id或者资源id为null");
		}
		return new ResRole(UUID.randomUUID(), rid, resId);
		
	}
}
