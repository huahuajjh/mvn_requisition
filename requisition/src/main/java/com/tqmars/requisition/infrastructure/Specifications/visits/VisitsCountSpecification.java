package com.tqmars.requisition.infrastructure.Specifications.visits;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.visits.Visits;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tqmars.requisition.presentation.dto.visits.VisitsQueryModel;

/**
 * 根据查询model查询上访总记录数，规约
 * @author jjh
 * @time 2016-01-13 15:54
 *
 */
public class VisitsCountSpecification extends Specification<Visits>{
	private VisitsQueryModel queryModel;
	
	public VisitsCountSpecification(Class<Visits> _t,VisitsQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from tb_visit_info where is_del=0 ");
		if(queryModel.getProId()!=null)
		{
			sb.append(" and visit_pro_id='").append(queryModel.getProId().toString()+"'");
		}
		if(queryModel.getTel()!=null && queryModel.getTel().trim().equals(""))
		{
			sb.append(" and tel='").append(queryModel.getTel()+"'");
		}
		expression.setSql(sb.toString());
		expression.setType(OperationType.SQL);
		return expression;
	}
}

