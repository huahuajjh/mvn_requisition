package com.tqmars.requisition.infrastructure.Specifications.account;

import java.util.UUID;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.account.Account;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 账户模糊分页查询规约
 * @author jjh
 * @time 2015-12-26 21:14
 */
public class UserQueryFuzzySpecification extends Specification<Account>{
	private UUID deptId;
	private UUID orgId;
	
	public UserQueryFuzzySpecification(Class<Account> _t,int pageIndex,int pageSize,String name,String account,UUID deptId,UUID orgId) {
		super(_t);
		this.deptId = deptId;
		this.orgId = orgId;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from tb_account where 1=1");
		if(orgId!=null){sb.append(" and org_id='"+orgId.toString()+"'");}
		if(deptId!=null){sb.append(" and dept_id='"+deptId.toString()+"'");}
		
		expression.setSql(sb.toString());
		expression.setType(OperationType.SQL);
		return expression;
	}	
	
}
