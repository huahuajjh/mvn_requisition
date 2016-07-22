package com.tq.requisition.infrastructure.Repository.resource;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.resource.IResourceRepository;
import com.tq.requisition.domain.model.resource.Resource;
import com.tq.requisition.domain.model.share.ResourceType;
import com.tq.requisition.infrastructure.Repository.HbRepository;
import com.tq.requisition.infrastructure.Specifications.resource.ResourceByTypeListSpecifocations;
import com.tq.requisition.infrastructure.Specifications.resource.ResourceListSpecifocations;

/**
 * 资源聚合根仓储实现
 * 
 * @author jjh
 * @time 2015-12-21 18：14
 */
public class ResourceRepository extends HbRepository<Resource> implements
		IResourceRepository {

	public ResourceRepository(IRepositoryContext context) {
		super(context);
	}

	@Override
	public List<Resource> getResourceByIds(int hierarchy, UUID... uuids) {
		return getAll(new ResourceListSpecifocations(Resource.class, hierarchy,
				uuids));
	}

	@Override
	public List<Resource> getResourcesByIdsAndType(int hierarchy,
			ResourceType type, UUID... uuids) {
		return getAll(new ResourceByTypeListSpecifocations(Resource.class,
				hierarchy, type, uuids));
	}

	@Override
	public List<Resource> getAllResources() {
		List<Resource> list = getAll(new SpecificationExt<Resource>(Resource.class) {
			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_resource where moudle <> 'admin'";
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

}
