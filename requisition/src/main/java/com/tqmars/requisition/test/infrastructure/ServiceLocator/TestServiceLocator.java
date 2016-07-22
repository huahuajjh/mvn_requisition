package com.tqmars.requisition.test.infrastructure.ServiceLocator;

import org.junit.Test;

import com.tqmars.requisition.infrastructure.log.ILog;
import com.tqmars.requisition.infrastructure.serviceLocator.ServiceLocator;

public class TestServiceLocator {
	@Test
	public void getServiceTest() {
		ServiceLocator.instance().getService(ILog.class);
		
	}
}
