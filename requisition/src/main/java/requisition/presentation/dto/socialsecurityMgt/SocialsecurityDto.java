package com.tq.requisition.presentation.dto.socialsecurityMgt;

import java.util.Date;
import java.util.UUID;

/**
 * 提供查询社保信息集合数据的dto展示
 * @author jjh
 * @time 2015-12-31 21:18
 *
 */
public class SocialsecurityDto {
	/**社保数据id*/
	private UUID id;
	/**所属项目id*/
	private UUID proId;
	/**所属项目名称*/
	private String proName;
	/**社保人姓名*/
	private String name;
	/**社保人身份证*/
	private String idNumber;
	/**社保类型名称*/
	private String ssTypeStr;
	/**纳入社保时间*/
	private Date ssDate;
	/**拆迁人员id*/
	private UUID fmlItemId;
	
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
	/**社保类型id*/
	private UUID ssTypeId;
	
	
	/*constructors*/
	public SocialsecurityDto(){}
	public SocialsecurityDto(UUID id, UUID proId, String proName, String name,
			String idNumber, String ssTypeStr, Date ssDate,UUID fmlItemId,
			Integer serveArmyTime,Integer endowmentInsuranceYear,Integer medicalInsuranceMonth,String joinWhichMedicalInsurance,
			String community,Integer prisonTime,UUID ssTypeId) {
		super();
		this.id = id;
		this.proId = proId;
		this.proName = proName;
		this.name = name;
		this.idNumber = idNumber;
		this.ssTypeStr = ssTypeStr;
		this.ssDate = ssDate;
		this.fmlItemId = fmlItemId;
		this.serveArmyTime = serveArmyTime;
		this.endowmentInsuranceYear = endowmentInsuranceYear;
		this.medicalInsuranceMonth = medicalInsuranceMonth;
		this.joinWhichMedicalInsurance = joinWhichMedicalInsurance;
		this.community = community;
		this.prisonTime = prisonTime;
		this.ssTypeId = ssTypeId;
	}

	/*getters and setters*/
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getProId() {
		return proId;
	}
	public void setProId(UUID proId) {
		this.proId = proId;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
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
	public String getSsTypeStr() {
		return ssTypeStr;
	}
	public void setSsTypeStr(String ssTypeStr) {
		this.ssTypeStr = ssTypeStr;
	}
	public Date getSsDate() {
		return ssDate;
	}
	public UUID getSsTypeId() {
		return ssTypeId;
	}
	public void setSsTypeId(UUID ssTypeId) {
		this.ssTypeId = ssTypeId;
	}
	public void setSsDate(Date ssDate) {
		this.ssDate = ssDate;
	}
	public UUID getFmlItemId() {
		return fmlItemId;
	}
	public void setFmlItemId(UUID fmlItemId) {
		this.fmlItemId = fmlItemId;
	}
	public Integer getServeArmyTime() {
		return serveArmyTime;
	}
	public void setServeArmyTime(Integer serveArmyTime) {
		this.serveArmyTime = serveArmyTime;
	}
	public Integer getEndowmentInsuranceYear() {
		return endowmentInsuranceYear;
	}
	public void setEndowmentInsuranceYear(Integer endowmentInsuranceYear) {
		this.endowmentInsuranceYear = endowmentInsuranceYear;
	}
	public Integer getMedicalInsuranceMonth() {
		return medicalInsuranceMonth;
	}
	public void setMedicalInsuranceMonth(Integer medicalInsuranceMonth) {
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
	public Integer getPrisonTime() {
		return prisonTime;
	}
	public void setPrisonTime(Integer prisonTime) {
		this.prisonTime = prisonTime;
	}

}
