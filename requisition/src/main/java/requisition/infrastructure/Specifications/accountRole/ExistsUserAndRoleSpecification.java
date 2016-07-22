package com.tq.requisition.infrastructure.Specifications.accountRole;

import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.roleAccount.RoleAccount;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 根据账户id和角色id查询该关联是否存在的规约类
 * @author jjh
 * @time 2015-12-21 17:33
 */
public class ExistsUserAndRoleSpecification extends Specification<RoleAccount>{
	private UUID uid;
	private UUID rid;	

	public ExistsUserAndRoleSpecification(Class<RoleAccount> _t, UUID uid,
			UUID rid) {
		super(_t);
		this.uid = uid;
		this.rid = rid;
	}

	public ExistsUserAndRoleSpecification(Class<RoleAccount> _t) {
		super(_t);
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select count(1) from tb_role_account where account_id=? and role_id=?");
		expression.setParameters(uid.toString(),rid.toString());
		expression.setType(OperationType.SQL);
		return expression;
	}
}
