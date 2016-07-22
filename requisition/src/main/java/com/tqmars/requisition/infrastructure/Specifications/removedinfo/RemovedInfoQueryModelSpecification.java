package com.tqmars.requisition.infrastructure.Specifications.removedinfo;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.removedInfo.RemovedInfo;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tqmars.requisition.infrastructure.utils.PageHelper;
import com.tqmars.requisition.presentation.dto.removedinfo.RemovedInfoQueryModel;
import com.tqmars.requisition.presentation.dto.share.PageModel;

/**
 * 规约，根据查询model查询
 * @author jjh
 * @time 2016-01-11 22:42
 *
 */
public class RemovedInfoQueryModelSpecification extends Specification<RemovedInfo>{
	private RemovedInfoQueryModel queryModel;
	private PageModel pageModel;
	
	public RemovedInfoQueryModelSpecification(Class<RemovedInfo> _t,RemovedInfoQueryModel queryModel,PageModel pageModel) {
		super(_t);
		this.queryModel = queryModel;
		this.pageModel = pageModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		StringBuilder sb = new StringBuilder();
		IHqlExpression expression = new HqlExpression();
		sb.append("select * from tb_removed_info where 1=1 ");		
		if(queryModel.getCommunityId()!=null){
			sb.append(" and community_id='").append(queryModel.getCommunityId().toString()).append("'");			
		}
		if(queryModel.getStreetId()!=null)
		{
			sb.append(" and street_id='").append(queryModel.getStreetId().toString()).append("'");
		}
		sb.append(" limit ").append(PageHelper.getPageIndex(pageModel.pageIndex, pageModel.pageSize)+" ").append(pageModel.pageSize);
		expression.setSql(sb.toString());
		expression.setType(OperationType.SQL);
		return expression;
	}
	
}
