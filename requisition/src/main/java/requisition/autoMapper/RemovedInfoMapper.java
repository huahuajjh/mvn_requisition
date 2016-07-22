package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.removedInfo.RemovedInfo;
import com.tq.requisition.presentation.dto.removedinfo.RemovedInfoDto;

/**
 * 已迁户档案对象mapper
 * @author jjh
 * @time 2016-01-12 20:24
 *
 */
public class RemovedInfoMapper {
	public static RemovedInfo toModel(RemovedInfoDto dto) {
		RemovedInfo model = new RemovedInfo();
		model.setAddress(dto.getAddress());
		model.setBirthDay(dto.getBirthDay());
		model.setCommunityId(dto.getCommunityId());
		model.setDel(dto.isDel());
		model.setIdNumber(dto.getIdNumber());
		model.setName(dto.getName());
		model.setRemoveDate(dto.getRemoveDate());
		model.setStreetId(dto.getStreetId());
		return model;
	}
	
	public static RemovedInfoDto toDto(RemovedInfo model) {
		RemovedInfoDto dto = new RemovedInfoDto();
		dto.setAddress(model.getAddress());
		dto.setBirthDay(model.getBirthDay());
		dto.setCommunityId(model.getCommunityId());
		dto.setDel(model.getDel());
		dto.setId(model.getId());
		dto.setIdNumber(model.getIdNumber());
		dto.setName(model.getName());
		dto.setRemoveDate(model.getRemoveDate());
		dto.setStreetId(model.getStreetId());
		return dto;
	}
	
	public static List<RemovedInfoDto> toDtoList(List<RemovedInfo> models) {
		List<RemovedInfoDto> dtos = new ArrayList<RemovedInfoDto>();
		for (RemovedInfo removedInfo : models) {
			dtos.add(toDto(removedInfo));
		}
		return dtos;
	}
	
	public static List<RemovedInfo> toModelList(List<RemovedInfoDto> dtos) {
		List<RemovedInfo> models = new ArrayList<>();
		for (RemovedInfoDto removedInfoDto : dtos) {
			models.add(toModel(removedInfoDto));
		}
		return models;
	}
	
}
