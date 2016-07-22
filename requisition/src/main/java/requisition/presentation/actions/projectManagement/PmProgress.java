package com.tq.requisition.presentation.actions.projectManagement;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.excel.util.ExcelFactory;
import com.excel.util.intefaces.IExcelOutput;
import com.excel.util.model.ColAttrVal;
import com.excel.util.model.ExcelColData;
import com.tq.requisition.infrastructure.utils.ConfigFileUtil;
import com.tq.requisition.infrastructure.utils.ProExcelHead;
import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.project.AnnouncementDto;
import com.tq.requisition.presentation.dto.project.ProExportCondition;
import com.tq.requisition.presentation.dto.project.ProImportAndExportDto;
import com.tq.requisition.presentation.dto.project.ProItemDto;
import com.tq.requisition.presentation.dto.project.ProQueryModel;
import com.tq.requisition.presentation.dto.project.ProTypeDto;
import com.tq.requisition.presentation.dto.share.AddressDto;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.serviceContract.proMgt.IProMgtServiceContract;
import com.tq.requisition.presentation.serviceContract.share.IAddressServiceContract;

@SuppressWarnings("serial")
public class PmProgress extends BaseAction {

	public static final String[] ANNVALS = new String[] { "一公告", "二公告", "三公告" };

	// 输出文件流
	private InputStream outputStream;
	private String downloadChineseFileName;

	public PmProgress() {
		this.addressServiceContract = getService("addressService",
				IAddressServiceContract.class);
		this.proMgtServiceContract = getService("proMgtServiceContract",
				IProMgtServiceContract.class);
	}

	private IProMgtServiceContract proMgtServiceContract;
	private IAddressServiceContract addressServiceContract;

	/** 前端交互数据 */
	// ---模糊查询列表
	private int pageNum = 30;
	private int pageIndex = 1;
	private int typeId = 0;
	private int annouceQueue = 0;
	private String address;
	// --------
	private String val;// 模糊查询项目名称
	// ---------
	private String proId = "";// 项目ID

	// 录入月报信息
	private String dataJson;
	// -----------------------
	// 公告Model
	private String number;
	private String date;
	private String fdocVal;
	private int sequence;
	private String id;

	// 打印参数
	private String daYinData;
	private String daYinIds;
	private String daoChuAttrModel;
	private String daoChuHead;

	/** 页面对象 */
	private List<AddressDto> addressDtos;
	private List<ProTypeDto> proTypeDtos;

	@Override
	public String execute() throws Exception {
		this.addressDtos = this.addressServiceContract.getAddress();
		this.proTypeDtos = this.proMgtServiceContract.getProType();
		return SUCCESS;
	}

	// 获取列表
	public String list() throws IOException {
		ProQueryModel queryModel = new ProQueryModel();
		queryModel.setAnnouceQueue(annouceQueue);
		queryModel.setCreateUId(userId().toString());
		queryModel.setTypeId(typeId);
		queryModel.setAddress(address);
		queryModel.setProName(val);
		PageModel pageModel = new PageModel();
		pageModel.setPageIndex(pageIndex);
		pageModel.setPageSize(pageNum);
		String jsonDataString = this.proMgtServiceContract.getProListFuzzy(
				queryModel, pageModel);
		response().getWriter().write(jsonDataString);
		return null;
	}

	public String get() throws IOException {
		String strVal = this.proMgtServiceContract.getProById(UUID
				.fromString(proId));
		response().getWriter().write(strVal);
		return null;
	}

	// 获取名字列表
	public String names() throws IOException {
		String names = this.proMgtServiceContract.getProNameListFuzzy(val);
		response().getWriter().write(names);
		return null;
	}

	// 获取月报详细信息
	public String info() throws IOException {
		String info = this.proMgtServiceContract.getProItemsByProId(UUID
				.fromString(proId));
		response().getWriter().write(info);
		return null;
	}

	// 录入月报
	public String inputMouth() throws ParseException, IOException {
		ProItemDto dto = Serialization.toObject(dataJson, ProItemDto.class);
		String jsonData = this.proMgtServiceContract.addProItem(dto,
				dto.getProId());
		response().getWriter().write(jsonData);
		return null;
	}

	// 修改月报
	public String editMouth() throws IOException {
		ProItemDto dto = Serialization.toObject(dataJson, ProItemDto.class);
		String stateJson = this.proMgtServiceContract.editProItem(dto);
		response().getWriter().write(stateJson);
		return null;
	}

	// 获取公告列表
	public String getAnnouncement() throws IOException {
		String infos = this.proMgtServiceContract.getAnnounce(UUID
				.fromString(proId));
		response().getWriter().write(infos);
		return null;
	}

	// 新增公共
	public String addAnnouncement() throws ParseException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = dateFormat.parse(this.date);
		AnnouncementDto dto = new AnnouncementDto();
		dto.setDate(date);
		dto.setDocPath(fdocVal);
		dto.setId(UUID.randomUUID());
		dto.setName(ANNVALS[sequence-1]);
		dto.setNumber(number);
		dto.setProId(UUID.fromString(proId));
		dto.setSequence(sequence);
		String stateJson = this.proMgtServiceContract.addAnnouncement(dto);
		response().getWriter().write(stateJson);
		return null;
	}

	// 修改公告
	public String editAnnouncement() throws ParseException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = dateFormat.parse(this.date);
		AnnouncementDto dto = new AnnouncementDto();
		dto.setDate(date);
		dto.setDocPath(fdocVal);
		dto.setId(UUID.randomUUID());
		dto.setName(ANNVALS[sequence-1]);
		dto.setNumber(number);
		dto.setProId(UUID.fromString(proId));
		dto.setSequence(sequence);
		dto.setId(UUID.fromString(id));
		String stateJson = this.proMgtServiceContract.editAnnouncement(dto);
		response().getWriter().write(stateJson);
		return null;
	}

	public String daYin() throws ParseException, IOException {
		String stateJsonString = this.proMgtServiceContract
				.getPro4Print(daYinIds);
		response().getWriter().write(stateJsonString);
		return null;
	}

	public String daoChu() throws ParseException, IOException {
		int[] ids = Serialization.toObject(daoChuAttrModel, int[].class);
		List<ExcelColData> colDatas = ProExcelHead.getExcelColDatas(ids);
		List<ColAttrVal> colAttrVals = ProExcelHead.getkeys(ids);
		ProExportCondition data = Serialization.toObject(this.daYinData,
				ProExportCondition.class);
		List<ProImportAndExportDto> dtos = this.proMgtServiceContract
				.exportProByDate(data);
		for (int i = 0; dtos != null && i < dtos.size(); i++) {
			dtos.get(i).setNum("=row()");
		}
		int proTemplateRowIndex = Integer.parseInt(ConfigFileUtil
				.readByKey("proTemplateRowIndex"));
		InputStream inputStream = null;
		IExcelOutput excelOutput = null;
		try {
			excelOutput = ExcelFactory.getExcelOutput();
			excelOutput.setColVal(0, 0, "长沙县??年?月省、市重点工程项目拆迁补偿进度情况统计表", 0, 33);
			excelOutput.setColVal(1, 0, "填报时间：", 0, 23);
			excelOutput.setColVal(1, 24, "单位：亩、栋、平方米、人、万元", 0, 8);
			excelOutput.writeDatas(colDatas);
			excelOutput.writeDatas(ProImportAndExportDto.class, dtos,
					colAttrVals, proTemplateRowIndex);
			downloadChineseFileName = "project";
			this.outputStream = excelOutput.getInputStream();
			return SUCCESS;
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (excelOutput != null) {
				excelOutput.close();
			}
		}
		return null;
	}

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

	public void setVal(String val) {
		this.val = val;
	}

	public void setProId(String proId) {
		if (proId == "")
			return;
		this.proId = proId;
	}

	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}

	public List<ProTypeDto> getProTypeDtos() {
		return proTypeDtos;
	}

	public void setFdocVal(String fdocVal) {
		this.fdocVal = fdocVal;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDaYinIds(String daYinIds) {
		this.daYinIds = daYinIds;
	}

	public InputStream getOutputStream() {
		return outputStream;
	}

	public String getDownloadChineseFileName() {
		return downloadChineseFileName;
	}

	public void setDaoChuAttrModel(String daoChuAttrModel) {
		this.daoChuAttrModel = daoChuAttrModel;
	}

	public void setDaoChuHead(String daoChuHead) {
		this.daoChuHead = daoChuHead;
	}

	public void setDaYinData(String daYinData) {
		this.daYinData = daYinData;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
