package com.tq.requisition.presentation.dto.rmHousehold;

import java.util.ArrayList;
import java.util.List;

import com.excel.util.annotation.InputColAnnotation;
import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.domain.model.removeFamily.Family;
import com.tq.requisition.domain.model.share.Gender;

/**
 * 拆迁户导入模板
 * @author jjh
 * @time 2016-01-16 13:11
 *
 */
public class FmlImportAndExport {
	/**项目名称*/
	@InputColAnnotation(colCoord=1,required=true,rowCoord=1,requiredErrorMsg="[项目名称]是必填项")
	private String proName;
	/**户主姓名*/
	@InputColAnnotation(colCoord=5,required=true,rowCoord=1,requiredErrorMsg="[户主姓名]是必填项")
	private String headName;
	/**征地公告发布日期*/
	@InputColAnnotation(colCoord=7,rowCoord=1)
	private String announceDate;
	/**乡，镇(办事处)*/
	@InputColAnnotation(colCoord=11,rowCoord=1)
	private String town;
	/**村*/
	@InputColAnnotation(colCoord=13,rowCoord=1)
	private String contry;
	/**组*/
	@InputColAnnotation(colCoord=15,rowCoord=1)
	private String group;
	/**拆迁人员集合*/
	private List<FmlItemImportAndExport> items;

	public Family toFamily(List<FmlItemImportAndExport> list) {
		items = list;
		Family fml = new Family();
		String address = "";
		if(town != null && !town.equals("null")){
			address += town;
		}
		if(contry != null && !contry.equals("null")){
			address += contry;
		}
		if(group != null && !group.equals("null")){
			address += group;
		}
		fml.setAddress(address);
		fml.setFmlNumber(list.size());
		fml.setHeadName(headName);
		fml.setProName(proName);
		fml.setItems(getFmlItems(list));
		return fml;
	}
	
	private List<FamilyItem> getFmlItems(List<FmlItemImportAndExport> list){
		List<FamilyItem> items = new ArrayList<FamilyItem>();
		for (FmlItemImportAndExport dto : list) {
			FamilyItem item = new FamilyItem();
			String address = "";
			if(town != null && !town.equals("null")){
				address += town;
			}
			if(contry != null && !contry.equals("null")){
				address += contry;
			}
			if(group != null && !group.equals("null")){
				address += group;
			}
			item.setAddress(address);
			item.setBirthday(dto.getBirthday());
			item.setCurrentEducationSituation(dto.getCurrentEducationSituation());
			item.setEducationLevel(dto.getEducationLevel());
			item.setFarmingTime(dto.getFarmingTime());
			item.setGender(Gender.FEMALE.obtainByStr(dto.getGender()));
			item.setHalf(getHalf(dto.isHalf()));
			item.setHouseholdStr(dto.getHouseholdStr());
			item.setIdNumber(dto.getIdNumber());
			item.setName(dto.getName());
			item.setOnlyChildNumber(dto.getOnlyChildNumber());
			item.setProName(proName);
			item.setRelationshipStr(dto.getRelationshipStr());
			item.setServeArmySituation(dto.getServeArmySituation());
			item.setTel(dto.getTel());
			item.setIsSocialsecurity(dto.isSocialsecurity());
			item.setRemark(dto.getRemark());
			items.add(item);
		}
		return items;
	}
	
	boolean getHalf(String half)
	{
		if(half != null && half.trim().equals("是"))
		{
			return true;
		}
		return false;
	}
}
