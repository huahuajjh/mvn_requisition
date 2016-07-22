package com.tqmars.requisition.application.proMgt;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tqmars.requisition.application.BaseApplication;
import com.tqmars.requisition.autoMapper.AnnouncementMapper;
import com.tqmars.requisition.autoMapper.NewProjectMapper;
import com.tqmars.requisition.autoMapper.ProItemMapper;
import com.tqmars.requisition.autoMapper.ProMapper;
import com.tqmars.requisition.domain.IRepository.IRepositoryContext;
import com.tqmars.requisition.domain.model.project.Announcement;
import com.tqmars.requisition.domain.model.project.IProjectRepository;
import com.tqmars.requisition.domain.model.project.Project;
import com.tqmars.requisition.domain.model.project.ProjectItem;
import com.tqmars.requisition.domain.model.project.ProjectType;
import com.tqmars.requisition.exception.DomainException;
import com.tqmars.requisition.infrastructure.utils.Formater;
import com.tqmars.requisition.infrastructure.utils.PageFormater;
import com.tqmars.requisition.presentation.dto.project.AnnouncementDto;
import com.tqmars.requisition.presentation.dto.project.NewProDto;
import com.tqmars.requisition.presentation.dto.project.ProExportCondition;
import com.tqmars.requisition.presentation.dto.project.ProImportAndExportDto;
import com.tqmars.requisition.presentation.dto.project.ProItemDto;
import com.tqmars.requisition.presentation.dto.project.ProNameDto;
import com.tqmars.requisition.presentation.dto.project.ProQueryModel;
import com.tqmars.requisition.presentation.dto.project.ProTypeDto;
import com.tqmars.requisition.presentation.dto.project.ProjectDto;
import com.tqmars.requisition.presentation.dto.share.PageModel;
import com.tqmars.requisition.presentation.serviceContract.proMgt.IProMgtServiceContract;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * 项目管理契约接口实现类，该类操作项目的curd，以及项目月度信息
 * @author jjh
 * @time 2015-12-27 17:42
 */
public class ProMgtServiceIContractmpl extends BaseApplication implements IProMgtServiceContract{
	private IProjectRepository projectRepository;
	
	public ProMgtServiceIContractmpl(
			IRepositoryContext context,//
			IProjectRepository projectRepository) {
		super(context);
		this.projectRepository = projectRepository;
		this.projectRepository.setAggregatorRootClass(Project.class);
	}

	@Override
	public String addPro(NewProDto dto) {
		Project pro = NewProjectMapper.toModel(dto);
		context().beginTransaction();
		try {
			projectRepository.addPro(pro);
			context().commit();
			return toJson("新增項目成功", null, Formater.OperationResult.SUCCESS);
		} catch (DomainException e) {
			return toJson("新增項目失敗-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().close();
		}
	}

	@Override
	public List<ProTypeDto> getProType() {
		List<ProTypeDto> list = new ArrayList<ProTypeDto>();
		list.add(ProTypeDto.obtain(ProjectType.OTHER.toValue(),ProjectType.OTHER.toStr()));
		list.add(ProTypeDto.obtain(ProjectType.INFRASTRUCTURE.toValue(),ProjectType.INFRASTRUCTURE.toStr()));
		return list;
	}

	@Override
	public String getAnnounce(UUID proId) {
		try {
			List<Announcement> list = projectRepository.getAnnouncementsByProId(proId);
			List<AnnouncementDto> dtos = AnnouncementMapper.toDtoList(list);
			return toJson("成功", dtos, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String getProListFuzzy(ProQueryModel query, PageModel page) {
		try {
			PageFormater pageFormater = projectRepository.getProjectsByFuzzy(query, page);
			return toJson("成功", pageFormater, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
		}
		
	}

	@Override
	public String editPro(ProjectDto dto) {
		context().beginTransaction();
		try {
			Project pro = projectRepository.getByKey(Project.class, dto.getId());
			pro.modify(ProMapper.toModel(dto));
			pro.validate();
			pro.toAnnStr();
			pro.toProTypeStr();
			projectRepository.update(pro);
			context().commit();
			return toJson("修改项目信息成功", pro, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("修改项目信息失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().close();
		}
	}

	@Override
	public String deletePro(UUID proId) {
		throw new NotImplementedException("未实现的方法");
	}

	@Override
	public String getProNameListFuzzy(String fuzzyName) {
		/*仓储中开启了事务*/
		try {
			List<ProNameDto> names = projectRepository.getProNameByFuzzy(fuzzyName);
			return toJson("成功", names, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("获取项目名失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String addProItem(ProItemDto dto, UUID proId) {
		context().beginTransaction();
		try {
			Project pro = projectRepository.addProItem(ProItemMapper.toModel(dto), proId);
			context().commit();
			return toJson("新增項目月度信息成功", pro, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("新增項目月度信息失敗-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().close();
		}
	}

	@Override
	public String getProItemsByProId(UUID proId) {
		try {
			List<ProjectItem> items = projectRepository.getProjectItemsByProId(proId);
			List<ProItemDto> dtos = ProItemMapper.toDtoList(items);
			return toJson("成功", dtos, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("獲取月度信息失敗-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String addAnnouncement(AnnouncementDto dto) {
		try {
			context().beginTransaction();
			Project pro = projectRepository.addAnnouncement(AnnouncementMapper.toModel(dto));
			context().commit();
			return toJson("新增公告成功", pro, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("新增公告失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		
	}

	@Override
	public String getProById(UUID id) {
		try {
			return toJson("成功", getById4Entity(id), Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("获取项目信息失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public ProjectDto getById4Entity(UUID id) {
		Project pro = projectRepository.getByKey(Project.class, id);
		if(null==pro){return null;}
		pro.toAnnStr();
		pro.toProTypeStr();
		ProjectDto dto = ProMapper.toDto(pro);
		return dto;
	}

	@Override
	public String addByFile(List<ProImportAndExportDto> list) {
		if(null == list){
			return toJson("项目报表转换异常-未获取到项目信息", null, Formater.OperationResult.FAIL);
		}
		List<Project> pros = new ArrayList<>();
		for (ProImportAndExportDto proImportAndExportDto : list) {
			pros.add(proImportAndExportDto.toProject());
		}
		
		context().beginTransaction();
		try {
			projectRepository.addProByFile(pros);
			context().commit();
			return toJson("项目报表上传成功", list, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			context().rollback();
			return toJson("项目报表转换异常-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().close();
		}
	}
	
	@Override
	public List<ProImportAndExportDto> exportProByDate(ProExportCondition condition) {
		if(null==condition){
			return null;
		}
		List<Project> list = projectRepository.exportByMonth(condition);
		if(null==list || list.size()==0){
			return null;
		}
		List<ProImportAndExportDto> dtos = ProImportAndExportDto.obtainByProList(list);
		return dtos;
	}
	
	@Override
	public String editAnnouncement(AnnouncementDto dto) {
		try {
			context().beginTransaction();
			Announcement an = AnnouncementMapper.toModel(dto);
			an.setId(dto.getId());
			projectRepository.editAnnouncement(an);
			context().commit();
			return toJson("编辑公告成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("编辑公告失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().rollback();
		}
	}
	
	@Override
	public String editProItem(ProItemDto dto) {
		try {
			context().beginTransaction();
			ProjectItem item = ProItemMapper.toModel(dto);
			item.setId(dto.getId());
			projectRepository.editProItem(item);
			context().commit();
			return toJson("编辑月度信息成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("编辑月度信息失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().rollback();
		}
	}
	
	@Override
	public String getPro4Print(String uuids) {
		List<Project> list = projectRepository.getProjects4Print(uuids);
		return toJson("获取打印信息成功", list, Formater.OperationResult.SUCCESS);
	}

	@Override
	public String editMoneyUnit(UUID id, String moneyUnit, String otherMoneyUnit) {
		context().beginTransaction();
		try {
			Project pro = projectRepository.getByKey(Project.class, id);
			pro.setMoneyUnit(moneyUnit);
			pro.setOtherMoneyUnit(otherMoneyUnit);
			projectRepository.update(pro);
			context().commit();
			return toJson("录入项目出资单位信息成功", pro, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("录入项目出资单位信息失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().close();
		}
	}

}
