package com.tq.requisition.application.hpt;

import com.tq.requisition.application.BaseApplication;
import com.tq.requisition.autoMapper.FamilyItemMapper;
import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.domain.model.familyMember.IFamilyItemRepository;
import com.tq.requisition.domain.model.housePuraseTicket.IHPTRepository;
import com.tq.requisition.domain.model.removeFamily.Family;
import com.tq.requisition.domain.model.removeFamily.IFamilyRepository;
import com.tq.requisition.infrastructure.Repository.removeFamily.FamilyRepository;
import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.presentation.dto.hpt.PersonAndHPTDto;
import com.tq.requisition.presentation.dto.rmHousehold.FamilyItemDto;
import com.tq.requisition.presentation.serviceContract.hptMgt.IHPTMgtFmlItemServiceContract;

public class HPTMgtFmlItemServiceImpl extends BaseApplication implements IHPTMgtFmlItemServiceContract{

	private IHPTRepository htpRepository;
	private IFamilyItemRepository familyItemRepository;
	private IFamilyRepository fmlRepository;
	
	public HPTMgtFmlItemServiceImpl(IRepositoryContext context,
			IHPTRepository htpRepository,
			IFamilyItemRepository familyItemRepository,
			IFamilyRepository fmlRepository) {
		super(context);
		this.htpRepository = htpRepository;
		this.familyItemRepository = familyItemRepository;
		this.fmlRepository = fmlRepository;
	}

	@Override
	public String add(PersonAndHPTDto personAndHPTDto) {
		try {
			context().beginTransaction();
			FamilyItem item = personAndHPTDto.toFamilyItem();
			familyItemRepository.addFamilyItem(item);
			Family family = fmlRepository.getByKey(Family.class, item.getFmlId());
			family.setFmlNumber(family.getFmlNumber() + 1);
			fmlRepository.editFamily(family);
			htpRepository.addHPT(personAndHPTDto.toHousePuraseTicket(item));
			context().commit();
			return toJson("新增人员以及购房券成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			context().rollback();
			return toJson("新增人员以及购房券失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().close();
		}
	}

	@Override
	public String add(FamilyItemDto dto) {
		try {
			context().beginTransaction();
			FamilyItem item = familyItemRepository.addFamilyItem(FamilyItemMapper.toModel(dto));
			Family family = fmlRepository.getByKey(Family.class, item.getFmlId());
			family.setFmlNumber(family.getFmlNumber() + 1);
			fmlRepository.editFamily(family);
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

}
