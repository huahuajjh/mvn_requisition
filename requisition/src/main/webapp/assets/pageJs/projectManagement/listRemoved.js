setProListModal("#selectProInfoModal",function(data){
	if(data){
		$("#queryPrName").data("data",data);
		$("#queryPrName").val(data.proName);
	}
});
var tableData = $.generateData({
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : "projectManagement/listRemovedList.do",
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		data.idNumber = $("#idNumber").val();
		var queryPrName =  $("#queryPrName").val();
		if(queryPrName){
			data.queryProName = queryPrName;
		}
		data.huZhuName = $("#huZhuName").val();
		data.name = $("#name").val();
		data.isOnlyChild = $("#isDSZN").val();
		data.half = $("#isBBH").val();
		data.isTransfer = $("#isZH").val();
		data.isSSecurity = $("#isNRSB").val();
		data.streetId = $("#street").val();
		data.communityId = $("#community").val();
		data.zu = $("#zu").val();
		data.address = $("#queryAddressName").val();
	},
	lastFn : function(data) {
		var tempData = actionFormate(data, false);
		if(!tempData) return;
		for ( var i in tempData.datas) {
			var d = tempData.datas[i];
			if(d.gender =="MALE"){
				d.genderStr = "男";
				d.genderVal = 0;
			} else{
				d.genderStr = "女";
				d.genderVal = 1;
			}
		}
		$("#countArea").html(tempData.totalCount);
		return tempData;
	}
});

$('#editPersonInfoModal').modal({
	backdrop : "static",
	keyboard : false,
	show : false
});
$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
});
$.dropDownInput({
	inputId : "#queryPrName",
	dropDownId : "#queryPrDown",
	url : "projectManagement/pmProgressNames.do",
	templateId : "#queryPrDownTemplate",
	lastFn:function(data){
		return actionFormate(data,false);
	},itemClick:function(data){
		$("#queryPrName").data("data",data);
	}
});
$.dropDownInput({
	inputId : "#queryAddressName",
	dropDownId : "#queryAddressDown",
	url : sendUrl.addrProvider_getAddr,
	templateId : "#queryAddressDownTemplate",
	valName:"fuzzy",
	selectVal:"this",
	urlType:"get",
	firstFn:function(data){
		data.code = 3;
	},
	lastFn:function(data){
		return actionFormate(data,false);
	}
});
$.dropDownInput({
	inputId : "#name",
	dropDownId : "#nameQueryPrDown",
	url : sendUrl.onekeyQuery_getFuzzy,
	urlType:"get",
	valName:"fuzzy",
	selectVal:"name",
	templateId : "#nameQueryPrDownTemplate",
	lastFn:function(data){
		return actionFormate(data,false);
	},itemClick:function(data){
		$("#name").data("data",data);
	}
});
$.dropDownInput({
	inputId : "#idNumber",
	dropDownId : "#idNumberQueryPrDown",
	url : sendUrl.onekeyQuery_getFuzzy,
	urlType:"get",
	valName:"fuzzy",
	selectVal:"idNumber",
	templateId : "#idNumberQueryPrDownTemplate",
	lastFn:function(data){
		return actionFormate(data,false);
	},itemClick:function(data){
		$("#idNumber").data("data",data);
	}
});
function showProInfo(id){
	$.get("share/projectInfo.do",{
		id:id
	},function(html){
		$("#showProInfoArea").html(html);
		$("#showProInfoModal").modal("show");
	});
}
function showRemovedInfo(dom){
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#showInfoTemplate").html());
	var html = template(data);
	$("#showPersonInfo").html(html);
	$.post("projectManagement/pmProgressGet.do",{
		proId:data.proId
	},function(data){
		actionFormate(data,false,function(type,msg,d){
			var template = Handlebars.compile($("#proInfoEntrytemplate").html());
			var html = template(d);
			$("#proInfoShow").html(html);
		});
	},"json");
	$.post("projectManagement/listRemovedGet.do",{
		id:data.fmlId
	},function(data){
		 actionFormate(data,false,function(type,msg,d){
			var template = Handlebars.compile($("#showHuMsgEntrytemplate").html());
			var html = $(template(d));
			html.data("data",d);
			$("#showHuMsgArea").append(html);
			if(d.items){
				var child = d.items;
				for (var i = 0; i < child.length; i++) {
			 		var t = child[i];
			 		template = Handlebars.compile($("#famitlyItemEntrytemplate").html());
					html = $(template(t));
					html.data("data",t);
					$("#familyInfoItem").append(html);
				}
			}
		 });
	},"json");
	$('#showPersonInfoModal').modal('show');
}
function showHuInfo(dom){
	var table = $(dom).closest("table");
	var data = table.data("data");
	var template = Handlebars.compile($("#showHuInfoEntrytemplate").html());
	var html = $(template(data));
	html.data("data",data);
	$("#showHuArea").html(html);
	$("#showHuMsgModal").modal("show");
}
function showPersonInfo(dom){
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#familyItemInfoEntrytemplate").html());
	var html = template(data);
	$("#showPesroMsgArea").html(html);
	$("#showPesroMsgModal").modal("show");
}
function showFileItem(dom){
	var table = $(dom).closest("table");
	var data = table.data("data");
	var fileItem = [];
	var docPath = data.houseImgPath;
	if(docPath){
		var paths = docPath.split("|");
		for ( var p in paths) {
			var path = paths[p];
			fileItem.push(path.substring(path.indexOf("/") + 1));
		}
	}
	if(!fileItem || fileItem.length <= 0) return;
	$.initShowImage(fileItem);
}
function showFileImage(dom){
	var table = $(dom).closest("table");
	var data = table.data("data");
	var image = data.image;
	if(image){
		$.initShowImage([image]);
	}
}

var tr = null;
function editRemevedInfo(dom){
	tr = $(dom).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#editPersonInfoTemplate").html());
	var html = template(data);
	var rHtml = $(html);
	$('[data-plugin-masked-input]',rHtml).each(function() {
		var $this = $(this), opts = {};
		var pluginOptions = $this.data('plugin-options');
		if (pluginOptions)
			opts = pluginOptions;

		$this.themePluginMaskedInput(opts);
	});
	$('[data-plugin-datepicker]',rHtml).each(function() {
		var $this = $(this), opts = {};
		$this.themePluginDatePicker(opts);
	});
	$("[name='gender'] option[value='"+data.genderVal+"']",rHtml).prop("selected",true);
	$("[name='householdId'] option[value='"+data.householdId+"']",rHtml).prop("selected",true);
	$("#editPersonInfo").html(rHtml);
	$('#editPersonInfoModal').modal('show');
}
$("#editPersonInfoModal").validate({
	rules : {
		name : {
			required : true,
			maxlength : 10
		},
		idNumber : {
			required : true
		},
		birthday : {
			required : true
		},
		gender : {
			required : true
		},
		householdId : {
			required : true
		}
	},
	submitHandler : function(form) {
		var data = tr.data("data");
		var subData = {};
		subData.id = data.id;
		subData.name = $("[name='name']",form).val();
		subData.idNumber = $("[name='idNumber']",form).val();
		subData.birthday = $("[name='birthday']",form).val();
		subData.gender = $("[name='gender']",form).val();
		if($("[name='onlyChildNumber']",form).val()){
			subData.onlyChildNumber = $("[name='onlyChildNumber']",form).val();
		}
		subData.half = $("[name='half']",form).prop("checked");
		subData.householdId = $("[name='householdId']",form).val();
		subData.householdStr = $("[name='householdId'] option:selected",form).html();
		subData.educationLevel = $("[name='educationLevel']",form).val();
		subData.currentEducationSituation = $("[name='currentEducationSituation']",form).val();
		subData.farmingTime = $("[name='farmingTime']",form).val();
		subData.serveArmySituation = $("[name='serveArmySituation']",form).val();
		subData.tel = $("[name='tel']",form).val();
		subData.remark = $("[name='remark']",form).val();
		subData.userdSocialsecurity = $("[name='userdSocialsecurity']",form).prop("checked");
		var dataJson = JSON.stringify(subData);
		$.post("projectManagement/listRemovedEdit.do",{
			dataJson:dataJson
		},function(data){
			actionFormate(data,true,function(type,msg,data){
				operationLog("修改拆迁户人员","修改拆迁户人员信息");
				if(data.gender =="MALE"){
					data.genderStr = "男";
					data.genderVal = 0;
				} else{
					data.genderStr = "女";
					data.genderVal = 1;
				}
				tr.remove();
				var template = Handlebars.compile($("#entrytemplate").html());
				var html = template(data);
				var rHtml = $(html);
				rHtml.data("data",data);
				$("#dataTbody").prepend(rHtml);
				$("#editPersonInfoModal").modal("hide");
			});
		},"json");
	}
});