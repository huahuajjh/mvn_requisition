package com.tq.requisition.infrastructure.Repository.registedAgricultureInfo;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.model.registedAgricultureInfo.IRegistedAgricultureInfoRepository;
import com.tq.requisition.domain.model.registedAgricultureInfo.RegistedAgricultureInfo;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.Repository.HbRepository;
import com.tq.requisition.infrastructure.Specifications.registedAgric.RegistedAgricCountSpecificatino;
import com.tq.requisition.infrastructure.Specifications.registedAgric.RegistedAgricQuerySpecification;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.registedAgr.RegistedAgricQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 在籍农业人口信息管理仓储实现
 * 
 * @author jjh
 * @time 2015-01-07 16:37
 * 
 */
public class RegistedAgricultureInfoRepository extends HbRepository<RegistedAgricultureInfo> implements IRegistedAgricultureInfoRepository {

	public RegistedAgricultureInfoRepository(IRepositoryContext context) {
		super(context);
	}

	@Override
	public PageFormater queryByPage(
			RegistedAgricQueryModel queryModel, PageModel pageModel) {
		int count = getTotalCount(new RegistedAgricCountSpecificatino(RegistedAgricultureInfo.class, queryModel));
		if(count==0){return null;}
		List<RegistedAgricultureInfo> list = getAll(new RegistedAgricQuerySpecification(RegistedAgricultureInfo.class, queryModel, pageModel));
		return PageFormater.obtain(list, count);
	}

	@Override
	public void addInfo(RegistedAgricultureInfo entity) {
		add(entity);
	}

	@Override
	public void addBatch(List<RegistedAgricultureInfo> list) {
		for (RegistedAgricultureInfo registedAgricultureInfo : list) {
			addInfo(registedAgricultureInfo);
		}

	}

	@Override
	public void editInfo(RegistedAgricultureInfo entity) throws DomainException {
		RegistedAgricultureInfo info = getByKey(RegistedAgricultureInfo.class, entity.getId());
		if(null == info){throw new DomainException("未查询到指定的在籍农业人口信息");}
		info.modify(entity);
		update(info);
	}

	@Override
	public void delById(UUID id) {		
		removeBySql("delete from tb_registed_argc_info where id=?", id.toString());
	}

}
