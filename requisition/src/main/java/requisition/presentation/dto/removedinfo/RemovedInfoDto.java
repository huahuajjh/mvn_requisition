package com.tq.requisition.presentation.dto.removedinfo;

import java.util.Date;
import java.util.UUID;

public class RemovedInfoDto {
	/**id*/
	private UUID id;
	/**姓名*/
	private String name;
	/**身份证号*/
	private String idNumber;
	/**出生年月*/
	private Date birthDay;
	/**地址*/
	private String address;
	/**街道信息地址*/
	private UUID streetId;
	/**街道信息地址*/
	private UUID communityId;
	/**所在组地址id*/
	private UUID groupId;
	/**拆迁日期*/
	private Date removeDate;
	/**是否删除*/
	private boolean del;
	
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
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
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
	public Date getRemoveDate() {
		return removeDate;
	}
	public void setRemoveDate(Date removeDate) {
		this.removeDate = removeDate;
	}
	public boolean isDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	public UUID getGroupId() {
		return groupId;
	}
	public void setGroupId(UUID groupId) {
		this.groupId = groupId;
	}
}
