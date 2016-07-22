package com.tq.requisition.presentation.actions.transferAccountManagement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.dto.share.HouseholdTypeDto;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.transferMgt.NewAndEditTransferHouseholdInfoDto;
import com.tq.requisition.presentation.dto.transferMgt.TransferInfoQueryModel;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IShareTypeServiceContract;
import com.tq.requisition.presentation.serviceContract.transferMgt.ITransferMgtServiceContract;

public class TaInfoBatch extends BaseAction{
	
	private IAddressServiceContract addressServiceContract;
	private IShareTypeServiceContract shareTypeServiceContract;
	private ITransferMgtServiceContract transferMgtServiceContract;
	
	public TaInfoBatch(){
		this.transferMgtServiceContract = getService("transferService", ITransferMgtServiceContract.class);
		this.shareTypeServiceContract = getService("shareTypeService", IShareTypeServiceContract.class);
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
	}
	
	private List<AddressDto> addressDtos;
	private List<HouseholdTypeDto> householdTypeDtos;
	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}
	public List<HouseholdTypeDto> getHouseholdTypeDtos() {
		return householdTypeDtos;
	}
	
	private String queryProName;
	private int pageNum = 30;
	private int pageIndex = 1;
	private String streetId  = "";
	private String communityId  = "";
	private String proId = "";
	private String huZhuName;
	private String zuId = "";
	private String idNumber;
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void setStreetId(String streetId) {
		if(streetId == null) return;
		this.streetId = streetId;
	}
	public void setCommunityId(String communityId) {
		if(communityId == null) return;
		this.communityId = communityId;
	}
	public void setProId(String proId) {
		if(proId == null) return;
		this.proId = proId;
	}
	public void setHuZhuName(String huZhuName) {
		this.huZhuName = huZhuName;
	}
	public void setZuId(String zuId) {
		this.zuId = zuId;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public void setQueryProName(String queryProName) {
		this.queryProName = queryProName;
	}

	private String dataJson;
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	
	@Override
	public String execute() throws Exception {
		this.addressDtos = this.addressServiceContract.getAddress();
		this.householdTypeDtos = this.shareTypeServiceContract.getAllHouseholdTypeList();
		return SUCCESS;
	}
	
	public String list() throws IOException{
		TransferInfoQueryModel queryModal = new TransferInfoQueryModel();
		if(!communityId.equals("")){
			queryModal.setCommunityId(UUID.fromString(communityId));
		}
		if(!streetId.equals("")){
			queryModal.setStreetId(UUID.fromString(streetId));
		}
		if(queryProName != null && !queryProName.equals("")){
			queryModal.setProName(queryProName);
		}
		if(!zuId.equals("")){
			queryModal.setGroupId(UUID.fromString(zuId));
		}
		queryModal.setIdNumber(idNumber);
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(pageNum);
		String jsonData = this.transferMgtServiceContract.query4AddByFuzzy(queryModal, pageModel);
		response().getWriter().write(jsonData);
		return null;
	}
	
	public String add() throws IOException{
		String stateJson = "";
		NewAndEditTransferHouseholdInfoDto[] dto = Serialization.toObject(dataJson, NewAndEditTransferHouseholdInfoDto[].class);
		//写入操作人员
		 System.out.println(dto);
		List<NewAndEditTransferHouseholdInfoDto> dtos = new ArrayList<NewAndEditTransferHouseholdInfoDto>();
		for (int i = 0; i < dto.length; i++) {
			NewAndEditTransferHouseholdInfoDto t = dto[i];
			t.setOprUserId(userId());
			t.setOprDate(new Date());
			dtos.add(t);
		}
		stateJson = this.transferMgtServiceContract.addBatchTransferInfo(dtos);
		response().getWriter().write(stateJson);
		return null;
	}
}
