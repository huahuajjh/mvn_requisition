package com.tqmars.requisition.presentation.actions.transferAccountManagement;

import java.util.List;

import com.tqmars.requisition.presentation.actions.BaseAction;
import com.tqmars.requisition.presentation.dto.share.AddressDto;
import com.tqmars.requisition.presentation.serviceContract.share.IAddressServiceContract;

public class TaManagement extends BaseAction {
	
	private IAddressServiceContract addressServiceContract;
	
	public TaManagement(){
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
}
