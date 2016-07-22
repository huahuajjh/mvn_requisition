package com.tq.requisition.domain.IRepository;

import java.util.Map;
import java.util.UUID;

import com.tq.requisition.domain.share.IAggregateRoot;
import com.tq.requisition.exception.InvalidOperationException;

/**
 * 仓储上下文基类
 * @author jjh
 * @time 2015-12-17 00:46
 */
public abstract class RepositoryContext implements IRepositoryContext{
	/*fields*/
	private final static ThreadLocal<Boolean> localCommited = new ThreadLocal<Boolean>();
	private final static ThreadLocal<Map<UUID, Object>> localNewCollection = new ThreadLocal<Map<UUID, Object>>();
	private final static ThreadLocal<Map<UUID, Object>> localModifiedCollection = new ThreadLocal<Map<UUID, Object>>();
	private final static ThreadLocal<Map<UUID, Object>> localDeletedCollection = new ThreadLocal<Map<UUID, Object>>();

	public RepositoryContext(){
		localCommited.set(false);
	}

	/**
	 * 设置一个boolean值，该值表明了当前的uow事务是否被提交 
	 * @return boolean
	 * 		是否提交
	 */
	@Override
	public boolean commited() {
		if(localCommited.get()==null)
		{
			localCommited.set(false);
//			throw new NullPointerException("RepositoryContext类中的localCommited.get()为null");
		}
		return localCommited.get();
	}

	/**
	 * 设置一个boolean值，该值表明了当前的uow事务是否被提交 
	 * @param isCommited
	 * 		是否提交
	 */
	@Override
	public void commited(boolean isCommited) {
		localCommited.set(isCommited);
	}

	/*IUnitOfWork实现*/	
	@Override
	public void commit() {
		doCommit();
	}
	
	/**
	 * 关闭上下文
	 */
	@Override
	public void close() {
		doClose();
	}

	/**
	 * 开启事务
	 */
	@Override
	public void beginTransaction() {
		doBegintransaction();
	}
	
	@Override
	public void rollback() {
		doRollback();
	}

	/**
	 * 提交事务，该方法由子类实现
	 */
	protected abstract void doCommit();
	
	protected abstract void doClose();
	
	/**
	 * 开启事务，该方法由子类实现
	 */
	protected abstract void doBegintransaction();
	
	/**
	 * 回滚事务，该方法由子类实现
	 */
	protected abstract void doRollback();

	@Override
	public <TAggregateRoot extends IAggregateRoot> void registerNew(TAggregateRoot root) {
		if(localDeletedCollection.get().containsKey(root.id()))
		{
			throw new InvalidOperationException("该对象不能被标记为新增，因为它当前被标记为删除状态");
		}
		if(!localNewCollection.get().containsKey(root.id()) && !localModifiedCollection.get().containsKey(root.id()))
		{
			localModifiedCollection.get().put(root.id(), root);
			localCommited.set(false);
		}	
		
	}

	@Override
	public <TAggregateRoot extends IAggregateRoot> void registerModified(TAggregateRoot root) {
		if(localDeletedCollection.get().containsKey(root.id()))
		{
			throw new InvalidOperationException("该对象不能被标记为新增，因为它当前被标记为删除状态");
		}
		if(!localNewCollection.get().containsKey(root.id()) && !localModifiedCollection.get().containsKey(root.id()))
		{
			localModifiedCollection.get().put(root.id(), root);
			localCommited.set(false);
		}
		
	}

	@Override
	public <TAggregateRoot extends IAggregateRoot> void registerDeleted(TAggregateRoot root) {
		if(root.id().equals(null))
		{throw new NullPointerException("空指针异常");}
		
		if(localNewCollection.get().containsKey(root.id()))
		{
			localNewCollection.get().remove(root.id());
		}
		if(!localDeletedCollection.get().containsKey(root.id()))
		{
			localDeletedCollection.get().put(root.id(),root);
			localCommited.set(false);
		}
		
	}
	
}
