package com.tq.requisition.presentation.dto.rmHousehold;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 拆迁户dto
 * 
 * @author jjh
 * @time 2015-12-28 18:03
 */
public class FamilyDto {
	/*private fields*/
	/**id*/
	private UUID id;
	/**户主*/
	private String headName;
	/**所属项目id*/
	private UUID proId;
	/**户主id*/
	private UUID headId;
	/**街道id*/
	private UUID streetId;
	/**社区id*/
	private UUID communityId;
	/**地址全名称*/
	private String address;
	/**家庭人数*/
	private Integer fmlNumber;
	/**房屋合法面积*/
	private float houseLegalArea;
	/**房屋违章面积*/
	private float houseIllegalArea;
	/**批证及其他情况说明*/
	private String satuationDesc;
	/**拟定处理方案*/
	private String dealSolution;
	/**联合会审意见*/
	private String unionSuggestion;
	/**备注*/
	private String remark;
	/**房屋图片地址*/
	private String houseImgPath;
	/** 所属项目名称 */
	private String proName;
	/** 组地址id*/
	private UUID groupId;
	/**联合会审意见文件路径*/
	private String unionSuggestionPath;
	/**家庭成员集合*/
	private List<FamilyItemDto> items;
	/**高拍仪拍照*/
	private String image;
	
	/**创建人的标识*/
	private String createUid;
	/**创建时间*/
	private Date createDate;
	
	/*getters and setters*/
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public UUID getProId() {
		return proId;
	}
	public void setProId(UUID proId) {
		this.proId = proId;
	}
	public UUID getHeadId() {
		return headId;
	}
	public void setHeadId(UUID headId) {
		this.headId = headId;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getFmlNumber() {
		return fmlNumber;
	}
	public void setFmlNumber(Integer fmlNumber) {
		this.fmlNumber = fmlNumber;
	}
	public float getHouseLegalArea() {
		return houseLegalArea;
	}
	public void setHouseLegalArea(float houseLegalArea) {
		this.houseLegalArea = houseLegalArea;
	}
	public float getHouseIllegalArea() {
		return houseIllegalArea;
	}
	public void setHouseIllegalArea(float houseIllegalArea) {
		this.houseIllegalArea = houseIllegalArea;
	}
	public String getSatuationDesc() {
		return satuationDesc;
	}
	public void setSatuationDesc(String satuationDesc) {
		this.satuationDesc = satuationDesc;
	}
	public String getDealSolution() {
		return dealSolution;
	}
	public void setDealSolution(String dealSolution) {
		this.dealSolution = dealSolution;
	}
	public String getUnionSuggestion() {
		return unionSuggestion;
	}
	public void setUnionSuggestion(String unionSuggestion) {
		this.unionSuggestion = unionSuggestion;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getHouseImgPath() {
		return houseImgPath;
	}
	public void setHouseImgPath(String houseImgPath) {
		this.houseImgPath = houseImgPath;
	}
	public List<FamilyItemDto> getItems() {
		return items;
	}
	public void setItems(List<FamilyItemDto> items) {
		this.items = items;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public UUID getGroupId() {
		return groupId;
	}
	public void setGroupId(UUID groupId) {
		this.groupId = groupId;
	}
	public String getUnionSuggestionPath() {
		return unionSuggestionPath;
	}
	public void setUnionSuggestionPath(String unionSuggestionPath) {
		this.unionSuggestionPath = unionSuggestionPath;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	
	/*constructors*/
	public FamilyDto(){
		items = new ArrayList<FamilyItemDto>();
	}
	public FamilyDto(UUID id, String headName, UUID proId, UUID headId,
			UUID streetId, UUID communityId, String address, Integer fmlNumber,
			float houseLegalArea, float houseIllegalArea, String satuationDesc,
			String dealSolution, String unionSuggestion, String remark,
			String houseImgPath, List<FamilyItemDto> items,String proName,UUID groupId,String unionSuggestionPath) {
		this();
		this.id = id;
		this.headName = headName;
		this.proId = proId;
		this.headId = headId;
		this.streetId = streetId;
		this.communityId = communityId;
		this.address = address;
		this.fmlNumber = fmlNumber;
		this.houseLegalArea = houseLegalArea;
		this.houseIllegalArea = houseIllegalArea;
		this.satuationDesc = satuationDesc;
		this.dealSolution = dealSolution;
		this.unionSuggestion = unionSuggestion;
		this.remark = remark;
		this.houseImgPath = houseImgPath;
		this.items = items;
		this.proName = proName;
		this.groupId = groupId;
		this.unionSuggestionPath = unionSuggestionPath;
	}
	
	/*public methods*/
	public static FamilyDto obtain(UUID id, String headName, UUID proId, UUID headId,
			UUID streetId, UUID communityId, String address, Integer fmlNumber,
			float houseLegalArea, float houseIllegalArea, String satuationDesc,
			String dealSolution, String unionSuggestion, String remark,
			String houseImgPath, List<FamilyItemDto> items,String proName,UUID groupId,String unionSuggestionPath) {
		
		return new FamilyDto(//
				id,//
				headName, //
				proId, //
				headId, //
				streetId,//
				communityId, //
				address,//
				fmlNumber,//
				houseLegalArea,//
				houseIllegalArea,//
				satuationDesc, //
				dealSolution, //
				unionSuggestion, //
				remark, //
				houseImgPath, //
				items,//
				proName,//
				groupId,//
				unionSuggestionPath);
	}
	
}
