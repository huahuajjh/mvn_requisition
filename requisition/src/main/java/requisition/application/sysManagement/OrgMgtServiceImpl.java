package com.tq.requisition.application.sysManagement;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.application.BaseApplication;
import com.tq.requisition.autoMapper.OrgMapper;
import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.model.organization.IOrganizationRepository;
import com.tq.requisition.domain.model.organization.Organization;
import com.tq.requisition.domain.service.idomainservice.IOrgRemoveService;
import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.presentation.dto.sysMgt.OrgDto;
import com.tq.requisition.presentation.serviceContract.sysManagement.IOrgMgtService;

/**
 * 组织管理业务
 * @author jjh+bless
 * @time 2015-12-23 16:22
 */
public class OrgMgtServiceImpl extends BaseApplication implements IOrgMgtService {
	private IOrganizationRepository orgRepository;
	private IOrgRemoveService orgRemoveDomainService;
	
 	public OrgMgtServiceImpl(//
 			IRepositoryContext context,//
 			IOrganizationRepository orgRepository,//
 			IOrgRemoveService orgRemoveDomainService) {
		super(context);
		this.orgRepository = orgRepository;
		this.orgRepository.setAggregatorRootClass(Organization.class);
		
		this.orgRemoveDomainService = orgRemoveDomainService;		
	}

	@Override
	public List<OrgDto> getOrgList() {
		context().beginTransaction();
		List<Organization> orgList = this.orgRepository.getOrgList();
		List<OrgDto> orgDtoList = OrgMapper.toDtoList(orgList);
		context().commit();
		return orgDtoList;
	}

	@Override
	public String getOrgListJson() {
		context().beginTransaction();
		String json = toJson("获取组织列表成功", getOrgList(), Formater.OperationResult.SUCCESS);
		context().commit();
		return json;
	}

	@Override
	public String addOrg(OrgDto org) {
		try {
			context().beginTransaction();
			Organization o = orgRepository.createOrg(OrgMapper.toModel(org));
			context().commit();
			return toJson("新增单位信息成功", OrgMapper.toDto(o), Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("新增单位信息失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String editOrgInfo(OrgDto org) {
		try {
			context().beginTransaction();
			orgRepository.editOrg(OrgMapper.toModel(org));
			context().commit();
			return toJson("修改单位信息成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("编辑单位信息失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String deleteByOrgKey(UUID orgId) {
		try {
			context().beginTransaction();
			orgRemoveDomainService.removeOrgById(orgId);
			context().commit();
			return toJson("删除组织成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("删除组织失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

}
