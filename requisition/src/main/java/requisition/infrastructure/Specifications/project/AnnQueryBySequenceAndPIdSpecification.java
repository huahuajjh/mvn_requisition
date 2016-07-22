package com.tq.requisition.infrastructure.Specifications.project;

import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.project.Announcement;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 规约，根据序列和项目id查询
 * @author jjh
 * @time 2016-01-25 19::45
 *
 */
public class AnnQueryBySequenceAndPIdSpecification extends Specification<Announcement>{
	private int sequence;
	private UUID proId;
	
	public AnnQueryBySequenceAndPIdSpecification(Class<Announcement> _t,int sequence,UUID proId) {
		super(_t);
		this.sequence = sequence;
		this.proId = proId;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setHql("from Announcement where proId=? and sequence=?");
		expression.setParameters(proId,sequence);
		expression.setType(OperationType.HQL);
		return expression;
		
	}
	
}
