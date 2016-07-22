package com.tq.requisition.domain.model.familyMember;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.model.removedInfo.RemovedInfo;
import com.tq.requisition.domain.model.share.Gender;
import com.tq.requisition.domain.model.transferHouseholdInfo.TransferHouseholdInfo;
import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 家庭成员信息聚合根
 * 
 * @author jjh
 * @time 2015-12-18 13:25
 */
public class FamilyItem extends AggregateRoot {
	/* private fields */
	/** 姓名 */
	private String name;
	/**所属项目名称*/
	private String proName;
	/** 身份证号 */
	private String idNumber;
	/** 出生年月 */
	private Date birthday;
	/** 性別 */
	private Gender gender;
	/** 獨身子女號 */
	private String onlyChildNumber;
	/** 半邊戶 */
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
	/** 是否拆迁 */
	private boolean removed;
	/** 是否转户 */
	private boolean transfer;
	/** 是否社保 */
	private boolean socialsecurity;
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
	/**所在组地址id*/
	private UUID groupId;
	/**备注*/
	private String remark;
	/**是否参加过社保*/
	private boolean isSocialsecurity;
	/**其他关系内容*/
	private String otherRelationship;
	
	/*constructors*/
	public FamilyItem(){
		this.id = UUID.randomUUID();
	}
	public FamilyItem(UUID id, String name, String idNumber, Date birthday,
			Gender gender, String onlyChildNumber, boolean half,
			String address, String relationshipStr, String householdStr,
			String socialsecurityStr, UUID streetId, UUID communityId,
			UUID relationshipId, UUID householdId, UUID socialsecurityTypeId,//
			UUID proId,UUID fmlId,String proName,boolean isRemoved, boolean isTransfer,boolean isSS) {
		this();
		this.name = name;
		this.idNumber = idNumber;
		this.birthday = birthday;
		this.gender = gender;
		this.onlyChildNumber = onlyChildNumber;
		this.half = half;
		this.address = address;
		this.relationshipStr = relationshipStr;
		this.householdStr = householdStr;
		this.socialsecurityStr = socialsecurityStr;
		this.streetId = streetId;
		this.communityId = communityId;
		this.relationshipId = relationshipId;
		this.householdId = householdId;
		this.socialsecurityTypeId = socialsecurityTypeId;
		this.proId = proId;
		this.fmlId = fmlId;
		this.proName = proName;
		this.removed = isRemoved;
		this.socialsecurity = isSS;
		this.transfer = isTransfer;
				
	}

	/*public methods*/
	public static FamilyItem obtain( String name, String idNumber, Date birthday,
			Gender gender, String onlyChildNumber, boolean half,
			String address, String relationshipStr, String householdStr,
			String socialsecurityStr, UUID streetId, UUID communityId,
			UUID relationshipId, UUID householdId, UUID socialsecurityTypeId,//
			UUID proId,UUID fmlId,String proName) {
		return new FamilyItem(//
				UUID.randomUUID(),//
				name, //
				idNumber, //
				birthday, //
				gender, //
				onlyChildNumber, //
				half, //
				address, //
				relationshipStr, //
				householdStr,//
				socialsecurityStr, //
				streetId, //
				communityId,//
				relationshipId,//
				householdId,//
				socialsecurityTypeId,//
				proId,//
				fmlId,//
				proName,//
				false,//
				false,//
				false);
	}
	
	/**
	 * 修改
	 * @param item
	 */
	public void modify(FamilyItem item) {
		this.birthday = item.getBirthday();
		this.gender = item.gender;
		this.half = item.getHalf();
		this.idNumber = item.getIdNumber();
		this.onlyChildNumber = item.getOnlyChildNumber();
		this.householdId = item.getHouseholdId();
		this.householdStr = item.getHouseholdStr();
		this.name = item.getName();
		this.currentEducationSituation = item.getCurrentEducationSituation();
		this.educationLevel = item.getEducationLevel();
		this.farmingTime = item.getFarmingTime();
		this.serveArmySituation = item.getServeArmySituation();
		this.tel = item.getTel();
		this.remark = item.getRemark();
		this.isSocialsecurity = item.getIsSocialsecurity();
	}
	
	/*getters and setters*/
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return this.id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getOnlyChildNumber() {
		return onlyChildNumber;
	}
	public void setOnlyChildNumber(String onlyChildNumber) {
		this.onlyChildNumber = onlyChildNumber;
	}
	public boolean getHalf() {
		return half;
	}
	public void setHalf(boolean half) {
		this.half = half;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRelationshipStr() {
		return relationshipStr;
	}
	public void setRelationshipStr(String relationshipStr) {
		this.relationshipStr = relationshipStr;
	}
	public String getHouseholdStr() {
		return householdStr;
	}
	public void setHouseholdStr(String householdStr) {
		this.householdStr = householdStr;
	}
	public String getSocialsecurityStr() {
		return socialsecurityStr;
	}
	public void setSocialsecurityStr(String socialsecurityStr) {
		this.socialsecurityStr = socialsecurityStr;
	}
	public UUID getStreetId() {
		return streetId;
	}
	public void setStreetId(UUID streetId) {
		this.streetId = streetId;
	}
	public UUID getCommunityId() {
		return communityId;
	}
	public void setCommunityId(UUID communityId) {
		this.communityId = communityId;
	}
	public UUID getRelationshipId() {
		return relationshipId;
	}
	public void setRelationshipId(UUID relationshipId) {
		this.relationshipId = relationshipId;
	}
	public UUID getHouseholdId() {
		return householdId;
	}
	public void setHouseholdId(UUID householdId) {
		this.householdId = householdId;
	}
	public UUID getSocialsecurityTypeId() {
		return socialsecurityTypeId;
	}
	public void setSocialsecurityTypeId(UUID socialsecurityTypeId) {
		this.socialsecurityTypeId = socialsecurityTypeId;
	}
	public void setProId(UUID proId) {
		this.proId = proId;
	}
	public UUID getProId() {
		return this.proId;
	}
	public void setFmlId(UUID fmlId) {
		this.fmlId = fmlId;
	}
	public UUID getFmlId() {
		return fmlId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public boolean getRemoved() {
		return removed;
	}
	public void setRemoved(boolean isRemoved) {
		this.removed = isRemoved;
	}
	public boolean getTransfer() {
		return transfer;
	}
	public void setTransfer(boolean isTransfer) {
		this.transfer = isTransfer;
	}
	public boolean getSocialsecurity() {
		return socialsecurity;
	}
	public void setSocialsecurity(boolean isSS) {
		this.socialsecurity = isSS;
	}
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	public String getCurrentEducationSituation() {
		return currentEducationSituation;
	}
	public void setCurrentEducationSituation(String currentEducationSituation) {
		this.currentEducationSituation = currentEducationSituation;
	}
	public String getFarmingTime() {
		return farmingTime;
	}
	public void setFarmingTime(String farmingTime) {
		this.farmingTime = farmingTime;
	}
	public String getServeArmySituation() {
		return serveArmySituation;
	}
	public void setServeArmySituation(String serveArmySituation) {
		this.serveArmySituation = serveArmySituation;
	}
	public UUID getGroupId() {
		return groupId;
	}
	public void setGroupId(UUID groupId) {
		this.groupId = groupId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public boolean getIsSocialsecurity() {
		return isSocialsecurity;
	}
	public void setIsSocialsecurity(boolean isS) {
		this.isSocialsecurity = isS;
	}
	public String getOtherRelationship() {
		return otherRelationship;
	}
	public void setOtherRelationship(String otherRelationship) {
		this.otherRelationship = otherRelationship;
	}
	/*public methods*/
	/**
	 * 标记转户状态
	 */
	public void markTransferState() {
		this.transfer = true;
	}

	/**
	 * 标记转户的社保状态为已社保
	 */
	public void markSocialsecurityState() {
		this.socialsecurity = true;
	}
	
	/**
	 * 转户信息更新
	 * @param info
	 * 		转户信息
	 */
	public void transferHousehold(TransferHouseholdInfo info) {
		this.householdId = info.getHouseHoldTypeId();
		this.householdStr = info.getHouseHoldTypeStr();
		this.streetId = info.getStreetId();
		this.communityId = info.getCommunityId();
		this.transfer = true;
		this.address = info.getAddress();
	}

	public RemovedInfo toRemovedInfo() {
		RemovedInfo info = new RemovedInfo();
		info.setId(UUID.randomUUID());
		info.setBirthDay(birthday);
		info.setCommunityId(communityId);
		info.setDel(false);
		info.setIdNumber(idNumber);
		info.setName(name);
		info.setAddress(address);
		info.setRemoveDate(new Date());
		info.setStreetId(streetId);
		return info;
	}
}
