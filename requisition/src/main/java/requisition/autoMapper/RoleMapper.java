package com.tq.requisition.autoMapper;

import com.tq.requisition.domain.model.role.Role;
import com.tq.requisition.presentation.dto.sysMgt.RoleDto;

/**
 * role聚合根与dto转换类,此类不能被继承
 * @author jjh
 * @time 2015-12-24 9:43
 */
public final class RoleMapper {
	/**
	 * role dto转role领域实体
	 * @param RoleDto
	 * 		待转换的role dto
	 * @return role
	 * 		返回role领域实体
	 */
	public static Role dto2Model(RoleDto dto){		
		Role role = new Role();
		role.setId(dto.getId());
		role.setDel(false);
		role.setRoleName(dto.getName());
		return role;
	}
	
	/**
	 * role领域实体转role dto
	 * @param Role
	 * 		待转换的role
	 * @return RoleDto
	 * 		返回RoleDto
	 */
	public static RoleDto model2Dto(Role model){
		return new RoleDto(model.getRoleName(),model.getId());
	}
}
