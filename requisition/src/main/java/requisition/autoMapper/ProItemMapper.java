package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.project.ProjectItem;
import com.tq.requisition.presentation.dto.project.ProItemDto;

/**
 * 项目月度dto与领域model转换
 * 
 * @author jjh
 * @time 2015-12-28 13:27
 */
public class ProItemMapper {
	public static ProjectItem toModel(ProItemDto dto) {
		ProjectItem item = new ProjectItem();
		item.setDate(dto.getDate());
		item.setRemovedLandArea(dto.getRemovedLandArea());
		item.setRemovedBuildings(dto.getRemovedBuildings());
		item.setRmovedHouses(dto.getRmovedHouses());
		item.setRemovedIllegalArea(dto.getRemovedIllegalArea());
		item.setMovedPopulation(dto.getMovedPopulation());
		item.setYearDeadlineFile(dto.getYearDeadlineFile());
		item.setYearCourtExecute(dto.getYearCourtExecute());
		item.setYearLegalRemoved(dto.getYearLegalRemoved());
		item.setRemark(dto.getRemark());
		item.setNewStart(dto.isNewStart());
		item.setProId(dto.getProId());
		item.setStartDate(dto.getStartDate());
		item.setPaidMoney(dto.getPaidMoney());
		item.setCurMonthComplete(dto.getCurMonthComplete());
		item.setRemovedLegalArea(dto.getRemovedLegalArea());
		return item;
	}
	
	public static ProItemDto toDto(ProjectItem model) {
		ProItemDto dto = new ProItemDto();
		dto.setId(model.getId());
		dto.setDate(model.getDate());
		dto.setRemovedLandArea(model.getRemovedLandArea()); 
		dto.setRemovedBuildings(model.getRemovedBuildings());
		dto.setRmovedHouses(model.getRmovedHouses()); 
		dto.setRemovedLegalArea(model.getRemovedLegalArea()); 
		dto.setRemovedIllegalArea(model.getRemovedIllegalArea());
		dto.setMovedPopulation(model.getMovedPopulation());
		dto.setPaidMoney(model.getPaidMoney()); 
		dto.setYearDeadlineFile(model.getYearDeadlineFile()); 
		dto.setYearCourtExecute(model.getYearCourtExecute());
		dto.setYearLegalRemoved(model.getYearLegalRemoved()); 
		dto.setRemark(model.getRemark()); 
		dto.setNewStart(model.getNewStart());
		dto.setProId(model.getProId());
		dto.setStartDate(model.getStartDate());
		dto.setCurMonthComplete(model.getCurMonthComplete());
		return dto;
	}
	
	public static List<ProjectItem> toModelList(List<ProItemDto> dtos) {
		List<ProjectItem> models = new ArrayList<ProjectItem>();
		for (ProItemDto proItemDto : dtos) {
			models.add(toModel(proItemDto));
		}
		return models;
	}
	
	public static List<ProItemDto> toDtoList(List<ProjectItem> models) {
		List<ProItemDto> dtos = new ArrayList<ProItemDto>();
		for (ProjectItem projectItem : models) {
			dtos.add(toDto(projectItem));
		}
		return dtos;
	}
	
}
