package com.tq.requisition.infrastructure.Specifications.hpt;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.housePuraseTicket.HousePuraseTicket;
import com.tq.requisition.domain.model.share.TicketState;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.presentation.dto.hpt.HPTFuzzyQueryModel;

/**
 * 查询规约，根据查询model返回总记录数
 * @author jjh
 * @time 2015-01-02 22:48
 */
public class HPTProvideCountSpecification extends Specification<HousePuraseTicket>{
	private HPTFuzzyQueryModel queryModel;
	
	public HPTProvideCountSpecification(Class<HousePuraseTicket> _t,HPTFuzzyQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from HousePuraseTicket t,FamilyItem i");		
		sb.append(" where  t.fmlItemId=i.id and t.del=false and (t.state=? or t.state=? or t.state=?)");
		if(queryModel.getProName()!=null)
		{
			sb.append(" and i.proName like '%"+queryModel.getProName()+"%'");
		}
		
		if(queryModel.getIdNumber()!=null && !(queryModel.getIdNumber().trim().equals(""))){
			sb.append(" and i.idNumber='"+queryModel.getIdNumber()+"'");	
		}
		if(queryModel.getName()!=null && !(queryModel.getName().trim().equals("")))
		{
			sb.append(" and i.name like '%"+queryModel.getName()+"%'");
		}
		if(queryModel.getTicketNumber()!=null && !(queryModel.getTicketNumber().trim().equals(""))){
			sb.append(" and t.ticketNumber='"+queryModel.getTicketNumber()+"'");
		}
		expression.setHql(sb.toString());
		expression.setParameters(TicketState.RECEIVED,TicketState.USED,TicketState.CASHED);
		expression.setType(OperationType.HQL);
		return expression;
	}
	
}
