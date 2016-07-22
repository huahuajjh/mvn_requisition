package com.tq.requisition.presentation.dto.project;

import java.util.Date;

public class NewProMonthProcessDto {
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
	private float paidMoney;
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
	public float getPaidMoney() {
		return paidMoney;
	}
	public void setPaidMoney(float paidMoney) {
		this.paidMoney = paidMoney;
	}
	public int getYearDeadlineFile() {
		return yearDeadlineFile;
	}
	public void setYearDeadlineFile(int yearDeadlineFile) {
		this.yearDeadlineFile = yearDeadlineFile;
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
	public boolean isNewStart() {
		return newStart;
	}
	public void setNewStart(boolean newStart) {
		this.newStart = newStart;
	}
	
	
}
