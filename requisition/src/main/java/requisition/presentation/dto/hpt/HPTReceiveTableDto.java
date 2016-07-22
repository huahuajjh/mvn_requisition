package com.tq.requisition.presentation.dto.hpt;

import java.util.Date;
import java.util.UUID;

/**
 * 购房券发放台账dto
 * @author jjh
 * @time 2015-01-02 22:27
 *
 */
public class HPTReceiveTableDto {
	/**项目id*/
	private UUID proId;
	/**项目名称*/
	private String proName;
	/**拆迁户人员id*/
	private UUID fmlItemId;
	/**购房券号*/
	private UUID ticketId;
	/**姓名*/
	private String name;
	/**身份证*/
	private String idNumber;
	/**领用人*/
	private String gettingPerson;
	/**券号*/
	private String ticketNumber;
	/**凭证*/
	private String evidenve;
	/**领用时间*/
	private Date recevieTime;
	/**金额*/
	private float bonus;
	
	/*constructors*/
	public HPTReceiveTableDto(){}
	public HPTReceiveTableDto(UUID proId, String proName, UUID fmlItemId,
			UUID ticketId, String name, String idNumber, String gettingPerson,
			String ticketNumber, String evidenve, Date recevieTime, float bonus) {
		super();
		this.proId = proId;
		this.proName = proName;
		this.fmlItemId = fmlItemId;
		this.ticketId = ticketId;
		this.name = name;
		this.idNumber = idNumber;
		this.gettingPerson = gettingPerson;
		this.ticketNumber = ticketNumber;
		this.evidenve = evidenve;
		this.recevieTime = recevieTime;
		this.bonus = bonus;
	}
	
	/*getters and setters*/
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
	public String getGettingPerson() {
		return gettingPerson;
	}
	public void setGettingPerson(String gettingPerson) {
		this.gettingPerson = gettingPerson;
	}
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getEvidenve() {
		return evidenve;
	}
	public void setEvidenve(String evidenve) {
		this.evidenve = evidenve;
	}
	public Date getRecevieTime() {
		return recevieTime;
	}
	public void setRecevieTime(Date recevieTime) {
		this.recevieTime = recevieTime;
	}
	public float getBonus() {
		return bonus;
	}
	public void setBonus(float bonus) {
		this.bonus = bonus;
	}
}
