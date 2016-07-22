package com.tq.requisition.infrastructure.Specifications.orgMgt;

import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.organization.Organization;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

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
