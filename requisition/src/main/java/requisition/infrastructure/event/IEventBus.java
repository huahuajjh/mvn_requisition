package com.tq.requisition.infrastructure.event;

/**
 * @description
 * 		表明该接口的实现类型是一种事件聚合器类型
 * @author jjh
 * @time 2015-12-16 12:57
 */
public interface IEventBus {
	
	/**
	 * @description
	 * 		事件订阅方法
	 * @param handler
	 * 		事件处理器
	 */
	void subscribe(IEventHandler<IEvent> handler);
	
	/**
	 * @description
	 * 		取消订阅方法
	 * @param handler
	 * 		事件处理器
	 */
	void unsubscribe(IEventHandler<IEvent> handler);
	
	/**
	 * @description
	 * 		发布事件	
	 * @param evt
	 * 		待发布的事件
	 */
	void publish(IEvent evt);
}
