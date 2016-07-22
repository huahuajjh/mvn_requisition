package com.tq.requisition.infrastructure.Specifications.hpt;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.housePuraseTicket.HousePuraseTicket;
import com.tq.requisition.domain.model.share.TicketState;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.presentation.dto.hpt.HPTFuzzyQueryModel;

/**
 * 根据查询model获取购房券使用台账
 * @author jjh
 * @time 2015-01-04 2:15
 *
 */
public class HPTUseTableSpecification extends Specification<HousePuraseTicket>{
	private HPTFuzzyQueryModel queryModel;
	
	public HPTUseTableSpecification(Class<HousePuraseTicket> _t,HPTFuzzyQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select new com.tq.requisition.presentation.dto.hpt.HPTUseTableDto(fi.proId,fi.proName,fi.idNumber,fi.name,fi.id,h.id,h.ticketNumber,h.bonus,u.usingDate,u.usingType,u.usingToWhere,u.situationExplain,u.evidencePath,h.makeDate)");
		sb.append(" from HPTUseAndCash u,FamilyItem fi,HousePuraseTicket h");
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
		expression.setParameters(TicketState.CASHED,TicketState.USED);
		expression.setHql(sb.toString());
		expression.setType(OperationType.HQL);
		return expression;
	}
}
