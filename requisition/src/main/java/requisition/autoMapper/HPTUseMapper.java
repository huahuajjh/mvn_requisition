package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.housePuraseTicket.HPTUseAndCash;
import com.tq.requisition.presentation.dto.hpt.HPTUseAndCashInfoDto;

public class HPTUseMapper {
	public static HPTUseAndCash toModel(HPTUseAndCashInfoDto dto) {
		HPTUseAndCash model = new HPTUseAndCash();
		model.setEvidencePath(dto.getEvidencePath());
		model.setOprDate(dto.getOprDate());
		model.setOprUserId(dto.getOprUserId());
		model.setOwnerId(dto.getOwnerId());
		model.setSituationExplain(dto.getSituationExplain());
		model.setTicketId(dto.getTicketId());
		model.setUsingDate(dto.getUsingDate());
		model.setUsingToWhere(dto.getUsingToWhere());
		model.setUsingType(dto.getUsingType());
		model.setImage(dto.getImage());
		return model;
	}
	
	public static HPTUseAndCashInfoDto toDto(HPTUseAndCash model) {
		HPTUseAndCashInfoDto dto = new HPTUseAndCashInfoDto();
		dto.setId(model.getId());
		dto.setDel(model.getDel());
		dto.setEvidencePath(model.getEvidencePath());
		dto.setOprDate(model.getOprDate());
		dto.setOprUserId(model.getOprUserId());
		dto.setOwnerId(model.getOwnerId());
		dto.setSituationExplain(model.getSituationExplain());
		dto.setTicketId(model.getTicketId());
		dto.setUsingDate(model.getUsingDate());
		dto.setUsingToWhere(model.getUsingToWhere());
		dto.setUsingType(model.getUsingType());
		dto.setImage(model.getImage());
		return dto;
	}
	
	public static List<HPTUseAndCashInfoDto> toDtoList(List<HPTUseAndCash> models) {
		List<HPTUseAndCashInfoDto> dtos = new ArrayList<>(); 
		for (HPTUseAndCash hptUseAndCash : models) {
			dtos.add(toDto(hptUseAndCash));
		}
		return dtos;
	}
	
	public static List<HPTUseAndCash> toModelList(List<HPTUseAndCashInfoDto> dtos) {
		List<HPTUseAndCash> models = new ArrayList<>();
		for (HPTUseAndCashInfoDto hptUseAndCashInfoDto : dtos) {
			models.add(toModel(hptUseAndCashInfoDto));
		}
		return models;
	}
}
