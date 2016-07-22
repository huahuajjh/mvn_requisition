package com.tq.requisition.presentation.dto.hpt;

import java.util.Date;
import java.util.UUID;

public class HPTRecevieInfoDto {
	/**id*/
	private UUID id;
	/**购房券id*/
	private UUID ticketId;
	/**购房券所有者*/
	private UUID ownerId;
	/**操作人*/
	private UUID oprUserId;
	/**操作日期*/
	private Date oprDate;
	/**领取凭证*/
	private String evidenceOfGetting;
	/**领用人姓名*/
	private String name;
	/**领用人身份证*/
	private String idNumber;
	/**领用时间*/
	private Date gettingDate;
	/**是否删除*/
	private boolean del;
	/**备注*/
	private String remark;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getTicketId() {
		return ticketId;
	}
	public void setTicketId(UUID ticketId) {
		this.ticketId = ticketId;
	}
	public UUID getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(UUID ownerId) {
		this.ownerId = ownerId;
	}
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
	public String getEvidenceOfGetting() {
		return evidenceOfGetting;
	}
	public void setEvidenceOfGetting(String evidenceOfGetting) {
		this.evidenceOfGetting = evidenceOfGetting;
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
	public Date getGettingDate() {
		return gettingDate;
	}
	public void setGettingDate(Date gettingDate) {
		this.gettingDate = gettingDate;
	}
	public boolean isDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Override
	public String toString() {
		return "HPTRecevieInfoDto [id=" + id + ", ticketId=" + ticketId
				+ ", ownerId=" + ownerId + ", oprUserId=" + oprUserId
				+ ", oprDate=" + oprDate + ", evidenceOfGetting="
				+ evidenceOfGetting + ", name=" + name + ", idNumber="
				+ idNumber + ", gettingDate=" + gettingDate + ", del=" + del
				+ "]";
	}
	
}
