package com.tq.requisition.test.utils;


import java.io.FileNotFoundException;
import java.io.InputStream;

import org.junit.Test;

import com.tq.requisition.infrastructure.utils.ConfigFileUtil;

public class TestConfigUtil {
	@Test
	public void read(){
		System.out.println(ConfigFileUtil.readByKey("acc"));
	}
	
	@Test
	public void write() throws FileNotFoundException {
//		FileInputStream fis = new FileInputStream("");
		InputStream is = this.getClass().getResourceAsStream("/admin.properties");
		System.out.println(is);
	}
	
	@Test
	public void log() {
//		LoggerFactory.logger().error("aaa");
		String[] arr = ConfigFileUtil.readByKey("proCategory").split(",");
		System.out.println(arr[1]);
	}
}
