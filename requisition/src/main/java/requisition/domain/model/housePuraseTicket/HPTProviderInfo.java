package com.tq.requisition.domain.model.housePuraseTicket;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.share.Entity;

/**
 * 购房券发放实体上下文
 * @author jjh
 * @time 2015-12-18 18:40
 */
public class HPTProviderInfo extends Entity{
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
	/**备注*/
	private String remark;
	
	/**是否删除*/
	private boolean del;
	
	/*constructors*/
	public HPTProviderInfo(){
	}
	public HPTProviderInfo(UUID id, UUID ticketId, UUID ownerId, UUID oprUserId,
			Date oprDate, String evidenceOfGetting, String name,
			String idNumber, Date gettingDate) {
		super();
		this.id = UUID.randomUUID();
		this.ticketId = ticketId;
		this.ownerId = ownerId;
		this.oprUserId = oprUserId;
		this.oprDate = oprDate;
		this.evidenceOfGetting = evidenceOfGetting;
		this.name = name;
		this.del = false;
		this.idNumber = idNumber;
		this.gettingDate = gettingDate;
	}
	
	/*getters and setters*/
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
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return this.id;
	}
	public boolean getDel() {
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
	
	/*public methods*/
	public void validate() {
		
	}
	
}
