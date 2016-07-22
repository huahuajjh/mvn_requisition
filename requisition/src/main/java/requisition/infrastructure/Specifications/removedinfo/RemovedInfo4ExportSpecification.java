package com.tq.requisition.infrastructure.Specifications.removedinfo;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.removedInfo.RemovedInfo;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.presentation.dto.removedinfo.RemovedInfoQueryModel;

/**
 * 导出已迁户集合信息筛选规约
 * @author jjh
 * @time 2016-02-03 15:14
 *
 */
public class RemovedInfo4ExportSpecification extends Specification<RemovedInfo>{
	private RemovedInfoQueryModel queryModel;
	
	public RemovedInfo4ExportSpecification(Class<RemovedInfo> _t,RemovedInfoQueryModel queryModel) {
		super(_t);
		this.queryModel = queryModel;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select * from tb_removed_info");
		expression.setType(OperationType.SQL);
		return expression;
	}

}
