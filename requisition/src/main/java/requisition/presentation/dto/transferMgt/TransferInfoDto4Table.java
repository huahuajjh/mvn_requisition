package com.tq.requisition.presentation.dto.transferMgt;

import java.util.Date;
import java.util.UUID;

/**
 * 转户信息查询dto，UIMODEL，用于台账model
 * @author jjh
 * @time 2015-12-30 18:47
 *
 */
public class TransferInfoDto4Table {
	/*private fields*/
	private UUID id;
	/**转户人姓名*/
	private String name;
	/**转户人身份证*/
	private String idNumber;
	/**转户全地址*/
	private String address;
	/**转户日期*/
	private Date transferDate;
	/**转户后的户口类型*/
	private String houseHoldTypeStr;
	/**所属项目id*/
	private UUID proId;
	/**所属项目名称*/
	private String proName;
	/**被拆迁人员id*/
	private UUID fmlItemId;
	/**转户信息id*/
	private UUID transferId;
	/**户口类型id*/
	private UUID houseHoldTypeId;
	/**街道地址id*/
	private UUID streetId;
	/**社区地址id*/
	private UUID communityId;
	/**组地址id*/
	private UUID groupId;
	
	
	/*getters and setters*/	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	public String getHouseHoldTypeStr() {
		return houseHoldTypeStr;
	}
	public void setHouseHoldTypeStr(String houseHoldTypeStr) {
		this.houseHoldTypeStr = houseHoldTypeStr;
	}
	public UUID getProId() {
		return proId;
	}
	public void setProId(UUID proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public UUID getFmlItemId() {
		return fmlItemId;
	}
	public void setFmlItemId(UUID fmlItemId) {
		this.fmlItemId = fmlItemId;
	}
	public UUID getTransferId() {
		return transferId;
	}
	public void setTransferId(UUID transferId) {
		this.transferId = transferId;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getHouseHoldTypeId() {
		return houseHoldTypeId;
	}
	public void setHouseHoldTypeId(UUID houseHoldTypeId) {
		this.houseHoldTypeId = houseHoldTypeId;
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
	
	/*constructors*/
	public TransferInfoDto4Table(){}
	public TransferInfoDto4Table(UUID proId, String proName,String name, 
			String idNumber, String houseHoldTypeStr, Date transferDate,String address,
			UUID fmlItemId,UUID transferId,UUID houseHoldTypeId,UUID streetId,UUID communityId,UUID groupId) {
		super();
		this.name = name;
		this.idNumber = idNumber;
		this.address = address;
		this.transferDate = transferDate;
		this.houseHoldTypeStr = houseHoldTypeStr;
		this.proId = proId;
		this.proName = proName;
		this.fmlItemId = fmlItemId;
		this.transferId = transferId;
		this.houseHoldTypeId = houseHoldTypeId;
		this.streetId = streetId;
		this.communityId = communityId;
		this.groupId = groupId;
	}
	
}
