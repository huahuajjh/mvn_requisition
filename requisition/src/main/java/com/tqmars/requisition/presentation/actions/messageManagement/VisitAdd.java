package com.tqmars.requisition.presentation.actions.messageManagement;

import java.io.IOException;

import com.tqmars.requisition.infrastructure.utils.Serialization;
import com.tqmars.requisition.presentation.actions.BaseAction;
import com.tqmars.requisition.presentation.dto.visits.VisitsDto;
import com.tqmars.requisition.presentation.serviceContract.visits.IVisitsServiceContract;

public class VisitAdd extends BaseAction {

	private IVisitsServiceContract visitsServiceContract;
	
	private String dataJson;
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

	@Override
	public String execute() throws Exception {
		return super.execute();
	}
	
	public String add() throws IOException{
		VisitsDto dto = Serialization.toObject(dataJson, VisitsDto.class); 
		String stateJson = visitsServiceContract.addInfo(dto);
		response().getWriter().write(stateJson);
		return null;
	}
}
