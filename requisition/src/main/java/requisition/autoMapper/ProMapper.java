package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.project.Project;
import com.tq.requisition.domain.model.project.ProjectType;
import com.tq.requisition.presentation.dto.project.ProjectDto;

/**
 * 项目dto与领域model转换
 * 
 * @author jjh
 * @time 2015-12-28 3:23
 */
public class ProMapper {
	public static Project toModel(ProjectDto dto) {
		Project model = new Project();
		model.setProName(dto.getProName());
		model.setApprovalNumber(dto.getApprovalNumber());
		model.setRequisitionArea(dto.getRequisitionArea());
		model.setShouldRemoveBuildings(dto.getShouldRemoveBuildings());
		model.setShouldRemoveHouses(dto.getShouldRemoveHouses());
		model.setShouldRemoveLegalArea(dto.getShouldRemoveLegalArea());
		model.setShouldRemoveIllegalArea(dto.getShouldRemoveIllegalArea());
		model.setShouldMovePopulation(dto.getShouldMovePopulation());
		model.setShouldPayMoney(dto.getShouldPayMoney());
		model.setTotalPayMoney(dto.getTotalPayMoney());
		model.setStartDate(dto.getStartDate());
		model.setNewStart(dto.isNewStart());
		model.setProType(dto.getProType());
		model.setProTypeStr(ProjectType.INFRASTRUCTURE.toStr(dto.getProType()));
		model.setTotalAddress(dto.getAddress());
		model.setStreetId(dto.getStreetId());
		model.setCommunityId(dto.getCommunityId());
		model.setSequence(dto.getS());
		model.setSequenceStr(dto.getAnnounceName());
		model.setRequisitionLandAreaTotal(dto.getRequisitionLandAreaTotal());
		model.setRequisitionLandAreaYear(dto.getRequisitionLandAreaYear());
		model.setRemovedBuildingsLegalTotal(dto.getRemovedBuildingsLegalTotal());
		model.setRemovedBuildingsLegalYear(dto.getRemovedBuildingsLegalYear());
		model.setRemovedHousesLegalTotal(dto.getRemovedHousesLegalTotal());
		model.setRemovedHousesLegalYear(dto.getRemovedHousesLegalYear());
		model.setRemovedAreaLegalTotal(dto.getRemovedAreaLegalTotal());
		model.setRemovedAreaLegalYear(dto.getRemovedAreaLegalYear());
		model.setRemovedAreaIllegalTotal(dto.getRemovedAreaIllegalTotal());
		model.setRemovedAreaIllegalYear(dto.getRemovedAreaIllegalYear());
		model.setRemovedPopulationTotal(dto.getRemovedPopulationTotal());
		model.setRemovedPopulationYear(dto.getRemovedPopulationYear());
		model.setSixForward(dto.getSixForward());
		model.setCategoryId(dto.getCategoryId());
		model.setCategoryStr(dto.getCategoryStr());
//		model.setCurMonthComplete(dto.getCurMonthComplete());		
		return model;
	}

	public static ProjectDto toDto(Project model) {
		ProjectDto dto = new ProjectDto();
		dto.setAddress(model.getTotalAddress());
		dto.setAnnounceName(model.getSequenceStr());
		dto.setApprovalNumber(model.getApprovalNumber());
		dto.setCommunityId(model.getCommunityId());
		dto.setId(model.getId());
		dto.setNewStart(model.getNewStart());
		dto.setProName(model.getProName());
		dto.setProType(model.getProType());
		dto.setProTypeStr(model.getProTypeStr());
		dto.setS(model.getSequence());
		dto.setShouldMovePopulation(model.getShouldMovePopulation());
		dto.setShouldPayMoney(model.getShouldPayMoney());
		dto.setShouldRemoveBuildings(model.getShouldRemoveBuildings());
		dto.setShouldRemoveHouses(model.getShouldRemoveHouses());
		dto.setShouldRemoveIllegalArea(model.getShouldRemoveIllegalArea());
		dto.setShouldRemoveLegalArea(model.getShouldRemoveIllegalArea());
		dto.setStreetId(model.getStreetId());
		dto.setStartDate(model.getStartDate());
		dto.setTotalPayMoney(model.getTotalPayMoney());
		dto.setRemovedAreaIllegalTotal(model.getRemovedAreaIllegalTotal());
		dto.setRemovedAreaIllegalYear(model.getRemovedAreaIllegalYear());
		dto.setRemovedAreaLegalTotal(model.getRemovedAreaLegalTotal());
		dto.setRemovedAreaLegalYear(model.getRemovedAreaLegalTotal());
		dto.setRemovedBuildingsLegalTotal(model.getRemovedBuildingsLegalTotal());
		dto.setRemovedBuildingsLegalYear(model.getRemovedBuildingsLegalYear());
		dto.setRemovedHousesLegalTotal(model.getRemovedHousesLegalTotal());
		dto.setRemovedHousesLegalYear(model.getRemovedHousesLegalYear());
		dto.setRemovedPopulationTotal(model.getRemovedPopulationTotal());
		dto.setRemovedPopulationYear(model.getRemovedPopulationYear());
		dto.setRequisitionArea(model.getRequisitionArea());
		dto.setRequisitionLandAreaTotal(model.getRequisitionLandAreaTotal());
		dto.setRequisitionLandAreaYear(model.getRequisitionLandAreaYear());
		dto.setSixForward(model.getSixForward());
		dto.setCategoryId(model.getCategoryId());
		dto.setCategoryStr(model.getCategoryStr());
		dto.setCurMonthComplete(model.getCurMonthComplete());
		dto.setMoneyUnit(model.getMoneyUnit());
		dto.setOtherMoneyUnit(model.getOtherMoneyUnit());
		return dto;
	}

	public static List<ProjectDto> toDtoList(List<Project> models) {
		List<ProjectDto> dtos = new ArrayList<ProjectDto>();
		for (Project project : models) {
			dtos.add(toDto(project));
		}
		return dtos;
	}

	public static List<Project> toModeList(List<ProjectDto> dtos) {
		List<Project> models = new ArrayList<>();
		for (ProjectDto projectDto : dtos) {
			models.add(toModel(projectDto));
		}
		return models;
	}
}
