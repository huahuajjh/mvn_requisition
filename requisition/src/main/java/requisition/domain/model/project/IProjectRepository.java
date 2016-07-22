package com.tq.requisition.domain.model.project;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.project.ProExportCondition;
import com.tq.requisition.presentation.dto.project.ProNameDto;
import com.tq.requisition.presentation.dto.project.ProQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 项目仓储接口
 * 
 * @author jjh
 * @time 2015-12-27 20:47
 */
public interface IProjectRepository extends IRepository<Project> {
	/**
	 * 新增项目
	 * @param model
	 * 		项目model
	 */
	void addPro(Project model) throws DomainException;
	
	/**
	 * 根据项目id获取公告集合
	 * @param proId
	 * 		项目id
	 * @return List<Announcement>
	 * 		公告集合
	 */
	List<Announcement> getAnnouncementsByProId(UUID proId);
	
	/**
	 * 项目多条件模糊查询
	 * @param queryModel
	 * 		查询条件model
	 * @param pageModel
	 * 		查询分页参数model
	 * @return PageFormater
	 * 		分页model
	 */
	PageFormater getProjectsByFuzzy(ProQueryModel queryModel,PageModel pageModel);
	
	/**
	 * 根据项目模糊名查询项目名集合
	 * @param fuzzyName
	 * 		项目模糊名
	 * @return
	 */
	List<ProNameDto> getProNameByFuzzy(String fuzzyName);
	
	/**
	 * 根据项目id获取项目月度集合数据
	 * @param proId
	 * 		项目id
	 * @return List<ProjectItem>
	 * 		项目月度信息集合
	 */
	List<ProjectItem> getProjectItemsByProId(UUID proId);
	
	/**
	 * 为指定的项目新增项目月度信息
	 * @param item
	 * 		项目月度信息实体
	 * @param proId
	 * 		项目id
	 */
	Project addProItem(ProjectItem item,UUID proId) throws DomainException;

	/**
	 * 新增公告
	 * @param announcement
	 * @return
	 */
	public Project addAnnouncement(Announcement announcement);
	
	/**
	 * 导入文件，新增项目
	 * @param list
	 * 		项目集合
	 */
	public void addProByFile(List<Project> list) throws DomainException;
	
	/**
	 * 根据月份导出项目报表
	 * @param date
	 * 		筛选的月份
	 * @return List<Project>
	 * 		项目集合
	 */
	public List<Project> exportByMonth(ProExportCondition condition);
	
	/**
	 * 修改公告
	 * @param model
	 * 		公告实体
	 */
	public void editAnnouncement(Announcement model);
	
	/**
	 * 编辑
	 * @param item
	 */
	public void editProItem(ProjectItem item);
	
	/**
	 * 根据项目id集合获取项目集合
	 * @param uuids
	 * 		项目id集合
	 * @return List<Project>
	 * 		项目集合
	 */
	public List<Project> getProjects4Print(String uuids);
	
	/**
	 * 根据项目名称获取id
	 * @param name
	 * 		项目名
	 * @return UUID
	 * 		项目id
	 */
	public UUID getIdByName(String name);
	
}
