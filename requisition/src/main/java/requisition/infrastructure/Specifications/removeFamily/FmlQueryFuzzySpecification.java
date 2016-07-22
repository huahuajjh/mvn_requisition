package com.tq.requisition.infrastructure.Specifications.removeFamily;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.removeFamily.Family;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.infrastructure.utils.PageHelper;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

public class FmlQueryFuzzySpecification extends Specification<Family>{
	private FamilyQueryModel queryModel;
	private PageModel pageModel;
	
	public FmlQueryFuzzySpecification(Class<Family> _t,FamilyQueryModel queryModel,PageModel pageModel) {
		super(_t);
		this.queryModel = queryModel;
		this.pageModel = pageModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from tb_family where 1=1 ");
		List<Object> list = new ArrayList<Object>();
		if(queryModel.getCreateUId() != null && !queryModel.equals("")){
			sb.append(" and create_uId='" + queryModel.getCreateUId() + "'");
		}
		if(queryModel.getIdNumber()!=null)
		{
			sb.append(" and id_number=?");
			list.add(queryModel.getIdNumber());
		}
		if(queryModel.getProName()!=null)
		{
			sb.append(" and pro_name like " + "'%" + queryModel.getProName() +"%'");
		}
		if(queryModel.getProId() != null)
		{
			sb.append(" and pro_id = ?");
			list.add(queryModel.getProId().toString());
		}
		if(queryModel.getStreetId()!=null)
		{
			sb.append(" and street_id=?");
			list.add(queryModel.getStreetId().toString());
		}
		if(queryModel.getCommunityId()!=null)
		{
			sb.append(" and community_id=?");
			list.add(queryModel.getCommunityId().toString());
		}
		if(queryModel.getGroupId()!=null)
		{
			sb.append(" and group_id=?");
			list.add(queryModel.getGroupId().toString());
		}
		if(null!=queryModel.getName() && !(queryModel.getName().trim().equals(""))){
			sb.append(" and head_name like '%" + queryModel.getName() + "%'");
		}
		if(queryModel.getAddress()!=null && !queryModel.getAddress().equals(""))
		{
			sb.append(" and address like " + "'%" + queryModel.getAddress() + "%'");
		}
		sb.append(" limit ?,?");
		list.add(PageHelper.getPageIndex(pageModel.pageIndex, pageModel.pageSize));
		list.add(pageModel.pageSize);
		expression.setSql(sb.toString());
		expression.setType(OperationType.SQL);
		Object[] objects = new Object[list.size()];
		for (int i = 0; i < objects.length; i++) {
			objects[i] = list.get(i);
		}
		expression.setParameters(objects);
		return expression;
	}

}
