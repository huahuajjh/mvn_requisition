package com.tqmars.requisition.presentation.actions.housePurchaseMansgement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tqmars.requisition.infrastructure.utils.Formater.OperationResult;
import com.tqmars.requisition.infrastructure.utils.Serialization;
import com.tqmars.requisition.presentation.actions.BaseAction;
import com.tqmars.requisition.presentation.dto.hpt.HPTRecevieInfoDto;
import com.tqmars.requisition.presentation.serviceContract.hptMgt.IHPTMgtServiceContract;

public class HptSet extends BaseAction {
	
	private IHPTMgtServiceContract hptMgtServiceContract;
	
	public HptSet(){
		this.hptMgtServiceContract = getService("hptService", IHPTMgtServiceContract.class);
	}
	
	private String idNumber;
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	private String dataJson;
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String get() throws IOException{
		String jsonState = this.hptMgtServiceContract.queryByFml(idNumber);
		response().getWriter().write(jsonState);
		return null;
	}
	
	public String add() throws IOException{
		String stateJson = "";
		try {
			HPTRecevieInfoDto[] dtoArr = Serialization.toObject(dataJson, HPTRecevieInfoDto[].class);
			List<HPTRecevieInfoDto> dtos = new ArrayList<HPTRecevieInfoDto>();
			for (HPTRecevieInfoDto dto : dtoArr) {
				dto.setOprDate(new Date());
				dto.setOprUserId(userId());
				dtos.add(dto);
			}
			stateJson = this.hptMgtServiceContract.provideByFml(dtos);
		} catch (Exception e) {
			stateJson = toForMaterJson(OperationResult.ERROR,"数据格式不正确");
		}
		response().getWriter().write(stateJson);
		return null;
	}
}
