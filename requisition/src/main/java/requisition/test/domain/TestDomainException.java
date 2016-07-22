package com.tq.requisition.test.domain;

import org.junit.Test;

import com.tq.requisition.exception.DomainException;


public class TestDomainException {
	@Test
	public void ctorTest() throws DomainException {
		throw new DomainException("领域业务异常");
	}
}
