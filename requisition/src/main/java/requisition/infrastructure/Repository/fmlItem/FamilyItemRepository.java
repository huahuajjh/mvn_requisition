package com.tq.requisition.infrastructure.Repository.fmlItem;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.domain.model.familyMember.IFamilyItemRepository;
import com.tq.requisition.exception.SpecifiedObjectDoesNotExistsException;
import com.tq.requisition.infrastructure.Repository.HbRepository;
import com.tq.requisition.infrastructure.Specifications.familyItem.FmlItemQueryFuzzySpecification;
import com.tq.requisition.infrastructure.Specifications.familyItem.FmlItemTotalItemCountSpecification;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemDto;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;

public class FamilyItemRepository extends HbRepository<FamilyItem> implements IFamilyItemRepository{

	public FamilyItemRepository(IRepositoryContext context) {
		super(context);
	}

	@Override
	public FamilyItem addFamilyItem(FamilyItem item) {
		add(item);
		return item;
	}
	
	@Override
	public PageFormater queryByFuzzy(FamilyItemQueryModel queryModel,
			PageModel pageModel) {
		int count = getTotalCount(new FmlItemTotalItemCountSpecification(FamilyItem.class, queryModel));
		if(count==0){
			return PageFormater.obtain(null, count);
		}
		
		List<FamilyItem> list = getAll(new FmlItemQueryFuzzySpecification(FamilyItem.class, queryModel, pageModel));
		return PageFormater.obtain(list, count);
	}

	@Override
	public FamilyItem editFamilyItem(FamilyItem item) {
		FamilyItem i = getByKey(FamilyItem.class, item.getId());
		if(null==i)
		{
			throw new NullPointerException("未查询到指定的拆迁户人员信息");
		}
		i.modify(item);
		update(i);
		return i;
	}

	@Override
	public void deleteFamilyItem(UUID id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public FamilyItem queryByIdNumber(final String idNumber) throws SpecifiedObjectDoesNotExistsException {
		List<FamilyItem> item = getAll(new SpecificationExt<FamilyItem>(FamilyItem.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_family_item where id_number=?";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{idNumber};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		if(item==null || item.size()<=0)
		{
			throw new SpecifiedObjectDoesNotExistsException("未查询到该身份证和姓名对应的记录");
		}
		return item.get(0);
	}
	
	@Override
	public boolean existsByIdNumAndName(final String idNumber, final String name) {
		boolean r = exists(new SpecificationExt<FamilyItem>(FamilyItem.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select count(1) from tb_family_item where id_number=? and name=?";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{idNumber,name};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
			
		});
		
		if(r)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public List<FamilyItem> queryItemsByFmlId(final UUID fmlId) {
		if(null==fmlId){throw new NullPointerException("拆迁户id为空");}
		List<FamilyItem> list = getAll(new SpecificationExt<FamilyItem>(FamilyItem.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_family_item where fml_id=?";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{fmlId.toString()};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		if(null==list||list.size()==0)
		{
			return null;
		}
		return list;
	}
	
	@Override
	public List<FamilyItem> queryItemsByProName(final String proName) {
		List<FamilyItem> list = getAll(new SpecificationExt<FamilyItem>(FamilyItem.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_family_item where pro_name=?";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{proName};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		if(null==list||list.size()==0)
		{
			return null;
		}
		return list;
	}
	
	@Override
	public FamilyItem queryByIdNumberAndName(final String idNumber,final String name) {
		List<FamilyItem> items = getAll(new SpecificationExt<FamilyItem>(FamilyItem.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_family_item where id_number=? and name=?";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{idNumber,name};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		if(items==null || items.size()==0)
		{
			throw new NullPointerException("未查询到对应的拆迁人员信息");
		}
		return items.get(0);
	}

	
	@Override
	public UUID getIdByIdNumber(String idnumber) {
		return getIdByUniqueCol("select id from tb_family_item where id_number=?", idnumber);
	}
	
}
