package com.tq.requisition.presentation.actions.socialSecurityMansgement;

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
import com.tq.requisition.domain.model.socialsecurity.SocialsecurityInfo;
import com.tq.requisition.infrastructure.utils.ConfigFileUtil;
import com.tq.requisition.infrastructure.utils.Formater.OperationResult;
import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.share.SocialsecurityTypeDto;
import com.tq.requisition.presentation.dto.socialsecurityMgt.NewSocialsecurityDto;
import com.tq.requisition.presentation.dto.socialsecurityMgt.SsImportAndExportDto;
import com.tq.requisition.presentation.serviceContract.rmHousehold.IFamilyItemServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IShareTypeServiceContract;
import com.tq.requisition.presentation.serviceContract.socialsecurityMgt.ISocialsecurityMgtServiceContract;

@SuppressWarnings("serial")
public class SolmImportFile extends BaseAction {

	private ISocialsecurityMgtServiceContract ssService;
	private IFamilyItemServiceContract familyItemServiceContract;
	private IShareTypeServiceContract shareTypeServiceContract;
	
	private List<SocialsecurityTypeDto> socialsecurityTypeDtos;
	public List<SocialsecurityTypeDto> getSocialsecurityTypeDtos() {
		return socialsecurityTypeDtos;
	}

	private String idNumber;
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	private String dataJson;

	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

	public SolmImportFile(){
		this.ssService = getService("ssService", ISocialsecurityMgtServiceContract.class);
		this.familyItemServiceContract = getService("fmlItemService", IFamilyItemServiceContract.class);
		this.shareTypeServiceContract = getService("shareTypeService", IShareTypeServiceContract.class);
	}
	
	@Override
	public String execute() throws Exception {
		this.socialsecurityTypeDtos = this.shareTypeServiceContract.getAllSocialsecurityTypeList();
		return super.execute();
	}
	
	public String get() throws IOException{
		System.out.println(idNumber);
		String jsonState = this.familyItemServiceContract.queryByIdNumber(idNumber);
		response().getWriter().write(jsonState);
		return null;
	}
	
	public String add() throws IOException{
		String stateJson = "";
		try {
			NewSocialsecurityDto dto = Serialization.toObject(dataJson, NewSocialsecurityDto.class);
			//写入操作人员
			dto.setOprUserId(userId());
			dto.setOprDate(new Date());
			dto.setCreateDate(new Date());
			dto.setCreateUid(userId().toString());
			stateJson = this.ssService.addSSInfo(dto);
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
				 List<SsImportAndExportDto> items =  excelInput.getDatas(SsImportAndExportDto.class, 2);
				if(errors.size() > 0){
					 String str = toForMaterJson(OperationResult.ERROR, "上传错误，请按错误按钮查看详细错误", errors.toArray());
					 response().getWriter().write(str);
					 return null;
				 } else {
					 String stateJsonString = "";
					 if(items.size() > 0 ){
						 for (SsImportAndExportDto item : items) {
								item.setUserId(userId());
								item.setCreateDate(new Date());
								item.setCreateUid(userId().toString());
							}
						 stateJsonString = this.ssService.importSS(items);
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
    private String fileContentType;
	public void setFile(File file) {
		this.file = file;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
}
