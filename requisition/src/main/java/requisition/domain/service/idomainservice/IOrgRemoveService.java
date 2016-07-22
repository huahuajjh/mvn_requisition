package com.tq.requisition.domain.service.idomainservice;

import java.util.UUID;

/**
 * 组织删除服务
 * @author jjh
 * @time 2015-12-26 14:44
 */
public interface IOrgRemoveService {
	/**
	 * 根据id删除组织
	 * @param Id
	 * 		组织id
	 */
	void removeOrgById(UUID Id);
}
