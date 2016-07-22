package com.tq.requisition.presentation.dto.visits;

import java.util.UUID;

public class VisitsQueryModel {
	/**来访者电话*/
	private String tel;
	/**上访项目id*/
	private UUID proId;

	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public UUID getProId() {
		return proId;
	}
	public void setProId(UUID proId) {
		this.proId = proId;
	}	
}
