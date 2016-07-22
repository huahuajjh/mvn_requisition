package com.tq.requisition.presentation.dto.hpt;

import java.util.Date;
import java.util.UUID;

public class HPTMendInfoDto {
	/**id*/
	private UUID id;
	/**补券日期*/
	private Date mendDate;
	/**操作人*/
	private UUID oprUserId;
	/**操作日期*/
	private Date oprDate;
	/**补券人*/
	private UUID onwerId;
	/**找补的购房券*/
	private UUID ticketId;
	/**是否删除*/
	private boolean del;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public Date getMendDate() {
		return mendDate;
	}
	public void setMendDate(Date mendDate) {
		this.mendDate = mendDate;
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
	public UUID getOnwerId() {
		return onwerId;
	}
	public void setOnwerId(UUID onwerId) {
		this.onwerId = onwerId;
	}
	public UUID getTicketId() {
		return ticketId;
	}
	public void setTicketId(UUID ticketId) {
		this.ticketId = ticketId;
	}
	public boolean isDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}	
}
