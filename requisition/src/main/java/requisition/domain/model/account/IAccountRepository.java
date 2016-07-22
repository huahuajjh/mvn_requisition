package com.tq.requisition.domain.model.account;

import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.domain.model.share.AccountState;
import com.tq.requisition.exception.AccountOperationException;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.utils.PageFormater;

/**
 * 表示账户的聚合根仓储接口
 * @author jjh
 * @time 2015-12-20 23:26
 * 
 * 增加一个检查用户账号是否存在的接口
 * @author Bless
 * @time 2016/3/3 9:51
 */
public interface IAccountRepository extends IRepository<Account>{
	/**
	 * 登陆账户检测
	 * @param account
	 * 		账户名
	 * @param pwd
	 * 		账户密码
	 * @return boolean
	 * 		返回一个boolean值，表明给定的用户名和密码是否存在对应的账户
	 */
	boolean login(String account,String pwd);
	
	/**
	 * 登陆账户检测
	 * @param account
	 * 		账户名
	 * @param pwd
	 * 		账户密码
	 * @return Account
	 */
	Account login4session(String account,String pwd);
	
	/**
	 * 根据id获取用户实体
	 * @param id
	 * 		用户id
	 * @return
	 */
	Account getById(UUID id);
	
	/**
	 * 创建新账户
	 * @param account
	 * 		账户实例
	 * @return TODO
	 */
	Account createAccount(Account account) throws AccountOperationException;
	
	/**
	 * 创建账户
	 * @param account
	 * 		账户名
	 * @param pwd
	 * 		密码
	 * @param state
	 * 		账户状态
	 * @param name
	 * 		姓名
	 */
	public void createAccount(String account,String pwd,AccountState state,String... name);
	
	/**
	 * 修改密码
	 * @param id
	 * 		账户id
	 * @param oldPwd
	 * 		旧密码
	 * @param newPwd
	 * 		新密码
	 */
	public void changePwd(UUID id,String oldPwd,String newPwd) throws DomainException;
	
	/**
	 * 禁用账户
	 * @param id
	 * 		账户id
	 */
	public void disableAccount(UUID id);
	
	/**
	 * 更新账户信息
	 * @param account
	 * 		待更新的账户
	 */
	public void updateAccount(Account account) throws AccountOperationException ;
	
	/**
	 * 锁账户
	 * @param id
	 * 		账户id
	 */
	public void lockAccount(UUID id);

	/**
	 * 启用账户
	 * @param id
	 * 		账户id
	 */
	public void enableAccount(UUID id);
	
	/**
	 * 根据用户id检测是否存在该用户
	 * @param id
	 * 		用户id
	 * @return boolean
	 * 		如果存在该用户返回true，否则返回false
	 */
	public boolean existsById(UUID id);
	
	/**
	 * 解除被删除部门及组织
	 * @param orgid
	 * 		被删除组织id
	 */
	public void updateOrg(UUID orgid);
	
	/**
	 * 更新账户仓储中被删除的所有部门id为null
	 * @param deptid
	 * 		被删除的部门id
	 */
	public void updateDept(UUID deptid);	

	/**
	 * 指定角色被删除时，更新所有账号仓储的角色信息
	 * @param rid
	 * 		被删除的角色id
	 */
	public void updateRole(UUID rid);
	
	/**
	 * 分页获取账户列表
	 * @param userName
	 * 		用户名
	 * @param name
	 * 		姓名
	 * @param orgId
	 * 		单位id
	 * @param deptId
	 * 		部门id
	 * @param pageIndex
	 * 		起始页
	 * @param pageNum
	 * 		页码
	 * @return List<Account>
	 * 		账户集合
	 */
	PageFormater queryByPage(String userName, String name, UUID orgId, UUID deptId, int pageIndex, int pageNum);
	
	/**
	 * 根据用户账号检测是否存在该用户
	 * @param account
	 * 		用户账号
	 * @return boolean
	 * 		如果存在该用户返回true，否则返回false
	 */
	public boolean existsByAccount(String account);
}
