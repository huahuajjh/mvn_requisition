package com.tq.requisition.domain.model.removeFamily;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.domain.share.AggregateRoot;
import com.tq.requisition.exception.InvalidOperationException;

/**
 * 拆迁户聚合根
 * @author jjh
 * @time 2015-12-18 13:25
 */
public class Family extends AggregateRoot{
	/*private fields*/
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
	/**所属项目名称*/
	private String proName;
	/**组地址id*/
	private UUID groupId;
	/**联合会审意见文件路径*/
	private String unionSuggestionPath;
	/**高拍仪拍照*/
	private String image;
	
	/**创建人的标识*/
	private String createUid;
	/**创建时间*/
	private Date createDate;
	
	//非持久化字段
	/**户主身份证*/
	private String idNumber;
	/**家庭成员集合*/
	private List<FamilyItem> items = new ArrayList<FamilyItem>();

	/*constructors*/
	public Family(){
		this.id = UUID.randomUUID();
	}
	public Family(UUID id,String headName, UUID proId, UUID headId, UUID streetId,
			UUID communityId, String address, Integer fmlNumber,
			float houseLegalArea, float houseIllegalArea, String satuationDesc,
			String dealSolution, String unionSuggestion, String remark,
			String houseImgPath,List<FamilyItem> items,String proName,UUID groupId,String unionSuggestionPath,String image,String createUid,Date createDate) {
		this();
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
		this.image = image;
		this.createUid = createUid;
		this.createDate = createDate;
	}

	/*public methods*/
	public static Family obtain(String headName, UUID proId, UUID headId, UUID streetId,
			UUID communityId, String address, Integer fmlNumber,
			float houseLegalArea, float houseIllegalArea, String satuationDesc,
			String dealSolution, String unionSuggestion, String remark,
			String houseImgPath,List<FamilyItem> items,String proName,UUID groupId,String unionSuggestionPath,String image,String createUid,Date createDate) {
		
		return new Family(//
				UUID.randomUUID(),
				headName,//
				proId, //
				headId, //
				streetId, //
				communityId,//
				address, //
				fmlNumber, //
				houseLegalArea, //
				houseIllegalArea, //
				satuationDesc, //
				dealSolution, //
				unionSuggestion,//
				remark, //
				houseImgPath,//
				items,//
				proName,//
				groupId,//
				unionSuggestionPath,
				image,
				createUid,
				createDate);
	}

	public void validate() {
		if(headName==null || headName.trim().equals(""))
		{
			throw new NullPointerException("户信息多个字段为空");
		}		
	}

	/*getters and setters*/
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return this.id;
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
	public List<FamilyItem> getItems() {
		return items;
	}
	public void setItems(List<FamilyItem> items) {
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
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
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
	/*public methods*/
	/**
	 * 新增数据验证
	 */
	public void validateByAdd() {
		if(this.items==null)
		{
			throw new InvalidOperationException("新增数据无拆迁户成员信息");
		}
	}

	public void modify(Family fml) {
		this.address = fml.getAddress();
		this.communityId = fml.getCommunityId();
		this.streetId = fml.getStreetId();
		this.dealSolution = fml.getDealSolution();
		this.unionSuggestion = fml.getUnionSuggestion();
		this.remark = fml.getRemark();
		this.headName = fml.getHeadName();
		this.houseIllegalArea = fml.getHouseIllegalArea();
		this.houseLegalArea = fml.getHouseLegalArea();
		this.groupId = fml.getGroupId();
		this.satuationDesc = fml.getSatuationDesc();
		this.unionSuggestionPath = fml.getUnionSuggestionPath();
		this.houseImgPath = fml.getHouseImgPath();
		this.image = fml.image;
	}
	
}
