package com.tq.requisition.domain.model.housePuraseTicket;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.share.Entity;

/**
 * 补券上下文实体
 * @author jjh
 * @time 2015-12-18 16:53
 */
public class HPTMendInfo extends Entity{
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
	
	/*constructors*/
	public HPTMendInfo(){
		this.id = UUID.randomUUID();
		this.del = false;
	}
	public HPTMendInfo(UUID id,Date mendDate, UUID oprUserId, Date oprDate,
			UUID onwerId, UUID ticketId) {
		this();
		this.mendDate = mendDate;
		this.oprUserId = oprUserId;
		this.oprDate = oprDate;
		this.onwerId = onwerId;
		this.ticketId = ticketId;		
	}
	
	/*getters and setters*/
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return this.id;
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
	public boolean getDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
}
