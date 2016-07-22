package com.tqmars.requisition.infrastructure.Repository.removedinfo;

import java.util.List;
import java.util.UUID;

import com.tqmars.requisition.domain.IRepository.IRepositoryContext;
import com.tqmars.requisition.domain.model.removedInfo.IRemovedInfoRepository;
import com.tqmars.requisition.domain.model.removedInfo.RemovedInfo;
import com.tqmars.requisition.exception.DomainException;
import com.tqmars.requisition.infrastructure.Repository.HbRepository;
import com.tqmars.requisition.infrastructure.Specifications.removedinfo.RemovedInfo4ExportSpecification;
import com.tqmars.requisition.infrastructure.Specifications.removedinfo.RemovedInfoCountSpecification;
import com.tqmars.requisition.infrastructure.Specifications.removedinfo.RemovedInfoIdNumExistsSpecification;
import com.tqmars.requisition.infrastructure.Specifications.removedinfo.RemovedInfoQueryModelSpecification;
import com.tqmars.requisition.infrastructure.utils.PageFormater;
import com.tqmars.requisition.presentation.dto.removedinfo.RemovedInfoQueryModel;
import com.tqmars.requisition.presentation.dto.share.PageModel;

/**
 * 已迁户档案仓储实现
 * @author jjh
 * @time 2015-01-11 19:44
 *
 */
public class RemovedInfoRepository extends HbRepository<RemovedInfo> implements IRemovedInfoRepository{

	public RemovedInfoRepository(IRepositoryContext context) {
		super(context);		
	}

	@Override
	public RemovedInfo addRemovedInfo(RemovedInfo entity) throws DomainException {
		entity.validate();
		//验证身份证是否存在
		Boolean r = exists(new RemovedInfoIdNumExistsSpecification(RemovedInfo.class, entity.getIdNumber()));
		if(r){throw new DomainException("该身份证["+entity.getIdNumber()+"]已存在，不能重复添加");}
		add(entity);
		return entity;
	}

	@Override
	public void delRemovedInfo(UUID id) {
		removeBySql("delete from tb_removed_info where id=?", id.toString());
	}
	

	@Override
	public void deleteByIdNum(String idNum) {
		removeBySql("delete from tb_removed_info where id_number=?", idNum);
	}

	@Override
	public PageFormater queryByPage(RemovedInfoQueryModel queryModel,
			PageModel pageModel) {
		int count = getTotalCount(new RemovedInfoCountSpecification(RemovedInfo.class, queryModel));
		if(count==0){return null;}
		
		List<RemovedInfo> list = getAll(new RemovedInfoQueryModelSpecification(RemovedInfo.class, queryModel,pageModel));
		return PageFormater.obtain(list, count);
	}

	@Override
	public RemovedInfo editRemovedInfo(RemovedInfo entity) throws DomainException {
		RemovedInfo info = getByKey(RemovedInfo.class, entity.getId());
		if(null == info){throw new DomainException("未查询到指定的已迁户人员信息");}
		info.validate();
		info.modify(entity);
		update(info);
		return info;
	}
	
	@Override
	public void addBatch(List<RemovedInfo> list) throws DomainException {
		for (RemovedInfo removedInfo : list) {
			addRemovedInfo(removedInfo);
		}
	}
	
	@Override
	public List<RemovedInfo> queryByModel(RemovedInfoQueryModel queryModel) {
		List<RemovedInfo> list = getAll(new RemovedInfo4ExportSpecification(RemovedInfo.class, queryModel));
		return list;
	}

}
