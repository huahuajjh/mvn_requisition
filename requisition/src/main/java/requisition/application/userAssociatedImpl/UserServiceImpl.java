package com.tq.requisition.application.userAssociatedImpl;

import java.util.UUID;

import com.tq.requisition.autoMapper.AccountSafeMapper;
import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.domain.model.account.IAccountRepository;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.utils.ConfigFileUtil;
import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.infrastructure.utils.Serialization;
import com.tq.requisition.presentation.dto.sysMgt.AccountSafeDto;
import com.tq.requisition.presentation.serviceContract.userAssociated.IUserService;

/**
 * 用户相关操作接口实现
 * @author jjh
 * @time 2015-12-21 22:01
 */
public class UserServiceImpl implements IUserService{
	/*private fields*/
	private IAccountRepository accountRepository;
	
	/*constructors*/
	public UserServiceImpl(IAccountRepository _accountRepository)
	{
		accountRepository = _accountRepository;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean login(String account, String pwd) {
		boolean state = accountRepository.login(account, pwd);
		return state;
	}
	
	/**
	 * {@inheritDoc} 
	 * @throws DomainException
	 * 操作异常实例 
	 */
	@Override
	public String changePwd(UUID uId, String oldPwd, String newPwd) {
		try {
			accountRepository.context().beginTransaction();
			accountRepository.changePwd(uId, oldPwd, newPwd);
			accountRepository.context().commit();
			return Serialization.toJson(Formater.obtain("修改密码成功", null, Formater.OperationResult.SUCCESS)); 
		} catch (DomainException e) {
			return Serialization.toJson(Formater.obtain("修改密码失败-"+e.getMessage(), null, Formater.OperationResult.FAIL));
		}
	}
	
	@Override
	public AccountSafeDto login4session(String account, String pwd) {
		//admin
		if(isAdmin(account, pwd)){
			AccountSafeDto dto = new AccountSafeDto();
			dto.setAccount(account);
			dto.setName(account);
			dto.setId(UUID.fromString(ConfigFileUtil.readByKey("adminId")));
			return dto;
		}
		Account acc = accountRepository.login4session(account, pwd);
		if(null == acc){return null;}
		return AccountSafeMapper.toDto(acc);
	}	
	
	@Override
	public AccountSafeDto getUserById(UUID id) {
		Account acc = accountRepository.getById(id);
		if(acc == null) return null;
		return AccountSafeMapper.toDto(acc);
	}
	
	private boolean isAdmin(String acc,String pwd) {
		String admin = ConfigFileUtil.readByKey("acc");
		String adminPwd = ConfigFileUtil.readByKey("pwd");
		if(acc.equals(admin) && pwd.equals(adminPwd))
		{
			return true;
		}
		return false;
	}
}
