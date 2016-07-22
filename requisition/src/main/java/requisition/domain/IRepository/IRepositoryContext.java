package com.tq.requisition.domain.IRepository;

import com.tq.requisition.domain.share.IAggregateRoot;
import com.tq.requisition.infrastructure.unitOfWork.IUnitOfWork;

/**
 * 表明该接口的实现是一个仓储上下文,仓储上下文定义了具体的数据持久化api，这些api的实现位于基础设施层。实现可替换的持久化机制。
 * @author jjh
 * @time 2015-12-14 14:11
 */
public interface IRepositoryContext extends IUnitOfWork{
	/**
	 * 将指定的聚合根标记为'新建'状态
	 * @param root
	 * 		被标注的聚合根对象 
	 * @return TAggregateRoot
	 * 		泛型方法的泛型参数类型
	 */
	<TAggregateRoot extends IAggregateRoot> void registerNew(TAggregateRoot root);
	
	/**
	 * 将指定的聚合根标记为'修改'状态
	 * @param root
	 * 		被标注的聚合根对象 
	 * @return TAggregateRoot
	 * 		泛型方法的泛型参数类型
	 */
	<TAggregateRoot extends IAggregateRoot> void registerModified(TAggregateRoot root);
	
	/**
	 * 将指定的聚合根标记为'删除'状态
	 * @param root
	 * 		被标注的聚合根对象 
	 * @return TAggregateRoot
	 * 		泛型方法的泛型参数类型
	 */
	<TAggregateRoot extends IAggregateRoot> void registerDeleted(TAggregateRoot root);
	
	void close();
}
