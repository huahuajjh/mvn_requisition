package com.tq.requisition.domain.model.project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.share.AggregateRoot;
import com.tq.requisition.exception.DomainException;

/**
 * 项目领域模型，聚合根
 * @author jjh
 * @time 2015-12-18 11:01
 */
public class Project extends AggregateRoot{
	/*private fields*/
	/**项目名称*/
	private String proName;	
	/**项目审批号*/
	private String approvalNumber;
	/**征地面积*/
	private float requisitionArea;
	/**应拆栋数*/
	private int shouldRemoveBuildings;
	/**应拆户数*/
	private int shouldRemoveHouses;
	/**应拆合法总面积*/
	private float shouldRemoveLegalArea;
	/**应拆违章总面积*/
	private float shouldRemoveIllegalArea;
	/**应动迁人口*/
	private int shouldMovePopulation;
	/**项目应付补偿款*/
	private BigDecimal shouldPayMoney = new BigDecimal(0);
	/**项目累计已付补偿款*/
	private BigDecimal totalPayMoney = new BigDecimal(0);
	/**项目启动日期*/
	private Date startDate;
	/**是否新启动*/
	private boolean newStart;
	/**项目类型*/
	private Integer proType;
	/**项目类型名称*/
	private String proTypeStr;
	/**项目全地址*/
	private String totalAddress;
	/**街道地址id*/
	private String streetId;
	/**社区地址id*/
	private String communityId;
	/**公告序列*/
	private int sequence;
	/**公告名称*/
	private String sequenceStr;
	/**累计已腾地*/
	private float requisitionLandAreaTotal;
	/**本年已腾地*/
	private float requisitionLandAreaYear;
	/**累计已拆合法栋数*/
	private int removedBuildingsLegalTotal;
	/**本年已拆合法栋数*/
	private int removedBuildingsLegalYear;
	/**累计已拆合法户数*/
	private int removedHousesLegalTotal;
	/**本年已拆合法户数*/
	private int removedHousesLegalYear;
	/**累计已拆合法面积*/
	private float removedAreaLegalTotal;
	/**本年已拆合法面积*/
	private float removedAreaLegalYear;
	/**累计已拆合法面积*/
	private float removedAreaIllegalTotal;
	/**本年已拆违章面积*/
	private float removedAreaIllegalYear;
	/**累计已迁人口*/
	private int removedPopulationTotal;
	/**本年已迁人口*/
	private int removedPopulationYear;
	/**项目分类id*/
	private String categoryId;
	/**项目分类str*/
	private String categoryStr;
	/**是否本月完成*/
	private String curMonthComplete;
	/**是否6前项目*/
	private String sixForward;
	/**出资单位*/
	private String moneyUnit;
	/**其他出资单位*/
	private String otherMoneyUnit;
	/**公告集合-为导出服务*/
	private List<Announcement> announcements;
	/**项目item集合*/
	private List<ProjectItem> items;
	
	/**创建人的标识*/
	private String createUid;
	/**创建时间*/
	private Date createDate;
	
	/*getters and setters*/
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
	public float getRequisitionArea() {
		return requisitionArea;
	}
	public void setRequisitionArea(float requisitionArea) {
		this.requisitionArea = requisitionArea;
	}
	public int getShouldRemoveBuildings() {
		return shouldRemoveBuildings;
	}
	public void setShouldRemoveBuildings(int shouldRemoveBuildings) {
		this.shouldRemoveBuildings = shouldRemoveBuildings;
	}
	public int getShouldRemoveHouses() {
		return shouldRemoveHouses;
	}
	public void setShouldRemoveHouses(int shouldRemoveHouses) {
		this.shouldRemoveHouses = shouldRemoveHouses;
	}
	public float getShouldRemoveLegalArea() {
		return shouldRemoveLegalArea;
	}
	public void setShouldRemoveLegalArea(float shouldRemoveLegalArea) {
		this.shouldRemoveLegalArea = shouldRemoveLegalArea;
	}
	public float getShouldRemoveIllegalArea() {
		return shouldRemoveIllegalArea;
	}
	public void setShouldRemoveIllegalArea(float shouldRemoveIllegalArea) {
		this.shouldRemoveIllegalArea = shouldRemoveIllegalArea;
	}
	public int getShouldMovePopulation() {
		return shouldMovePopulation;
	}
	public void setShouldMovePopulation(int shouldMovePopulation) {
		this.shouldMovePopulation = shouldMovePopulation;
	}
	public BigDecimal getShouldPayMoney() {
		if(null==shouldPayMoney){
			return new BigDecimal(0);
		}
		return shouldPayMoney;
	}
	public void setShouldPayMoney(BigDecimal shouldPayMoney) {
		this.shouldPayMoney = shouldPayMoney;
	}
	public BigDecimal getTotalPayMoney() {
		if(null==totalPayMoney){
			return new BigDecimal(0);
		}
		return totalPayMoney;
	}
	public void setTotalPayMoney(BigDecimal totalPayMoney) {
		this.totalPayMoney = totalPayMoney;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public boolean getNewStart() {
		return newStart;
	}
	public void setNewStart(boolean newStart) {
		this.newStart = newStart;
	}
	public Integer getProType() {
		return proType;
	}
	public void setProType(int proType) {
		this.proType = proType;
	}
	public String getTotalAddress() {
		return totalAddress;
	}
	public void setTotalAddress(String totalAddress) {
		this.totalAddress = totalAddress;
	}
	public String getStreetId() {
		return streetId;
	}
	public void setStreetId(String streetId) {
		this.streetId = streetId;
	}
	public String getCommunityId() {
		return communityId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return this.id;
	}
	public void setSequence(int s) {
		this.sequence = s;
	}
	public int getSequence() {
		return this.sequence;
	}
	public String getSequenceStr() {
		return sequenceStr;
	}
	public void setSequenceStr(String sequenceStr) {
		this.sequenceStr = sequenceStr;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	public String getProTypeStr() {
		toProTypeStr();
		return this.proTypeStr;
	}
	public float getRequisitionLandAreaTotal() {
		return requisitionLandAreaTotal;
	}
	public void setRequisitionLandAreaTotal(float requisitionLandAreaTotal) {
		this.requisitionLandAreaTotal = requisitionLandAreaTotal;
	}
	public float getRequisitionLandAreaYear() {
		return requisitionLandAreaYear;
	}
	public void setRequisitionLandAreaYear(float requisitionLandAreaYear) {
		this.requisitionLandAreaYear = requisitionLandAreaYear;
	}
	public int getRemovedBuildingsLegalTotal() {
		return removedBuildingsLegalTotal;
	}
	public void setRemovedBuildingsLegalTotal(int removedBuildingsLegalTotal) {
		this.removedBuildingsLegalTotal = removedBuildingsLegalTotal;
	}
	public int getRemovedBuildingsLegalYear() {
		return removedBuildingsLegalYear;
	}
	public void setRemovedBuildingsLegalYear(int removedBuildingsLegalYear) {
		this.removedBuildingsLegalYear = removedBuildingsLegalYear;
	}
	public int getRemovedHousesLegalTotal() {
		return removedHousesLegalTotal;
	}
	public void setRemovedHousesLegalTotal(int removedHousesLegalTotal) {
		this.removedHousesLegalTotal = removedHousesLegalTotal;
	}
	public int getRemovedHousesLegalYear() {
		return removedHousesLegalYear;
	}
	public void setRemovedHousesLegalYear(int removedHousesLegalYear) {
		this.removedHousesLegalYear = removedHousesLegalYear;
	}
	public float getRemovedAreaLegalTotal() {
		return removedAreaLegalTotal;
	}
	public void setRemovedAreaLegalTotal(float removedAreaLegalTotal) {
		this.removedAreaLegalTotal = removedAreaLegalTotal;
	}
	public float getRemovedAreaLegalYear() {
		return removedAreaLegalYear;
	}
	public void setRemovedAreaLegalYear(float removedAreaLegalYear) {
		this.removedAreaLegalYear = removedAreaLegalYear;
	}
	public float getRemovedAreaIllegalTotal() {
		return removedAreaIllegalTotal;
	}
	public void setRemovedAreaIllegalTotal(float removedAreaIllegalTotal) {
		this.removedAreaIllegalTotal = removedAreaIllegalTotal;
	}
	public float getRemovedAreaIllegalYear() {
		return removedAreaIllegalYear;
	}
	public void setRemovedAreaIllegalYear(float removedAreaIllegalYear) {
		this.removedAreaIllegalYear = removedAreaIllegalYear;
	}
	public int getRemovedPopulationTotal() {
		return removedPopulationTotal;
	}
	public void setRemovedPopulationTotal(int removedPopulationTotal) {
		this.removedPopulationTotal = removedPopulationTotal;
	}
	public int getRemovedPopulationYear() {
		return removedPopulationYear;
	}
	public void setRemovedPopulationYear(int removedPopulationYear) {
		this.removedPopulationYear = removedPopulationYear;
	}
	public void setProTypeStr(String proTypeStr) {
		this.proTypeStr = proTypeStr;
	}
	public List<ProjectItem> getItems() {
		return items;
	}
	public void setItems(List<ProjectItem> items) {
		this.items = items;
	}
	public void setProType(Integer proType) {
		this.proType = proType;
	}
	public List<Announcement> getAnnouncements() {
		return announcements;
	}
	public void setAnnouncements(List<Announcement> announcements) {
		this.announcements = announcements;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryStr() {
		return categoryStr;
	}
	public void setCategoryStr(String categoryStr) {
		this.categoryStr = categoryStr;
	}
	public String getCurMonthComplete() {
		return curMonthComplete;
	}
	public void setCurMonthComplete(String curMonthComplete) {
		this.curMonthComplete = curMonthComplete;
	}
	public String getSixForward() {
		return sixForward;
	}
	public void setSixForward(String sixForward) {
		this.sixForward = sixForward;
	}
	public String getCreateUid() {
		return createUid;
	}
	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getMoneyUnit() {
		return moneyUnit;
	}
	public void setMoneyUnit(String moneyUnit) {
		this.moneyUnit = moneyUnit;
	}
	public String getOtherMoneyUnit() {
		return otherMoneyUnit;
	}
	public void setOtherMoneyUnit(String otherMoneyUnit) {
		this.otherMoneyUnit = otherMoneyUnit;
	}
	
	/*constructors*/
	public Project(){
		this.id = UUID.randomUUID();
		this.items = new ArrayList<ProjectItem>();
	}
	public Project(UUID id,String proName, String approvalNumber,
			float requisitionArea, int shouldRemoveBuildings,
			int shouldRemoveHouses, float shouldRemoveLegalArea,
			float shouldRemoveIllegalArea, int shouldMovePopulation,
			BigDecimal shouldPayMoney, BigDecimal totalPayMoney,
			Date startDate, boolean newStart, Integer proType,
			String proTypeStr, String totalAddress, String streetId,
			String communityId, Integer sequence, String sequenceStr,
			float requisitionLandAreaTotal, float requisitionLandAreaYear,
			int removedBuildingsLegalTotal, int removedBuildingsLegalYear,
			int removedHousesLegalTotal, int removedHousesLegalYear,
			float removedAreaLegalTotal, float removedAreaLegalYear,
			float removedAreaIllegalTotal, float removedAreaIllegalYear,
			int removedPopulationTotal, int removedPopulationYear) {
		this();
		this.proName = proName;
		this.approvalNumber = approvalNumber;
		this.requisitionArea = requisitionArea;
		this.shouldRemoveBuildings = shouldRemoveBuildings;
		this.shouldRemoveHouses = shouldRemoveHouses;
		this.shouldRemoveLegalArea = shouldRemoveLegalArea;
		this.shouldRemoveIllegalArea = shouldRemoveIllegalArea;
		this.shouldMovePopulation = shouldMovePopulation;
		this.shouldPayMoney = shouldPayMoney;
		this.totalPayMoney = totalPayMoney;
		this.startDate = startDate;
		this.newStart = newStart;
		this.proType = proType;
		this.proTypeStr = proTypeStr;
		this.totalAddress = totalAddress;
		this.streetId = streetId;
		this.communityId = communityId;
		this.sequence = sequence;
		this.sequenceStr = sequenceStr;
		this.requisitionLandAreaTotal = requisitionLandAreaTotal;
		this.requisitionLandAreaYear = requisitionLandAreaYear;
		this.removedBuildingsLegalTotal = removedBuildingsLegalTotal;
		this.removedBuildingsLegalYear = removedBuildingsLegalYear;
		this.removedHousesLegalTotal = removedHousesLegalTotal;
		this.removedHousesLegalYear = removedHousesLegalYear;
		this.removedAreaLegalTotal = removedAreaLegalTotal;
		this.removedAreaLegalYear = removedAreaLegalYear;
		this.removedAreaIllegalTotal = removedAreaIllegalTotal;
		this.removedAreaIllegalYear = removedAreaIllegalYear;
		this.removedPopulationTotal = removedPopulationTotal;
		this.removedPopulationYear = removedPopulationYear;
	}

	/*public methods*/
	public static Project obtain(UUID id,String proName, String approvalNumber,
			float requisitionArea, int shouldRemoveBuildings,
			int shouldRemoveHouses, float shouldRemoveLegalArea,
			float shouldRemoveIllegalArea, int shouldMovePopulation,
			BigDecimal shouldPayMoney, BigDecimal totalPayMoney,
			Date startDate, boolean newStart, Integer proType,
			String proTypeStr, String totalAddress, String streetId,
			String communityId, Integer sequence, String sequenceStr,
			float requisitionLandAreaTotal, float requisitionLandAreaYear,
			int removedBuildingsLegalTotal, int removedBuildingsLegalYear,
			int removedHousesLegalTotal, int removedHousesLegalYear,
			float removedAreaLegalTotal, float removedAreaLegalYear,
			float removedAreaIllegalTotal, float removedAreaIllegalYear,
			int removedPopulationTotal, int removedPopulationYear) {
		
		return new Project(UUID.randomUUID(),proName, approvalNumber,
				requisitionArea, shouldRemoveBuildings,
				shouldRemoveHouses, shouldRemoveLegalArea,
				shouldRemoveIllegalArea, shouldMovePopulation,
				shouldPayMoney, totalPayMoney,
				startDate, newStart, proType,
				proTypeStr, totalAddress, streetId,
				communityId, sequence, sequenceStr,
				requisitionLandAreaTotal, requisitionLandAreaYear,
				removedBuildingsLegalTotal, removedBuildingsLegalYear,
				removedHousesLegalTotal, removedHousesLegalYear,
				removedAreaLegalTotal, removedAreaLegalYear,
				removedAreaIllegalTotal, removedAreaIllegalYear,
				removedPopulationTotal, removedPopulationYear);
	}

	/**
	 * 字段验证
	 */
	public void validate() {
		
	}

	public void modify(Project pro) {
		this.proName = pro.getProName();
		this.approvalNumber = pro.getApprovalNumber();
		this.requisitionArea = pro.getRequisitionArea();
		this.shouldRemoveBuildings = pro.getShouldRemoveBuildings();
		this.shouldRemoveHouses = pro.getShouldRemoveHouses();
		this.shouldRemoveLegalArea = pro.getShouldRemoveLegalArea();
		this.shouldRemoveIllegalArea = pro.getShouldRemoveIllegalArea();
		this.shouldMovePopulation = pro.getShouldMovePopulation();
		this.shouldPayMoney = pro.getShouldPayMoney();
		this.totalPayMoney = pro.getTotalPayMoney();
		this.proType = pro.getProType();
		this.totalAddress = pro.getTotalAddress();
		this.sixForward = pro.getSixForward();
		this.categoryStr = pro.getCategoryStr();
		this.setStreetId(pro.getStreetId());
		this.setCommunityId(pro.getCommunityId());
	}
	
	public void modify4Import(Project pro) {
		this.requisitionArea = pro.getRequisitionArea();
		this.shouldRemoveBuildings = pro.getShouldRemoveBuildings();
		this.shouldRemoveHouses = pro.getShouldRemoveHouses();
		this.shouldRemoveLegalArea = pro.getShouldRemoveLegalArea();
		this.shouldRemoveIllegalArea = pro.getShouldRemoveIllegalArea();
		this.shouldMovePopulation = pro.getShouldMovePopulation();
		this.shouldPayMoney = pro.getShouldPayMoney();
		this.totalPayMoney = pro.getTotalPayMoney();
		this.proType = pro.getProType();
		this.totalAddress = pro.getTotalAddress();
		this.sequence = pro.getSequence();
	}

	public void toAnnStr() {
		switch (sequence) {
		case 1:
			this.sequenceStr = "一公告";
			break;
		case 2:
			this.sequenceStr = "二公告";
			break;
		case 3:
			this.sequenceStr = "三公告";
			break;
		default:
			break;
		}
	}

	public void toProTypeStr() {
		switch (proType) {
		case 1:
			this.proTypeStr="基础设施";
			break;
			
		case 2:
			this.proTypeStr="其他";
			break;

		default:
			break;
		}
	}

	public void updateMoney(BigDecimal m) throws DomainException {
		if(null==m){return;}
		if(this.getTotalPayMoney().add(m).compareTo(this.getShouldPayMoney())>0){
			throw new DomainException("[已付补偿款]累计已经大于[项目应付补偿款]了");
		}
		this.setTotalPayMoney(this.getTotalPayMoney().add(m));
	}
	
	/**
	 * 更新项目启动时间
	 * @param d
	 */
	public void updateStartDate(Date d) {
		if(this.startDate==null)
		{
			this.startDate = d;
			return;
		}
	}
		
	/**
	 * 更新年度和累计数据
	 * @param item
	 * 		月度信息实体
	 */
	public void updateTotalData(ProjectItem item) {
		//月度累计
		this.removedAreaIllegalTotal += item.getRemovedIllegalArea();
		this.removedAreaLegalTotal += item.getRemovedLegalArea();
		this.removedBuildingsLegalTotal += item.getRemovedBuildings();
		this.removedHousesLegalTotal += item.getRmovedHouses();
		this.removedPopulationTotal += item.getMovedPopulation();
		this.requisitionLandAreaTotal += item.getRemovedLandArea();
		
		//年度？
		this.removedAreaIllegalYear += item.getRemovedIllegalArea();
		this.removedAreaLegalYear += item.getRemovedLegalArea();
		this.removedBuildingsLegalYear += item.getRemovedBuildings();
		this.removedHousesLegalYear += item.getRmovedHouses();
		this.removedPopulationYear += item.getMovedPopulation();
		this.requisitionLandAreaYear += item.getRemovedLandArea();
	}
	
}
