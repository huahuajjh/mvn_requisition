package com.tq.requisition.presentation.dto.rmHousehold;

import java.util.UUID;

/**
 * 家庭户查询model
 * @author jjh
 * @time 2015-12-29 19:57
 */
public class FamilyQueryModel {
	/*private fields*/
	/**项目ID*/
	private UUID proId;
	public UUID getProId() {
		return proId;
	}
	public void setProId(UUID proId) {
		this.proId = proId;
	}
	/**身份证号*/
	private String idNumber;
	/**项目id*/
	private String proName;
	/**街道地址id*/
	private UUID streetId;
	/**社区地址id*/
	private UUID communityId;
	/**组地址id*/
	private UUID groupId;
	/**户主姓名*/
	private String name;
	/**创建用户*/
	private String createUId;
	
	private String address;
		
	/*getters and setters*/
 	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public UUID getStreetId() {
		return streetId;
	}
	public void setStreetId(UUID streetId) {
		this.streetId = streetId;
	}
	public UUID getCommunityId() {
		return communityId;
	}
	public void setCommunityId(UUID communityId) {
		this.communityId = communityId;
	}
	public UUID getGroupId() {
		return groupId;
	}
	public void setGroupId(UUID groupId) {
		this.groupId = groupId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getCreateUId() {
		return createUId;
	}
	public void setCreateUId(String createUId) {
		this.createUId = createUId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
