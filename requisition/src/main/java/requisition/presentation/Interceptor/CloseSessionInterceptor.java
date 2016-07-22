package com.tq.requisition.presentation.Interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tq.requisition.infrastructure.Repository.HibernateUtils;

@SuppressWarnings("serial")
public class CloseSessionInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HibernateUtils.closeSession();
		return invocation.invoke();
	}

}