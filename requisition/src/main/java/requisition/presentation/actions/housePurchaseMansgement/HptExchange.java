package com.tq.requisition.presentation.actions.housePurchaseMansgement;

import java.io.IOException;
import java.util.Date;

import com.tq.requisition.infrastructure.utils.Formater.OperationResult;
import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.hpt.HPTExchangeInfoDto;
import com.tq.requisition.presentation.dto.hpt.HousePuraseTicketDto;
import com.tq.requisition.presentation.serviceContract.hptMgt.IHPTMgtServiceContract;

public class HptExchange extends BaseAction {

	private IHPTMgtServiceContract hptMgtServiceContract;
	
	public HptExchange(){
		this.hptMgtServiceContract = getService("hptService", IHPTMgtServiceContract.class);
	}
	
	private String idNumber;
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	private String hptExchangeInfo;
	private String housePuraseTicket;
	public void setHptExchangeInfo(String hptExchangeInfo) {
		this.hptExchangeInfo = hptExchangeInfo;
	}
	public void setHousePuraseTicket(String housePuraseTicket) {
		this.housePuraseTicket = housePuraseTicket;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

	public String get() throws IOException{
		String jsonState = this.hptMgtServiceContract.queryByIdNumber(idNumber);
		response().getWriter().write(jsonState);
		return null;
	}
	
	public String add() throws IOException{
		String stateJson = "";
		try {
			HPTExchangeInfoDto hptExchangeInfoDto = Serialization.toObject(hptExchangeInfo, HPTExchangeInfoDto.class);
			hptExchangeInfoDto.setOprDate(new Date());
			hptExchangeInfoDto.setOprUserId(userId());
			HousePuraseTicketDto housePuraseTicketDtoDto = Serialization.toObject(housePuraseTicket, HousePuraseTicketDto.class);
			housePuraseTicketDtoDto.setCreateDate(new Date());
			housePuraseTicketDtoDto.setCreateUId(userId().toString());
			stateJson = this.hptMgtServiceContract.exchange(hptExchangeInfoDto, housePuraseTicketDtoDto);
		} catch (Exception e) {
			stateJson = toForMaterJson(OperationResult.ERROR,"数据格式不正确");
		}
		response().getWriter().write(stateJson);
		return null;
	}
	
}
