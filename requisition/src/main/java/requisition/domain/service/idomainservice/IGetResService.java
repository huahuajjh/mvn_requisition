package com.tq.requisition.domain.service.idomainservice;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.model.resource.Resource;

public interface IGetResService {

//	/**
//	 * 获取用户所拥有的资源
//	 * 
//	 * @param userId
//	 *            用户DI
//	 * @param hierarchy
//	 *            资源层级
//	 * @return 资源集合
//	 */
//	List<Resource> getResourceByUserId(UUID userId, int hierarchy);
//
//	/**
//	 * 获取用户所拥有的资源
//	 * 
//	 * @param userId
//	 *            用户DI
//	 * @param hierarchy
//	 *            资源层级
//	 * @param ResourceType
//	 *            资源类型
//	 * @return 资源集合
//	 */
//	List<Resource> getResourceByUserId(UUID userId, ResourceType type,
//			int hierarchy);
//	
	/**
	 * 根据用户获取资源
	 * @param uid
	 * @return
	 */
	List<Resource> getResByUserId(UUID uid);
}
