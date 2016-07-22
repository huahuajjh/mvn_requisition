package com.tq.requisition.infrastructure.Specifications.project;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.project.Project;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 根据项目id集合获取项目集合，规约
 * @author jjh
 * @time 2016-01-17 16:08
 *
 */
public class ProQuery4PrintSpecification extends Specification<Project>{
	private String uuids;
	
	public ProQuery4PrintSpecification(Class<Project> _t,String uuids) {
		super(_t);
		this.uuids = uuids;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select * from tb_project where id in("+uuids+")");
		expression.setType(OperationType.SQL);
		return expression;
	}	
}
