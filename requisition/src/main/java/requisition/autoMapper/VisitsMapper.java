package com.tq.requisition.autoMapper;


import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.visits.Visits;
import com.tq.requisition.presentation.dto.visits.VisitsDto;

public class VisitsMapper {
	public static Visits toModel(VisitsDto dto) {
		Visits model = new Visits();
		model.setOtherMsg(dto.getOtherMsg());
		model.setProName(dto.getProName());
		model.setVisitMaterialPath(dto.getVisitMaterialPath());
		model.setVisitorAddr(dto.getVisitorAddr());
		model.setVisitorName(dto.getVisitorName());
		model.setVisitorTel(dto.getVisitorTel());
		model.setVisitProId(dto.getVisitProId());
		model.setVisitReason(dto.getVisitReason());
		model.setVisitTime(dto.getVisitTime());
		return model;
	}
	
	public static VisitsDto toDto(Visits model) {
		VisitsDto dto = new VisitsDto();
		dto.setId(model.getId());
		dto.setOtherMsg(model.getOtherMsg());
		dto.setProName(model.getProName());
		dto.setVisitMaterialPath(model.getVisitMaterialPath());
		dto.setVisitorAddr(model.getVisitorAddr());
		dto.setVisitorName(model.getVisitorName());
		dto.setVisitorTel(model.getVisitorTel());
		dto.setVisitProId(model.getVisitProId());
		dto.setVisitReason(model.getVisitReason());
		dto.setVisitTime(model.getVisitTime());
		return dto;
	}
	
	public static List<Visits> toModelList(List<VisitsDto> dtos) {
		List<Visits> models = new ArrayList<Visits>();
		for (VisitsDto visitsDto : dtos) {
			models.add(toModel(visitsDto));
		}
		return models;
	}
	
	public static List<VisitsDto> toDtoList(List<Visits> models) {
		List<VisitsDto> dtos = new ArrayList<VisitsDto>();
		for (Visits visits : models) {
			dtos.add(toDto(visits));
		}
		return dtos;
	}

}
