package com.tq.requisition.infrastructure.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.tq.requisition.infrastructure.log.LoggerFactory;

/**
 * 读取properties配置文件
 * @author jjh
 * @time 2015-01-09 21:54
 *
 */
public final class ConfigFileUtil {
	private static Properties p;
	
	static{		
		p = new Properties();
		InputStream is = null;
		try {
			is = ConfigFileUtil.class.getResourceAsStream("/admin.properties");
			p.load(is);
		} catch (IOException e) {
			LoggerFactory.logger().error(ConfigFileUtil.class.getName(), "readByKey(String key)", e.getMessage());
		}
		finally{
			try {
				is.close();
			} catch (IOException e) {
				LoggerFactory.logger().error(ConfigFileUtil.class.getName(), "readByKey(String key)", e.getMessage());
			}
		}
	}
	
	/**
	 * 读取指定key的value值
	 * @param key
	 * 		指定的key值
	 * @return String
	 * 		value值
	 */
	public static String readByKey(String key) {
		return p.getProperty(key);
	}
	
	/**
	 * 插入键值对
	 * @param key 		
	 * @param value
	 */
	public static void setValue(String key,String value) {
	}
	
}
