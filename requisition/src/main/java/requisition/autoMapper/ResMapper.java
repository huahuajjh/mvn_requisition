package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.resource.Resource;
import com.tq.requisition.presentation.dto.sysMgt.ResDto;

public final class ResMapper {
	
	public static Resource toModel(ResDto dto)
	{
		Resource res = Resource.obtain(//
				dto.getName(),//资源名称
				dto.getLink(),//资源链接
				"NA",//资源icon
				dto.getType(),//资源类型
				dto.getOrder(), //资源排序
				dto.getpId(), //资源父id
				true, //是否有子节点
				dto.getPath(),//资源祖先序列
				dto.getHtmlId(), //资源hemlid
				dto.getHtml(),//资源html
				dto.getHierarchy());
		return res;
	}
	
	public static ResDto toDto(Resource res)
	{
		ResDto dto = ResDto.obtain(//
				res.getId(),//
				res.getTitle(),//
				res.getLink(), //
				res.getParentResourceId(), //
				res.getHtmlId(),//
				res.getHtml(), //
				res.getOrder(),//
				res.getHierarchy(),//
				res.getType(), //
				res.getPath());
		return dto;
	}

	public static List<Resource> toModelList(List<ResDto> dtoList) {
		List<Resource> resList = new ArrayList<Resource>();
		for (ResDto resDto : dtoList) {
			resList.add(toModel(resDto));
		}
		return resList;
	}
	
	public static List<ResDto> toDtoList(List<Resource> modelList) {
			List<ResDto> dtoList = new ArrayList<>();
			for (Resource resource : modelList) {
				dtoList.add(toDto(resource));
			}
			return dtoList;
	}	
}
