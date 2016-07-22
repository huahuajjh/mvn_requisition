package com.tq.requisition.presentation.actions.share;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyQueryModel;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.serviceContract.rmHousehold.IFamilyMgtServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;

@SuppressWarnings("serial")
public class HuList extends BaseAction {
	
	public HuList() {
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
		this.familyMgtServiceContract = getService("fmlService",
				IFamilyMgtServiceContract.class);
	}
	
	private IFamilyMgtServiceContract familyMgtServiceContract;
	private IAddressServiceContract addressServiceContract;
	
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
		FamilyQueryModel familyQueryModel = new FamilyQueryModel();
		familyQueryModel.setIdNumber(idNumber);
		familyQueryModel.setName(huZhuName);
		if(!streetId.equals("")){
			familyQueryModel.setStreetId(UUID.fromString(streetId));
		}
		if(!communityId.equals("")){
			familyQueryModel.setCommunityId(UUID.fromString(communityId));
		}
		if(!zuId.equals("")){
			familyQueryModel.setGroupId(UUID.fromString(zuId));
		}
		if(queryProName != null && !queryProName.equals("")){
			familyQueryModel.setProName(queryProName);
		}
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(pageNum);
		String stateJson = this.familyMgtServiceContract.getFml4HPT(familyQueryModel, pageModel);
		response().getWriter().write(stateJson);
		return null;
	}
	
	private int pageNum = 30;
	private int pageIndex = 1;

	private String queryProName;
	private String proId = "";
	private String idNumber = null;
	private String huZhuName = "";
	private String streetId = "";
	private String communityId = "";
	private String zuId = "";
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public void setHuZhuName(String huZhuName) {
		this.huZhuName = huZhuName;
	}
	public void setStreetId(String streetId) {
		this.streetId = streetId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	public void setZuId(String zuId) {
		this.zuId = zuId;
	}
	public void setQueryProName(String queryProName) {
		this.queryProName = queryProName;
	}
}
