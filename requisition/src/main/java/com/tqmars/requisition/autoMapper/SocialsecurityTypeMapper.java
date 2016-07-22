package com.tqmars.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tqmars.requisition.domain.model.socialsecurityType.SocialsecurityType;
import com.tqmars.requisition.presentation.dto.share.SocialsecurityTypeDto;

public class SocialsecurityTypeMapper {
	public static SocialsecurityType toModel(SocialsecurityTypeDto dto) {
		return SocialsecurityType.obtain(dto.getId(), dto.getName());
	}
	
	public static SocialsecurityTypeDto toDto(SocialsecurityType model) {
		return SocialsecurityTypeDto.obtain(model.getId(), model.getName());
	}
	
	public static List<SocialsecurityType> toModelList(List<SocialsecurityTypeDto> dtos) {
		List<SocialsecurityType> models = new ArrayList<SocialsecurityType>();
		for (SocialsecurityTypeDto socialsecurityTypeDto : dtos) {
			models.add(toModel(socialsecurityTypeDto));
		}
		return models;
	}
	
	
	public static List<SocialsecurityTypeDto> toDtoList(List<SocialsecurityType> models) {
		List<SocialsecurityTypeDto> dtos = new ArrayList<>();
		for (SocialsecurityType socialsecurityType : models) {
			dtos.add(toDto(socialsecurityType));
		}
		return dtos;	
	}
}