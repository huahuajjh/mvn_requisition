package com.tq.requisition.infrastructure.Specifications.resRole;

import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.model.resRole.ResRole;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

public class ResourIdsAtRoleIdsSpecifications extends Specification<ResRole> {

	private  String roldIds = "";
	
	public ResourIdsAtRoleIdsSpecifications(Class<ResRole> _t, UUID... roldIds) {
		super(_t);
		if (roldIds != null && roldIds.length > 0) {
			for (int i = 0; i < roldIds.length; i++) {
				this.roldIds = "\"" + roldIds[i].toString() + "\",";
			}
			this.roldIds = this.roldIds.substring(0, roldIds.length - 1);
		}
	}
	
	
	/**
	 * 获取hql表达式
	 * 
	 * @return IHqlExpression hql表达式实例
	 */
	@Override
	public IHqlExpression getHqlExpression() {
		HqlExpression expression = new HqlExpression();
		expression.setSql("select * from tb_permission where role_id in (" + roldIds + ")");
		
		return expression;
	}
}
