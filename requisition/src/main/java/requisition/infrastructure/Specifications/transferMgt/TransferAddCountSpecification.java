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
 * 转户批量新增前查询总行数规约
 * @author jjh
 * @time 2015-12-31 17:34
 */
public class TransferAddCountSpecification extends Specification<TransferHouseholdInfo>{
	private TransferInfoQueryModel queryModel;
	
	public TransferAddCountSpecification(Class<TransferHouseholdInfo> _t,TransferInfoQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("select count(1) from tb_family_item where is_transferd=0 ");
		if(queryModel.getProName()!=null)
		{
			sb.append(" and pro_name like " + "'%" + queryModel.getProName() + "%'");
		}
		if(queryModel.getCommunityId()!=null)
		{
			sb.append(" and community_id=?");
			list.add(queryModel.getCommunityId().toString());
		}
		if(queryModel.getStreetId()!=null)
		{
			sb.append(" and street_id=?");
			list.add(queryModel.getStreetId().toString());
		}
		if(queryModel.getIdNumber()!=null && !(queryModel.getIdNumber().trim().equals(""))){
			sb.append(" and id_number=?");
			list.add(queryModel.getIdNumber());
		}
		if(queryModel.getGroupId()!=null){
			sb.append(" and group_id=?");
			list.add(queryModel.getGroupId().toString());
		}
		
		Object[] objects = new Object[list.size()];
		for (int i = 0; i < list.size(); i++) {
			objects[i] = list.get(i);
		}
		expression.setParameters(objects);
		expression.setSql(sb.toString());
		expression.setType(OperationType.SQL);
		return expression;
	}
	
}
