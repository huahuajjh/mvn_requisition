package com.tq.requisition.presentation.actions.housePurchaseMansgement;

import java.io.IOException;
import java.util.Date;

import com.tq.requisition.infrastructure.utils.Formater.OperationResult;
import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.hpt.HPTUseAndCashInfoDto;
import com.tq.requisition.presentation.serviceContract.hptMgt.IHPTMgtServiceContract;

@SuppressWarnings("serial")
public class HptUseAndCash extends BaseAction {
	
	private IHPTMgtServiceContract hptMgtServiceContract;
	
	public HptUseAndCash(){
		this.hptMgtServiceContract = getService("hptService", IHPTMgtServiceContract.class);
	}
	
	private String idNumber;
	private String ticketNumber;
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	private String dataJson;
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
	public void get() throws IOException{
		String jsonState = this.hptMgtServiceContract.queryByIdNumber(idNumber);
		response().getWriter().write(jsonState);
	}
	
	public void add() throws IOException{
		String stateJson = "";
		try {
			HPTUseAndCashInfoDto dto = Serialization.toObject(dataJson, HPTUseAndCashInfoDto.class);
			dto.setOprDate(new Date());
			dto.setOprUserId(userId());;
			stateJson = this.hptMgtServiceContract.use(dto);
		} catch (Exception e) {
			stateJson = toForMaterJson(OperationResult.ERROR,"数据格式不正确");
		}
		response().getWriter().write(stateJson);
	}
}
