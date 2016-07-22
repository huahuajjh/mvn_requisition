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
			required: true,
			minlength:18
		}, birthday:{
			required: true
		}, removeDate:{
			required: true
		}, streetId:{
			required: true
		}, communityId:{
			required: true
		}, groupId:{
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
		subData.streetId = $("[name='streetId']",form).val();
		subData.communityId = $("[name='communityId']",form).val();
		//subData.groupId = $("[name='groupId']",form).val();
		subData.address = $("[name='streetId'] option:selected",form).html();
		subData.address += $("[name='communityId'] option:selected",form).html();
		//	subData.address += $("[name='groupId'] option:selected",form).html();
		subData.address += "," + $("[name='other']",form).val();
		
		subData.fitPolicy = $("[name='fitPolicy']",form).val();
		
		$.post(sendUrl.removedInfo_edit,{
			entity:JSON.stringify(subData)
		},function(d){
			actionFormate(d, true,function(){
				 operationLog("编辑已拆迁户","编辑已拆迁户");
				 tr.remove();
				 data.name = subData.name;
				 data.idNumber = subData.idNumber;
				 data.birthday = subData.birthday;
				 data.removeDate = subData.removeDate;
				 data.streetId = subData.streetId;
				 data.communityId = subData.communityId;
				 data.address = subData.address;
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
	if(address.indexOf(",") > -1){
		var addressArr = address.split(",");
		data.other = addressArr[1]; 
	}
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
//	new bindingSelect({
//		masterSelect:$("[name='communityId']",html),
//		childSelect:$("[name='groupId']",html),
//		childDefalueVal:"请选择组",
//		url:"share/address",
//		childVal:data.groupId,
//		afterFn:function(data){
//			return actionFormate(data, false);
//		}
//	});
	new bindingSelect({
		masterSelect:$("[name='streetId']",html),
		childSelect:$("[name='communityId']",html),
		childDefalueVal:"请选择社区",
		url:"share/address",
		childVal:data.communityId,
		afterFn:function(data){
			return actionFormate(data, false);
		}
	});
	$("[name='streetId']",html).val(data.streetId).change();
	$('#editInfoModal').data("data",data);
	$('#editInfoModal').data("dom",$(dom).closest("tr"));
	$('#editInfoModal').modal('show');
}