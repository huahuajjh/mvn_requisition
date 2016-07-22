package com.tqmars.requisition.infrastructure.Specifications.orgMgt;

import java.util.UUID;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.organization.Organization;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;

public class OrgIdExistsSpecificator  extends Specification<Organization> {

	private String orgId;
	
	public OrgIdExistsSpecificator(Class<Organization> _t,UUID orgId) {
		super(_t);
		this.orgId = orgId.toString();
	}
	
	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select count(1) from tb_org where id=?");
		expression.setType(OperationType.SQL);
		expression.setParameters(orgId);
		return expression;
	}

}
