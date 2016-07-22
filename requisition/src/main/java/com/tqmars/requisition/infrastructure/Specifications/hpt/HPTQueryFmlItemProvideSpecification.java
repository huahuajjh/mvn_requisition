package com.tqmars.requisition.infrastructure.Specifications.hpt;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.housePuraseTicket.HousePuraseTicket;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;

public class HPTQueryFmlItemProvideSpecification extends Specification<HousePuraseTicket> {
	
	private String proName = "";
	
	public HPTQueryFmlItemProvideSpecification(Class<HousePuraseTicket> _t, String proName) {
		super(_t);
		this.proName = proName;
	}
	
	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select new com.tqmars.requisition.presentation.dto.hpt.HPTDisplayFmlDto(");
		sb.append(" fi.proName,fi.proId,h.id,fi.id,fi.name,fi.idNumber,h.ticketNumber,h.bonus,h.state,h.makeDate,fi.relationshipStr,fi.otherRelationship) ");
		sb.append(" from FamilyItem fi, HousePuraseTicket h");
		sb.append(" where h.fmlItemId=fi.id and h.del=false and h.state = 6 and fi.proName=?");
		expression.setHql(sb.toString());
		expression.setParameters(proName);
		expression.setType(OperationType.HQL);
		return expression;
	}
}
