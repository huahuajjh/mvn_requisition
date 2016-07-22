package com.tq.requisition.exception;

/**
 * 查询语句异常类
 * @author jjh
 * @time 2015-12-22 2:50
 */
@SuppressWarnings("serial")
public class QueryStatementException extends RuntimeException{
	public QueryStatementException(String message) {
		super(message);
	}
	
}
