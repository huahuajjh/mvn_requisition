package com.tq.requisition.exception;

/**
 * 指定的查询条件下，无法查找出结果异常
 * @author jjh
 * @time 2015-12-23 14:09
 */
@SuppressWarnings("serial")
public class SpecifiedObjectDoesNotExistsException extends DomainException{

	public SpecifiedObjectDoesNotExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
