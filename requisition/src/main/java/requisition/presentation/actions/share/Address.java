package com.tq.requisition.presentation.actions.share;

import java.io.IOException;
import java.util.UUID;

import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;

public class Address extends BaseAction {

	/**地址操作*/
	private IAddressServiceContract addressServiceContract;
	
	public Address(){
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
	}
	
	/**提交地址*/
	private String id;
	
	public void setId(String id) {
		this.id = id;
	}

	public String listById() throws IOException{
		UUID uId = null;
		if(id!=null && !id.equals("")){
			uId = UUID.fromString(id);
		}
		String dataJson = addressServiceContract.getAddress(uId);
		response().getWriter().write(dataJson);
		return null;
	}
	
}
