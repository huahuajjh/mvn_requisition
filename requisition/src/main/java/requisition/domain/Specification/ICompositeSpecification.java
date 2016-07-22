package com.tq.requisition.domain.Specification;

/**
 * 组合规约接口，实现该接口的类型为组合规约接口类型，该类型提供左右节点的规约对象
 * 方便规约的组合以及深度遍历，组合成规约树
 * @param <T>
 * 		指定规约要满足的对象
 * @author jjh
 * @time 2015-12-16 16:41
 */
public interface ICompositeSpecification<T> extends ISpecification<T>{
	/**
	 * 获取组合规约对象设置左节点
	 * @return ISpecification<T>
	 * 		规约对象
	 */
	ISpecification<T> left();
	
	/**
	 * 获取组合规约对象设置右节点
	 * @return ISpecification<T>
	 * 		规约对象
	 */
	ISpecification<T> right();

}
