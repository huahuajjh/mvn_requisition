﻿package com.tqmars.requisition.presentation.actions.share;

import java.util.List;

import com.tqmars.requisition.presentation.actions.BaseAction;
import com.tqmars.requisition.presentation.dto.project.ProTypeDto;
import com.tqmars.requisition.presentation.dto.share.AddressDto;
import com.tqmars.requisition.presentation.serviceContract.proMgt.IProMgtServiceContract;
import com.tqmars.requisition.presentation.serviceContract.share.IAddressServiceContract;

@SuppressWarnings("serial")
public class ProjectList extends BaseAction {

	private IProMgtServiceContract proMgtServiceContract;
	private IAddressServiceContract addressServiceContract;
	
	public ProjectList() {
		this.addressServiceContract = getService("addressService",
				IAddressServiceContract.class);
		this.proMgtServiceContract = getService("proMgtServiceContract",
				IProMgtServiceContract.class);
	}
	
	private List<AddressDto> addressDtos;
	private List<ProTypeDto> proTypeDtos;
	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}
	public List<ProTypeDto> getProTypeDtos() {
		return proTypeDtos;
	}

	@Override
	public String execute() throws Exception {
		this.addressDtos = this.addressServiceContract.getAddress();
		this.proTypeDtos = this.proMgtServiceContract.getProType();
		return SUCCESS;
	}

}
