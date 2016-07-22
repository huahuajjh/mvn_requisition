package com.tq.requisition.infrastructure.Specifications.account;

import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 根据条件获取账户总记录数规约
 * @author jjh
 *	@time 2015-01-07 17:30
 */
public class UserCountSpecification extends Specification<Account>{
	private String userName;
	private UUID orgId;
	private UUID deptId;
	private String name;
	
	public UserCountSpecification(Class<Account> _t,String userName, String name, UUID orgId, UUID deptId) {
		super(_t);
		this.userName = userName;
		this.name = name;
		this.orgId = orgId;
		this.deptId = deptId;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from tb_account where state='3' ");
		if(userName!=null && !userName.trim().equals(""))
		{
			sb.append(" and account='").append(userName+"'");
		}
		if(name!=null && ! name.trim().equals(""))
		{
			sb.append(" and name='").append(name+"'");
		}
		if(orgId!=null)
		{
			sb.append(" and org_id='").append(orgId.toString()+"'");
		}
		if(deptId!=null)
		{
			sb.append(" and dept_id='").append(deptId.toString()+"'");
		}
		IHqlExpression expression = new HqlExpression();
		expression.setSql(sb.toString());
		expression.setType(OperationType.SQL);
		return expression;
	}
	
}
