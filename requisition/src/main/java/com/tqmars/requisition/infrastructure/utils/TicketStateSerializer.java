package com.tqmars.requisition.infrastructure.utils;

import java.lang.reflect.Type;

import com.tqmars.requisition.domain.model.share.TicketState;

public class TicketStateSerializer  implements JsonSerializer<TicketState>,JsonDeserializer<TicketState> {

	@Override
	public TicketState deserialize(JsonElement arg0, Type arg1,
			JsonDeserializationContext arg2) throws JsonParseException {
		return TicketState.CASHED.obtainByInt(arg0.getAsShort()) ;
	}

	@Override
	public JsonElement serialize(TicketState arg0, Type arg1,
			JsonSerializationContext arg2) {
		return new JsonPrimitive(arg0.name());
	}

}
