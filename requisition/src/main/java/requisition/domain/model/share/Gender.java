package com.tq.requisition.domain.model.share;

/**
 * 性别枚举
 * @author jjh
 * @time 2015-12-18 18:44
 */
public enum Gender {
	/**男*/
	MALE(0,"男"),
	/**女*/
	FEMALE(1,"女");
	
	/*private fields*/
	private String strV;
	private int v;
	
	/*constructors*/
	private Gender(int v,String strV)
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
	public Gender obtainByInt(int v) {
		switch (v) {
		case 0:
			return Gender.MALE;
		case 1:
			return Gender.FEMALE;
		default:
			throw new IllegalArgumentException("枚举int参数错误，超出枚举限定,最小值为0，最大值为1");
		}
	}
	
	public Gender obtainByStr(String s) {
		switch (s) {
		case "男":
			return MALE;

		default:
			return FEMALE;
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
