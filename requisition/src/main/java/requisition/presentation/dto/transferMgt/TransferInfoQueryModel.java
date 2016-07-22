package com.tq.requisition.presentation.dto.transferMgt;

import java.util.UUID;

import com.tq.requisition.infrastructure.utils.ThreeState;

/**
 * 转户信息查询model
 * @author jjh
 * @time 2015-12-30 17:13
 *
 */
public class TransferInfoQueryModel {
	/**项目id*/
	private String proName;
	/**街道地址d*/
	private UUID streetId;
	/**社区地址id*/
	private UUID communityId;
	/**是否转户*/
	private ThreeState isTransfered;
	/**身份证*/
	private String idNumber;
	/**组id*/
	private UUID groupId;
	
	
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
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
	public ThreeState getIsTransfered() {
		return isTransfered;
	}
	public void setIsTransfered(ThreeState isTransfered) {
		this.isTransfered = isTransfered;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public UUID getGroupId() {
		return groupId;
	}
	public void setGroupId(UUID groupId) {
		this.groupId = groupId;
	}
	
}