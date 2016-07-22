package com.tq.requisition.domain.service.idomainservice;

import java.util.UUID;

/**
 * 删除部门领域服务
 * 
 * @author jjh
 * @time 2015-12-26 16:00
 */
public interface IDeptRemoveService {
	/**
	 * 删除指定id的部门
	 * @param deptId
	 * 		部门id
	 */
	void removeDept(UUID deptId);

}
