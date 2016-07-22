package com.tq.requisition.domain.model.housePuraseTicket;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.model.share.UseType;
import com.tq.requisition.domain.share.Entity;

/**
 * 购房券兑换实体信息
 * @author jjh
 * @time 2015-12-18 17:09
 */
public class HPTUseAndCash extends Entity{
	/**购房券所有者id*/
	private UUID ownerId;
	/**购房券id*/
	private UUID ticketId;
	/**使用日期*/
	private Date usingDate;
	/**使用类型*/
	private UseType usingType;
	/**使用去向*/
	private String usingToWhere;
	/**情况说明*/
	private String situationExplain;
	/**相关凭证*/
	private String evidencePath;
	/**操作人*/
	private UUID oprUserId;
	/**操作日期*/
	private Date oprDate;
	/**是否删除*/
	private boolean del;
	/**图片采集*/
	private String image;
	
	/*CONSTRUCTORS*/
	public HPTUseAndCash(){
		this.id = UUID.randomUUID();
		this.del = false;
	}
	public HPTUseAndCash(UUID id,UUID ownerId, UUID ticketId, Date usingDate,
			UseType usingType, String usingToWhere, String situationExplain,
			String evidencePath, UUID oprUserId, Date oprDate) {
		this();		
		this.ownerId = ownerId;
		this.ticketId = ticketId;
		this.usingDate = usingDate;
		this.usingType = usingType;
		this.usingToWhere = usingToWhere;
		this.situationExplain = situationExplain;
		this.evidencePath = evidencePath;
		this.oprUserId = oprUserId;
		this.oprDate = new Date();		
	}

	/*getters and setters*/
	public UUID getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(UUID ownerId) {
		this.ownerId = ownerId;
	}

	public UUID getTicketId() {
		return ticketId;
	}

	public void setTicketId(UUID ticketId) {
		this.ticketId = ticketId;
	}

	public Date getUsingDate() {
		return usingDate;
	}

	public void setUsingDate(Date usingDate) {
		this.usingDate = usingDate;
	}

	public UseType getUsingType() {
		return usingType;
	}

	public void setUsingType(UseType usingType) {
		this.usingType = usingType;
	}

	public String getUsingToWhere() {
		return usingToWhere;
	}

	public void setUsingToWhere(String usingToWhere) {
		this.usingToWhere = usingToWhere;
	}

	public String getSituationExplain() {
		return situationExplain;
	}

	public void setSituationExplain(String situationExplain) {
		this.situationExplain = situationExplain;
	}

	public String getEvidencePath() {
		return evidencePath;
	}

	public void setEvidencePath(String evidencePath) {
		this.evidencePath = evidencePath;
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

	public boolean getDel() {
		return del;
	}

	public void setDel(boolean del) {
		this.del = del;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return this.id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
