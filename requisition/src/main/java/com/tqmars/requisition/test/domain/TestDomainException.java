package com.tqmars.requisition.test.domain;

import org.junit.Test;

import com.tqmars.requisition.exception.DomainException;


public class TestDomainException {
	@Test
	public void ctorTest() throws DomainException {
		throw new DomainException("领域业务异常");
	}
}
