package com.tq.requisition.infrastructure.Specifications.socialsecurity;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.socialsecurity.SocialsecurityInfo;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.presentation.dto.socialsecurityMgt.SocialsecurityQueryModel;

/**
 * 根据查询model查询待新增社保数据的拆迁人员总行数
 * @author jjh
 * @time 2015-01-04 20：33
 */
public class SSQuery4AddCountSpecification extends Specification<SocialsecurityInfo>{
	private SocialsecurityQueryModel queryModel;
	
	public SSQuery4AddCountSpecification(Class<SocialsecurityInfo> _t,SocialsecurityQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from tb_family_item where is_socialsecurity=0");
		if(queryModel.getProName()!=null)
		{
			sb.append(" and pro_name like '%"+queryModel.getProName()).append("%'");
		}
		if(queryModel.getCommunityId()!=null)
		{
			sb.append(" and community_id='"+queryModel.getCommunityId().toString()).append("'");
		}
		if(queryModel.getStreetId()!=null)
		{
			sb.append(" and street_id='"+queryModel.getStreetId().toString()).append("'");
		}
		if(queryModel.getGroupId()!=null)
		{
			sb.append(" and group_id='"+queryModel.getGroupId().toString()).append("'");
		}
		if(queryModel.getIdNumber()!=null)
		{
			sb.append(" and id_number='"+queryModel.getIdNumber().toString()).append("'");
		}
		if(queryModel.getAddress()!=null && !queryModel.getAddress().equals(""))
		{
			sb.append(" and address like " + "'%" + queryModel.getAddress() + "%'");
		}
		expression.setSql(sb.toString());
		expression.setType(OperationType.SQL);
		return expression;
		
	}
}
