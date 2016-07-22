package com.tq.requisition.presentation.actions.removedDocManagement;

import java.util.List;

import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;

public class RemovedInfoMaintain extends BaseAction {
	
	public RemovedInfoMaintain(){
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
	}
	
	private IAddressServiceContract addressServiceContract;
	
	private List<AddressDto> addressDtos;
	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}
	
	@Override
	public String execute() throws Exception {
		this.addressDtos = this.addressServiceContract.getAddress();
		return super.execute();
	}
}
