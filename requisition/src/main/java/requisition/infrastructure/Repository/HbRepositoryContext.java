package com.tq.requisition.infrastructure.Repository;

import org.hibernate.Session;

import com.tq.requisition.domain.IRepository.RepositoryContext;
import com.tq.requisition.domain.share.IAggregateRoot;
import com.tq.requisition.infrastructure.log.LoggerFactory;

/**
 * hibernate实现的仓储上下文
 * 
 * @author jjh
 * @time 2015-12-17 12:46
 */
public class HbRepositoryContext extends RepositoryContext implements
		IHbRepositoryContext {

	public HbRepositoryContext() {

	}

	/**
	 * 获取Session上下文
	 * 
	 * @return Session Session实例
	 */
	@Override
	public Session session() {
		return HibernateUtils.session();
	}

	/**
	 * 清理緩存
	 */
	public void clear() {
		session().clear();
	}
	
	@Override
	protected void doCommit() {
		if (!super.commited()) {
			try {
//				session().flush();
				session().getTransaction().commit();
				super.commited(true);
				return;
			} catch (Exception e) {
				LoggerFactory.logger().debug(this.getClass().getName(),
						"doCommit()", e.getMessage());
				throw e;
			}
			finally{
				if(session().isOpen())
				{
					session().close();
				}
			}
		}
	}

	@Override
	protected void doRollback() {
		session().getTransaction().rollback();
		super.commited(false);
	}

	@Override
	public <TAggregateRoot extends IAggregateRoot> void registerNew(TAggregateRoot root) {
//		session().beginTransaction();
		session().save(root);
		super.commited(false);
	}

	@Override
	public <TAggregateRoot extends IAggregateRoot> void registerModified(TAggregateRoot root) {		
//		session().beginTransaction();
		session().update(root);
		super.commited(false);
	}

	@Override
	public <TAggregateRoot extends IAggregateRoot> void registerDeleted(TAggregateRoot root) {
//		session().beginTransaction();
		session().delete(root);
		super.commited(false);
	}
	
	@Override
	protected void doBegintransaction() {
		session().beginTransaction();		
	}
	
	@Override
	protected void doClose() {
		if(session().isOpen())
		{
			session().close();
		}
	}
}
