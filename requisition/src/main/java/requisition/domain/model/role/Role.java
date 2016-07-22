package com.tq.requisition.domain.model.role;

import java.util.UUID;

import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 角色聚合根
 * @author jjh
 * @time 2015-12-18 16:49
 */
public class Role extends AggregateRoot{
	/*private fields*/
	private String roleName;
	private boolean del;
	
	/*getters and setters*/
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public boolean getDel() {
		return del;
	}
	public void setDel(boolean isDel) {
		this.del = isDel;
	}	
	public void setId(UUID _id) {
		id = _id;		
	}
	public UUID getId() {
		return id;
	}

	/*constructors*/	
	public Role(){}
	public Role(UUID id,String roleName, boolean del) {
		this.roleName = roleName;
		this.del = del;
		this.id = id;
	}
	
	/*override*/
	@Override
	public String toString() {
		return "Role [roleName=" + roleName + ", del=" + del + "]";
	}
	
	/*public methods*/
	/**
	 * 修改角色
	 * @param role
	 * 		待修改的角色实体
	 */
	public void modify(Role role)
	{
		this.roleName = role.getRoleName();
		//this.del = role.getDel();
	}

	/**
	 * 检测角色名是否为空
	 */
	public void checkRoleNameNull() {
		if(this.roleName == null)
		{
			throw new NullPointerException("角色名称不能为空");
		}
	}
	
	public static Role obtain(String roleName) {
		if(roleName == null)
		{
			throw new NullPointerException("创建角色实体引发异常，角色名为null");
		}
		return new Role(UUID.randomUUID(),roleName,false);
	}
	
}
