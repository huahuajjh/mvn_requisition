package com.tq.requisition.application.hpt;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.application.BaseApplication;
import com.tq.requisition.autoMapper.HPTExchangeInfoMapper;
import com.tq.requisition.autoMapper.HPTLossMapper;
import com.tq.requisition.autoMapper.HPTMapper;
import com.tq.requisition.autoMapper.HPTMendMapper;
import com.tq.requisition.autoMapper.HPTProvideMapper;
import com.tq.requisition.autoMapper.HPTUseMapper;
import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.model.familyMember.FamilyItem;
import com.tq.requisition.domain.model.familyMember.IFamilyItemRepository;
import com.tq.requisition.domain.model.housePuraseTicket.HousePuraseTicket;
import com.tq.requisition.domain.model.housePuraseTicket.IHPTRepository;
import com.tq.requisition.domain.service.idomainservice.IHPTService;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.exception.SpecifiedObjectDoesNotExistsException;
import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.presentation.dto.hpt.HPTDisplayDto;
import com.tq.requisition.presentation.dto.hpt.HPTDisplayFmlDto;
import com.tq.requisition.presentation.dto.hpt.HPTExchangeInfoDto;
import com.tq.requisition.presentation.dto.hpt.HPTFuzzyQueryModel;
import com.tq.requisition.presentation.dto.hpt.HPTImportAndExport;
import com.tq.requisition.presentation.dto.hpt.HPTLossInfoDto;
import com.tq.requisition.presentation.dto.hpt.HPTMendInfoDto;
import com.tq.requisition.presentation.dto.hpt.HPTQueryModel;
import com.tq.requisition.presentation.dto.hpt.HPTReceiveTableDto;
import com.tq.requisition.presentation.dto.hpt.HPTRecevieInfoDto;
import com.tq.requisition.presentation.dto.hpt.HPTUseAndCashInfoDto;
import com.tq.requisition.presentation.dto.hpt.HousePuraseTicketDto;
import com.tq.requisition.presentation.dto.hpt.HptUseAndCashQueryModel;
import com.tq.requisition.presentation.dto.share.PageModel;
import com.tq.requisition.presentation.serviceContract.hptMgt.IHPTMgtServiceContract;

/**
 * 购房券服务契约实现
 * @author jjh
 * @time 2015-01-02 20:57
 *
 */
public class HPTMgtServiceImpl extends BaseApplication implements IHPTMgtServiceContract{
	private IHPTRepository hptRepository;
	private IHPTService hptService;
	private IFamilyItemRepository itemRepository;

	public HPTMgtServiceImpl(//
			IRepositoryContext context,//
			IHPTRepository hptRepository,//
			IHPTService hptService,//
			IFamilyItemRepository itemRepository) {
		
		super(context);
		this.hptRepository = hptRepository;
		this.hptRepository.setAggregatorRootClass(HousePuraseTicket.class);
		
		this.itemRepository = itemRepository;
		this.itemRepository.setAggregatorRootClass(FamilyItem.class);
		
		this.hptService = hptService;	
	}

	@Override
	public String add(HousePuraseTicketDto dto) {
		try {
			context().beginTransaction();
			hptService.addHPT(HPTMapper.toModel(dto));
			context().commit();
			return toJson("新增购房券成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			context().rollback();
			return toJson("新增购房券失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().close();
		}
	}

	@Override
	public String add(List<HousePuraseTicketDto> dtos) {
		try {
			context().beginTransaction();
			hptService.addHPT(HPTMapper.toModelList(dtos));
			context().commit();
			return toJson("批量新增购房券成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			context().rollback();
			return toJson("批量新增购房券失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().close();
		}
	}

	@Override
	public String exchange(HPTExchangeInfoDto dto, HousePuraseTicketDto hpt) {
		try {
			context().beginTransaction();
			hptService.exchange(HPTExchangeInfoMapper.toModel(dto), HPTMapper.toModel(hpt));
			context().commit();
			return toJson("换券成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("换券失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String mend(HPTMendInfoDto mendDto, HousePuraseTicketDto hptDto) {
		try {
			context().beginTransaction();
			hptService.mend(HPTMendMapper.toModel(mendDto), HPTMapper.toModel(hptDto));
			context().commit();
			return toJson("补券成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("补券失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		
	}

	@Override
	public String use(HPTUseAndCashInfoDto dto) {
		try {
			context().beginTransaction();
			hptService.useOrCash(HPTUseMapper.toModel(dto));
			context().commit();
			return toJson("兑换成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("兑换失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String provide(HPTRecevieInfoDto dto) {
		try {
			context().beginTransaction();
			hptService.provider(HPTProvideMapper.toModel(dto));
			context().commit();
			return toJson("领取购房券成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("领取购房券失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String lossOfReport(HPTLossInfoDto dto) {
		try {
			context().beginTransaction();
			hptService.reportOfLoss(HPTLossMapper.toModel(dto));
			context().commit();
			return toJson("挂失操作成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			context().rollback();
			return toJson("挂失操作失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().close();
		}
	}

	@Override
	public String queryExchangeInfo(HPTQueryModel queryModel) {
		try {
			HPTDisplayDto dto = hptRepository.queryByIdnumberAndTicketNum(queryModel);
			return toJson("查询换券信息成功", dto, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("查询换券信息失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String queryByFml(String idNumber) {
		 try {
			 List<HPTDisplayFmlDto> list = hptService.queryByFml(idNumber);
			 return toJson("查询成功", list, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("查询失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		 
	}

	@Override
	public String queryNotByProId(String proName, PageModel pageModel) {
		 try {
			 PageFormater list = hptService.queryNotByPro(proName,pageModel);
			 return toJson("查询成功", list, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("查询失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}
	
	@Override
	public String queryByIdNumber(String idNumber) {
		try {
			List<HPTDisplayDto> dto = hptRepository.queryByIdnumber(idNumber);
			return toJson("查询成功", dto, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("查询失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String queryProvideTable(HPTFuzzyQueryModel queryModel,PageModel pageModel) {
		try {
			PageFormater page = hptRepository.queryProvideTable(queryModel, pageModel);
			return toJsonByPage(page, "查询发放台账成功", Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJsonByPage(null, "查询发放台账失败-"+e.getMessage(), Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String queryUseTable(HPTFuzzyQueryModel queryModel,PageModel pageModel) {
		try {
			if(queryModel.getHuIdNumber()!=null && !queryModel.getHuIdNumber().equals("")){
				try {
					FamilyItem item = itemRepository.queryByIdNumber(queryModel.getHuIdNumber());
					if(item != null && item.getFmlId() != null){
						queryModel.setHuFmlId(item.getFmlId());
					} else if(item != null){
						queryModel.setHuFmlId(item.getId());
					}					
				} catch (Exception e) {
					queryModel.setHuFmlId(UUID.randomUUID());
				}
			}
			PageFormater page = hptRepository.queryUseTable(queryModel, pageModel);
			return toJsonByPage(page, "查询兑付台账成功", Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJsonByPage(null, "查询兑付台账失败-"+e.getMessage(), Formater.OperationResult.FAIL);
		}
	}
	
	@Override
	public String provideByFml(List<HPTRecevieInfoDto> list) {
		try {
			context().beginTransaction();
			hptRepository.provideByFml(HPTProvideMapper.toModelList(list));
			context().commit();
			return toJson("发放成功", null, Formater.OperationResult.SUCCESS);
		} catch (SpecifiedObjectDoesNotExistsException e) {
			context().rollback();
			return toJson("发放失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
		finally{
			context().close();
		}
	}
	
	@Override
	public String importHPT(List<HPTImportAndExport> list) {
		if(null==list || list.size()==0){
			return toJson("待导入的列表为空", null, Formater.OperationResult.FAIL);
		}
		List<HPTImportAndExport>  notExistHPT = new ArrayList<HPTImportAndExport>();
		List<HPTImportAndExport>  errorHPT = new ArrayList<HPTImportAndExport>();
		List<HPTImportAndExport>  successHPT = new ArrayList<HPTImportAndExport>();
		try {
			context().beginTransaction();
			for (HPTImportAndExport hptImportAndExport : list) {
				FamilyItem fmlItem = null;
				try {
					fmlItem = itemRepository.queryByIdNumber(hptImportAndExport.getIdNumber());
				} catch (SpecifiedObjectDoesNotExistsException e) {
				}
				if(null==fmlItem){
					notExistHPT.add(hptImportAndExport);
					continue;
				}
				
				hptImportAndExport.setFmlItemId(fmlItem.getId());
				HousePuraseTicket hpt = hptImportAndExport.toHPT();
				
				try {
					hptService.addHPT(hpt);
					successHPT.add(hptImportAndExport);
				} catch (DomainException e) {
					errorHPT.add(hptImportAndExport);
				}
			}
			context().commit();
			return toJson("导入购房券信息<br>不存在的人员有："+notExistHPT.size() + "人<br>重复导入的购房券有：" + errorHPT.size() + "张<br>成功导入的购房券：" + successHPT.size() + "张", new Object[]{notExistHPT,errorHPT,successHPT}, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			context().rollback();
			return toJson("导入购房券信息失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}finally{
			context().close();
		}
	}

	@Override
	public String queryByPage4Json(HptUseAndCashQueryModel queryModel,
			PageModel pageModel) {
		return toJsonByPage(queryByPage4List(queryModel, pageModel), "查询成功", Formater.OperationResult.SUCCESS);
	}

	@Override
	public PageFormater queryByPage4List(HptUseAndCashQueryModel queryModel,
			PageModel pageModel) {
		try {
			if(queryModel.getIdNumber() != null && !queryModel.getIdNumber().equals("")){
				FamilyItem item = itemRepository.queryByIdNumber(queryModel.getIdNumber());
				if(item != null && item.getFmlId() != null){
					queryModel.setFmlItemId(item.getFmlId());
				} else if(item != null){
					queryModel.setFmlItemId(item.getId());
				}
			}
		} catch (SpecifiedObjectDoesNotExistsException e) {
			return PageFormater.obtain(null, 0);
		}
		return hptRepository.queryByPage(queryModel, pageModel);
	}

	@Override
	public String use(List<HPTUseAndCashInfoDto> dtos) {
		try {
			context().beginTransaction();
			for (HPTUseAndCashInfoDto dto : dtos) {
				hptService.useOrCash(HPTUseMapper.toModel(dto));
			}
			context().commit();
			return toJson("兑换成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("兑换失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public List<HPTReceiveTableDto> getHPTReceiveTableDtoAll(String proName) {
		return hptRepository.getHPTReceiveTableDtoAll(proName);
	}
}
