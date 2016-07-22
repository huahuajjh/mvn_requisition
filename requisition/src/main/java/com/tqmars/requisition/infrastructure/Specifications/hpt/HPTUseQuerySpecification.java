package com.tqmars.requisition.infrastructure.Specifications.hpt;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.share.TicketState;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tqmars.requisition.presentation.dto.hpt.HPTDisplayDto;
import com.tqmars.requisition.presentation.dto.hpt.HptUseAndCashQueryModel;

public class HPTUseQuerySpecification extends Specification<HPTDisplayDto> {

	private HptUseAndCashQueryModel queryModel;
	
	public HPTUseQuerySpecification(Class<HPTDisplayDto> _t,HptUseAndCashQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}
	
	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();		
		StringBuilder sb = new StringBuilder();
		sb.append("select new com.tqmars.requisition.presentation.dto.hpt.HPTDisplayDto(");
		sb.append(" fi.proName,fi.proId,h.id,fi.id,fi.name,fi.idNumber,h.ticketNumber,h.bonus,h.state,h.makeDate)");
		sb.append(" from HousePuraseTicket h,FamilyItem fi ");
		sb.append(" where h.fmlItemId=fi.id and h.del=false and h.state=? ");
		if(queryModel.getName() != null && !queryModel.getName().equals("")){
			sb.append(" and fi.name like '%" + queryModel.getName() + "%'");
		}
		if(queryModel.getFmlItemId() != null){
			sb.append(" and (fi.id ='" + queryModel.getFmlItemId().toString() + "' or fi.fmlId = '"+queryModel.getFmlItemId().toString()+"')");
		}
		if(queryModel.getStreetId() != null){
			sb.append(" and fi.streetId='" + queryModel.getStreetId().toString()  + "'");
		}
		if(queryModel.getCommunityId() != null){
			sb.append(" and fi.communityId='" + queryModel.getCommunityId().toString() + "'");
		}
		if(queryModel.getGroupId() != null){
			sb.append(" and fi.groupId='" + queryModel.getGroupId().toString() + "'");
		}
		if(queryModel.getProName() !=null && !queryModel.getProName().equals("")){
			sb.append(" and fi.proName like '%" + queryModel.getProName() +"%'");
		}
		if(queryModel.getAddress()!=null && !queryModel.getAddress().equals(""))
		{
			sb.append(" and fi.address like " + "'%" + queryModel.getAddress() + "%'");
		}
		expression.setHql(sb.toString());
		expression.setParameters(TicketState.RECEIVED);
		expression.setType(OperationType.HQL);
		return expression;
	}

}
