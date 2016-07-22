package com.tq.requisition.presentation.actions.housePurchaseMansgement;

import java.io.IOException;
import java.util.UUID;

import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.hpt.HPTFuzzyQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.serviceContract.hptMgt.IHPTMgtServiceContract;

public class HptExchangeBill extends BaseAction {
	
	private IHPTMgtServiceContract hptMgtServiceContract;
	
	public HptExchangeBill(){
		this.hptMgtServiceContract = getService("hptService", IHPTMgtServiceContract.class);
	}
	
	private String queryProName;
	private int pageNum = 30;
	private int pageIndex = 1;
	private String proId = "";
	private String num;
	private String idNumber;
	private String name;
	private String huIdNumber;
	public void setNum(String num) {
		this.num = num;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void setProId(String proId) {
		if(proId == null) return;
		this.proId = proId;
	}
	public void setQueryProName(String queryProName) {
		this.queryProName = queryProName;
	}
	public void setHuIdNumber(String huIdNumber) {
		this.huIdNumber = huIdNumber;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String list() throws IOException{
		HPTFuzzyQueryModel hptFuzzyQueryModel = new HPTFuzzyQueryModel();
		if(queryProName != null && !queryProName.equals("")){
			hptFuzzyQueryModel.setProName(queryProName);
		}
		hptFuzzyQueryModel.setHuIdNumber(huIdNumber);
		hptFuzzyQueryModel.setName(name);
		hptFuzzyQueryModel.setIdNumber(idNumber);
		hptFuzzyQueryModel.setTicketNumber(num);
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(pageNum);
		String jsonState = this.hptMgtServiceContract.queryUseTable(hptFuzzyQueryModel, pageModel);
		response().getWriter().write(jsonState);
		return null;
	}
	
}
