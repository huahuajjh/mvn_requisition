package com.tq.requisition.domain.model.project;

/**
 * 项目类型
 * 
 * @author jjh
 * @time 2015-12-18 12:40
 */
public enum ProjectType {
	/** 重点项目 */
	INFRASTRUCTURE(1, "基础设施"),
	/** 一般项目 */
	OTHER(2, "其他");

	/* private fields */
	private int value;
	private String strValue;

	/* contructors */
	private ProjectType(int v, String strValue) {
		this.value = v;
		this.strValue = strValue;
	}

	/**
	 * 返回枚举的int值
	 * 
	 * @return
	 */
	public int toValue() {
		return this.value;
	}

	/**
	 * 通过int值获取枚举类型
	 * 
	 * @param v
	 *            int参数
	 * @return 枚举
	 */
	public ProjectType obtainByInt(int v) {

		switch (v) {
		case 1:
			return INFRASTRUCTURE;
		case 2:
			return OTHER;
		default:
			throw new IllegalArgumentException("枚举int参数错误，超出枚举限定");
		}
	}

	public String toStr() {
		return strValue;
	}

	public String toStr(int v) {
		switch (v) {
		case 1:
			return "基础设施";
		case 2:
			return "其他";
		default:
			throw new IllegalArgumentException("枚举int参数错误，超出枚举限定");
		}

	}
}
