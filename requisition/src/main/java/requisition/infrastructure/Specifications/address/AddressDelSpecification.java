package com.tq.requisition.infrastructure.Specifications.address;

import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.address.Address;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 删除地址规约，需要删除以此为pid的子节点
 * @author jjh
 * @time 2015-01-07 1:54
 *
 */
public class AddressDelSpecification extends Specification<Address>{
	private String id;
	
	public AddressDelSpecification(Class<Address> _t,UUID id) {
		super(_t);
		this.id = id.toString();
	}

	@Override
	public IHqlExpression getHqlExpression() {		
		IHqlExpression expression = new HqlExpression();
		expression.setSql("delete from tb_address where id=?");
		expression.setParameters(id);
		expression.setType(OperationType.SQL);
		return expression;
	}
}