package com.tq.requisition.presentation.dto.rmHousehold;

import java.util.Date;

import com.excel.util.annotation.InputColAnnotation;

/**
 * 拆迁户家庭成员导出导入model
 * @author jjh
 * @time 2016-01-16 13:16
 *
 */
public class FmlItemImportAndExport {
	/** 姓名 */
	@InputColAnnotation(colCoord=1,requiredErrorMsg="[名称]是必填项")
	private String name;
	/** 身份证号 */
	@InputColAnnotation(colCoord=6,required=true,requiredErrorMsg="[身份证号]是必填项",only = true,onlyErrorMsg = "[身份证号]是必须唯一")
	private String idNumber;
	/** 出生年月 */
	@InputColAnnotation(colCoord=5,required=true,converErrorMsg="类型错误，[出生时间]是日期类型-格式为2016-01-01",requiredErrorMsg="[出生时间]是必填项")
	private Date birthday;
	/** 性别 */
	@InputColAnnotation(colCoord=4,required=true,requiredErrorMsg="[性别]是必填项")
	private String gender;
	/** 独身子女号 */
	@InputColAnnotation(colCoord=11)
	private String onlyChildNumber;
	/** 半边户 */
	@InputColAnnotation(colCoord=12)
	private String half;
	/** 与户主关系 */
	@InputColAnnotation(colCoord=2)
	private String relationshipStr;
	/** 户口性质 */
	@InputColAnnotation(colCoord=3)
	private String householdStr;
	/** 是否参加过社保 */
	@InputColAnnotation(colCoord=10)
	private String socialsecurity;
	/**受教育程度*/
	@InputColAnnotation(colCoord=7)
	private String educationLevel;
	/**在读情况*/	
	@InputColAnnotation(colCoord=8)
	private String currentEducationSituation;
	/**务农时间*/
	@InputColAnnotation(colCoord=9)
	private String farmingTime;
	/**服兵役，劳教等情况及时间*/
	@InputColAnnotation(colCoord=13)
	private String serveArmySituation;
	/**电话*/
	@InputColAnnotation(colCoord=14)
	private String tel;
	/**备注*/
	@InputColAnnotation(colCoord=15)
	private String remark;
	
	public String getName() {
		return name;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public Date getBirthday() {
		return birthday;
	}
	public String getGender() {
		return gender;
	}
	public String getOnlyChildNumber() {
		return onlyChildNumber;
	}
	public String isHalf() {
		return half;
	}
	public String getRelationshipStr() {
		return relationshipStr;
	}
	public String getHouseholdStr() {
		return householdStr;
	}
	public boolean isSocialsecurity() {
		return socialsecurity != null && socialsecurity.equals("是");
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
	
}
