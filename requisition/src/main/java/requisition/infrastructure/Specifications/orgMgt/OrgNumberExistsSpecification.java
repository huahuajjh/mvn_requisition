package com.tq.requisition.infrastructure.Specifications.orgMgt;

import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.organization.Organization;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

public class OrgNumberExistsSpecification extends Specification<Organization> {
	private String orgNumber;
	private String id;

	public OrgNumberExistsSpecification(Class<Organization> _t, String orgNumber,UUID id) {
		super(_t);
		this.orgNumber = orgNumber;
		this.id = id.toString();
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression
				.setSql("select count(1) from tb_org where org_number=? and id <> ?");
		expression.setType(OperationType.SQL);
		expression.setParameters(new Object[]{orgNumber,id});
		return expression;
	}
}
