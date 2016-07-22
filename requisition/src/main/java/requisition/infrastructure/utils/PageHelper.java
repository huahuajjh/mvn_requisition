package com.tq.requisition.infrastructure.utils;

/**
 * 分页计算帮助类，计算页码和页眉
 * @author jjh
 * @time 2015-12-25 20:01
 * 
 */
public final class PageHelper {
	private PageHelper(){}
	
	/**
	 * 计算分页数据
	 * @param pageStart
	 * 		起始页参数
	 * @param pageNum
	 * 		页码
	 * @return 返回数据库其实记录行数
	 */
	public static int getPageIndex(int pageStart,int pageNum) {
		return (pageStart-1)*pageNum;
	}
}
