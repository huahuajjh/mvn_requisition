package com.tq.requisition.infrastructure.Repository.organization;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.model.organization.IOrganizationRepository;
import com.tq.requisition.domain.model.organization.Organization;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.exception.InvalidOperationException;
import com.tq.requisition.infrastructure.Repository.HbRepository;
import com.tq.requisition.infrastructure.Specifications.orgMgt.OrgIdExistsSpecificator;
import com.tq.requisition.infrastructure.Specifications.orgMgt.OrgListSpecification;
import com.tq.requisition.infrastructure.Specifications.orgMgt.OrgNameExistsSpecification;
import com.tq.requisition.infrastructure.Specifications.orgMgt.OrgNumberExistsSpecification;

/**
 * 组织聚合仓储
 * 
 * @author jjh
 * @time 2015-12-18 19:20
 */
public class OrganizationRepository extends HbRepository<Organization>
		implements IOrganizationRepository {

	public OrganizationRepository(IRepositoryContext context) {
		super(context);
	}

	@Override
	public boolean isOrgNameUnique(String orgName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Organization createOrg(final Organization org) throws DomainException {
		if(org==null)
		{
			throw new NullPointerException("单位对象为null");
		}
		checkNameAndNumber(org.getOrgName(), org.getOrgNumber(), org.getId());		
		Organization o = Organization.obtain(org.getOrgName(),org.getOrgNumber());
		add(o);
		return o;
	}

	@Override
	public void editOrg(Organization org) throws DomainException {
		boolean r = exists(new OrgIdExistsSpecificator(Organization.class,org.getId()));
		if (!r) {
			throw new InvalidOperationException("未查询到指定的单位信息");
		}
		Organization organization = getByKey(Organization.class, org.getId());
		
		if (org.getOrgName().equals(organization.getOrgName())
				&&
				org.getOrgNumber().equals(organization.getOrgNumber()))
		{return;}
		
		
		//检测单位名称和编号是否存在
		checkNameAndNumber(org.getOrgName(), org.getOrgNumber(),org.getId());
		
		organization.setOrgName(org.getOrgName());
		organization.setOrgNumber(org.getOrgNumber());
		update(organization);
	}

	@Override
	public List<Organization> getOrgList() {
		return getAll(new OrgListSpecification(Organization.class));
	}

	@Override
	public void deleteOrgByKey(UUID orgId) {
		if(orgId == null)
		{
			throw new NullPointerException("组织id为null");
		}
		
		removeByKey(Organization.class, orgId);		
	}

	private void checkNameAndNumber(String name,String number,UUID id) throws DomainException {
		boolean r = exists(new OrgNameExistsSpecification(Organization.class, name,id));
		if (r) {
			throw new DomainException("单位名[" + name+ "]已存在");
		}
		boolean r2 = exists(new OrgNumberExistsSpecification(Organization.class, number,id));
		if(r2){throw new DomainException("单位编号[" + number+ "]已存在");}
	}
}
