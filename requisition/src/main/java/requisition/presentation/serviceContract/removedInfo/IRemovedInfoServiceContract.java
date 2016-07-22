package com.tq.requisition.presentation.serviceContract.removedInfo;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.model.removedInfo.RemovedInfo;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.removedinfo.RemovedInfoDto;
import com.tq.requisition.presentation.dto.removedinfo.RemovedInfoImportAndExportDto;
import com.tq.requisition.presentation.dto.removedinfo.RemovedInfoQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 已迁户档案服务契约 
 * @author jjh
 * @time 2016 01-12 12:57
 */
public interface IRemovedInfoServiceContract {
	/**
	 * 新增已迁户人员信息
	 * @param dto
	 * 		已迁户人员信息dto
	 * @return String
	 * 		返回 Formater 序列化 String 对象
	 */
	String addInfo(RemovedInfoDto dto);
	
	/**
	 * 批量新增已迁户人员信息集合
	 * @param list
	 * 		在籍农业人口信息集合
	 * @return String
	 * 		返回 Formater 序列化 String 对象
	 */
	String addBatch(List<RemovedInfoDto> list);
	
	/**
	 * 编辑指定已迁户人员对象
	 * @param dto
	 * 		待编辑的dto
	 * @return String
	 * 		返回 Formater 序列化 String 对象
	 */
	String editInfo(RemovedInfoDto dto);
	
	/**
	 * 根据id删已迁户人员信息
	 * @param id
	 * 		id
	 * @return String
	 * 		返回 Formater 序列化 String 对象
	 */
	String delInfo(UUID id);
	
	/**
	 * 根据指定的身份证删除人员信息
	 * @param idNumber
	 */
	void delInfo(String idNumber);
	
	/**
	 * 根据查询model和分页model插叙结果集
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return String
	 * 		List<RemovedInfoDto>
	 */
	String queryByPage4Json(RemovedInfoQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 根据查询model和分页model插叙结果集
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return List<RemovedInfoDto>
	 * 		返回 List<RemovedInfoDto> 结果集合
	 */
	PageFormater queryByPage4List(RemovedInfoQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 导入已迁户列表
	 * @param list
	 * 		已迁户导入导出model
	 * @return String
	 * 		json
	 */
	String importRemovedInfo(List<RemovedInfoImportAndExportDto> list);
	
	/**
	 * 根据查询model导出已迁户列表
	 * @param queryModel
	 * 		查询model
	 * @return 已迁户列表
	 */
	List<RemovedInfo> exportByQueryModel(RemovedInfoQueryModel queryModel);
}
