package com.tq.requisition.presentation.actions;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.Cookie;

import com.tq.requisition.infrastructure.log.LoggerFactory;
import com.tq.requisition.infrastructure.utils.ConfigFileUtil;
import com.tq.requisition.infrastructure.utils.TimeStamp;
import com.tq.requisition.presentation.Interceptor.LoginInterceptor;
import com.tq.requisition.presentation.dto.sysMgt.AccountSafeDto;
import com.tq.requisition.presentation.serviceContract.sysManagement.IGetResService;
import com.tq.requisition.presentation.serviceContract.userAssociated.IUserService;

@SuppressWarnings("serial")
public class Index extends BaseAction {
	private String username;
	private String password;
	private String oldPassword;
	private String newPassword;
	private IUserService userService;
	private IGetResService getResService;
	
	private  AccountSafeDto userInfo;
	
	public Index() {
		userService = getService("userService", IUserService.class);
		getResService = getService("getResService", IGetResService.class);
	}
		
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public AccountSafeDto getUserInfo() {
		return userInfo;
	}

	@Override
	public String execute() throws Exception {
		userInfo = user();
		return SUCCESS;
	}

	public String login() throws IOException {
			String cookPath = ConfigFileUtil.readByKey("cookiesPath");
			int outTime = Integer.valueOf(ConfigFileUtil.readByKey("loginOutTime"));
			
			AccountSafeDto accountDto = userService.login4session(username, password);
			if(null == accountDto){
				response().getWriter().write(ERROR);
				return null;
			}
			Cookie cookie = new Cookie(LoginInterceptor.LOGIN, accountDto.getId().toString());
			cookie.setPath(cookPath);
			response().addCookie(cookie);
			String name = URLEncoder.encode(accountDto.getName(),"UTF-8");
			Cookie nameCookie = new Cookie(LoginInterceptor.LOGINNAME, name);
			nameCookie.setPath(cookPath);
			response().addCookie(nameCookie);
			
			Calendar nowTime = Calendar.getInstance();
			nowTime.setTime(new Date());
			nowTime.add(Calendar.MINUTE, outTime);
			
			Cookie timeCookie = new Cookie(LoginInterceptor.TIME, TimeStamp.timeStamp(nowTime.getTime()));
			timeCookie.setPath(cookPath);
			response().addCookie(timeCookie);
			response().getWriter().write(SUCCESS);
			return null;
	}

	public String editPassword() throws IOException{
		String jsonState = userService.changePwd(userId(), oldPassword, newPassword);
		response().getWriter().write(jsonState);
		return null;
	}
	
	public String getRes() throws IOException {
		String jsonData = this.getResService.getResByUserIdJson(userId());
		response().getWriter().write(jsonData);
		return null;
	}
}
