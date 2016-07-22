package com.tq.requisition.presentation.dto.statistics;

public class StatisticsExportDto {
	/**项目名称*/
	private String proName;
	
	//合计
	/**户数*/
	private int totalFmlCount;
	/**人数*/
	private int totalFmlItems;
	/**金额*/
	private float totalAmount;
	
	//已领凭证
	/**户数*/
	private int rcdFmlCount;
	/**人数*/
	private int rcdFmlItems;
	/**金额*/
	private float rcdAmount;
	
	//未领凭证	
	/**户数*/
	private int nrcdFmlCount;
	/**人数*/
	private int nrcdFmlItems;
	/**金额*/
	private float nrcdAmount;
	
	/**国土局出资单位名称*/
	private String moneyUnit;
	/**其他出资单位名称*/
	private String otherMoneyUnit;
	
	//国土局出资
	private float landBureauAmount;
	//非国土局出资
	private float nLandBureauAmount;
	
	//国土局出资信息
	private String landBureauAmountMsg;
	private String nLandBureauAmountMsg;
	
	//独生子女数
	private int onlyChildCount;
	//半边户人数
	private int halfCount;
	//个人补贴标准
	private float personalSubsidyStd;
	//非国土局出资计算标准
	private int nonLrbStd;
	//人口总数
	private int totalPopu;
	
	public String getProName() {
		return proName;
	}
	public int getTotalFmlCount() {
		return totalFmlCount;
	}
	public int getTotalFmlItems() {
		return totalFmlItems;
	}
	public float getTotalAmount() {
		return totalAmount;
	}
	public int getRcdFmlCount() {
		return rcdFmlCount;
	}
	public int getRcdFmlItems() {
		return rcdFmlItems;
	}
	public float getRcdAmount() {
		return rcdAmount;
	}
	public int getNrcdFmlCount() {
		return nrcdFmlCount;
	}
	public int getNrcdFmlItems() {
		return nrcdFmlItems;
	}
	public float getNrcdAmount() {
		return nrcdAmount;
	}
	public float getLandBureauAmount() {
		return landBureauAmount;
	}
	public float getnLandBureauAmount() {
		return nLandBureauAmount;
	}
	public int getOnlyChildCount() {
		return onlyChildCount;
	}
	public int getHalfCount() {
		return halfCount;
	}
	public float getPersonalSubsidyStd() {
		return personalSubsidyStd;
	}
	public int getNonLrbStd() {
		return nonLrbStd;
	}
	public int getTotalPopu() {
		return totalPopu;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public void setTotalFmlCount(int totalFmlCount) {
		this.totalFmlCount = totalFmlCount;
	}
	public void setTotalFmlItems(int totalFmlItems) {
		this.totalFmlItems = totalFmlItems;
	}
	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}
	public void setRcdFmlCount(int rcdFmlCount) {
		this.rcdFmlCount = rcdFmlCount;
	}
	public void setRcdFmlItems(int rcdFmlItems) {
		this.rcdFmlItems = rcdFmlItems;
	}
	public void setRcdAmount(float rcdAmount) {
		this.rcdAmount = rcdAmount;
	}
	public void setNrcdFmlCount(int nrcdFmlCount) {
		this.nrcdFmlCount = nrcdFmlCount;
	}
	public void setNrcdFmlItems(int nrcdFmlItems) {
		this.nrcdFmlItems = nrcdFmlItems;
	}
	public void setNrcdAmount(float nrcdAmount) {
		this.nrcdAmount = nrcdAmount;
	}
	public void setLandBureauAmount(float landBureauAmount) {
		this.landBureauAmount = landBureauAmount;
	}
	public void setnLandBureauAmount(float nLandBureauAmount) {
		this.nLandBureauAmount = nLandBureauAmount;
	}
	public void setOnlyChildCount(int onlyChildCount) {
		this.onlyChildCount = onlyChildCount;
	}
	public void setHalfCount(int halfCount) {
		this.halfCount = halfCount;
	}
	public void setPersonalSubsidyStd(float personalSubsidyStd) {
		this.personalSubsidyStd = personalSubsidyStd;
	}
	public void setNonLrbStd(int nonLrbStd) {
		this.nonLrbStd = nonLrbStd;
	}
	public void setTotalPopu(int totalPopu) {
		this.totalPopu = totalPopu;
	}
	public String getLandBureauAmountMsg() {
		return landBureauAmountMsg;
	}
	public String getnLandBureauAmountMsg() {
		return nLandBureauAmountMsg;
	}
	public void setLandBureauAmountMsg(String landBureauAmountMsg) {
		this.landBureauAmountMsg = landBureauAmountMsg;
	}
	public void setnLandBureauAmountMsg(String nLandBureauAmountMsg) {
		this.nLandBureauAmountMsg = nLandBureauAmountMsg;
	}
	public String getMoneyUnit() {
		return moneyUnit;
	}
	public String getOtherMoneyUnit() {
		return otherMoneyUnit;
	}
	public void setMoneyUnit(String moneyUnit) {
		this.moneyUnit = moneyUnit;
	}
	public void setOtherMoneyUnit(String otherMoneyUnit) {
		this.otherMoneyUnit = otherMoneyUnit;
	}
}
