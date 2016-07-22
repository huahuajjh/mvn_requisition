package com.tq.requisition.infrastructure.Specifications.visits;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.visits.Visits;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.infrastructure.utils.PageHelper;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.visits.VisitsQueryModel;

/**
 * 根据查询model查询集合，规约
 * @author jjh
 * @time 2016-01-13 15:59
 *
 */
public class VisitsQuerySpecification extends Specification<Visits>{
	private VisitsQueryModel queryModel;
	private PageModel pageModel;
	
	public VisitsQuerySpecification(Class<Visits> _t, VisitsQueryModel queryModel,PageModel pageModel) {
		super(_t);
		this.queryModel = queryModel;
		this.pageModel = pageModel;
	}
	
	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from tb_visit_info where is_del=0 ");
		if(queryModel.getProId()!=null)
		{
			sb.append(" and visit_pro_id='").append(queryModel.getProId().toString()+"'");
		}
		if(queryModel.getTel()!=null && queryModel.getTel().trim().equals(""))
		{
			sb.append(" and tel='").append(queryModel.getTel()+"'");
		}
		sb.append(" limit ").append(PageHelper.getPageIndex(pageModel.pageIndex, pageModel.pageSize)+" ").append(pageModel.pageSize);
		expression.setSql(sb.toString());
		expression.setType(OperationType.SQL);
		return expression;
	}

}
