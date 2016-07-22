package com.tq.requisition.presentation.actions.socialSecurityMansgement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.infrastructure.utils.Formater.OperationResult;
import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.share.SocialsecurityTypeDto;
import com.tq.requisition.presentation.dto.socialsecurityMgt.NewSocialsecurityDto;
import com.tq.requisition.presentation.dto.socialsecurityMgt.SocialsecurityQueryModel;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IShareTypeServiceContract;
import com.tq.requisition.presentation.serviceContract.socialsecurityMgt.ISocialsecurityMgtServiceContract;

@SuppressWarnings("serial")
public class SolmInfoBatch extends BaseAction {
	
	private IAddressServiceContract addressServiceContract;
	private IShareTypeServiceContract shareTypeServiceContract;
	private ISocialsecurityMgtServiceContract socialsecurityMgtServiceContract;
	
	private List<AddressDto> addressDtos;
	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}
	private List<SocialsecurityTypeDto> socialsecurityTypeDtos;
	public List<SocialsecurityTypeDto> getSocialsecurityTypeDtos() {
		return socialsecurityTypeDtos;
	}
	
	private String queryProName;
	private int pageNum = 30;
	private int pageIndex = 1;
	private String streetId  = "";
	private String communityId  = "";
	private String proId = "";
	private String idNumber;
	private String zuId;
	private String address;
	
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void setStreetId(String streetId) {
		if(streetId == null) return;
		this.streetId = streetId;
	}
	public void setCommunityId(String communityId) {
		if(communityId == null) return;
		this.communityId = communityId;
	}
	public void setProId(String proId) {
		if(proId == null) return;
		this.proId = proId;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public void setZuId(String zuId) {
		this.zuId = zuId;
	}
	public void setQueryProName(String queryProName) {
		this.queryProName = queryProName;
	}
	
	private String dataJson;
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	
	public SolmInfoBatch(){
		this.socialsecurityMgtServiceContract = getService("ssService", ISocialsecurityMgtServiceContract.class);
		this.shareTypeServiceContract = getService("shareTypeService", IShareTypeServiceContract.class);
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
	}
	
	@Override
	public String execute() throws Exception {
		this.addressDtos = this.addressServiceContract.getAddress();
		this.socialsecurityTypeDtos = this.shareTypeServiceContract.getAllSocialsecurityTypeList();
		return super.execute();
	}
	
	public String list() throws IOException{
		SocialsecurityQueryModel queryModal = new SocialsecurityQueryModel();
		if(!communityId.equals("")){
			queryModal.setCommunityId(UUID.fromString(communityId));
		}
		if(!streetId.equals("")){
			queryModal.setStreetId(UUID.fromString(streetId));
		}
		if(queryProName != null && !queryProName.equals("")){
			queryModal.setProName(queryProName);
		}
		if(idNumber !=null && !idNumber.equals("")){
			queryModal.setIdNumber(idNumber);
		}
		if(zuId !=null && !zuId.equals("")){
			queryModal.setGroupId(UUID.fromString(zuId));
		}
		if(idNumber != null && !idNumber.equals("")){
			queryModal.setIdNumber(idNumber);
		}
		queryModal.setAddress(address);
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(pageNum);
		String jsonData = this.socialsecurityMgtServiceContract.queryFuzzyByAddJson(queryModal, pageModel);
		response().getWriter().write(jsonData);
		return null;
	}
	
	public String add() throws IOException{
		String stateJson = "";
		 try {
			 NewSocialsecurityDto[] dto = Serialization.toObject(dataJson, NewSocialsecurityDto[].class);
			//写入操作人员
			 System.out.println(dto);
			List<NewSocialsecurityDto> dtos = new ArrayList<NewSocialsecurityDto>();
			Date date = new Date();
			for (NewSocialsecurityDto t : dto) {
				t.setOprUserId(userId());
				t.setOprDate(date);
				t.setCreateDate(date);
				t.setCreateUid(userId().toString());
				dtos.add(t);
			}
			 stateJson = this.socialsecurityMgtServiceContract.addBatch(dtos);
		} catch (Exception e) {
			stateJson = toForMaterJson(OperationResult.ERROR,"数据格式不正确");
		}
		response().getWriter().write(stateJson);
		return null;
	}
}
