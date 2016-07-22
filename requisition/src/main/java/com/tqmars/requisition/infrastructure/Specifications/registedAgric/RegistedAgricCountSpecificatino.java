package com.tqmars.requisition.infrastructure.Specifications.registedAgric;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.model.registedAgricultureInfo.RegistedAgricultureInfo;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tqmars.requisition.presentation.dto.registedAgr.RegistedAgricQueryModel;

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
