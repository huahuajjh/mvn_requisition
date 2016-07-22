package com.tq.requisition.infrastructure.Repository.account;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.domain.model.account.IAccountRepository;
import com.tq.requisition.domain.model.share.AccountState;
import com.tq.requisition.exception.AccountOperationException;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.exception.SpecifiedObjectDoesNotExistsException;
import com.tq.requisition.infrastructure.Repository.HbRepository;
import com.tq.requisition.infrastructure.Specifications.account.UserCountSpecification;
import com.tq.requisition.infrastructure.Specifications.account.UserExistsByIdSpecification;
import com.tq.requisition.infrastructure.Specifications.account.UserNameExistsSpecification;
import com.tq.requisition.infrastructure.Specifications.account.UserQueryByPageSpecification;
import com.tq.requisition.infrastructure.Specifications.account.UserUpdateDeptSpecification;
import com.tq.requisition.infrastructure.Specifications.account.UserUpdateOrgSpecification;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.infrastructure.utils.PageHelper;
import com.tq.requisition.presentation.dto.sysMgt.AccountSafeDto;

/**
 * 表示账户的聚合根仓储实现
 * @author jjh
 * @time 2015-12-20 23:30
 */
public class AccountRepository extends HbRepository<Account> implements IAccountRepository{

	public AccountRepository(IRepositoryContext context) {
		super(context);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean login(String account, String pwd) {
		final String _acc = account;
		final String _pwd = pwd;
		boolean r = doExists(new SpecificationExt<Account>(Account.class) {
			@Override
			public String getAbsHql() {
				return "select count(1) from Account acc where acc.account=? and acc.pwd=? and acc.state=?";
			}

			@Override
			public String getAbsSql() {
				return "select count(1) from tb_account where account=? and pwd=? and state =?";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{_acc,_pwd,AccountState.ENABLE.toValue()};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.HQL;
			}			
		});
		
		return r;
		
	}

	@Override
	public void createAccount(String account, String pwd, AccountState state,
			String... name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changePwd(UUID id, String oldPwd, String newPwd) throws DomainException {
		Account account = getByKey(Account.class, id);
		if(account != null)
		{
			account.changePwd(oldPwd, newPwd);
			update(account);
			return;
		}
		throw new SpecifiedObjectDoesNotExistsException("未查询到要修改的用户信息");
	}

	@Override
	public void disableAccount(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lockAccount(UUID id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enableAccount(UUID id) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Account createAccount(Account account) throws AccountOperationException{
		/*检测账户创建规约，不符合规约条件的，抛出AccountOperationException异常*/
		account.accountCheck();
		boolean isExists = exists(new UserNameExistsSpecification(Account.class, account));
		if(isExists){
			throw new AccountOperationException("账户名["+account.getAccount()+"]已经存在");
		}
		Account acc = Account.obtain(account.getAccount(), account.getPwd(), account.getName(), account.getDeptId(), account.getOrgId());
		acc.setRoleId(account.getRoleId());
		/*将新增实体标记为新增状态*/
		add(acc);
		return acc;
	}

	@Override
	public boolean existsById(UUID id) {
		boolean r = exists(new UserExistsByIdSpecification(Account.class, id));
		if(r)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void updateOrg(UUID orgid) {
		if(orgid == null)
		{
			throw new NullPointerException("组织id为null");
		}
		executeUpdate(new UserUpdateOrgSpecification(Account.class, orgid));				
	}

	/**
	 * 更新账户仓储中被删除的所有部门id为null
	 * @param deptid
	 * 		被删除的部门id
	 */
	@Override
	public void updateDept(UUID deptid)
	{
		if(deptid==null)
		{
			throw new NullPointerException("组织id为null");
		}
		executeUpdate(new UserUpdateDeptSpecification(Account.class, deptid));		
	}

	@Override
	public void updateAccount(Account account) throws AccountOperationException {
		if(account == null)
		{
			throw new NullPointerException("账户为null");
		}
		account.accountCheck();
		Account acc = getByKey(Account.class, account.getId());
		acc.modify(account);
		update(acc);
	}
	
	@Override
	public void updateRole(final UUID rid) {
		executeUpdate(new SpecificationExt<Account>(Account.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "update tb_account set role_id=null where role_id=?";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{rid};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
	}

	@Override
	public Account login4session(final String account,final String pwd) {
		List<Account> list = getAll(new SpecificationExt<Account>(Account.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_account where account=? and pwd=?";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{account,pwd};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		if(null == list || list.size()==0)
		{
			throw new NullPointerException("用户名或者密码错误");
		}
		return list.get(0);
	}
	
	@Override
	public Account getById(UUID id) {
		Account acc = getByKey(Account.class, id);
		return acc;
	}
	
	@Override
	public PageFormater queryByPage(String userName, String name, UUID orgId,
			UUID deptId, int pageIndex, int pageNum) {
		int count = getTotalCount(new UserCountSpecification(Account.class, userName, name, orgId, deptId));
		if(count==0){
			return PageFormater.obtain(null, 0);
		}
		List<Object[]> objs = getAllBySqlJoin(//
				new UserQueryByPageSpecification(Account.class, userName, name, orgId, deptId), //
				PageHelper.getPageIndex(pageIndex, pageNum), //
				pageNum);
		
		List<AccountSafeDto> dtos = new ArrayList<AccountSafeDto>();
		for (Object[] objects : objs) {
			AccountSafeDto dto = new AccountSafeDto();
			dto.setId(UUID.fromString(objects[0].toString()));
			dto.setAccount(objects[1].toString());
			dto.setName(objects[2]==null?null:objects[2].toString());
			dto.setDeptId(objects[3]==null?null:UUID.fromString(objects[3].toString()));
			dto.setDepName(objects[4]==null?null:objects[4].toString());
			dto.setOrgId(objects[5]==null?null:UUID.fromString(objects[5].toString()));
			dto.setOrgName(objects[6]==null?null:objects[6].toString());
			dto.setRoleId(objects[7]==null?null:UUID.fromString(objects[7].toString()));
			dto.setRoleName(objects[8]==null?null:objects[8].toString());
			dto.setStateStr(objects[9]==null?null:AccountState.ENABLE.toStr(Integer.parseInt(objects[9].toString())));
			dtos.add(dto);
		}		
		return PageFormater.obtain(dtos, count);
	}

	@Override
	public boolean existsByAccount(String account) {
		final String _acc = account;
		boolean r = doExists(new SpecificationExt<Account>(Account.class) {
			@Override
			public String getAbsHql() {
				return "select count(1) from Account acc where acc.account=?";
			}

			@Override
			public String getAbsSql() {
				return "select count(1) from tb_account where account=?";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{_acc};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.HQL;
			}			
		});
		
		return r;
	}
	
}
