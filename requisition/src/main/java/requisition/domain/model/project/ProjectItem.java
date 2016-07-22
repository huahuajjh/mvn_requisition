package com.tq.requisition.domain.model.project;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.share.Entity;

/**
 * 项目月度实体
 * 
 * @author jjh
 * @time 2015-12-18 13:00
 */
public class ProjectItem extends Entity {
	/*private fields*/
	/** 月度填报时间 */
	private Date date;
	/** 本月已腾地 */
	private float removedLandArea;
	/** 本月已拆栋数 */
	private int removedBuildings;
	/** 本月已迁户数 */
	private int rmovedHouses;
	/** 本月已拆合法面积 */
	private float removedLegalArea;
	/** 本月已拆违章面积 */
	private float removedIllegalArea;
	/** 本月已动迁人口 */
	private int movedPopulation;
	/** 本月已付赔偿款 */
	private BigDecimal paidMoney;
	/** 本年下达限期腾地决定书 */
	private int yearDeadlineFile;
	/** 本年申请法院执行 */
	private int yearCourtExecute;
	/** 本年依法实施强制腾地户数 */
	private int yearLegalRemoved;
	/** 备注 */
	private String remark;
	/** 本月新启动 */
	private boolean newStart;
	/** 項目id */
	private UUID proId;
	/** 项目启动时间 */
	private Date startDate;
	/** 是否本月完成项目 */
	private String curMonthComplete;
	
	/*getters and setters*/
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
	public int getRemovedBuildings() {
		return removedBuildings;
	}
	public void setRemovedBuildings(int removedBuildings) {
		this.removedBuildings = removedBuildings;
	}
	public int getRmovedHouses() {
		return rmovedHouses;
	}
	public void setRmovedHouses(int rmovedHouses) {
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
	public int getMovedPopulation() {
		return movedPopulation;
	}
	public void setMovedPopulation(int movedPopulation) {
		this.movedPopulation = movedPopulation;
	}
	public BigDecimal getPaidMoney() {
		return paidMoney;
	}
	public void setPaidMoney(BigDecimal paidMoney) {
		this.paidMoney = paidMoney;
	}
	public int getYearDeadlineFile() {
		return yearDeadlineFile;
	}
	public int getYearCourtExecute() {
		return yearCourtExecute;
	}
	public void setYearCourtExecute(int yearCourtExecute) {
		this.yearCourtExecute = yearCourtExecute;
	}
	public int getYearLegalRemoved() {
		return yearLegalRemoved;
	}
	public void setYearLegalRemoved(int yearLegalRemoved) {
		this.yearLegalRemoved = yearLegalRemoved;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public boolean getNewStart() {
		return newStart;
	}
	public void setNewStart(boolean newStart) {
		this.newStart = newStart;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return this.id;
	}
	public void setProId(UUID proId) {
		this.proId = proId;
	}
	public UUID getProId() {
		return this.proId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setYearDeadlineFile(int yearDeadlineFile) {
		this.yearDeadlineFile = yearDeadlineFile;
	}
	public String getCurMonthComplete() {
		return curMonthComplete;
	}
	public void setCurMonthComplete(String curMonthComplete) {
		this.curMonthComplete = curMonthComplete;
	}

	
	/*constructors*/
	public ProjectItem(){
		this.id = UUID.randomUUID();
	}
	public ProjectItem(UUID id,Date date, float removedLandArea,
			int removedBuildings, int rmovedHouses,
			float removedLegalArea, float removedIllegalArea,
			int movedPopulation, BigDecimal paidMoney, int yearDeadlineFile,
			int yearCourtExecute, int yearLegalRemoved, String remark,
			boolean newStart, UUID proId,Date startDate) {
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

	/*public methods*/
	public static ProjectItem obtain(UUID id,Date date, float removedLandArea,
			int removedBuildings, int rmovedHouses,
			float removedLegalArea, float removedIllegalArea,
			int movedPopulation, BigDecimal paidMoney, int yearDeadlineFile,
			int yearCourtExecute, int yearLegalRemoved, String remark,
			boolean newStart, UUID proId,Date startDate) {
		
		ProjectItem item = new ProjectItem();
		item.setId(UUID.randomUUID());
		item.setRemovedLandArea(removedLandArea);
		item.setRemovedBuildings(removedBuildings);
		item.setRmovedHouses(rmovedHouses);
		item.setRemovedLegalArea(removedLegalArea);
		item.setRemovedIllegalArea(removedIllegalArea);
		item.setMovedPopulation(movedPopulation);
		item.setPaidMoney(paidMoney);
		item.setYearDeadlineFile(yearDeadlineFile);
		item.setYearCourtExecute(yearCourtExecute);
		item.setYearLegalRemoved(yearLegalRemoved);
		item.setRemark(remark);
		item.setNewStart(false);
		item.setProId(proId);
		item.setStartDate(startDate);
		item.setDate(date);
		
		return item;
	}

	public void modify(ProjectItem item) {
		this.movedPopulation = item.getMovedPopulation();
		this.paidMoney = item.getPaidMoney();
		this.removedBuildings = item.getRemovedBuildings();
		this.removedIllegalArea = item.getRemovedIllegalArea();
		this.removedLandArea = item.getRemovedLandArea();
		this.removedLegalArea = item.getRemovedLegalArea();
		this.rmovedHouses = item.getRmovedHouses();
		this.yearCourtExecute = item.getYearCourtExecute();
		this.yearDeadlineFile = item.getYearDeadlineFile();
		this.yearLegalRemoved = item.getYearLegalRemoved();
		this.curMonthComplete = item.getCurMonthComplete();
		this.remark = item.getRemark();
	}
	
	/**
	 * 验证
	 */
	public void validate() {
	}
	
}
