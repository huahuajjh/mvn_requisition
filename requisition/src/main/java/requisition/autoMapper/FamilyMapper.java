package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.removeFamily.Family;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyDto;

public class FamilyMapper {
	public static FamilyDto toDto(Family model) {
		return FamilyDto.obtain(//
				model.getId(), //
				model.getHeadName(), //
				model.getProId(), //
				model.getHeadId(),//
				model.getStreetId(), //
				model.getCommunityId(),//
				model.getAddress(),//
				model.getFmlNumber(),//
				model.getHouseLegalArea(), //
				model.getHouseIllegalArea(), //
				model.getSatuationDesc(),//
				model.getDealSolution(), //
				model.getUnionSuggestion(), //
				model.getRemark(), //
				model.getHouseImgPath(),//
				null,//
				model.getProName(),//
				model.getGroupId(),
				model.getUnionSuggestionPath());
	}

	public static Family toModel(FamilyDto dto) {
		return Family.obtain(//
				dto.getHeadName(),//
				dto.getProId(), //
				dto.getHeadId(), //
				dto.getStreetId(),//
				dto.getCommunityId(), //
				dto.getAddress(), //
				dto.getFmlNumber(), //
				dto.getHouseLegalArea(),//
				dto.getHouseIllegalArea(), //
				dto.getSatuationDesc(), //
				dto.getDealSolution(), //
				dto.getUnionSuggestion(), //
				dto.getRemark(), //
				dto.getHouseImgPath(),//
				FamilyItemMapper.toModelList(dto.getItems()),//
				dto.getProName(),//
				dto.getGroupId(),
				dto.getUnionSuggestionPath(),
				dto.getImage(),
				dto.getCreateUid(),
				dto.getCreateDate());
	}
	
	public static List<Family> toModelList(List<FamilyDto> dtos) {
		List<Family> models = new ArrayList<Family>();
		for (FamilyDto familyDto : dtos) {
			models.add(toModel(familyDto));
		}
		return models;
	}
	
	public static List<FamilyDto> toDtoList(List<Family> models) {
		List<FamilyDto> dtos = new ArrayList<>();
		for (Family family : models) {
			dtos.add(toDto(family));
		}
		return dtos;
	}
}
