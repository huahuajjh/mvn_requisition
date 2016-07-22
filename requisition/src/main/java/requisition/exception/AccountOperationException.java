package com.tq.requisition.exception;

/**
 * 账户操作异常
 * @author jjh
 * @time 2015-12-23 15:42
 */
@SuppressWarnings("serial")
public class AccountOperationException extends DomainException {

	public AccountOperationException(String message) {
		super(message);
	}

}
