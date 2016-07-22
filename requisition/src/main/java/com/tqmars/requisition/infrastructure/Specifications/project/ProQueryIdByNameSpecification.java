package com.tqmars.requisition.infrastructure.Specifications.project;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.project.Project;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 规约，根据项目名称获取项目id
 * @author jjh
 * @time 2016-01-17 20:11
 *
 */
public class ProQueryIdByNameSpecification extends Specification<Project>{
	private String proName;
	
	public ProQueryIdByNameSpecification(Class<Project> _t,String proName) {
		super(_t);
		this.proName = proName;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select id from tb_project where pro_name=?");
		expression.setParameters(proName);
		expression.setType(OperationType.SQL);
		return expression;
	}
	
}
