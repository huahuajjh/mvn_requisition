package com.tq.requisition.infrastructure.Specifications.removeFamily;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.removeFamily.Family;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

public class Fml4PrintSpecification extends Specification<Family>{
	private String uuids;
	
	public Fml4PrintSpecification(Class<Family> _t,String uuids) {
		super(_t);
		this.uuids = uuids;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select * from tb_family where id in("+uuids+")");
		expression.setType(OperationType.SQL);
		return expression;
	}	
}
