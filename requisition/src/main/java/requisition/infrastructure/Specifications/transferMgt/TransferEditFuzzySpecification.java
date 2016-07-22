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
public class TransferEditFuzzySpecification extends Specification<TransferHouseholdInfo>{
	private TransferInfoQueryModel queryModel;
	
	public TransferEditFuzzySpecification(Class<TransferHouseholdInfo> _t,TransferInfoQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		
		//express
		sb.append("select new com.tq.requisition.presentation.dto.transferMgt.EditQueryTransferDto(t.id,f.name,f.idNumber,f.householdId,t.streetId,t.communityId,t.transferDate)");
		sb.append(" from TransferHouseholdInfo t,FamilyItem f,Project p ");
		sb.append(" where t.fmlItemId=f.id and f.proId=p.id ");
		
		if(queryModel.getProName()!=null)
		{
			sb.append(" and f.proName like ?");
			list.add("'%" + queryModel.getProName() + "%'");
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
		Object[] objects = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			objects[i] = list.get(i);
		}
		expression.setHql(sb.toString());
		expression.setParameters(objects);
		expression.setType(OperationType.HQL);
		return expression;
	}

}
