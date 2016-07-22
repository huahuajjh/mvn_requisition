package com.tq.requisition.infrastructure.unitOfWork;

/**
 * 表明实现该接口的类是一种uow的实现，该模式的实现用于保证数据一致性。
 * @author jjh
 * @time 2015-12-14 14:46
 */
public interface IUnitOfWork {
	void beginTransaction();
	
	/**
	 * 设置一个boolean值，该值表明了当前的uow事务是否被提交 
	 * @return boolean
	 * 		是否提交
	 */
	boolean commited();
		
	/**
	 * 设置一个boolean值，该值表明了当前的uow事务是否被提交 
	 * @param isCommited
	 * 		是否提交
	 */
	void commited(boolean isCommited);
	
	/**提交当前uow事务*/
	void commit();
	
	/**回滚当前uow事务*/
	void rollback();
	
}
