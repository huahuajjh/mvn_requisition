package com.tq.requisition.domain.Specification;

/**
 * 或规约类型
 * @author jjh
 * @param <T>
 * 		满足规约的对象
 * @date 2015-12-16 17:07
 *
 */
public class NotSpecification<T> extends Specification<T>{

	public NotSpecification(ISpecification<T> specification,Class<T> t) {
		super(t);
	}

}
