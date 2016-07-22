package com.tq.requisition.presentation.actions.housePurchaseMansgement;

import java.io.IOException;
import java.util.UUID;

import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.serviceContract.proMgt.IProMgtServiceContract;

@SuppressWarnings("serial")
public class HptProjectMoneyUnit extends BaseAction {
	
	private String proId;
	public void setProId(String proId) {
		this.proId = proId;
	}

	private String moneyUnit;
	private String otherMoneyUnit;
	public void setMoneyUnit(String moneyUnit) {
		this.moneyUnit = moneyUnit;
	}
	public void setOtherMoneyUnit(String otherMoneyUnit) {
		this.otherMoneyUnit = otherMoneyUnit;
	}

	private IProMgtServiceContract proMgtServiceContract;
	
	public HptProjectMoneyUnit(){
		this.proMgtServiceContract = getService("proMgtServiceContract", IProMgtServiceContract.class);
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
	public String get() throws IOException{
		UUID id = UUID.fromString(proId);
		String state = this.proMgtServiceContract.getProById(id);
		response().getWriter().write(state);
		return null;
	}
	
	public String edit() throws IOException {
		UUID id = UUID.fromString(proId);
		String state = this.proMgtServiceContract.editMoneyUnit(id, moneyUnit, otherMoneyUnit);
		response().getWriter().write(state);
		return null;
	}
}
