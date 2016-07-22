$('#addOrgModal,#editOrgModal,#deptModal').modal({
	backdrop : "static",
	keyboard : false,
	show : false
});
$('#addOrgModal').on('hidden.bs.modal', function(e) {
	$('#addOrgModal')[0].reset();
});

// 刷新数据
function restOrgData() {
	$.post("management/sysOrgManagementList.do", function(data) {
		var datas = actionFormate(data,false);
		var template = Handlebars.compile($("#entryOrgTemplate").html());
		var html = template(datas);
		$("#dataArea").empty();
		$("#dataArea").append(html);
	}, "json");
}
restOrgData();
// 删除
function deleteData(dom) {
	var tr = $(dom).closest("tr");
	var zuName = tr.children("td").first().html();
	if (!confirm("你确定要删除 " + zuName + " 该组织吗？这会导致该组织下所有部门和账户的变动？")) {
		return;
	}
	var id = tr.attr("id");
	$.post("management/sysOrgManagementDelete.do", {
		id : id
	}, function(data) {
		actionFormate(data, true, function() {
			tr.remove();
		});
	}, "json");
}
// 修改
function editData(dom) {
	var tr = $(dom).closest("tr");
	var id = tr.attr("id");
	var data = {};
	data.id = id;
	data.name = $("td", tr).eq(0).html();
	data.orgNumber = $("td", tr).eq(1).html();
	var modeHtml = $("#entrytemplate").html();
	var template = Handlebars.compile(modeHtml);
	var html = template(data);
	$("#editContext").html(html);
	$('#editOrgModal').modal('show');
}

// 初始化
function initCurRow(id) {
	$("#addDeptOrgid").val(id);
	$("#deptModal").modal('show');
	$.post("management/sysDeptManagementList.do", {
		orgId : id
	}, function(data) {
		var datas = actionFormate(data, false);
		$("#departmentArea").empty();
		console.log(datas);
		for ( var d in datas) {
			var modeHtml = $("#departmentTemp").html();
			var template = Handlebars.compile(modeHtml);
			var html = template(datas[d]);
			$("#departmentArea").append(html);
		}
		$("#deptModal").modal('show');
	}, "json");
}
// 删除行
function delCurRow(dom) {
	if (!confirm("确定要删除该部门吗？"))
		return;
	var tr = $(dom).closest("tr");
	$.post("management/sysDeptManagementDelete.do", {
		id : tr.attr("dataId")
	}, function(data) {
		actionFormate(data, true, function() {
			tr.remove();
		});
	}, "json");
}
// 编辑
function editCurRow(dom) {
	var tr = $(dom).closest("tr");
	var td = $("td", tr).first();
	var input = $("input", td);
	input.prop("disabled", false);
	$(".btn-success", tr).css("display", "inline-block");
	$(".btn-primary", tr).css("display", "none");
}
// 取消编辑
function cancelCurRow(dom) {
	var tr = $(dom).closest("tr");
	var td = $("td", tr).first();
	var input = $("input", td);
	input.val(input.attr("defauleVal"));
	input.prop("disabled", true);
	$(".btn-success", tr).css("display", "none");
	$(".btn-primary", tr).css("display", "inline-block");
}
// 保存数据
function saveCurRow(dom) {
	var tr = $(dom).closest("tr");
	var td = $("td", tr).first();
	var input = $("input", td);
	if (input.val() == null || input.val() == "") {
		$(dom).popover({
			content : "部门名称不能为空",
			placement : "top",
			trigger : "manual"
		});
		$(dom).popover('show');
		setTimeout(function() {
			$(dom).popover('hide');
		}, 1000)
	}
	$.post("management/sysDeptManagementEdit.do", {
		id : tr.attr("dataId"),
		name : input.val(),
		orgId : tr.attr("orgId")
	}, function(data) {
		actionFormate(data, true, function() {
			input.attr("defauleVal", input.val());
			input.prop("disabled", true);
			$(".btn-success", tr).css("display", "none");
			$(".btn-primary", tr).css("display", "inline-block");
		});
	}, "json");
}

$("#addDeptForm").validate({
	rules : {
		name : {
			required : true,
			maxlength : 10
		}
	}, messages : {
		name : {
			required : "部门名称不能为空",
			maxlength : "格式不正确"
		}
	},
	submitHandler : function(form) {
		$.post("management/sysDeptManagementAdd.do", $(form).serialize(),
			function(data) {
				actionFormate(data, true, function() {
					$(form)[0].reset();
					initCurRow($("#addDeptOrgid").val());
				});
		}, "json");
	}
});

$("#addOrgModal,#editOrgModal").each(function() {
	$(this).validate({
		rules : {
			name : {
				required : true,
				maxlength : 20
			},
			address : {
				required : true,
				maxlength : 50
			}
		},
		messages : {
			name : {
				required : "单位名称不能为空",
				maxlength : "格式不正确"
			},
			address : {
				required : "单位编号不能为空",
				maxlength : "格式正确"
			}
		},
		submitHandler : function(form) {
			$.post($(form).attr("action"), $(form).serialize(), function(data) {
				actionFormate(data,true,function(){
					restOrgData();
					$(form).modal('hide');
				});
			},"json");
		}
	});
});
