package com.tq.requisition.presentation.actions.management;

import java.io.IOException;
import java.util.UUID;

import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IShareTypeServiceContract;

@SuppressWarnings("serial")
public class SysDataDict extends BaseAction {

	public SysDataDict(){
		this.shareTypeServiceContract = getService("shareTypeService", IShareTypeServiceContract.class);
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
	}
	
	private IShareTypeServiceContract shareTypeServiceContract;
	private IAddressServiceContract addressServiceContract;
	
	private String id;
	private String pid;
	private String name;
	private String ids;
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	//获取所有户口类型
	public String getAllHouseholdType() throws IOException{
		String jsonState = this.shareTypeServiceContract.getAllHouseholdType();
		response().getWriter().write(jsonState);
		return null;
	}
	public String deleteHouseholdType() throws IOException{//删除
		String jsonData = this.shareTypeServiceContract.delHouseholdType(UUID.fromString(id));
		response().getWriter().write(jsonData);
		return null;
	}
	public String editHouseholdType() throws IOException{//修改
		String jsonData = this.shareTypeServiceContract.editHouseholdType(UUID.fromString(id), name);
		response().getWriter().write(jsonData);
		return null;
	}
	public String addHouseholdType() throws IOException{
		String jsonData = this.shareTypeServiceContract.addHouseholdType(name);
		response().getWriter().write(jsonData);
		return null;
	}
	
	//获取所有与户主关系集合
	public String getAllRelationshipType() throws IOException{
		String jsonState = this.shareTypeServiceContract.getAllRelationshipType();
		response().getWriter().write(jsonState);
		return null;
	}
	public String deleteRelationshipType() throws IOException{
		String JsonData = this.shareTypeServiceContract.delRelationshipType(UUID.fromString(id));
		response().getWriter().write(JsonData);
		return null;
	}
	public String editRelationshipType() throws IOException{
		String jsonData = this.shareTypeServiceContract.editRelationshipType(UUID.fromString(id), name);
		response().getWriter().write(jsonData);
		return null;
	}
	public String addRelationshipType() throws IOException{
		String jsonData = this.shareTypeServiceContract.addRelationshipType(name);
		response().getWriter().write(jsonData);
		return null;
	}
	
	//获取所有社保类型集合
	public String getAllSocialsecurityType() throws IOException{
		String jsonState = this.shareTypeServiceContract.getAllSocialsecurityType();
		response().getWriter().write(jsonState);
		return null;
	}
	public String deleteSocialsecurityType() throws IOException{
		String jsonData = this.shareTypeServiceContract.delSSType(UUID.fromString(id));
		response().getWriter().write(jsonData);
		return null;
	}
	public String editSocialsecurityType() throws IOException{
		String jsonData = this.shareTypeServiceContract.editSSType(UUID.fromString(id), name);
		response().getWriter().write(jsonData);
		return null;
	}
	public String addSocialsecurityType() throws IOException{
		String jsonData = this.shareTypeServiceContract.addSSType(name);
		response().getWriter().write(jsonData);
		return null;
	}
	
	//获取所有地址
	public String getAddress() throws IOException{
		String jsonState = this.addressServiceContract.getAllAddresses();
		response().getWriter().write(jsonState);
		return null;
	}
	public String deleteAddress() throws IOException{
		String[] arr = Serialization.toObject(ids, String[].class);
		UUID[] addressIds =new UUID[arr.length];
		for (int i = 0; i < arr.length; i++) {
			addressIds[i] = UUID.fromString(arr[i]);
		}
		String jsonState = this.addressServiceContract.deleteAddress(addressIds);
		response().getWriter().write(jsonState);
		return null;
	}
	public String editAddress() throws IOException{
		AddressDto dto = new AddressDto();
		dto.setId(UUID.fromString(id));
		dto.setName(name);
		if(null==pid||pid.trim().equals(""))
		{
			dto.setPid(null);
		}
		else {
			dto.setPid(UUID.fromString(pid));
		}
		String jsonDate = this.addressServiceContract.editAddress(dto);
		response().getWriter().write(jsonDate);
		return null;
	}
	public String addAddress() throws IOException{
		AddressDto dto = new AddressDto();
		dto.setName(name);
		if(pid != null && !pid.equals("")){
			dto.setPid(UUID.fromString(pid));
		}
		String jsonDate = this.addressServiceContract.addNewAddress(dto);
		response().getWriter().write(jsonDate);
		return null;
	}
	
	//政策法规类型
	public String getPolicyType(){
		return null;
	}
	public String editPolicyType(){
		return null;
	}
	public String deletePolicyType(){
		return null;
	}
	public String addPolicyType(){
		return null;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
}
