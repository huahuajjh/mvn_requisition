package com.tq.requisition.infrastructure.Specifications.socialsecurity;

import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.socialsecurity.SocialsecurityInfo;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 根据拆迁人员id检测该人员是否有社保数据，规约
 * @author jjh
 * @time 2015-12-31 20:42
 */
public class SSExistsByFmlItemIdSpecification extends Specification<SocialsecurityInfo>{
	private String fmlItemId;
	
	public SSExistsByFmlItemIdSpecification(Class<SocialsecurityInfo> _t,UUID fmlItemId) {
		super(_t);
		this.fmlItemId = fmlItemId.toString();
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select count(1) from tb_socialsecurity_info where fml_item_id=?");
		expression.setParameters(fmlItemId);
		expression.setType(OperationType.SQL);
		return expression;
	}
}
