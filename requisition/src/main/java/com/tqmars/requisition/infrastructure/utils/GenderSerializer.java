package com.tqmars.requisition.infrastructure.utils;

import java.lang.reflect.Type;

import com.tqmars.requisition.domain.model.share.Gender;

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
