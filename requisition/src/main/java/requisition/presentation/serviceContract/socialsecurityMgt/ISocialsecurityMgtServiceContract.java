package com.tq.requisition.presentation.serviceContract.socialsecurityMgt;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.model.socialsecurity.SocialsecurityInfo;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.socialsecurityMgt.NewSocialsecurityDto;
import com.tq.requisition.presentation.dto.socialsecurityMgt.SocialsecurityQueryModel;
import com.tq.requisition.presentation.dto.socialsecurityMgt.SsImportAndExportDto;

/**
 * 社保管理接口
 * @author jjh
 * @time 2015-12-30 17:39
 */
public interface ISocialsecurityMgtServiceContract {
	/**
	 * 录入社保信息
	 * @param dto
	 * 		社保信息dto
	 * @return
	 * 		新增后的社保信息
	 */
	String addSSInfo(NewSocialsecurityDto dto);
	
	/**
	 * 批量新增社保数据
	 * @param list
	 * 		待新增的社保集合数据
	 * @return
	 */
	String addBatch(List<NewSocialsecurityDto> list);
	
	/**
	 * 根据查询model查询数据
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 */
	PageFormater queryFuzzyByAdd(SocialsecurityQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 根据查询model查询数据
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 */
	String queryFuzzyByAddJson(SocialsecurityQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 台账查询接口
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 * 		台账dto集合
	 */
	PageFormater query4TableListByFuzzy(SocialsecurityQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 台账查询接口
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return
	 * 		台账dto json数据
	 */
	String query4TableByFuzzy(SocialsecurityQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 编辑社保信息
	 * @param dto
	 * 		待更新的社保数据
	 * @return
	 */
	String editSS(NewSocialsecurityDto dto);
	
	/**
	 * 删除指定社保id的数据
	 * @param uuids
	 * 		待删除的id数组
	 * @return
	 */
	String deleteSS(UUID... uuids);
	
	/**
	 * 导入社保信息
	 * @param list
	 * 		社保集合
	 * @return String
	 * 		json
	 */	
	String importSS(List<SsImportAndExportDto> items);
}
