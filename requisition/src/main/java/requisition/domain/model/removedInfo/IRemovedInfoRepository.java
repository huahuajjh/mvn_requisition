package com.tq.requisition.domain.model.removedInfo;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.removedinfo.RemovedInfoQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 已迁户聚合根仓储接口
 * @author jjh
 * @time 2015-12-30 22:46
 */
public interface IRemovedInfoRepository extends IRepository<RemovedInfo>{
	/**
	 * 新增已迁人员档案信息
	 * @param entity
	 * 		拆迁人员档案信息实体
	 * @return
	 * 		返回已拆迁人员档案信息实体
	 */
	RemovedInfo addRemovedInfo(RemovedInfo entity) throws DomainException ;

	/**
	 * 批量新增已迁户人员集合信息
	 * @param list
	 * 		已迁户人员集合信息
	 */
	void addBatch(List<RemovedInfo> list) throws DomainException;
	
	/**
	 * 根据指定id删除的拆迁人员档案信息
	 * @param id
	 * 		人员id
	 */
	void delRemovedInfo(UUID id);
	
	/**
	 * 查询已迁户档案
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 * 		已迁户档案集合
	 */
	PageFormater queryByPage(RemovedInfoQueryModel queryModel, PageModel pageModel);
	
	/**
	 * 编辑已迁户档案信息
	 * @param entity
	 * 		待编辑的已迁户档案实体
	 * @return
	 * 		返回被编辑后的档案实体
	 */
	RemovedInfo editRemovedInfo(RemovedInfo entity) throws DomainException ;
	
	/**
	 * 根据查询model查询已迁户集合
	 * @param queryModel
	 * 		查询model
	 * @return List<RemovedInfo>
	 * 		已迁户集合
	 */
	List<RemovedInfo> queryByModel(RemovedInfoQueryModel queryModel);
	
	/**
	 * 根据指定的身份证删除
	 * @param idNum 身份证
	 */
	void deleteByIdNum(String idNum);
}

