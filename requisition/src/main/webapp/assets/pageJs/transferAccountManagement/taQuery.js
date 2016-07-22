setProListModal("#selectProInfoModal",function(data){
	if(data){
		$("#queryPrName").data("data",data);
		$("#queryPrName").val(data.proName);
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
var tableData = $.generateData({
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : "transferAccountManagement/taQueryList",
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		var queryPrName =  $("#queryPrName").val();
		if(queryPrName){
			data.queryProName = queryPrName;
		}
		data.streetId = $("#street").val();
		data.communityId = $("#community").val();
		data.isTransfered = $("#isTransfered").val();
		data.zuId = $("#zu").val();
		data.idNumber = $("#idNumber").val();
	},
	lastFn : function(data) {
		var tempData = actionFormate(data, false);
		$("#countArea").html(tempData.totalCount);
		return tempData;
	}
});
$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
});
$('#editTransferModal').modal({
	backdrop : "static",
	keyboard : false,
	show : false
});
$.dropDownInput({
	inputId : "#queryPrName",
	dropDownId : "#queryPrDown",
	url : "projectManagement/pmProgressNames",
	templateId : "#queryPrDownTemplate",
	lastFn:function(data){
		return actionFormate(data,false);
	},itemClick:function(data){
		$("#queryPrName").data("data",data);
	}
});
new bindingSelect({
	masterSelect:"#community",
	childSelect:"#zu",
	childDefalueVal:"所有组",
	url:"share/address",
	afterFn:function(data){
		return actionFormate(data, false);
	}
});
new bindingSelect({
	masterSelect:"#street",
	childSelect:"#community",
	childDefalueVal:"所有社区",
	url:"share/address",
	afterFn:function(data){
		return actionFormate(data, false);
	}
});
$("#editTransferModal").submit(function(){
	if(isPassValidate()){
		var data = tr.data("data");
		var subData = {};
		subData.transferDate = $("#editTime").val();
		var streetId = $("#editStreet").val();
		var address = "";
		if(streetId){
			subData.streetId = streetId;
			address +=  $("#editStreet option:selected").html();
		}
		var communityId = $("#editCommunity").val();
		if(communityId){
			subData.communityId = communityId;
			address +=  $("#editCommunity option:selected").html();
		}
		var editZu = $("#editZu").val();
		if(editZu){
			subData.groupId = editZu;
			address +=  $("#editZu option:selected").html();
		}
		subData.address = address + "," + $("#editAddressOtherMsg").val();
		subData.houseHoldTypeId = $("#editHuType").val();
		subData.houseHoldTypeStr = $("#editHuType option:selected").html();
		subData.fmlItemId = data.fmlItemId;
		subData.id = data.transferId;
		$.post("transferAccountManagement/taQueryEdit",{
			dataJson:JSON.stringify(subData)
		},function(d){
			actionFormate(d, true,function(type,msg,d){
				operationLog("编辑转户信息","编辑转户信息");
				tr.remove();
				var template = Handlebars.compile($("#entrytemplate").html());
				data.address = subData.address;
				data.transferDate = subData.transferDate;
				data.communityId = subData.communityId;
				data.streetId = subData.streetId;
				data.groupId = subData.groupId;
				data.houseHoldTypeId = subData.houseHoldTypeId;
				data.houseHoldTypeStr = subData.houseHoldTypeStr;
				var html = template(data);
				 var rHtml = $(html);
				 rHtml.data("data",data);
				$("#dataTbody").prepend(rHtml);
				$("#editTransferModal").modal("hide");
			 });
		},"json");
	}
	return false;
});
var tr =null;
function editData(dom){
	tr = $(dom).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#editDataTemplate").html());
	var html = template(data);
	var rHtml = $(html);
	initDom(rHtml,data);
	$("#editInfoArea").html(rHtml);
	$("#editTransferModal").modal("show");
}
function initDom(dom,data){
	new bindingSelect({
		masterSelect:$("#editCommunity",dom),
		childSelect:$("#editZu",dom),
		childDefalueVal:"请选择组",
		childVal:data.groupId,
		url:"share/address",
		afterFn:function(data){
			return actionFormate(data, false);
		}
	});
	new bindingSelect({
		masterSelect:$("#editStreet",dom),
		childSelect:$("#editCommunity",dom),
		childDefalueVal:"请选择社区",
		childVal:data.communityId,
		url:"share/address",
		afterFn:function(data){
			return actionFormate(data, false);
		}
	});
	$("#editStreet",dom).val(data.streetId).change();
	var dotIndex = data.address.lastIndexOf(",");
	if(dotIndex > -1){
		var addressOtherMsg = data.address.substring(dotIndex+1);
		$("#editAddressOtherMsg",dom).val(addressOtherMsg);
	}
	$("#editHuType",dom).val(data.houseHoldTypeId);
	$('[data-plugin-datepicker]',dom).each(function() {
		var $this = $(this), opts = {};
		$this.themePluginDatePicker(opts);
	});
	$('[data-plugin-masked-input]',dom).each(function() {
		var $this = $(this), opts = {};

		var pluginOptions = $this.data('plugin-options');
		if (pluginOptions)
			opts = pluginOptions;

		$this.themePluginMaskedInput(opts);
	});
} 
function showProInfo(id){
	$.get("share/projectInfo",{
		id:id
	},function(html){
		$("#showProInfoArea").html(html);
		$("#showProInfoModal").modal("show");
	});
}
function isPassValidate() {
	var time = $("#editTime");
	if (!time.val()){
		time.popover({
			content:"请输入转户时间",
			placement:"left"
		});
		time.popover('show');
		setTimeout(function(){
			time.popover('destroy');
		}, 1000);
	}
	var hType = $("#editHuType");
	if (!hType.val()){
		hType.popover({
			content:"请选择转户类型",
			placement:"left"
		});
		hType.popover('show');
		setTimeout(function(){
			hType.popover('destroy');
		}, 1000);
	}
	var street = $("#editStreet").val();
	var community = $("#editCommunity").val();
	var zu = $("#editZu").val();
	if(!street || !community || !zu){
		$("#editStreetAndCommunity").popover({
			content:"请选择转户地址",
			placement:"left"
		});
		$("#editStreetAndCommunity").popover('show');
		setTimeout(function(){
			$("#editStreetAndCommunity").popover('destroy');
		}, 1000);
	}
	if ( !time.val() || !hType.val() || !street || !community || !zu) {
		return false;
	}
	return true;
}