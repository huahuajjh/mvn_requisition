package com.tq.requisition.test.utils;

import java.util.UUID;

import org.junit.Test;

public class TestFormatter {
	@Test
	public void stringFormater() {		
		//System.out.println(String.format("爱上当海盗%s所属%s", UUID.randomUUID(),UUID.randomUUID()));
//		System.out.println(new Date().toLocaleString().replace('-', '/'));
		double f = 10.12442511022425d;
		System.out.println(f);
	}
	
	@Test
	public void genUUID() {
		for (int i = 0; i < 10; i++) {
			System.out.println(UUID.randomUUID());
		}
	}
}
