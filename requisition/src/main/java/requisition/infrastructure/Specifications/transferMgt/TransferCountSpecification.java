package com.tq.requisition.infrastructure.Specifications.transferMgt;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.transferHouseholdInfo.TransferHouseholdInfo;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.presentation.dto.transferMgt.TransferInfoQueryModel;

/**
 * 转户信息模糊查询规约,查询总行数
 * @author jjh
 * @time 2015-12-30 00:18
 */
public class TransferCountSpecification extends Specification<TransferHouseholdInfo>{
	private TransferInfoQueryModel queryModel;
	
	public TransferCountSpecification(Class<TransferHouseholdInfo> _t,TransferInfoQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("select count(1) from tb_transfer_household_info t ");
		sb.append(" inner join tb_family_item i	on t.fml_item_id = i.id where 1=1 ");
		if(queryModel.getProName()!=null)
		{
			sb.append(" and i.pro_name like " + "'%" + queryModel.getProName() + "%'");
		}
		if(queryModel.getStreetId()!=null)
		{
			sb.append(" and t.street_id=?");
			list.add(queryModel.getStreetId().toString());
		}
		if(queryModel.getCommunityId()!=null)
		{
			sb.append(" and t.community_id=?");
			list.add(queryModel.getCommunityId().toString());
		}
		if(queryModel.getGroupId()!=null)
		{
			sb.append(" and t.group_id=?");
			list.add(queryModel.getGroupId().toString());
		}
		Object[] objects = new Object[list.size()];
		for (int i = 0; i < objects.length; i++) {
			objects[i] = list.get(i);
		}
		expression.setSql(sb.toString());
		expression.setParameters(objects);
		expression.setType(OperationType.SQL);
		return expression;
	}
}
