package com.tq.requisition.infrastructure.log;

/**
 * @description
 * 		log4j工厂，此类不能被继承
 * @author jjh
 * @time 2015-12-14 16:41
 */
public final class LoggerFactory {
	/**fields*/
	private final static ILog logger;
	
	private LoggerFactory(){}
	
	static{		
		logger = new LogImpl();
	}
	
	/**
	 * @description
	 * 		获取Logger对象
	 * @return
	 * 		Logger对象
	 */
	public final static ILog logger() {
		return logger;
	}
}
