﻿package com.tqmars.requisition.test.domain.ServiceLocator;

import org.junit.Test;

import com.tqmars.requisition.infrastructure.log.LoggerFactory;

public class TestServiceLocator {
	@Test
	public void getServiceTest() {
//		ILog logger = ServiceLocator.instance().getService(ILog.class);
//		logger.error(this.getClass().getName(), "getServiceTest()", "xxxxx");
		LoggerFactory.logger().info("asdd");
	}
}
