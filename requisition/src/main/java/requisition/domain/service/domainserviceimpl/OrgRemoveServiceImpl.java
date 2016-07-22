package com.tq.requisition.domain.service.domainserviceimpl;

import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.domain.model.account.IAccountRepository;
import com.tq.requisition.domain.model.department.Department;
import com.tq.requisition.domain.model.department.IDepartmentRepository;
import com.tq.requisition.domain.model.organization.IOrganizationRepository;
import com.tq.requisition.domain.model.organization.Organization;
import com.tq.requisition.domain.service.BaseDomainService;
import com.tq.requisition.domain.service.idomainservice.IOrgRemoveService;
import com.tq.requisition.infrastructure.Specifications.deptMgt.DeptRemoveByOrgIdSpecification;

/**
 * 删除组织服务实现类
 * @author jjh
 * @time 2015-12-26 14:51
 */
public class OrgRemoveServiceImpl extends BaseDomainService implements IOrgRemoveService{
	/**组织仓储*/
	private IOrganizationRepository orgRepository;
	/**部门仓储*/
	private IDepartmentRepository deptRepository;
	/**账户仓储*/
	private IAccountRepository accountRepository;
	
	public OrgRemoveServiceImpl(//
			IRepositoryContext context,//
			IOrganizationRepository orgRepository,//
			IDepartmentRepository deptRepository,//
			IAccountRepository accountRepository) {	
		super(context);
		this.orgRepository = orgRepository;
		this.orgRepository.setAggregatorRootClass(Organization.class);
		
		this.deptRepository = deptRepository;
		this.deptRepository.setAggregatorRootClass(Department.class);
		
		this.accountRepository = accountRepository;
		this.accountRepository.setAggregatorRootClass(Account.class);
	}

	@Override
	public void removeOrgById(UUID Id) {
		//将待删除的组织标记为删除状态
		orgRepository.removeByKey(Organization.class, Id);
		System.out.println("--------------------------org delete--------------------------");
		//根据组织id去部门仓储中标记部门为删除状态
		deptRepository.executeUpdate(new DeptRemoveByOrgIdSpecification(Department.class, Id));
		System.out.println("--------------------------dept delete--------------------------");
		//将账户仓储中关于该部门的id记录标记为null状态
		accountRepository.updateOrg(Id);
		System.out.println("--------------------------acc update--------------------------");
		//提交事务
		context().commit();
	}

}
