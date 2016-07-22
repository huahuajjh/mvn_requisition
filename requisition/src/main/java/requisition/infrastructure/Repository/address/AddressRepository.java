package com.tq.requisition.infrastructure.Repository.address;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.domain.Specification.SpecificationExt;
import com.tq.requisition.domain.Specification.expression.OperationType;
import com.tq.requisition.domain.model.address.Address;
import com.tq.requisition.domain.model.address.IAddressRepository;
import com.tq.requisition.exception.DataAlreadyExistsException;
import com.tq.requisition.exception.DomainException;
import com.tq.requisition.infrastructure.Repository.HbRepository;
import com.tq.requisition.infrastructure.Specifications.address.AddressNameExistsSpecification;

/**
 * 地址仓储实现类
 * @author jjh
 * @time 2015-12-27 23:23
 */
public class AddressRepository extends HbRepository<Address>implements IAddressRepository{
	public AddressRepository(IRepositoryContext context) {
		super(context);
	}

	@Override
	public List<Address> getChildrenAddressByPid(final UUID pid) {
		List<Address> list;
		if(pid == null)
		{
			return getTopAddresses();
		}
		list = getAll(new SpecificationExt<Address>(Address.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_address where pid=?";
			}

			@Override
			public Object[] getAbsParameters() {
				return new Object[]{pid.toString()};
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		return list;		
	}
	
	@Override
	public List<Address> getTopAddresses() {
		List<Address> list = getAll(new SpecificationExt<Address>(Address.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_address where pid is null";
			}

			@Override
			public Object[] getAbsParameters() {
				return null;
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		return list;
	}
	
	@Override
	public void deleteAddressById(UUID... ids) {
		if(ids == null)
		{
			throw new NullPointerException("地址id为null");
		}
		for (UUID uuid : ids) {
			removeByKey(Address.class, uuid);
		}		
	}
	
	@Override
	public void editAddress(Address address) {
		if(address==null)
		{
			throw new NullPointerException("待修改的地址信息为空");
		}
		address = getByKey(Address.class, address.getId());
		if(address==null)
		{
			throw new NullPointerException("没有这条地址信息,无法修改");
		}
		update(address);
	}

	@Override
	public Address addAddress(final Address address) throws DomainException {
		boolean r = exists(new AddressNameExistsSpecification(Address.class, address.getTitle(), address.getPid()));
		if(r)
		{
			throw new DataAlreadyExistsException("地址名称["+address.getTitle()+"]已经存在");
		}
		add(address);
		return address;
	}
	
	@Override
	public List<Address> getAllAddresses() {
		List<Address> list = getAll(new SpecificationExt<Address>(Address.class) {

			@Override
			public String getAbsHql() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String getAbsSql() {
				return "select * from tb_address";
			}

			@Override
			public Object[] getAbsParameters() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public OperationType getAbsType() {
				return OperationType.SQL;
			}
		});
		return list;	
	}

}
