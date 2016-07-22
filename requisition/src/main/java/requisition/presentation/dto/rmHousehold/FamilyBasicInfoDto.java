package com.tq.requisition.presentation.dto.rmHousehold;

import java.util.UUID;

/**
 * 用于查询户基本信息
 * @author jjh
 * 	@time 2016-01-21 18:06
 *
 */
public class FamilyBasicInfoDto {
	/**户主姓名*/
	private String name;
	
	/**户主身份证*/
	private String idNumber;
	
	/**所属项目*/
	private String proName;
	
	/**地址*/
	private String address;
	
	/**镇(街道)*/
	private UUID streetId;
	
	/**村（社区）*/
	private UUID communityId;
	
	/**组*/
	private UUID groupId;
	
	/**项目ID*/
	private UUID proId;
	
	/**户ID*/
	private UUID fmlId;
	
	/*constructors*/
	public FamilyBasicInfoDto(String name, String idNumber, String proName,
			String address,UUID streetId,UUID communityId,UUID groupId,UUID proId,UUID fmlId) {
		super();
		this.name = name;
		this.idNumber = idNumber;
		this.proName = proName;
		this.address = address;
		this.streetId = streetId;
		this.communityId = communityId;
		this.groupId = groupId;
		this.proId = proId;
		this.fmlId = fmlId;
	}
	
	/*getters and setter*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public UUID getProId() {
		return proId;
	}
	public void setProId(UUID proId) {
		this.proId = proId;
	}
	public UUID getFmlId() {
		return fmlId;
	}
	public void setFmlId(UUID fmlId) {
		this.fmlId = fmlId;
	}
}
