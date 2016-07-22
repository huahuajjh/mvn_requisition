package com.tq.requisition.presentation.dto.hpt;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.model.share.UseType;

public class HPTUseAndCashInfoDto {
	/**id*/
	private UUID id;
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
	public boolean isDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
