package com.tq.requisition.infrastructure.Specifications.hpt;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.housePuraseTicket.HousePuraseTicket;
import com.tq.requisition.domain.model.share.TicketState;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.presentation.dto.hpt.HPTFuzzyQueryModel;

/**
 * 规约，根据查询model获取购房券使用台账总记录
 * @author jjh
 * @time 2015-01-04 2::09
 *
 */
public class HPTUseCountSpecification extends Specification<HousePuraseTicket>{
	private HPTFuzzyQueryModel queryModel;
	
	public HPTUseCountSpecification(Class<HousePuraseTicket> _t,HPTFuzzyQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from HPTUseAndCash u,HousePuraseTicket h,FamilyItem fi ");
		sb.append(" where u.ticketId=h.id and u.del=false and h.fmlItemId=fi.id and h.del=false");
		sb.append(" and (h.state=? or h.state=?)");
		if(queryModel.getProName()!=null)
		{
			sb.append(" and fi.proName like '%"+queryModel.getProName()+"%'");
		}
		if(queryModel.getHuFmlId() != null){
			sb.append(" and (fi.id ='" + queryModel.getHuFmlId().toString() + "' or fi.fmlId = '"+queryModel.getHuFmlId().toString()+"')");
		}
		if(queryModel.getTicketNumber()!=null){
			sb.append(" and h.ticketNumber='"+queryModel.getTicketNumber()+"'");
		}
		if(queryModel.getIdNumber()!=null && !(queryModel.getIdNumber().trim().equals("")))
		{
			sb.append(" and fi.idNumber='"+queryModel.getIdNumber()+"'");
		}
		if(queryModel.getName()!=null && !(queryModel.getName().trim().equals("")))
		{
			sb.append(" and fi.name like '%"+queryModel.getName()+"%'");
		}
		expression.setHql(sb.toString());
		expression.setParameters(TicketState.CASHED,TicketState.USED);
		expression.setType(OperationType.HQL);
		return expression;
	}
}
