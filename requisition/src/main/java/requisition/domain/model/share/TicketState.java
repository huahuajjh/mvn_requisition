package com.tq.requisition.domain.model.share;

/**
 * 购房券状态枚举
 * @author jjh
 * @time 2015-12-18 18:44
 */
public enum TicketState {
	/**挂失*/
	LOSSOFREPORT(1,"挂失"),
	/**已换券*/
	EXCHANGEED(2,"已换券"),
	/**已补券*/
	MENDED(3,"已补券"),
	/**已兑换*/
	USED(4,"已兑换"),
	/**已领取*/
	RECEIVED(5,"已领取"),
	/**已兑现*/
	CASHED(6,"已兑现"),
	/**正常*/
	NORMAL(7,"正常");
	
	/*private fields*/
	private String strV;
	private int v;
	
	/*constructors*/
	private TicketState(int v,String strV)
	{
		this.v = v;
		this.strV = strV;
	}
	
	public int toValue() {
		return v;
	}
	
	/**
	 * 通过int值获取枚举
	 * @param v
	 * @return
	 */
	public TicketState obtainByInt(int v) {
		switch (v) {
		case 1:
			return LOSSOFREPORT;
		case 2:
			return EXCHANGEED;
		case 3:
			return MENDED;
		case 4:
			return USED;
		case 5:
			return RECEIVED;
		case 6:
			return CASHED;
		case 7:
			return NORMAL;
		default:
			throw new IllegalArgumentException("枚举int参数错误，超出枚举限定,最小值为1，最大值为7");
		}
	}
	
	/**
	 * 获取枚举值得String
	 * @return
	 */
	public String toStr() {
		return this.strV;
	}
	
}
