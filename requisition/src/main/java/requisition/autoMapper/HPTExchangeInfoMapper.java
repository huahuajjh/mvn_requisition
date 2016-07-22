package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.housePuraseTicket.HPTExchangeInfo;
import com.tq.requisition.presentation.dto.hpt.HPTExchangeInfoDto;

/**
 * 购房券换券dto映射
 * @author jjh
 * @time 2015-01-02 21:24
 *
 */
public class HPTExchangeInfoMapper {
	public static HPTExchangeInfo toModel(HPTExchangeInfoDto dto) {
		HPTExchangeInfo model = new HPTExchangeInfo();
		model.setDel(dto.isDel());
		model.setEvidencePath(dto.getEvidencePath());
		model.setExchengeDate(dto.getExchengeDate());
		model.setGettingUser(dto.getGettingUser());
		model.setNewTicketId(dto.getNewTicketId());
		model.setOldTicketId(dto.getOldTicketId());
		model.setOprDate(dto.getOprDate());
		model.setOprUserId(dto.getOprUserId());
		model.setOwnerId(dto.getOwnerId());
		return model;
	}
	
	public static HPTExchangeInfoDto toDto(HPTExchangeInfo model) {
		HPTExchangeInfoDto dto = new HPTExchangeInfoDto();
		dto.setDel(model.getDel());
		dto.setEvidencePath(model.getEvidencePath());
		dto.setExchengeDate(model.getExchengeDate());
		dto.setGettingUser(model.getGettingUser());
		dto.setNewTicketId(model.getNewTicketId());
		dto.setOldTicketId(model.getOldTicketId());
		dto.setOprDate(model.getOprDate());
		dto.setOprUserId(model.getOprUserId());
		dto.setOwnerId(model.getOwnerId());
		return dto;
	}
	
	public static List<HPTExchangeInfoDto> toDtoList(List<HPTExchangeInfo> models) {
		List<HPTExchangeInfoDto> dtos = new ArrayList<HPTExchangeInfoDto>();
		for (HPTExchangeInfo hptExchangeInfo : models) {
			dtos.add(toDto(hptExchangeInfo));
		}
		return dtos;
	}
	
	public static List<HPTExchangeInfo> toModelList(List<HPTExchangeInfoDto> dtos) {
		List<HPTExchangeInfo> models = new ArrayList<>();
		for (HPTExchangeInfoDto hptExchangeInfoDto : dtos) {
			models.add(toModel(hptExchangeInfoDto));
		}
		return models;
	}
	
}
