package com.tq.requisition.infrastructure.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.springframework.beans.BeanUtils;


/**
 * hql查詢結果與pojo的映射，有些查詢不需要返回全部字段，有些查詢需要聯合查詢
 * 	此時查詢結果不會返回當初映射好的實體，請使用此類來映射需要的pojo類
 * 
 * @author jjh
 * @time 2015-12-25 0:35
 */
public final class Hql2Pojo {
	public static Object toPojo(Map<String, Object> objs,Class<?> target)throws IllegalAccessException, InvocationTargetException, InstantiationException {
		if(null == objs)
		{
			return null;
		}
		//根據傳入的目標pojo的Class對象實例化
		Object _target = target.newInstance();
		if(_target != null)
		{
			for (Map.Entry<String, Object> entry: objs.entrySet()) {
				BeanUtils.copyProperties(_target,entry.getKey(),entry.getValue().getClass());
			}
		}
		return _target;
	}
}
