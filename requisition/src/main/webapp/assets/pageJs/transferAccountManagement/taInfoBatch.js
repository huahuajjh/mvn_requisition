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
	url : "transferAccountManagement/taInfoBatchList",
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		var queryPrName =  $("#queryPrName").val();
		if(queryPrName){
			data.queryProName = queryPrName;
		}
		data.streetId = $("#street").val();
		data.communityId = $("#community").val();
		data.idNumber = $("#idNumber").val();
		data.zuId = $("#zu").val();
	}, lastFn : function(data) {
		var tempData = actionFormate(data, false);
		$("#countArea").html(tempData.totalCount);
		return tempData;
	}, domFn:function(dom){
		initStreetAndCommunityAndClick(dom);
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
$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
});
$.dropDownInput({
	inputId : "#queryPrName",
	dropDownId : "#queryPrDown",
	url : "projectManagement/pmProgressNames",
	templateId : "#queryPrDownTemplate",
	lastFn : function(data) {
		return actionFormate(data, false);
	},itemClick:function(data){
		$("#queryPrName").data("data",data);
	}
});
$("#selectAll").change(function() {
	var thisCheck = $(this).prop("checked");
	$("#dataTbody input[name='isCheck']").prop("checked", thisCheck);
});
$("#isZhuanHu input[type='radio']").change(
		function() {
			var thisVal = $(this).val();
			var thisCheck = $(this).prop("checked");
			$("#dataTbody input[name='isZhuanHu'][value='" + thisVal + "']")
					.prop("checked", thisCheck);
			$("#dataTbody input[name='isZhuanHu'][value='" + thisVal + "']")
					.change();
		});
$("#time").change(function() {
	var thisVal = $(this).val();
	$("#dataTbody input[name='time']:not(:disabled)").val(thisVal);
});
$("#hType").change(
		function() {
			var thisVal = $(this).val();
			$(
					"#dataTbody select[name='hType']:not(:disabled) option[value='"
							+ thisVal + "']").prop("selected", true);
		});
$("#sendDataBtn").click(function() {
	var datas = getData();
	if (datas == null) {
		$('#sendDataBtn').popover({
			content : "请把数据填写完整"
		});
		$('#sendDataBtn').popover('show');
		setTimeout(function() {
			$("#sendDataBtn").popover('destroy');
		}, 1000);
	} else if (datas.length <= 0) {
		return;
		$('#sendDataBtn').popover({
			content : "请选择你需要提交的数据"
		});
		$('#sendDataBtn').popover('show');
		setTimeout(function() {
			$("#sendDataBtn").popover('destroy');
		}, 1000);
	} else {
		$.post("transferAccountManagement/taInfoBatchAdd",{
			dataJson:JSON.stringify(datas)
		},function(data){
			actionFormate(data, true,function(){
				operationLog("批量录入转户信息","批量录入转户信息");
				tableData.refreshData();
			 });
		},"json");
	}
});

function initStreetAndCommunityAndClick(dom) {
	var thisDom = dom;
	var street = $("select[name='streetId']", thisDom);
	var community = $("select[name='communityId']", thisDom);
	var zu = $("select[name='zuId']", thisDom);
	var isZhuanHu = $(".isZhuanHu [name='isZhuanHu']", thisDom);
	var address = $(".isZhuanHu [name='address']", thisDom);
	new bindingSelect({
		masterSelect:community,
		childSelect:zu,
		childDefalueVal:"请选择",
		url:"share/address",
		afterFn:function(data){
			return actionFormate(data, false);
		}
	});
	new bindingSelect({
		masterSelect:street,
		childSelect:community,
		childDefalueVal:"请选择社区",
		url:"share/address",
		afterFn:function(data){
			return actionFormate(data, false);
		}
	});
	isZhuanHu.change(function() {
		console.log("ccc");
		var thisVal = $(this).val();
		if (thisVal == "true") {
			$("[name='time'],[name='streetId'],[name='communityId'],[name='zuId'],[name='hType'],[name='address']",thisDom).prop("disabled", false);
		} else {
			$("[name='time'],[name='streetId'],[name='communityId'],[name='zuId'],[name='hType'],[name='address']", thisDom).prop("disabled", true);
			$("[name='time'],[name='streetId'],[name='communityId'],[name='zuId'],[name='hType'],[name='address']", thisDom).val("");
		}
	});
	$("*", dom).click(function() {
		$(this).removeClass("errorBorder");
	});
}
function getData() {
	if (!isPassValidate())
		return null;
	var datas = [];
	$("[name='isZhuanHu']:checked", "#dataTbody").each(function() {
		var tr = $(this).closest("tr");
		var isZhuanHu = $(this).val();
		var streetId = $("[name='streetId']", tr).val();
		var communityId = $("[name='communityId']", tr).val();
		var zuId = $("[name='zuId']", tr).val();
		var time = $("[name='time']", tr).val();
		var hType = $("[name='hType']", tr).val();
		var data = {};
		var address = "";
		data.streetId = streetId;
		address +=   $("[name='streetId'] option:selected", tr).html();
		data.communityId = communityId;
		address +=  $("[name='communityId'] option:selected", tr).html();
		data.groupId = zuId;
		address +=  $("[name='zuId'] option:selected", tr).html();
		data.address = address + "," + $("[name='address']", tr).val();
		data.transferDate = time;
		data.houseHoldTypeId = hType;
		data.houseHoldTypeStr = $("[name='hType'] option:selected", tr).html();
		data.fmlItemId = tr.data("data").fmlItemId;
		if (isZhuanHu == "true")
			datas.push(data);
	});
	return datas;
}
function isPassValidate() {
	var state = true;
	$("#dataTbody *").removeClass("errorBorder");
	$("[name='isZhuanHu']:checked", "#dataTbody").each(function() {
		var tr = $(this).closest("tr");
		var isZhuanHu = $(this).val();
		if (isZhuanHu == "false")
			return;
		var time = $("[name='time']", tr);
		if (!time.val())
			time.addClass("errorBorder");
		var hType = $("[name='hType']", tr);
		if (!hType.val())
			hType.addClass("errorBorder");
		var streetId = $("[name='streetId']", tr);
		if (!streetId.val())
			streetId.addClass("errorBorder");
		var communityId = $("[name='communityId']", tr);
		if (!communityId.val())
			communityId.addClass("errorBorder");
		var zuId = $("[name='zuId']", tr);
		if (!zuId.val())
			zuId.addClass("errorBorder");
		
		if (!isZhuanHu || !time.val() || !hType.val() || !streetId.val() || !communityId.val() || !zuId.val()) {
			state = false;
		}
	});
	return state;
}
function showProInfo(id){
	$.get("share/projectInfo",{
		id:id
	},function(html){
		$("#showProInfoArea").html(html);
		$("#showProInfoModal").modal("show");
	});
}