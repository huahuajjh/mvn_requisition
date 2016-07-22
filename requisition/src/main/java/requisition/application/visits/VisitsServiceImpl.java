package com.tq.requisition.application.visits;

import java.util.UUID;

import com.tq.requisition.application.BaseApplication;
import com.tq.requisition.autoMapper.VisitsMapper;
import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.model.visits.IVisitsRepository;
import com.tq.requisition.domain.model.visits.Visits;
import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.visits.VisitsDto;
import com.tq.requisition.presentation.dto.visits.VisitsQueryModel;
import com.tq.requisition.presentation.serviceContract.visits.IVisitsServiceContract;

/**
 * 上访管理服务实现
 * @author jjh
 * @time 2016-01-13 16:17/
 *
 */
public class VisitsServiceImpl extends BaseApplication implements IVisitsServiceContract{
	private IVisitsRepository visitsRepository;

	public VisitsServiceImpl(IRepositoryContext context,IVisitsRepository visitsRepository) {
		super(context);
		this.visitsRepository = visitsRepository;
		this.visitsRepository.setAggregatorRootClass(Visits.class);
	}

	@Override
	public String addInfo(VisitsDto dto) {
		try {
			context().beginTransaction();
			visitsRepository.add(VisitsMapper.toModel(dto));
			context().commit();
			return toJson("新增上访信息成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("新增上访信息失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}		
	}

	@Override
	public String editInfo(VisitsDto dto) {
		context().beginTransaction();
		Visits model = visitsRepository.getByKey(Visits.class, dto.getId());
		if(null==model){return toJson("未查询到指定的上访信息", null, Formater.OperationResult.FAIL);}
		model.modify(VisitsMapper.toModel(dto));
		try {
			visitsRepository.update(model);
			context().commit();
			return toJson("修改上访信息成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("修改上访信息失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String queryByPage4Json(VisitsQueryModel queryModel,
			PageModel pageModel){
		return toJsonByPage(queryByPage4List(queryModel, pageModel), "获取数据成功", Formater.OperationResult.SUCCESS);
	}

	@Override
	public PageFormater queryByPage4List(VisitsQueryModel queryModel,
			PageModel pageModel) {
		return visitsRepository.queryByPage(queryModel, pageModel);
	}

	@Override
	public String delById(UUID id) {
		try {
			context().beginTransaction();
			visitsRepository.delById(id);
			context().commit();
			return toJson("删除上访信息成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("删除上访信息失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

}
