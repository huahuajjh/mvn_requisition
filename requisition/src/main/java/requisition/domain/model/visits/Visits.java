package com.tq.requisition.domain.model.visits;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 信访聚合跟
 * @author jjh
 * @time 2015-12-18 19：09
 */
public class Visits extends AggregateRoot{
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
	
	/**constructors*/
	public Visits() {
		this.id = UUID.randomUUID();
		this.del = false;
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
	public boolean getDel() {
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
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return this.id;
	}

	/**
	 * 修改
	 * @param entity
	 */
	public void modify(Visits entity) {
		this.otherMsg = entity.getOtherMsg();
		this.proName = entity.getProName();
		this.visitMaterialPath = entity.getVisitMaterialPath();
		this.visitorAddr = entity.getVisitorAddr();
		this.visitorName = entity.getVisitorName();
		this.visitorTel = entity.getVisitorTel();
		this.visitProId = entity.getVisitProId();
		this.visitReason = entity.getVisitReason();
		this.visitTime = entity.getVisitTime();
	}
}
