package com.tq.requisition.infrastructure.utils;

/**
 * 分页模型
 * @author jjh
 * @time 2015-12-25 18::30
 */
public class PageFormater {
	private Object datas;
	private int totalCount;
	
	private PageFormater(Object _datas,int _totalCount)
	{
		this.datas = _datas;
		this.totalCount = _totalCount;
	}
	
	public static PageFormater obtain(Object _datas,int _totalCount) {
		return new PageFormater(_datas, _totalCount);
	}

	@Override
	public String toString() {
		return "PageFormater [datas=" + datas + ", totalCount=" + totalCount
				+ "]";
	}

	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	
}
