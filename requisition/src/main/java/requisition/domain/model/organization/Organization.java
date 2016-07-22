package com.tq.requisition.domain.model.organization;

import java.util.UUID;

import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 组织聚合根
 * @author jjh
 * @time 2015-12-18 19:18 
 */
public class Organization extends AggregateRoot{
	/**组织名称*/
	private String orgName;
	/**是否删除*/
	private boolean del;
	/**单位编号*/
	private String orgNumber;
	
	/*constructors*/
	public Organization()
	{
	}
	public Organization(UUID id,String orgName,String orgNumber) {
		this.id = id;
		this.orgNumber = orgNumber;
		this.orgName = orgName;
		this.del = false;
	}


	/*private fields*/
	public void setId(UUID _id) {
		id = _id;
	}
	public UUID getId() {
		return id;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public boolean getDel() {
		return del;
	}
	public void setDel(boolean isDel) {
		this.del = isDel;
	}
	public String getOrgNumber() {
		return orgNumber;
	}
	public void setOrgNumber(String orgNumber) {
		this.orgNumber = orgNumber;
	}

	/*public methods*/
	public static Organization obtain(String name,String orgNumber) {
		if(name == null || name.trim().equals(""))
		{
			throw new NullPointerException("组织名称为null");
		}
		return new Organization(UUID.randomUUID(), name,orgNumber);
	}

	
}
