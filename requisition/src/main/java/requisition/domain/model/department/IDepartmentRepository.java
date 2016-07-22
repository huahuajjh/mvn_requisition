package com.tq.requisition.domain.model.department;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.exception.DomainException;

/**
 * 部门聚合仓储
 * 
 * @author jjh
 * @time 2015-12-21 16：38
 */
public interface IDepartmentRepository extends IRepository<Department>{
	
	/**
	 * 根据组织id检测新增的部门名称是否唯一
	 * @param orgId
	 * 		组织id
	 * @param deptName
	 * 		部门名称
	 * @return boolean
	 * 		返回一个boolean值，表明该部门名称是否在当前组织中是唯一的
	 */
	boolean isDeptNameUnique(UUID orgId,String deptName);
	
	/**
	 * 在指定的组织下面创建其部门，该部门属于该组织
	 * @param dept
	 * 		待創建的部門實體
	 * 		組織id
	 * @return TODO
	 */
	Department createDept(Department dept) throws DomainException;
	
	/**
	 * 修改部门实体
	 * @param dept
	 * 		待修改的部门实体
	 */
	void modifyDept(Department dept) throws DomainException;
	
	/**
	 * 根据组织id获取部门集合
	 * @param orgId
	 * 		组织id
	 * @return List<Department>
	 * 		部门集合
	 */
	List<Department> getDeptByOrgId(UUID orgId);
	
	/**
	 * 根据id删除指定的部门
	 * @param deptId
	 * 		部门id
	 */
	void removeDept(UUID deptId);
}
