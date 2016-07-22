package com.tq.requisition.infrastructure.Specifications.address;

import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.address.Address;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 规约，检测新增地址信息相同节点下是否存在同样的地址名称
 * @author jjh
 * @time 2015-01-08 17:23
 *
 */
public class AddressNameExistsSpecification extends Specification<Address>{
	private String name;
	private UUID pid;
	
	public AddressNameExistsSpecification(Class<Address> _t,String name,UUID pid) {
		super(_t);
		this.name = name;
		this.pid = pid;
				
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from tb_address where title=? ");
		if(null!=pid)
		{
			sb.append(" and pid='"+pid.toString()+"'");
		}
		else {
			sb.append(" and pid is null");
		}
		expression.setSql(sb.toString());
		expression.setParameters(name);
		expression.setType(OperationType.SQL);
		return expression;		
	}	
	
}
