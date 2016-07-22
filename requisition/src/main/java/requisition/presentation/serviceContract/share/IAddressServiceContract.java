package com.tq.requisition.presentation.serviceContract.share;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.presentation.dto.share.AddressDto;

public interface IAddressServiceContract {
	/**
	 * 连动获取地址信息，如果nodeId为null，标示获取所有根节点
	 * json{}
	 * @param nodeId
	 * 		节点id
	 * @return
	 */
	String getAddress(UUID nodeId);
	
	/**
	 * 获取地址集合信息
	 * @return
	 */
	List<AddressDto> getAddress();
	
	/**
	 * 新增地址节点
	 * @param ads
	 * 		地址节点实体
	 * @return
	 */
	String addNewAddress(AddressDto ads);
	
	/**
	 * 编辑地址信息
	 * @param ads
	 * 		待更新的地址dto对象
	 * @return
	 */
	String editAddress(AddressDto ads);
	
	/**
	 * 根据id删除地址节点数据
	 * @param id
	 * 		待删除地址节点的id
	 * @return
	 */
	String deleteAddress(UUID... id);
	
	/**
	 * 获取所有地址信息
	 * @return AddressDto
	 * 		json
	 */
	String getAllAddresses();
}
