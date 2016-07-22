package com.tq.requisition.test.infrastructure.ServiceLocator;

import org.junit.Test;

import com.tq.requisition.infrastructure.log.ILog;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestServiceLocator {
	@Test
	public void getServiceTest() {
		ServiceLocator.instance().getService(ILog.class);
		
	}
}
