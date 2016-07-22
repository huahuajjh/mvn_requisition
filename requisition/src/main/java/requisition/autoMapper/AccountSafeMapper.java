package com.tq.requisition.autoMapper;

import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.presentation.dto.sysMgt.AccountSafeDto;

public class AccountSafeMapper {
	public static AccountSafeDto toDto(Account model) {
		AccountSafeDto dto = new AccountSafeDto();
		dto.setAccount(model.getAccount());
		dto.setDeptId(model.getDeptId());
		dto.setName(model.getName());
		dto.setId(model.getId());
		dto.setRoleId(model.getRoleId());
		dto.setOrgId(model.getOrgId());
		return dto;
	}
	

}
