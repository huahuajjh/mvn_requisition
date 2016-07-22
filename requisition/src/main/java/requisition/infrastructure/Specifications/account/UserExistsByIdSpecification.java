package com.tq.requisition.infrastructure.Specifications.account;

import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 根据账户id检测账户是否存在的规约实现
 * @author jjh
 * @time 2015-12-20 23:30
 */
public class UserExistsByIdSpecification extends Specification<Account>{
	private UUID id;

	public UserExistsByIdSpecification(Class<Account> _t,UUID id) {
		super(_t);
		this.id = id;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select count(1) from tb_account where id=?");
		expression.setParameters(this.id);
		expression.setType(OperationType.SQL);
		return expression;
	}

}
