package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.registedAgricultureInfo.RegistedAgricultureInfo;
import com.tq.requisition.presentation.dto.registedAgr.RegistedAgricultureInfoDto;

public class RegistedAgricMapper {
	public static RegistedAgricultureInfo toModel(RegistedAgricultureInfoDto dto) {
		RegistedAgricultureInfo model = new RegistedAgricultureInfo();
		model.setAddress(dto.getAddress());
		model.setDel(dto.isDel());
		model.setIdNumber(dto.getIdNumber());
		model.setIsRemove(dto.isRemove());
		model.setIsSetting(dto.isSetting());
		model.setIsSocialSecurity(dto.isSocialSecurity());
		model.setIsTransfer(dto.isTransfer());
		model.setName(dto.getName());
		model.setPolicyStateId(dto.getPolicyStateId());
		model.setUserStateId(dto.getUserStateId());
		model.setUserStateStr(dto.getUserStateStr());
		model.setPolicyStr(dto.getPolicyStr());
		return model;
	}
	
	public static RegistedAgricultureInfoDto toDto(RegistedAgricultureInfo model) {
		RegistedAgricultureInfoDto dto = new RegistedAgricultureInfoDto();
		dto.setAddress(model.getAddress());
		dto.setDel(model.getDel());
		dto.setIdNumber(model.getIdNumber());
		dto.setRemove(model.getIsRemove());
		dto.setSetting(model.getIsSetting());
		dto.setSocialSecurity(model.getIsSocialSecurity());
		dto.setTransfer(model.getIsTransfer());
		dto.setName(model.getName());
		dto.setPolicyStateId(model.getPolicyStateId());
		dto.setUserStateId(model.getUserStateId());
		dto.setUserStateStr(model.getUserStateStr());
		dto.setPolicyStr(model.getPolicyStr());
		dto.setId(model.getId());
		return dto;
	}
	
	public static List<RegistedAgricultureInfoDto> toDtoList(List<RegistedAgricultureInfo> models) {
		List<RegistedAgricultureInfoDto> dtos = new ArrayList<RegistedAgricultureInfoDto>();
		for (RegistedAgricultureInfo registedAgricultureInfo : models) {
			dtos.add(toDto(registedAgricultureInfo));
		}
		return dtos;
	}
	
	public static List<RegistedAgricultureInfo> toModelList(List<RegistedAgricultureInfoDto> dtos) {
		List<RegistedAgricultureInfo> models = new ArrayList<>();
		for (RegistedAgricultureInfoDto registedAgricultureInfoDto : dtos) {
			models.add(toModel(registedAgricultureInfoDto));
		}
		return models;
	}
}
