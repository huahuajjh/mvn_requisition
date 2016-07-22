package com.tq.requisition.presentation.dto.rmHousehold;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.model.share.Gender;

public class FamilyItemDto {
	/* private fields */
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
	
	/*getters and setters*/
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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
	public boolean isHalf() {
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
	public UUID getFmlId() {
		return fmlId;
	}
	public void setFmlId(UUID fmlId) {
		this.fmlId = fmlId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public boolean getRemoved() {
		return isRemoved;
	}
	public void setRemoved(boolean isRemoved) {
		this.isRemoved = isRemoved;
	}
	public boolean getTransfer() {
		return isTransfer;
	}
	public void setTransfer(boolean isTransfer) {
		this.isTransfer = isTransfer;
	}
	public boolean getSS() {
		return isSS;
	}
	public void setSS(boolean isSS) {
		this.isSS = isSS;
	}
	public UUID getGroupId() {
		return groupId;
	}
	public void setGroupId(UUID groupId) {
		this.groupId = groupId;
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
	public boolean isRemoved() {
		return isRemoved;
	}
	public boolean isTransfer() {
		return isTransfer;
	}
	public boolean isSS() {
		return isSS;
	}
	public boolean getUserdSocialsecurity() {
		return userdSocialsecurity;
	}
	public void setUserdSocialsecurity(boolean userdSocialsecurity) {
		this.userdSocialsecurity = userdSocialsecurity;
	}
	public String getOtherRelationship() {
		return otherRelationship;
	}
	public void setOtherRelationship(String otherRelationship) {
		this.otherRelationship = otherRelationship;
	}
	
	/*constructors*/
	public FamilyItemDto() {}
	public FamilyItemDto(UUID id,String name, String idNumber,
			Date birthday, Gender gender, String onlyChildNumber, boolean half,
			String address, String relationshipStr, String householdStr,
			String socialsecurityStr, UUID streetId, UUID communityId,
			UUID relationshipId, UUID householdId, UUID socialsecurityTypeId,//
			UUID proId,UUID fmlId,String proName,boolean isRemoved, boolean isTransfer,boolean isSS) {
		super();
		this.id = id;
		this.proId = proId;
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
		this.fmlId = fmlId;
		this.proName = proName;
		this.isRemoved = isRemoved;
		this.isSS = isSS;
		this.isTransfer = isTransfer;
	}

	/*public methods*/
	public static FamilyItemDto obtain(UUID id, String name, String idNumber,
			Date birthday, Gender gender, String onlyChildNumber, boolean half,
			String address, String relationshipStr, String householdStr,
			String socialsecurityStr, UUID streetId, UUID communityId,
			UUID relationshipId, UUID householdId, UUID socialsecurityTypeId,//
			UUID proId,UUID fmlId,String proName,boolean isRemoved, boolean isTransfer,boolean isSS) {

		return new FamilyItemDto(//
				id,//
				name,//
				idNumber, //
				birthday, //
				gender, //
				onlyChildNumber, //
				half,//
				address, //
				relationshipStr, //
				householdStr, //
				socialsecurityStr, //
				streetId, //
				communityId,//
				relationshipId, //
				householdId,//
				socialsecurityTypeId,//
				proId,//
				fmlId,//
				proName,//
				isRemoved,//
				isSS,//
				isTransfer);
	}
	
}
