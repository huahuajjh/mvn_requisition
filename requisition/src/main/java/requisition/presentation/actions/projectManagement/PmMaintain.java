package com.tq.requisition.presentation.actions.projectManagement;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.project.ProQueryModel;
import com.tq.requisition.presentation.dto.project.ProTypeDto;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.serviceContract.proMgt.IProMgtServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;

public class PmMaintain extends BaseAction {
	
	public PmMaintain(){
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
		this.proMgtServiceContract = getService("proMgtServiceContract", IProMgtServiceContract.class);
	}
	
	/**地址操作对象*/
	private IAddressServiceContract addressServiceContract;
	private IProMgtServiceContract proMgtServiceContract;
	
	// ---模糊查询列表
	private int pageNum = 30;
	private int pageIndex = 1;
	private int typeId = 0;
	private int annouceQueue = 0;
	private String streetId = "";
	private String communityId = "";
	private String queryProName = "";
	private String address;
	
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public void setAnnouceQueue(int annouceQueue) {
		this.annouceQueue = annouceQueue;
	}
	public void setStreetId(String streetId) {
		this.streetId = streetId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	public void setQueryProName(String queryProName) {
		this.queryProName = queryProName;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**页面对象*/
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
	
	public String list() throws IOException{
		ProQueryModel queryModel = new ProQueryModel();
		queryModel.setCreateUId(userId().toString());
		queryModel.setAnnouceQueue(annouceQueue);
		if (!communityId.equals(""))
			queryModel.setCommunityId(UUID.fromString(communityId));
		if (!queryProName.equals(""))
			queryModel.setProName(queryProName);
		if (!streetId.equals(""))
			queryModel.setStreetId(UUID.fromString(streetId));
		queryModel.setTypeId(typeId);
		queryModel.setAddress(address);
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(pageNum);
		String jsonDataString = this.proMgtServiceContract.getProListFuzzy(
				queryModel, pageModel);
		response().getWriter().write(jsonDataString);
		return null;
	}
}
