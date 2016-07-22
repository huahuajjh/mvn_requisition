package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.housePuraseTicket.HPTProviderInfo;
import com.tq.requisition.presentation.dto.hpt.HPTRecevieInfoDto;

/**
 * 购房券发放dto映射
 * @author jjh
 * @time 2015-01-02 21:53
 *
 */
public class HPTProvideMapper {
	public static HPTProviderInfo toModel(HPTRecevieInfoDto dto) {
		HPTProviderInfo model = new HPTProviderInfo(
				null,
			    dto.getTicketId(),
				dto.getOwnerId(),
				dto.getOprUserId(),
				dto.getOprDate(),
				dto.getEvidenceOfGetting(),				
				dto.getName(),
				dto.getIdNumber(),
				dto.getGettingDate()
				);
		model.setRemark(dto.getRemark());
		return model;
	}
	
	public static HPTRecevieInfoDto toDto(HPTProviderInfo model) {
		HPTRecevieInfoDto dto = new HPTRecevieInfoDto();
		dto.setEvidenceOfGetting(model.getEvidenceOfGetting());
		dto.setGettingDate(model.getGettingDate());
		dto.setIdNumber(model.getIdNumber());
		dto.setName(model.getName());
		dto.setOprDate(model.getOprDate());
		dto.setOprUserId(model.getOprUserId());
		dto.setTicketId(model.getTicketId());
		dto.setOwnerId(model.getOwnerId());
		dto.setDel(model.getDel());
		dto.setId(model.getId());
		return dto;
	}

	public static List<HPTProviderInfo> toModelList(List<HPTRecevieInfoDto> dtos) {
		List<HPTProviderInfo> models = new ArrayList<HPTProviderInfo>();
		for (HPTRecevieInfoDto hptRecevieInfoDto : dtos) {
			models.add(toModel(hptRecevieInfoDto));
		}
		return models;
	}
}
