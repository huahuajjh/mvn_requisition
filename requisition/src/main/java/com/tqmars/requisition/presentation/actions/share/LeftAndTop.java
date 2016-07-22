package com.tqmars.requisition.presentation.actions.share;

import com.tqmars.requisition.presentation.actions.BaseAction;
import com.tqmars.requisition.presentation.dto.sysMgt.AccountSafeDto;

public class LeftAndTop extends BaseAction {

	private  AccountSafeDto userInfo;
	
	@Override
	public String execute() throws Exception {
		userInfo = user();
		return SUCCESS;
	}

	public AccountSafeDto getUserInfo() {
		return userInfo;
	}
}
