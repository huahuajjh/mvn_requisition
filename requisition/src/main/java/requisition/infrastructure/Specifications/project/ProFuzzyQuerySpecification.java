package com.tq.requisition.infrastructure.Specifications.project;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.project.Project;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.infrastructure.utils.PageHelper;
import com.tq.requisition.presentation.dto.project.ProQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 规约，项目多条件模糊查询规约条件
 * 
 * @author jjh
 * @time 2015-12-28 2:00
 */
public class ProFuzzyQuerySpecification extends Specification<Project>{
	private ProQueryModel queryModel;
	private PageModel pageModel;
	
	public ProFuzzyQuerySpecification(Class<Project> _t,ProQueryModel queryModel,PageModel pageModel) {
		super(_t);
		this.queryModel = queryModel;
		this.pageModel = pageModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from tb_project where 1=1 ");
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
		sb.append(" limit ").append(PageHelper.getPageIndex(pageModel.pageIndex, pageModel.pageSize)).append(","+pageModel.pageSize);
		
		expression.setSql(sb.toString());
		expression.setParameters();
		expression.setType(OperationType.SQL);
		return expression;
	}

}
