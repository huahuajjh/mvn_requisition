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
 * 转户新增前查询model规约
 * @author jjh
 * @time 2015-01-04 16:04
 *
 */
public class TransferAddQuerySpecification extends Specification<TransferHouseholdInfo>{
	private TransferInfoQueryModel queryModel;
	
	public TransferAddQuerySpecification(Class<TransferHouseholdInfo> _t,TransferInfoQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("select new com.tq.requisition.presentation.dto.transferMgt.TransferInfo4AddDto(f.name,f.proName,f.householdStr,f.address,f.idNumber,f.proId,f.id) from FamilyItem f");
		sb.append(" where f.transfer=false ");
		if(queryModel.getProName()!=null)
		{
			sb.append(" and f.proName like " + "'%" + queryModel.getProName() + "%'");
		}
		
		if(queryModel.getCommunityId()!=null)
		{
			sb.append(" and f.communityId=?");
			list.add(queryModel.getCommunityId());
		}
		if(queryModel.getStreetId()!=null)
		{
			sb.append(" and f.streetId=?");
			list.add(queryModel.getStreetId());
		}
		if(queryModel.getIdNumber()!=null && !(queryModel.getIdNumber().trim().equals(""))){
			sb.append(" and f.idNumber=?");
			list.add(queryModel.getIdNumber());
		}
		if(queryModel.getGroupId()!=null){
			sb.append(" and f.groupId=?");
			list.add(queryModel.getGroupId());
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
