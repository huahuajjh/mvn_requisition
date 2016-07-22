package com.tq.requisition.autoMapper;

import com.tq.requisition.domain.model.housePuraseTicket.HPTReportOfLossInfo;
import com.tq.requisition.presentation.dto.hpt.HPTLossInfoDto;

/**
 * 购房券挂失dto映射
 * @author jjh
 * @time 2015-01-02 22:12
 *
 */
public class HPTLossMapper {
	public static HPTReportOfLossInfo toModel(HPTLossInfoDto dto) {
		HPTReportOfLossInfo model = new HPTReportOfLossInfo(
				null,
				dto.getReportOfLossDate(),
				dto.getOprUserId(),
				dto.getOprDate(),
				dto.getFmlItemId(),
				dto.getTicketId());
		model.setRemark(dto.getRemark());
		return model;
	}
	
	public static HPTLossInfoDto toDto(HPTReportOfLossInfo model) {
		HPTLossInfoDto dto = new HPTLossInfoDto();
		dto.setFmlItemId(model.getFmlItemId());
		dto.setOprDate(model.getOprDate());
		dto.setOprUserId(model.getOprUserId());
		dto.setReportOfLossDate(model.getReportOfLossDate());
		dto.setTicketId(model.getTicketId());
		dto.setId(model.getId());
		dto.setDel(model.getDel());
		return dto;
	}
}
