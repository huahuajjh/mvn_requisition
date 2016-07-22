package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.address.Address;
import com.tq.requisition.presentation.dto.share.AddressDto;

/**
 * 地址dto與model轉換
 * @author jjh
 * @time 2015-12-27 23:12
 */
public final class AddressMapper {
	public static AddressDto toDto(Address address) {
		return AddressDto.obtain(address.getTitle(), address.getId(),address.getPid());
	}
	
	public static List<AddressDto> toDtoList(List<Address> addresses) {
		List<AddressDto> dtos = new ArrayList<AddressDto>();
		for (Address address : addresses) {
			dtos.add(toDto(address));
		}
		return dtos;
	}

	public static Address toModel(AddressDto dto) {
		if(dto.getName().equals("") || dto.getName() == null)
		{
			throw new NullPointerException("地址名称为null");
		}
		return Address.obtain(dto.getName(), dto.getPid());
	}
	
}
