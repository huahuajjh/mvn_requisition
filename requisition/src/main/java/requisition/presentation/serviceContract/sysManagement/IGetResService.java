package com.tq.requisition.presentation.serviceContract.sysManagement;

import java.util.List;
import java.util.UUID;

import com.tq.requisition.presentation.dto.sysMgt.ResDto;

/**
 * 获取资源服务
 * 
 * @author Bless
 * @version 1.0
 * @time 2015/12/24 15:51
 */
public interface IGetResService {
	/**
	 * 根据用户标识获取用户菜单资源
	 * 
	 * @param uId
	 *            用户标识
	 * @param hierarchy
	 *            层级
	 * @return 菜单资源
	 */
//	List<ResDto> getMenuResByUserId(UUID uId, int hierarchy);
	
	/**
	 * 根据用户标识获取用户菜单资源
	 * 
	 * @param uId
	 *            用户标识
	 * @param hierarchy
	 *            层级
	 * @return 菜单资源
	 */
//	String getMenuResByUserIdJSON(UUID uId, int hierarchy);

	/**
	 * 根据用户Id和父节点Id获取资源
	 * 
	 * @param uId
	 *            用户Id
	 * @param type
	 *            资源类型
	 * @param hierarchy
	 *            层级
	 * @return 资源数据
	 */
//	List<ResDto> getResByUserIdAndType(UUID uId, ResourceType type, int hierarchy);
	
	/**
	 * 根据用户Id和父节点Id获取资源
	 * 
	 * @param uId
	 *            用户Id
	 * @param type
	 *            资源类型
	 * @param hierarchy
	 *            层级
	 * @return 资源数据
	 */
//	String getResByUserIdAndTypeJSON(UUID uId,ResourceType type, int hierarchy);
	
	/**
	 * 根据用户id获取资源集合
	 * @param uid
	 * 		用户id
	 * @return List<ResDto>
	 * 		资源集合
	 */
	List<ResDto> getResByUserId(UUID uid);
	
	/**
	 * 根据用户id获取资源集合
	 * @param uid
	 * @return
	 */
	String getResByUserIdJson(UUID uid);
}
