package com.tq.requisition.presentation.actions.projectManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.excel.util.ExcelFactory;
import com.excel.util.error.ConvertErrorException;
import com.excel.util.intefaces.IErrorCallback;
import com.excel.util.intefaces.IExcelInput;
import com.excel.util.model.ExcelRowError;
import com.mysql.fabric.xmlrpc.base.Data;
import com.tq.requisition.domain.model.removeFamily.Family;
import com.tq.requisition.infrastructure.utils.ConfigFileUtil;
import com.tq.requisition.infrastructure.utils.Formater.OperationResult;
import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.project.ProTypeDto;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyDto;
import com.tq.requisition.presentation.dto.rmHousehold.FmlImportAndExport;
import com.tq.requisition.presentation.dto.rmHousehold.FmlItemImportAndExport;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.dto.share.HouseholdTypeDto;
import com.tq.requisition.presentation.dto.share.RelationshipTypeDto;
import com.tq.requisition.presentation.serviceContract.proMgt.IProMgtServiceContract;
import com.tq.requisition.presentation.serviceContract.rmHousehold.IFamilyMgtServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IShareTypeServiceContract;

@SuppressWarnings("serial")
public class UploadRemoveInfo extends BaseAction {
	
	/**地址操作对象*/
	private IAddressServiceContract addressServiceContract;
	private IFamilyMgtServiceContract familyMgtServiceContract;
	private IShareTypeServiceContract shareTypeServiceContract;
	private IProMgtServiceContract proMgtServiceContract;
	
	public UploadRemoveInfo(){
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
		this.familyMgtServiceContract = getService("fmlService", IFamilyMgtServiceContract.class);
		this.shareTypeServiceContract = getService("shareTypeService", IShareTypeServiceContract.class);
		this.proMgtServiceContract = getService("proMgtServiceContract", IProMgtServiceContract.class);
	}
	
	private List<AddressDto> addressDtos;
	private List<HouseholdTypeDto> householdTypeDtos;
	private List<RelationshipTypeDto> relationshipTypeDtos;
	private List<ProTypeDto> proTypeDtos;
	
	public List<ProTypeDto> getProTypeDtos() {
		return proTypeDtos;
	}
	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}
	public List<HouseholdTypeDto> getHouseholdTypeDtos() {
		return householdTypeDtos;
	}
	public List<RelationshipTypeDto> getRelationshipTypeDtos() {
		return relationshipTypeDtos;
	}

	private String dataJson = "";
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

	@Override
	public String execute() throws Exception {
		this.addressDtos = this.addressServiceContract.getAddress();
		this.householdTypeDtos = this.shareTypeServiceContract.getAllHouseholdTypeList();
		this.relationshipTypeDtos = this.shareTypeServiceContract.getAllRelationshipTypeList();
		this.proTypeDtos = this.proMgtServiceContract.getProType();
		return SUCCESS;
	}		
	
	public String add() throws IOException{
		String stateJson = "";
		try {
			FamilyDto family = Serialization.toObject(dataJson, FamilyDto.class);
			family.setCreateDate(new Date());
			family.setCreateUid(userId().toString());
			stateJson = this.familyMgtServiceContract.addFamily(family);
		} catch (Exception e) {
			stateJson = toForMaterJson(OperationResult.ERROR,"数据格式不正确");
		}
		response().getWriter().write(stateJson);
		return null;
	}
	
	public String upFile() throws IOException{
		InputStream fileInputStream = null;
		IExcelInput excelInput = null;
		try {
			final List<ExcelRowError> errors = new ArrayList<ExcelRowError>();
			fileInputStream = new FileInputStream(file);
			int errorNum = Integer.parseInt(ConfigFileUtil.readByKey("errorNum"));
			excelInput = ExcelFactory.getExcelInput(fileInputStream,errorNum, new IErrorCallback() {
				@Override
				public void callback(List<ExcelRowError> subErrors) {
					errors.addAll(subErrors);
				}
			});
			 try {
				 int familyTemplateChildIndex = Integer.parseInt(ConfigFileUtil.readByKey("familyTemplateChildIndex"));
				 List<Family> dtos = new ArrayList<Family>();
				FmlImportAndExport dto = excelInput.getData(FmlImportAndExport.class);
				if(errors.size() == 0 && dto !=null){
					List<FmlItemImportAndExport> items =  excelInput.getDatas(FmlItemImportAndExport.class, familyTemplateChildIndex);
					if(errors.size() == 0){
						Family family = dto.toFamily(items);
						family.setCreateDate(new Date());
						family.setCreateUid(userId().toString());
						dtos.add(family);
					}
				}
				if(errors.size() > 0){
					 String str = toForMaterJson(OperationResult.ERROR, "上传错误，请按错误按钮查看详细错误", errors.toArray());
					 response().getWriter().write(str);
					 return null;
				 } else {
					 String stateJsonString = "";
					 if(dtos.size() > 0){
						 stateJsonString = this.familyMgtServiceContract.importFml(dtos.get(0));
					 } else {
						 stateJsonString = toForMaterJson(OperationResult.FAIL, "文件中没有数据");
					 }
					 response().getWriter().write(stateJsonString);
				 }
			} catch (InstantiationException | NoSuchMethodException
					| ConvertErrorException e) {
				String str = toForMaterJson(OperationResult.ERROR, "获取Excel列表错误");
				 response().getWriter().write(str);
			}
		} catch (IOException e) {
			String str = toForMaterJson(OperationResult.ERROR, "请上传正确的Excel文件");
			 response().getWriter().write(str);
		} finally{
			file.delete();
			if(excelInput != null){
				excelInput.close();
			}
			if(fileInputStream != null){
				fileInputStream.close();
			}
		}
		return null;
	}
	
    private File file ;  
    private String fileFileName ;
	public void setFile(File file) {
		this.file = file;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
}
