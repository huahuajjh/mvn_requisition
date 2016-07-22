package com.tq.requisition.presentation.dto.sysMgt;

import java.util.UUID;

import com.tq.requisition.domain.model.share.AccountState;

public class AccountSafeDto {
	/*private fields*/
	/**账户id*/
	private UUID id;
	/**账户名*/
	private String account;
	/**姓名*/
	private String name;
	/**账户所属部门id*/
	private UUID deptId;
	/**账号所属部门名称*/
	private String depName;
	/**账户所属组织id*/
	private UUID orgId;
	/**账号所属组织的名称*/
	private String orgName;
	/**账户角色id*/
	private UUID roleId;
	/**账户角色名称*/
	private String roleName;
	/**账户状态*/
	private AccountState state;
	/**账户状态字符串*/
	private String stateStr;
	
	/*construtors*/
	public AccountSafeDto(){}
	public AccountSafeDto(UUID id, String account, String name, UUID deptId,
			String depName, UUID orgId, String orgName, UUID roleId,
			String roleName,AccountState state) {
		super();
		this.id = id;
		this.account = account;
		this.name = name;
		this.deptId = deptId;
		this.depName = depName;
		this.orgId = orgId;
		this.orgName = orgName;
		this.roleId = roleId;
		this.roleName = roleName;
		this.state = state;
	}
	
	/*getters and setters*/
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UUID getDeptId() {
		return deptId;
	}
	public void setDeptId(UUID deptId) {
		this.deptId = deptId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public UUID getOrgId() {
		return orgId;
	}
	public void setOrgId(UUID orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public UUID getRoleId() {
		return roleId;
	}
	public void setRoleId(UUID roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public AccountState getState() {
		return state;
	}
	public void setState(AccountState state) {
		this.state = state;
	}
	public String getStateStr() {
		return stateStr;
	}
	public void setStateStr(String stateStr) {
		this.stateStr = stateStr;
	}
	
}
