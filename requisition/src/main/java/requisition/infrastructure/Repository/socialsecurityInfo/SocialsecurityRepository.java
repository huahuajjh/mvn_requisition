package com.tq.requisition.infrastructure.Repository.socialsecurityInfo;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.model.socialsecurity.ISocialsecurityRepository;
import com.tq.requisition.domain.model.socialsecurity.SocialsecurityInfo;
import com.tq.requisition.exception.SpecifiedObjectDoesNotExistsException;
import com.tq.requisition.infrastructure.Repository.HbRepository;
import com.tq.requisition.infrastructure.Specifications.socialsecurity.SSExistsByFmlItemIdSpecification;
import com.tq.requisition.infrastructure.Specifications.socialsecurity.SSQuery4AddCountSpecification;
import com.tq.requisition.infrastructure.Specifications.socialsecurity.SSQuery4AddSpecification;
import com.tq.requisition.infrastructure.Specifications.socialsecurity.SSQueryCountFuzzySpecification;
import com.tq.requisition.infrastructure.Specifications.socialsecurity.SSQueryFuzzySpecification;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.infrastructure.utils.PageHelper;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.dto.socialsecurityMgt.Socialsecurity4AddDto;
import com.tq.requisition.presentation.dto.socialsecurityMgt.SocialsecurityDto;
import com.tq.requisition.presentation.dto.socialsecurityMgt.SocialsecurityQueryModel;

/**
 * 社保信息仓储实现
 * @author jjh
 * @time 2015-12-31 20:38
 */
public class SocialsecurityRepository  extends HbRepository<SocialsecurityInfo> implements ISocialsecurityRepository{

	public SocialsecurityRepository(IRepositoryContext context) {
		super(context);
	}

	@Override
	public void addSS(SocialsecurityInfo ss) throws SpecifiedObjectDoesNotExistsException {
		if(null == ss)
		{
			throw new NullPointerException("新增的社保信息为空");
		}		
		//检测该用户是否有社保数据
		boolean r = exists(new SSExistsByFmlItemIdSpecification(SocialsecurityInfo.class, ss.getFmlItemId()));
		if(r)
		{
			throw new SpecifiedObjectDoesNotExistsException("已经添加过该用户的社保信息了");
		}
		add(ss);
	}

	@Override
	public void addBatch(List<SocialsecurityInfo> list) throws SpecifiedObjectDoesNotExistsException {
		if(null == list)
		{
			throw new NullPointerException("新增数据部分为空");
		}
		for (SocialsecurityInfo socialsecurityInfo : list) {
			addSS(socialsecurityInfo);
		}
	}

	@Override
	public void deleteSS(UUID... uuids) {
		if(null == uuids)
		{
			throw new NullPointerException("待删除的社保数据id为空");
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < uuids.length; i++) {
			if(i==uuids.length)
			{
				sb.append(",").append("'").append(uuids[i]).append("'");
			}
			sb.append("'").append(uuids[i]).append("'").append(",");
		}
		updateBySql("update tb_socialsecurity_info set is_del=? where id in(?)", true,sb.toString());		
	}

	@Override
	public void editSS(SocialsecurityInfo ss) throws SpecifiedObjectDoesNotExistsException {
		if(null == ss)
		{
			throw new NullPointerException("待修改的社保数据为空");
		}
		SocialsecurityInfo _s = getByKey(SocialsecurityInfo.class, ss.getId());
		if(null == _s)
		{
			throw new SpecifiedObjectDoesNotExistsException("未查询到待编辑的社保数据");
		}
		_s.modify(ss);
		update(_s);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageFormater query4TableByFuzzy(SocialsecurityQueryModel queryModel,
			PageModel pageModel) {

		int count = getTotalCount(new SSQueryCountFuzzySpecification(SocialsecurityInfo.class, queryModel));
		if(count==0){
			return PageFormater.obtain(null, count);
		}
		List<SocialsecurityDto> list = getAllByHqlJoin(//
				new SSQueryFuzzySpecification(SocialsecurityInfo.class, queryModel),//
				PageHelper.getPageIndex(pageModel.pageIndex, pageModel.pageSize), //
				pageModel.pageSize);

		return PageFormater.obtain(list, count);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageFormater query4AddByFuzzy(SocialsecurityQueryModel queryModel,
			PageModel pageModel) {

		int count = getTotalCount(new SSQuery4AddCountSpecification(SocialsecurityInfo.class, queryModel));

		List<Socialsecurity4AddDto> list = getAllByHqlJoin(new SSQuery4AddSpecification(SocialsecurityInfo.class, queryModel),//
				PageHelper.getPageIndex(pageModel.pageIndex, pageModel.pageSize), //
				pageModel.pageSize);

		return PageFormater.obtain(list, count);
	}

}
