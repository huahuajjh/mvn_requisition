package com.tq.requisition.infrastructure.Specifications.share;

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
public class ExistsByColNameSpecification<T extends IAggregateRoot> extends Specification<T>{
	private String tableName;
	private String colName;
	private Object value;
	
	public ExistsByColNameSpecification(Class<T> _t,String tableName,String colName,Object value) {
		super(_t);
		this.tableName = tableName;
		this.colName = colName;
		this.value = value;
	}

	@Override
	public IHqlExpression getHqlExpression() {
		IHqlExpression expression = new HqlExpression();
		expression.setSql("select count(1) from "+tableName+" where "+colName+" = ?");
		expression.setParameters(value.toString());
		expression.setType(OperationType.SQL);
		return expression;
	}
}
