package com.tq.requisition.presentation.actions.registedInfoManagement;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.registedAgr.RegistedAgricQueryModel;
import com.tq.requisition.presentation.dto.registedAgr.RegistedAgricultureInfoDto;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.serviceContract.registedAgric.IRegistedAgricServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;

public class QueryRegistedInfo extends BaseAction {
	
	public QueryRegistedInfo(){
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
		this.registedAgricServiceContract = getService("registedAgricService", IRegistedAgricServiceContract.class);
	}
	
	private List<AddressDto> addressDtos;
	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}
	
	private IRegistedAgricServiceContract registedAgricServiceContract;
	private IAddressServiceContract addressServiceContract;
	
	private int pageNum = 30;
	private int pageIndex = 1;
	private String idNumber;
	private String name;
	private String streetId;
	private String communityId;
	private String zuId;
	public void setName(String name) {
		this.name = name;
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
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	@Override
	public String execute() throws Exception {
		this.addressDtos = this.addressServiceContract.getAddress();
		return super.execute();
	}
	
	public String list() throws IOException{
		RegistedAgricQueryModel queryModel = new RegistedAgricQueryModel();
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(pageNum);
		String stateJsonString = registedAgricServiceContract.queryByPage4Json(queryModel, pageModel);
		response().getWriter().write(stateJsonString);
		return null;
	}
	
	public String edit() throws IOException{
		RegistedAgricultureInfoDto dto = Serialization.toObject(dataJson, RegistedAgricultureInfoDto.class); 
		String statJson =  registedAgricServiceContract.editInfo(dto);
		response().getWriter().write(statJson);
		return null;
	}
	
	public String delete() throws IOException{
		String stateJson = registedAgricServiceContract.delInfo(UUID.fromString(id));
		response().getWriter().write(stateJson);
		return null;
	}

	private String dataJson;
	private String id;
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	public void setId(String id) {
		this.id = id;
	}
}
