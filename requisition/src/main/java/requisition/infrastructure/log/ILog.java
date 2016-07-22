package com.tq.requisition.infrastructure.log;

/**
 * @description
 * 		日志接口，继承该接口实现日志系统的实现
 * @author jjh
 * @time 2015-12-14 18:19
 */
public interface ILog {
	/**
	 *	info级别日志输出
	 */	
	public void info(Object message);
	
	/**
	 *	info级别日志输出
	 * @param className
	 * 		输出日志的class name
	 * @param methodName
	 * 		输出日志的method name
	 * @param message
	 * 		日志消息
	 */
	public void info(String className,String methodName,Object message);
	
	/**
	 *	debug级别日志输出
	 */
	public void debug(Object message);
	
	/**
	 *	info级别日志输出
	 * @param className
	 * 		输出日志的class name
	 * @param methodName
	 * 		输出日志的method name
	 * @param message
	 * 		日志消息
	 */
	public void debug(String className,String methodName,Object message);
		
	/**
	 *	error级别日志输出
	 */
	public void error(Object message);
	
	/**
	 *	info级别日志输出
	 * @param className
	 * 		输出日志的class name
	 * @param methodName
	 * 		输出日志的method name
	 * @param message
	 * 		日志消息
	 */
	public void error(String className,String methodName,Object message);
	
}
