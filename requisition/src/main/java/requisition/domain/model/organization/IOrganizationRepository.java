package com.tq.requisition.domain.model.organization;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.exception.InvalidOperationException;

/**
 * 组织聚合仓储接口
 * 
 * @author jjh
 * @time 2015-12-21 15:01
 */
public interface IOrganizationRepository extends IRepository<Organization> {
	/**
	 * 检测一个组织名称是否唯一
	 * 
	 * @param orgName
	 *            组织名称
	 * @return boolean 返回一个boolean值，表明该组织每次是否是唯一的
	 */
	boolean isOrgNameUnique(String orgName);

	/**
	 * 新增组织数据
	 * 
	 * @param org
	 *            组织数据
	 * @return TODO
	 * @throws InvalidOperationException
	 *             操作异常
	 */
	Organization createOrg(Organization org) throws DomainException;

	/**
	 * 修改组织信息
	 * 
	 * @param org
	 *            组织数据
	 * @throws InvalidOperationException
	 *             操作异常
	 */
	void editOrg(Organization org) throws DomainException;

	/**
	 * 获取组织数据集合
	 * 
	 * @return 组织数据集合
	 */
	List<Organization> getOrgList();
	
	/**
	 * 根据组织ID删除组织
	 * @param orgId 根据组织ID删除组织
	 */
	void deleteOrgByKey(UUID orgId);
}
