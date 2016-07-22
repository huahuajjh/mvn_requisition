package com.tq.requisition.infrastructure.Specifications.roleAccount;

import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.roleAccount.RoleAccount;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 根据用户id获取角色规约
 * @author jjh
 * @time 2015-01-06 8:55
 *
 */
public class RoleAccByUIdSpecification extends Specification<RoleAccount>{
	private UUID uid;
	
	public RoleAccByUIdSpecification(Class<RoleAccount> _t,UUID uid) {
		super(_t);
		this.uid = uid;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		String str = "select * from tb_role_account where account_id=?";
		expression.setType(OperationType.SQL);
		expression.setParameters(uid.toString());
		expression.setSql(str);
		return expression;
	}

}
