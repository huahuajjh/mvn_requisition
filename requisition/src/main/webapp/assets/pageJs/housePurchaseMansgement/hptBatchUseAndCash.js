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
		data.code = 3
	},
	lastFn:function(data){
		return actionFormate(data,false);
	}
});
setProListModal("#selectProInfoModal",function(data){
	if(data){
		$("#queryPrName").data("data",data);
		$("#queryPrName").val(data.proName);
	}
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
var tableData = $.generateData({
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : "housePurchaseMansgement/hptBatchUseAndCashList.do",
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		data.queryProName =  $("#queryPrName").val();
		data.streetId = $("#street").val();
		data.communityId = $("#community").val();
		data.name = $("#name").val();
		data.zu = $("#zu").val();
		data.address = $("#queryAddressName").val();
		data.idNumber = $("#idNumber").val();
	},
	lastFn : function(data) {
		var tempData = actionFormate(data, false,function(type,msg,d){
			var da = d || {};
			var data = da.datas || [];
			for (var i = 0; i < data.length; i++) {
				var td = data[i];
				td.ticketName = toTicketStr(td.ticketState);
				td.ticketIndex = toTicketNumber(td.ticketState);
			}
		});
		$("#countArea").html(tempData.totalCount || 0);
		return tempData;
	}, domFn:function(dom){
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
		submitFileStyle($(".upLoadFile",dom),"EVIDENCE_FILE",function(data){
			return actionFormate(data, true);
		});
		$(".yuLanBtn",dom).click(function(){
			var img = $(this).data("img");
			if(!img) return;
			$.initShowImage([img]);
		});
		$(".upFile",dom).change(function(){
			var par = $(this).parent();
			var val = $(this).val();
			if(!val) return;
			$(".paiZhaoFileLoginState",par).css("display","inline");
			$(".zhaoBtn,.yuLanBtn,.paiZhaoFileCheckState,.upBtn",par).css("display","none");
			submitFile(this,{
				fileType:"EVIDENCE_FILE"
			},"json",function(data){
				actionFormate(data, true, function(type, msg, data) {
					data = data.substring(data.indexOf("/")+1);
					$(".yuLanBtn",par).data("img",data);
				});
				$(".paiZhaoFileLoginState",par).css("display","none");
				$(".zhaoBtn,.yuLanBtn,.paiZhaoFileCheckState,.upBtn",par).css("display","inline");
			},function(e){
				$(".paiZhaoFileLoginState",par).css("display","none");
				$(".zhaoBtn,.yuLanBtn,.paiZhaoFileCheckState,.upBtn",par).css("display","inline");
			});
		});
		$(".isChuLi [name='isChuLi']", dom).change(function() {
			var dom = $(this).closest("tr");
			var thisVal = $(this).val();
			if (thisVal == "true") {
				$("input[type='text'],select,button",dom).prop("disabled", false);
			} else {
				$("input[type='text'],select,button",dom).prop("disabled", true);
				$("input[type='text'],select",dom).val("");
			}
		});
		$("*", dom).click(function() {
			$(this).removeClass("errorBorder");
		});
	}
});
$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
});
$("#isChuLi input[type='radio']").change(function() {
	var thisVal = $(this).val();
	var thisCheck = $(this).prop("checked");
	$("#dataTbody input[name='isChuLi'][value='" + thisVal + "']").prop("checked", thisCheck).change();
});
$("#useType").change(function() {
	var thisVal = $(this).val();
	$("#dataTbody select[name='useType']:not(:disabled)").val(thisVal);
});
$("#time").change(function() {
	var thisVal = $(this).val();
	$("#dataTbody input[name='time']:not(:disabled)").val(thisVal);
});
$("#useExplain").change(function() {
	var thisVal = $(this).val();
	$("#dataTbody input[name='useExplain']:not(:disabled)").val(thisVal);
});
$("#situationExplain").change(function() {
	var thisVal = $(this).val();
	$("#dataTbody input[name='situationExplain']:not(:disabled)").val(thisVal);
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
	} else {
		$.post("housePurchaseMansgement/hptBatchUseAndCashProcessing.do",{
			dataJson:JSON.stringify(datas)
		},function(data){
			actionFormate(data, true,function(){
				var template = Handlebars.compile($("#logItemTemplate").html());
				var logHtml = template(datas);
				operationLog("批量兑付购房券信息","批量兑付购房券信息",logHtml);
				tableData.refreshData();
			 });
		},"json");
	}
});
function getData() {
	if (!isPassValidate())
		return null;
	var datas = [];
	$("[name='isChuLi']:checked", "#dataTbody").each(function() {
		var tr = $(this).closest("tr");
		var isChuLi = $(this).val();
		var time = $("[name='time']", tr).val();
		var sbType = $("[name='useType']", tr).val();
		var data = tr.data("data");
		var subData = {};
		subData.ownerId = data.fmlItemId;
//		/**购房券id*/
		subData.ticketId = data.hptId;
//		/**使用日期*/
		subData.usingDate = $("[name='time']",tr).val();
//		/**使用类型*/
		subData.usingType= $("[name='useType']",tr).val();
//		/**使用去向*/
		subData.usingToWhere = $("[name='useExplain']",tr).val();
//		/**情况说明*/
		subData.situationExplain = $("[name='situationExplain']",tr).val();
//		/**相关凭证*/
		subData.evidencePath = $("[name='evidencePath']",tr).val();
		//拍照
		subData.image = $(".yuLanBtn",tr).data("img");

		subData.resideName = data.name;
		subData.resideIdNumber = data.idNumber;
		subData.resideTicketNumber = data.ticketNumber;
		subData.resideBonus = data.bonus;
		subData.resideMakeTime = data.makeTime;
		subData.usingTypeStr = $("[name='useType'] option:selected",tr).html();
		
		if (isChuLi == "true")
			datas.push(subData);
	});
	return datas;
}
function isPassValidate() {
	var state = true;
	$("#dataTbody *").removeClass("errorBorder");
	$("[name='isChuLi']:checked", "#dataTbody").each(function() {
		var tr = $(this).closest("tr");
		var isChuLi = $(this).val();
		if (isChuLi == "false")
			return;
		var time = $("[name='time']", tr);
		if (!time.val()){
			time.addClass("errorBorder");
			state = false;
		}
		var useType = $("[name='useType']", tr);
		if (!useType.val()){
			useType.addClass("errorBorder");
			state = false;
		}
	});
	return state;
}
function upFileZhaoPian(dom){
	$(dom).parent().children(".upFile").click();
}
function paiZhao(dom){
	var par = $(dom).parent();
	$("#phonePaiZhaoModal").data("dom",par);
	$.get("share/photographs.do",function(html){
		$("#phonePaiZhaoBody").html(html);
		$("#phonePaiZhaoModal").modal("show");
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
$("#phonePaiZhaoModal").on("hidden.bs.modal",function(){
	var par = $("#phonePaiZhaoModal").data("dom");
	if($(this).data("btnState") == true){
		var imgData = $(this).data("imgData");
		$(".paiZhaoFileLoginState",par).css("display","inline");
		$(".zhaoBtn,.yuLanBtn,.paiZhaoFileCheckState,.upBtn",par).css("display","none");
		$.post("share/saveFile.do",{
			baseSFFile:imgData
		},function(data){
			actionFormate(data, true, function(type, msg, data) {
				$(".yuLanBtn",par).data("img",data);
			});
			$(".paiZhaoFileLoginState",par).css("display","none");
			$(".zhaoBtn,.yuLanBtn,.paiZhaoFileCheckState,.upBtn",par).css("display","inline");
		},"json");
	}
});
$("#phonePaiZhaoModal").modal({
	show:false, 
	keyboard:false,
	backdrop:"static",
});