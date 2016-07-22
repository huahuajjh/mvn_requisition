package com.tq.requisition.autoMapper;

import java.util.ArrayList;
import java.util.List;

import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.presentation.dto.sysMgt.AccountDto;

/**
 * accout聚合根与dto转换类,此类不能被继承
 * @author jjh
 * @time 2015-12-23 17:05
 */
public final class AccountMapper {	
	/**
	 * account dto转account领域实体
	 * @param accountDto
	 * 		待转换的accountDto
	 * @return Account
	 * 		返回Account领域实体
	 */
	public static Account toModel(AccountDto accountDto) {
		return new Account(accountDto.getId()//
				, accountDto.getAccount()//
				, accountDto.getPwd()//
				, accountDto.getName()//
				, accountDto.getState()//
				, accountDto.getDeptId()//
				, accountDto.getOrgId()//
				,accountDto.getRoleId());
	}
	
	/**
	 * account领域实体转account dto
	 * @param account
	 * 		待转换的account
	 * @return AccountDto
	 * 		返回AccountDto实体
	 */
	public static AccountDto toDto(Account account) {
		return new AccountDto(account.getId()//
				, account.getAccount()//
				, account.getPwd()//
				, account.getName()//
				, account.getDeptId()//
				, account.getOrgId()//
				, account.getRoleId()//
				, account.getState());
	}
	
	public static List<Account> toModelList(List<AccountDto> dtoList) {
		if(dtoList==null)
		{
			throw new NullPointerException("待转换的dto集合为null");
		}
		List<Account> modelList = new ArrayList<Account>();
		for (AccountDto accountDto : dtoList) {
			modelList.add(toModel(accountDto));
		}
		return modelList;
	}
	
	public static List<AccountDto> toDtoList(List<Account> modelList) {
		if(modelList==null)
		{
			throw new NullPointerException("待转换的model集合为null");
		}
		List<AccountDto> dtoList = new ArrayList<>();
		for (Account account : modelList) {
			dtoList.add(toDto(account));
		}
		return dtoList;
	}
	
}
