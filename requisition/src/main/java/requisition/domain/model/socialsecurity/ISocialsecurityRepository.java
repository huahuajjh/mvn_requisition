package com.tq.requisition.domain.model.socialsecurity;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.exception.SpecifiedObjectDoesNotExistsException;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.socialsecurityMgt.SocialsecurityQueryModel;

/**
 * 社保仓储接口
 * @author jjh
 * @time 2015-12-31 20:26
 */
public interface ISocialsecurityRepository extends IRepository<SocialsecurityInfo>{
	/**
	 * 新增社保信息
	 * @param ss
	 * 		待新增的社保信息
	 */
	void addSS(SocialsecurityInfo ss) throws SpecifiedObjectDoesNotExistsException ;
	
	/**
	 * 批量新增社保数据
	 * @param list
	 * 		待新增的社保集合数据
	 */
	void addBatch(List<SocialsecurityInfo> list) throws SpecifiedObjectDoesNotExistsException;
	
	/**
	 * 根据指定的id删除社保信息实体
	 * @param uuids
	 * 		待删除的id数组
	 */
	void deleteSS(UUID... uuids);
	
	/**
	 * 编辑指定的社保信息
	 * @param ss
	 * 		待更新的社保实体
	 */
	void editSS(SocialsecurityInfo ss) throws SpecifiedObjectDoesNotExistsException;
	
	/**
	 * 分页查询社保数据台账,台账查询
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 */
	PageFormater query4TableByFuzzy(SocialsecurityQueryModel queryModel,PageModel pageModel);

	/**
	 * 新增社保数据时的查询，分页查询社保数据台账
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 */
	PageFormater query4AddByFuzzy(SocialsecurityQueryModel queryModel,PageModel pageModel);
}
