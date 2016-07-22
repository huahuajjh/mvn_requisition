package com.tq.requisition.infrastructure.Repository.project;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.project.Announcement;
import com.tq.requisition.domain.model.project.IProjectRepository;
import com.tq.requisition.domain.model.project.Project;
import com.tq.requisition.domain.model.project.ProjectItem;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.Repository.HbRepository;
import com.tq.requisition.infrastructure.Specifications.project.ProByMonthSpecification;
import com.tq.requisition.infrastructure.Specifications.project.ProExistsByApprovalNumSpecification;
import com.tq.requisition.infrastructure.Specifications.project.ProExistsByNameSpecification;
import com.tq.requisition.infrastructure.Specifications.project.ProFuzzyQueryPageCountSpecification;
import com.tq.requisition.infrastructure.Specifications.project.ProFuzzyQuerySpecification;
import com.tq.requisition.infrastructure.Specifications.project.ProItemExistsDateSpecification;
import com.tq.requisition.infrastructure.Specifications.project.ProQuery4PrintSpecification;
import com.tq.requisition.infrastructure.Specifications.project.ProQueryByNameSpecification;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.project.ProExportCondition;
import com.tq.requisition.presentation.dto.project.ProNameDto;
import com.tq.requisition.presentation.dto.project.ProQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

/**
 * 项目仓储实现类
 * 
 * @author jjh
 * @time 2015-12-27 21:01
 */
public class ProjectRepository extends HbRepository<Project> implements
		IProjectRepository {

	public ProjectRepository(IRepositoryContext context) {
		super(context);
	}

	@Override
	public List<Announcement> getAnnouncementsByProId(UUID proId) {
		if (proId == null) {
			throw new NullPointerException("项目id为null");
		}
		List<Announcement> list = getBySql(Announcement.class, //
				"select * from tb_announcement where pro_id=?",//
				proId.toString());
		return list;
	}

	@Override
	public PageFormater getProjectsByFuzzy(ProQueryModel queryModel,
			PageModel pageModel) {
		int count = getTotalCount(new ProFuzzyQueryPageCountSpecification(
				Project.class, queryModel));
		List<Project> list = getAll(new ProFuzzyQuerySpecification(
				Project.class, queryModel, pageModel));
		for (Project project : list) {
			project.toAnnStr();
			project.toProTypeStr();
		}
		return PageFormater.obtain(list, count);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProNameDto> getProNameByFuzzy(String fuzzyName) {
		Session session = hbContext().session();
		if (fuzzyName == null) {
			fuzzyName = "";
		}
		SQLQuery sql = session
				.createSQLQuery("select id,pro_name from tb_project where pro_name like '%"
						+ fuzzyName.trim() + "%'");
		sql.setFirstResult(0);
		sql.setMaxResults(10);
		List<Object[]> list = sql.list();
		List<ProNameDto> proNames = new ArrayList<ProNameDto>();
		for (Object[] objects : list) {
			ProNameDto p = new ProNameDto();
			p.setId(objects[0].toString());
			p.setProName(objects[1].toString());
			proNames.add(p);
		}
		return proNames;
	}

	@Override
	public List<ProjectItem> getProjectItemsByProId(UUID proId) {
		if (proId == null) {
			throw new NullPointerException("项目id为null");
		}
		List<ProjectItem> items = getBySql(ProjectItem.class,
				"select * from tb_project_item where pro_id=?",
				proId.toString());
		return items;
	}

	@Override
	public Project addProItem(ProjectItem item, UUID proId)
			throws DomainException {
		if (item == null) {
			throw new DomainException("未查询到指定的项目信息");
		}
		item.validate();

		// 检测重复时间
		boolean r = exists(new ProItemExistsDateSpecification(Project.class,
				item.getDate(), proId));
		if (r) {
			throw new DomainException("时间[" + item.getDate().toLocaleString()
					+ "]已经录入了项目月度信息，如要修改，请点击[管理月报]");
		}

		// 更新项目是否新启动
		Project pro = getByKey(Project.class, proId);
		// 更新项目累计赔付款项
		pro.updateMoney(item.getPaidMoney());
		if (item.getStartDate() != null) {
			pro.updateStartDate(item.getStartDate());
		}
		// 更新项目年度和累计数据
		pro.updateTotalData(item);
		update(pro);
		add(item);
		pro.toProTypeStr();
		return pro;
	}

	@Override
	public Project addAnnouncement(Announcement announcement) {
		announcement.validate();
		Announcement an = Announcement.obtain(announcement.getNumber(),//
				announcement.getDocPath(), announcement.getDate(), //
				announcement.getSequence(), announcement.getProId());

		// 更新项目公告进度
		Project pro = getByKey(Project.class, announcement.getProId());
		if (null == pro) {
			throw new NullPointerException("未查询到项目信息");
		}
		pro.setSequence(announcement.getSequence());
		pro.toAnnStr();
		pro.toProTypeStr();

		// 更新项目公告进度
		update(pro);

		// 持久化公告信息
		add(an);
		return pro;
	}

	@Override
	public void addProByFile(List<Project> list) throws DomainException {
		for (Project project : list) {
			// for (Announcement an : project.getAnnouncements()) {
			// an.setProId(project.getId());
			// addAnnouncement(an);
			// }
			saveOrUpdatePro(project);
		}
	}

	@Override
	public void addPro(Project model) throws DomainException {
		if (null == model) {
			throw new DomainException("新增的项目信息为空");
		}

		boolean r = exists(new ProExistsByNameSpecification(Project.class,
				model.getProName()));
		if (r) {
			throw new DomainException("项目名称[" + model.getProName() + "]已经存在");
		}

//		boolean r1 = exists(new ProExistsByApprovalNumSpecification(
//				Project.class, model.getApprovalNumber()));
//		if (r1) {
//			throw new DomainException("项目审批号[" + model.getApprovalNumber()
//					+ "]已经存在");
//		}

		add(model);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> exportByMonth(ProExportCondition condition) {
		List<ProjectItem> list = getAllByHqlJoin(new ProByMonthSpecification(
				ProjectItem.class, condition), -1, -1);
		if (null == list || list.size() == 0) {
			return null;
		}
		List<Project> pros = new ArrayList<>();
		for (ProjectItem projectItem : list) {
			Project pro = getByKey(Project.class, projectItem.getProId());
			if(null==pro){continue;}
			if(condition.getProCategory() != null && !condition.getProCategory().equals("")){
				if(pro.getCategoryStr() == null || !pro.getCategoryStr().equals(condition.getProCategory())){
					continue;
				}
			}
			pro.setAnnouncements(getAnnouncementsByProId(pro.getId()));
			pro.getItems().add(projectItem);
			pro.toProTypeStr();
			pros.add(pro);
		}
		return pros;
	}

	@Override
	public void editAnnouncement(Announcement model) {
		Announcement an = get(Announcement.class, model.getId());
		an.modify(model);
		update(an);
	}

	@Override
	public void editProItem(ProjectItem item) {
		ProjectItem entity = get(ProjectItem.class, item.getId());
		if (null == entity) {
			throw new NullPointerException("未查询到月度信息");
		}
		entity.modify(item);
		update(entity);
	}

	@Override
	public List<Project> getProjects4Print(String uuids) {
		List<Project> list = getAll(new ProQuery4PrintSpecification(
				Project.class, uuids));
		for (Project project : list) {
			List<Announcement> ans = getAnnouncementsByProId(project.getId());
			project.setAnnouncements(ans);
			List<ProjectItem> items = getProjectItemsByProId(project.getId());
			project.setItems(items);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public UUID getIdByName(String name) {
		Session session = hbContext().session();
		SQLQuery query = session
				.createSQLQuery("select id from tb_project where pro_name=?");
		query.setParameter(0, name);
		List<Object> id = query.list();
		if (id != null && id.size() > 0) {
			return UUID.fromString(id.get(0).toString());
		}
		return null;

	}

	@SuppressWarnings("unchecked")
	private void saveOrUpdateProItem(final ProjectItem proItem)
			throws DomainException {
		SpecificationExt<ProjectItem> sp = new SpecificationExt<ProjectItem>(
				ProjectItem.class) {

			@Override
			public String getAbsHql() {
				return "from ProjectItem where date=? and proId=?";
			}

			@Override
			public String getAbsSql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[] { proItem.getDate(), proItem.getProId() };
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.HQL;
			}

		};
		List<ProjectItem> items = getAllByHqlJoin(sp, -1, -1);
		// 如果未查询到指定项目id与项目月度填报日期的记录，则新增项目月度信息
		if (null == items || items.size() == 0) {
			add(proItem);
			return;
		}

		// 如果查询到指定项目和月度填报时间的记录，则覆盖月度数据
		ProjectItem item = items.get(0);
		item.modify(proItem);
		// 更新月度信息
		update(item);
	}

	private void saveOrUpdatePro(Project pro) throws DomainException {
		List<Project> ps = getAll(new ProQueryByNameSpecification(Project.class, pro.getProName()));
		
		// 如果未查询到项目信息，视为新增
		if (null == ps || ps.size()==0) {
			add(pro);
			for (ProjectItem item : pro.getItems()) {
				item.setProId(pro.getId());
				add(item);
			}
//			for (Announcement ann : pro.getAnnouncements()) {
//				ann.setId(UUID.randomUUID());
//				ann.setProId(pro.getId());
//				add(ann);
//			}
		} 
		else {
			// 如果不为空，则更新
			ps.get(0).modify4Import(pro);
			update(ps.get(0));
			for (ProjectItem item : pro.getItems()) {
				item.setProId(ps.get(0).getId());
				saveOrUpdateProItem(item);
			}
//			for (Announcement ann : pro.getAnnouncements()) {				
//				saveOrUpdateAnnouncement(pro.getAnnouncements(), ps.get(0).getId());
//			}
		}

	}

//	@SuppressWarnings("unchecked")
//	private void saveOrUpdateAnnouncement(List<Announcement> list, UUID proId) {
//		if (null == list || list.size() == 0) {
//			return;
//		}
//		for (Announcement announcement : list) {
//			List<Announcement> anns = getAllByHqlJoin(new AnnQueryBySequenceAndPIdSpecification(Announcement.class, announcement.getSequence(),proId), -1, -1);
//			if(anns==null || anns.size()==0){
//				announcement.setId(UUID.randomUUID());
//				announcement.setProId(proId);
//				add(announcement);
//			}
//			else {
//				anns.get(0).modify(announcement);
//			}
//		}
//	}

}
