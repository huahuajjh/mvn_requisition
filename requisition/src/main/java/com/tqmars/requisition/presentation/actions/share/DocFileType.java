package com.tqmars.requisition.presentation.actions.share;

import com.tqmars.requisition.presentation.actions.BaseAction;

public class DocFileType extends BaseAction {
	
	private String id = "";
	
	public void setId(String id) {
		if(id == null) return;
		this.id = id;
	}

	public String list(){
		return null;
	}
}
