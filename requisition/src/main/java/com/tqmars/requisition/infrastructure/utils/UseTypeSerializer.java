package com.tqmars.requisition.infrastructure.utils;

import java.lang.reflect.Type;

import com.tqmars.requisition.domain.model.share.UseType;

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
