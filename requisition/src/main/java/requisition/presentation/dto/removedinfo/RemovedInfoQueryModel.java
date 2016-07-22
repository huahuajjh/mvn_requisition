package com.tq.requisition.presentation.dto.removedinfo;

import java.util.UUID;

/**
 * 已迁户档案信息查询model
 * @author jjh
 * 	@time 2016-01-11 18::54
 *
 */
public class RemovedInfoQueryModel {
	/*private fields*/
	private UUID proId;
	private UUID communityId;
	private UUID streetId;
	
	/*getters and setters*/
	public UUID getProId() {
		return proId;
	}
	public void setProId(UUID proId) {
		this.proId = proId;
	}
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
	
	/*constructors*/
	public RemovedInfoQueryModel(){}
	public RemovedInfoQueryModel(UUID proId, UUID communityId, UUID streetId) {
		super();
		this.proId = proId;
		this.communityId = communityId;
		this.streetId = streetId;
	}

}
