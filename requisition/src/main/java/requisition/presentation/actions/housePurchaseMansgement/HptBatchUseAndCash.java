package com.tq.requisition.presentation.actions.housePurchaseMansgement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.infrastructure.utils.Formater.OperationResult;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.hpt.HPTUseAndCashInfoDto;
import com.tq.requisition.presentation.dto.hpt.HptUseAndCashQueryModel;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.serviceContract.hptMgt.IHPTMgtServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;

@SuppressWarnings("serial")
public class HptBatchUseAndCash extends BaseAction {
	
	private IHPTMgtServiceContract hptMgtServiceContract;
	private IAddressServiceContract addressServiceContract;
	
	public HptBatchUseAndCash(){
		this.hptMgtServiceContract = getService("hptService", IHPTMgtServiceContract.class);
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
	}
	
	private List<AddressDto> addressDtos;
	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}
	
	@Override
	public String execute() throws Exception {
		this.addressDtos = this.addressServiceContract.getAddress();
		return SUCCESS;
	}
	
	public String list() throws IOException{
		HptUseAndCashQueryModel queryModel = new HptUseAndCashQueryModel();
		queryModel.setName(name);
		queryModel.setProName(queryProName);
		queryModel.setIdNumber(idNumber);
		if(!streetId.equals("")){
			queryModel.setStreetId(UUID.fromString(streetId));
		}
		if(!communityId.equals("")){
			queryModel.setCommunityId(UUID.fromString(communityId));
		}
		if(!zu.equals("")){
			queryModel.setGroupId(UUID.fromString(zu));
		}
		queryModel.setAddress(address);
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(pageNum);
		
		String stateJson = this.hptMgtServiceContract.queryByPage4Json(queryModel, pageModel);
		response().getWriter().write(stateJson);
		return null;
	}
	
	public String processing() throws IOException{
		HPTUseAndCashInfoDto[] dtos = Serialization.toObject(dataJson, HPTUseAndCashInfoDto[].class);
		List<HPTUseAndCashInfoDto> dtoList = new ArrayList<HPTUseAndCashInfoDto>();
		for (HPTUseAndCashInfoDto dto : dtos) {
			dto.setOprDate(new Date());
			dto.setOprUserId(userId());
			dtoList.add(dto);
		}
		String stateJson = this.hptMgtServiceContract.use(dtoList);
		response().getWriter().write(stateJson);
		return null;
	}
	
	private int pageNum = 30;
	private int pageIndex = 1;
	
	private String queryProName = "";
	private String name = null;
	private String streetId  = "";
	private String communityId  = "";
	private String zu  = "";
	private String address;
	private String idNumber;
	
	private String dataJson;

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void setQueryProName(String queryProName) {
		this.queryProName = queryProName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStreetId(String streetId) {
		this.streetId = streetId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	public void setZu(String zu) {
		this.zu = zu;
	}
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
}
