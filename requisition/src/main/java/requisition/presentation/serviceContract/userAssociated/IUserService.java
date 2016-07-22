package com.tq.requisition.presentation.serviceContract.userAssociated;

import java.util.UUID;

import com.tq.requisition.presentation.dto.sysMgt.AccountSafeDto;


/**
 * 用户相关操作契约接口
 * @author jjh
 * @time 2015-12-21 21:50
 */
public interface IUserService {
	/**
	 * 用户登陆
	 * @param account
	 * 		登陆账户
	 * @param pwd
	 * 		用户密码
	 * @return boolean
	 * 		登陆是否成功标志
	 */
	boolean login(String account,String pwd);
	
	/**
	 * 登陆
	 * @param account
	 * 		账户
	 * @param pwd
	 * 		密码
	 * @return
	 * 		登陆实体
	 */
	AccountSafeDto login4session(String account,String pwd);
	
	/**
	 * 根据id获取用户对象
	 * @param id
	 * 		id
	 * @return
	 * 		AccountDto
	 */
	AccountSafeDto getUserById(UUID id);
		
	/**
	 * 修改密码
	 * @param uId
	 * 		待修改密码的账号
	 * @param DomainException
	 * 		操作异常实例
	 * @param oldPwd
	 * 		旧密码
	 * @param newPwd
	 * 		新密码
	 */
	String changePwd(UUID uId,String oldPwd,String newPwd);
	
}
