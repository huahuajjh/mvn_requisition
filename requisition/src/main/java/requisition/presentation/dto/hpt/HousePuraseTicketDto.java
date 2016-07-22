package com.tq.requisition.presentation.dto.hpt;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.model.share.TicketState;

/**
 * 购房券dto
 * 
 * @author jjh
 * @time 2015-01-02 17:32
 */
public class HousePuraseTicketDto{
	/** id */
	private UUID id;
	/** 补贴金额 */
	private float bonus;
	/** 制券日期 */
	private Date makeDate;
	/** 券号 */
	private String ticketNumber;
	/** 购房券状态 */
	private TicketState state;
	/** 购房券所有者id */
	private UUID fmlItemId;
	/** 购房券所有者身份证 */
	private String idNumber;
	/** 是否删除 */
	private boolean del;
	/** 姓名 */
	private String name;
	/**创建人ID*/
	private String createUId;
	/**创建时间*/
	private Date createDate;

	/* constructors */
	public HousePuraseTicketDto() {
		this.id = UUID.randomUUID();
		this.state = TicketState.NORMAL;
		this.del = false;
	}

	public HousePuraseTicketDto(UUID id, float bonus, Date makeDate,
			String ticketNumber, TicketState state, UUID fmlItemId,
			String idNumber,String name) {
		this();
		this.makeDate = makeDate;		
		this.bonus = bonus;
		this.ticketNumber = ticketNumber;
		this.fmlItemId = fmlItemId;
		this.idNumber = idNumber;
		this.name = name;
	}

	/* getters and setters */
	public void setId(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return this.id;
	}

	public float getBonus() {
		return bonus;
	}

	public void setBonus(float bonus) {
		this.bonus = bonus;
	}

	public Date getMakeDate() {
		return makeDate;
	}

	public void setMakeDate(Date makeDate) {
		this.makeDate = makeDate;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public TicketState getState() {
		return state;
	}

	public void setState(TicketState state) {
		this.state = state;
	}

	public UUID getFmlItemId() {
		return fmlItemId;
	}

	public void setFmlItemId(UUID fmlItemId) {
		this.fmlItemId = fmlItemId;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public boolean getDel() {
		return del;
	}

	public void setDel(boolean del) {
		this.del = del;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreateUId() {
		return createUId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateUId(String createUId) {
		this.createUId = createUId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
