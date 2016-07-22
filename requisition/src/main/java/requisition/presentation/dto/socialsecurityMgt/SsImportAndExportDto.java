package com.tq.requisition.presentation.dto.socialsecurityMgt;

import java.util.Date;
import java.util.UUID;

import com.excel.util.annotation.InputColAnnotation;
import com.tq.requisition.domain.model.socialsecurity.SocialsecurityInfo;

/**
 * 社保导入导出model
 * 
 * @author jjh
 * @time 2016-01-16 15:16
 */
public class SsImportAndExportDto {

	/** 姓名 */
	@InputColAnnotation(colCoord = 0)
	private String name;

	/** 身份证号 */
	@InputColAnnotation(colCoord = 1, only = true, required = true, onlyErrorMsg = "[身份证号]只能唯一", requiredErrorMsg = "[身份证号]不可为空")
	private String idNumber;

	/** 性别 */
	@InputColAnnotation(colCoord = 2)
	private String gender;

	/** 出生日期 */
	@InputColAnnotation(colCoord = 3)
	private String birthday;

	/** 年龄段 */
	@InputColAnnotation(colCoord = 4)
	private String ageRange;

	/** 从事农业生产时间 */
	@InputColAnnotation(colCoord = 5)
	private String farmingTime;

	/** 服刑或劳动教养时间(月) */
	@InputColAnnotation(colCoord = 6, converErrorMsg = "类型错误，[服刑或劳动教养时间(月)]是整数")
	private int prisonTime;

	/** 军队服役时间(月) */
	@InputColAnnotation(colCoord = 7, converErrorMsg = "类型错误，[军队服役时间(月)]是整数")
	private int serveArmyTime;

	/** 养老保险补缴年限 */
	@InputColAnnotation(colCoord = 8, converErrorMsg = "类型错误，[养老保险补缴年限]是整数")
	private int endowmentInsuranceYear;

	/** 医疗保险视同缴费月数 */
	@InputColAnnotation(colCoord = 9, converErrorMsg = "类型错误，[医疗保险视同缴费月数]是整数")
	private int medicalInsuranceMonth;

	/** 参加何种医疗保险 */
	@InputColAnnotation(colCoord = 10)
	private String joinWhichMedicalInsurance;

	/** 是否参加过社保 */
	@InputColAnnotation(colCoord = 11)
	private String isSocialsecurity;

	/** 所属社区 */
	@InputColAnnotation(colCoord = 12)
	private String community;

	/** 联系电话 */
	@InputColAnnotation(colCoord = 13)
	private String tel;

	/** 待遇标准 */
	// @InputColAnnotation(colCoord=14,required=true,requiredErrorMsg="[待遇标准]不可为空")
	private String type;

	/** 征地名称 */
	@InputColAnnotation(colCoord = 14)
	private String proName;

	/** 操作人id */
	private UUID userId;

	/** 创建人的标识 */
	private String createUid;
	/** 创建时间 */
	private Date createDate;

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public SocialsecurityInfo toSocialsecurityInfo() {
		SocialsecurityInfo info = new SocialsecurityInfo();
		info.setId(UUID.randomUUID());
		info.setDel(false);
		info.setCommunity(community);
		info.setEndowmentInsuranceYear(endowmentInsuranceYear);
		info.setJoinWhichMedicalInsurance(joinWhichMedicalInsurance);
		info.setMedicalInsuranceMonth(medicalInsuranceMonth);
		info.setOprDate(new Date());
		info.setOprUserId(userId);
		info.setTypeStr(type);
		info.setIdNumber(idNumber);
		info.setPrisonTime(prisonTime);
		info.setSocialsecurityDate(new Date());
		info.setServeArmyTime(serveArmyTime);
		info.setIsSocialsecurity(getSS(isSocialsecurity));
		info.setCreateDate(createDate);
		info.setCreateUid(createUid);
		return info;
	}

	private boolean getSS(String ss) {
		if (ss != null && ss.trim().equals("是")) {
			return true;
		}
		return false;
	}

	public void setCreateUid(String createUid) {
		this.createUid = createUid;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
