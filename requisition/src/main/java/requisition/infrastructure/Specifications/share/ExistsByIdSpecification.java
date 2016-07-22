package com.tq.requisition.infrastructure.Specifications.share;

import java.util.UUID;

import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.share.IAggregateRoot;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;

/**
 * 根据id判定对象是否存在的规约类
 * @author jjh
 * @time 2015-12-20 23:30
 */
public class ExistsByIdSpecification<T extends IAggregateRoot> extends Specification<T>{
	private UUID id;
	private String tableName;
	private String colName;
	
	public ExistsByIdSpecification(Class<T> _t,UUID id,String tableName,String colName) {
		super(_t);
		this.id = id;
		this.tableName = tableName;
		this.colName = colName;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setHql("select count(1) from "+tableName+" where "+colName+" = ?");
		expression.setParameters(id);
		expression.setType(OperationType.SQL);
		return expression;
	}
}
