package com.tq.requisition.infrastructure.Specifications.project;

import java.util.Date;
import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.project.Project;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 检测项目月度时间是否唯一，规约
 * @author jjh
 * @time 2016-01-17 1:58
 *
 */
public class ProItemExistsDateSpecification extends Specification<Project>{
	private Date date;
	private UUID proId;
	
	public ProItemExistsDateSpecification(Class<Project> _t,Date date,UUID proId) {
		super(_t);
		this.date = date;
		this.proId = proId;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setHql("select count(*) from ProjectItem where date=? and proId=?");
		expression.setParameters(date,proId);
		expression.setType(OperationType.HQL);
		return expression;
	}

}
