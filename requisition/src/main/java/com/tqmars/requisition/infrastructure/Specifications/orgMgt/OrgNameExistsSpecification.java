package com.tqmars.requisition.infrastructure.Specifications.orgMgt;

import java.util.UUID;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.organization.Organization;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;

public class OrgNameExistsSpecification extends Specification<Organization> {
	private String orgName;
	private String id;

	public OrgNameExistsSpecification(Class<Organization> _t, String orgName,UUID id) {
		super(_t);
		this.orgName = orgName;
		this.id = id.toString();
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression
				.setSql("select count(1) from tb_org where org_name=? and id <> ?");
		expression.setType(OperationType.SQL);
		expression.setParameters(new Object[]{orgName,id});
		return expression;
	}
}
