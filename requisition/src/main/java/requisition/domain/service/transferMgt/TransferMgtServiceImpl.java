package com.tq.requisition.domain.service.transferMgt;

import java.util.List;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.domain.model.familyMember.IFamilyItemRepository;
import com.tq.requisition.domain.model.transferHouseholdInfo.ITransferInfoRepository;
import com.tq.requisition.domain.model.transferHouseholdInfo.TransferHouseholdInfo;
import com.tq.requisition.domain.service.BaseDomainService;
import com.tq.requisition.domain.service.idomainservice.ITransferMgtService;
import com.tq.requisition.exception.DomainException;

/**
 * 转户管理业务接口实现
 * @author jjh
 * @time 2015-12-30 19:09
 */
public class TransferMgtServiceImpl extends BaseDomainService implements ITransferMgtService{
	private ITransferInfoRepository transferInfoRepository;
	private IFamilyItemRepository itemRepository;
	
	public TransferMgtServiceImpl(IRepositoryContext context,//
			ITransferInfoRepository transferInfoRepository,//
			IFamilyItemRepository itemRepository) {
		super(context);
		this.transferInfoRepository = transferInfoRepository;
		this.transferInfoRepository.setAggregatorRootClass(TransferHouseholdInfo.class);
		
		this.itemRepository = itemRepository;
		this.itemRepository.setAggregatorRootClass(FamilyItem.class);
	}

	@Override
	public void addTransferInfo(TransferHouseholdInfo model) throws DomainException {
		transferInfoRepository.addTransferInfo(model);
		
		//更新拆迁人员转户信息
		FamilyItem item = itemRepository.getByKey(FamilyItem.class, model.getFmlItemId());
		
		//标记被拆迁人员转户状态
		if(null == item)
		{
			throw new NullPointerException("未查询到转户对应的拆迁人员信息");
		}
		item.setHouseholdId(model.getHouseHoldTypeId());
		item.setHouseholdStr(model.getHouseHoldTypeStr());
		item.markTransferState();
		itemRepository.update(item);				
	}

	@Override
	public void addBatchTransferInfo(List<TransferHouseholdInfo> list) throws DomainException {
		if(null == list || list.size()==0){throw new NullPointerException("新增的转户信息集合为空");}
		//持久化转户信息
		transferInfoRepository.addBatch(list);
		//更新拆迁人员信息
		for (TransferHouseholdInfo transferHouseholdInfo : list) {
			FamilyItem item = itemRepository.getByKey(FamilyItem.class, transferHouseholdInfo.getFmlItemId());
			if(null==item){throw new DomainException("未查询到转户信息");}
			item.transferHousehold(transferHouseholdInfo);
			itemRepository.update(item);
		}
	}

	@Override
	public void editBatchTransferInfo(List<TransferHouseholdInfo> list) {
		// TODO Auto-generated method stub
		
	}
	
}
