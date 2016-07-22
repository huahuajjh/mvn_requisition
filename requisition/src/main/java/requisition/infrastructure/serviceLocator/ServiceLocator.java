package com.tq.requisition.infrastructure.serviceLocator;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.tq.requisition.infrastructure.log.LoggerFactory;

/*
 * @description
 * 		服务定位器
 * @author jjh
 * @time 2015-12-14 23:42
 */
public final class ServiceLocator implements IServiceLocator {
	/* fields */
	private static ServiceLocator instance;
	private static ApplicationContext springContext;

	static {
//		String path = ServiceLocator.class.getResource("").getPath();
//		path = path.substring(1,path.indexOf("classes/"));
//		path+="config/spring.xml";
//		try {
//			springContext = new FileSystemXmlApplicationContext(path);
//			instance = new ServiceLocator();
//		} catch (Exception e) {
//			LoggerFactory.logger().error(ServiceLocator.class.getName(), "static block", e.getMessage());
//		}
		try {
			springContext = new ClassPathXmlApplicationContext(
					"../config/spring.xml");
		} catch (BeansException e) {
			// 如果web读取配置失败，则采用文件系统读取
			springContext = new FileSystemXmlApplicationContext(
					"WebRoot/WEB-INF/config/spring.xml");
		}

	}

	/*
	 * @description 获取服务定位器实例
	 * 
	 * @return IServiceLocator 返回一个服务定位器接口类型
	 */
	public static IServiceLocator instance() {
		if(null==instance){
			return new ServiceLocator();
		}
		return instance;
	}

	/* ctors */
	private ServiceLocator() {
	}

	/*
	 * @description 根据服务类型参数，获取一个服务实例
	 * 
	 * @param serviceType 服务类型参数
	 * 
	 * @return TService 服务类型
	 */
	@Override
	public <TService> TService getService(Class<TService> serviceType) {
		return springContext.getBean(serviceType);
	}

	/*
	 * @description 根据服务类型参数，获取一个服务实例
	 * 
	 * @param serviceType 服务类型参数
	 * 
	 * @param springId 配置bean节点id
	 * 
	 * @return TService 服务类型
	 */
	@Override
	public <TService> TService getService(String springId,
			Class<TService> serviceType) {
		try {
			return springContext.getBean(springId, serviceType);
		} catch (Exception e) {
			LoggerFactory.logger().error(ServiceLocator.class.getName(), "getService(String,Class<TService>)", e.getMessage());
			return null;
		}
	}

}
