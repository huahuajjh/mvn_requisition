package com.tq.requisition.presentation.dto.project;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * 项目月度dto
 * @author jjh
 * @time 2015-12-27 17:42
 */
public class ProItemDto {
	/*private fields*/
	/** id */
	private UUID id;
	/** 月度填报时间 */
	private Date date;
	/** 本月已腾地 */
	private float removedLandArea;
	/** 本月已拆栋数 */
	private Integer removedBuildings;
	/** 本月已迁户数 */
	private Integer rmovedHouses=0;
	/** 本月已拆合法面积 */
	private float removedLegalArea;
	/** 本月已拆违章面积 */
	private float removedIllegalArea;
	/** 本月已动迁人口 */
	private Integer movedPopulation;
	/** 本月已付赔偿款 */
	private BigDecimal paidMoney;
	/** 本年下达限期腾地决定书 */
	private Integer yearDeadlineFile;
	/** 本年申请法院执行 */
	private Integer yearCourtExecute;
	/** 本年依法实施强制腾地户数 */
	private Integer yearLegalRemoved;
	/** 备注 */
	private String remark;
	/** 本月新启动 */
	private boolean newStart;
	/** 项目id */
	private UUID proId;
	/** 项目启动时间 */
	private Date startDate;
	/** 是否本月完成项目 */
	private String curMonthComplete;

	/*public methods*/
	public static ProItemDto obtain(UUID id, Date date, float removedLandArea,
			Integer removedBuildings, Integer rmovedHouses,
			float removedLegalArea, float removedIllegalArea,
			Integer movedPopulation, BigDecimal paidMoney, Integer yearDeadlineFile,
			Integer yearCourtExecute, Integer yearLegalRemoved, String remark,
			boolean newStart, UUID proId,Date startDate) {
		
		return new ProItemDto(id, date, removedLandArea, removedBuildings, rmovedHouses, removedLegalArea, removedIllegalArea, movedPopulation, paidMoney, yearDeadlineFile, yearCourtExecute, yearLegalRemoved, remark, newStart, proId,startDate);
	}

	/*getters and setters*/
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getRemovedLandArea() {
		return removedLandArea;
	}

	public void setRemovedLandArea(float removedLandArea) {
		this.removedLandArea = removedLandArea;
	}

	public Integer getRemovedBuildings() {
		return removedBuildings;
	}

	public void setRemovedBuildings(Integer removedBuildings) {
		this.removedBuildings = removedBuildings;
	}

	public Integer getRmovedHouses() {
		return rmovedHouses;
	}

	public void setRmovedHouses(Integer rmovedHouses) {
		this.rmovedHouses = rmovedHouses;
	}

	public float getRemovedLegalArea() {
		return removedLegalArea;
	}

	public void setRemovedLegalArea(float removedLegalArea) {
		this.removedLegalArea = removedLegalArea;
	}

	public float getRemovedIllegalArea() {
		return removedIllegalArea;
	}

	public void setRemovedIllegalArea(float removedIllegalArea) {
		this.removedIllegalArea = removedIllegalArea;
	}

	public Integer getMovedPopulation() {
		return movedPopulation;
	}

	public void setMovedPopulation(Integer movedPopulation) {
		this.movedPopulation = movedPopulation;
	}

	public BigDecimal getPaidMoney() {
		return paidMoney;
	}

	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}

	public Integer getYearDeadlineFile() {
		return yearDeadlineFile;
	}

	public void setYearDeadlineFile(Integer yearDeadlineFile) {
		this.yearDeadlineFile = yearDeadlineFile;
	}

	public Integer getYearCourtExecute() {
		return yearCourtExecute;
	}

	public void setYearCourtExecute(Integer yearCourtExecute) {
		this.yearCourtExecute = yearCourtExecute;
	}

	public Integer getYearLegalRemoved() {
		return yearLegalRemoved;
	}

	public void setYearLegalRemoved(Integer yearLegalRemoved) {
		this.yearLegalRemoved = yearLegalRemoved;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public boolean isNewStart() {
		return newStart;
	}

	public void setNewStart(boolean newStart) {
		this.newStart = newStart;
	}

	public UUID getProId() {
		return proId;
	}

	public void setProId(UUID proId) {
		this.proId = proId;
	}
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getCurMonthComplete() {
		return curMonthComplete;
	}

	public void setCurMonthComplete(String curMonthComplete) {
		this.curMonthComplete = curMonthComplete;
	}

	/*constructors*/
	public ProItemDto(){}
	public ProItemDto(UUID id, Date date, float removedLandArea,
			Integer removedBuildings, Integer rmovedHouses,
			float removedLegalArea, float removedIllegalArea,
			Integer movedPopulation, BigDecimal paidMoney, Integer yearDeadlineFile,
			Integer yearCourtExecute, Integer yearLegalRemoved, String remark,
			boolean newStart, UUID proId,Date startDate) {
		super();
		this.id = id;
		this.date = date;
		this.removedLandArea = removedLandArea;
		this.removedBuildings = removedBuildings;
		this.rmovedHouses = rmovedHouses;
		this.removedLegalArea = removedLegalArea;
		this.removedIllegalArea = removedIllegalArea;
		this.movedPopulation = movedPopulation;
		this.paidMoney = paidMoney;
		this.yearDeadlineFile = yearDeadlineFile;
		this.yearCourtExecute = yearCourtExecute;
		this.yearLegalRemoved = yearLegalRemoved;
		this.remark = remark;
		this.newStart = newStart;
		this.proId = proId;
		this.startDate = startDate;
	}
	
}
