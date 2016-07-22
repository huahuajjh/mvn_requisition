package com.tq.requisition.presentation.dto.hpt;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.model.share.UseType;

public class HPTUseTableDto {
	/**项目id*/
	private UUID proId;
	/**项目名称*/
	private String proName;
	/**身份证*/
	private String idNumber;
	/**姓名*/
	private String name;
	/**拆迁户人员id*/
	private UUID fmlItemId;
	/**购房券id*/
	private UUID ticketId;
	/**券号*/
	private String ticketNumber;
	/**面额*/
	private float bous;
	/**使用时间*/
	private Date useTime;
	/**使用类型*/
	private UseType useType;
	/**使用去向*/
	private String useToWhere;
	/**情况说明*/
	private String situation;
	/**证明*/
	private String evidence;
	/**制券之间*/
	private Date makeDate;
	
	public UUID getProId() {
		return proId;
	}
	public void setProId(UUID proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
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
	public UUID getFmlItemId() {
		return fmlItemId;
	}
	public void setFmlItemId(UUID fmlItemId) {
		this.fmlItemId = fmlItemId;
	}
	public UUID getTicketId() {
		return ticketId;
	}
	public void setTicketId(UUID ticketId) {
		this.ticketId = ticketId;
	}
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public float getBous() {
		return bous;
	}
	public void setBous(float bous) {
		this.bous = bous;
	}
	public Date getUseTime() {
		return useTime;
	}
	public void setUseTime(Date useTime) {
		this.useTime = useTime;
	}
	public UseType getUseType() {
		return useType;
	}
	public void setUseType(UseType useType) {
		this.useType = useType;
	}
	public String getUseToWhere() {
		return useToWhere;
	}
	public void setUseToWhere(String useToWhere) {
		this.useToWhere = useToWhere;
	}
	public String getSituation() {
		return situation;
	}
	public void setSituation(String situation) {
		this.situation = situation;
	}
	public String getEvidence() {
		return evidence;
	}
	public void setEvidence(String evidence) {
		this.evidence = evidence;
	}
	public Date getMakeDate() {
		return makeDate;
	}
	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}
	
	public HPTUseTableDto(){}
	public HPTUseTableDto(UUID proId, String proName, String idNumber,
			String name, UUID fmlItemId, UUID ticketId, String ticketNumber,
			float bous, Date useTime, UseType useType, String useToWhere,
			String situation, String evidence,Date makeDate) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.idNumber = idNumber;
		this.name = name;
		this.fmlItemId = fmlItemId;
		this.ticketId = ticketId;
		this.ticketNumber = ticketNumber;
		this.bous = bous;
		this.useTime = useTime;
		this.useType = useType;
		this.useToWhere = useToWhere;
		this.situation = situation;
		this.evidence = evidence;
		this.makeDate = makeDate;
	}
}
