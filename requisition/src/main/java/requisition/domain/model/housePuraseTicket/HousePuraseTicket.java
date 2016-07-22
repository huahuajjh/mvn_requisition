package com.tq.requisition.domain.model.housePuraseTicket;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.model.share.TicketState;
import com.tq.requisition.domain.share.AggregateRoot;
import com.tq.requisition.exception.SpecifiedObjectDoesNotExistsException;

/**
 * 购房券聚合根
 * 
 * @author jjh
 * @time 2015-12-18 16:53
 */
public class HousePuraseTicket extends AggregateRoot {
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
	public HousePuraseTicket() {
		this.id = UUID.randomUUID();
		this.state = TicketState.NORMAL;
		this.del = false;
	}

	public HousePuraseTicket(UUID id, float bonus, Date makeDate,
			String ticketNumber, TicketState state, UUID fmlItemId,
			String idNumber,String name) {
		this();
		this.id = UUID.randomUUID();
		this.makeDate = makeDate;		
		this.bonus = bonus;
		this.ticketNumber = ticketNumber;
		this.fmlItemId = fmlItemId;
		this.idNumber = idNumber;
		this.name = name;
		this.del = false;
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

	/* public methods */
	/**
	 * 领用购房券
	 * @throws SpecifiedObjectDoesNotExistsException 
	 */
	public void receive() throws SpecifiedObjectDoesNotExistsException {
		if(this.state!=TicketState.NORMAL)
		{
			throw new SpecifiedObjectDoesNotExistsException("无法领取指定的购房券，该购房券状态为:"+this.state.toStr());
		}
		this.state = TicketState.RECEIVED;
	}

	/**
	 * 换券,将原来的购房券标记为true，不再使用，并且新增更换的购房券
	 * @throws SpecifiedObjectDoesNotExistsException 
	 */
	public void exchange() throws SpecifiedObjectDoesNotExistsException {
		if(this.del==true)
		{
			throw new SpecifiedObjectDoesNotExistsException("该券已经被挂失作为历史记录");
		}
		
		if(this.state==TicketState.CASHED || //已兑现状态
				this.state==TicketState.LOSSOFREPORT || //挂失状态
				this.state==TicketState.NORMAL || //正常状态，未领取
				this.state==TicketState.USED)	//已兑换
		{
			throw new SpecifiedObjectDoesNotExistsException("不能执行该操作，当前购房券状态为:"+this.state.toStr());
		}
		this.state = TicketState.EXCHANGEED;
		this.del = true;
	}

	/**
	 * 挂失操作，标记购房券状态为挂失
	 * @throws SpecifiedObjectDoesNotExistsException 
	 */
	public void reportOfLoss() throws SpecifiedObjectDoesNotExistsException {
		if(this.del==true)
		{
			throw new SpecifiedObjectDoesNotExistsException("不能挂失该购房券，该券已被挂失过");
		}
		if(!(this.state==TicketState.RECEIVED || this.state==TicketState.EXCHANGEED||this.state==TicketState.MENDED))
		{
			throw new SpecifiedObjectDoesNotExistsException("不能挂失指定的购房券，当前购房券状态为:"+this.state.toStr());
		}		
		this.state = TicketState.LOSSOFREPORT;
	}

	/**
	 * 补券
	 * @throws SpecifiedObjectDoesNotExistsException 
	 */
	public void mend() throws SpecifiedObjectDoesNotExistsException {
		if(this.state!=TicketState.LOSSOFREPORT)
		{
			throw new SpecifiedObjectDoesNotExistsException("无法执行该操作,只有挂失后的购房券才能进行该操作，当前购房券的状态为:"+this.state.toStr());
		}
		this.state = TicketState.MENDED;
		//将原有的购房券作为历史纪录保存
		this.del = true;
	}

	/**
	 * 使用
	 * @throws SpecifiedObjectDoesNotExistsException 
	 */
	public void useAndCash() throws SpecifiedObjectDoesNotExistsException {
		if(this.del==true)
		{
			throw new SpecifiedObjectDoesNotExistsException("该券不能使用");
		}
		if(this.state == TicketState.CASHED ||
				this.state == TicketState.LOSSOFREPORT ||
				this.state == TicketState.NORMAL||
				this.state == TicketState.USED)
		{
			throw new SpecifiedObjectDoesNotExistsException("无法执行该操作，该购房券状态为:"+this.state.toStr());
		}
		this.state = TicketState.CASHED;		
	}
}
