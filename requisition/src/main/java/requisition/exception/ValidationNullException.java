package com.tq.requisition.exception;

/**
 * 验证运行时异常，该类不能被继承
 * @author jjh
 * @time 2015-12-17 01:20
 */
@SuppressWarnings("serial")
public final class ValidationNullException extends RuntimeException{

	public ValidationNullException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
		
}
