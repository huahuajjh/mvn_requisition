package com.tq.requisition.domain.service.idomainservice;

import java.util.List;

import com.tq.requisition.domain.model.housePuraseTicket.HPTExchangeInfo;
import com.tq.requisition.domain.model.housePuraseTicket.HPTMendInfo;
import com.tq.requisition.domain.model.housePuraseTicket.HPTProviderInfo;
import com.tq.requisition.domain.model.housePuraseTicket.HPTReportOfLossInfo;
import com.tq.requisition.domain.model.housePuraseTicket.HPTUseAndCash;
import com.tq.requisition.domain.model.housePuraseTicket.HousePuraseTicket;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.exception.SpecifiedObjectDoesNotExistsException;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.hpt.HPTDisplayFmlDto;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 购房券管理领域服务
 * @author jjh
 * @time 2015-01-01 10:48
 */
public interface IHPTService {
	/**
	 * 新增购房券信息,并且返回新增后的消息
	 * @param hpt
	 * 		待新增的购房券信息实体
	 * @return String
	 * 		返回一个String符号，描述新增结果
	 */
	void addHPT(HousePuraseTicket hpt) throws DomainException ;
	
	/**
	 * 批量新增购房券
	 * @param hpts
	 * 		购房券信息集合
	 * @throws DomainException
	 */
	void addHPT(List<HousePuraseTicket> hpts) throws DomainException ;
	
	/**
	 * 换券操作
	 * @param exHPT
	 * 		换券信息
	 */
	void exchange(HPTExchangeInfo exHPT,HousePuraseTicket newTicket) throws SpecifiedObjectDoesNotExistsException;
	
	/**
	 * 购房券领取
	 * @param revieveHPT
	 * 		领取信息
	 */
	void provider(HPTProviderInfo revieveHPT) throws SpecifiedObjectDoesNotExistsException;
	
	/**
	 * 购房券挂失
	 * @param lossPHT
	 * 		挂失信息
	 */
	void reportOfLoss(HPTReportOfLossInfo lossPHT) throws SpecifiedObjectDoesNotExistsException;
	
	/**
	 * 购房券补券
	 * @param mendHPT
	 * 		补券信息
	 */
	void mend(HPTMendInfo mendHPT,HousePuraseTicket newTicket) throws SpecifiedObjectDoesNotExistsException;
	
	/**
	 * 购房券的使用
	 * @param useAndCashHPT
	 * 		使用信息
	 */
	void useOrCash(HPTUseAndCash useAndCashHPT) throws SpecifiedObjectDoesNotExistsException;
	
	/**
	 * 根据户主身份查询该户购房券信息
	 * @param idNumber
	 * 		户主身份证
	 * @throws SpecifiedObjectDoesNotExistsException 
	 */
	public List<HPTDisplayFmlDto> queryByFml(String idNumber) throws SpecifiedObjectDoesNotExistsException;
	
	/**
	 * 根据项目ID获取未发放的购房券信息
	 * @param proId
	 * 		项目编号
	 * @return
	 * @throws SpecifiedObjectDoesNotExistsException
	 */
	public PageFormater queryNotByPro(String proName, PageModel pageModel) throws SpecifiedObjectDoesNotExistsException;
}
