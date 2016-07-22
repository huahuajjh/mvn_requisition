package com.tq.requisition.presentation.actions.projectManagement;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.infrastructure.utils.ThreeState;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemQueryModel;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.dto.share.HouseholdTypeDto;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.share.RelationshipTypeDto;
import com.tq.requisition.presentation.serviceContract.rmHousehold.IFamilyItemServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IShareTypeServiceContract;

public class MaintainPersonRemove extends BaseAction {
	
	private IAddressServiceContract addressServiceContract;
	private IFamilyItemServiceContract familyItemServiceContract;
	private IShareTypeServiceContract shareTypeServiceContract;
	
	public MaintainPersonRemove(){
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
		this.familyItemServiceContract = getService("fmlItemService", IFamilyItemServiceContract.class);
		this.shareTypeServiceContract = getService("shareTypeService", IShareTypeServiceContract.class);
	}
	
	private List<HouseholdTypeDto> householdTypeDtos;
	private List<RelationshipTypeDto> relationshipTypeDtos;
	private List<AddressDto> addressDtos;
	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}
	public List<RelationshipTypeDto> getRelationshipTypeDtos() {
		return relationshipTypeDtos;
	}
	
	private int pageNum = 30;
	private int pageIndex = 1;
	
	private String queryProName = "";
	private String idNumber = null;
	private String huZhuName = null;
	private String name = null;
	private int isOnlyChild = 2;
	private int half = 2;
	private int isTransfer = 2;
	private int isSSecurity = 2;
	private String streetId  = "";
	private String communityId  = "";
	private String zu  = "";
	private String address;
	
	@Override
	public String execute() throws Exception {
		this.householdTypeDtos = this.shareTypeServiceContract.getAllHouseholdTypeList();
		this.addressDtos = this.addressServiceContract.getAddress();
		this.relationshipTypeDtos = this.shareTypeServiceContract.getAllRelationshipTypeList();
		return SUCCESS;
	}
	
	public String list() throws IOException{
		FamilyItemQueryModel queryModel = new FamilyItemQueryModel();
		queryModel.setCreateUId(userId().toString());
		queryModel.setIdNumber(idNumber);
		queryModel.setName(name);
		queryModel.setHeadName(huZhuName);
		if(!streetId.equals("")){
			queryModel.setStreetId(UUID.fromString(streetId));
		}
		if(!communityId.equals("")){
			queryModel.setCommunityId(UUID.fromString(communityId));
		}
		if(!zu.equals("")){
			queryModel.setGroupId(UUID.fromString(zu));
		}
		if(queryProName != null && !queryProName.equals("")){
			queryModel.setProName(queryProName);
		}
		queryModel.setHalf(ThreeState.ALL.obtainByInt(half));
		queryModel.setIsOnlyChild(ThreeState.ALL.obtainByInt(isOnlyChild));
		queryModel.setIsSSecurity(ThreeState.ALL.obtainByInt(isSSecurity));
		queryModel.setIsTransfer(ThreeState.ALL.obtainByInt(isTransfer));
		queryModel.setAddress(address);
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(pageNum);
		String stateJson = this.familyItemServiceContract.queryFamilyItemsByFuzzy(queryModel, pageModel);
		response().getWriter().write(stateJson);
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
		if(idNumber.equals("")) return;
		this.idNumber = idNumber;
	}
	public void setIsOnlyChild(int isOnlyChild) {
		if(isOnlyChild <0 || isOnlyChild>2) return;
		this.isOnlyChild = isOnlyChild;
	}
	public void setHalf(int half) {
		if(half <0 || half>2) return;
		this.half = half;
	}
	public void setStreetId(String streetId) {
		if(streetId == null) return;
		this.streetId = streetId;
	}
	public void setCommunityId(String communityId) {
		if(communityId == null) return;
		this.communityId = communityId;
	}
	public void setIsTransfer(int isTransfer) {
		if(isTransfer <0 || isTransfer>2) return;
		this.isTransfer = isTransfer;
	}
	public void setIsSSecurity(int isSSecurity) {
		if(isSSecurity <0 || isSSecurity>2) return;
		this.isSSecurity = isSSecurity;
	}
	public List<HouseholdTypeDto> getHouseholdTypeDtos() {
		return householdTypeDtos;
	}
	public void setHuZhuName(String huZhuName) {
		if(huZhuName == null) return;
		this.huZhuName = huZhuName;
	}
	public void setName(String name) {
		if(name == null) return;
		this.name = name;
	}
	public void setZu(String zu) {
		if(zu == null) return;
		this.zu = zu;
	}
	public void setQueryProName(String queryProName) {
		this.queryProName = queryProName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
