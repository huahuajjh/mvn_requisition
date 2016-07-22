package com.tq.requisition.domain.IRepository;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.Specification.ISpecification;
import com.tq.requisition.domain.share.IAggregateRoot;

/**
 * 表明该接口的实现是一个仓储上下文,仓储上下文定义了具体的数据持久化api，这些api的实现位于基础设施层。实现可替换的持久化机制。
 * 
 * @author jjh
 * @time 2015-12-17 00:12
 */
public abstract class Repository<TAggregateRoot extends IAggregateRoot>
		implements IRepository<TAggregateRoot> {
	private final IRepositoryContext context;
	protected Class<TAggregateRoot> rootClass;

	/**
	 * 初始化
	 * 
	 * @param _context
	 *            仓储上下文
	 */
	public Repository(IRepositoryContext _context) {
		context = _context;
	}

	/**
	 * 设置泛型类型class
	 * 
	 * @param root
	 *            泛型类型class
	 */
	@Override
	public IRepository<TAggregateRoot> setAggregatorRootClass(
			Class<TAggregateRoot> root) {
		rootClass = root;
		return this;
	}

	/**
	 * 获取仓储上下文
	 * 
	 * @return IRepositoryContext 仓储上下文对象
	 */
	@Override
	public IRepositoryContext context() {
		return context;
	}

	@Override
	public boolean existsById(UUID id, String tableName, String idName) {
		return doExistsById(id, tableName, idName);
	}

	@Override
	public int executeUpdate(ISpecification<TAggregateRoot> specification) {
		return doExecuteUpdate(specification);
	}

	@Override
	public int getTotalCount(ISpecification<TAggregateRoot> specification) {
		return doGetTotalCount(specification);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(ISpecification<TAggregateRoot> specification) {
		return doExists(specification);
	}

	/**
	 * 将指定的聚合跟添加到仓储中
	 * 
	 * @param aggregateRoot
	 *            待添加的聚合根
	 */
	@Override
	public void add(TAggregateRoot aggregateRoot) {
		doAdd(aggregateRoot);
	}

	/**
	 * 根据聚合根的id值，获取聚合根
	 * 
	 * @param key
	 *            聚合根id
	 * @param cls
	 *            聚合根class
	 * @return TAggregateRoot 聚合根实例
	 */
	@Override
	public TAggregateRoot getByKey(Class<TAggregateRoot> cls, UUID key) {
		return doGetByKey(cls, key);
	}

	/**
	 * 根据指定的规约，从仓储中获取所有符合条件的聚合根
	 * 
	 * @param specification
	 *            规约
	 * @return List<TAggregateRoot> 符合条件的聚合根实例集合
	 */
	@Override
	public List<TAggregateRoot> getAll(
			ISpecification<TAggregateRoot> specification) {
		return doGetAll(specification);
	}

	/**
	 * 分页获取聚合根集合对象
	 * 
	 * @param specification
	 *            规约
	 * @param indexPage
	 *            起始页
	 * @param pageSize
	 *            页码大小
	 * @return List<TAggregateRoot> 聚合根集合
	 */
	@Override
	public List<TAggregateRoot> getAll(
			ISpecification<TAggregateRoot> specification, int indexPage,
			int pageSize) {
		return doGetAll(specification, indexPage, pageSize);
	}

	/**
	 * 将指定的聚合根从仓储中删除
	 * 
	 * @param aggregateRoot
	 *            待删除的聚合根
	 */
	@Override
	public void remove(TAggregateRoot aggregateRoot) {
		doRemove(aggregateRoot);
	}

	/**
	 * 根据规约删除聚合根
	 * 
	 * @param specification
	 *            规约
	 */
	@Override
	public void remove(ISpecification<TAggregateRoot> specification) {
		doRemove(specification);
	}

	/**
	 * 根据id删除聚合根
	 * 
	 * @param cls
	 *            聚合根class
	 * @param keyUuid
	 *            uuid主键
	 */
	@Override
	public void removeByKey(Class<TAggregateRoot> cls, UUID keyUuid) {
		doRemove(cls, keyUuid);
	}

	/**
	 * 更新指定的聚合根
	 * 
	 * @param aggregateRoot
	 *            待更新的聚合根
	 */
	@Override
	public void update(TAggregateRoot aggregateRoot) {
		doUpdate(aggregateRoot);
	}

	/**
	 * 根据id获取聚合根，该方法由子类实现
	 * 
	 * @param key
	 *            聚合根id
	 * @param cls
	 *            聚合根class
	 * @return TAggregateRoot 聚合根对象
	 */
	protected abstract TAggregateRoot doGetByKey(Class<TAggregateRoot> cls,
			UUID key);

	/**
	 * 新增指定的聚合根到仓储中，该方法由子类实现
	 * 
	 * @param aggregateRoot
	 *            待新增的聚合根
	 */
	protected abstract void doAdd(TAggregateRoot aggregateRoot);

	/**
	 * 根据指定的规约获取聚合根，该方法由子类实现
	 * 
	 * @param specification
	 *            指定的规约
	 * @return List<TAggregateRoot> 聚合根集合
	 */
	protected abstract List<TAggregateRoot> doGetAll(
			ISpecification<TAggregateRoot> specification);

	/**
	 * 根据指定的规约分页获取聚合根，该方法由子类实现
	 * 
	 * @param specification
	 *            指定的规约
	 * @param indexPage
	 *            起始页
	 * @param pageSize
	 *            页码
	 * @return List<TAggregateRoot> 聚合根集合
	 */
	protected abstract List<TAggregateRoot> doGetAll(
			ISpecification<TAggregateRoot> specification, int indexPage,
			int pageSize);

	/**
	 * 删除指定的聚合根，该方法由子类实现
	 * 
	 * @param aggregateRoot
	 *            待删除的聚合根
	 */
	protected abstract void doRemove(TAggregateRoot aggregateRoot);

	/**
	 * 根据规约删除聚合根
	 * 
	 * @param specification
	 *            规约
	 */
	protected abstract void doRemove(
			ISpecification<TAggregateRoot> specification);

	/**
	 * 根据id删除聚合根
	 * 
	 * @param cls
	 *            聚合根class
	 * @param keyUuid
	 *            uuid主键
	 */
	protected abstract void doRemove(Class<TAggregateRoot> cls, UUID key);

	/**
	 * 更新指定的聚合根到仓储中，该方法由子类实现
	 * 
	 * @param aggregateRoot
	 *            待更新的聚合根
	 */
	protected abstract void doUpdate(TAggregateRoot aggregateRoot);

	/**
	 * 返回一个boolean值，该值表明该聚合根是否存在 该方法应当由子类重写
	 * 
	 * @param specification
	 *            规约
	 * @return boolean 如果符合指定规约条件的聚合根存在，返回true，否则返回false
	 */
	protected abstract boolean doExists(
			ISpecification<TAggregateRoot> specification);

	protected abstract boolean doExistsById(UUID id, String tableName,
			String idName);

	protected abstract int doExecuteUpdate(ISpecification<TAggregateRoot> specification);
		
	protected abstract int doGetTotalCount(ISpecification<TAggregateRoot> specification);
}
