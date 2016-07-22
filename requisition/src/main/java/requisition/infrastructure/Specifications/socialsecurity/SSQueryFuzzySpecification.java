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
 * 根据查询model模糊查询社保数据，规约
 * @author jjh
 * @time 2015-12-31 2
 */
public class SSQueryFuzzySpecification extends Specification<SocialsecurityInfo>{
	private SocialsecurityQueryModel queryModel;
	
	public SSQueryFuzzySpecification(Class<SocialsecurityInfo> _t,SocialsecurityQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("select new com.tq.requisition.presentation.dto.socialsecurityMgt.SocialsecurityDto(s.id,f.proId,f.proName,f.name,f.idNumber,'',s.socialsecurityDate,f.id,s.serveArmyTime,s.endowmentInsuranceYear,s.medicalInsuranceMonth,s.joinWhichMedicalInsurance,s.community,s.prisonTime,s.socialsecurityTypeId)");
		sb.append(" from SocialsecurityInfo s, FamilyItem f where s.fmlItemId=f.id and s.del=false ");
		if(queryModel.getCreateUId() != null && !queryModel.getCreateUId().equals("")){
			sb.append(" and s.createUid = '"+queryModel.getCreateUId() +"'");
		}
		if(queryModel.getProName()!=null)
		{
			sb.append(" and f.proName like '%" + queryModel.getProName() + "%'");
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
		Object[] objects = new Object[list.size()];
		for (int i = 0; i < objects.length; i++) {
			objects[i] = list.get(i);
		}
		if(queryModel.getGroupId()!=null){
			sb.append(" and f.groupId='"+queryModel.getGroupId().toString()+"'");
		}
		if(queryModel.getIdNumber()!=null && !(queryModel.getIdNumber().trim().equals(""))){
			sb.append(" and f.idNumber='"+queryModel.getIdNumber()+"'");
		}
		if(queryModel.getAddress()!=null && !queryModel.getAddress().equals(""))
		{
			sb.append(" and f.address like " + "'%" + queryModel.getAddress() + "%'");
		}
		expression.setHql(sb.toString());
		expression.setParameters(objects);
		expression.setType(OperationType.HQL);
		return expression;
	}
	
}
