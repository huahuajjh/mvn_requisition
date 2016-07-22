package com.tqmars.requisition.infrastructure.Specifications.deptMgt;

import java.util.UUID;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.department.Department;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 规约，根据组织id删除部门条件类
 * @author jjh
 * @time 2015-12-26 14:59
 */
public class DeptRemoveByOrgIdSpecification extends Specification<Department>{
	private String orgId;

	public DeptRemoveByOrgIdSpecification(Class<Department> _t,UUID _orgId) {
		super(_t);
		orgId = _orgId.toString();
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("delete from tb_dept where org_id=?");
		expression.setParameters(orgId);
		expression.setType(OperationType.SQL);
		return expression;
	}

}
