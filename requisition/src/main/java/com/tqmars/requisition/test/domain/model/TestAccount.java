package com.tqmars.requisition.test.domain.model;

import org.junit.Before;
import org.junit.Test;

import com.tqmars.requisition.domain.model.account.Account;
import com.tqmars.requisition.domain.model.share.AccountState;
import com.tqmars.requisition.exception.ChangePwdException;

import junit.framework.Assert;

public class TestAccount {
	private Account account;
	@Before
	public void init() {
	}
	
	@Test
	public void changePwd() {
		try {
			account.changePwd("huahuajjh3", "1231123");
			Assert.assertEquals("1231123", account.getPwd());
		} catch (ChangePwdException e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void changeState() {
		account.disableAccount();
		Assert.assertEquals(AccountState.DISABLE, account.getState());
		
		account.enableAccount();
		Assert.assertEquals(AccountState.ENABLE, account.getState());
		
		account.lockAccount();
		Assert.assertEquals(AccountState.LOCKED, account.getState());
	}
}
