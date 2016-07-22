package com.tqmars.requisition.application.sysManagement;

import java.util.List;
import java.util.UUID;

import com.tqmars.requisition.application.BaseApplication;
import com.tqmars.requisition.autoMapper.ResMapper;
import com.tqmars.requisition.domain.IRepository.IRepositoryContext;
import com.tqmars.requisition.domain.model.resource.Resource;
import com.tqmars.requisition.infrastructure.utils.Formater;
import com.tqmars.requisition.presentation.dto.sysMgt.ResDto;
import com.tqmars.requisition.presentation.serviceContract.sysManagement.IGetResService;

public class GetResServiceImpl extends BaseApplication implements IGetResService {
	
	private com.tqmars.requisition.domain.service.idomainservice.IGetResService getResService;
	
	public GetResServiceImpl(
			IRepositoryContext context,
			com.tqmars.requisition.domain.service.idomainservice.IGetResService getResService) {
		super(context);
		this.getResService = getResService;
	}

//	@Override
//	public List<ResDto> getMenuResByUserId(UUID uId, int hierarchy) {
//		List<ResDto> resDtos = new ArrayList<ResDto>();
//		List<Resource> resDataList = this.getResService.getResourceByUserId(uId, hierarchy);
//		for (Resource resource : resDataList) {
//			resDtos.add(ResMapper.toDto(resource));
//		}
//		return resDtos;
//	}

//	@Override
//	public String getMenuResByUserIdJSON(UUID uId, int hierarchy) {
//		return Serialization.toJson(getMenuResByUserId(uId,hierarchy));
//	}

//	@Override
//	public List<ResDto> getResByUserIdAndType(UUID uId, ResourceType type,
//			int hierarchy) {
//		List<ResDto> resDtos = new ArrayList<ResDto>();
//		List<Resource> resDataList = this.getResService.getResourceByUserId(uId, type,hierarchy);
//		for (Resource resource : resDataList) {
//			resDtos.add(ResMapper.toDto(resource));
//		}
//		return resDtos;
//	}

//	@Override
//	public String getResByUserIdAndTypeJSON(UUID uId, ResourceType type,
//			int hierarchy) {
//		return Serialization.toJson(getResByUserIdAndType(uId,type,hierarchy));
//	}

	
	@Override
	public List<ResDto> getResByUserId(UUID uid) {
		List<Resource> list = getResService.getResByUserId(uid);
		if(null == list || list.size()==0) return null;
		return ResMapper.toDtoList(list);
	}

	@Override
	public String getResByUserIdJson(UUID uid) {
		try {
			return toJson("成功", getResByUserId(uid), Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}
	
}
