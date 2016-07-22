package com.tq.requisition.presentation.actions.docFileManagement;

import com.tq.requisition.presentation.actions.BaseAction;

public class FileMListMsg extends BaseAction {

	private int pageNum = 30;
	private int pageIndex = 1;
	private String title;
	private String type;
	private String id;
	public void setId(String id) {
		this.id = id;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	private String dataJson;
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	
	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	
	public String get(){
		return null;
	}
	
	public String list(){
		return null;
	}
	
	public String edit(){
		return null;
	}
	
	public String delete(){
		return null;
	}
}
