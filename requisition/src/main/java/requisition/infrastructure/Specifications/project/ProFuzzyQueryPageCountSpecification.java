package com.tq.requisition.infrastructure.Specifications.project;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.project.Project;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.presentation.dto.project.ProQueryModel;

/**
 * 项目模糊查询获取总行数规约条件
 * @author jjh
 * @time 2015-12-28 3:10
 *
 */
public class ProFuzzyQueryPageCountSpecification extends Specification<Project>{
	private ProQueryModel queryModel;
	
	public ProFuzzyQueryPageCountSpecification(Class<Project> _t,ProQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from tb_project where 1=1 ");
		if(queryModel.getCreateUId()!=null && !queryModel.getCreateUId().equals(""))
		{
			sb.append(" and create_uId='" + queryModel.getCreateUId() + "'");
		}
		if(queryModel.getProName()!=null)
		{
			sb.append(" and pro_name like '%"+queryModel.getProName()).append("%'");
		}
		if(queryModel.getAnnouceQueue()!=0)
		{
			sb.append(" and sequence ='"+queryModel.getAnnouceQueue()).append("'");
		}
		if(queryModel.getTypeId()!=0)
		{
			sb.append(" and pro_type='"+queryModel.getTypeId()).append("'");
		}
		if(queryModel.getStreetId()!=null)
		{
			sb.append(" and street_id='"+queryModel.getStreetId().toString()).append("'");
		}
		if(queryModel.getCommunityId()!=null)
		{
			sb.append(" and community_id='"+queryModel.getCommunityId().toString()).append("'");
		}
		if(queryModel.getAddress() != null && !queryModel.getAddress().equals(""))
		{
			sb.append(" and total_address like '%"+queryModel.getAddress() + "%'");
		}
		expression.setSql(sb.toString());
		expression.setParameters();
		expression.setType(OperationType.SQL);
		return expression;
	}

}
