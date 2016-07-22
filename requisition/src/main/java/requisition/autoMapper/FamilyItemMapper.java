package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemDto;

public class FamilyItemMapper {
	public static FamilyItem toModel(FamilyItemDto dto) {
		FamilyItem item = new FamilyItem();
		item.setAddress(dto.getAddress());
		item.setBirthday(dto.getBirthday());
		item.setCommunityId(dto.getCommunityId());
		item.setCurrentEducationSituation(dto.getCurrentEducationSituation());
		item.setEducationLevel(dto.getEducationLevel());
		item.setFarmingTime(dto.getFarmingTime());
		item.setFmlId(dto.getFmlId());
		item.setGender(dto.getGender());
		item.setGroupId(dto.getGroupId());
		item.setHalf(dto.isHalf());
		item.setHouseholdId(dto.getHouseholdId());
		item.setHouseholdStr(dto.getHouseholdStr());
		item.setIdNumber(dto.getIdNumber());
		item.setName(dto.getName());
		item.setOnlyChildNumber(dto.getOnlyChildNumber());
		item.setProId(dto.getProId());
		item.setProName(dto.getProName());
		item.setRelationshipId(dto.getRelationshipId());
		item.setRelationshipStr(dto.getRelationshipStr());
		item.setRemark(dto.getRemark());
		item.setRemoved(dto.getRemoved());
		item.setServeArmySituation(dto.getServeArmySituation());
		item.setSocialsecurity(dto.isSS());
		item.setSocialsecurityStr(dto.getSocialsecurityStr());
		item.setSocialsecurityTypeId(dto.getSocialsecurityTypeId());
		item.setStreetId(dto.getStreetId());
		item.setTel(dto.getTel());
		item.setTransfer(dto.getTransfer());
		item.setIsSocialsecurity(dto.getUserdSocialsecurity());
		item.setOtherRelationship(dto.getOtherRelationship());
		return item;		
	}
	
	public static FamilyItemDto toDto(FamilyItem model) {
		FamilyItemDto dto = new FamilyItemDto();
		dto.setId(model.getId());
		dto.setAddress(model.getAddress());
		dto.setBirthday(model.getBirthday());
		dto.setCommunityId(model.getCommunityId());
		dto.setCurrentEducationSituation(model.getCurrentEducationSituation());
		dto.setEducationLevel(model.getEducationLevel());
		dto.setFarmingTime(model.getFarmingTime());
		dto.setFmlId(model.getFmlId());
		dto.setGender(model.getGender());
		dto.setGroupId(model.getGroupId());
		dto.setHalf(model.getHalf());
		dto.setHouseholdId(model.getHouseholdId());
		dto.setHouseholdStr(model.getHouseholdStr());
		dto.setIdNumber(model.getIdNumber());
		dto.setName(model.getName());
		dto.setOnlyChildNumber(model.getOnlyChildNumber());
		dto.setProId(model.getProId());
		dto.setProName(model.getProName());
		dto.setRelationshipId(model.getRelationshipId());
		dto.setRelationshipStr(model.getRelationshipStr());
		dto.setRemark(model.getRemark());
		dto.setRemoved(model.getRemoved());
		dto.setServeArmySituation(model.getServeArmySituation());
		dto.setSS(model.getSocialsecurity());
		dto.setSocialsecurityStr(model.getSocialsecurityStr());
		dto.setSocialsecurityTypeId(model.getSocialsecurityTypeId());
		dto.setStreetId(model.getStreetId());
		dto.setTel(model.getTel());
		dto.setTransfer(model.getTransfer());
		dto.setUserdSocialsecurity(model.getIsSocialsecurity());
		dto.setOtherRelationship(model.getOtherRelationship());
		return dto;
	}

	public static List<FamilyItem> toModelList(List<FamilyItemDto> dtos) {
		List<FamilyItem> models = new ArrayList<FamilyItem>();
		for (FamilyItemDto familyItemDto : dtos) {
			models.add(toModel(familyItemDto));
		}
		return models;		
	}
	
	public static List<FamilyItemDto> toDtoList(List<FamilyItem> models) {
		List<FamilyItemDto> dtos = new ArrayList<>();
		 for (FamilyItem familyItem : models) {
			dtos.add(toDto(familyItem));
		}
		 return dtos;
	}
	
}
