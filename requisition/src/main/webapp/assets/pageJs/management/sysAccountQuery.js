$("#organization").change(
		function() {
			var thisVal = $(this).val();
			$("#department").empty();
			$("#department").append('<option value="">请选择部门</option>');
			if (!thisVal)
				return;
			$.post("management/sysDeptManagementList.do", {
				orgId : thisVal
			}, function(data) {
				var datas = actionFormate(data, false);
				for ( var d in datas) {
					$("#department").append(
							'<option value="' + datas[d].id + '">' + datas[d].name + '</option>');
				}
			}, "json");
		});
var tableData = $.generateData({
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : "management/sysAccountQueryList.do",
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		data.account = $("#username").val();
		data.name = $("#name").val();
		data.orgId = $("#organization").val();
		data.deptId = $("#department").val();
	},
	lastFn : function(data) {
		var tempData = actionFormate(data, false);
		$("#accountCount").html(tempData.totalCount);
		return tempData;
	}
});
$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
});
$('#editAccountModal').modal({
	backdrop : "static",
	keyboard : false,
	show : false
});
var tr = null;
var selectDepId = "";
function editAccount(dom) {
	tr = $(dom).closest("tr");
	var dataId = tr.attr("dataId");
	var dataAccount = tr.attr("dataAccount");
	var dataName = tr.attr("dataName");
	var dataDeptId = tr.attr("dataDeptId");
	var dataOrgId = tr.attr("dataOrgId");
	var dataRoleId = tr.attr("dataRoleId");
	$("#editId").val(dataId);
	$("#editAccount").val(dataAccount).data("account",dataAccount);
	$("#editName").val(dataName);
	$("option[value='" + dataOrgId + "']", "#editOrgId").prop("selected", true);
	$("option[value='" + dataRoleId + "']", "#editRoleId").prop("selected", true);
	selectDepId = dataDeptId;
	$("#editOrgId").change();
	$('#editAccountModal').modal('show');
}
function reset(id){
	if(!confirm("确定要重置密码？")) return;
	$.post("management/sysAccountQueryReset.do",{id:id},function(data){
		 actionFormate(data, true);
	},"json");
}
function disable(id){
	if(!confirm("确定要冻结用户？")) return;
	$.post("management/sysAccountQueryDisable.do",{id:id},function(data){
		 actionFormate(data, true);
		 tableData.refreshData();
	},"json");
}
$("#editOrgId").change(
		function() {
			var thisVal = $(this).val();
			$("#editDeptId").empty();
			$("#editDeptId").append('<option value="">请选择部门</option>');
			if (!thisVal)
				return;
			$.post("management/sysDeptManagementList.do", {
				orgId : thisVal
			}, function(data) {
				var datas = actionFormate(data, false);
				for ( var d in datas) {
					$("#editDeptId").append(
							'<option value="' + datas[d].id + '" '
									+ (datas[d].id == selectDepId ? "selected" : "")
									+ '>' + datas[d].name + '</option>');
				}
			}, "json");
		});
$("#editAccountModal").validate({
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
		var val = $("#editAccount").val();
		var account = $("#editAccount").data("account");
		existsAccount(val,account,function(){
			var data = tr.data("data");
//			/**账户名*/
			data.account =  $("#editAccount").val();
//			/**姓名*/
			data.name = $("#editName").val();
			if($("#editDeptId").val()){
		//		/**账户所属部门id*/
				data.deptId = $("#editDeptId").val();
		//		/**账号所属部门名称*/
				data.depName  = $("#editDeptId option:selected").html();
			}
//			/**账户所属组织id*/
			data.orgId   = $("#editOrgId").val();
//			/**账号所属组织的名称*/
			data.orgName  = $("#editOrgId option:selected").html();
//			/**账户角色id*/
			data.roleId = $("#editRoleId").val();
//			/**账户角色名称*/
			data.roleName = $("#editRoleId option:selected").html();
			$.post("management/sysAccountQueryEdit.do",{
				dataJson:JSON.stringify(data)
			},function(d){
				actionFormate(d, true,function(){
					$("#editAccountModal").modal('hide');
					tr.remove();
					var template = Handlebars.compile($("#entrytemplate").html());
					var html = $(template(data));
					html.data("data",data);
					$("#dataTbody").prepend(html);
				});
			},"json");
		},function(msg){
			$("#editAccountModal").validate().showErrors({
				"account" : msg
			});
		});
	}
});
$("#editAccount").blur(function(){
	var val = $(this).val();
	var account = $(this).data("account");
	existsAccount(val,account,function(){},function(msg){
		$("#editAccountModal").validate().showErrors({
			"account" : msg
		});
	});
});
function existsAccount(account,oldAccount,suFn,erFn){
	if(account == oldAccount){
		suFn();
		return;
	}
	$.post("management/sysAccountExistsAccount.do",{
		account:account
	},function(data){
		actionFormate(data,false,function(){
			suFn();
		},function(type,msg,data){
			erFn(msg);
		});
	},"json");
}