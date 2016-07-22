package com.tq.requisition.presentation.dto.hpt;

import java.util.UUID;

public class HPTFuzzyQueryModel {
	/**项目id*/
	private String proName;
	/**券号*/
	private String ticketNumber;
	/**身份证*/
	private String idNumber;
	/**姓名*/
	private String name;
	/**户主的身份证*/
	private String huIdNumber;
	/**户主的ID*/
	private UUID huFmlId;

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getHuIdNumber() {
		return huIdNumber;
	}

	public void setHuIdNumber(String huIdNumber) {
		this.huIdNumber = huIdNumber;
	}

	public UUID getHuFmlId() {
		return huFmlId;
	}

	public void setHuFmlId(UUID huFmlId) {
		this.huFmlId = huFmlId;
	}
	
}
