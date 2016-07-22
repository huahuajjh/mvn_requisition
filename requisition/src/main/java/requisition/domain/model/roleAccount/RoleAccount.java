package com.tq.requisition.domain.model.roleAccount;

import java.util.UUID;

import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 角色用户关系聚合根
 * @author jjh
 * @time 2015-12-21 17:21
 */
public class RoleAccount  extends AggregateRoot{
	/*private fields*/
	/**账户id*/
	private UUID accountId;
	/**角色id*/
	private UUID roleId;
	/**是否删除*/
	private boolean del;
	
	/*constructors*/
	public RoleAccount(){}
	public RoleAccount(UUID id,UUID accountId, UUID roleId) {
		super();
		this.accountId = accountId;
		this.roleId = roleId;
		this.id = id;
		this.del = false;
	}
	
	/*getters and setters*/
	public UUID getAccountId() {
		return accountId;
	}
	public void setAccountId(UUID accountId) {
		this.accountId = accountId;
	}
	public UUID getRoleId() {
		return roleId;
	}
	public void setRoleId(UUID roleId) {
		this.roleId = roleId;
	}
	public void setId(UUID _id) {
		id = _id;
	}
	public UUID getId() {
		return id;
	}
	public void setDel(boolean isDel) {
		this.del = isDel;
	}
	public boolean getDel() {
		return this.del;
	}
	
	/*override toString*/
	@Override
	public String toString() {
		return "RoleAccount [accountId=" + accountId + ", roleId=" + roleId
				+ ", id=" + id + "]";
	}	
	
	/*public static methods*/
	public static RoleAccount obtain(UUID uid,UUID rid) {
		if(uid == null || rid == null)
		{
			throw new NullPointerException("账户id或者角色id为null");
		}
		return new RoleAccount(UUID.randomUUID(),uid,rid);
	}

}
