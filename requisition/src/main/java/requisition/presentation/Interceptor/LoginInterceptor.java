package com.tq.requisition.presentation.Interceptor;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.tq.requisition.infrastructure.serviceLocator.ServiceLocator;
import com.tq.requisition.infrastructure.utils.ConfigFileUtil;
import com.tq.requisition.infrastructure.utils.TimeStamp;
import com.tq.requisition.presentation.dto.sysMgt.AccountSafeDto;
import com.tq.requisition.presentation.serviceContract.userAssociated.IUserService;

@SuppressWarnings("serial")
public class LoginInterceptor extends AbstractInterceptor {

	public final static String LOGIN = "login";
	public final static String LOGINNAME = "loginName";
	public final static String USER="loginUser";
	public final static String TIME="loginTime";
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext context = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			boolean state = false;
			boolean timeState = false;
			String val = "";
			Date time = null;
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(LOGIN)){
					state = true;
					val = cookie.getValue();
				} else if(cookie.getName().equals(TIME)){
					String timeVal = cookie.getValue();
					if(timeVal != null && !timeVal.equals("")){
						time = TimeStamp.timeStamp2Date(timeVal);
						if(time != null){
							timeState = true;
						}
					}
				}
			}
			Date nowDate = new Date(); 
			if(state && timeState && nowDate.before(time)){
				String cookPath = ConfigFileUtil.readByKey("cookiesPath");
				int outTime = Integer.valueOf(ConfigFileUtil.readByKey("loginOutTime"));
				
				Calendar nowTime = Calendar.getInstance();
				nowTime.setTime(new Date());
				nowTime.add(Calendar.MINUTE, outTime);
				Cookie timeCookie = new Cookie(LoginInterceptor.TIME, TimeStamp.timeStamp(nowTime.getTime()));
				timeCookie.setPath(cookPath);
				HttpServletResponse hsr = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
				hsr.addCookie(timeCookie);
				
				String adminId = ConfigFileUtil.readByKey("adminId");
				if(adminId.equals(val)){
					AccountSafeDto accountDto = new AccountSafeDto();
					accountDto.setAccount(ConfigFileUtil.readByKey("acc"));
					accountDto.setId(UUID.fromString(adminId));
					accountDto.setName("Admin");
					context.getSession().put(USER,accountDto);
					return invocation.invoke();
				}
				IUserService userService = ServiceLocator.instance().getService("userService", IUserService.class);
				UUID userId = UUID.fromString(val);
				AccountSafeDto accountDto = userService.getUserById(userId);
				if(accountDto != null){
					context.getSession().put(USER,accountDto);
					return invocation.invoke();
				}
			}
		}
		return "login";
	}

}
