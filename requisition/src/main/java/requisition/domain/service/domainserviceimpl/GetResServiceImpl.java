package com.tq.requisition.domain.service.domainserviceimpl;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.account.Account;
import com.tq.requisition.domain.model.account.IAccountRepository;
import com.tq.requisition.domain.model.resRole.IResRoleRepository;
import com.tq.requisition.domain.model.resRole.ResRole;
import com.tq.requisition.domain.model.resource.IResourceRepository;
import com.tq.requisition.domain.model.resource.Resource;
import com.tq.requisition.domain.service.idomainservice.IGetResService;
import com.tq.requisition.infrastructure.utils.ConfigFileUtil;

public class GetResServiceImpl implements IGetResService {

//	private IRoleAccountRepository roleAccountRepository;
	private IAccountRepository accountRepository;
	private IResRoleRepository resRoleRepository;
	private IResourceRepository resourceRepository;
	
	public GetResServiceImpl(//IRoleAccountRepository roleAccountRepository,
			IResRoleRepository resRoleRepository,
			IResourceRepository resourceRepository,
			IAccountRepository accountRepository) {
		super();
//		this.roleAccountRepository = roleAccountRepository;
//		this.roleAccountRepository.setAggregatorRootClass(RoleAccount.class);
		
		this.resRoleRepository = resRoleRepository;
		this.resRoleRepository.setAggregatorRootClass(ResRole.class);
		
		this.resourceRepository = resourceRepository;
		this.resourceRepository.setAggregatorRootClass(Resource.class);
		
		this.accountRepository = accountRepository;
		this.accountRepository.setAggregatorRootClass(Account.class);
	}

//	@Override
//	public List<Resource> getResourceByUserId(UUID userId, int hierarchy) {
//		List<Resource> datas = new ArrayList<Resource>();
//		Role role = this.roleAccountRepository.getRoleByUid(userId);
//		List<UUID> resIds = this.resRoleRepository.getResIdsByRoleIds(role.getId());
//		UUID[] resKeys = new UUID[resIds.size()];
//		for (int i = 0; i < resIds.size(); i++) {
//			resKeys[i] = resIds.get(i);
//		}
//		return this.resourceRepository.getResourceByIds(hierarchy, resKeys);
//	}
//
//	@Override
//	public List<Resource> getResourceByUserId(UUID userId, ResourceType type,
//			int hierarchy) {
//		List<Resource> datas = new ArrayList<Resource>();
//		Role role = this.roleAccountRepository.getRoleByUid(userId);
//		List<UUID> resIds = this.resRoleRepository.getResIdsByRoleIds(role.getId());
//		UUID[] resKeys = new UUID[resIds.size()];
//		for (int i = 0; i < resIds.size(); i++) {
//			resKeys[i] = resIds.get(i);
//		}
//		return this.resourceRepository.getResourcesByIdsAndType(hierarchy, type,resKeys);
//	}

	
	@Override
	public List<Resource> getResByUserId(UUID uid) {
		if(isAdmin(uid.toString())){
			List<Resource> list = resourceRepository.getAll(new SpecificationExt<Resource>(Resource.class) {

				@Override
				public String getAbsHql() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public String getAbsSql() {
					return "select * from tb_resource where moudle='admin'";
				}

				@Override
				public Object[] getAbsParameters() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public OperationType getAbsType() {
					return OperationType.SQL;
				}
			});
			return list;
		}
		
		Account acc = accountRepository.getById(uid);
		List<UUID> ids = resRoleRepository.getResourcesByRid(acc.getRoleId());
		if(null == ids || ids.size()==0)
		{
			return null;
		}
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ids.size(); i++) {
			if(i==ids.size()-1)
			{
				sb.append("'").append(ids.get(i).toString()).append("'");
				break;
			}
			sb.append("'").append(ids.get(i).toString()).append("',");
		}
		List<Resource> ress = resourceRepository.getAll(new SpecificationExt<Resource>(Resource.class) {

			@Override
			public String getAbsHql() {
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_resource where id in("+sb.toString()+")";
			}

			@Override
			public Object[] getAbsParameters() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		return ress;
	}
	
	private boolean isAdmin(String adminId) {
		String id = ConfigFileUtil.readByKey("adminId");
		if(adminId.trim().equals(id))
		{
			return true;
		}
		return false;
	}

}
