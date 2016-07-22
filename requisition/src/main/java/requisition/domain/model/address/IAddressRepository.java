package com.tq.requisition.domain.model.address;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.domain.IRepository.IRepository;
import com.tq.requisition.exception.DomainException;

/**
 * 連動地址倉儲藉口
 * @author jjh
 * @time 2015-12-27 23：20
 */
public interface IAddressRepository extends IRepository<Address>{
	/**
	 * 根据父id获取子节点地址集合
	 * @param pid
	 * 		父id
	 * @return List<Address>
	 * 		子节点集合
	 */
	List<Address> getChildrenAddressByPid(UUID pid);
	
	/**
	 * 获取所有顶级地址节点
	 * @return List<Address>
	 * 		顶级地址节点集合数据
	 */
	List<Address> getTopAddresses();
	
	/**
	 * 删除指定id的地址
	 * @param id
	 * 		地址id
	 */
	void deleteAddressById(UUID... ids);
	
	/**
	 * 修改地址
	 * @param address
	 * 		地址实体
	 */
	void editAddress(Address address);
	
	/**
	 * 新增地址信息
	 * @param address
	 * 		待新增的地址信息实体
	 * @return Address
	 * 		返回新增后的地址信息实体
	 */
	Address addAddress(Address address) throws DomainException;	
	
	/**
	 * 获取所有地址列表
	 * @return List<Address>
	 * 		地址集合
	 */
	List<Address> getAllAddresses();
}
