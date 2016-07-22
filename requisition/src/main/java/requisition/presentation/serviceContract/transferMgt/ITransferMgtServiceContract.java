package com.tq.requisition.presentation.serviceContract.transferMgt;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.transferMgt.NewAndEditTransferHouseholdInfoDto;
import com.tq.requisition.presentation.dto.transferMgt.TransferInfoQueryModel;

/**
 * 转户管理业务接口契约
 * @author jjh
 * @time 2015-12-30 17:07
 *
 */
public interface ITransferMgtServiceContract {
	/**
	 * 新增转户信息
	 * @param dto
	 * 		转户信息dto
	 * @return
	 */
	String addTransferInfo(NewAndEditTransferHouseholdInfoDto dto);
	
	/**
	 * 批量处理转户信息
	 * @param list
	 * 		转户信息dto集合
	 * @return
	 */
	String addBatchTransferInfo(List<NewAndEditTransferHouseholdInfoDto> list);
	
	/**
	 * 根据查询model获取转户信息列表，返回json格式
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 */
	String queryJsonByFuzzy(TransferInfoQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 根据查询model获取转户信息集合
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 	分页model
	 * @return
	 */
	PageFormater queryListByFuzzy(TransferInfoQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 批量编辑转户信息时查询的接口，用于批量编辑的查询。
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 */
//	PageFormater queryListByFuzzy4Edit(TransferInfoQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 批量编辑前的查询操作，返回json字符串	
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 */
//	String queryByFuzzy4Edit(TransferInfoQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 批量更新转户集合信息
	 * @param list
	 * 		待更新的转户集合
	 * @return
	 */
//	String editBatchTransferInfo(List<NewAndEditTransferHouseholdInfoDto> list);
	
	/**
	 * 新增时查询api
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 */
	PageFormater queryList4AddByFuzzy(TransferInfoQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 新增时查询api
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 */
	String query4AddByFuzzy(TransferInfoQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 批量删除到回收站
	 * @param uuids
	 * 		待删除的id数组
	 * @return
	 */
	String deleteBatch(UUID... uuids);

	/**
	 * 编辑转户信息
	 * @param dto
	 * 		dto
	 * @return
	 */
	String editTransferInfo(NewAndEditTransferHouseholdInfoDto dto);
}
