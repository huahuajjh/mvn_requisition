setPersonListModal("#selectPerson",function(data){
	if(data){
		$("#idNumber").val(data.idNumber);
		initDom(data);
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
$("#idNumberBtn").click(function() {
	var idNumber = $("#idNumber").val();
	if (!idNumber)
		return;
	$.post("socialSecurityMansgement/solmImportFileGet", {
		idNumber : idNumber
	}, function(data) {
		actionFormate(data, true, function(type, msg, data) {
			initDom(data);
		});
	}, "json");
});
$("#addZhuanHu").submit(function() {
		if(!isPassValidate()) return false;
		if($("#isZhuanHuTrue").prop("checked")){
			var data = $("#dataArea").data("data");
			var subData = {};
			subData.fmlItemId = data.id;
			subData.transferDate = $("#time").val();
			subData.streetId = $("#street").val();
			subData.communityId = $("#community").val();
			subData.groupId = $("#zu").val();
			var address = "";
			address += $("#street option:selected").html();
			address += $("#community option:selected").html();
			address += $("#zu option:selected").html();
			subData.address = address + "," + $("#addressOtherMsg").val();
			subData.houseHoldTypeId = $("#newType").val();
			subData.houseHoldTypeStr = $("#newType option:selected").html();
			$.post("transferAccountManagement/taImportFileAdd",{
				dataJson:JSON.stringify(subData)
			},function(data){
				 actionFormate(data, true,function(){
					 operationLog("录入转户信息","录入转户信息");
					 resestForm();
				 });
			},"json");
		} else {
			showMsg("录入成功","提示：");
			resestForm();
		}
		return false;
});
function initDom(data){
	var template = Handlebars.compile($("#entrytemplate").html());
	var html = $(template(data));
	$("#dataArea").data("data",data);
	$("#dataArea").html(html);
	$("#isZhuanHuTrue,#isZhuanHuFalse").change(function() {
		if ($(this).attr("id") == "isZhuanHuTrue") {
			enablingDom();
		} else {
			disabledDom();
		}
	});
	new bindingSelect({
		masterSelect:"#community",
		childSelect:"#zu",
		childDefalueVal:"请选择组",
		url:"share/address",
		afterFn:function(data){
			return actionFormate(data, false);
		}
	});
	new bindingSelect({
		masterSelect:"#street",
		childSelect:"#community",
		childDefalueVal:"请选择社区",
		url:"share/address",
		afterFn:function(data){
			return actionFormate(data, false);
		}
	});
	$('[data-plugin-datepicker]').each(function() {
		var $this = $(this), opts = {};
		$this.themePluginDatePicker(opts);
	});
	$('[data-plugin-masked-input]').each(function() {
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
function disabledDom() {
	$("#time,#addressOtherMsg").val("");
	$("#street option:first").prop("selected", true).change();
	$("#newType option:first").prop("selected", true);
	$("#street,#community,#zu,#newType,#time,#addressOtherMsg").prop("disabled", true);
}
function enablingDom() {
	$("#street,#community,#zu,#time,#newType,#addressOtherMsg").prop("disabled", false);
}
function isPassValidate() {
	if (!$("#isZhuanHuTrue").prop("checked")
			&& !$("#isZhuanHuFalse").prop("checked")) {
		$("#isZhuanHuArea").popover({
			content : "请选择是否需要转户",
			placement : "left"
		});
		$("#isZhuanHuArea").popover("show");
		setTimeout(function() {
			$("#isZhuanHuArea").popover("destroy");
		}, 1000);
		return false;
	} else if ($("#isZhuanHuTrue").prop("checked")) {
		if (!$("#time").val()) {
			$("#timeArea").popover({
				content : "请输入转户时间",
				placement : "left"
			});
			$("#timeArea").popover("show");
			setTimeout(function() {
				$("#timeArea").popover("destroy");
			}, 1000);
		}
		if (!$("#newType").val()) {
			$("#newTypeArea").popover({
				content : "请选择转户类型",
				placement : "left"
			});
			$("#newTypeArea").popover("show");
			setTimeout(function() {
				$("#newTypeArea").popover("destroy");
			}, 1000);
		}
		var street = $("#street").val();
		var community = $("#community").val();
		var zu = $("#zu").val();
		if(!street || !community || !zu ){
			$("#AddressArea").popover({
				content : "请选择转户地址",
				placement : "left"
			});
			$("#AddressArea").popover("show");
			setTimeout(function() {
				$("#AddressArea").popover("destroy");
			}, 1000);
		}
		if(!$("#newType").val() || !$("#time").val() || !street || !community || !zu){
			return false;
		}
	}
	return true;
}
function resestForm(){
	$("#dataArea").html("");
}