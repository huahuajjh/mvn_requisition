package com.tq.requisition.presentation.serviceContract.proMgt;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.presentation.dto.project.AnnouncementDto;
import com.tq.requisition.presentation.dto.project.NewProDto;
import com.tq.requisition.presentation.dto.project.ProExportCondition;
import com.tq.requisition.presentation.dto.project.ProImportAndExportDto;
import com.tq.requisition.presentation.dto.project.ProItemDto;
import com.tq.requisition.presentation.dto.project.ProQueryModel;
import com.tq.requisition.presentation.dto.project.ProTypeDto;
import com.tq.requisition.presentation.dto.project.ProjectDto;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 项目管理契约接口，用于项目的curd
 * @author jjh
 * @time 2015-12-27 16:16
 *
 */
public interface IProMgtServiceContract {
	/**
	 * 新增项目
	 * @param dto
	 * 		新增项目dto
	 * @return
	 */
	String addPro(NewProDto dto);	
	
	/**
	 * 获取项目类型
	 * @return
	 */
	List<ProTypeDto> getProType();
	
	/**
	 * 根据项目id获取公告
	 * @param proId
	 * @return
	 */
	String getAnnounce(UUID proId);
	
	/**
	 * 模糊查询项目
	 * @param query
	 * 		模糊查询模型
	 * @param page
	 * 		分页模型
	 * @return
	 */
	String getProListFuzzy(ProQueryModel query,PageModel page);
	
	/**
	 * 编辑项目
	 * @param dto
	 * 		待编辑的项目dto
	 * @return
	 */
	String editPro(ProjectDto dto);
	
	/**
	 * 删除项目
	 * @param proId
	 * 		项目id
	 * @return
	 */
	String deletePro(UUID proId);
	
	/**
	 * 根据模糊项目名称字符串获取项目名称集合
	 * @param fuzzyName
	 * 		模糊项目名称
	 * @return
	 */
	String getProNameListFuzzy(String fuzzyName);
	
	/**
	 * 为指定的项目添加项目月度信息
	 * @param dto
	 * 		项目月度dto
	 * @param proId
	 * 		项目id
	 * @return
	 */
	String addProItem(ProItemDto dto,UUID proId);
	
	/**
	 * 根据项目id获取该项目下的月度进度信息
	 * @param proId
	 * 		项目id
	 * @return
	 */
	String getProItemsByProId(UUID proId);
	
	/**
	 * 新增公告
	 * @param dto
	 * 		待新增的公告
	 * @return
	 * 		返回新增后的公告实体数据
	 */
	String addAnnouncement(AnnouncementDto dto);

	/**
	 * 根据项目id获取项目信息
	 * @param id
	 * @return
	 */
	String getProById(UUID id);

	/**
	 * 根据项目id获取项目信息
	 * @param id
	 * @return
	 */
	ProjectDto getById4Entity(UUID id);
	
	/**
	 * 导入项目信息
	 * @param list
	 * 		项目dto集合
	 * @return String
	 * 		json消息
	 */
	String addByFile(List<ProImportAndExportDto> list);
	
	/**
	 * 根据查询model导出项目报表
	 * @param condition
	 * 		查询model
	 * @return List<ProImportAndExportDto>
	 * 		项目导入导出model
	 * 
	 */
	List<ProImportAndExportDto> exportProByDate(ProExportCondition condition);
	
	/**
	 * 编辑公告
	 * @param dto
	 * 		公告实体dto
	 * @return String
	 * 		json
	 */
	String editAnnouncement(AnnouncementDto dto);
	
	/**
	 * 编辑项目月度信息
	 * @param dto
	 * 		项目月度实体
	 * @return String
	 * 		json
	 */
	String editProItem(ProItemDto dto);
	
	/**
	 * 为项目打印提供接口，根据项目id集合获取项目集合
	 * @param uuids
	 * 		项目id集合
	 * @return String
	 * 		json
	 */
	String getPro4Print(String uuids);
	
	/**
	 * 修改指定项目的出资单位
	 * @param id 所属项目
	 * @param moneyUnit 国土局出资
	 * @param otherMoneyUnit 其他出资单位
	 * @return
	 */
	String editMoneyUnit(UUID id,String moneyUnit,String otherMoneyUnit);
}
