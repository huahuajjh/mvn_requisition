package com.tq.requisition.domain.model.roleAccount;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.domain.model.role.Role;

/**
 * 角色用户关系仓储接口
 * @author jjh
 * @time 2015-12-21 17:32
 */
public interface IRoleAccountRepository extends IRepository<RoleAccount>{
	/**
	 * 根据用户id获取角色
	 * @param uId
	 * 		用户id
	 * @return Role
	 * 		角色对象
	 */
	Role getRoleByUid(UUID uId);
	
	/**
	 * 根据角色id获取用户列表
	 * @param rId
	 * 		角色id
	 * @return List<Account>
	 * 		用户集合
	 */
	List<Account> getUsersByRid(UUID rId);
	
	/**
	 * 创建账户角色关系	
	 * @param uid
	 * 		账户id
	 * @param rId
	 * 		角色id
	 */
	void	createRelationship(UUID uid,UUID rId);
		
	/**
	 * 解除指定账户的角色
	 * @param uid
	 * 		账户id
	 * @param rId
	 * 		角色id
	 */
	void removeRelationship(UUID uid,UUID rId);
	
	/**
	 * 移除指定账户的所有角色，这个操作只标记删除标记为true
	 * @param uid
	 * 		账户id
	 */
	void removeAllRelationships(UUID uid);

	/**
	 * 更改用户角色
	 * @param uid
	 * 		用户id
	 * @param roleid
	 * 		角色id
	 */
	void	changeRole(UUID uid,UUID roleid);
}
