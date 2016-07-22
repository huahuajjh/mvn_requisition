package com.tq.requisition.infrastructure.Specifications.Expression;

import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.exception.ValidationNullException;

/**
 * hql语句表达式
 * @author jjh
 * @time 2015-12-17 16:20
 *
 */
public final class HqlExpression extends IHqlExpression{
	/*private fields*/
	private String sqlQuery;
	private String hqlQuery;
	private Object[] objects;
	private OperationType type;
	
	/**
	 * 设置sql表达式
	 * @param _hql
	 * 		hql表达式
	 */
	@Override
	public void setSql(String sql) {		
		sqlQuery = sql;
	}
	
	/**
	 * 获取hql表达式
	 * @return
	 * 		hql表达式
	 */
	@Override
	public String getSql() {
		if(sqlQuery == null)
		{
			throw new ValidationNullException("设置的sql表达式为空，请指定返回操作表达式类型，默认SQL类型");
		}
		return sqlQuery;
	}
	
	/**
	 * 设置hql表达式
	 * @param hql
	 * 		hql表达式
	 */
	public void setHql(String hql) {
		hqlQuery = hql;
	}
	
	/**
	 * 获取hql表达式
	 * @param hql
	 * 		hql表达式
	 */
	@Override
	public String getHql() {
		if(hqlQuery == null)
		{
			throw new ValidationNullException("设置的hql表达式为空，请指定返回操作表达式类型，默认SQL类型");
		}
		return hqlQuery;
	}
	
	/**
	 * {@inheritDoc}
	 */

	@Override
	public void setParameters(Object... objects) {
		this.objects = objects;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object[] getParameters() {
		return this.objects;
	}

	@Override
	public void setType(OperationType type) {
		this.type = type;
	}

	@Override
	public OperationType getType() {		
		if(type == null)
		{
			return OperationType.SQL;
		}
		return type;
	}

	
}
