package com.tq.requisition.domain.IRepository;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.Specification.ISpecification;
import com.tq.requisition.domain.share.IAggregateRoot;

/**
 * 表明实现该接口的实例为domain中仓储的接口实现，将该接口定义在domain中，是为了实现与技术细节的持久化层（基础设施层infrastructure）的分离（依赖倒置）
 * 同时，该接口泛型类型限定必须是实现了IAggregateRoot的实例，即泛型参数类型必须是聚合根
 * @author jjh
 * @time 2015-12-14 14:05
 */
public interface IRepository<TAggregateRoot extends IAggregateRoot> {
	/**
	 * 设置泛型类型class 	
	 * @param root
	 * 		泛型类型class
	 */
	public IRepository<TAggregateRoot> setAggregatorRootClass(Class<TAggregateRoot> root);
	
	/**
	 * 返回一个boolean值，该值表明该聚合根是否存在
	 * @param specification
	 * 		规约
	 * @return boolean
	 * 		如果符合指定规约条件的聚合根存在，返回true，否则返回false
	 */
	boolean exists(ISpecification<TAggregateRoot> specification);
	
	/**
	 * 获取仓储上下文
	 * @return IRepositoryContext
	 * 		仓储上下文
	 */
	IRepositoryContext context();
	
	/**
	 * 将指定的聚合跟添加到仓储中
	 * @param aggregateRoot
	 * 		待添加的聚合根
	 */
	void add(TAggregateRoot aggregateRoot);
	
	/**
	 * 根据聚合根的id值，获取聚合根
	 * @param key
	 * 		聚合根id
	 * @param cls
	 * 		聚合根class
	 * @return TAggregateRoot
	 * 		聚合根实例
	 */
	TAggregateRoot getByKey(Class<TAggregateRoot> cls,UUID key);
		
	/**
	 * 根据指定的规约，从仓储中获取所有符合条件的聚合根
	 * @param specification
	 * 		规约
	 * @return List<TAggregateRoot>
	 * 		符合条件的聚合根实例集合
	 */
	List<TAggregateRoot> getAll(ISpecification<TAggregateRoot> specification);
	
	/**
	 * 分页获取聚合根集合对象
	 * @param specification
	 * 		规约
	 * @param indexPage
	 * 		起始页
	 * @param pageSize
	 * 		页码大小
	 * @return List<TAggregateRoot>
	 * 		聚合根集合
	 */
	List<TAggregateRoot> getAll(ISpecification<TAggregateRoot> specification,int indexPage,int pageSize);
	
	/**
	 * 将指定的聚合根从仓储中删除
	 * @param aggregateRoot
	 * 		待删除的聚合根
	 */
	void remove(TAggregateRoot aggregateRoot);
	
	/**
	 * 根据规约删除聚合根
	 * @param specification
	 * 		规约
	 */
	void remove(ISpecification<TAggregateRoot> specification);
	
	/**
	 * 根据id删除聚合根
	 * @param cls
	 * 		聚合根class
	 * @param keyUuid
	 * 		uuid主键
	 */
	void removeByKey(Class<TAggregateRoot> cls,UUID keyUuid);
	
	/**
	 * 更新指定的聚合根
	 * @param aggregateRoot
	 * 		待更新的聚合根
	 */
	void update(TAggregateRoot aggregateRoot);
		
	/**
	 * 根据聚合根id检测是否存在该聚合根
	 * @param id
	 * 		聚合根id
	 * @param tableName
	 * 		表名
	 * @param idName
	 * 		id列名
	 * @return boolean
	 * 		存在返回true，否则返回false
	 */
	boolean existsById(UUID id,String tableName,String idName);

	/**
	 * 执行一次update或者delet命令
	 * @param specification
	 * 		规约
	 * @return int
	 * 		影响行数
	 */
	int executeUpdate(ISpecification<TAggregateRoot> specification);

	/**
	 * 获取总记录数
	 * @param tableName
	 * 		表名
	 * @return int
	 * 		总记录数
	 */
	int getTotalCount(ISpecification<TAggregateRoot> specification);
}
