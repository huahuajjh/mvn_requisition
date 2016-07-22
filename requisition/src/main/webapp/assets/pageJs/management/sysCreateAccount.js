$("#personForm").validate({
	rules : {
		account : {
			required : true,
			minlength : 3,
			maxlength : 15
		},
		name : {
			required : true,
			maxlength : 5
		},
		orgId : {
			required : true
		},
		roleId : {
			required : true
		}
	},
	messages : {
		account : {
			required : "账号不能为空",
			minlength : "不能少于3个字符",
			maxlength : "账号格式不正确"
		},
		name : {
			required : "姓名不能为空",
			maxlength : "姓名格式不正确"
		},
		orgId : {
			required : "请选择组织"
		},
		deptId : {
			required : "请选择部门"
		},
		roleId : {
			required : "请选择角色"
		}
	},
	submitHandler : function(form) {
		var subData = {};
//		/**账户名*/
		subData.account =$("#personForm [name='account']").val();
//		/**姓名*/
		subData.name = $("#personForm [name='name']").val();
		if($("#personForm [name='deptId']").val()){
	//		/**账户所属部门id*/
			subData.deptId = $("#personForm [name='deptId']").val();
	//		/**账号所属部门名称*/
			subData.depName  = $("#personForm [name='deptId'] option:selected").html();
		}
//		/**账户所属组织id*/
		subData.orgId = $("#personForm [name='orgId']").val();
//		/**账号所属组织的名称*/
		subData.orgName  = $("#personForm [name='orgId'] option:selected").html();
//		/**账户角色id*/
		subData.roleId = $("#personForm [name='roleId']").val();
//		/**账户角色名称*/
		subData.roleName = $("#personForm [name='roleId'] option:selected").html();
		$.post("management/sysAccountQueryAdd.do",{
			dataJson:JSON.stringify(subData)
		},function(data){
			actionFormate(data, true,function(){
				$(form)[0].reset();
			});
		},"json");
	}
});
$("#organization").change( function() {
	var thisVal = $(this).val();
	$("#department").empty();
	$("#department").append('<option value="">请选择部门</option>');
	if (!thisVal) return;
	$.post("management/sysDeptManagementList.do", {
		orgId : thisVal
	}, function(data) {
		var datas = actionFormate(data,false);
		for ( var d in datas) {
			$("#department").append( '<option value="' + datas[d].id + '">' + datas[d].name + '</option>');
		}
	}, "json");
});