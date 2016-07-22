package com.tqmars.requisition.infrastructure.Specifications.hpt;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.housePuraseTicket.HousePuraseTicket;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 查询规约，根据身份证查询记录
 * @author jjh
 * @time 2015-01-02 23:13
 */
public class HPTQueryByIdNumSpecification extends Specification<HousePuraseTicket>{
	private String idNumber;
	
	public HPTQueryByIdNumSpecification(Class<HousePuraseTicket> _t,String idNumber) {
		super(_t);
		this.idNumber = idNumber;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();		
		StringBuilder sb = new StringBuilder();
		sb.append("select new com.tqmars.requisition.presentation.dto.hpt.HPTDisplayDto(");
		sb.append(" fi.proName,fi.proId,h.id,fi.id,fi.name,fi.idNumber,h.ticketNumber,h.bonus,h.state,h.makeDate) ");
		sb.append(" from HousePuraseTicket h,FamilyItem fi ");
		sb.append(" where h.fmlItemId=fi.id and h.del=? and fi.idNumber=?");	
		expression.setHql(sb.toString());
		expression.setParameters(false,idNumber);
		expression.setType(OperationType.HQL);
		return expression;
	}

}
