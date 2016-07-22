package com.tq.requisition.domain.model.socialsecurity;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 社保实体
 * @author jjh
 * @time 2015-12-18 18:30
 */
public class SocialsecurityInfo extends AggregateRoot{
	/**操作人*/
	private UUID oprUserId;
	/**操作日期*/
	private Date oprDate;
	/**纳入社保日期*/
	private Date socialsecurityDate;
	/**社保类型id*/
	private UUID socialsecurityTypeId;
	/**社保人id*/
	private UUID fmlItemId;
	/**是否删除*/
	private boolean del;
	/**军队服役时间(月)*/
	private Integer serveArmyTime;
	/**养老保险补缴年限*/
	private Integer endowmentInsuranceYear;
	/**医疗保险视同缴费月数*/
	private Integer medicalInsuranceMonth;
	/**参加何种医疗保险*/
	private String joinWhichMedicalInsurance;
	/**所属社区*/
	private String community;
	/**服刑或劳动教养时间(月)*/
	private Integer prisonTime;
	/**是否参加过社保*/
	private Boolean isSocialsecurity;
	/**身份证*/
	private String idNumber;
	/**类型字符串*/
	private String typeStr;
	/**创建人的标识*/
	private String createUid;
	/**创建时间*/
	private Date createDate;
	
	/*getters and setters*/	
	public UUID getOprUserId() {
		return oprUserId;
	}
	public Boolean getIsSocialsecurity() {
		return isSocialsecurity;
	}
	public void setIsSocialsecurity(Boolean isSocialsecurity) {
		this.isSocialsecurity = isSocialsecurity;
	}
	public void setServeArmyTime(Integer serveArmyTime) {
		this.serveArmyTime = serveArmyTime;
	}

	public void setPrisonTime(Integer prisonTime) {
		this.prisonTime = prisonTime;
	}
	public void setOprUserId(UUID oprUserId) {
		this.oprUserId = oprUserId;
	}
	public Date getOprDate() {
		return oprDate;
	}
	public void setOprDate(Date oprDate) {
		this.oprDate = oprDate;
	}
	public Date getSocialsecurityDate() {
		return socialsecurityDate;
	}
	public void setSocialsecurityDate(Date socialsecurityDate) {
		this.socialsecurityDate = socialsecurityDate;
	}
	public UUID getSocialsecurityTypeId() {
		return socialsecurityTypeId;
	}
	public void setSocialsecurityTypeId(UUID socialsecurityTypeId) {
		this.socialsecurityTypeId = socialsecurityTypeId;
	}
	public UUID getFmlItemId() {
		return fmlItemId;
	}
	public void setFmlItemId(UUID fmlItemId) {
		this.fmlItemId = fmlItemId;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return this.id;
	}
	public boolean getDel() {
		return del;
	}
	public void setDel(boolean del) {
		this.del = del;
	}
	public int getServeArmyTime() {
		return serveArmyTime;
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

	public int getEndowmentInsuranceYear() {
		return endowmentInsuranceYear;
	}
	public void setEndowmentInsuranceYear(int endowmentInsuranceYear) {
		this.endowmentInsuranceYear = endowmentInsuranceYear;
	}
	public int getMedicalInsuranceMonth() {
		return medicalInsuranceMonth;
	}
	public void setMedicalInsuranceMonth(int medicalInsuranceMonth) {
		this.medicalInsuranceMonth = medicalInsuranceMonth;
	}
	public String getJoinWhichMedicalInsurance() {
		return joinWhichMedicalInsurance;
	}
	public void setJoinWhichMedicalInsurance(String joinWhichMedicalInsurance) {
		this.joinWhichMedicalInsurance = joinWhichMedicalInsurance;
	}
	public String getCommunity() {
		return community;
	}
	public void setCommunity(String community) {
		this.community = community;
	}
	public int getPrisonTime() {
		return prisonTime;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getTypeStr() {
		return typeStr;
	}
	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}
	
	/*constructors*/
	public SocialsecurityInfo(){
		this.id = UUID.randomUUID();
	}

	/*public methods*/
	public void modify(SocialsecurityInfo ss) {
		this.socialsecurityDate = ss.getSocialsecurityDate();
		this.socialsecurityTypeId = ss.getSocialsecurityTypeId();
		this.community = ss.getCommunity();
		this.joinWhichMedicalInsurance = ss.getJoinWhichMedicalInsurance();
		this.endowmentInsuranceYear = ss.getEndowmentInsuranceYear();
		this.medicalInsuranceMonth = ss.getMedicalInsuranceMonth();
		this.prisonTime = ss.getPrisonTime();
		this.serveArmyTime = ss.getServeArmyTime();
		
	}
	
}
