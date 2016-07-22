package com.tq.requisition.presentation.dto.socialsecurityMgt;

import java.util.UUID;

public class SocialsecurityQueryModel {
	/**项目id*/
	private String proName;
	/**街道地址id*/
	private UUID streetId;
	/**社区地址id*/
	private UUID communityId;
	/**组id*/
	private UUID groupId;
	/**身份证*/
	private String idNumber;
	/**创建人员信息*/
	private String createUId;
	
	private String address;
	
	/*getters and setters*/
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
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
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
	/*constructors*/
	public SocialsecurityQueryModel(){}
	public SocialsecurityQueryModel(String proName, UUID streetId, UUID communityId) {
		super();
		this.proName = proName;
		this.streetId = streetId;
		this.communityId = communityId;
	}

}
