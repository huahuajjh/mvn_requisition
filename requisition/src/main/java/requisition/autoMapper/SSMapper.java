package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.socialsecurity.SocialsecurityInfo;
import com.tq.requisition.presentation.dto.socialsecurityMgt.NewSocialsecurityDto;

/**
 * 社保dto，用于新增社保信息
 * @author jjh
 * @time 2015-12-31 21:37
 */
public class SSMapper {
	public static SocialsecurityInfo toModel(NewSocialsecurityDto dto) {
		SocialsecurityInfo info = new SocialsecurityInfo();
		info.setCommunity(dto.getCommunity());
		info.setDel(dto.isDel());		
		info.setEndowmentInsuranceYear(dto.getEndowmentInsuranceYear());
		info.setFmlItemId(dto.getFmlItemId());
		info.setSocialsecurityDate(dto.getSocialsecurityDate());
		info.setIsSocialsecurity(dto.getIsSocialsecurity());
		info.setJoinWhichMedicalInsurance(dto.getJoinWhichMedicalInsurance());
		info.setMedicalInsuranceMonth(dto.getMedicalInsuranceMonth());
		info.setOprDate(dto.getOprDate());
		info.setPrisonTime(dto.getPrisonTime());
		info.setPrisonTime(dto.getPrisonTime());
		info.setServeArmyTime(dto.getServeArmyTime());
		info.setSocialsecurityTypeId(dto.getSocialsecurityTypeId());
		info.setOprUserId(dto.getOprUserId());
		info.setCreateDate(dto.getCreateDate());
		info.setCreateUid(dto.getCreateUid());
		return info;		
	}
	
	public static List<SocialsecurityInfo> toModelList(List<NewSocialsecurityDto> dtos) {
		List<SocialsecurityInfo> models = new ArrayList<SocialsecurityInfo>();
		for (NewSocialsecurityDto newSocialsecurityDto : dtos) {
			models.add(toModel(newSocialsecurityDto));
		}
		return models;
	}
}
