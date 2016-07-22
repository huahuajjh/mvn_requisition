package com.tq.requisition.domain.model.userRole;

import java.util.UUID;

import com.tq.requisition.domain.share.AggregateRoot;

/**
 * 角色用户聚合根
 * @author jjh
 * @time 2015-12-19 14:23
 */
public class UserRole extends AggregateRoot{
	/**用户id*/
	private UUID uid;
	/**角色id*/
	private UUID rid;	
}
