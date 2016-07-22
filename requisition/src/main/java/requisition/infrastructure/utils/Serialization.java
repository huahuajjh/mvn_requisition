package com.tq.requisition.infrastructure.utils;


import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tq.requisition.domain.model.share.Gender;
import com.tq.requisition.domain.model.share.TicketState;
import com.tq.requisition.domain.model.share.UseType;


/**
 * 该类提供序列化和反序列化服务
 * @author JJh
 * @time 2015-06-05 12:05
 * @lastmodify 2015-06-05 12:05
 * @version 1.0
 */
public final class Serialization {
	private static GsonBuilder builder = new GsonBuilder();
	private static Gson gson;
	
	static {
		builder.registerTypeAdapter(UseType.class, new UseTypeSerializer());
		builder.registerTypeAdapter(TicketState.class, new TicketStateSerializer());
		builder.registerTypeAdapter(Gender.class, new GenderSerializer());
		builder.setDateFormat("yyyy/MM/dd");
		gson = builder.create();
	}
	
	/**
	 * 将javabean序列化成json字符串
	 * @param obj - 待序列化的java对象，该对象符合javabean规范
	 * @return String - json字符串 
	 */
	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}
	
	/**
	 * 将json字符串序列化成java对象
	 * @param jsonString - json字符串
	 * @return T - 类型参数,序列化之后的目标类型参数
	 */
	public static <T> T toObject(String jsonString,Class<T> clazz) {
		return gson.fromJson(jsonString, clazz);		
	}
	
	/**
	 * 将json数组对象的字符串转换为java集合对象
	 * @param jsonString - json数组字符串
	 * @param clazz - java集合对象的Class
	 * @return T - 集合类型参数 
	 */
	public static <T> List<T> toList(String jsonString,T clazz) {
		Type type = new TypeToken<List<T>>(){}.getType();
		return gson.fromJson(jsonString, type);
	}
	
}
