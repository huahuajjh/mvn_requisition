package com.tq.requisition.domain.share;

import java.util.UUID;

/**
 * domain实体接口，表明实现该接口的实例是domain实体
 * @author jjh
 * @time 2015-12-14 13:20 
 */
public interface IEntity {
	/**
	 * 获取一个id
	 * @return UUID
	 * 		UUID
	 */
	UUID id();
	
	/**
	 * 设置一个id
	 * @param id
	 * 		UUID
	 */
	void id(UUID _id);
}
