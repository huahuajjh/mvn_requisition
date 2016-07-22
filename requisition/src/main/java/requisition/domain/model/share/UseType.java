package com.tq.requisition.domain.model.share;

/**
 * 购房券使用类型枚举
 * @author jjh
 * @time 2015-12-18 18:44
 */
public enum UseType {
	/**已使用*/
	USED(0,"使用"),
	/**已兑现*/
	CASH(1,"兑现");
	
	/*private fields*/
	private String strV;
	private int v;
	
	/*constructors*/
	private UseType(int v,String strV)
	{
		this.v = v;
		this.strV = strV;
	}
	
	public UseType obtainByInt(int v) {
		switch (v) {
		case 0:
			return USED;
		case 1:
			return CASH;
		default:
			throw new IllegalArgumentException("枚举int参数错误，超出枚举限定,最小值为0，最大值为1");
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
