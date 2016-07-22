package com.tq.requisition.presentation.actions.housePurchaseMansgement;

import java.io.IOException;
import java.util.Date;

import com.tq.requisition.infrastructure.utils.Formater.OperationResult;
import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.hpt.HPTLossInfoDto;
import com.tq.requisition.presentation.dto.hpt.HPTMendInfoDto;
import com.tq.requisition.presentation.dto.hpt.HousePuraseTicketDto;
import com.tq.requisition.presentation.serviceContract.hptMgt.IHPTMgtServiceContract;

public class HptLossAndMend extends BaseAction {
	
	private IHPTMgtServiceContract hptMgtServiceContract;
	
	public HptLossAndMend(){
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
	
	private String hptLossInfo;
	private String htpMendInfo;
	private String housePuraseTicket;
	public void setHptLossInfo(String hptLossInfo) {
		this.hptLossInfo = hptLossInfo;
	}
	public void setHtpMendInfo(String htpMendInfo) {
		this.htpMendInfo = htpMendInfo;
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
	
	public String guaShi() throws IOException{
		String stateJson = "";
		try {
			HPTLossInfoDto dto = Serialization.toObject(hptLossInfo, HPTLossInfoDto.class);
			dto.setOprDate(new Date());
			dto.setOprUserId(userId());
			stateJson = this.hptMgtServiceContract.lossOfReport(dto);
		} catch (Exception e) {
			stateJson = toForMaterJson(OperationResult.ERROR,"数据格式不正确");
		}
		response().getWriter().write(stateJson);
		return null;
	}
	
	public String buShi() throws IOException{
		String stateJson = "";
		try {
			HPTMendInfoDto hptMendInfoDto = Serialization.toObject(htpMendInfo, HPTMendInfoDto.class);
			hptMendInfoDto.setOprDate(new Date());
			hptMendInfoDto.setOprUserId(userId());
			HousePuraseTicketDto housePuraseTicketDto = Serialization.toObject(housePuraseTicket, HousePuraseTicketDto.class);
			housePuraseTicketDto.setCreateDate(new Date());
			housePuraseTicketDto.setCreateUId(userId().toString());
			stateJson = this.hptMgtServiceContract.mend(hptMendInfoDto,housePuraseTicketDto);
		} catch (Exception e) {
			stateJson = toForMaterJson(OperationResult.ERROR,"数据格式不正确");
		}
		response().getWriter().write(stateJson);
		return null;
	}
}
