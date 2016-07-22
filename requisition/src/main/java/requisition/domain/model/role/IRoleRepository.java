package com.tq.requisition.domain.model.role;

import java.util.List;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.exception.InvalidOperationException;
import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.infrastructure.utils.TotalCount;

/**
 * 角色仓储接口
 * 
 * @author jjh
 * @time 2015-12-21 13:16
 * 
 */
public interface IRoleRepository extends IRepository<Role> {
	/**
	 * 创建角色
	 * @param dto
	 * 角色dto
	 */
	Formater createRole(Role role) throws InvalidOperationException;
	
	/**
	 * 根据角色名称获取角色列表，模糊查询
	 * @param name
	 * 		角色名
	 * @param pageIndex
	 * 		起始页
	 * @param pageNum
	 * 		页码
	 * @param totalCount TODO
	 * @return List<RoleDto>
	 * 		角色集合
	 */
	List<Role> getRoleList(String name, int pageIndex, int pageNum, TotalCount totalCount);

	/**
	 * 修改角色
	 * @param dto
	 * 		待修改的角色dto
	 */
	Formater	modifyRole(Role role) throws InvalidOperationException;
	
	/**
	 * 根据角色名称获取角色
	 * @param roleName
	 * 		角色名称
	 * @return RoleDto
	 * 		角色dto对象
	 */
	Role getRoleByName(String roleName);

	/**
	 * 获取所有角色
	 * @return
	 */
	List<Role> getAllRole();
}
