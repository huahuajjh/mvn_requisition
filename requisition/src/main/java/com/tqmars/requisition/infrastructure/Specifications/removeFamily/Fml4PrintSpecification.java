package com.tqmars.requisition.infrastructure.Specifications.removeFamily;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.removeFamily.Family;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;

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
