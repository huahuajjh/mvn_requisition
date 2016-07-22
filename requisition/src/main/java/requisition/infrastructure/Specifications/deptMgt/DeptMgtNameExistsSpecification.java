package com.tq.requisition.infrastructure.Specifications.deptMgt;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.department.Department;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 組織管理規約條件類
 * @author jjh
 * @time 2015-12-24 16:54
 */
public class DeptMgtNameExistsSpecification extends Specification<Department>{
	private Department dept;
	
	public DeptMgtNameExistsSpecification(Class<Department> _t,Department _dept) {
		super(_t);
		this.dept = _dept;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select count(1) from tb_dept where dept_name=? and org_id=?");
		expression.setParameters(dept.getDeptName(),dept.getOrgId().toString());
		expression.setType(OperationType.SQL);
		return expression;
	}
	
}
