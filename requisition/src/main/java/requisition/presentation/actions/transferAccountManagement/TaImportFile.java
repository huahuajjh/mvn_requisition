package com.tq.requisition.presentation.actions.transferAccountManagement;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.dto.share.HouseholdTypeDto;
import com.tq.requisition.presentation.dto.transferMgt.NewAndEditTransferHouseholdInfoDto;
import com.tq.requisition.presentation.serviceContract.rmHousehold.IFamilyItemServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IShareTypeServiceContract;
import com.tq.requisition.presentation.serviceContract.transferMgt.ITransferMgtServiceContract;

public class TaImportFile extends BaseAction {

	private ITransferMgtServiceContract transferMgtServiceContract;
	private IFamilyItemServiceContract familyItemServiceContract;
	private IAddressServiceContract addressServiceContract;
	private IShareTypeServiceContract shareTypeServiceContract;
	
	public TaImportFile(){
		this.transferMgtServiceContract = getService("transferService", ITransferMgtServiceContract.class);
		this.familyItemServiceContract = getService("fmlItemService", IFamilyItemServiceContract.class);
		this.shareTypeServiceContract = getService("shareTypeService", IShareTypeServiceContract.class);
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
	}
	
	private List<AddressDto> addressDtos;
	private List<HouseholdTypeDto> householdTypeDtos;
	
	private String idNumber;
	private String dataJson;
	
	@Override
	public String execute() throws Exception {
		this.addressDtos = this.addressServiceContract.getAddress();
		this.householdTypeDtos = this.shareTypeServiceContract.getAllHouseholdTypeList();
		return SUCCESS;
	}
	
	public String get() throws IOException{
		String jsonState = this.familyItemServiceContract.queryByIdNumber(idNumber);
		response().getWriter().write(jsonState);
		return null;
	}
	
	public String add() throws IOException{
		String stateJson = "";
		NewAndEditTransferHouseholdInfoDto dto = Serialization.toObject(dataJson, NewAndEditTransferHouseholdInfoDto.class);
		//写入操作人员
		dto.setOprUserId(userId());
		dto.setOprDate(new Date());
		stateJson = this.transferMgtServiceContract.addTransferInfo(dto);
		response().getWriter().write(stateJson);
		return null;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}
	public List<HouseholdTypeDto> getHouseholdTypeDtos() {
		return householdTypeDtos;
	}
}
