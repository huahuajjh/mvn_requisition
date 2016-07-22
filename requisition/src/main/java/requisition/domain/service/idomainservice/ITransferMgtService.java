package com.tq.requisition.domain.service.idomainservice;

import java.util.List;

import com.tq.requisition.domain.model.transferHouseholdInfo.TransferHouseholdInfo;
import com.tq.requisition.exception.DomainException;

/**
 * 转户管理业务接口服务
 * @author jjh
 * @time 2015-12-30 19:00
 */
public interface ITransferMgtService {
	/**
	 * 新增转户信息，该服务涉及拆迁户人员信息变动，是关于拆迁户人员仓储和转户信息仓储共同完成的业务过程
	 * @param model
	 * 		待新增的转户信息实体
	 */
	void addTransferInfo(TransferHouseholdInfo model) throws DomainException ;
	
	/**
	 * 批量新增转户信息，该服务涉及拆迁户人员信息更新，由拆迁户人员仓储和转户仓储共同完成该业务
	 * @param list
	 * 		待新增的数据集合
	 */
	void addBatchTransferInfo(List<TransferHouseholdInfo> list) throws DomainException ;
	
	/**
	 * 批量更新转户信息，该服务涉及拆迁户人员信息更新，由拆迁户人员仓储和转户仓储共同完成该业务
	 * @param list
	 * 		待更新的数据集合
	 */
	void editBatchTransferInfo(List<TransferHouseholdInfo> list);
	
}
