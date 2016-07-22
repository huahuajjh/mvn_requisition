package com.tq.requisition.domain.Specification;

import com.tq.requisition.domain.Specification.expression.ICriteriaExpression;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;


/**
 * 规约接口，表明实现该接口的类型是一种规约实现
 * @author jjh
 * @time 2015-12-14 13:27 
 * @param <T>
 * 		应用规约的类型参数
 */
public interface ISpecification<T> {
	/**
	 * 返回一个boolean值，表明该规约是否匹配给定的t对象
	 * @param t
	 * 		该规约适用的对象
	 * @return boolean
	 * 		是否符合规约
	 */
	boolean isSatisfiedBy(T t);
	
	/**
	 * 获取T泛型参数的Class
	 * @return Class<T>
	 */
	Class<T> getType();
	
	/**
	 * 链接当前规约和给定的规约对象，返回链接后的规约对象，该合并后的规约对象
	 * 表明了给定的规约对象和当前规约对象必须都满足指定的对象
	 * @param another
	 * 		给定的另一个规约对象
	 * @return ISpecification<T>
	 * 		合并后的规约对象
	 */
	ISpecification<T> and(ISpecification<T> another);
	
	/**
	 * 链接当前规约和给定的规约对象，返回链接后的规约对象，该合并后的规约对象
	 * 表明了给定的规约对象不应该满足指定对象，而当前规约满足指定对象
	 * @param another
	 * 		给定的另一个规约对象
	 * @return ISpecification<T>
	 * 		合并后的规约对象
	 */
	ISpecification<T> andNot(ISpecification<T> another);
	
	/**
	 * 链接当前规约和给定的规约对象，返回链接后的规约对象，该合并后的规约对象
	 * 表明了给定的规约对象和当前规约对象任意一个满足指定的对象
	 * @param another
	 * 		给定的另一个规约对象
	 * @return ISpecification<T>
	 * 		合并后的规约对象
	 */
	ISpecification<T> or(ISpecification<T> another);
	
	/**
	 * 反转规约的逻辑，同时返回反转逻辑的规约对象
	 * @return ISpecification<T>
	 * 		反转后的规约对象
	 */
	ISpecification<T> not();
	
	/**
	 * 获取条件表达式
	 * @return HqlExpression
	 * 		hql表达式
	 */
	IHqlExpression getHqlExpression();
	
	/**
	 * 获取条件表达式
	 * @return HqlExpression
	 * 		Criteria表达式
	 */
	ICriteriaExpression getCriteriaExpression();
	
}
