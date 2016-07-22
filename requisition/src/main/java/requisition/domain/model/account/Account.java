package com.tq.requisition.domain.model.account;

import java.util.UUID;

import com.tq.requisition.domain.model.share.AccountState;
import com.tq.requisition.domain.share.AggregateRoot;
import com.tq.requisition.exception.AccountOperationException;
import com.tq.requisition.exception.ChangePwdException;

/**
 * 账户聚合根
 * @author jjh
 * @time 2015-12-18 16:46
 */
public class Account extends AggregateRoot{
	/*private fields*/
	/**用户名*/
	private String account;
	/**密码*/
	private String pwd;
	/**姓名*/
	private String name;
	/**状态*/
	private Integer state;
	/**所属部门id*/
	private UUID deptId;
	/**所属组织id*/
	private UUID orgId;
	/**角色id*/
	private UUID roleId;
	
	/*override toString*/
	@Override
	public String toString() {
		return "Account [account=" + account + ", pwd=" + pwd + ", name="
				+ name + ", state=" + state + ", deptId=" + deptId + ", orgId="
				+ orgId + ", id=" + id + "]";
	}

	/*constructors*/
	public Account() {}
	public Account(UUID id,String account, String pwd, String name, Integer state,
			UUID deptId, UUID orgId,UUID _roleId) {
		super();
		this.account = account;
		this.pwd = "1234567";
		this.name = name;
		this.state = AccountState.ENABLE.toValue();
		this.deptId = deptId;
		this.orgId = orgId;
		this.id = UUID.randomUUID();
		this.roleId = _roleId;		
	}

	/*getters and setters*/
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
	public Integer getState() {
		return state;
	}
	public void setState(Integer _state) {
		this.state = _state;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
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

	/*public methods*/
	/**
	 * 锁账户
	 */
	public void lockAccount() {
		this.state = AccountState.LOCKED.toValue();
	}
	
	/**
	 * 禁用账户
	 */
	public void disableAccount() {
		this.state = AccountState.DISABLE.toValue();
	}
	
	/**
	 * 启用账户
	 */
	public void enableAccount() {
		this.state = AccountState.ENABLE.toValue();
	}

	/**
	 * 修改密码
	 * @param oldPwd
	 * 		旧密码
	 * @param newPwd
	 * 		新密码
	 * @throws ChangePwdException
	 * 		抛出异常类型
	 */
	public void changePwd(String oldPwd,String newPwd) throws ChangePwdException {
		if(!oldPwd.equals(this.pwd))
		{
			throw new ChangePwdException("旧密码错误");
		}		
		if(oldPwd.equals(newPwd))
		{
			throw new ChangePwdException("旧密码不能与新密码相同");
		}
		if(newPwd.length() <= 6)
		{
			throw new ChangePwdException("密码长度不能小于6");
		}
		this.pwd = newPwd;
	}
	
	/**
	 * 创建账户
	 * @throws AccountOperationException
	 * 		账户操作异常 
	 */
	public void accountCheck() throws AccountOperationException {
		if(pwd.length() <= 2)
		{
			throw new AccountOperationException("密码长度不能小于3");
		}
		if(account.length() <= 2)
		{
			throw new AccountOperationException("账户名长度不能小于3");
		}
	}
	
	public static Account obtain(String _account,String _pwd,String _name,UUID _deptId,UUID _orgId) {
		return new Account(UUID.randomUUID(), _account, _pwd, _name, AccountState.ENABLE.toValue(), _deptId, _orgId, null);
	}
	
	public void modify(Account temp) throws AccountOperationException {
		this.account = temp.getAccount();
		this.deptId = temp.getDeptId();
		this.orgId = temp.getOrgId();
		this.name = temp.getName();
		this.state = temp.getState();
		this.roleId = temp.roleId;
		accountCheck();
	}
	
	/**
	 * 重置密码，默认1234567
	 */
	public void resetPwd() {
		this.pwd = "1234567";
	}
}
