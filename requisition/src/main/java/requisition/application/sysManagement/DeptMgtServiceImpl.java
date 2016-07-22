package com.tq.requisition.application.sysManagement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.application.BaseApplication;
import com.tq.requisition.autoMapper.DeptMapper;
import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.model.department.Department;
import com.tq.requisition.domain.model.department.IDepartmentRepository;
import com.tq.requisition.domain.service.idomainservice.IDeptRemoveService;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.presentation.dto.sysMgt.DeptDto;
import com.tq.requisition.presentation.serviceContract.sysManagement.IDeptMgtService;

/**
 * 部门管理业务
 * @author jjh
 * @time 2015-12-24 16:39
 * @version 1.0
 */
public class DeptMgtServiceImpl extends BaseApplication implements IDeptMgtService{
	private IDepartmentRepository deptRepository;
	private IDeptRemoveService deptRemoveService;	

	public DeptMgtServiceImpl(//
			IRepositoryContext context,//
			IDepartmentRepository deptRepository,
			IDeptRemoveService deptRemoveService) {
		super(context);
		this.deptRepository = deptRepository;
		this.deptRepository.setAggregatorRootClass(Department.class);
		this.deptRemoveService = deptRemoveService;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<DeptDto> getDeptDtoListByOrgId(UUID orgId) {
		List<Department> list = deptRepository.getDeptByOrgId(orgId);
		List<DeptDto>deptDtoList = new ArrayList<DeptDto>();
		for (Department department : list) {
			deptDtoList.add(DeptMapper.toDto(department));
		}
		return deptDtoList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getDeptDtoListByOrgIdToJson(UUID orgId) {
		List<Department> deptList = deptRepository.getDeptByOrgId(orgId);
		return toJson("获取部门列表成功", DeptMapper.toDtolList(deptList), Formater.OperationResult.SUCCESS);
	}

	@Override
	public String edit(DeptDto dept) {
		if(dept == null)
		{
			throw new NullPointerException("部门dto为null");
		}
		try {
			context().beginTransaction();
			deptRepository.modifyDept(DeptMapper.toModel(dept));
			context().commit();
			return toJson("修改部门成功",null, Formater.OperationResult.SUCCESS); 
		} catch (DomainException e) {
			e.printStackTrace();
			return toJson("修改部门失败-"+e.getMessage(),null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String delete(UUID id) {
		if(id == null)
		{
			throw new NullPointerException("部门id为null");
		}
		try {
			context().beginTransaction();
			deptRemoveService.removeDept(id);
			context().commit();
			return toJson("删除部门成功", null, Formater.OperationResult.SUCCESS);			
		} catch (Exception e) {
			return toJson("删除部门失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}
	
	@Override
	public String createDept(DeptDto dto) {
		if(dto == null)
		{
			throw new NullPointerException("待新增的dto对象为null");
		}
		Department dept;
		try {
			context().beginTransaction();
			dept = deptRepository.createDept(DeptMapper.toModel(dto));
			context().commit();
			return toJson("创建部门成功", DeptMapper.toDto(dept), Formater.OperationResult.SUCCESS);
		} catch (DomainException e) {
			return toJson("创建部门失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		
	}

}
