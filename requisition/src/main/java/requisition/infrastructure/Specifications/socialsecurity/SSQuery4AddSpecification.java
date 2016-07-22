package com.tq.requisition.infrastructure.Specifications.socialsecurity;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.socialsecurity.SocialsecurityInfo;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.presentation.dto.socialsecurityMgt.SocialsecurityQueryModel;

/**
 * 根据查询model查询待新增社保数据的拆迁人员记录
 * @author jjh
 * @time 2015-01-04 20：33
 */
public class SSQuery4AddSpecification extends Specification<SocialsecurityInfo>{
	private SocialsecurityQueryModel queryModel;
	
	public SSQuery4AddSpecification(Class<SocialsecurityInfo> _t,SocialsecurityQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("select new com.tq.requisition.presentation.dto.socialsecurityMgt.Socialsecurity4AddDto(f.proId,f.id,f.proName,f.name,f.idNumber,f.birthday)");
		sb.append(" from FamilyItem f where f.socialsecurity=false ");
		if(queryModel.getProName()!=null)
		{
			sb.append(" and f.proName like '%" + queryModel.getProName() +"%'");
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
		if(queryModel.getGroupId()!=null)
		{
			sb.append(" and groupId='"+queryModel.getGroupId().toString()).append("'");
		}
		if(queryModel.getIdNumber()!=null && !(queryModel.getIdNumber().trim().equals("")))
		{
			sb.append(" and idNumber='"+queryModel.getIdNumber().toString()).append("'");
		}
		if(queryModel.getAddress()!=null && !queryModel.getAddress().equals(""))
		{
			sb.append(" and address like " + "'%" + queryModel.getAddress() + "%'");
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
