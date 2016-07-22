package com.tq.requisition.presentation.dto.transferMgt;

import java.util.Date;
import java.util.UUID;

public class EditQueryTransferDto {
	/**转户信息id*/
	private UUID id;
	/**姓名*/
	private String name;
	/**身份证*/
	private String idNumber;
	/**转户后的户口类型id*/
	private UUID householdTypeId;
	/**转户后的街道地址id*/
	private UUID streetId;
	/**转户后的社区地址id*/
	private UUID communityId;
	/**转户日期*/
	private Date transferDate;
	
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
	public UUID getHouseholdTypeId() {
		return householdTypeId;
	}
	public void setHouseholdTypeId(UUID householdTypeId) {
		this.householdTypeId = householdTypeId;
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
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
	/*constructors*/
	public EditQueryTransferDto(){}
	public EditQueryTransferDto(UUID id, String name, String idNumber,
			UUID householdTypeId, UUID streetId, UUID communityId,
			Date transferDate) {
		super();
		this.id = id;
		this.name = name;
		this.idNumber = idNumber;
		this.householdTypeId = householdTypeId;
		this.streetId = streetId;
		this.communityId = communityId;
		this.transferDate = transferDate;
	}
		
}
