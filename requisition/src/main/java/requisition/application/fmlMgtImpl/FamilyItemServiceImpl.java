package com.tq.requisition.application.fmlMgtImpl;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.application.BaseApplication;
import com.tq.requisition.autoMapper.FamilyItemMapper;
import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.Specification.Specification;
import com.tq.requisition.domain.Specification.expression.IHqlExpression;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.domain.model.familyMember.IFamilyItemRepository;
import com.tq.requisition.domain.model.removeFamily.Family;
import com.tq.requisition.domain.model.removeFamily.IFamilyRepository;
import com.tq.requisition.domain.model.removedInfo.IRemovedInfoRepository;
import com.tq.requisition.infrastructure.Specifications.Expression.HqlExpression;
import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemDto;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.serviceContract.rmHousehold.IFamilyItemServiceContract;

public class FamilyItemServiceImpl extends BaseApplication implements IFamilyItemServiceContract{
	private IFamilyItemRepository itemRepository;
	private IFamilyRepository fmlRepository;
	private IRemovedInfoRepository removedInfoRepository;
	
	public FamilyItemServiceImpl(//
			IRepositoryContext context,//
			IFamilyItemRepository itemRepository ,
			IRemovedInfoRepository removedInfoRepository,
			IFamilyRepository fmlRepository) {
		super(context);
		this.itemRepository = itemRepository;
		this.itemRepository.setAggregatorRootClass(FamilyItem.class);
		this.removedInfoRepository = removedInfoRepository;
		this.fmlRepository = fmlRepository;
	}

	@Override
	public String queryFamilyItemsByFuzzy(FamilyItemQueryModel queryModel,
			PageModel pageModel) {
		try {
			return toJsonByPage(queryFamilyItemListByFuzzy(queryModel, pageModel), "成功", Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public PageFormater queryFamilyItemListByFuzzy(
			FamilyItemQueryModel queryModel, PageModel pageModel) {
		PageFormater page = itemRepository.queryByFuzzy(queryModel, pageModel);
		return page;
	}

	@Override
	public String editFmlItem(FamilyItemDto item) {
		FamilyItem i = FamilyItemMapper.toModel(item);
		item.setId(item.getId());
		i.setId(item.getId());
		try {
			context().beginTransaction();
			FamilyItem fmlItem = itemRepository.editFamilyItem(i);
			context().commit();
			return toJson("编辑成功", fmlItem, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			context().rollback();
			return toJson("编辑失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().close();
		}
	}

	@Override
	public String deleteFmlItem(UUID id) {
		try {
			final FamilyItem item = itemRepository.getByKey(FamilyItem.class, id);
			int countSol = itemRepository.getTotalCount(new Specification<FamilyItem>(FamilyItem.class) {
				@Override
				public IHqlExpression getHqlExpression() {
					IHqlExpression expression = new HqlExpression();
					String sqlStr = "select count(1) from tb_socialsecurity_info where fml_item_id = '" + item.getId().toString() + "'";
					expression.setSql(sqlStr);
					expression.setType(OperationType.SQL);
					return expression;
				}
			});
			int countHpt = itemRepository.getTotalCount(new Specification<FamilyItem>(FamilyItem.class) {
				@Override
				public IHqlExpression getHqlExpression() {
					IHqlExpression expression = new HqlExpression();
					String sqlStr = "select count(1) from tb_housepurchase_ticket where fml_item_id='" + item.getId().toString() + "'";
					expression.setSql(sqlStr);
					expression.setType(OperationType.SQL);
					return expression;
				}
			});
			if(countSol > 0 || countHpt > 0){
				return toJson("失败-人员有购房券或社保信息关联", null, Formater.OperationResult.FAIL);
			}
			Family family = fmlRepository.getByKey(Family.class, item.getFmlId());
			context().beginTransaction();
			itemRepository.remove(item);
			removedInfoRepository.deleteByIdNum(item.getIdNumber());
			family.setFmlNumber(family.getFmlNumber() - 1);
			fmlRepository.editFamily(family);
			context().commit();
			return toJson("成功", item, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			context().rollback();
			return toJson("失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}
	
	@Override
	public String queryByIdNumber(String idNumber) {
		try {
			FamilyItem item = itemRepository.queryByIdNumber(idNumber);
			return toJson("成功", item, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String queryByFmlId(UUID id) {
		try {
			List<FamilyItem> list = itemRepository.queryItemsByFmlId(id);
			return toJson("成功", FamilyItemMapper.toDtoList(list), Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}
	
	@Override
	public String addFmlItem(FamilyItemDto dto) {
		try {
			context().beginTransaction();
			FamilyItem item = itemRepository.addFamilyItem(FamilyItemMapper.toModel(dto));
			context().commit();
			return toJson("成功", item, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			context().rollback();
			return toJson("失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().close();
		}
	}
	
	@Override
	public String queryByIdNumberAndName(String idNumber, String name) {
		try {
			FamilyItem item = itemRepository.queryByIdNumberAndName(idNumber, name);
			return toJson("查询人员信息成功", FamilyItemMapper.toDto(item), Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("查询人员信息失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}
	
}
