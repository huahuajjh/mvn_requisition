package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.relationshipType.RelationshipType;
import com.tq.requisition.presentation.dto.share.RelationshipTypeDto;

public class RelationshipTypeMapper {
	public static RelationshipType toModel(RelationshipTypeDto dto) {
		return RelationshipType.obtain(dto.getId(), dto.getName());
	}
	
	public static RelationshipTypeDto toDto(RelationshipType model) {
		return RelationshipTypeDto.obtain(model.getId(), model.getName());
	}

	public static List<RelationshipType> toModelList(List<RelationshipTypeDto> dtos) {
		List<RelationshipType> models = new ArrayList<RelationshipType>();
		for (RelationshipTypeDto relationshipTypeDto : dtos) {
			models.add(toModel(relationshipTypeDto));
		}
		return models;
	}
	
	public static List<RelationshipTypeDto> toDtoList(List<RelationshipType> models) {
		List<RelationshipTypeDto> dtos =  new ArrayList<>();
		for (RelationshipType relationshipType : models) {
			dtos.add(toDto(relationshipType));
		}		
		return dtos;
	}
}
