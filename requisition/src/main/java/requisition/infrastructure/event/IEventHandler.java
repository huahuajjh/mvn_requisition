package com.tq.requisition.infrastructure.event;

/**
 * @description
 * 		表明实现该接口的类型是一种事件处理器
 * @author jjh
 * @time 2015-12-16 12:54
 */
public interface IEventHandler<TEvent extends IEvent> {
	/**
	 * @description
	 * 		事件处理器处理方法
	 * @param evt
	 * 		事件接口实例
	 */
	void handle(TEvent evt);
}
