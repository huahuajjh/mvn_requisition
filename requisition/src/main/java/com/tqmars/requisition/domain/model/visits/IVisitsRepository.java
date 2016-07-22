package com.tqmars.requisition.domain.model.visits;

import java.util.UUID;

import com.tqmars.requisition.domain.IRepository.IRepository;
import com.tqmars.requisition.infrastructure.utils.PageFormater;
import com.tqmars.requisition.presentation.dto.share.PageModel;
import com.tqmars.requisition.presentation.dto.visits.VisitsQueryModel;

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
