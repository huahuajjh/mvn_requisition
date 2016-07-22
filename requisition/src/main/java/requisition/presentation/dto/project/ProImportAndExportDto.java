package com.tq.requisition.presentation.dto.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.excel.util.annotation.InputColAnnotation;
import com.excel.util.annotation.OutputColAnnotation;
import com.tq.requisition.domain.model.project.Announcement;
import com.tq.requisition.domain.model.project.Project;
import com.tq.requisition.domain.model.project.ProjectItem;
import com.tq.requisition.domain.model.project.ProjectType;

/**
 * 导入导出项目dto
 * 
 * @author jjh
 * @time 2016-01-15 19::49
 * 
 */
public class ProImportAndExportDto {
	/**序号*/
	@OutputColAnnotation(colCoord = 0,formula="(row() - 6)")
	private String num;
	/** 项目名称 */
	@InputColAnnotation(colCoord = 1, isAbandonRowspanData = true)
	@OutputColAnnotation(colCoord = 1)
	private String proName;
	/** 项目审批号 */
	@InputColAnnotation(colCoord = 2,isAbandonRowspanData = true)
	@OutputColAnnotation(colCoord = 2)
	private String approvalNumber;
	/** 项目类型 */
	@InputColAnnotation(colCoord = 3)
	@OutputColAnnotation(colCoord = 3)
	private String proCategory;
	/** 项目类型名称-基础设施 */
	@InputColAnnotation(colCoord = 4)
	@OutputColAnnotation(colCoord = 4)
	private String proTypeStrInfra;
	/** 项目类型名称-其他 */
	@InputColAnnotation(colCoord = 5)
	@OutputColAnnotation(colCoord = 5)
	private String proTypeStrOther;
	/** 本月新启动项目 */
	@InputColAnnotation(colCoord = 6)
	@OutputColAnnotation(colCoord = 6)
	private String newStart;
	/** 是否为本月完成结算项目 */
	@InputColAnnotation(colCoord = 7)
	@OutputColAnnotation(colCoord = 7)
	private String curMonthComplete;
	/** 项目全地址 */
	@InputColAnnotation(colCoord = 8)
	@OutputColAnnotation(colCoord = 8)
	private String totalAddress;
	
	/** 公告1 */
	@OutputColAnnotation(colCoord = 9)
	private Date announce1;
	private String announce1Number;
	/** 公告2 */
	@OutputColAnnotation(colCoord = 10)
	private Date announce2;
	private String announce2Number;
	/** 公告3 */
	@OutputColAnnotation(colCoord = 11)
	private Date announce3;
	private String announce3Number;

	/** 征地面积 */
	@InputColAnnotation(colCoord = 9, required = true, converErrorMsg = "类型错误，[征地面积]是小数类型", requiredErrorMsg = "[征地面积]不可为空")
	@OutputColAnnotation(colCoord = 12)
	private float requisitionArea;
	/** 累计已腾地 */
	@InputColAnnotation(colCoord = 10, converErrorMsg = "类型错误，[累计已腾地]是小数类型")
	@OutputColAnnotation(colCoord = 13)
	private float requisitionLandAreaTotal;
	/** 本月已腾地 */
	@InputColAnnotation(colCoord = 11, converErrorMsg = "类型错误，[本月已腾地]是小数类型")
	@OutputColAnnotation(colCoord = 14)
	private float removedLandArea;
	/** 本年已腾地 */
	@InputColAnnotation(colCoord = 12, converErrorMsg = "类型错误，[本年已腾地]是小数类型")
	@OutputColAnnotation(colCoord = 15)
	private float requisitionLandAreaYear;
	
	/** 应拆栋数 */
	@InputColAnnotation(colCoord = 13, required = true, converErrorMsg = "类型错误，[应拆栋数]是整数类型", requiredErrorMsg = "[应拆栋数]不可为空")
	@OutputColAnnotation(colCoord = 16)
	private int shouldRemoveBuildings;
	/** 累计已拆合法栋数 */
	@InputColAnnotation(colCoord = 14, converErrorMsg = "类型错误，[累计已拆合法栋数]是整数类型")
	@OutputColAnnotation(colCoord = 17)
	private int removedBuildingsLegalTotal;
	/** 本月已拆栋数 */
	@InputColAnnotation(colCoord = 15, converErrorMsg = "类型错误，[本月已拆栋数]是整数类型")
	@OutputColAnnotation(colCoord = 18)
	private int removedBuildings;
	/** 本年已拆合法栋数 */
	@InputColAnnotation(colCoord = 16, converErrorMsg = "类型错误，[本年已拆合法栋数]是整数类型")
	@OutputColAnnotation(colCoord = 19)
	private int removedBuildingsLegalYear;
	
	/** 应拆合法总面积 */
	@InputColAnnotation(colCoord = 17, required = true, converErrorMsg = "类型错误，[应拆合法总面积]是小数类型", requiredErrorMsg = "[应拆合法总面积]不可为空")
	@OutputColAnnotation(colCoord = 20)
	private float shouldRemoveLegalArea;
	/** 累计已拆合法面积 */
	@InputColAnnotation(colCoord = 18, converErrorMsg = "类型错误，[累计已拆合法面积]是小数类型")
	@OutputColAnnotation(colCoord = 21)
	private float removedAreaLegalTotal;
	/** 本月已拆合法面积 */
	@InputColAnnotation(colCoord = 19, converErrorMsg = "类型错误，[本月已拆合法面积]是小数类型")
	@OutputColAnnotation(colCoord = 22)
	private float removedLegalArea;
	/** 本年已拆合法面积 */
	@InputColAnnotation(colCoord = 20, converErrorMsg = "类型错误，[本年已拆合法面积]是小数类型")
	@OutputColAnnotation(colCoord = 23)
	private float removedAreaLegalYear;
	
	/** 应动迁人口 */
	@InputColAnnotation(colCoord = 21, required = true, converErrorMsg = "类型错误，[应动迁人口]是整数类型", requiredErrorMsg = "[应动迁人口]不可为空")
	@OutputColAnnotation(colCoord = 24)
	private int shouldMovePopulation;
	/** 累计已迁人口 */
	@InputColAnnotation(colCoord = 22, converErrorMsg = "类型错误，[累计已迁人口]是整数类型")
	@OutputColAnnotation(colCoord = 25)
	private int removedPopulationTotal;
	/** 本月已动迁人口 */
	@InputColAnnotation(colCoord = 23, converErrorMsg = "类型错误，[本月已动迁人口]整数类型")
	@OutputColAnnotation(colCoord = 26)
	private int movedPopulation;
	/** 本年已迁人口 */
	@InputColAnnotation(colCoord = 24, converErrorMsg = "类型错误，[本年已迁人口]是整数类型")
	@OutputColAnnotation(colCoord = 27)
	private int removedPopulationYear;
	
	/** 本年下达限期腾地决定书 */
	@InputColAnnotation(colCoord = 25, converErrorMsg = "类型错误，[本年下达限期腾地决定书]整数类型")
	@OutputColAnnotation(colCoord = 28)
	private int yearDeadlineFile;
	/** 本年申请法院执行 */
	@InputColAnnotation(colCoord = 26, converErrorMsg = "类型错误，[本年申请法院执行]整数类型")
	@OutputColAnnotation(colCoord = 29)
	private int yearCourtExecute;
	/** 本年依法实施强制腾地户数 */
	@InputColAnnotation(colCoord = 27, converErrorMsg = "类型错误，[本年依法实施强制腾地户数]整数类型")
	@OutputColAnnotation(colCoord = 30)
	private int yearLegalRemoved;
	/** 是否“六前项目” */
	@InputColAnnotation(colCoord = 28)
	@OutputColAnnotation(colCoord = 31)
	private String sixForheadPro;
	/** 备注 */
	@InputColAnnotation(colCoord = 29)
	@OutputColAnnotation(colCoord = 32)
	private String remark;
	
	
	/** 应拆户数 */
	private int shouldRemoveHouses;
	/** 累计已拆合法户数 */
	private int removedHousesLegalTotal;
	/** 本年已拆合法户数 */
	private int removedHousesLegalYear;
	/** 应拆违章总面积 */
	private float shouldRemoveIllegalArea;
	/** 项目应付补偿款 */
	private BigDecimal shouldPayMoney;
	/** 项目累计已付补偿款 */
	private BigDecimal totalPayMoney = new BigDecimal(0);
	/** 项目启动日期 */
	private Date startDate;
	/** 累计已拆违章面积 */
	private float removedAreaIllegalTotal;
	/** 本年已拆违章面积 */
	private float removedAreaIllegalYear;
	/** 本月已迁户数 */
	private int rmovedHouses;
	/** 本月已拆违章面积 */
	private float removedIllegalArea;
	/** 本月已付赔偿款 */
	private BigDecimal paidMoney = new BigDecimal(0);
	/**项目分类id*/
	private String categoryId;
	
	/**项目分类str*/
	@InputColAnnotation(colCoord = 5)
	@OutputColAnnotation(colCoord = 5)
	private String categoryStr;
	
	/** 月度填报时间 */
	private Date date;
	
	/**创建人的标识*/
	private String createUid;
	/**创建时间*/
	private Date createDate;

	/* public methods */
	public Project toProject() {
		// 项目a
		Project model = new Project();
		model.setProName(proName);
		model.setApprovalNumber(approvalNumber);
		model.setRequisitionArea(requisitionArea);
		model.setShouldRemoveBuildings(shouldRemoveBuildings);
		model.setShouldRemoveHouses(shouldRemoveHouses);
		model.setShouldRemoveLegalArea(shouldRemoveLegalArea);
		model.setShouldRemoveIllegalArea(shouldRemoveIllegalArea);
		model.setShouldMovePopulation(shouldMovePopulation);
		model.setShouldPayMoney(shouldPayMoney);
		model.setTotalPayMoney(totalPayMoney);
		if(curMonthComplete != null && (curMonthComplete.trim().equals("√") || curMonthComplete.trim().equals("是"))){
			model.setStartDate(date);
		}
		model.setProType(getProType());
		// model.setProTypeStr(proTypeStr);
		model.setTotalAddress(totalAddress);
		model.setSequence(0);
		// model.setSequenceStr(dto.getAnnounceName());
		//List<Announcement> anns = getAnnouncements();
		//model.setAnnouncements(anns);
		//model.setSequence(anns.size());
		model.setRequisitionLandAreaTotal(requisitionLandAreaTotal);
		model.setRequisitionLandAreaYear(requisitionLandAreaYear);
		model.setRemovedBuildingsLegalTotal(removedBuildingsLegalTotal);
		model.setRemovedBuildingsLegalYear(removedBuildingsLegalYear);
		model.setRemovedHousesLegalTotal(removedHousesLegalTotal);
		model.setRemovedHousesLegalYear(removedHousesLegalYear);
		model.setRemovedAreaLegalTotal(removedAreaLegalTotal);
		model.setRemovedAreaLegalYear(removedAreaLegalYear);
		model.setRemovedAreaIllegalTotal(removedAreaIllegalTotal);
		model.setRemovedAreaIllegalYear(removedAreaIllegalYear);
		model.setRemovedPopulationTotal(removedPopulationTotal);
		model.setRemovedPopulationYear(removedPopulationYear);
		
		model.setCurMonthComplete(curMonthComplete);
		model.setCategoryStr(proCategory);
		if(sixForheadPro != null && (sixForheadPro.trim().equals("√") || sixForheadPro.trim().equals("是"))){
			model.setSixForward("是");
		} else {
			model.setSixForward(sixForheadPro);
		}
		
		model.setCreateDate(createDate);
		model.setCreateUid(createUid);

		// 项目月度		
		ProjectItem item = new ProjectItem();
		item.setId(UUID.randomUUID());
		item.setDate(date);
		item.setRemovedLandArea(removedLandArea);
		item.setRemovedBuildings(removedBuildings);
		item.setRmovedHouses(rmovedHouses);
		item.setRemovedLegalArea(removedLegalArea);
		item.setRemovedIllegalArea(removedIllegalArea);
		item.setMovedPopulation(movedPopulation);
		item.setYearDeadlineFile(yearDeadlineFile);
		item.setYearCourtExecute(yearCourtExecute);
		item.setYearLegalRemoved(yearLegalRemoved);
		item.setRemark(remark);
		item.setNewStart(false);
		item.setProId(model.getId());
		item.setStartDate(startDate);
		if(curMonthComplete != null && (curMonthComplete.trim().equals("√") || curMonthComplete.trim().equals("是"))){
			item.setCurMonthComplete("是");
		} else {
			item.setCurMonthComplete(curMonthComplete);
		}
		
		model.getItems().add(item);
		return model;
	}

	/**
	 * 获取项目类型int值
	 * 
	 * @return int 返回一个int值，该值表明一个项目类型的int值
	 */
	private int getProType() {
		if (proTypeStrInfra != null && !(proTypeStrInfra.trim().equals(""))) {
			return ProjectType.INFRASTRUCTURE.toValue();
		}
		return ProjectType.OTHER.toValue();
	}

	/**
	 * 获取公告序列
	 * 
	 * @return int 返回一个int值，该值表明当前项目处于第几公告
	 */
//	private int getSequence() {
//		if (announce1 != null) {
//			return 1;
//		}
//		if (announce2 != null) {
//			return 2;
//		}
//		return 3;
//	}

//	private List<Announcement> getAnnouncements() {
//		List<Announcement> announcements = new ArrayList<>();
//		if (announce1 != null) {
//			Announcement announcement = new Announcement();
//			announcement.setDate(announce1);
//			announcement.setSequence(1);
//			announcements.add(announcement);
//		}
//		if (announce2 != null) {
//			Announcement announcement = new Announcement();
//			announcement.setDate(announce2);
//			announcement.setSequence(2);
//			announcements.add(announcement);
//		}
//
//		if(announce3!=null)
//		{
//			Announcement announcement = new Announcement();
//			announcement.setDate(announce3);
//			announcement.setSequence(3);
//			announcements.add(announcement);
//		}
//		return announcements;
//	}

	/**
	 * 根据项目集合转换成报表model
	 * 
	 * @param pros
	 *            项目集合
	 * @return List<ProImportAndExportDto> 项目导入导出model
	 */
	public static List<ProImportAndExportDto> obtainByProList(List<Project> pros) {
		List<ProImportAndExportDto> list = new ArrayList<ProImportAndExportDto>();
		for (Project project : pros) {
			ProImportAndExportDto dto = new ProImportAndExportDto();
			dto.proName = project.getProName();
			dto.approvalNumber = project.getApprovalNumber();
			dto.requisitionArea = project.getRequisitionArea();
			dto.shouldRemoveBuildings = project.getShouldRemoveBuildings();
			dto.shouldRemoveHouses = project.getShouldRemoveHouses();
			dto.shouldRemoveLegalArea = project.getShouldRemoveLegalArea();
			dto.shouldRemoveIllegalArea = project.getShouldRemoveIllegalArea();
			dto.shouldMovePopulation = project.getShouldMovePopulation();
			dto.shouldPayMoney = project.getShouldPayMoney();
			dto.totalPayMoney = project.getTotalPayMoney();
			dto.startDate = project.getStartDate();
			dto.categoryId = project.getCategoryId();
			dto.categoryStr = project.getCategoryStr();
			setProType(dto, project.getProTypeStr());
			dto.totalAddress = project.getTotalAddress();
			setAnnounce(dto, project);
			dto.requisitionLandAreaTotal = project.getRequisitionLandAreaTotal();
			dto.requisitionLandAreaYear = project.getRequisitionLandAreaYear();
			dto.removedBuildingsLegalTotal = project.getRemovedBuildingsLegalTotal();
			dto.removedBuildingsLegalYear = project.getRemovedBuildingsLegalYear();
			dto.removedHousesLegalTotal = project.getRemovedHousesLegalTotal();
			dto.removedHousesLegalYear = project.getRemovedHousesLegalYear();
			dto.removedAreaLegalTotal = project.getRemovedAreaLegalTotal();
			dto.removedAreaLegalYear = project.getRemovedAreaLegalYear();
			dto.removedAreaIllegalTotal = project.getRemovedAreaIllegalTotal();
			dto.removedAreaIllegalYear = project.getRemovedAreaIllegalYear();
			dto.removedPopulationTotal = project.getRemovedPopulationTotal();
			dto.removedPopulationYear = project.getRemovedPopulationYear();
			dto.sixForheadPro = project.getSixForward();
			List<ProjectItem> items = project.getItems();
			if (null != items && items.size() != 0) {
				dto.date = items.get(0).getDate();
				dto.removedLandArea = items.get(0).getRemovedLandArea();
				dto.removedBuildings = items.get(0).getRemovedBuildings();
				dto.rmovedHouses = items.get(0).getRmovedHouses();
				dto.removedLegalArea = items.get(0).getRemovedLegalArea();
				dto.removedIllegalArea = items.get(0).getRemovedIllegalArea();
				dto.movedPopulation = items.get(0).getMovedPopulation();
				dto.paidMoney = items.get(0).getPaidMoney();
				dto.yearDeadlineFile = items.get(0).getYearDeadlineFile();
				dto.yearCourtExecute = items.get(0).getYearCourtExecute();
				dto.yearLegalRemoved = items.get(0).getYearLegalRemoved();
				dto.remark = items.get(0).getRemark();
				dto.curMonthComplete = items.get(0).getCurMonthComplete();
			}
			list.add(dto);
		}
		return list;
	}

	private static void setProType(ProImportAndExportDto dto, String typeName) {
		if (typeName.trim().equals("基础设施")) {
			dto.proTypeStrInfra = "√";
			return;
		}
		dto.proTypeStrOther = "√";
	}

	private static void setAnnounce(ProImportAndExportDto dto, Project pro) {
		for (Announcement announcement : pro.getAnnouncements()) {
			switch (announcement.getSequence()) {
			case 1:
				dto.announce1 = announcement.getDate();
				dto.announce1Number = announcement.getNumber();
				break;

			case 2:
				dto.announce2 = announcement.getDate();
				dto.announce2Number = announcement.getNumber();
				break;

			default:
				dto.announce3 = announcement.getDate();
				dto.announce3Number = announcement.getNumber();
				break;
			}
		}
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getApprovalNumber() {
		return approvalNumber;
	}

	public void setApprovalNumber(String approvalNumber) {
		this.approvalNumber = approvalNumber;
	}

	public String getProTypeStrInfra() {
		return proTypeStrInfra;
	}

	public void setProTypeStrInfra(String proTypeStrInfra) {
		this.proTypeStrInfra = proTypeStrInfra;
	}

	public String getProTypeStrOther() {
		return proTypeStrOther;
	}

	public void setProTypeStrOther(String proTypeStrOther) {
		this.proTypeStrOther = proTypeStrOther;
	}

	public String getNewStart() {
		return newStart;
	}

	public void setNewStart(String newStart) {
		this.newStart = newStart;
	}

	public String getCurMonthComplete() {
		return curMonthComplete;
	}

	public void setCurMonthComplete(String curMonthComplete) {
		this.curMonthComplete = curMonthComplete;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
