package com.tq.requisition.domain.model.registedAgricultureInfo;

import java.util.UUID;

import com.tq.requisition.domain.share.AggregateRoot;
import com.tq.requisition.exception.DomainException;

/**
 * 在籍农业人口聚合根
 * @author jjh
 * @time 2015-12-18 18:44
 */
public class RegistedAgricultureInfo extends AggregateRoot{
	/**姓名*/
	private String name;
	/**身份证号*/
	private String idNumber;
	/**地址*/
	private String address;
	/**是否拆迁*/
	private boolean isRemove;
	/**是否安置*/
	private boolean isSetting;
	/**是否转户*/
	private boolean isTransfer;
	/**是否社保*/
	private boolean isSocialSecurity;
	/**适用政策*/
	private UUID policyStateId;
	/**人员状态*/
	private UUID userStateId;
	/**使用政策字符串*/
	private String policyStr;
	/**人员状态字符串*/
	private String userStateStr;
	/**是否删除*/
	private boolean del;
	
	public RegistedAgricultureInfo(){
		this.id = UUID.randomUUID();
		this.del = false;
	}
	
	/**
	 * 字段检查
	 * @throws DomainException
	 * 		异常
	 */
	public void check() throws DomainException {
		check(name, "姓名不能为空");
		check(idNumber, "身份证不能为空");
		check(address,"地址不能为空");
		check(policyStr,"适用政策不能为空");
		check(userStateStr,"人员状态不能为空");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean getIsRemove() {
		return isRemove;
	}

	public void setIsRemove(boolean isRemove) {
		this.isRemove = isRemove;
	}

	public boolean getIsSetting() {
		return isSetting;
	}

	public void setIsSetting(boolean isSetting) {
		this.isSetting = isSetting;
	}

	public boolean getIsTransfer() {
		return isTransfer;
	}

	public void setIsTransfer(boolean isTransfer) {
		this.isTransfer = isTransfer;
	}

	public boolean getIsSocialSecurity() {
		return isSocialSecurity;
	}

	public void setIsSocialSecurity(boolean isSocialSecurity) {
		this.isSocialSecurity = isSocialSecurity;
	}

	public UUID getPolicyStateId() {
		return policyStateId;
	}

	public void setPolicyStateId(UUID policyStateId) {
		this.policyStateId = policyStateId;
	}

	public UUID getUserStateId() {
		return userStateId;
	}

	public void setUserStateId(UUID userStateId) {
		this.userStateId = userStateId;
	}

	public String getPolicyStr() {
		return policyStr;
	}

	public void setPolicyStr(String policyStr) {
		this.policyStr = policyStr;
	}

	public String getUserStateStr() {
		return userStateStr;
	}

	public void setUserStateStr(String userStateStr) {
		this.userStateStr = userStateStr;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return this.id;
	}

	public boolean getDel() {
		return del;
	}

	public void setDel(boolean del) {
		this.del = del;
	}
	
	/*public methods*/
	public void modify(RegistedAgricultureInfo entity) {
		this.address = entity.getAddress();
		this.idNumber = entity.getIdNumber();
		this.isRemove = entity.getIsRemove();
		this.isSetting = entity.getIsSetting();
		this.isSocialSecurity = entity.getIsSocialSecurity();
		this.isTransfer = entity.getIsTransfer();
		this.name = entity.getName();
		this.policyStateId = entity.getPolicyStateId();
		this.policyStr = entity.getPolicyStr();
		this.userStateId = entity.getUserStateId();
		this.userStateStr = entity.getUserStateStr();
	}
	
}
