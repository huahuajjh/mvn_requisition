package com.tq.requisition.presentation.actions.removedDocManagement;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.excel.util.ExcelFactory;
import com.excel.util.intefaces.IExcelOutput;
import com.excel.util.model.ColAttrVal;
import com.excel.util.model.ExcelColData;
import com.tq.requisition.domain.model.removedInfo.RemovedInfo;
import com.tq.requisition.infrastructure.utils.ConfigFileUtil;
import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.removedinfo.RemovedInfoDto;
import com.tq.requisition.presentation.dto.removedinfo.RemovedInfoQueryModel;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.serviceContract.removedInfo.IRemovedInfoServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;

public class RemovedInfoQuery extends BaseAction {
	
	public RemovedInfoQuery(){
		this.addressServiceContract = getService("addressService", IAddressServiceContract.class);
		removedInfoServiceContract = getService("removedInfoService", IRemovedInfoServiceContract.class);
	}
	
	private IRemovedInfoServiceContract removedInfoServiceContract;
	private IAddressServiceContract addressServiceContract;
	
	private List<AddressDto> addressDtos;
	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}

	private int pageNum = 30;
	private int pageIndex = 1;
	private String idNumber;
	private String name ;
	private String streetId ;
	private String communityId ;
	private String zuId ;
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
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

	private String dataJson;
	private String id;
	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		this.addressDtos = this.addressServiceContract.getAddress();
		return super.execute();
	}
	
	public String list() throws IOException{
		RemovedInfoQueryModel queryModel = new RemovedInfoQueryModel();
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(pageNum);
		String stateJson = removedInfoServiceContract.queryByPage4Json(queryModel, pageModel);
		response().getWriter().write(stateJson);
		return null;
	}
	
	public String edit() throws IOException{
		RemovedInfoDto dto = Serialization.toObject(dataJson, RemovedInfoDto.class); 
		String stateJson = removedInfoServiceContract.editInfo(dto);
		response().getWriter().write(stateJson);
		return null;
	}
	
	public String delete() throws IOException{
		String stateJson = removedInfoServiceContract.delInfo(UUID.fromString(id));
		response().getWriter().write(stateJson);
		return null;
	}
	
	public String daoChu() throws ParseException, IOException {
		List<ExcelColData> colDatas = new ArrayList<>();
		ExcelColData[] colDataArr =  Serialization.toObject(daoChuHead, ExcelColData[].class);
		for (ExcelColData excelColData : colDataArr) {
			colDatas.add(excelColData);
		}
		List<ColAttrVal> colAttrVals = new ArrayList<>();
		ColAttrVal[] cAttrValsArr =  Serialization.toObject(daoChuAttrModel, ColAttrVal[].class);
		for (ColAttrVal cVal : cAttrValsArr) {
			colAttrVals.add(cVal);
		}
		List<RemovedInfo> dtos = this.removedInfoServiceContract.exportByQueryModel(new RemovedInfoQueryModel());
		int removedInfoRowIndex = Integer.parseInt(ConfigFileUtil
				.readByKey("removedInfoRowIndex"));
		IExcelOutput excelOutput = null;
		try {
			excelOutput = ExcelFactory.getExcelOutput();
			excelOutput.writeDatas(colDatas);
			excelOutput.writeDatas(RemovedInfo.class, dtos, colAttrVals, removedInfoRowIndex);
			downloadChineseFileName = "removedInfo";
			this.outputStream = excelOutput.getInputStream();
			return SUCCESS;
		} catch (FileNotFoundException | IllegalAccessException e) {
			return ERROR;
		} finally {
			if (excelOutput != null) {
				excelOutput.close();
			}
		}
	}
	private String daoChuAttrModel;
	private String daoChuHead;
	public void setDaoChuAttrModel(String daoChuAttrModel) {
		this.daoChuAttrModel = daoChuAttrModel;
	}
	public void setDaoChuHead(String daoChuHead) {
		this.daoChuHead = daoChuHead;
	}

	// 输出文件流
	private InputStream outputStream;
	private String downloadChineseFileName;
	public InputStream getOutputStream() {
		return outputStream;
	}
	public String getDownloadChineseFileName() {
		return downloadChineseFileName;
	}
}
