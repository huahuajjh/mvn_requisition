package com.tq.requisition.infrastructure.Specifications.orgMgt;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.organization.Organization;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

public class OrgListSpecification extends Specification<Organization> {

	public OrgListSpecification(Class<Organization> _t) {
		super(_t);
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression
				.setSql("select * from tb_org where is_del=0");
		expression.setType(OperationType.SQL);
		expression.setParameters();
		return expression;
	}
}
