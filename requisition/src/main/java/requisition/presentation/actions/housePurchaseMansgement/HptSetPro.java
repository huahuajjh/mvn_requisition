package com.tq.requisition.presentation.actions.housePurchaseMansgement;

import java.io.IOException;

import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.serviceContract.hptMgt.IHPTMgtServiceContract;

public class HptSetPro extends BaseAction {

	private IHPTMgtServiceContract hptMgtServiceContract;

	private int pageNum = 30;
	private int pageIndex = 1;
	private String queryProName = "";

	public HptSetPro() {
		this.hptMgtServiceContract = getService("hptService",
				IHPTMgtServiceContract.class);
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String list() throws IOException{
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(pageNum);
		String dataJson = hptMgtServiceContract.queryNotByProId(queryProName, pageModel);
		response().getWriter().write(dataJson);
		return null;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setQueryProName(String queryProName) {
		this.queryProName = queryProName;
	}

}
