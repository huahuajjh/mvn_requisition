package com.tq.requisition.exception;

/**
 * 领域操作异常父类
 * @author jjh
 * @time 2015-12-23 14:08
 */
@SuppressWarnings("serial")
public class DomainException extends Exception{
	private String msg;

	public DomainException(String message) {
		super(message);
		this.msg = message;
	}

	@Override
	public String getMessage() {		
		return msg;
	}	
}
