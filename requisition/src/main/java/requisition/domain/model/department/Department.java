package com.tq.requisition.domain.model.department;

import java.util.UUID;

import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 部门聚合根
 * @author jjh
 * @time 2015-12-18 19:20 
 */
public class Department extends AggregateRoot{
	/**部门名称*/
	private String deptName;
	/**是否删除*/
	private boolean del;
	/**所属组织id*/
	private UUID orgId;
	
	/*getters and setters*/
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public boolean getDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	public UUID getOrgId() {
		return orgId;
	}
	public void setOrgId(UUID orgId) {
		this.orgId = orgId;
	}
	public void setId(UUID _id) {
		id = _id;
	}
	public UUID getId() {
		return id;
	}
	
	/*constructors*/
	public Department(){}
	public Department(UUID id,String deptName, boolean del, UUID orgId) {
		super();
		this.deptName = deptName;
		this.del = del;
		this.id = id;
		this.orgId = orgId;
	}
	
	/*override toString*/
	@Override
	public String toString() {
		return "Department [deptName=" + deptName + ", del=" + del + ", orgId="
				+ orgId + ", id=" + id + "]";
	}
	
	/*public methods*/
	/**
	 * 修改部门
	 * @param dept
	 * 		待修改的部门实体
	 */
	public void modify(Department dept)
	{
		this.deptName = dept.getDeptName();
		//this.del = dept.getDel();
		//this.orgId = dept.getOrgId();
	}
	
	public static Department obtain(String name,UUID _orgId) {
		if(name == null || _orgId == null)
		{
			throw new NullPointerException("部门名称或者组织id为null");
		}
		return new Department(UUID.randomUUID(),name, false, _orgId);
	}
}
