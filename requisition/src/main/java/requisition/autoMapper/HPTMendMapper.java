package com.tq.requisition.autoMapper;

import com.tq.requisition.domain.model.housePuraseTicket.HPTMendInfo;
import com.tq.requisition.presentation.dto.hpt.HPTMendInfoDto;

/**
 * 购房券补券dto映射
 * @author jjh
 * @time 2015-01-02 21:34
 *
 */
public class HPTMendMapper {
	public static HPTMendInfo toModel(HPTMendInfoDto dto) {
		HPTMendInfo model = new HPTMendInfo();
		model.setMendDate(dto.getMendDate());
		model.setOnwerId(dto.getOnwerId());
		model.setOprDate(dto.getOprDate());
		model.setOprUserId(dto.getOprUserId());
		model.setTicketId(dto.getTicketId());
		return model;
	}
	
	public static HPTMendInfoDto toDto(HPTMendInfo model) {
		HPTMendInfoDto dto = new HPTMendInfoDto();
		dto.setDel(model.getDel());
		dto.setId(model.getId());
		dto.setMendDate(model.getMendDate());
		dto.setOnwerId(model.getOnwerId());
		dto.setOprDate(model.getOprDate());
		dto.setOprUserId(model.getOprUserId());
		dto.setTicketId(model.getTicketId());		
		return dto;
	}
}
