package com.tq.requisition.presentation.serviceContract.registedAgric;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.registedAgr.RegistedAgricQueryModel;
import com.tq.requisition.presentation.dto.registedAgr.RegistedAgricultureInfoDto;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 在籍农业人口信息管理服务契约
 * @author jjh
 * @time 2016 01-12 19:55
 */
public interface IRegistedAgricServiceContract{
	/**
	 * 新增在籍农业
	 * @param dto
	 * 		在籍农业人口dto
	 * @return String
	 * 		Formater 序列化对象
	 */
	String addInfo(RegistedAgricultureInfoDto dto);
	
	/**
	 * 批量新增在籍农业人口信息
	 * @param list
	 * 		在籍农业人口集合
	 * @return String
	 * 		Formater 序列化对象
	 */
	String addBatch(List<RegistedAgricultureInfoDto> list);
	
	/**
	 * 编辑在籍农业人口信息
	 * @param dto
	 * 		待编辑的在籍农业人口dto
	 * @return String
	 * 		Formater 序列化对象
	 */
	String editInfo(RegistedAgricultureInfoDto dto);
	
	/**
	 * 根据id删除指定的在籍农业人口信息
	 * @param id
	 * 		id
	 * @return String
	 * 		Formater 序列化对象
	 */
	String delInfo(UUID id);
	
	/**
	 * 根据查询model和分页model查询结果集
	 * @param queryModel
	 *		查询model
	 * @param pageModel
	 * 		分页model
	 * @return String
	 * 		List<RegistedAgricultureInfoDto> 序列化对象
	 */
	String queryByPage4Json(RegistedAgricQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 根据查询model和分页model查询结果集
	 * @param queryModel
	 *		查询model
	 * @param pageModel
	 * 		分页model
	 * @return String
	 * 		List<RegistedAgricultureInfoDto>
	 */
	PageFormater queryByPage4List(RegistedAgricQueryModel queryModel,PageModel pageModel);
	
	
}
