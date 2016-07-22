package com.tq.requisition.infrastructure.Specifications.hpt;

import java.util.List;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.domain.model.housePuraseTicket.HousePuraseTicket;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

public class HPTQueryFmlItemProvideCountSpecification extends Specification<HousePuraseTicket> {

	private String proName = "";
	
	public HPTQueryFmlItemProvideCountSpecification(Class<HousePuraseTicket> _t, String proName) {
		super(_t);
		this.proName = proName;
	}
	
	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select count(0) from tb_family_item fi, tb_housepurchase_ticket h");
		sb.append(" where h.fml_item_id=fi.id and h.is_del=0 and h.ticket_state_id = 6 and fi.pro_name = ?");
		expression.setSql(sb.toString());
		expression.setParameters(proName);
		expression.setType(OperationType.SQL);
		return expression;
	}
}
