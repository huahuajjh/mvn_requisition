package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.model.transferHouseholdInfo.TransferHouseholdInfo;
import com.tq.requisition.presentation.dto.transferMgt.NewAndEditTransferHouseholdInfoDto;

public class NewTransferInfoMapper {
	public static TransferHouseholdInfo toModel(NewAndEditTransferHouseholdInfoDto dto) {
		TransferHouseholdInfo t = new TransferHouseholdInfo();
		t.setId(UUID.randomUUID());
		t.setAddress(dto.getAddress());
		t.setCommunityId(dto.getCommunityId());
		t.setStreetId(dto.getStreetId());
		t.setDel(false);
		t.setFmlItemId(dto.getFmlItemId());
		t.setHouseHoldTypeId(dto.getHouseHoldTypeId());
		t.setHouseHoldTypeStr(dto.getHouseHoldTypeStr());
		t.setTransferDate(dto.getTransferDate());
		t.setOprUserId(dto.getOprUserId());
		t.setOprDate(new Date());
		t.setGroupId(dto.getGroupId());
		return t;
	}
	
	public static List<TransferHouseholdInfo> toModelList(List<NewAndEditTransferHouseholdInfoDto> dtos) {
		List<TransferHouseholdInfo> models = new ArrayList<TransferHouseholdInfo>();
		for (NewAndEditTransferHouseholdInfoDto newAndEditTransferHouseholdInfoDto : dtos) {
			models.add(toModel(newAndEditTransferHouseholdInfoDto));
		}
		return models;
	}
}
