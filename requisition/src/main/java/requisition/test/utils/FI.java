package com.tq.requisition.test.utils;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;

public class FI {
	private String headName;
	private Date birthday;
	public FI(String headName, Date birthday) {
		super();
		this.headName = headName;
		this.birthday = birthday;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "FI [headName=" + headName + ", birthday=" + birthday + "]";
	}
	
	@Test
	public void getUUIDs(){
		for (int i = 0; i < 17; i++) {
			System.out.println(UUID.randomUUID().toString());
		}
	}
}
