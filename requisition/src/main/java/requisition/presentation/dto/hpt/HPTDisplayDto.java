package com.tq.requisition.presentation.dto.hpt;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.model.share.TicketState;

public class HPTDisplayDto {
	/**项目名称*/
	private String proName;
	/**项目id*/
	private UUID proId;
	/**购房券id*/
	private UUID hptId;
	/**拆迁户人员id*/
	private UUID fmlItemId;
	/**姓名*/
	private String name;
	/**身份证*/
	private String idNumber;
	/**券号*/
	private String ticketNumber;
	/**金额*/
	private float bonus;
	/**购房券状态*/
	private TicketState ticketState;
	/**制券日期*/
	private Date makeTime;
	
	/*getters and setters*/
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public UUID getProId() {
		return proId;
	}
	public void setProId(UUID proId) {
		this.proId = proId;
	}
	public UUID getHptId() {
		return hptId;
	}
	public void setHptId(UUID hptId) {
		this.hptId = hptId;
	}
	public UUID getFmlItemId() {
		return fmlItemId;
	}
	public void setFmlItemId(UUID fmlItemId) {
		this.fmlItemId = fmlItemId;
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
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public float getBonus() {
		return bonus;
	}
	public void setBonus(float bonus) {
		this.bonus = bonus;
	}
	public TicketState getTicketState() {
		return ticketState;
	}
	public void setTicketState(TicketState ticketState) {
		this.ticketState = ticketState;
	}
	public Date getMakeTime() {
		return makeTime;
	}
	public void setMakeTime(Date makeTime) {
		this.makeTime = makeTime;
	}
	
	/**constructors*/
	public HPTDisplayDto(){}
	public HPTDisplayDto(String proName, UUID proId, UUID hptId,
			UUID fmlItemId, String name, String idNumber, String ticketNumber,
			float bonus, TicketState ticketState, Date makeTime) {
		super();
		this.proName = proName;
		this.proId = proId;
		this.hptId = hptId;
		this.fmlItemId = fmlItemId;
		this.name = name;
		this.idNumber = idNumber;
		this.ticketNumber = ticketNumber;
		this.bonus = bonus;
		this.ticketState = ticketState;
		this.makeTime = makeTime;
	}
}
