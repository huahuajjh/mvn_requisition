package com.tq.requisition.domain.model.visits;

import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.visits.VisitsQueryModel;

/**
 * 上访记录仓储接口
 * @author jjh
 * @time 2016-01-13 15:42
 *
 */
public interface IVisitsRepository extends IRepository<Visits>{
	/**
	 * 根据id删除指定的上访信息
	 * @param id
	 * 		待删除对象的id
	 */
	void delById(UUID id);
	
	PageFormater queryByPage(VisitsQueryModel queryModel,PageModel pageModel);
}
