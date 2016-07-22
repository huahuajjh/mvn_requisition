package com.tq.requisition.domain.Specification;

/**
 * 组合规约接口实现的抽象规约类
 * @author jjh
 * @param <T>
 * 		满足规约的对象
 * @date 2015-12-16 16:58
 *
 */
public abstract class CompositeSpecification<T> extends Specification<T> implements ICompositeSpecification<T>{
	private ISpecification<T> left;
	private ISpecification<T> right;
	
	public CompositeSpecification(ISpecification<T> left,ISpecification<T> right,Class<T> t) {
		super(t);
		this.left = left;
		this.right = right;
	}
	
	/**
	 * 获取组合规约对象设置左节点
	 * @return ISpecification<T>
	 * 		规约对象
	 */
	public ISpecification<T> left() {
		return this.left;
	}
	
	/**
	 * 获取组合规约对象设置右节点
	 * @return ISpecification<T>
	 * 		规约对象
	 */
	public ISpecification<T> right() {
		return this.right;
	}
	
}
