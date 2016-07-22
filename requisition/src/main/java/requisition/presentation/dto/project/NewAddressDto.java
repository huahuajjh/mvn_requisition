package com.tq.requisition.presentation.dto.project;

import java.util.UUID;

public class NewAddressDto {
	/**社区id*/
	private UUID communityId;
	/**街道id*/
	private UUID streetId;
	
	/**总地质*/
	private String totalAddress;
	
	public UUID getCommunityId() {
		return communityId;
	}
	public void setCommunityId(UUID communityId) {
		this.communityId = communityId;
	}
	public UUID getStreetId() {
		return streetId;
	}
	public void setStreetId(UUID streetId) {
		this.streetId = streetId;
	}

	public String getTotalAddress() {
		return totalAddress;
	}
	public void setTotalAddress(String totalAddress) {
		this.totalAddress = totalAddress;
	}
	
}
