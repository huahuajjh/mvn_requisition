package com.tq.requisition.domain.model.removeFamily;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 拆迁户聚合根接口
 * @author jjh
 * @time 2015-12-28 2:26
 */
public interface IFamilyRepository extends IRepository<Family>{
	/**
	 * 新增拆迁户
	 * @param fml
	 * 		待新增的拆迁户
	 * @return
	 * 		新增后的拆迁户
	 */
	Family addFamily(Family fml);
	
	/**
	 * 根据查询模型查询拆迁户集合
	 * @param queryModel
	 * 		查询模型
	 * @param pageModel
	 * 		分页模型
	 * @return PageFormater
	 * 		page模型
	 */
	PageFormater getListByFuzzy(FamilyQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 编辑拆迁户数据
	 * @param fml
	 */
	Family editFamily(Family fml);
	
	/**
	 * 删除拆迁户信息
	 * @param id
	 * 		拆迁户id
	 */
	void deleteFamily(UUID id);
	
	/**
	 * 根据id集合获取户信息集合
	 * @param uuids
	 * 		id集合
	 * @return List<Family>
	 * 		户集合
	 */
	List<Family> getFml4Print(String uuids);
	
	/**
	 * 查询拆迁户基本信息集合
	 * @param queryModel
	 * 		查询model
	 * @param pageModel
	 * 		分页model
	 * @return List<FamilyBasicInfoDto>
	 * 		List<FamilyBasicInfoDto>
	 */
	PageFormater getFmlBasicInfo(FamilyQueryModel queryModel,PageModel pageModel);
}
