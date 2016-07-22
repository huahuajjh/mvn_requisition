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
 * 转户信息模糊查询规约
 * @author jjh
 * @time 2015-12-30 00:18
 */
public class TransferQueryFuzzySpecifiaction extends Specification<TransferHouseholdInfo>{
	private TransferInfoQueryModel queryModel;
	public TransferQueryFuzzySpecifiaction(Class<TransferHouseholdInfo> _t,TransferInfoQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}
	
	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select new com.tq.requisition.presentation.dto.transferMgt.TransferInfoDto4Table(f.proId,f.proName,f.name,f.idNumber,f.householdStr,t.transferDate,t.address,f.id,t.id,f.householdId,");
		sb.append("t.streetId,t.communityId,t.groupId)");
		sb.append(" from TransferHouseholdInfo t,FamilyItem f");//
		sb.append(" where t.fmlItemId=f.id");
		List<Object> list = new ArrayList<Object>();
		if(queryModel.getProName()!=null)
		{
			sb.append(" and f.proName like " + "'%" + queryModel.getProName() + "%'");
		}
		if(queryModel.getStreetId()!=null)
		{
			sb.append(" and t.streetId=?");
			list.add(queryModel.getStreetId());
		}
		if(queryModel.getCommunityId()!=null)
		{
			sb.append(" and t.communityId=?");
			list.add(queryModel.getCommunityId());
		}
		if(queryModel.getGroupId()!=null)
		{
			sb.append(" and t.groupId=?");
			list.add(queryModel.getGroupId());
		}
		Object[] objects = new Object[list.size()];
		for (int i = 0; i < objects.length; i++) {
			objects[i] = list.get(i);
		}
		expression.setHql(sb.toString());
		expression.setParameters(objects);
		expression.setType(OperationType.HQL);
		return expression;
	}
	
}
