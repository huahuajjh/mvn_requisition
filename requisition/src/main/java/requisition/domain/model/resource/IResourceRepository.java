package com.tq.requisition.domain.model.resource;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.domain.model.share.ResourceType;

/**
 * 资源聚合根仓储
 * 
 * @author jjh
 * @time 2015-12-21 18：13
 */
public interface IResourceRepository extends IRepository<Resource> {

	/**
	 * 根据资源集合ID获取资源列表
	 * 
	 * @param uuids
	 *            资源集合
	 * @return 资源集合
	 */
	List<Resource> getResourceByIds( int hierarchy,UUID... uuids);
	
	List<Resource> getResourcesByIdsAndType(int hierarchy,ResourceType type ,UUID... uuids);

	/**
	 * 獲取所有資源列表
	 * 只需要部分字段，資源名稱，資源id，資源是否有子節點
	 * @return List<Resource>
	 * 		資源集合
	 */
	List<Resource> getAllResources();
}
