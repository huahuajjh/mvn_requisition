package com.tq.requisition.presentation.actions.housePurchaseMansgement;

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
import com.tq.requisition.presentation.dto.hpt.HPTFuzzyQueryModel;
import com.tq.requisition.presentation.dto.hpt.HPTReceiveTableDto;
import com.tq.requisition.presentation.dto.project.ProExportCondition;
import com.tq.requisition.presentation.dto.removedinfo.RemovedInfoQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.serviceContract.hptMgt.IHPTMgtServiceContract;

public class HptIssue extends BaseAction {

private IHPTMgtServiceContract hptMgtServiceContract;
	
	public HptIssue(){
		this.hptMgtServiceContract = getService("hptService", IHPTMgtServiceContract.class);
	}
	
	private String queryProName;
	private int pageNum = 30;
	private int pageIndex = 1;
	private String proId = "";
	private String num;
	private String idNumber;
	private String name;
	public void setNum(String num) {
		this.num = num;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public void setProId(String proId) {
		if(proId == null) return;
		this.proId = proId;
	}
	public void setQueryProName(String queryProName) {
		this.queryProName = queryProName;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String list() throws IOException{
		HPTFuzzyQueryModel hptFuzzyQueryModel = new HPTFuzzyQueryModel();
		if(queryProName != null && !queryProName.equals("")){
			hptFuzzyQueryModel.setProName(queryProName);
		}
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(pageNum);
		hptFuzzyQueryModel.setName(name);
		hptFuzzyQueryModel.setIdNumber(idNumber);
		hptFuzzyQueryModel.setTicketNumber(num);
		String jsonState = this.hptMgtServiceContract.queryProvideTable(hptFuzzyQueryModel, pageModel);
		response().getWriter().write(jsonState);
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
		HPTFuzzyQueryModel data = Serialization.toObject(this.daYinData, HPTFuzzyQueryModel.class);
		List<HPTReceiveTableDto> dtos = this.hptMgtServiceContract.getHPTReceiveTableDtoAll(data.getProName());
		int removedInfoRowIndex = Integer.parseInt(ConfigFileUtil
				.readByKey("removedInfoRowIndex"));
		IExcelOutput excelOutput = null;
		try {
			excelOutput = ExcelFactory.getExcelOutput();
			excelOutput.writeDatas(colDatas);
			excelOutput.writeDatas(HPTReceiveTableDto.class, dtos, colAttrVals, removedInfoRowIndex);
			downloadChineseFileName = "hptLssue";
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
	private String daYinData;
	
	public void setDaoChuAttrModel(String daoChuAttrModel) {
		this.daoChuAttrModel = daoChuAttrModel;
	}
	public void setDaoChuHead(String daoChuHead) {
		this.daoChuHead = daoChuHead;
	}
	public void setDaYinData(String daYinData) {
		this.daYinData = daYinData;
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
