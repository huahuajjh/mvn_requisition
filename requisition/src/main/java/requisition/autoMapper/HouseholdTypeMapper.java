package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.householdType.HouseholdType;
import com.tq.requisition.presentation.dto.share.HouseholdTypeDto;

public class HouseholdTypeMapper {
	public static HouseholdType toModel(HouseholdTypeDto dto) {
		return HouseholdType.obtain(dto.getId(), dto.getName());
	}
	
	public static HouseholdTypeDto toDto(HouseholdType model) {
		return HouseholdTypeDto.obtain(model.getId(), model.getName());
	}
	
	public static List<HouseholdType> toModelList(List<HouseholdTypeDto> dtos) {
		List<HouseholdType> models = new ArrayList<HouseholdType>();
		for (HouseholdTypeDto householdTypeDto : dtos) {
			models.add(toModel(householdTypeDto));
		}
		return models;
	}
	
	public static List<HouseholdTypeDto> toDtoList(List<HouseholdType> models) {
		List<HouseholdTypeDto> dtos = new ArrayList<>();
		for (HouseholdType householdType : models) {
			dtos.add(toDto(householdType));
		}
		return dtos;
	}
	
}
