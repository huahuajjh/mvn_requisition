package com.tq.requisition.infrastructure.event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.scheduling.annotation.Async;

/**
 * 事件聚合器
 * @author jjh
 * @time 2015-12-16 12:57
 */
public class EventBus implements IEventBus{
	/**以事件类型为key,事件处理器集合为value的map对象*/
	private final Map<Class<IEvent>, List<Object>> eventMap = new HashMap<>();
	
	@Async
	@Override
	public void subscribe(IEventHandler<IEvent> handler) {
		if(eventMap.containsKey(IEvent.class))
		{
			List<Object> handlers = eventMap.get(IEvent.class);
			if(handlers != null)
			{
				handlers.add(handler);
			}
			else {
				handlers = new ArrayList<Object>();
				handlers.add(handler);
			}
		}
		else {
			List<Object> hList = new ArrayList<>();
			hList.add(handler);
			eventMap.put(IEvent.class, hList);
		}
	}

	@Async
	@Override
	public void unsubscribe(IEventHandler<IEvent> handler) {
		  if(eventMap.containsKey(IEvent.class))
		  {
			  List<Object> handlers = eventMap.get(IEvent.class);
			  handlers.remove(handler);
		  }
	}

	@Override
	public void publish(IEvent evt) {
		List<Object> handlers = eventMap.get(evt.getClass());
		for (Object handler : handlers) {
			
			@SuppressWarnings("unchecked")
			IEventHandler<IEvent> _handler = (IEventHandler<IEvent>)handler;
			_handler.handle(evt);
		}
		 
	}

}
