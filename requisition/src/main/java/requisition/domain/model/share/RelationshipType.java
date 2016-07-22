package com.tq.requisition.domain.model.share;

public enum RelationshipType {
	/**儿子*/
	SON(0,"儿子"),
	/**女儿*/
	DAUGHTER(1,"女儿"),
	/**妻子*/
	WIFE(2,"妻子"),
	/**丈夫*/
	HUSBAND(3,"丈夫");
	
	
	/*private fields*/
	private String strV;
	private int v;
	
	/*constructors*/
	private RelationshipType(int v,String strV)
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
	public RelationshipType obtainByInt(int v) {
		switch (v) {
		case 0:
			return RelationshipType.SON;
		case 1:
			return RelationshipType.DAUGHTER;
		case 2:
			return RelationshipType.WIFE;
		case 3:
			return RelationshipType.HUSBAND;
		default:
			throw new IllegalArgumentException("枚举int参数错误，超出枚举限定,最小值为0，最大值为3");
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
