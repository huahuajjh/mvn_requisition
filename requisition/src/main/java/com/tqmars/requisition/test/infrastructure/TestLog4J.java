package com.tqmars.requisition.test.infrastructure;

import java.util.logging.Logger;

import org.junit.Test;

import com.tqmars.requisition.infrastructure.log.ILog;
import com.tqmars.requisition.infrastructure.log.LoggerFactory;

public class TestLog4J{
	private Logger logger;
	private ApplicationContext factory;
	
	public void init() {
		PropertyConfigurator.configure("/WEB-INF/config/log4j.properties");
		logger = Logger.getLogger(TestLog4J.class);
	}
	
	@Test
	public void consoleLogTest() {
		//logger.info("log info");
		//logger.debug("log debug");
		LoggerFactory.logger().info("hhj");
	}
	
	@Test
	public void loggerFactoryTest() {
		factory = new FileSystemXmlApplicationContext("config/spring.xml");
		
		ILog log = factory.getBean(ILog.class);
		log.info("spring test ss");
		
		
	}
	
	@Test
	public void testLog() {
		LoggerFactory.logger().debug("test11");
	}
}
