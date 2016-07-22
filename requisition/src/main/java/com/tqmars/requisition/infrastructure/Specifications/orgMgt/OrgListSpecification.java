package com.tqmars.requisition.infrastructure.Specifications.orgMgt;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.organization.Organization;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;

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
