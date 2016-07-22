package com.tq.requisition.infrastructure.utils;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tq.requisition.domain.model.share.Gender;

public class GenderSerializer implements JsonSerializer<Gender>,JsonDeserializer<Gender> {

	@Override
	public Gender deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		return Gender.FEMALE.obtainByInt(arg0.getAsShort()) ;
	}

	@Override
	public JsonElement serialize(Gender arg0, Type arg1,
			JsonSerializationContext arg2) {
		return new JsonPrimitive(arg0.name());
	}

}
