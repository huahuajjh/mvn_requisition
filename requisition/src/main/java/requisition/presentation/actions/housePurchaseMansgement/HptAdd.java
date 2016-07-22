package com.tq.requisition.presentation.actions.housePurchaseMansgement;

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
import com.tq.requisition.infrastructure.utils.ConfigFileUtil;
import com.tq.requisition.infrastructure.utils.Formater.OperationResult;
import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.hpt.HPTImportAndExport;
import com.tq.requisition.presentation.dto.hpt.HousePuraseTicketDto;
import com.tq.requisition.presentation.dto.hpt.PersonAndHPTDto;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemDto;
import com.tq.requisition.presentation.dto.share.HouseholdTypeDto;
import com.tq.requisition.presentation.dto.share.RelationshipTypeDto;
import com.tq.requisition.presentation.serviceContract.hptMgt.IHPTMgtFmlItemServiceContract;
import com.tq.requisition.presentation.serviceContract.hptMgt.IHPTMgtServiceContract;
import com.tq.requisition.presentation.serviceContract.rmHousehold.IFamilyItemServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IShareTypeServiceContract;

@SuppressWarnings("serial")
public class HptAdd extends BaseAction {

	private IFamilyItemServiceContract familyItemServiceContract;
	private IHPTMgtServiceContract hptMgtServiceContract;
	private IShareTypeServiceContract shareTypeServiceContract;
	private IHPTMgtFmlItemServiceContract htpMgtFmlItemServiceContract;
	
	public HptAdd(){
		this.familyItemServiceContract = getService("fmlItemService", IFamilyItemServiceContract.class);
		this.hptMgtServiceContract = getService("hptService", IHPTMgtServiceContract.class);
		this.shareTypeServiceContract = getService("shareTypeService", IShareTypeServiceContract.class);
		this.htpMgtFmlItemServiceContract = getService("hptFmlItemService", IHPTMgtFmlItemServiceContract.class);
	}
	
	private List<HouseholdTypeDto> householdTypeDtos;
	private List<RelationshipTypeDto> relationshipTypeDtos;
	
	public List<HouseholdTypeDto> getHouseholdTypeDtos() {
		return householdTypeDtos;
	}
	public List<RelationshipTypeDto> getRelationshipTypeDtos() {
		return relationshipTypeDtos;
	}
	
	private String idNumber;
	private String name;
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private String dataJson;
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	@Override
	public String execute() throws Exception {
		this.householdTypeDtos = this.shareTypeServiceContract.getAllHouseholdTypeList();
		this.relationshipTypeDtos = this.shareTypeServiceContract.getAllRelationshipTypeList();
		return SUCCESS;
	}
	
	public String get() throws IOException{
		String jsonState = this.familyItemServiceContract.queryByIdNumber(idNumber);
		response().getWriter().write(jsonState);
		return null;
	}
	
	public String add() throws IOException{
		String stateJson = "";
		try {
			HousePuraseTicketDto dto = Serialization.toObject(dataJson, HousePuraseTicketDto.class);
			dto.setCreateDate(new Date());
			dto.setCreateUId(userId().toString());
			stateJson = this.hptMgtServiceContract.add(dto);
		} catch (Exception e) {
			stateJson = toForMaterJson(OperationResult.ERROR,"数据格式不正确");
		}
		response().getWriter().write(stateJson);
		return null;
	}
	
	public String addFmlItem() throws IOException{
		String stateJson = "";
		FamilyItemDto dto = Serialization.toObject(dataJson, FamilyItemDto.class);
		stateJson = this.htpMgtFmlItemServiceContract.add(dto);
		response().getWriter().write(stateJson);
		return null;
	}
	
	public String addFmlItemAndHpt() throws IOException{
		String stateJson = "";
		PersonAndHPTDto dto = Serialization.toObject(dataJson, PersonAndHPTDto.class);
		dto.setCreateDate(new Date());
		dto.setCreateUId(userId().toString());
		stateJson = this.htpMgtFmlItemServiceContract.add(dto);
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
				 int rowIndex = Integer.parseInt(ConfigFileUtil.readByKey("housePurchaseRowIndex"));
				 List<HPTImportAndExport> items =  excelInput.getDatas(HPTImportAndExport.class, rowIndex);
				if(errors.size() > 0){
					 String str = toForMaterJson(OperationResult.ERROR, "上传错误，请按错误按钮查看详细错误", errors.toArray());
					 response().getWriter().write(str);
					 return null;
				 } else {
					 String stateJsonString = "";
					 if(items.size() > 0 ){
						 Date nowDate = new Date();
						 for (HPTImportAndExport dto : items) {
							dto.setCreateDate(nowDate);
							dto.setCreateUId(userId().toString());
						}
						 stateJsonString = this.hptMgtServiceContract.importHPT(items);
					 } else {
						 stateJsonString = toForMaterJson(OperationResult.SUCCESS, "文件中没有数据");
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
