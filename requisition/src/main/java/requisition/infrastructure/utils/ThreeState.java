package com.tq.requisition.infrastructure.utils;

/**
 * 状态枚举值，yes,no,all三种状态
 * @author jjh
 * @time 2015-12-30 15:30
 */
public enum ThreeState {
	NO(0,"否"),
	YES(1,"是"),
	ALL(2,"全部");
	
	/*private fields*/
	private int value;
	private String strValue;
	
	/*contructors*/
	private ThreeState(int v,String strValue)
	{
		this.value = v;
		this.strValue = strValue;
	}
		
	/**
	 * 返回枚举的int值
	 * @return
	 */
	public int toValue()
	{
		return this.value;
	}
	
	/**
	 * 通过int值获取枚举类型
	 * @param v
	 * 		int参数
	 * @return
	 * 		枚举
	 */
	public ThreeState obtainByInt(int v) {
		
		switch (v) {
		case 0:
			return NO;
		case 1:
			return YES;
		case 2:
			return ALL;
		default:
			throw new IllegalArgumentException("枚举int参数错误，超出枚举限定");
		}
	}

	public String toStr() {
		return strValue;
	}
}
