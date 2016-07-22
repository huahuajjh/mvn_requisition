package com.tq.requisition.infrastructure.utils;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.tq.requisition.domain.model.share.UseType;

public class UseTypeSerializer  implements JsonSerializer<UseType>,JsonDeserializer<UseType> {

	@Override
	public UseType deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		return UseType.CASH.obtainByInt(arg0.getAsShort()) ;
	}

	@Override
	public JsonElement serialize(UseType arg0, Type arg1,
			JsonSerializationContext arg2) {
		return new JsonPrimitive(arg0.toStr());
	}

}
