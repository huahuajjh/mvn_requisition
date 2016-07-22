package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.housePuraseTicket.HousePuraseTicket;
import com.tq.requisition.presentation.dto.hpt.HousePuraseTicketDto;

/**
 * 购房券dto映射
 * @author jjh
 * @time 2015-01-02 21:04
 *
 */
public class HPTMapper {
	public static HousePuraseTicket toModel(HousePuraseTicketDto dto) {
		HousePuraseTicket model = new HousePuraseTicket();
		model.setBonus(dto.getBonus());
		model.setFmlItemId(dto.getFmlItemId());
		model.setIdNumber(dto.getIdNumber());
		model.setMakeDate(dto.getMakeDate());
		model.setName(dto.getName());
		model.setState(dto.getState());
		model.setDel(dto.getDel());
		model.setTicketNumber(dto.getTicketNumber());
		model.setCreateDate(dto.getCreateDate());
		model.setCreateUId(dto.getCreateUId());
		return model;
	}
	
	public static HousePuraseTicketDto toDto(HousePuraseTicket model) {
		HousePuraseTicketDto dto = new HousePuraseTicketDto();
		dto.setBonus(model.getBonus());
		dto.setDel(model.getDel());
		dto.setFmlItemId(model.getFmlItemId());
		dto.setId(model.getId());
		dto.setIdNumber(model.getIdNumber());
		dto.setMakeDate(model.getMakeDate());
		dto.setName(model.getName());
		dto.setState(model.getState());
		dto.setTicketNumber(model.getTicketNumber());
		return dto;
	}
	
	public static List<HousePuraseTicket> toModelList(List<HousePuraseTicketDto> dtos) {
		List<HousePuraseTicket> models = new ArrayList<HousePuraseTicket>();
		for (HousePuraseTicketDto housePuraseTicketDto : dtos) {
			models.add(toModel(housePuraseTicketDto));
		}
		return models;
	}
	
	public static List<HousePuraseTicketDto> toDtoList(List<HousePuraseTicket> models) {
		List<HousePuraseTicketDto> dtos = new ArrayList<>();
		for (HousePuraseTicket housePuraseTicket : models) {
			dtos.add(toDto(housePuraseTicket));
		}
		return dtos;
	}
	
}
