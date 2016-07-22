package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.organization.Organization;
import com.tq.requisition.presentation.dto.sysMgt.OrgDto;

public class OrgMapper {
	public static Organization toModel(OrgDto dto)
	{
		Organization org = new Organization();
		org.setDel(false);
		org.setId(dto.getId());
		org.setOrgNumber(dto.getOrgNumber());
		org.setOrgName(dto.getName());
		return org;
	}
	
	public static OrgDto toDto(Organization org)
	{
		OrgDto dto = new OrgDto(org.getOrgName(),org.getId(),org.getOrgNumber());
		return dto;
	}
	
	public static List<Organization> toModelList(List<OrgDto> dtoList) {		
		if(dtoList == null)
		{
			throw new NullPointerException("组织dto集合对象为null");
		}
		
		List<Organization> orgList = new ArrayList<Organization>();
		
		for (OrgDto orgDto : dtoList) {
			orgList.add(toModel(orgDto));
		}
		return orgList;
	}
	
	public static List<OrgDto> toDtoList(List<Organization> orgList) {
		if(orgList == null)
		{
			throw new NullPointerException("组织集合对象为null");
		}
		List<OrgDto> dtoList = new ArrayList<>();
		for (Organization organization : orgList) {
			dtoList.add(toDto(organization));
		}
		return dtoList;
	}
	
}
