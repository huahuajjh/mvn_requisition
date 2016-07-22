package com.tq.requisition.presentation.actions.supervisionManagement;

import java.util.List;

import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.project.ProTypeDto;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.serviceContract.proMgt.IProMgtServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;

public class InfoSummary extends BaseAction {

	/**地址操作对象*/
	private IAddressServiceContract addressServiceContract;
	private IProMgtServiceContract proMgtServiceContract;

	/**页面对象*/
	private List<AddressDto> addressDtos;
	private List<ProTypeDto> proTypeDtos;
	
	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}
	public List<ProTypeDto> getProTypeDtos() {
		return proTypeDtos;
	}
	
	public InfoSummary(){
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
		this.proMgtServiceContract = getService("proMgtServiceContract", IProMgtServiceContract.class);
	}
	
	@Override
	public String execute() throws Exception {
		this.addressDtos = this.addressServiceContract.getAddress();
		System.out.println(this.addressDtos.size());
		this.proTypeDtos = this.proMgtServiceContract.getProType();
		return super.execute();
	}

	private String headId;
	public void setHeadId(String headId) {
		this.headId = headId;
	}
	public void getPersonsAtHeadId(){
		
	}
	
	private String id;
	public void setId(String id) {
		this.id = id;
	}
	
	public void zhuanHuMsg(){
		
	}
	
	public void sheBaoMsg(){
		
	}
	
	public void gouFangQuanMsg(){
		
	}
}
