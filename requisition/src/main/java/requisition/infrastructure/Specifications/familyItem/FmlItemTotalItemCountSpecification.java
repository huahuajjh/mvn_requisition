package com.tq.requisition.infrastructure.Specifications.familyItem;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.infrastructure.utils.ThreeState;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemQueryModel;

public class FmlItemTotalItemCountSpecification extends Specification<FamilyItem>{	
	private FamilyItemQueryModel queryModel;
	
	public FmlItemTotalItemCountSpecification(Class<FamilyItem> _t,FamilyItemQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		List<Object> list = new ArrayList<Object>();
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from tb_family_item as fi,tb_family as f where fi.fml_id=f.id ");
		
		if(queryModel.getCreateUId() != null && !queryModel.getCreateUId().equals("")){
			sb.append(" and f.create_uId='" + queryModel.getCreateUId() + "'");
		}
		//是否转户
		if(queryModel.getIsTransfer() == ThreeState.YES)
		{
			sb.append(" and fi.is_transferd=1");
		}
		//是否转户
		if(queryModel.getIsTransfer() == ThreeState.NO)
		{
			sb.append(" and fi.is_transferd=0");
		}
		//是否纳入社保
		if(queryModel.getIsSSecurity()==ThreeState.YES)
		{
			sb.append(" and fi.is_socialsecurity=1");
		}
		//是否纳入社保
		if(queryModel.getIsSSecurity()==ThreeState.NO)
		{
			sb.append(" and fi.is_socialsecurity=0");
		}
		if(queryModel.isHalf() == ThreeState.YES)
		{
			sb.append(" and fi.is_half=1");
		}
		if(queryModel.isHalf() == ThreeState.NO)
		{
			sb.append(" and fi.is_half=0");
		}
		if(queryModel.getCommunityId()!=null)
		{
			sb.append(" and fi.street_id=?");
			list.add(queryModel.getStreetId().toString());
		}
		if(queryModel.getCommunityId()!=null)
		{
			sb.append(" and fi.community_id=?");
			list.add(queryModel.getCommunityId().toString());
		}
		if(queryModel.getGroupId()!=null)
		{
			sb.append(" and fi.group_id=?");
			list.add(queryModel.getGroupId().toString());
		}
		if(queryModel.getIdNumber()!=null)
		{
			sb.append(" and fi.id_number=?");
			list.add(queryModel.getIdNumber());
		}
		if(queryModel.getProName()!=null)
		{
			sb.append(" and fi.pro_name like " + "'%" + queryModel.getProName() + "%'");
		}
		if(queryModel.getAddress()!=null && !queryModel.getAddress().equals(""))
		{
			sb.append(" and fi.address like " + "'%" + queryModel.getAddress() + "%'");
		}
		if(null!=queryModel.getName() && !(queryModel.getName().trim().equals("")))
		{
			sb.append(" and fi.name like '%" + queryModel.getName() + "%'");
		}
		//独生子女
		if(queryModel.getIsOnlyChild()==ThreeState.YES)
		{
			sb.append(" and fi.only_child_number is not null");
		}
		//独生子女
		if(queryModel.getIsOnlyChild()==ThreeState.NO)
		{
			sb.append(" and fi.only_child_number is null");
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
