package com.tq.requisition.infrastructure.Repository;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.IRepository.Repository;
import com.tq.requisition.domain.Specification.ISpecification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.share.IAggregateRoot;
import com.tq.requisition.domain.share.IEntity;
import com.tq.requisition.exception.QueryStatementException;
import com.tq.requisition.infrastructure.Specifications.Expression.CriteriaExpression;
import com.tq.requisition.infrastructure.Specifications.share.ExistsByIdSpecification;

/**
 * Hibernate仓储
 * 
 * @param <TAggregatorRoot>
 *            聚合根
 * @author jjh
 * @time 2015-12-17 13:45
 */
public class HbRepository<TAggregatorRoot extends IAggregateRoot> extends
		Repository<TAggregatorRoot> {
	private IHbRepositoryContext hbContext;

	/**
	 * 初始化
	 * 
	 * @param context
	 *            仓储上下文对象
	 */
	public HbRepository(IRepositoryContext context) {
		super(context);
		hbContext = (IHbRepositoryContext) context;
	}

	/**
	 * 获取仓储上下文
	 * 
	 * @return IRepositoryContext 仓储上下文实例
	 */
	@Override
	public IRepositoryContext context() {
		return hbContext;
	}

	/* protect methods */
	/**
	 * 获取hibernate的仓储上下文
	 * 
	 * @return IHbRepositoryContext hibernate仓储上下文
	 */
	protected IHbRepositoryContext hbContext() {
		return hbContext;
	}

	/**
	 * 检测rootClass
	 */
	private void checkRootClassNull() {
		if (rootClass == null)
			throw new RuntimeException(
					"HbRepository:checkRootClassNull--rootClass为被初始化，请使用setAggregatorRootClass方法指定要持久化的实体类型");
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
	@Override
	protected TAggregatorRoot doGetByKey(Class<TAggregatorRoot> cls, UUID key) {
		return hbContext.session().get(cls, key);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doAdd(TAggregatorRoot aggregateRoot) {
		hbContext.registerNew(aggregateRoot);
	}

	/**
	 * 根据规约获取聚合根集合
	 * 
	 * @param specification
	 *            规约
	 * @return List<TAggregatorRoot> 聚合根实例集合
	 */
	@Override
	protected List<TAggregatorRoot> doGetAll(			
			ISpecification<TAggregatorRoot> specification) {
		OperationType type = specification.getHqlExpression().getType();
		switch (type) {
		case SQL:
			return doGetAllBySql(specification);
		case HQL:
			return doGetAllByHql(specification);
		default:
			return doGetAllByCriteria(specification);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected List<TAggregatorRoot> doGetAll(
			ISpecification<TAggregatorRoot> specification, int indexPage,
			int pageSize) {
		checkRootClassNull();
		Criteria ct = hbContext.session().createCriteria(super.rootClass);
		CriteriaExpression criteriaExpression = (CriteriaExpression) specification
				.getCriteriaExpression();
		for (Criterion criterion : criteriaExpression.getRestriction()) {
			ct.add(criterion);
		}
		ct.setFirstResult(indexPage);
		ct.setMaxResults(pageSize);
		return ct.list();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doRemove(TAggregatorRoot aggregateRoot) {
		hbContext.registerDeleted(aggregateRoot);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doUpdate(TAggregatorRoot aggregateRoot) {
		hbContext.registerModified(aggregateRoot);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doRemove(ISpecification<TAggregatorRoot> specification) {
		throw new NotImplementedException("未实现的方法");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doRemove(Class<TAggregatorRoot> cls, UUID key) {
		TAggregatorRoot entity = getByKey(rootClass, key);
		if(entity == null)
		{
			throw new NullPointerException("根据id未查询到实体，数据为null");
		}
		doRemove(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean doExists(ISpecification<TAggregatorRoot> specification) {
		IHqlExpression expression = specification.getHqlExpression();
		Object[] objects = expression.getParameters();
		Query hqlQuery;
		SQLQuery sqlQuery;
		OperationType type = expression.getType();
		int result = 0;
		boolean r;

		if (type == OperationType.SQL) {
			r = expression.getSql().toLowerCase().contains(" count(");
			if (!r) {
				throw new QueryStatementException("请使用count的sql子句");
			}
			sqlQuery = hbContext.session().createSQLQuery(expression.getSql());
			for (int i = 0; i < objects.length; i++) {
				sqlQuery.setParameter(i, objects[i]);
			}
			result = ((Number) sqlQuery.uniqueResult()).intValue();
		}

		if (type == OperationType.HQL) {
			r = expression.getHql().toLowerCase().contains(" count(");
			if (!r) {
				throw new QueryStatementException("请使用count的sql子句");
			}
			hqlQuery = hbContext.session().createQuery(expression.getHql());
			for (int i = 0; i < objects.length; i++) {
				hqlQuery.setParameter(i, objects[i]);
			}
			result = ((Number) hqlQuery.uniqueResult()).intValue();
		}

		if (result <= 0) {
			return false;
		}
		return true;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected boolean doExistsById(UUID id, String tableName, String idName) {
		return doExists(new ExistsByIdSpecification(rootClass, id, tableName,
				idName));
	}

	@Override
	protected int doExecuteUpdate(ISpecification<TAggregatorRoot> specification) {
		OperationType type = specification.getHqlExpression().getType();
		switch (type) {
		case SQL:
			return doExecuteUpdateBySql(specification);
		case HQL:
			return doExecuteUpdateByHql(specification);
		default:
			return doExecuteUpdateByCriteria(specification);
		}
	}

	@Override
	protected int doGetTotalCount(ISpecification<TAggregatorRoot> specification) {
		IHqlExpression expression = specification.getHqlExpression();
		if(expression.getType() == OperationType.HQL)
		{
			return doGetTotalCountByHql(specification);
		}
		Object[] objects = expression.getParameters();
		SQLQuery query = hbContext.session().createSQLQuery(expression.getSql());
		if(!(objects==null))
		{
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		Object result = query.uniqueResult();
		int count = ((Number) result).intValue();
		return count;
	}
	
	private int doGetTotalCountByHql(ISpecification<TAggregatorRoot> specification){
		IHqlExpression expression = specification.getHqlExpression();
		Object[] objects = expression.getParameters();
		Query query = hbContext.session().createQuery(expression.getHql());
		if(!(objects==null))
		{
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		Object result = query.uniqueResult();
		int count = ((Number) result).intValue();
		return count;
	}
	
	/**
	 * 根据hql查询实体集合，请不要轻易调用该方法，该方法为了给聚合根一致性提交提供其界限范围内的领域实体
	 * 持久化使用的。如果不是该需求，请使用聚合根仓储提供的api
	 * @param query
	 * 		查询语句
	 * @param objects
	 * 		查询参数
	 * @return
	 */
	protected <T extends IEntity> List<T> getByHql(String query,Object... objects) {
		Query hql = hbContext.session().createQuery(query);
		if(objects!=null)
		{
			for (int i = 0; i < objects.length; i++) {
				hql.setParameter(i, objects[i]);
			}
		}
		return hql.list();
	}

	/**
	 * 根据sql查询实体集合，请不要轻易调用该方法，该方法为了给聚合根一致性提交提供其界限范围内的领域实体
	 * 持久化使用的。如果不是该需求，请使用聚合根仓储提供的api
	 * @param query
	 * 		查询语句
	 * @param objects
	 * 		查询参数
	 * @return
	 */
	protected <T extends IEntity> List<T> getBySql(Class<T> cls,String query,Object... objects) {
		SQLQuery sql = hbContext.session().createSQLQuery(query).addEntity(cls);
		if(objects!=null)
		{
			for (int i = 0; i < objects.length; i++) {
				sql.setParameter(i, objects[i]);
			}
		}
		return sql.list();
	}
		
	/**
	 * 為實體持久化，該方法為聚合根下的實體提供持久化操作。
	 * @param entity
	 * 		待新增的實體
	 */
	protected <T extends IEntity> void add(T entity) {
		hbContext.session().save(entity);
	}
	
	/**
	 * 根据唯一约束的字段查找id
	 * @param queryStr
	 * 		sql表达式
	 * @param param
	 * 		参数
	 * @return UUID
	 * 		UUID
	 */
	protected UUID getIdByUniqueCol(String queryStr,Object param) {
		SQLQuery query = hbContext.session().createSQLQuery(queryStr);
		query.setParameter(0, param);
		List<Object> id = query.list();
		if(null==id || id.size()==0)
		{
			return null;
		}
		return UUID.fromString(id.get(0).toString());
	}
	
	/**
	 * 根据sql删除实体集合，请不要轻易调用该方法，该方法为了给聚合根一致性提交提供其界限范围内的领域实体
	 * 持久化使用的。如果不是该需求，请使用聚合根仓储提供的api
	 * @param query
	 * 		删除语句
	 * @param objects
	 * 		删除参数
	 * @return
	 */
	protected void removeBySql(String sqlStr,Object... objects) {
		SQLQuery sql = hbContext.session().createSQLQuery(sqlStr);
		if(objects!=null)
		{
			for (int i = 0; i < objects.length; i++) {
				sql.setParameter(i, objects[i]);
			}
		}
		sql.executeUpdate();
	}
	
	protected <T> T get(Class<T> cls,UUID id) {
		return hbContext.session().get(cls, id);
	}
	
	protected <T> void update(T t) {
		hbContext.session().update(t);
	}
	
	/**
	 * 根据hql删除实体集合，请不要轻易调用该方法，该方法为了给聚合根一致性提交提供其界限范围内的领域实体
	 * 持久化使用的。如果不是该需求，请使用聚合根仓储提供的api
	 * @param query
	 * 		删除语句
	 * @param objects
	 * 		删除参数
	 * @return
	 */
	protected void removeByHql(String hqlStr,Object... objects) {
		Query sql = hbContext.session().createQuery(hqlStr);
		if(objects!=null)
		{
			for (int i = 0; i < objects.length; i++) {
				sql.setParameter(i, objects[i]);
			}
		}
		sql.executeUpdate();
	}
	
	/**
	 * 根据sql更新实体集合，请不要轻易调用该方法，该方法为了给聚合根一致性提交提供其界限范围内的领域实体
	 * 持久化使用的。如果不是该需求，请使用聚合根仓储提供的api
	 * @param query
	 * 		更新语句
	 * @param objects
	 * 		更新参数
	 * @return
	 */
	protected void updateBySql(String sqlStr,Object... objects) {
		SQLQuery sql = hbContext.session().createSQLQuery(sqlStr);
		if(objects!=null)
		{
			for (int i = 0; i < objects.length; i++) {
				sql.setParameter(i, objects[i]);
			}
		}
		sql.executeUpdate();
	}
	
	/**
	 * 根据hql更新实体集合，请不要轻易调用该方法，该方法为了给聚合根一致性提交提供其界限范围内的领域实体
	 * 持久化使用的。如果不是该需求，请使用聚合根仓储提供的api
	 * @param query
	 * 		更新语句
	 * @param objects
	 * 		更新参数
	 * @return
	 */
	protected void updateByHql(String hqlStr,Object... objects) {
		Query hql = hbContext.session().createQuery(hqlStr);
		if(objects!=null)
		{
			for (int i = 0; i < objects.length; i++) {
				hql.setParameter(i, objects[i]);
			}
		}
		hql.executeUpdate();
	}
	
	/* private methods */
	@SuppressWarnings("unchecked")
	private List<TAggregatorRoot> doGetAllBySql(ISpecification<TAggregatorRoot> specification) 
	{
			checkRootClassNull();
			IHqlExpression expression = specification.getHqlExpression();
			Object[] objects = expression.getParameters();
			SQLQuery query = hbContext.session()
					.createSQLQuery(expression.getSql()).addEntity(rootClass);
			if (!(objects == null)) {
				for (int i = 0; i < objects.length; i++) {
					query.setParameter(i, objects[i]);
				}
			}
			return query.list();
	}

	@SuppressWarnings("unchecked")
	private List<TAggregatorRoot> doGetAllByHql(
			ISpecification<TAggregatorRoot> specification) {
		checkRootClassNull();
		IHqlExpression expression = specification.getHqlExpression();
		Object[] objects = expression.getParameters();
		Query query = hbContext.session().createQuery(expression.getHql());
		if (!(objects == null)) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query.list();
	}

	@SuppressWarnings("unchecked")
	private List<TAggregatorRoot> doGetAllByCriteria(
			ISpecification<TAggregatorRoot> specification) {
		checkRootClassNull();
		Criteria ct = hbContext.session().createCriteria(rootClass);
		CriteriaExpression criteriaExpression = (CriteriaExpression) specification
				.getCriteriaExpression();
		for (Criterion criterion : criteriaExpression.getRestriction()) {
			ct.add(criterion);
		}
		return ct.list();
	}

	protected List<Object> getAllBySqlJoin(ISpecification specification){
		IHqlExpression expression = specification.getHqlExpression();
		Object[] objects = expression.getParameters();
		SQLQuery query = hbContext.session()
				.createSQLQuery(expression.getSql());
		if (!(objects == null)) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query.list();
	}
	
	protected List<Object[]> getAllBySqlJoin(ISpecification specification,int pageIndex,int pageSize){
		IHqlExpression expression = specification.getHqlExpression();
		Object[] objects = expression.getParameters();
		SQLQuery query = hbContext.session()
				.createSQLQuery(expression.getSql());
		if(pageIndex!=-1 || pageSize != -1)
		{
			query.setFirstResult(pageIndex);
			query.setMaxResults(pageSize);
		}
		if (!(objects == null)) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query.list();
	}
	
	protected List getAllByHqlJoin(ISpecification specification,int pageIndex,int pageSize){
		IHqlExpression expression = specification.getHqlExpression();
		Object[] objects = expression.getParameters();
		Query query = hbContext.session()
				.createQuery(expression.getHql());
		if(pageIndex!=-1 || pageSize != -1)
		{
			query.setFirstResult(pageIndex);
			query.setMaxResults(pageSize);
		}
		if (!(objects == null)) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
		}
		return query.list();
	}
	
	private int doExecuteUpdateBySql(
			ISpecification<TAggregatorRoot> specification) {
		checkRootClassNull();
		IHqlExpression expression = specification.getHqlExpression();
		Object[] objects = expression.getParameters();
		SQLQuery query = hbContext.session()
				.createSQLQuery(expression.getSql());
		if (!(objects == null)) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
			return query.executeUpdate();
		}
		return query.executeUpdate();
	}

	private int doExecuteUpdateByHql(
			ISpecification<TAggregatorRoot> specification) {
		checkRootClassNull();
		IHqlExpression expression = specification.getHqlExpression();
		Object[] objects = expression.getParameters();
		Query query = hbContext.session().createQuery(expression.getHql());
		if (!(objects == null)) {
			for (int i = 0; i < objects.length; i++) {
				query.setParameter(i, objects[i]);
			}
			return query.executeUpdate();
		}
		return -1;
	}

	private int doExecuteUpdateByCriteria(
			ISpecification<TAggregatorRoot> specification) {
		return 0;
	}

}
