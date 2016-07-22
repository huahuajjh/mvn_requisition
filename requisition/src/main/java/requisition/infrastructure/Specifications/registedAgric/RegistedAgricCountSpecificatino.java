package com.tq.requisition.infrastructure.Specifications.registedAgric;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.model.registedAgricultureInfo.RegistedAgricultureInfo;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.presentation.dto.registedAgr.RegistedAgricQueryModel;

public class RegistedAgricCountSpecificatino extends Specification<RegistedAgricultureInfo>{
	private RegistedAgricQueryModel queryModel;
	
	public RegistedAgricCountSpecificatino(Class<RegistedAgricultureInfo> _t,RegistedAgricQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}
	
	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from tb_registed_argc_info where 1=1 ");

		return expression;
	}
}
