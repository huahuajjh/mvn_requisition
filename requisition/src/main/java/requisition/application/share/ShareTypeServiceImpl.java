package com.tq.requisition.application.share;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.tq.requisition.application.BaseApplication;
import com.tq.requisition.autoMapper.HouseholdTypeMapper;
import com.tq.requisition.autoMapper.RelationshipTypeMapper;
import com.tq.requisition.autoMapper.SocialsecurityTypeMapper;
import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.model.householdType.HouseholdType;
import com.tq.requisition.domain.model.householdType.IHouseholdTypeRepository;
import com.tq.requisition.domain.model.relationshipType.IRelationshipTypeRepository;
import com.tq.requisition.domain.model.relationshipType.RelationshipType;
import com.tq.requisition.domain.model.socialsecurityType.ISocialsecurityTypeRepository;
import com.tq.requisition.domain.model.socialsecurityType.SocialsecurityType;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.presentation.dto.share.HouseholdTypeDto;
import com.tq.requisition.presentation.dto.share.RelationshipTypeDto;
import com.tq.requisition.presentation.dto.share.SocialsecurityTypeDto;
import com.tq.requisition.presentation.serviceContract.share.IShareTypeServiceContract;

public class ShareTypeServiceImpl extends BaseApplication implements
		IShareTypeServiceContract {
	private IHouseholdTypeRepository householdTypeRepository;
	private IRelationshipTypeRepository relationshipTypeRepository;
	private ISocialsecurityTypeRepository socialsecurityTypeRepository;

	public ShareTypeServiceImpl(//
			IRepositoryContext context,//
			IHouseholdTypeRepository householdTypeRepository,//
			IRelationshipTypeRepository relationshipTypeRepository,//
			ISocialsecurityTypeRepository socialsecurityTypeRepository) {
		super(context);
		this.householdTypeRepository = householdTypeRepository;
		this.householdTypeRepository
				.setAggregatorRootClass(HouseholdType.class);

		this.relationshipTypeRepository = relationshipTypeRepository;
		this.relationshipTypeRepository
				.setAggregatorRootClass(RelationshipType.class);

		this.socialsecurityTypeRepository = socialsecurityTypeRepository;
		this.socialsecurityTypeRepository
				.setAggregatorRootClass(SocialsecurityType.class);
	}

	@Override
	public String getAllHouseholdType() {
		try {
			return toJson("成功", getAllHouseholdTypeList(),
					Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("获取户口类型失败-" + e.getMessage(), null,
					Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String getAllRelationshipType() {
		try {
			return toJson("成功", getAllRelationshipTypeList(),
					Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("获取户主关系数据失败-" + e.getMessage(), null,
					Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String getAllSocialsecurityType() {
		try {
			return toJson("成功", getAllSocialsecurityTypeList(),
					Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("获取社保类型数据失败-" + e.getMessage(), null,
					Formater.OperationResult.FAIL);
		}
	}

	@Override
	public List<HouseholdTypeDto> getAllHouseholdTypeList() {
		List<HouseholdType> list = householdTypeRepository.getAllType();
		return HouseholdTypeMapper.toDtoList(list);
	}

	@Override
	public List<RelationshipTypeDto> getAllRelationshipTypeList() {
		List<RelationshipType> list = relationshipTypeRepository.getAllType();
		//排序顺序把“其他这个类型拍到最后”  by:Bless time:2016/3/5 16:43
		List<RelationshipType> types = new ArrayList<RelationshipType>();
		for (RelationshipType item : list) {
			if(item.getName().equals("其他")){
				types.add(item);
			}
		}
		for (RelationshipType item : types) {
			list.remove(item);
		}
		list.addAll(types);
		//---------------------------------
		return RelationshipTypeMapper.toDtoList(list);
	}

	@Override
	public List<SocialsecurityTypeDto> getAllSocialsecurityTypeList() {
		List<SocialsecurityType> list = socialsecurityTypeRepository
				.getAllType();
		return SocialsecurityTypeMapper.toDtoList(list);
	}
	
	@Override
	public String delSSType(UUID id) {
		try {
			context().beginTransaction();
			socialsecurityTypeRepository.removeByKey(SocialsecurityType.class, id);
			context().commit();
			return toJson("删除数据成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("删除数据失败-"+ e.getMessage(),null, Formater.OperationResult.FAIL);
		}
	}
	
	@Override
	public String delRelationshipType(UUID id) {
		try {
			context().beginTransaction();
			relationshipTypeRepository.removeByKey(RelationshipType.class, id);
			context().commit();
			return toJson("删除数据成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("删除数据失败-"+ e.getMessage(),null, Formater.OperationResult.FAIL);
		}
	}
	
	@Override
	public String delHouseholdType(UUID id) {
		try {
			context().beginTransaction();
			householdTypeRepository.removeByKey(HouseholdType.class, id);
			context().commit();
			return toJson("删除数据成功", null, Formater.OperationResult.SUCCESS);
		} catch (Exception e) {
			return toJson("删除数据失败-"+ e.getMessage(),null, Formater.OperationResult.FAIL);
		}
	}
	
	@Override
	public String editSSType(UUID id, String name) {
		try {
			context().beginTransaction();
			socialsecurityTypeRepository.editType(id, name);
			context().commit();
			return toJson("编辑数据成功", null, Formater.OperationResult.SUCCESS);
		} catch (DomainException e) {
			return toJson("编辑数据失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}
	
	@Override
	public String editRelationshipType(UUID id, String name) {
		try {
			context().beginTransaction();
			relationshipTypeRepository.editType(id, name);
			context().commit();
			return toJson("编辑数据成功", null, Formater.OperationResult.SUCCESS);
		} catch (DomainException e) {
			return toJson("编辑数据失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}
	
	@Override
	public String editHouseholdType(UUID id, String name) {
		try {
			context().beginTransaction();
			householdTypeRepository.editType(id, name);
			context().commit();
			return toJson("编辑数据成功", null, Formater.OperationResult.SUCCESS);
		} catch (DomainException e) {
			return toJson("编辑数据失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String addSSType(String name) {
		SocialsecurityType entity = new SocialsecurityType(null,name);
		try {
			context().beginTransaction();
			socialsecurityTypeRepository.addType(entity);
			context().commit();
			return toJson("新增数据成功", entity, Formater.OperationResult.SUCCESS);
		} catch (DomainException e) {
			return toJson("新增数据失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String addHouseholdType(String name) {
		HouseholdType entity = new HouseholdType(null,name);
		try {
			context().beginTransaction();
			householdTypeRepository.addHouseholdType(entity);
			context().commit();
			return toJson("新增数据成功", entity, Formater.OperationResult.SUCCESS);
		} catch (DomainException e) {
			return toJson("新增数据失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

	@Override
	public String addRelationshipType(String name) {
		RelationshipType entity = new RelationshipType(null,name);
		try {
			context().beginTransaction();
			relationshipTypeRepository.addType(entity);
			context().commit();
			return toJson("新增数据成功", entity, Formater.OperationResult.SUCCESS);
		} catch (DomainException e) {
			return toJson("新增数据失败-"+e.getMessage(), null, Formater.OperationResult.FAIL);
		}
	}

}
