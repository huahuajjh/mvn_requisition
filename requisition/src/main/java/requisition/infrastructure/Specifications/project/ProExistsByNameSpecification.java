package com.tq.requisition.infrastructure.Specifications.project;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.project.Project;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 规约，根据项目名称判断是否存在
 * @author jjh
 * @time 2016-01-15 17:44
 *
 */
public class ProExistsByNameSpecification extends Specification<Project>{
	private String name;
	
	public ProExistsByNameSpecification(Class<Project> _t,String proName) {
		super(_t);
		this.name = proName;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select count(1) from tb_project where pro_name=?");
		expression.setParameters(name);
		expression.setType(OperationType.SQL);
		return expression;
	}

}
