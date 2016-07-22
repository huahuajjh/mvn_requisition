setPersonListModal("#selectPerson",function(data){
	if(data){
		$("#idNumber").val(data.idNumber);
		$("#queryBtn").click();
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
$("#queryData").validate({
	rules:{
		idNumber:{
			required: true
		}
	}, submitHandler:function(form){
		$.post("housePurchaseMansgement/hptLossAndMendGet.do",$("#queryData").serialize(),function(data){
			actionFormate(data, false,function(type,msg,datas){
				
				datas = datas || [];
				var template = Handlebars.compile($("#tableItemTemplate").html());
				var html = template();
				var rHtml = $(html);
				for (var i = 0; i < datas.length; i++) {
					var data = datas[i];
					data.ticketName = toTicketStr(data.ticketState);
					data.ticketIndex = toTicketNumber(data.ticketState);
					var t = Handlebars.compile($("#dataItemTemplate").html());
					var h = $(t(data));
					h.data("data",data);
					$("#dataItems",rHtml).append(h);
				}
				$("#showQueryDataArea").html(rHtml);
			},function(type,msg,data){
				showMsg(msg,"提示：");
			});
		},"json");
    }
});
$('#operationModal').validate({
	rules:{
		time:{
			required: true
		}, quanNum:{
			required: true
		}, money:{
			required: true,
			number:true,
			min:0
		}, buShiTime:{
			required: true
		}
	}, submitHandler:function(form){
		var ticketIndex = $("#operationModal").data("data").ticketIndex;
		if(ticketIndex == 5){
			guaShiForm();
		} else if(ticketIndex == 1){
			buShiForm();
		}
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
function guaShi(td){
	var tr = $(td).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#guaShiTemplate").html());
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
	$("#operationModal").data("tr",tr);
	$("#operationModal").data("data",data);
	$("#operationModal .modal-body").html(rHtml);
	$("#operationModal").modal("show");
}

function buShi(td){
	var tr = $(td).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#buShiTemplate").html());
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
	$("#operationModal").data("tr",tr);
	$("#operationModal").data("data",data);
	$("#operationModal .modal-body").html(rHtml);
	$("#operationModal").modal("show");
}
function guaShiForm(){
	var data = $("#operationModal").data("data");
	var hptLossInfo = {};
//	/**挂失日期*/
	hptLossInfo.reportOfLossDate = $("#operationModal [name='time']").val();
//	/**挂失人*/
	hptLossInfo.fmlItemId = data.fmlItemId;
//	/**挂失的购房券*/
	hptLossInfo.ticketId = data.hptId;
	hptLossInfo.remark = $("#operationModal [name='remark']").val();
	
	var logData = {};
	logData.resideName = data.name;
	logData.resideIdNumber = data.idNumber;
	logData.ticketNumber = data.ticketNumber;
	logData.bonus = data.bonus;
	logData.makeTime = data.makeTime;
	logData.reportOfLossDate = hptLossInfo.reportOfLossDate;
	logData.remark = hptLossInfo.remark;
	$.post("housePurchaseMansgement/hptLossAndMendGuaShi.do",{
		hptLossInfo:JSON.stringify(hptLossInfo)
	},function(data){
		actionFormate(data, true, function(type, msg, data) {
			var template = Handlebars.compile($("#logGSItemTemplate").html());
			var logHtml = template(logData);
			operationLog("购房券挂失","购房券挂失",logHtml);
    		var tr = $("#operationModal").data("tr");
    		$("#operationModal").modal("hide");
    		$("a",tr).remove();
		});
	},"json");
}
function buShiForm(){
	var data = $("#operationModal").data("data");
	var htpMendInfo = {};
//	/**补券日期*/
	htpMendInfo.mendDate = $("#operationModal [name='buShiTime']").val();
//	/**补券人*/
	htpMendInfo.onwerId=data.fmlItemId;
//	/**找补的购房券*/
	htpMendInfo.ticketId = data.hptId;
	var housePuraseTicket = {};
//	/** 补贴金额 */
	housePuraseTicket.bonus = $("#operationModal [name='money']").val();
//	/** 制券日期 */
	housePuraseTicket.makeDate = $("#operationModal [name='time']").val();
//	/** 券号 */
	housePuraseTicket.ticketNumber =  $("#operationModal [name='quanNum']").val();
//	/** 购房券所有者id */
	housePuraseTicket.fmlItemId = data.fmlItemId;
//	/** 购房券所有者身份证 */
	housePuraseTicket.idNumber = data.idNumber;
//	/** 姓名 */
	housePuraseTicket.name = data.name;
	
	var logData = {};
	logData.resideName = data.name;
	logData.resideIdNumber = data.idNumber;
	logData.ticketNumber = data.ticketNumber;
	logData.bonus = data.bonus;
	logData.makeTime = data.makeTime;
	logData.newTicketNumber = housePuraseTicket.ticketNumber;
	logData.newBonus = housePuraseTicket.bonus;
	logData.newMakeTime = housePuraseTicket.makeDate;
	logData.mendDate = htpMendInfo.mendDate;

	$.post("housePurchaseMansgement/hptLossAndMendBuShi.do",{
		htpMendInfo:JSON.stringify(htpMendInfo),
		housePuraseTicket:JSON.stringify(housePuraseTicket)
	},function(data){
		actionFormate(data, true, function(type, msg, data) {
			var template = Handlebars.compile($("#logBQItemTemplate").html());
			var logHtml = template(logData);
			operationLog("购房券补券","购房券补券",logHtml);
    		var tr = $("#operationModal").data("tr");
    		$("#operationModal").modal("hide");
    		$("a",tr).remove();
		});
	},"json");
}