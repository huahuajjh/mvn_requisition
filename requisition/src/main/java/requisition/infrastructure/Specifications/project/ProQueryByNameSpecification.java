package com.tq.requisition.infrastructure.Specifications.project;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.project.Project;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

public class ProQueryByNameSpecification extends Specification<Project>{
	private String name;
	
	public ProQueryByNameSpecification(Class<Project> _t,String proName) {
		super(_t);
		this.name = proName;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select * from tb_project where pro_name=?");
		expression.setParameters(name);
		expression.setType(OperationType.SQL);
		return expression;
	}

}
