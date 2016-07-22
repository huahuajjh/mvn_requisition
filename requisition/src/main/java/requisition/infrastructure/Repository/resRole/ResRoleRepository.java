package com.tq.requisition.infrastructure.Repository.resRole;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.NotImplementedException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.resRole.IResRoleRepository;
import com.tq.requisition.domain.model.resRole.ResRole;
import com.tq.requisition.exception.DataAlreadyExistsException;
import com.tq.requisition.infrastructure.Repository.HbRepository;
import com.tq.requisition.infrastructure.Specifications.resRole.ResourIdsAtRoleIdsSpecifications;

/**
 * 角色资源仓储
 * @author jjh
 * @time 2015-12-21 21:20
 */
public class ResRoleRepository  extends HbRepository<ResRole> implements IResRoleRepository{

	public ResRoleRepository(IRepositoryContext context) {
		super(context);
	}
	
	@Override
	public List<UUID> getResIdsByRoleIds(UUID... roldIds) {
		List<UUID> resIds = new ArrayList<UUID>();
		List<ResRole> roles = getAll(new ResourIdsAtRoleIdsSpecifications(ResRole.class,roldIds));
		for (ResRole resRole : roles) {
			resIds.add(resRole.getResId());
		}
		return resIds;
	}

	@Override
	public int removeAllPermissionByRId(final UUID rId) {
		int r = executeUpdate(new SpecificationExt<ResRole>(ResRole.class) {
			@Override
			public String getAbsHql() {
				throw new NotImplementedException("未使用hql表达式，待实现的方法getAbsHql()");
			}
			@Override
			public String getAbsSql() {
				return "delete from tb_permission where role_id=?";
			}
			@Override
			public Object[] getAbsParameters() {
				return new Object[]{rId.toString()};
			}
			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		return r;
	}

	@Override
	public void assignRes4Role(final UUID rid,final UUID resId) {
		boolean r = exists(new SpecificationExt<ResRole>(ResRole.class) {
			@Override
			public String getAbsHql() {
				throw new NotImplementedException("未实现的方法getAbsHql()");
			}
			@Override
			public String getAbsSql() {
				return "select count(1) from tb_permission where role_id=? and res_id=?";
			}
			@Override
			public Object[] getAbsParameters() {
				return new Object[]{rid.toString(),resId.toString()};
			}
			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		if(r)
		{
			throw new DataAlreadyExistsException("为该角色指定资源已经存在");
		}
		add(ResRole.obtain(rid, resId));
	}
	
	@Override
	public List<UUID> getResourcesByRid(final UUID rid) {
		if(null == rid)
		{
			throw new NullPointerException("角色id為null");
		}
		List<UUID> idList = new ArrayList<>();
		SQLQuery sql = hbContext().session().createSQLQuery("select res_id from tb_permission where role_id='"+rid.toString()+"'");
		List<Object> list = sql.list();
		for (Object objects : list) {
			idList.add(UUID.fromString(objects.toString()));
		}
		Session session = hbContext().session();
//		session.close();
		return idList;
	}
	
}
