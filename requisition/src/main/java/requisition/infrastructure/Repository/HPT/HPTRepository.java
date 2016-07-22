package com.tq.requisition.infrastructure.Repository.HPT;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.domain.model.housePuraseTicket.HPTExchangeInfo;
import com.tq.requisition.domain.model.housePuraseTicket.HPTMendInfo;
import com.tq.requisition.domain.model.housePuraseTicket.HPTProviderInfo;
import com.tq.requisition.domain.model.housePuraseTicket.HPTReportOfLossInfo;
import com.tq.requisition.domain.model.housePuraseTicket.HPTUseAndCash;
import com.tq.requisition.domain.model.housePuraseTicket.HousePuraseTicket;
import com.tq.requisition.domain.model.housePuraseTicket.IHPTRepository;
import com.tq.requisition.domain.model.removeFamily.Family;
import com.tq.requisition.domain.model.share.TicketState;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.exception.SpecifiedObjectDoesNotExistsException;
import com.tq.requisition.infrastructure.Repository.HbRepository;
import com.tq.requisition.infrastructure.Specifications.hpt.HPTProvideCountSpecification;
import com.tq.requisition.infrastructure.Specifications.hpt.HPTProvideTableSpecification;
import com.tq.requisition.infrastructure.Specifications.hpt.HPTQueryByIdNumSpecification;
import com.tq.requisition.infrastructure.Specifications.hpt.HPTQueryFmlItemProvideCountSpecification;
import com.tq.requisition.infrastructure.Specifications.hpt.HPTQueryFmlItemProvideSpecification;
import com.tq.requisition.infrastructure.Specifications.hpt.HPTQueryFmlProvideSpecification;
import com.tq.requisition.infrastructure.Specifications.hpt.HPTUseCountSpecification;
import com.tq.requisition.infrastructure.Specifications.hpt.HPTUseQuerySpecification;
import com.tq.requisition.infrastructure.Specifications.hpt.HPTUseTableSpecification;
import com.tq.requisition.infrastructure.Specifications.hpt.HptUseTotalCountSpecification;
import com.tq.requisition.infrastructure.Specifications.removeFamily.FmlBasicInfoSpecification;
import com.tq.requisition.infrastructure.Specifications.removeFamily.FmlTotalCountSpecification;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.infrastructure.utils.PageHelper;
import com.tq.requisition.presentation.dto.hpt.HPTDisplayDto;
import com.tq.requisition.presentation.dto.hpt.HPTDisplayFmlDto;
import com.tq.requisition.presentation.dto.hpt.HPTFuzzyQueryModel;
import com.tq.requisition.presentation.dto.hpt.HPTQueryModel;
import com.tq.requisition.presentation.dto.hpt.HPTReceiveTableDto;
import com.tq.requisition.presentation.dto.hpt.HPTUseTableDto;
import com.tq.requisition.presentation.dto.hpt.HptUseAndCashQueryModel;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyBasicInfoDto;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 购房券仓储实现
 * @author jjh
 * @time 2015-01-01 10:37
 */
public class HPTRepository extends HbRepository<HousePuraseTicket> implements IHPTRepository{

	public HPTRepository(IRepositoryContext context) {
		super(context);
	}

	@Override
	public void addHPT(final HousePuraseTicket hpt) throws DomainException {
		if(null == hpt)
		{
			throw new NullPointerException("新增的购房券信息为空");
		}
		//检测重复添加
		int count = getTotalCount(new SpecificationExt<HousePuraseTicket>(HousePuraseTicket.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select count(1) from tb_housepurchase_ticket where is_del=0 and id_number=?";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{hpt.getIdNumber()};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		boolean state = exists(new SpecificationExt<HousePuraseTicket>(HousePuraseTicket.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select count(1) from tb_housepurchase_ticket where is_del=0 and id_number=? and ticket_number=?";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{hpt.getIdNumber(),hpt.getTicketNumber().trim()};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		if(count >= 2 || state){throw new DomainException("已经为该人员添加了购房券");}
		add(hpt);
	}

	@Override
	public void exchange(HPTExchangeInfo exHPT,HousePuraseTicket newTicket) throws SpecifiedObjectDoesNotExistsException {
		if(null == exHPT || null == newTicket)
		{
			throw new NullPointerException("新增购房券信息或者换券信息为空");
		}
		//获取要被更换的购房券
		HousePuraseTicket ticket = getByKey(HousePuraseTicket.class, exHPT.getOldTicketId());
		if(null == ticket){throw new NullPointerException("未查询到要更换的购房券信息");}
		//换券操作
		ticket.exchange();
		//持久化新增的购房券
		add(newTicket);
		//更新换券信息
		exHPT.setNewTicketId(newTicket.getId());
		//持久化换券信息
		add(exHPT);
	}

	@Override
	public void provider(HPTProviderInfo revieveHPT) throws SpecifiedObjectDoesNotExistsException {
		if (null == revieveHPT) {
			throw new NullPointerException("发放实体是空");
		}		
		//查询待领取购房券的状态，修改对应购房券状态
		HousePuraseTicket ticket = getByKey(HousePuraseTicket.class, revieveHPT.getTicketId());
		if(null == ticket)
		{
			throw new NullPointerException("未查询到指定的购房券的信息");
		}
		//执行领券操作，如果不符合业务规则，抛出异常
		ticket.receive();
		//更新购房券状态
		update(ticket);
		//持久化购房券发放信息
		add(revieveHPT);
	}

	@Override
	public void reportOfLoss(HPTReportOfLossInfo lossPHT) throws SpecifiedObjectDoesNotExistsException {
		//根据挂失信息中的待挂失券的id，查询状态
		HousePuraseTicket ticket = getByKey(HousePuraseTicket.class,lossPHT.getTicketId());
		if(null == ticket)
		{
			throw new NullPointerException("未查询到指定的购房券信息");
		}
		//挂失操作
		ticket.reportOfLoss();
		//更新购房券状态为挂失
		update(ticket);
		//持久化挂失信息
		add(lossPHT);
	}

	@Override
	public void mend(HPTMendInfo mendHPT,HousePuraseTicket newTicket) throws SpecifiedObjectDoesNotExistsException {
		if(null == mendHPT || null == newTicket)
		{
			throw new NullPointerException("补券信息或者新券为空");
		}
		//获取购房券状态
		HousePuraseTicket ticket = getByKey(HousePuraseTicket.class, mendHPT.getTicketId());
		if(null == ticket)
		{
			throw new NullPointerException("未查询到购房券信息");
		}
		//补券操作
		ticket.mend();
		//持久化新券
		newTicket.setState(TicketState.NORMAL);
		add(newTicket);
		//更新购房券状态
		update(ticket);
		//持久化补券信息
		add(mendHPT);
	}

	@Override
	public void useOrCash(HPTUseAndCash useAndCashHPT) throws SpecifiedObjectDoesNotExistsException {
		if(null == useAndCashHPT){throw new NullPointerException("使用或兑现信息为空");}
		//查询购房券		
		HousePuraseTicket ticket = getByKey(HousePuraseTicket.class, useAndCashHPT.getTicketId());
		if(null == ticket){throw new NullPointerException("未查询到指定的购房券信息");}
		//使用购房券操作
		ticket.useAndCash();
		//更新购房券状态
		update(ticket);
		//持久化使用或者兑换信息
		add(useAndCashHPT);		
	}
	
	@Override
	public void addBatch(List<HousePuraseTicket> list) throws DomainException  {
		if(null==list)
		{
			throw new NullPointerException("购房券集合为空");
		}
		for (HousePuraseTicket housePuraseTicket : list) {
			addHPT(housePuraseTicket);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageFormater queryProvideTable(
			HPTFuzzyQueryModel queryModel,PageModel pageModel) {
		
		int count = getTotalCount(new HPTProvideCountSpecification(HousePuraseTicket.class, queryModel));		
		if(count==0)
		{
			return PageFormater.obtain(null, count);
		}
		List<HPTReceiveTableDto> list = getAllByHqlJoin(//
				new HPTProvideTableSpecification(HousePuraseTicket.class, queryModel),//
				PageHelper.getPageIndex(pageModel.pageIndex, pageModel.pageSize),//
				pageModel.pageSize);
		
		return PageFormater.obtain(list, count);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageFormater queryUseTable(HPTFuzzyQueryModel queryModel,PageModel pageModel) {
		int count = getTotalCount(new HPTUseCountSpecification(HousePuraseTicket.class, queryModel));
		if(count==0){return PageFormater.obtain(null, count);}
		
		List<HPTUseTableDto> list = getAllByHqlJoin(//
				new HPTUseTableSpecification(HousePuraseTicket.class, queryModel), //
				PageHelper.getPageIndex(pageModel.pageIndex, pageModel.pageSize), //
				pageModel.pageSize);
		
		return PageFormater.obtain(list, count);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HPTDisplayDto> queryByIdnumber(String idNumber) {
		List<HPTDisplayDto> list = getAllByHqlJoin(new HPTQueryByIdNumSpecification(HousePuraseTicket.class, idNumber), 0, 10);
		if(null == list || list.size()==0)
		{
			throw new NullPointerException("未查询到购房券信息");
		}
		return list;
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<HPTDisplayFmlDto> queryFmlByFmlId(UUID fmlId) {
		//根据户主身份证查询户主信息
		
		List<HPTDisplayFmlDto> list = getAllByHqlJoin(
				new HPTQueryFmlProvideSpecification(HousePuraseTicket.class, fmlId),0,999);
		
		return list;
		
	}
	
	@Override
	public PageFormater queryNotFmlByFmlItem(String proName, PageModel pageModel) {
		
		int count = getTotalCount(new HPTQueryFmlItemProvideCountSpecification(HousePuraseTicket.class, proName));
		if(count <= 0){
			return PageFormater.obtain(null, 0);
		}
		List<HPTDisplayFmlDto> list = getAllByHqlJoin(
				new HPTQueryFmlItemProvideSpecification(HousePuraseTicket.class, proName),
				PageHelper.getPageIndex(pageModel.pageIndex, pageModel.pageSize), //
				pageModel.pageSize);
		return PageFormater.obtain(list, count);
	}
	
	@Override
	public HPTDisplayDto queryByIdnumberAndTicketNum(HPTQueryModel queryModel) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void provideByFml(List<HPTProviderInfo> list) throws SpecifiedObjectDoesNotExistsException {
		if(null == list){throw new NullPointerException("待发放的户集合信息为空");}
		for (HPTProviderInfo hptProviderInfo : list) {
			provider(hptProviderInfo);
		}
		
	}

	@Override
	public PageFormater queryByPage(HptUseAndCashQueryModel queryModel,
			PageModel pageModel) {
		int count = getTotalCount(new HptUseTotalCountSpecification(HousePuraseTicket.class, queryModel));
		if(count==0){
			return PageFormater.obtain(null, count);
		}
		
		List<HPTDisplayDto> list = getAllByHqlJoin(
				new HPTUseQuerySpecification(HPTDisplayDto.class, queryModel),PageHelper.getPageIndex(pageModel.pageIndex, pageModel.pageSize), //
				pageModel.pageSize);
				
		return PageFormater.obtain(list, count);
	}

	@Override
	public List<HPTReceiveTableDto> getHPTReceiveTableDtoAll(String proName) {
		HPTFuzzyQueryModel queryModel = new HPTFuzzyQueryModel();
		queryModel.setProName(proName);
		return getAllByHqlJoin(//
				new HPTProvideTableSpecification(HousePuraseTicket.class, queryModel),//
				PageHelper.getPageIndex(1, 20000),//
				20000);
	}
	
}
