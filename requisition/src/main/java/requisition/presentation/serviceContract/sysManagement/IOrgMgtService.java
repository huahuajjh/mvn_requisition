package com.tq.requisition.presentation.serviceContract.sysManagement;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.presentation.dto.sysMgt.OrgDto;

/**
 * 组织管理契约接口
 * 
 * @author jjh
 * @time 2015-12-23 17:51
 */
public interface IOrgMgtService {
	/**
	 * 新增组织
	 * 
	 * @param org
	 *            组织Dto数据
	 * @return
	 */
	String addOrg(OrgDto org);

	/**
	 * 修改组织信息
	 * 
	 * @param org
	 * @return
	 */
	String editOrgInfo(OrgDto org);

	/**
	 * 删除组织
	 * 
	 * @param id
	 *            组织标识
	 */
	String deleteByOrgKey(UUID orgId);

	/**
	 * 获取组织DTO集合数据
	 * 
	 * @return 数据DTO列表
	 */
	List<OrgDto> getOrgList();

	/**
	 * 获取组织DTO数据Json集合数据
	 * json：[OrgDto...]
	 * @return Json集合
	 */
	String getOrgListJson();

}
