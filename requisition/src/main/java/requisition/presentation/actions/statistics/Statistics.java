package com.tq.requisition.presentation.actions.statistics;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.excel.util.ExcelFactory;
import com.excel.util.intefaces.IExcelOutput;
import com.excel.util.model.ColAttrVal;
import com.excel.util.model.ExcelColData;
import com.tq.requisition.domain.model.removedInfo.RemovedInfo;
import com.tq.requisition.infrastructure.utils.ConfigFileUtil;
import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.removedinfo.RemovedInfoQueryModel;
import com.tq.requisition.presentation.dto.statistics.StatisticsExportDto;

@SuppressWarnings("serial")
public class Statistics extends BaseAction {

	@Override
	public String execute() throws Exception {
		return SUCCESS;
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
		StatisticsExportDto[] dtosArr = Serialization.toObject(daoChuData, StatisticsExportDto[].class);
		List<StatisticsExportDto> dtos = new ArrayList<>();
		for (StatisticsExportDto dto : dtosArr) {
			dtos.add(dto);
		}
		IExcelOutput excelOutput = null;
		try {
			excelOutput = ExcelFactory.getExcelOutput();
			excelOutput.writeDatas(colDatas);
			excelOutput.writeDatas(StatisticsExportDto.class, dtos, colAttrVals, 3);
			downloadChineseFileName = "statistics";
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
	private String daoChuData;
	public void setDaoChuAttrModel(String daoChuAttrModel) {
		this.daoChuAttrModel = daoChuAttrModel;
	}
	public void setDaoChuHead(String daoChuHead) {
		this.daoChuHead = daoChuHead;
	}
	public void setDaoChuData(String daoChuData) {
		this.daoChuData = daoChuData;
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
