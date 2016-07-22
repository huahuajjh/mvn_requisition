package com.tq.requisition.test.domain;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tq.requisition.domain.share.AggregateRoot;

public class TestIAggregateRoot {
	private AggregateRoot ar;
	
	@Before
	public void init() {
		ar = new AggregateRoot(){};
	}
	
	@Test
	public void idTest() {
		ar.id(UUID.randomUUID());
		Assert.assertNotNull(ar);
		Assert.assertNotNull(ar.id());
		System.out.println(ar.id());
	}
	
	@Test
	public void equalsTest() {
		Assert.assertEquals(true, ar.equals(ar));		
	}
}
