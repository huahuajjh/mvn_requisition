package com.tq.requisition.presentation.actions.registedInfoManagement;

import java.io.IOException;
import java.util.List;

import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.registedAgr.RegistedAgricultureInfoDto;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.serviceContract.registedAgric.IRegistedAgricServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;

@SuppressWarnings("serial")
public class AddRegistedInfo  extends BaseAction {

	public AddRegistedInfo(){
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
		this.registedAgricServiceContract = getService("registedAgricService", IRegistedAgricServiceContract.class);
	}
	
	private List<AddressDto> addressDtos;
	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}
	
	private IRegistedAgricServiceContract registedAgricServiceContract;
	private IAddressServiceContract addressServiceContract;
	
	private String dataJson;
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	
	@Override
	public String execute() throws Exception {
		this.addressDtos = this.addressServiceContract.getAddress();
		return super.execute();
	}

	public String add() throws IOException{
		RegistedAgricultureInfoDto dto = Serialization.toObject(dataJson, RegistedAgricultureInfoDto.class); 
		String jsonData = registedAgricServiceContract.addInfo(dto);
		response().getWriter().write(jsonData);
		return null;
	}
}
