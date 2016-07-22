package com.tq.requisition.domain.share;

import java.util.UUID;

/**
 * 实体抽象类
 * @author jjh
 * @time 2015-12-19 18:48 
 */
public abstract class Entity implements IEntity{
	protected UUID id;
	
	/**
	 * 获取一个id
	 * @return UUID
	 * 		UUID
	 */
	//@Override
	public UUID id() {
		return id;
	}

	/**
	 * 设置一个id
	 * @param id
	 * 		UUID
	 */
	//@Override
	public void id(UUID _id) {
		this.id = _id;
	}
	
}
