package com.tq.requisition.presentation.serviceContract.sysManagement;

import java.util.UUID;

import com.tq.requisition.presentation.dto.sysMgt.AccountDto;

/**
 * 账户相关操作契约接口
 * 
 * @author jjh
 * @time 2015-12-23 15:37
 */
public interface IAccountService {
	/**
	 * 创建账户接口-
	 * 
	 * @param accountDto
	 * 		账户dto
	 * @return TODO
	 */
	String createAccount(AccountDto accountDto);
	
	/**
	 * 检测账户是否存在
	 * 
	 * @param account
	 *            账户名
	 * @return boolean 返回一个boolean值，该指表明指定账户名是否存在
	 */
	boolean checkAccountExists(String account);

	/**
	 * 禁用账户接口-
	 * 
	 * @param id
	 * 		账户id
	 * @return TODO
	 */
	String disableAccount(UUID id);
	
	/**
	 * 重置用户密码为 1234567
	 * 
	 * @param uId
	 *            用户标识
	 * @return
	 */
	String resetAccountPassword(UUID uId);
	
	/**
	 * 模糊查询账号数据，（注意：数据里面不能把密码带出来） 
	 * Json：[AccountDto...]
	 * 
	 * @param userName
	 *            账号（注意：空 表示获取所有人员）
	 * @param name
	 *            姓名（注意：空 表示获取所有人员）
	 * @param orgId
	 *            所属组织（注意：null 表示获取所有组织人员）
	 * @param deptId
	 *            所属部门（主要：null 表示获取所有部门人员）
	 * @param pageIndex
	 *            页码（默认从1开始）
	 * @param pageNum
	 *            每页显示的数据 默认从1 开始
	 * @return
	 */
	String getAccountList(String userName, String name, UUID orgId,
			UUID deptId, int pageIndex, int pageNum);
	
	/**
	 * 禁用账户接口
	 * 
	 * @param id
	 * 		账户id
	 * @return TODO
	 */
	String enableAccount(UUID id);

	/**
	 * 更新账户信息
	 * @param dto
	 * 		账户信息dto
	 * @return
	 */
	String updateAccount(AccountDto dto);
		
	/**
	 * 删除账户
	 * @param uid
	 * 		待删除账户的id
	 * @return
	 */
	String removeAccount(UUID uid);
}
