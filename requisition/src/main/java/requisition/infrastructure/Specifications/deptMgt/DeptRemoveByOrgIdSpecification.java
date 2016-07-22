package com.tq.requisition.infrastructure.Specifications.deptMgt;

import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.department.Department;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

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
