package com.tq.requisition.domain.model.resRole;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;

/**
 * 角色资源仓储接口
 * 
 * @author jjh
 * @time 2015-12-21 21:19
 */
public interface IResRoleRepository extends IRepository<ResRole> {
	/**
	 * 根据角色ID获取角色资源ID
	 * 
	 * @param roldIds
	 *            角色Id集合
	 * @return 资源ID集合
	 */
	List<UUID> getResIdsByRoleIds(UUID... roldIds);
	
	/**
	 * 移除指定角色id下的所有权限资源，该方法仅仅标记删除标记为true
	 * @param rId
	 * 		角色id
	 * @return int
	 * 		影响行数，-1表示未找到对应的rid
	 */
	int removeAllPermissionByRId(UUID rId);

	/**
	 * 为指定的角色分配资源
	 * @param rid
	 * 		角色id
	 * @param resId
	 * 		资源id
	 */
	void assignRes4Role(UUID rid,UUID resId);
	
	/**
	 * 根據角色id獲取資源id集合
	 * @param rid
	 * 		角色id
	 * @return
	 */
	public List<UUID> getResourcesByRid(UUID rid);
	
}
