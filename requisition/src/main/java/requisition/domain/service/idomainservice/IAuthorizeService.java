package com.tq.requisition.domain.service.idomainservice;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.model.resource.Resource;

/**
 * 
 * @author Administrator
 *
 */
public interface IAuthorizeService {

	/**
	 * 获取用户所拥有的资源
	 * 
	 * @param userId
	 *            用户DI
	 * @param hierarchy
	 *            资源层级
	 * @return 资源集合
	 */
	List<Resource> getResourceByUserId(UUID userId, int hierarchy);
}
