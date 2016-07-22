package com.tq.requisition.domain.model.share;

/**
 * 账号状态
 * @author jjh
 * @time 2015-12-18 19:24 
 */
public enum AccountState {
	/**锁定*/
	LOCKED(1,"已锁定"),
	/**禁用*/
	DISABLE(2,"已禁用"),
	/**启用*/
	ENABLE(3,"正常");
	
	/*private fields*/
	private int value;
	private String strValue;
	
	/*contructors*/
	private AccountState(int v,String strValue)
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
	public AccountState obtainByInt(int v) {
		
		switch (v) {
		case 1:
			return LOCKED;
		case 2:
			return DISABLE;
		case 3:
			return ENABLE;
		default:
			throw new IllegalArgumentException("枚举int参数错误，超出枚举限定，最小值为1，最大值为3");
		}
	}

	public String toStr(int v) {
		switch (v) {
		case 1:
			return "已锁定";
		case 2:
			return "已禁用";
		case 3:
			return "正常";
		default:
			throw new IllegalArgumentException("枚举int参数错误，超出枚举限定，最小值为1，最大值为3");
		}
	}
	
	public String toStr() {
		return strValue;
	}
}
