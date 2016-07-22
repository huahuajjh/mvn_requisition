package com.tq.requisition.presentation.dto.sysMgt;

import java.util.UUID;

/**
 * 用户账户dto
 * @author jjh
 * @time 2015-12-21 16:34
 *
 */
public class AccountDto {
	/*private fields*/
	/**账户id*/
	private UUID id;
	/**账户名*/
	private String account;
	/**账户密码*/
	private String pwd;
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
	private Integer state;
	
	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	public UUID getOrgId() {
		return orgId;
	}
	public void setOrgId(UUID orgId) {
		this.orgId = orgId;
	}
	public UUID getRoleId() {
		return roleId;
	}
	public void setRoleId(UUID roleId) {
		this.roleId = roleId;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	/*constructors*/
	public AccountDto(UUID id, String account, String pwd, String name,
			UUID deptId, UUID orgId, UUID roleId, Integer state) {
		super();
		this.id = id;
		this.account = account;
		this.pwd = pwd;
		this.name = name;
		this.deptId = deptId;
		this.orgId = orgId;
		this.roleId = roleId;
		this.state = state;
	}
	
	public AccountDto(UUID id, String account, String pwd, String name,
			UUID deptId, String depName, UUID orgId, String orgName,
			UUID roleId, String roleName, Integer state) {
		super();
		this.id = id;
		this.account = account;
		this.pwd = pwd;
		this.name = name;
		this.deptId = deptId;
		this.depName = depName;
		this.orgId = orgId;
		this.orgName = orgName;
		this.roleId = roleId;
		this.roleName = roleName;
		this.state = state;
	}
	
	@Override
	public String toString() {
		return "AccountDto [id=" + id + ", account=" + account + ", pwd=" + pwd
				+ ", name=" + name + ", deptId=" + deptId + ", depName="
				+ depName + ", orgId=" + orgId + ", orgName=" + orgName
				+ ", roleId=" + roleId + ", roleName=" + roleName + ", state="
				+ state + "]";
	}

	public AccountDto(){}
}
