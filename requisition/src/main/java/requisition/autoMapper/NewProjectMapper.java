package com.tq.requisition.autoMapper;

import com.tq.requisition.domain.model.project.Project;
import com.tq.requisition.presentation.dto.project.NewProDto;

/**
 * 項目dto與model映射
 * @author jjh
 * @time 2015-12-27 22:32
 */
public final class NewProjectMapper {
	public static Project toModel(NewProDto dto) {
		Project model = new Project();
		model.setApprovalNumber(dto.getApprovalNumber());
		model.setProName(dto.getProName());
		model.setRequisitionArea(dto.getRequisitionArea());
		model.setShouldRemoveBuildings(dto.getShouldRemoveBuildings());
		model.setShouldRemoveHouses(dto.getShouldRemoveHouses());
		model.setShouldRemoveLegalArea(dto.getShouldRemoveLegalArea());
		model.setShouldRemoveIllegalArea(dto.getShouldRemoveIllegalArea());
		model.setShouldMovePopulation(dto.getShouldMovePopulation());
		model.setShouldPayMoney(dto.getShouldPayMoney());
		model.setProType(dto.getProType());
		model.setTotalAddress(dto.getAddress());
		model.setStreetId(dto.getStreet());
		model.setCommunityId(dto.getCommunity());
		model.setSixForward(dto.getSixForwardPro());
		model.setCategoryStr(dto.getProCategory());
		model.setCreateDate(dto.getCreateDate());
		model.setCreateUid(dto.getCreateUid());
		return model;
	}
}
