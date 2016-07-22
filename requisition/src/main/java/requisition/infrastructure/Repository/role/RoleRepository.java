package com.tq.requisition.infrastructure.Repository.role;

import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.role.IRoleRepository;
import com.tq.requisition.domain.model.role.Role;
import com.tq.requisition.exception.InvalidOperationException;
import com.tq.requisition.infrastructure.Repository.HbRepository;
import com.tq.requisition.infrastructure.Specifications.role.RolePageByNameSpecification;
import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.infrastructure.utils.TotalCount;

/**
 * 角色仓储实现类
 * @author jjh
 * @time 2015-12-21 13:16
 * 
 */
public class RoleRepository extends HbRepository<Role> implements IRoleRepository{
	public RoleRepository(IRepositoryContext context) {
		super(context);		
	}

	@Override
	public Formater createRole(final Role role) throws InvalidOperationException {
		boolean r = exists(new SpecificationExt<Role>(Role.class) {
			@Override
			public String getAbsHql() {
				throw new NotImplementedException("未实现的方法getAbsHql()");
			}

			@Override
			public String getAbsSql() {
				return "select count(1) from tb_role where role_name=?";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{role.getRoleName()};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
			
		});

		if(r)
		{
			return Formater.obtain(String.format("角色名%s已经存在", role.getRoleName()), null, Formater.OperationResult.FAIL);
		}		
		Role _role = Role.obtain(role.getRoleName());
		add(_role);
		return Formater.obtain("新增角色成功", _role, Formater.OperationResult.SUCCESS);
	}

	@Override
	public List<Role> getRoleList(final String name, int pageIndex, int pageNum, TotalCount totalCount) {
		int total = getTotalCount(new SpecificationExt<Role>(Role.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select count(1) from tb_role where role_name like '%"+name+"%'";
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
		totalCount.setTotalCount(total);
		return getAll(new RolePageByNameSpecification(Role.class, name, pageIndex, pageNum));
	}

	@Override
	public Formater modifyRole(Role _role) throws InvalidOperationException {
		Role role = getByKey(Role.class, _role.getId());
		if(role==null)
		{
			return Formater.obtain("不存在的角色，无法修改", null, Formater.OperationResult.ERROR);
		}
		role.setRoleName(_role.getRoleName());
		update(role);
		return Formater.obtain("修改角色成功", null, Formater.OperationResult.SUCCESS);
	}

	@Override
	public Role getRoleByName(String roleName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> getAllRole() {
		return getAll(new SpecificationExt<Role>(Role.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_role";
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
	}
}
