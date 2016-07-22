var tableData = $.generateData({
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : sendUrl.removedInfo_query,
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		var queryEntity = {};
		queryEntity.pageIndex = data.pageIndex;
		queryEntity.pageSize = data.pageNum;
		var idNumber = $("#idNumber").val();
		var name = $("#name").val();
		var street = $("#street").val();
		var community = $("#community").val();
		var address = $("#queryAddressName").val();
		//var zu = $("#zu").val();
		if(idNumber){
			queryEntity.idNumber = idNumber;
		}
		if(name){
			queryEntity.name = name;
		}
		if(street){
			queryEntity.streetId = street;
		}
		if(community){
			queryEntity.communityId = community;
		}
		if(address){
			queryEntity.address = address;
		}
//		if(zu){
//			queryEntity.groupId = zu;
//		}
		queryEntity.createId = getCookie("login");
		data.queryEntity = JSON.stringify(queryEntity);
	},
	lastFn : function(data) {
		var tempData = actionFormate(data, false) || {datas:[],totalCount:0};
		$("#countArea").html(tempData.totalCount);
		return tempData;
	}
});
$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
});
$("#editInfoModal").validate({
	rules: {
		name: {
		    required: true
		}, idNumber:{
			required: true
		}, birthday:{
			required: true
		}, removeDate:{
			required: true
		}, other:{
			required: true
		}
	},  submitHandler:function(form){
		var tr = $('#editInfoModal').data("dom");
		var data = $(form).data("data");
		var subData ={};
		subData.id = data.id;
		subData.name = $("[name='name']",form).val();
		subData.idNumber = $("[name='idNumber']",form).val();
		subData.birthday = $("[name='birthday']",form).val();
		subData.removeDate = $("[name='removeDate']",form).val();
		//	subData.address += $("[name='groupId'] option:selected",form).html();
		subData.address = $("[name='other']",form).val();
		
		subData.fitPolicy = $("[name='fitPolicy']",form).val();
		
		$.post(sendUrl.removedInfo_edit,{
			entity:JSON.stringify(subData)
		},function(d){
			actionFormate(d, true,function(){
				var template = Handlebars.compile($("#logItemTemplate").html());
				var logHtml = template(subData);
				 operationLog("编辑已拆迁户","编辑已拆迁户",logHtml);
				 tr.remove();
				 data.name = subData.name;
				 data.idNumber = subData.idNumber;
				 data.birthday = subData.birthday;
				 data.removeDate = subData.removeDate;
				 data.streetId = subData.streetId;
				 data.communityId = subData.communityId;
				 data.address = subData.address;
				 data.fitPolicy = subData.fitPolicy;
				var template = Handlebars.compile($("#entrytemplate").html());
				var html = $(template(data));
				html.data("data",data);
				$("#dataTbody").prepend(html);
				$("#editInfoModal").modal("hide");
			});
		},"json");
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
	inputId : "#queryAddressName",
	dropDownId : "#queryAddressDown",
	url : sendUrl.addrProvider_getAddr,
	templateId : "#queryAddressDownTemplate",
	valName:"fuzzy",
	selectVal:"this",
	urlType:"get",
	firstFn:function(data){
		data.code = 5
	},
	lastFn:function(data){
		return actionFormate(data,false);
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
function deleteInfo(dom){
	if(!confirm("确定要删除？")) return;
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	$.get(sendUrl.removedInfo_del,{
		id:data.id
	},function(data){
		actionFormate(data, true,function(){
			 operationLog("删除已拆迁户","删除已拆迁户");
			tr.remove();
		});
	},"json");
}
function editInfo(dom){
	var data = $(dom).closest("tr").data("data");
	var address = data.address;
	data.other = address; 
	var template = Handlebars.compile($("#editModalTemplate").html());
	var html = $(template(data));
	$("#infoDataBody").html(html);
	$('[data-plugin-masked-input]',html).each(function() {
		var $this = $(this), opts = {};
		var pluginOptions = $this.data('plugin-options');
		if (pluginOptions)
			opts = pluginOptions;

		$this.themePluginMaskedInput(opts);
	});
	$('[data-plugin-datepicker]',html).each(function() {
		var $this = $(this), opts = {};
		$this.themePluginDatePicker(opts);
	});
	$('#editInfoModal').data("data",data);
	$('#editInfoModal').data("dom",$(dom).closest("tr"));
	$('#editInfoModal').modal('show');
}