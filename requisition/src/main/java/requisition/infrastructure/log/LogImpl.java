package com.tq.requisition.infrastructure.log;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * @description
 * 		Log的log4j实现
 * @author jjh
 * @time 2015-12-14 18:21
 */
public final class LogImpl implements ILog{
	private final static Logger logger;
	private static InputStream is = null;
	
	static{
		String path = LogImpl.class.getResource("").getPath();
		path = path.substring(1,path.indexOf("classes/"));
		
		is = LogImpl.class.getResourceAsStream("/log4j.properties");		
		PropertyConfigurator.configure(is);
		logger = Logger.getLogger(LogImpl.class);
	}
	
	/**
	 *	info级别日志输出
	 */	
	@Override
	public void info(Object message) {
		logger.info(message);
	}

	/**
	 *	info级别日志输出
	 * @param className
	 * 		输出日志的class name
	 * @param methodName
	 * 		输出日志的method name
	 * @param message
	 * 		日志消息
	 */
	@Override
	public void debug(Object message) {
		logger.debug(message);
	}

	/**
	 *	debug级别日志输出
	 */
	@Override
	public void error(Object message) {
		logger.error(message);
		
	}

	@Override
	public void info(String className, String methodName,Object message) {
		logger.info("["+className+"-"+methodName+"]"+message);
		
	}

	@Override
	public void debug(String className, String methodName,Object message) {
		logger.debug("["+className+"-"+methodName+"]"+message);
		
	}

	@Override
	public void error(String className, String methodName,Object message) {
		logger.error("["+className+"-"+methodName+"]"+message);
		
	}

}
