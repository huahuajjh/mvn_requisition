package com.tq.requisition.infrastructure.Specifications.account;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 根据条件查询账户集合规约
 * @author jjh
 * @time 2015-01-07 17:44
 *
 */
public class UserQueryByPageSpecification extends Specification<Account>{
	private String userName;
	private UUID orgId;
	private UUID deptId;
	private String name;
	
	public UserQueryByPageSpecification(Class<Account> _t,String userName, String name, UUID orgId, UUID deptId) {
		super(_t);
		this.userName = userName;
		this.name = name;
		this.orgId = orgId;
		this.deptId = deptId;
	}
	
	@Override
	public IHqlExpression getHqlExpression() {
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
//		sb.append("select new com.tq.requisition.presentation.dto.sysMgt.AccountSafeDto(a.id,a.account,a.name,a.deptId,d.deptName,a.orgId,o.orgName,a.roleId,r.roleName,a.state)");
//		sb.append(" from Account a left join Department d with a.deptId=d.id");
//		sb.append(" left join Organization o with a.orgId=o.id");
//		sb.append(" left join Role r with a.roleId=r.id where 1=1 ");
		
		sb.append("select a.id,a.account,a.name,a.dept_id,d.dept_name,a.org_id,o.org_name,a.role_id,r.role_name,a.state");
		sb.append(" from tb_account a left join tb_dept d on a.dept_id=d.id");
		sb.append(" left join tb_org o on a.org_id=o.id");
		sb.append(" left join tb_role r on a.role_id=r.id where a.state='3' ");
		
		if(userName!=null && ! userName.trim().equals(""))
		{
			sb.append(" and a.account=?");
			list.add(userName);
		}
		if(name!=null && ! name.trim().equals(""))
		{
			sb.append(" and a.name=?");
			list.add(name);
		}
		if(orgId!=null)
		{
			sb.append(" and a.org_id=?");
			list.add(orgId.toString());
		}
		if(deptId!=null)
		{
			sb.append(" and a.dept_id=?");
			list.add(deptId.toString());
		}
		
		IHqlExpression expression = new HqlExpression();
		if(list!=null || list.size()!=0)
		{
			Object[] objects = new Object[list.size()];
			for (int i = 0; i < objects.length; i++) {
				objects[i] = list.get(i);
			}
			expression.setParameters(objects);
		}
		
		expression.setSql(sb.toString());
		expression.setType(OperationType.SQL);
		return expression;
	}
}
