package com.tq.requisition.domain.share;

/**
 * 值对象接口
 * @author jjh
 * @time 2015-12-18 11:02
 */
public interface IValueObject<T> {
	boolean sameValueAs(T obj);
}
