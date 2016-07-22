package com.tq.requisition.domain.Specification;

/**
 * 与或规约类型
 * @author jjh
 * @param <T>
 * 		满足规约的对象
 * @date 2015-12-16 17:10
 *
 */
public class AndNotSpecification<T> extends CompositeSpecification<T> {

	public AndNotSpecification(ISpecification<T> left, ISpecification<T> right,Class<T> t) {
		super(left, right,t);
	}

}
