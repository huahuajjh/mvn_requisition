package com.tqmars.requisition.presentation.Interceptor;

import com.tqmars.requisition.infrastructure.Repository.HibernateUtils;

@SuppressWarnings("serial")
public class CloseSessionInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HibernateUtils.closeSession();
		return invocation.invoke();
	}

}