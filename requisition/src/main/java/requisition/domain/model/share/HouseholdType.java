package com.tq.requisition.domain.model.share;

public enum HouseholdType {
	/**农业户口*/
	FARMER(0,"农业户口"),
	/**居民户口*/
	CITY(1,"居民户口");
	
	/*private fields*/
	private String strV;
	private int v;
	
	/*constructors*/
	private HouseholdType(int v,String strV)
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
	public HouseholdType obtainByInt(int v) {
		switch (v) {
		case 0:
			return HouseholdType.FARMER;
		case 1:
			return HouseholdType.CITY;
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
