package com.tq.requisition.application.removedInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.application.BaseApplication;
import com.tq.requisition.autoMapper.RemovedInfoMapper;
import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.model.removedInfo.IRemovedInfoRepository;
import com.tq.requisition.domain.model.removedInfo.RemovedInfo;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.removedinfo.RemovedInfoDto;
import com.tq.requisition.presentation.dto.removedinfo.RemovedInfoImportAndExportDto;
import com.tq.requisition.presentation.dto.removedinfo.RemovedInfoQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.serviceContract.removedInfo.IRemovedInfoServiceContract;

/**
 * 已迁户档案服务实现
 * @author jjh
 * @time 2016 01-12 12:57
 */
public class RemovedInfoServiceImpl extends BaseApplication implements IRemovedInfoServiceContract{
	private IRemovedInfoRepository removedInfoRepository;
	
	public RemovedInfoServiceImpl(IRepositoryContext context,IRemovedInfoRepository removedInfoRepository) {
		super(context);
		this.removedInfoRepository = removedInfoRepository;
		this.removedInfoRepository.setAggregatorRootClass(RemovedInfo.class);
	}

	@Override
	public String addInfo(RemovedInfoDto dto) {
		try {
			context().beginTransaction();
			removedInfoRepository.addRemovedInfo(RemovedInfoMapper.toModel(dto));
			context().commit();
			return toJson("新增已迁户档案成功", null, Formater.OperationResult.SUCCESS);
		} catch (DomainException e) {
			return toJson("新增已迁户档案失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		
	}

	@Override
	public String addBatch(List<RemovedInfoDto> list) {
		try {
			context().beginTransaction();
			removedInfoRepository.addBatch(RemovedInfoMapper.toModelList(list));
			context().commit();
			return toJson("新增已迁户档案成功", null, Formater.OperationResult.SUCCESS);
		} catch (DomainException e) {
			context().rollback();
			return toJson("新增已迁户档案失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().close();
		}
	}

	@Override
	public String editInfo(RemovedInfoDto dto) {
		try {
			context().beginTransaction();
			removedInfoRepository.editRemovedInfo(RemovedInfoMapper.toModel(dto));
			context().commit();
			return toJson("编辑数据成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("编辑数据失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String delInfo(UUID id) {
		try {
			context().beginTransaction();
			removedInfoRepository.delRemovedInfo(id);
			context().commit();
			return toJson("删除数据成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("删除数据失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}
	
	@Override
	public void delInfo(String idNumber) {
		removedInfoRepository.deleteByIdNum(idNumber);
	}

	@Override
	public String queryByPage4Json(RemovedInfoQueryModel queryModel,
			PageModel pageModel) {
		return toJsonByPage(queryByPage4List(queryModel, pageModel), "查询成功", Formater.OperationResult.SUCCESS);
	}

	@Override
	public PageFormater queryByPage4List(
			RemovedInfoQueryModel queryModel, PageModel pageModel) {
		return removedInfoRepository.queryByPage(queryModel, pageModel);
	}
	
	@Override
	public String importRemovedInfo(List<RemovedInfoImportAndExportDto> list) {
		if(null==list || list.size()==0){
			return toJson("已迁户列表为空", null, Formater.OperationResult.FAIL);
		}
		List<RemovedInfo> infos = new ArrayList<RemovedInfo>();
		for (RemovedInfoImportAndExportDto removedInfoImportAndExportDto : list) {
			infos.add(removedInfoImportAndExportDto.toRemovedInfo());
		}
		try {
			context().beginTransaction();
			removedInfoRepository.addBatch(infos);
			context().commit();
			return toJson("导入已迁户信息成功", list, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			context().rollback();
			return toJson("导入已迁户信息失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().close();
		}
	}
	
	@Override
	public List<RemovedInfo> exportByQueryModel(RemovedInfoQueryModel queryModel) {
		if(null==queryModel){
			return null;
		}
		return removedInfoRepository.queryByModel(queryModel);
	}
	
}
