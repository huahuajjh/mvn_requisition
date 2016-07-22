package com.tq.requisition.presentation.dto.visits;

import java.util.Date;
import java.util.UUID;

public class VisitsDto {
	/**上访实体id*/
	private UUID id;
	/**上访者姓名*/
	private String visitorName;
	/**上访者手机号*/
	private String visitorTel;
	/**上访者地址*/
	private String visitorAddr;
	/**其他信息*/
	private String otherMsg;
	/**访问项目*/
	private String visitProId;
	/**访问原因*/
	private String visitReason;
	/**访问日期*/
	private Date visitTime;
	/**相关资料*/
	private String visitMaterialPath;
	/**是否删除*/
	private boolean del;
	/**项目名称*/
	private String proName;
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getVisitorName() {
		return visitorName;
	}
	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
	public String getVisitorTel() {
		return visitorTel;
	}
	public void setVisitorTel(String visitorTel) {
		this.visitorTel = visitorTel;
	}
	public String getVisitorAddr() {
		return visitorAddr;
	}
	public void setVisitorAddr(String visitorAddr) {
		this.visitorAddr = visitorAddr;
	}
	public String getOtherMsg() {
		return otherMsg;
	}
	public void setOtherMsg(String otherMsg) {
		this.otherMsg = otherMsg;
	}
	public String getVisitProId() {
		return visitProId;
	}
	public void setVisitProId(String visitProId) {
		this.visitProId = visitProId;
	}
	public String getVisitReason() {
		return visitReason;
	}
	public void setVisitReason(String visitReason) {
		this.visitReason = visitReason;
	}
	public Date getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	public String getVisitMaterialPath() {
		return visitMaterialPath;
	}
	public void setVisitMaterialPath(String visitMaterialPath) {
		this.visitMaterialPath = visitMaterialPath;
	}
	public boolean isDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
}
