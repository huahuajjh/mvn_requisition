package com.tq.requisition.infrastructure.serviceLocator;

/*
 * @description
 * 		服务定位器接口，该接口定义了获取服务规则
 * @author jjh
 * @time 2015-12-14 23:22
 */
public interface IServiceLocator {
	/**
	 * @description
	 * 		根据服务类型参数，获取一个服务实例
	 * @param serviceType
	 * 		服务类型参数
	 * @return  TService
	 * 		服务类型
	 */
	<TService> TService getService(Class<TService> serviceType);
	
	/*
	 * @description
	 * 		根据服务类型参数，获取一个服务实例
	 * @param serviceType
	 * 		服务类型参数
	 * @param springId
	 * 		 配置bean节点id
	 * @return  TService
	 * 		服务类型
	 */
	<TService> TService getService(String springId,Class<TService> serviceType);

}
