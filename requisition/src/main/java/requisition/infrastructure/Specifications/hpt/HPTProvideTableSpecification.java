package com.tq.requisition.infrastructure.Specifications.hpt;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.housePuraseTicket.HousePuraseTicket;
import com.tq.requisition.domain.model.share.TicketState;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.presentation.dto.hpt.HPTFuzzyQueryModel;

/**
 * 购房券发放台账规约查询条件
 * @author jjh
 * @time 2015-01-04 1:39
 *
 */
public class HPTProvideTableSpecification extends Specification<HousePuraseTicket>{
	private HPTFuzzyQueryModel queryModel;
	
	public HPTProvideTableSpecification(Class<HousePuraseTicket> _t,HPTFuzzyQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select new com.tq.requisition.presentation.dto.hpt.HPTReceiveTableDto(");
		sb.append(" fi.proId,fi.proName,fi.id,h.id,fi.name,fi.idNumber,pi.name,h.ticketNumber,pi.evidenceOfGetting,pi.gettingDate,h.bonus) ");
		sb.append(" from HPTProviderInfo pi, HousePuraseTicket h,FamilyItem fi");
		sb.append(" where h.fmlItemId=fi.id and pi.ticketId=h.id and h.del=false and (h.state=? or h.state=? or h.state=?)");
		if(queryModel.getProName()!=null)
		{
			sb.append(" and fi.proName like '%"+queryModel.getProName()+"%'");
		}
		if(queryModel.getIdNumber()!=null && !(queryModel.getIdNumber().trim().equals(""))){
			sb.append(" and fi.idNumber='"+queryModel.getIdNumber()+"'");	
		}
		if(queryModel.getName()!=null && !(queryModel.getName().trim().equals("")))
		{
			sb.append(" and fi.name like '%"+queryModel.getName()+"%'");
		}
		if(queryModel.getTicketNumber()!=null && !(queryModel.getTicketNumber().trim().equals(""))){
			sb.append(" and h.ticketNumber='"+queryModel.getTicketNumber()+"'");
		}
		expression.setHql(sb.toString());
		expression.setParameters(TicketState.RECEIVED,TicketState.USED,TicketState.CASHED);
		expression.setType(OperationType.HQL);
		return expression;
	}
}
