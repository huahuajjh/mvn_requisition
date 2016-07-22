package com.tq.requisition.infrastructure.Specifications.hpt;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.domain.model.housePuraseTicket.HousePuraseTicket;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

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
		sb.append("select new com.tq.requisition.presentation.dto.hpt.HPTDisplayFmlDto(");
		sb.append(" fi.proName,fi.proId,h.id,fi.id,fi.name,fi.idNumber,h.ticketNumber,h.bonus,h.state,h.makeDate,fi.relationshipStr,fi.otherRelationship) ");
		sb.append(" from FamilyItem fi, HousePuraseTicket h");
		sb.append(" where h.fmlItemId=fi.id and h.del=false and h.state = 6 and fi.proName=?");
		expression.setHql(sb.toString());
		expression.setParameters(proName);
		expression.setType(OperationType.HQL);
		return expression;
	}
}
