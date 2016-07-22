package com.tq.requisition.presentation.dto.transferMgt;

import java.util.UUID;

/**
 * 新增转户信息查询dto
 * @author jjh
 * @time 2015-01-04 15:53
 *
 */
public class TransferInfo4AddDto {
	private String name;
	private String proName;
	private String householdStr;
	private String address;
	private String idNumber;
	private UUID proId;
	private UUID fmlItemId;
	
	/*getters and setters*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getHouseholdStr() {
		return householdStr;
	}
	public void setHouseholdStr(String householdStr) {
		this.householdStr = householdStr;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public UUID getProId() {
		return proId;
	}
	public void setProId(UUID proId) {
		this.proId = proId;
	}
	public UUID getFmlItemId() {
		return fmlItemId;
	}
	public void setFmlItemId(UUID fmlItemId) {
		this.fmlItemId = fmlItemId;
	}
	
	/*constructors*/
	public TransferInfo4AddDto(){}
	public TransferInfo4AddDto(String name, String proName,String householdStr, String address, String idNumber, UUID proId,
			UUID fmlItemId) {
		super();
		this.name = name;
		this.proName = proName;
		this.householdStr = householdStr;
		this.address = address;
		this.idNumber = idNumber;
		this.proId = proId;
		this.fmlItemId = fmlItemId;
	}	
	
}
