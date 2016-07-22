package com.tq.requisition.domain.model.registedAgricultureInfo;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.registedAgr.RegistedAgricQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 在籍农业人口信息仓储接口
 * @author jjh
 * @time 2015-01-05 18:50
 *
 */
public interface IRegistedAgricultureInfoRepository extends IRepository<RegistedAgricultureInfo>{
	/**
	 * 根据分页与查询model查询
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return List<RegistedAgricultureInfo>
	 * 		在籍农业人口信息集合
	 */
	PageFormater queryByPage(RegistedAgricQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 新增在籍农业人口信息
	 * @param entity
	 * 		在籍农业人口信息实体
	 */
	void addInfo(RegistedAgricultureInfo entity);
	
	/**
	 * 批量新增在籍农业信息
	 * @param list
	 * 		信息集合
	 */
	void addBatch(List<RegistedAgricultureInfo> list);
	
	/**
	 * 编辑在籍农业人口信息
	 * @param entity
	 * 		在籍农业人口信息实体
	 */
	void editInfo(RegistedAgricultureInfo entity) throws DomainException ;
	
	/**
	 * 根据id删除指定的信息
	 * @param id
	 * 		id
	 */
	void delById(UUID id);
	
}
