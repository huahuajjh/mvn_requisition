package com.tq.requisition.infrastructure.Specifications.registedAgric;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.model.registedAgricultureInfo.RegistedAgricultureInfo;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.presentation.dto.registedAgr.RegistedAgricQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 根据查询model获取数据，规约
 * @author jjh
 * @time 2016-01-13 14:14
 *
 */
public class RegistedAgricQuerySpecification extends Specification<RegistedAgricultureInfo>{
	private RegistedAgricQueryModel queryModel;
	private PageModel pageModel;
	
	public RegistedAgricQuerySpecification(Class<RegistedAgricultureInfo> _t,RegistedAgricQueryModel queryModel, PageModel pageModel) {
		super(_t);
		this.queryModel = queryModel;
		this.pageModel = pageModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		StringBuilder sb = new StringBuilder();
		IHqlExpression expression = new HqlExpression();
		sb.append("select * from tb_registed_argc_info where 1=1 ");		

		return expression;
	}
}
