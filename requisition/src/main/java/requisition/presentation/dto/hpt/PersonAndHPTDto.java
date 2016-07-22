package com.tq.requisition.presentation.dto.hpt;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.domain.model.housePuraseTicket.HousePuraseTicket;
import com.tq.requisition.domain.model.share.Gender;
import com.tq.requisition.domain.model.share.TicketState;

public class PersonAndHPTDto {
	/** id */
	private UUID id;
	/** 姓名 */
	private String name;
	/** 身份证号 */
	private String idNumber;
	/** 出生年月 */
	private Date birthday;
	/** 性别 */
	private Gender gender;
	/** 独身子女号 */
	private String onlyChildNumber;
	/** 半边户 */
	private boolean half;
	/** 家庭住址 */
	private String address;
	/** 与户主关系 */
	private String relationshipStr;
	/** 户口性质 */
	private String householdStr;
	/** 社保类型 */
	private String socialsecurityStr;
	/** 街道id */
	private UUID streetId;
	/** 社区id */
	private UUID communityId;
	/** 与户主关系id */
	private UUID relationshipId;
	/** 户口类型id */
	private UUID householdId;
	/** 社保类型id */
	private UUID socialsecurityTypeId;
	/** 项目id */
	private UUID proId;
	/** 所属户id */
	private UUID fmlId;
	/** 所属项目名称 */
	private String proName;
	/** 是否拆迁 */
	private boolean isRemoved;
	/** 是否转户 */
	private boolean isTransfer;
	/** 是否社保 */
	private boolean isSS;
	/** 组地址id*/
	private UUID groupId;
	/**受教育程度*/
	private String educationLevel;
	/**在读情况*/	
	private String currentEducationSituation;
	/**务农时间*/
	private String farmingTime;
	/**服兵役，劳教等情况及时间*/
	private String serveArmySituation;
	/**电话*/
	private String tel;
	/**备注*/
	private String remark;
	/**是否参加过社保*/
	private boolean userdSocialsecurity;
	/**其他关系内容*/
	private String otherRelationship;
	/** 券号 */
	private String ticketNumber;
	/** 补贴金额 */
	private float bonus;

	/**创建人ID*/
	private String createUId;
	/**创建时间*/
	private Date createDate;
	
	/**
	 * 转换成人员信息
	 * @return
	 */
	public FamilyItem toFamilyItem(){
		FamilyItem item = new FamilyItem();
		item.setAddress(this.getAddress());
		item.setBirthday(this.getBirthday());
		item.setCommunityId(this.getCommunityId());
		item.setCurrentEducationSituation(this.getCurrentEducationSituation());
		item.setEducationLevel(this.getEducationLevel());
		item.setFarmingTime(this.getFarmingTime());
		item.setFmlId(this.getFmlId());
		item.setGender(this.getGender());
		item.setGroupId(this.getGroupId());
		item.setHalf(this.isHalf());
		item.setHouseholdId(this.getHouseholdId());
		item.setHouseholdStr(this.getHouseholdStr());
		item.setIdNumber(this.getIdNumber());
		item.setName(this.getName());
		item.setOnlyChildNumber(this.getOnlyChildNumber());
		item.setProId(this.getProId());
		item.setProName(this.getProName());
		item.setRelationshipId(this.getRelationshipId());
		item.setRelationshipStr(this.getRelationshipStr());
		item.setRemark(this.getRemark());
		item.setRemoved(this.isRemoved());
		item.setServeArmySituation(this.getServeArmySituation());
		item.setSocialsecurity(this.isSS());
		item.setSocialsecurityStr(this.getSocialsecurityStr());
		item.setSocialsecurityTypeId(this.getSocialsecurityTypeId());
		item.setStreetId(this.getStreetId());
		item.setTel(this.getTel());
		item.setTransfer(this.isTransfer());
		item.setIsSocialsecurity(this.isUserdSocialsecurity());
		item.setOtherRelationship(this.getOtherRelationship());
		return item;
	}

	public HousePuraseTicket toHousePuraseTicket(FamilyItem item){
		HousePuraseTicket model = new HousePuraseTicket();
		model.setBonus(this.getBonus());
		model.setFmlItemId(item.id());
		model.setIdNumber(item.getIdNumber());
		model.setMakeDate(new Date());
		model.setName(item.getName());
		model.setState(TicketState.NORMAL);
		model.setDel(false);
		model.setTicketNumber(this.getTicketNumber());
		model.setCreateDate(createDate);
		model.setCreateUId(createUId);
		return model;
	}
	
	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Gender getGender() {
		return gender;
	}

	public String getOnlyChildNumber() {
		return onlyChildNumber;
	}

	public boolean isHalf() {
		return half;
	}

	public String getAddress() {
		return address;
	}

	public String getRelationshipStr() {
		return relationshipStr;
	}

	public String getHouseholdStr() {
		return householdStr;
	}

	public String getSocialsecurityStr() {
		return socialsecurityStr;
	}

	public UUID getStreetId() {
		return streetId;
	}

	public UUID getCommunityId() {
		return communityId;
	}

	public UUID getRelationshipId() {
		return relationshipId;
	}

	public UUID getHouseholdId() {
		return householdId;
	}

	public UUID getSocialsecurityTypeId() {
		return socialsecurityTypeId;
	}

	public UUID getProId() {
		return proId;
	}

	public UUID getFmlId() {
		return fmlId;
	}

	public String getProName() {
		return proName;
	}

	public boolean isRemoved() {
		return isRemoved;
	}

	public boolean isTransfer() {
		return isTransfer;
	}

	public boolean isSS() {
		return isSS;
	}

	public UUID getGroupId() {
		return groupId;
	}

	public String getEducationLevel() {
		return educationLevel;
	}

	public String getCurrentEducationSituation() {
		return currentEducationSituation;
	}

	public String getFarmingTime() {
		return farmingTime;
	}

	public String getServeArmySituation() {
		return serveArmySituation;
	}

	public String getTel() {
		return tel;
	}

	public String getRemark() {
		return remark;
	}

	public boolean isUserdSocialsecurity() {
		return userdSocialsecurity;
	}

	public String getOtherRelationship() {
		return otherRelationship;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public float getBonus() {
		return bonus;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setOnlyChildNumber(String onlyChildNumber) {
		this.onlyChildNumber = onlyChildNumber;
	}

	public void setHalf(boolean half) {
		this.half = half;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setRelationshipStr(String relationshipStr) {
		this.relationshipStr = relationshipStr;
	}

	public void setHouseholdStr(String householdStr) {
		this.householdStr = householdStr;
	}

	public void setSocialsecurityStr(String socialsecurityStr) {
		this.socialsecurityStr = socialsecurityStr;
	}

	public void setStreetId(UUID streetId) {
		this.streetId = streetId;
	}

	public void setCommunityId(UUID communityId) {
		this.communityId = communityId;
	}

	public void setRelationshipId(UUID relationshipId) {
		this.relationshipId = relationshipId;
	}

	public void setHouseholdId(UUID householdId) {
		this.householdId = householdId;
	}

	public void setSocialsecurityTypeId(UUID socialsecurityTypeId) {
		this.socialsecurityTypeId = socialsecurityTypeId;
	}

	public void setProId(UUID proId) {
		this.proId = proId;
	}

	public void setFmlId(UUID fmlId) {
		this.fmlId = fmlId;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public void setRemoved(boolean isRemoved) {
		this.isRemoved = isRemoved;
	}

	public void setTransfer(boolean isTransfer) {
		this.isTransfer = isTransfer;
	}

	public void setSS(boolean isSS) {
		this.isSS = isSS;
	}

	public void setGroupId(UUID groupId) {
		this.groupId = groupId;
	}

	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}

	public void setCurrentEducationSituation(String currentEducationSituation) {
		this.currentEducationSituation = currentEducationSituation;
	}

	public void setFarmingTime(String farmingTime) {
		this.farmingTime = farmingTime;
	}

	public void setServeArmySituation(String serveArmySituation) {
		this.serveArmySituation = serveArmySituation;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setUserdSocialsecurity(boolean userdSocialsecurity) {
		this.userdSocialsecurity = userdSocialsecurity;
	}

	public void setOtherRelationship(String otherRelationship) {
		this.otherRelationship = otherRelationship;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public void setBonus(float bonus) {
		this.bonus = bonus;
	}

	public String getCreateUId() {
		return createUId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateUId(String createUId) {
		this.createUId = createUId;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
