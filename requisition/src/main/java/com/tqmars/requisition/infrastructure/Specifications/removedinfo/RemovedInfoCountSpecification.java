package com.tqmars.requisition.infrastructure.Specifications.removedinfo;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.removedInfo.RemovedInfo;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tqmars.requisition.presentation.dto.removedinfo.RemovedInfoQueryModel;

/**
 * 根据查询model获取总记录数，规约
 * @author jjh
 * @time 2016-01-13 13:57
 *
 */
public class RemovedInfoCountSpecification extends Specification<RemovedInfo>{
	private RemovedInfoQueryModel queryModel;
	
	public RemovedInfoCountSpecification(Class<RemovedInfo> _t,RemovedInfoQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from tb_removed_info where 1=1 ");
		if(queryModel.getCommunityId()!=null){
			sb.append(" and community_id='").append(queryModel.getCommunityId().toString()+"'");
		}
		if(queryModel.getStreetId()!=null){
			sb.append(" and street_id='").append(queryModel.getStreetId().toString()+"'");
		}
		expression.setSql(sb.toString());
		expression.setType(OperationType.SQL);
		return expression;
	}
}
