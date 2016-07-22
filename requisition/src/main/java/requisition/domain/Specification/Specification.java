package com.tq.requisition.domain.Specification;

import org.apache.commons.lang3.NotImplementedException;

import com.tq.requisition.domain.Specification.expression.ICriteriaExpression;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;


/**
 * 规约接口实现的抽象规约类
 * @author jjh
 * @param <T>
 * 		满足规约的对象
 * @date 2015-12-16 16:50
 *
 */
public abstract class Specification<T> implements ISpecification<T>{
	protected Class<T> t;
	
	/**
	 * 初始化
	 * @param t
	 * 		规约泛型参数
	 */
	public Specification(Class<T> _t)
	{
		if(t != null)
		{
			this.t = _t;
		}
	}

	@Override
	public boolean isSatisfiedBy(T t) {
		throw new NotImplementedException("");
	}

	@Override
	public ISpecification<T> and(ISpecification<T> another) {
		return new AndSpecification<>(this,another,t);
	}

	@Override
	public ISpecification<T> andNot(ISpecification<T> another) {
		return new AndNotSpecification<>(this,another,t);
	}

	@Override
	public ISpecification<T> or(ISpecification<T> another) {
		return new OrSpecification<>(this,another,t);
	}

	@Override
	public ISpecification<T> not() {
		return new NotSpecification<>(this,t);
	}
	
	/**
	 * 获取条件表达式
	 * @return HqlExpression
	 * 		hql表达式
	 */
	public IHqlExpression getHqlExpression(){
		throw new NotImplementedException("");
	}
	
	/**
	 * 获取条件表达式
	 * @return HqlExpression
	 * 		Criteria表达式
	 */
	public ICriteriaExpression getCriteriaExpression(){
		throw new NotImplementedException("");
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<T> getType() {
		return t;
	}
	
}
