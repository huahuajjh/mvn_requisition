package com.tq.requisition.presentation.dto.project;

import java.util.Date;

/**
 * 项目导出条件model
 * @author jjh
 * @time 2016 02-26 18:19
 * 
 * 增加两个条件，分别是：是否为本月完成结算项目；项目分类
 * @author bless
 * @time 2016/3/4 16:01
 */
public class ProExportCondition {
	/**起始日期*/
	private Date startDate;
	
	/**结束日期*/
	private Date endDate;
	
	/**是否本月完成*/
	private Boolean isInstantAchieve;
	
	/**项目分类*/
	private String proCategory;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Boolean getIsInstantAchieve() {
		return isInstantAchieve;
	}

	public void setIsInstantAchieve(Boolean isInstantAchieve) {
		this.isInstantAchieve = isInstantAchieve;
	}

	public String getProCategory() {
		return proCategory;
	}

	public void setProCategory(String proCategory) {
		this.proCategory = proCategory;
	}
}
