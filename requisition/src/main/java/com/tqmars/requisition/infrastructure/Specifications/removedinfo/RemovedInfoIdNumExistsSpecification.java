package com.tqmars.requisition.infrastructure.Specifications.removedinfo;

import com.tqmars.requisition.domain.Specification.Specification;
import com.tqmars.requisition.domain.Specification.expression.IHqlExpression;
import com.tqmars.requisition.domain.Specification.expression.OperationType;
import com.tqmars.requisition.domain.model.removedInfo.RemovedInfo;
import com.tqmars.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 规约，检验身份证是否在已迁户档案中存在
 * @author jjh
 * @time 2016-01-11 20:07
 *
 */
public class RemovedInfoIdNumExistsSpecification extends Specification<RemovedInfo>{
	private String idNumber;
	
	public RemovedInfoIdNumExistsSpecification(Class<RemovedInfo> _t,String idNumber) {
		super(_t);
		this.idNumber = idNumber;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select count(1) from tb_removed_info where id_number=?");
		expression.setParameters(idNumber);
		expression.setType(OperationType.SQL);
		return expression;
	}

}
