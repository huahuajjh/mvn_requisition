package com.tq.requisition.presentation.actions.projectManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.tq.requisition.presentation.dto.project.NewProDto;
import com.tq.requisition.presentation.dto.project.ProImportAndExportDto;
import com.tq.requisition.presentation.dto.project.ProTypeDto;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.serviceContract.proMgt.IProMgtServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;

@SuppressWarnings("serial")
public class PmAddPro extends BaseAction{
	/**地址操作对象*/
	private IAddressServiceContract addressServiceContract;
	private IProMgtServiceContract proMgtServiceContract;
	
	/**页面对象*/
	private List<AddressDto> addressDtos;
	private List<ProTypeDto> proTypeDtos;
	
	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}
	public List<ProTypeDto> getProTypeDtos() {
		return proTypeDtos;
	}
	
	public PmAddPro(){
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
		this.proMgtServiceContract = getService("proMgtServiceContract", IProMgtServiceContract.class);
	}
	
	@Override
	public String execute() throws Exception {
		this.addressDtos = this.addressServiceContract.getAddress();
		this.proTypeDtos = this.proMgtServiceContract.getProType();
		return SUCCESS;
	}
	
	private String dataJson = "";
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	
	public String add() throws IOException{
		NewProDto dto = Serialization.toObject(dataJson, NewProDto.class);
		dto.setCreateDate(new Date());
		dto.setCreateUid(userId().toString());
		String stateJson = this.proMgtServiceContract.addPro(dto);
		response().getWriter().write(stateJson);
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
	private String year;
	private String month;
	public void setYear(String year) {
		this.year = year;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String upFile() throws IOException, ConvertErrorException{
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
				 int proTemplateRowIndex = Integer.parseInt(ConfigFileUtil.readByKey("proTemplateRowIndex"));
				 List<ProImportAndExportDto> dtos =  excelInput.getDatas(ProImportAndExportDto.class, proTemplateRowIndex);
				 if(errors.size() > 0){
					 String str = toForMaterJson(OperationResult.ERROR, "上传错误，请按错误按钮查看详细错误", errors.toArray());
					 response().getWriter().write(str);
					 return null;
				 } else if(dtos.size() > 0){
					 Date date = new SimpleDateFormat("yyyy/MM/dd").parse(year + "/" + month +"/01");
					 Date createData = new Date();
					 for (ProImportAndExportDto dto : dtos) {
						 dto.setDate(date);
						 dto.setCreateDate(createData);
						 dto.setCreateUid(userId().toString());
					}
					 String str = proMgtServiceContract.addByFile(dtos);
					 response().getWriter().write(str);
				 } else {
					 String str = toForMaterJson(OperationResult.ERROR, "获取Excel里面没有数据");
					 response().getWriter().write(str);
				 }
			} catch (InstantiationException | NoSuchMethodException | ParseException e) {
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
}
