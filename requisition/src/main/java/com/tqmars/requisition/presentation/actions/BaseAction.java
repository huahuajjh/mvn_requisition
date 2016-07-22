package com.tqmars.requisition.presentation.actions;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tqmars.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tqmars.requisition.infrastructure.utils.Formater;
import com.tqmars.requisition.infrastructure.utils.Formater.OperationResult;
import com.tqmars.requisition.infrastructure.utils.Serialization;
import com.tqmars.requisition.presentation.Interceptor.LoginInterceptor;
import com.tqmars.requisition.presentation.dto.sysMgt.AccountDto;
import com.tqmars.requisition.presentation.dto.sysMgt.AccountSafeDto;

@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport {
	private final ActionContext context = ActionContext.getContext();
	
	protected ActionContext context(){
		
		return context;
	}
	
	protected HttpServletRequest request() {
		return (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
	}
	
	protected HttpServletResponse response()
	{
		HttpServletResponse hsr = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
		hsr.setCharacterEncoding("UTF-8");
		return hsr;
	}
	
	protected <TServiceType> TServiceType getService(String springId,Class<TServiceType> serviceClass) {
		return ServiceLocator.instance().getService(springId, serviceClass);
	}
	
	protected AccountDto getAccountDto(){
		return null;
	}
	
	protected String toForMaterJson(OperationResult type,String msg,Object data){
		return Serialization.toJson(Formater.obtain(msg, data, type));
	}
	protected String toForMaterJson(OperationResult type,String msg){
		return Serialization.toJson(Formater.obtain(msg, null, type));
	}
	
	protected UUID userId(){
		return user().getId();
	}
	
	protected AccountSafeDto user(){
		return (AccountSafeDto)context.getSession().get(LoginInterceptor.USER);
	}
}
