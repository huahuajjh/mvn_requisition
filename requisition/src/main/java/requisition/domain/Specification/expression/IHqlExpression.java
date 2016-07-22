package com.tq.requisition.domain.Specification.expression;

/**
 * hql表达式抽象类,实现该类的类型是一种hql方式的表达式
 * @author jjh
 * @time 2015-12-16 20::09
 */
public abstract class IHqlExpression implements IExpression{
	/**
	 * 设置sql表达式
	 * @param _hql
	 * 		hql表达式
	 */
	public abstract void setSql(String sql);
	
	/**
	 * 设置hql或者sql表达式的参数
	 * @param objects
	 * 		参数数组	
	 */
	public abstract void setParameters(Object... objects);
	
	/**
	 * 获取hql或者sql表达式的参数
	 * @return
	 */
	public abstract Object[] getParameters();
	
	/**
	 * 设置hql表达式
	 * @param hql
	 * 		hql表达式
	 */
	public abstract void setHql(String hql);
	
	/**
	 * 获取sql表达式
	 * @return String
	 * 		sql表达式
	 */
	public abstract String getSql();
	
	/**
	 * 获取hql表达式
	 * @param hql
	 * 		hql表达式
	 */
	public abstract String getHql();
	
	/**
	 * 设置当前操作表达式的类型
	 * @param type
	 * 		操作表达式类型
	 */
	public abstract void setType(OperationType type);
	
	/**
	 * 返回一个枚举，表明当前操作表达式的类型
	 * @return OperationType
	 * 		操作表达式类型
	 */
	public abstract OperationType getType();

}
