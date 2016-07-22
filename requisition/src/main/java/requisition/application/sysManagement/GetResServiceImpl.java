package com.tq.requisition.application.sysManagement;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.application.BaseApplication;
import com.tq.requisition.autoMapper.ResMapper;
import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.model.resource.Resource;
import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.presentation.dto.sysMgt.ResDto;
import com.tq.requisition.presentation.serviceContract.sysManagement.IGetResService;

public class GetResServiceImpl extends BaseApplication implements IGetResService {
	
	private com.tq.requisition.domain.service.idomainservice.IGetResService getResService;
	
	public GetResServiceImpl(
			IRepositoryContext context,
			com.tq.requisition.domain.service.idomainservice.IGetResService getResService) {
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
