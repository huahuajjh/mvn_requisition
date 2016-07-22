package com.tq.requisition.presentation.actions.messageManagement;

import java.io.IOException;
import java.util.UUID;

import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.visits.VisitsDto;
import com.tq.requisition.presentation.dto.visits.VisitsQueryModel;
import com.tq.requisition.presentation.serviceContract.visits.IVisitsServiceContract;

public class VisitQuery extends BaseAction {

	private IVisitsServiceContract visitsServiceContract;
	
	private int pageNum = 30;
	private int pageIndex = 1;
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	private String dataJson;
	private String id;
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	
	public String list() throws IOException{
		VisitsQueryModel queryModel = new VisitsQueryModel();
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(pageNum);
		String stateJson = visitsServiceContract.queryByPage4Json(queryModel, pageModel);
		response().getWriter().write(stateJson);
		return null;
	}
	
	public String edit() throws IOException{
		VisitsDto dto = Serialization.toObject(dataJson, VisitsDto.class); 
		String stateJson = visitsServiceContract.editInfo(dto);
		response().getWriter().write(stateJson);
		return null;
	}
	
	public String delete() throws IOException{
		String stateJson = visitsServiceContract.delById(UUID.fromString(id));
		response().getWriter().write(stateJson);
		return null;
	}
}
