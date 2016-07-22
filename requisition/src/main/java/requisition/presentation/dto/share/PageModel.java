package com.tq.requisition.presentation.dto.share;

/**
 * 分页查询模型，提供起始页，页码int值
 * @author jjh
 *
 */
public class PageModel {
	public int pageIndex;
	public int pageSize;
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	@Override
	public String toString() {
		return "PageModel [pageIndex=" + pageIndex + ", pageSize=" + pageSize
				+ "]";
	}
}
