package com.tq.requisition.domain.model.transferHouseholdInfo;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 转户信息上下文
 * @author jjh
 * @time 2015-12-18 16:39
 */
public class TransferHouseholdInfo extends AggregateRoot{
	/*private fields*/	
	/**操作人id*/
	private UUID oprUserId;
	/**操作日期*/
	private Date oprDate;
	/**转户街道地址id*/
	private UUID streetId;
	/**转户社区地址id*/
	private UUID communityId;
	/**转户全地址*/
	private String address;
	/**转户日期*/
	private Date transferDate;
	/**转户后的户口类型id*/
	private UUID houseHoldTypeId;
	/**转户后的户口类型*/
	private String houseHoldTypeStr;
	/**转户人id*/
	private UUID fmlItemId;
	/**是否启用*/
	private boolean del;
	/**组id*/
	private UUID groupId;
		
	/*getters and setters*/
	public UUID getOprUserId() {
		return oprUserId;
	}
	public void setOprUserId(UUID oprUserId) {
		this.oprUserId = oprUserId;
	}
	public Date getOprDate() {
		return oprDate;
	}
	public void setOprDate(Date oprDate) {
		this.oprDate = oprDate;
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
	public UUID getHouseHoldTypeId() {
		return houseHoldTypeId;
	}
	public void setHouseHoldTypeId(UUID houseHoldTypeId) {
		this.houseHoldTypeId = houseHoldTypeId;
	}
	public UUID getFmlItemId() {
		return fmlItemId;
	}
	public void setFmlItemId(UUID fmlItemId) {
		this.fmlItemId = fmlItemId;
	}
	public String getHouseHoldTypeStr() {
		return houseHoldTypeStr;
	}
	public void setHouseHoldTypeStr(String houseHoldTypeStr) {
		this.houseHoldTypeStr = houseHoldTypeStr;
	}
	public boolean isDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return this.id;
	}
	public UUID getGroupId() {
		return groupId;
	}
	public void setGroupId(UUID groupId) {
		this.groupId = groupId;
	}
	/*constructors*/
	public TransferHouseholdInfo(){}
	public TransferHouseholdInfo(UUID id,UUID oprUserId, Date oprDate, UUID streetId,
			UUID communityId, String address, Date transferDate,
			UUID houseHoldTypeId, UUID fmlItemId) {
		super();
		this.oprUserId = oprUserId;
		this.oprDate = oprDate;
		this.streetId = streetId;
		this.communityId = communityId;
		this.address = address;
		this.transferDate = transferDate;
		this.houseHoldTypeId = houseHoldTypeId;
		this.fmlItemId = fmlItemId;
		this.id = id;
		this.del = false;
	}

	/*public methods*/
	public void modify(TransferHouseholdInfo entity) {
		this.address = entity.getAddress();
		this.communityId = entity.getCommunityId();
		this.streetId = entity.getStreetId();
		this.houseHoldTypeId = entity.getHouseHoldTypeId();
		this.houseHoldTypeStr = entity.getHouseHoldTypeStr();
		this.transferDate = entity.getTransferDate();
		this.groupId = entity.groupId;
	}
	
}
