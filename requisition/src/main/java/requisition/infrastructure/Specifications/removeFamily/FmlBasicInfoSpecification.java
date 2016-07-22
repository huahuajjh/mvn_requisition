package com.tq.requisition.infrastructure.Specifications.removeFamily;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.removeFamily.Family;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyQueryModel;

/**
 * 查询拆迁户基本信息，规约
 * @author jjh
 * @time 2016-01-21 18:09
 *
 */
public class FmlBasicInfoSpecification extends Specification<Family>{
	private FamilyQueryModel queryModel;
	
	public FmlBasicInfoSpecification(Class<Family> _t,FamilyQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}
	
	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select new com.tq.requisition.presentation.dto.rmHousehold.FamilyBasicInfoDto(f.headName,fi.idNumber,fi.proName,fi.address,f.streetId,f.communityId,f.groupId,f.proId,fi.fmlId)");
		sb.append(" from Family f,FamilyItem fi where f.headId=fi.id ");
		List<Object> list = new ArrayList<Object>();
		if(queryModel.getProName()!=null)
		{
			sb.append(" and f.proName like " + "'%"+queryModel.getProName() + "%'");
		}
		if(queryModel.getStreetId()!=null)
		{
			sb.append(" and f.streetId=?");
			list.add(queryModel.getStreetId());
		}
		if(queryModel.getCommunityId()!=null)
		{
			sb.append(" and f.communityId=?");
			list.add(queryModel.getCommunityId());
		}
		if(queryModel.getGroupId()!=null)
		{
			sb.append(" and f.groupId=?");
			list.add(queryModel.getGroupId());
		}
		if(null!=queryModel.getName() && !(queryModel.getName().trim().equals(""))){
			sb.append(" and f.headName like '%" + queryModel.getName() + "%'");
		}
		
		expression.setHql(sb.toString());
		expression.setType(OperationType.HQL);
		Object[] objects = new Object[list.size()];
		for (int i = 0; i < objects.length; i++) {
			objects[i] = list.get(i);
		}
		expression.setParameters(objects);
		return expression;
	}
}
