package com.tq.requisition.infrastructure.Specifications.account;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 用户名检测规约类，该规约通过用户名检测是否存在该用户
 * @author jjh
 * @time 2015-12-23 16:22
 */
public class UserNameExistsSpecification extends Specification<Account>{
	/*fields*/
	private Account account;
	
	/*constructors*/
	public UserNameExistsSpecification(Class<Account> _t,Account _account) {
		super(_t);
		account = _account;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select count(1) from tb_account where account=?");
		expression.setParameters(new Object[]{account.getAccount()});
		expression.setType(OperationType.SQL);
		return expression;
	}
	
}
