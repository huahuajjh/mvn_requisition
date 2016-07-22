package com.tq.requisition.infrastructure.Repository;

import org.hibernate.Session;

import com.tq.requisition.domain.IRepository.IRepositoryContext;

/**
 * hibernate实现的仓储上下文，凡是继承该接口的实现都是hibernate的实现
 * @author jjh
 * @time 2015-12-17 12:42
 */
public interface IHbRepositoryContext extends IRepositoryContext{
	/**
	 * hibernate上下文
	 * @return Session
	 * 		上下文实例
	 */
	Session session();
	
	/**
	 * 开启事务
	 */
	void beginTransaction();	
}
