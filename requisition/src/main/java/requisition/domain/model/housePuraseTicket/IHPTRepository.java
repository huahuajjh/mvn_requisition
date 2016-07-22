package com.tq.requisition.domain.model.housePuraseTicket;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.exception.SpecifiedObjectDoesNotExistsException;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.hpt.HPTDisplayDto;
import com.tq.requisition.presentation.dto.hpt.HPTDisplayFmlDto;
import com.tq.requisition.presentation.dto.hpt.HPTFuzzyQueryModel;
import com.tq.requisition.presentation.dto.hpt.HPTQueryModel;
import com.tq.requisition.presentation.dto.hpt.HPTReceiveTableDto;
import com.tq.requisition.presentation.dto.hpt.HptUseAndCashQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 购房券仓储接口
 * 
 * @author jjh
 * @time 2015-12-31 22:20
 */
public interface IHPTRepository extends IRepository<HousePuraseTicket> {
	/**
	 * 新增购房券
	 * 
	 * @param hpt
	 *            待新增的购房券
	 */
	void addHPT(HousePuraseTicket hpt) throws DomainException;

	/**
	 * 换券操作
	 * 
	 * @param exHPT
	 *            换券信息
	 * @param newTicket
	 *            新券实体
	 */
	void exchange(HPTExchangeInfo exHPT, HousePuraseTicket newTicket)
			throws SpecifiedObjectDoesNotExistsException;

	/**
	 * 购房券领取
	 * 
	 * @param revieveHPT
	 *            领取信息
	 */
	void provider(HPTProviderInfo revieveHPT)
			throws SpecifiedObjectDoesNotExistsException;

	/**
	 * 按照户发放购房券
	 * 
	 * @param list
	 *            发放信息集合
	 */
	void provideByFml(List<HPTProviderInfo> list)
			throws SpecifiedObjectDoesNotExistsException;

	/**
	 * 购房券挂失
	 * 
	 * @param lossPHT
	 *            挂失信息
	 */
	void reportOfLoss(HPTReportOfLossInfo lossPHT)
			throws SpecifiedObjectDoesNotExistsException;

	/**
	 * 购房券补券
	 * 
	 * @param mendHPT
	 *            补券信息
	 * @param newTicket
	 *            新券实体
	 */
	void mend(HPTMendInfo mendHPT, HousePuraseTicket newTicket)
			throws SpecifiedObjectDoesNotExistsException;

	/**
	 * 购房券的使用
	 * 
	 * @param useAndCashHPT
	 *            使用信息
	 */
	void useOrCash(HPTUseAndCash useAndCashHPT)
			throws SpecifiedObjectDoesNotExistsException;

	/**
	 * 批量新增购房券信息
	 * 
	 * @param list
	 *            购房券信息集合
	 */
	void addBatch(List<HousePuraseTicket> list) throws DomainException;

	/**
	 * 查询购房券发放台账
	 * 
	 * @param queryModel
	 *            查询model
	 * @return 返回发放台账model
	 */
	PageFormater queryProvideTable(HPTFuzzyQueryModel queryModel,
			PageModel pageModel);

	/**
	 * 查询兑付台账
	 * 
	 * @param queryModel
	 *            查询model
	 * @return 兑付台账model
	 */
	PageFormater queryUseTable(HPTFuzzyQueryModel queryModel,
			PageModel pageModel);

	/**
	 * 查询购房券的使用与对付
	 * 
	 * @param queryModel
	 * @param pageModel
	 * @return
	 */
	PageFormater queryByPage(HptUseAndCashQueryModel queryModel,
			PageModel pageModel);

	/**
	 * 根据身份证号查询购房券和人员信息
	 * 
	 * @param idNumber
	 *            身份证
	 * @return 购房券和人员信息model
	 */
	List<HPTDisplayDto> queryByIdnumber(String idNumber);

	/**
	 * 根据查询model查询购房券和人员信息
	 * 
	 * @param queryModel
	 *            查询model
	 * @return HPTDisplayDto
	 */
	HPTDisplayDto queryByIdnumberAndTicketNum(HPTQueryModel queryModel);

	/**
	 * 根据身份证查询以户为单位的购房券信息
	 * 
	 * @param idNumber
	 *            户主身份证
	 * @return 整户的购房券信息集合
	 */
	List<HPTDisplayFmlDto> queryFmlByFmlId(UUID fmlId);

	/**
	 * 根据人员查询未发放购房券列表
	 * @param fmlItem
	 * 		人员列表
	 * @param pageModel
	 * 		分页数据
	 * @return
	 */
	PageFormater queryNotFmlByFmlItem(String proName,
			PageModel pageModel);
	
	/**
	 * 获取所有的发放台账数据
	 * @param proName
	 * 		项目名称
	 * @return
	 */
	List<HPTReceiveTableDto> getHPTReceiveTableDtoAll(String proName);
}
