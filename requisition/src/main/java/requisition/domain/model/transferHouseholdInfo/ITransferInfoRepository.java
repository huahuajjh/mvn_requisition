package com.tq.requisition.domain.model.transferHouseholdInfo;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.exception.SpecifiedObjectDoesNotExistsException;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.transferMgt.TransferInfoQueryModel;

/**
 * 转户信息仓储接口
 * @author jjh
 * @time 2015-12-30 23:04
 */
public interface ITransferInfoRepository  extends IRepository<TransferHouseholdInfo>{
	/**
	 * 录入转户信息	
	 * @param entity
	 * 		待录入的转户信息实体
	 */
	void addTransferInfo(TransferHouseholdInfo entity) throws DomainException ;
	
	/**
	 * 批量录入转户信息
	 * @param list
	 * 		转户信息集合
	 */
	void addBatch(List<TransferHouseholdInfo> list) throws DomainException ;
	
	/**
	 * 更新转户信息
	 * @param entity
	 * 		待更新的转户实体
	 */
	void editTransferInfo(TransferHouseholdInfo entity) throws SpecifiedObjectDoesNotExistsException ;
	
	/**
	 * 批量更新转户信息
	 * @param list
	 * 		待更新的集合
	 */
	void editBact(List<TransferHouseholdInfo> list) throws SpecifiedObjectDoesNotExistsException ;
	
	/**
	 * 批量删除转户信息
	 * @param uuids
	 */
	void deleteBact(UUID... uuids);
	
	/**
	 * 模糊查询转户集合
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return List<TransferHouseholdInfo>
	 * 		转户集合信息
	 */
	PageFormater queryByFuzzy(TransferInfoQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 用于新增时信息查询
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 */
	PageFormater queryByFuzzy4Add(TransferInfoQueryModel queryModel,PageModel pageModel);
}
