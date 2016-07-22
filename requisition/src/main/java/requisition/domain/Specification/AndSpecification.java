package com.tq.requisition.domain.Specification;

/**
 * 与规约类型
 * @author jjh
 * @param <T>
 * 		满足规约的对象
 * @date 2015-12-16 16:54
 *
 */
public class AndSpecification<T> extends CompositeSpecification<T>{

	public AndSpecification(ISpecification<T> left, ISpecification<T> right,Class<T> t) {
		super(left, right,t);
	}

}
