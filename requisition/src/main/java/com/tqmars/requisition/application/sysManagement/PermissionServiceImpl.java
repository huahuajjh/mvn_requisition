package com.tqmars.requisition.application.sysManagement;

import java.util.UUID;

import com.tqmars.requisition.application.BaseApplication;
import com.tqmars.requisition.domain.IRepository.IRepositoryContext;
import com.tqmars.requisition.exception.InvalidOperationException;
import com.tqmars.requisition.presentation.serviceContract.sysManagement.IPermissionService;

/**
 * 权限管理
 * @author jjh
 * @time 2015-12-24 22:14
 */
public class PermissionServiceImpl extends BaseApplication implements IPermissionService{	
	private com.tqmars.requisition.domain.service.idomainservice.IPermissionService pmsService;

	public PermissionServiceImpl(IRepositoryContext context,com.tqmars.requisition.domain.service.idomainservice.IPermissionService _pmsService) {		
		super(context);
		pmsService = _pmsService;
	}

	@Override
	public void assignRole4User(UUID id, UUID... rIds)  throws InvalidOperationException{
		pmsService.assignRole4User(id, rIds);
	}

	@Override
	public void assignRes4Role(UUID rid, UUID... resIds)  throws InvalidOperationException{
		pmsService.assignRes4Role(rid, resIds);
	}

	@Override
	public String assignResForRole(UUID rid, UUID... resIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAllRescourses(UUID roleId) {
		return pmsService.getAllRescourses(roleId);
	}

}
