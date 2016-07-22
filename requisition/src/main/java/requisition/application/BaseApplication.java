package com.tq.requisition.application;

import com.tq.requisition.domain.IRepository.IRepositoryContext;
import com.tq.requisition.infrastructure.utils.Formater;
import com.tq.requisition.infrastructure.utils.PageFormater;
import com.tq.requisition.infrastructure.utils.Serialization;

/**
 * 应用层父类，提供仓储上下文
 * @author jjh
 * @version 1.0
 * @time 2015-12-24 17:32
 *
 */
public abstract class BaseApplication {
	private IRepositoryContext context;

	public BaseApplication(IRepositoryContext context) {
		super();
		this.context = context;
	}
	
	/**
	 * 获取统一上下文
	 * @return IRepositoryContext
	 * 		仓储上下文
	 */
	protected IRepositoryContext context(){
		return context;
	}

	/**
	 * 格式化
	 * @param msg
	 * 		消息
	 * @param data
	 * 		数据
	 * @param type
	 * 		操作结果类型
	 * @return
	 */
	private Formater formater(String msg,Object data,Formater.OperationResult type) {
		return Formater.obtain(msg,data,type);
	}
	
	protected String toJson(String msg,Object data,Formater.OperationResult type) {
		return toJson(formater(msg, data, type));
	}
	
	/**
	 * 序列化操作封装
	 * @param obj
	 * 		待序列化的对象
	 * @return
	 * 		序列化后的json数据
	 */
	protected String toJson(Object obj) {
		if(obj == null)
		{
			throw new NullPointerException("待序列化的对象为null");
		}
		return Serialization.toJson(obj);
	}
		
	/**
	 * 返回分页模型json数据
	 * @param data
	 * 		装填的数据
	 * @param totalCount
	 * 		总行数
	 * @param msg
	 * 		消息
	 * @param type
	 * 		操作结果类型
	 * @return String
	 * 		json{PageFormater}
	 */
	protected String toJsonByPage(Object data,int totalCount,String msg,Formater.OperationResult type)
	{
		PageFormater pageData = PageFormater.obtain(data, totalCount);
		return toJson(msg, pageData, type);
	}

	protected String toJsonByPage(PageFormater page,String msg,Formater.OperationResult type) {
		return toJson(msg,page,type);
	}
}
