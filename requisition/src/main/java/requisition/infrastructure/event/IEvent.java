package com.tq.requisition.infrastructure.event;

import java.util.Date;
import java.util.UUID;

/**
 * 表明实现该接口类型是一种事件
 * @author jjh
 * @time 2015-12-16 12:46
 */
public interface IEvent {
	/**
	 * @description
	 * 		获取或设置一个事件id
	 * @return UUID
	 * 		返回一个UUID类型数据
	 */
	UUID getUUID();
	void setUUID(UUID uuid);
	
	/**
	 * @description
	 * 		获取或设置一个时间戳
	 * @return UUID
	 * 		返回一个日期类型的时间戳
	 */
	Date getTimeStamp();
	void setDate(Date timeStamp);
	
}
