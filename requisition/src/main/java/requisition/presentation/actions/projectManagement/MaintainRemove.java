package com.tq.requisition.presentation.actions.projectManagement;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyQueryModel;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.dto.share.HouseholdTypeDto;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.share.RelationshipTypeDto;
import com.tq.requisition.presentation.serviceContract.rmHousehold.IFamilyItemServiceContract;
import com.tq.requisition.presentation.serviceContract.rmHousehold.IFamilyMgtServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IShareTypeServiceContract;

public class MaintainRemove  extends BaseAction {
	
	private IAddressServiceContract addressServiceContract;
	private IFamilyItemServiceContract familyItemServiceContract;
	private IFamilyMgtServiceContract familyMgtServiceContract;
	private IShareTypeServiceContract shareTypeServiceContract;

	private List<AddressDto> addressDtos;
	private List<HouseholdTypeDto> householdTypeDtos;
	private List<RelationshipTypeDto> relationshipTypeDtos;

	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}

	public List<HouseholdTypeDto> getHouseholdTypeDtos() {
		return householdTypeDtos;
	}

	public List<RelationshipTypeDto> getRelationshipTypeDtos() {
		return relationshipTypeDtos;
	}
	
	private int pageNum = 30;
	private int pageIndex = 1;

	private String queryProName;
	private String idNumber = null;
	private String huZhuName = "";
	private String streetId = "";
	private String communityId = "";
	private String zuId = "";
	private String address;
	private String id;
	
	public void setId(String id) {
		this.id = id;
	}

	public MaintainRemove() {
		this.addressServiceContract = getService("addressService",
				IAddressServiceContract.class);
		this.familyItemServiceContract = getService("fmlItemService",
				IFamilyItemServiceContract.class);
		this.shareTypeServiceContract = getService("shareTypeService",
				IShareTypeServiceContract.class);
		this.familyMgtServiceContract = getService("fmlService",
				IFamilyMgtServiceContract.class);
	}
	
	@Override
	public String execute() throws Exception {
		this.addressDtos = this.addressServiceContract.getAddress();
		this.householdTypeDtos = this.shareTypeServiceContract
				.getAllHouseholdTypeList();
		this.relationshipTypeDtos = this.shareTypeServiceContract
				.getAllRelationshipTypeList();
		return SUCCESS;
	}
	
	public String list() throws IOException{
		FamilyQueryModel familyQueryModel = new FamilyQueryModel();
		familyQueryModel.setCreateUId(userId().toString());
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
		familyQueryModel.setAddress(address);
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(pageNum);
		String stateJson = this.familyMgtServiceContract.queryFmlByFuzzy(familyQueryModel, pageModel);
		response().getWriter().write(stateJson);
		return null;
	}
	
	public String delete() throws IOException{
		String state = familyItemServiceContract.deleteFmlItem(UUID.fromString(id));
		response().getWriter().write(state);
		return null;
	}
	
	public void setPageNum(int pageNum) {
		if(pageNum < 0) return;
		this.pageNum = pageNum;
	}

	public void setPageIndex(int pageIndex) {
		if(pageIndex < 0) return;
		this.pageIndex = pageIndex;
	}

	public void setIdNumber(String idNumber) {
		if(idNumber.trim().equals("")) return;
		this.idNumber = idNumber;
	}

	public void setStreetId(String streetId) {
		if(streetId == null) return;
		this.streetId = streetId;
	}

	public void setCommunityId(String communityId) {
		if(communityId == null) return;
		this.communityId = communityId;
	}

	public void setHuZhuName(String huZhuName) {
		if(huZhuName == null) return;
		this.huZhuName = huZhuName;
	}
	public void setZuId(String zuId) {
		if(zuId == null) return;
		this.zuId = zuId;
	}
	public void setQueryProName(String queryProName) {
		this.queryProName = queryProName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
