package com.tq.requisition.presentation.dto.hpt;

import java.util.Date;
import java.util.UUID;

public class HPTExchangeInfoDto {
	/**id*/
	private UUID id;
	/**所有者id*/
	private UUID ownerId;
	/**操作人id*/
	private UUID oprUserId;
	/**操作日期*/
	private Date oprDate;
	/**换发日期*/
	private Date exchengeDate;
	/**新券id*/
	private UUID newTicketId;
	/**领用证明*/
	private String evidencePath;
	/**老券id*/
	private UUID oldTicketId;
	/**是否删除*/
	private boolean del;
	/**领用人*/
	private String gettingUser;
	
	/*getters and setters*/
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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
	public Date getExchengeDate() {
		return exchengeDate;
	}
	public void setExchengeDate(Date exchengeDate) {
		this.exchengeDate = exchengeDate;
	}
	public UUID getNewTicketId() {
		return newTicketId;
	}
	public void setNewTicketId(UUID newTicketId) {
		this.newTicketId = newTicketId;
	}
	public String getEvidencePath() {
		return evidencePath;
	}
	public void setEvidencePath(String evidencePath) {
		this.evidencePath = evidencePath;
	}
	public UUID getOldTicketId() {
		return oldTicketId;
	}
	public void setOldTicketId(UUID oldTicketId) {
		this.oldTicketId = oldTicketId;
	}
	public boolean isDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	public String getGettingUser() {
		return gettingUser;
	}
	public void setGettingUser(String gettingUser) {
		this.gettingUser = gettingUser;
	}	
}
