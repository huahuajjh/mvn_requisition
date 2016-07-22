<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8" />
<meta name="renderer" content="webkit">
<title>登陆</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="assets/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" />
<link href="assets/css/style.css" rel="stylesheet" />
<style>
footer {
	display: none;
}
.logo {
	font-size: 20px;
	padding-top: 5px;
	padding-left: 60px;
	font-family: "微软雅黑";
	font-weight: 600;
}
@media (min-width: 768px) {
  .hidden-sm {
    display: none !important;
  }
}
</style>
<!--[if lt IE 9]>
        <script src="http://apps.bdimg.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
        <script src="http://apps.bdimg.com/libs/respond.js/1.4.2/respond.js"></script>
    <![endif]-->
</head>
<body>
	<form id="loginForm" class="container-fluid content" method="post" action="share/login.do">
		<div class="row">
			<!-- Main Page -->
			<div class="body-login">
				<div class="center-login">
					<div class="logo pull-left hidden-xs">征地拆迁安置信息管理系统</div>
					<div class="panel panel-login">
						<div class="panel-title-login text-right">
							<h2 class="title">
								<i class="fa fa-user"></i> 登陆
							</h2>
						</div>
						<div class="panel-body">
							<form method="post">
								<div class="form-group">
									<label>用户名</label>
									<div class="input-group input-group-icon">
										<input name="username" type="text" value="" id="username"
											placeholder="请输入用户名" class="form-control bk-noradius"
											maxlength="15" /> <span class="input-group-addon"> <span
											class="icon"> <i class="fa fa-user"></i>
										</span>
										</span>
									</div>
								</div>
								<div class="form-group">
									<label>密码</label>
									<div class="input-group input-group-icon">
										<input placeholder="请输入密码" value="" name="password" id="password"
											type="password" class="form-control bk-noradius"
											maxlength="15" /> <span class="input-group-addon"> <span
											class="icon"> <i class="fa fa-lock"></i>
										</span>
										</span>
									</div>
								</div>
								<br />
								<div class="row">
									<div class="col-sm-8">
										<div class="checkbox-custom checkbox-default bk-margin-bottom-10">
											<input id="RememberMe" type="checkbox"  />
											<label for="RememberMe">记住密码</label>
										</div>
									</div>
									<div class="col-sm-4 hidden-xs text-right">
										<button type="submit" class="btn btn-primary">登陆</button>
									</div>
									<div class="col-xs-12 hidden-sm">
										<button type="submit" class="btn btn-primary" style="width: 100%;">登陆</button>
									</div>
								</div>
								<br />
								<div class="text-with-hr">
									<span>Copyright © 征地安置管理系统</span>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<script src="assets/js/jquery.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-validation/jquery.validate.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-validation/messages_zh.min.js" type="text/javascript"></script>
	<script type="text/javascript">
		var userName = "loginUserName";
		var password = "loginPassword";
		var href = location.href;
		if(href.indexOf("#") > 0){
			href = href.substring(0, href.indexOf("#"));
		}
		if(href.indexOf("?") > 0){
			href = href.substring(0, href.indexOf("?"));
		}
		if(href.toLowerCase().indexOf("login") < 0){
			location.href = "login.jsp";
		}
		$("#username").val(getCookie(userName));
		var p = getCookie(password);
		if(p){
			$("#RememberMe").prop("checked",true);
			$("#password").val(p);			
		}
		$("#loginForm").validate(
				{
					rules : {
						username : {
							required : true,
							minlength : 3
						},
						password : {
							required : true,
							minlength : 5
						}
					},
					messages : {
						username : {
							required : "账号不能为空",
							minlength : "不能少于3个字符"
						},
						password : {
							required : "密码不能为空",
							minlength : "不能少于5个字符"
						}
					},
					submitHandler : function(f) {
						var form = f;
						var data = $(form).serialize();
						$("input,button",form).prop("disabled",true);
						$.ajax({
							  type: "POST",
							  url: $(form).attr("action"),
							  data:data,
							  dataType: "text",
							  success: function(data){
								  if (data == "success") {
										setCookie(userName,$("#username").val());
										if($("#RememberMe").prop("checked")){
											setCookie(password,$("#password").val());
										}else{
											delCookie(password);
										}
										location.href = "index.do";
									} else {
										$(form).validate().showErrors({
											"password" : "密码错误"
										});
									}
								  $("input,button",form).prop("disabled",false);
							  }, error:function(e){
								  $(form).validate().showErrors({
										"password" : "密码错误"
									});
								  $("input,button",form).prop("disabled",false);
							  }
						});
					}
				});
		
		function setCookie(name,value) { 
		    var Days = 30; 
		    var exp = new Date(); 
		    exp.setTime(exp.getTime() + Days*24*60*60*1000); 
		    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString(); 
		} 
		function getCookie(name) { 
		    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		 
		    if(arr=document.cookie.match(reg))
		 
		        return unescape(arr[2]); 
		    else 
		        return null; 
		} 
		function delCookie(name) 
		{ 
		    var exp = new Date(); 
		    exp.setTime(exp.getTime() - 1); 
		    var cval=getCookie(name); 
		    if(cval!=null) 
		        document.cookie= name + "="+cval+";expires="+exp.toGMTString(); 
		} 
	</script>
</body>
</html>