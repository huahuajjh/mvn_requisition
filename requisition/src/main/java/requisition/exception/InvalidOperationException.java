package com.tq.requisition.exception;

/**
 * 当方法调用对于对象的当前状态无效时引发的异常
 * @author jjh
 * @time 2015-12-17 01:20
 */
@SuppressWarnings("serial")
public class InvalidOperationException extends RuntimeException{
	public InvalidOperationException(String message) {
		super(message);
	}
	
}
