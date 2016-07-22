package com.tq.requisition.presentation.actions.share;

import java.text.SimpleDateFormat;
import java.util.UUID;

import com.tq.requisition.presentation.actions.BaseAction;
import com.tq.requisition.presentation.dto.project.ProjectDto;
import com.tq.requisition.presentation.serviceContract.proMgt.IProMgtServiceContract;

@SuppressWarnings("serial")
public class ProjectInfo extends BaseAction {
	
	private IProMgtServiceContract proMgtServiceContract;
	public ProjectInfo() {
		this.proMgtServiceContract = getService("proMgtServiceContract",
				IProMgtServiceContract.class);
	}
	
	private String id;
	public void setId(String id) {
		this.id = id;
	}
	
	private ProjectDto pro;

	@Override
	public String execute() throws Exception {
		pro = proMgtServiceContract.getById4Entity(UUID.fromString(id));
		proName = pro.getProName();
		approvalNumber = pro.getApprovalNumber();
		address = pro.getAddress();
		proType = pro.getProTypeStr();
		announceName = pro.getAnnounceName();
		if(pro.getStartDate() != null){
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			startDate = sdf.format(pro.getStartDate());
		}
		return super.execute();
	}
	
	
	private String proName;
	private String approvalNumber;
	private String address;
	private String proType;
	private String startDate;
	private String announceName;
	public String getProName() {
		return proName;
	}
	public String getApprovalNumber() {
		return approvalNumber;
	}
	public String getAddress() {
		return address;
	}
	public String getProType() {
		return proType;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getAnnounceName() {
		return announceName;
	}
}
