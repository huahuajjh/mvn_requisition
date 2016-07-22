package com.tq.requisition.presentation.serviceContract.visits;

import java.util.UUID;

import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.visits.VisitsDto;
import com.tq.requisition.presentation.dto.visits.VisitsQueryModel;

/**
 * 上访管理契约接口
 * @author jjh
 * @time 2016-01-13 16:12
 *
 */
public interface IVisitsServiceContract {
	/**
	 * 新增上访信息
	 * @param dto
	 * 		上访信息dto
	 * @return String
	 * 		Formater json对象
	 */
	String addInfo(VisitsDto dto);
	
	/**
	 * 编辑上访信息
	 * @param dto
	 * 		待编辑的上访信息dto
	 * @return String
	 * 		Formater json 对象
	 */
	String editInfo(VisitsDto dto);
	
	/**
	 * 根据查询model和分页model查询上访信息集合
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return PageFormater
	 * 		PageFormater json对象
	 */
	String queryByPage4Json(VisitsQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 根据查询model和分页model查询上访信息集合
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return List<VisitsDto>
	 * 		List<VisitsDto>
	 * 		
	 */
	PageFormater queryByPage4List(VisitsQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 根据id删除上访信息
	 * @param id
	 * 		待删除上访实体id
	 * @return String
	 * 		Formater json对象
	 */
	String delById(UUID id);
}
