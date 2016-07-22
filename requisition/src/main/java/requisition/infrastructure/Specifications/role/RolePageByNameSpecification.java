package com.tq.requisition.infrastructure.Specifications.role;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.role.Role;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.infrastructure.utils.PageHelper;

/**
 * 角色规约条件，通过角色名模糊组装条件，并且分页显示
 * @author jjh
 * @time 2015-12-21 13:16
 * 
 */
public class RolePageByNameSpecification extends Specification<Role>{
	private String roleName;
	private int pageIndex;
	private int pageNum;

	public RolePageByNameSpecification(Class<Role> _t,String roleName,int pageIndex,int pageNum) {
		super(_t);
		this.roleName = roleName;
		this.pageIndex = pageIndex;
		this.pageNum = pageNum;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select * from tb_role where role_name like '%"+roleName+"%' limit ?,?");
		expression.setParameters(PageHelper.getPageIndex(pageIndex, pageNum),pageNum);
		expression.setType(OperationType.SQL);
		return expression;
	}
	
}
