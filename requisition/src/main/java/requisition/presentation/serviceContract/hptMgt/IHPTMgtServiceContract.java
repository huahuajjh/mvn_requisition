package com.tq.requisition.presentation.serviceContract.hptMgt;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.hpt.HPTExchangeInfoDto;
import com.tq.requisition.presentation.dto.hpt.HPTFuzzyQueryModel;
import com.tq.requisition.presentation.dto.hpt.HPTImportAndExport;
import com.tq.requisition.presentation.dto.hpt.HPTLossInfoDto;
import com.tq.requisition.presentation.dto.hpt.HPTMendInfoDto;
import com.tq.requisition.presentation.dto.hpt.HPTQueryModel;
import com.tq.requisition.presentation.dto.hpt.HPTReceiveTableDto;
import com.tq.requisition.presentation.dto.hpt.HPTRecevieInfoDto;
import com.tq.requisition.presentation.dto.hpt.HPTUseAndCashInfoDto;
import com.tq.requisition.presentation.dto.hpt.HousePuraseTicketDto;
import com.tq.requisition.presentation.dto.hpt.HptUseAndCashQueryModel;
import com.tq.requisition.presentation.dto.removedinfo.RemovedInfoQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 购房券服务契约
 * @author jjh
 * @time 2015-01-02 17:31
 *
 */
public interface IHPTMgtServiceContract {
	/**
	 * 新增购房券信息
	 * @param dto
	 * 		待新增的购房券信息
	 * @return
	 * 		json
	 */
	String add(HousePuraseTicketDto dto);
	
	/**
	 * 批量新增购房券信息
	 * @param dtos
	 * 		购房券dto集合
	 * @return
	 * 		json
	 */
	String add(List<HousePuraseTicketDto> dtos);
	
	/**
	 * 换券
	 * @param dto
	 * 		换券信息
	 * @param hpt
	 * 		新增的购房券
	 * @return
	 * 		json
	 */
	String exchange(HPTExchangeInfoDto dto,HousePuraseTicketDto hpt);
	
	/**
	 * 补券
	 * @param mendDto
	 * 		补券信息
	 * @param hptDto
	 * 		新增的购房券
	 * @return
	 * 		json
	 */
	String mend(HPTMendInfoDto mendDto,HousePuraseTicketDto hptDto);
	
	/**
	 * 购房券的使用和兑现
	 * @param dto
	 * 		使用信息
	 * @return
	 * 		json
	 */
	String use(HPTUseAndCashInfoDto dto);
	
	/**
	 * 购房券的发放
	 * @param dto
	 * 		发放信息
	 * @return
	 * 		json
	 */
	String provide(HPTRecevieInfoDto dto);
	
	/**
	 * 挂失
	 * @param dto
	 * 		挂失信息
	 * @return
	 * 		json
	 */
	String lossOfReport(HPTLossInfoDto dto);

	/**
	 * 查询换券信息
	 * @param queryModel
	 * 		查询model
	 * @return
	 * 		json
	 */
	String queryExchangeInfo(HPTQueryModel queryModel);

	/**
	 * 查询一户的购房券信息
	 * @param idNumber
	 * 		户主身份证
	 * @return
	 * 		json
	 */
	String queryByFml(String idNumber);
	
	/**
	 * 根据项目的名称获取未发放购房券
	 * @param proName
	 * 		项目名称
	 * @return
	 */
	String queryNotByProId(String proName,PageModel pageModel);
	
	/**
	 * 查询单人的购房券信息
	 * @param idNumber
	 * 		身份证
	 * @return
	 * 		json
	 */
	String queryByIdNumber(String idNumber);
	
	/**
	 * 查询发放台账
	 * @param queryModel
	 * 		查询model
	 * @return
	 * 		json
	 */
	String queryProvideTable(HPTFuzzyQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 查询兑付台账
	 * @param queryModel
	 * 		查询model
	 * @return
	 * 		json
	 */
	String queryUseTable(HPTFuzzyQueryModel queryModel,PageModel pageModel);	
	
	/**
	 * 按户发放购房券
	 * @param list
	 * 		发放dto集合
	 * @return
	 */
	String provideByFml(List<HPTRecevieInfoDto> list);
	
	/**
	 * 导入购房券
	 * @param list
	 * 		购房券导入导出dto
	 * @return String
	 * 		json
	 */
	String importHPT(List<HPTImportAndExport> list);
	
	/**
	 * 根据查询model和分页model插叙结果集
	 * @param queryModel 查询model
	 * @param pageModel 分页model
	 * @return
	 */
	String queryByPage4Json(HptUseAndCashQueryModel queryModel,
			PageModel pageModel);

	/**
	 * 根据查询model和分页model插叙结果集
	 * @param queryModel 查询model
	 * @param pageModel 分页model
	 * @return
	 */
	PageFormater queryByPage4List(HptUseAndCashQueryModel queryModel,
			PageModel pageModel);
	
	/**
	 * 购房券的使用和兑现
	 * @param dtos
	 * 		使用信息
	 * @return
	 * 		json
	 */
	String use(List<HPTUseAndCashInfoDto> dtos);
	
	/**
	 * 获取所有的发放台账数据
	 * @param proName
	 * 		项目名称
	 * @return
	 */
	List<HPTReceiveTableDto> getHPTReceiveTableDtoAll(String proName);
}
