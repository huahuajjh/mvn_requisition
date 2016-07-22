package com.tq.requisition.domain.service.domainserviceimpl;

import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.domain.model.account.IAccountRepository;
import com.tq.requisition.domain.model.department.Department;
import com.tq.requisition.domain.model.department.IDepartmentRepository;
import com.tq.requisition.domain.service.BaseDomainService;
import com.tq.requisition.domain.service.idomainservice.IDeptRemoveService;

/**
 * 删除部门实现类
 * 
 * @author jjh
 * @time 2015-12-26 16:01
 */
public class DeptRemoveServiceImpl extends BaseDomainService implements IDeptRemoveService{
	//账户仓储
	private IAccountRepository accountRepository;
	//部门仓储
	private IDepartmentRepository deptRepository;	

	public DeptRemoveServiceImpl(
			IRepositoryContext context,
			IAccountRepository accountRepository,
			IDepartmentRepository deptRepository) {
		
		super(context);		
		this.accountRepository = accountRepository;
		this.accountRepository.setAggregatorRootClass(Account.class);
		
		this.deptRepository = deptRepository;
		this.deptRepository.setAggregatorRootClass(Department.class);
	}

	@Override
	public void removeDept(UUID deptId) {
		//将被删除的部门标记为删除状态
		deptRepository.removeDept(deptId);
		//更新所有的关于被删除部门的账户的部门信息标注为null
		accountRepository.updateDept(deptId);
	}

}
