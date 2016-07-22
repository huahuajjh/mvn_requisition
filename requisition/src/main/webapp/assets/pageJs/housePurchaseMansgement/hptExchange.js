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
	submitHandler:function(form){
		var idNumber = $("[name='idNumber']",form).val();
		var ticketNumber = $("[name='quanNumber']",form).val();
		if(!idNumber && !ticketNumber){
			$(form).validate().showErrors({
				"idNumber" : "身份证和券号必须填写一个",
				"quanNumber": "身份证和券号必须填写一个"
			});
			return;
		}
		$.post("housePurchaseMansgement/hptExchangeGet.do",{
			idNumber:idNumber,
			ticketNumber:ticketNumber
		},function(data){
			actionFormate(data, true,function(type,msg,datas){
				datas = datas || [];
				var template = Handlebars.compile($("#entrytemplate").html());
				var html = template();
				var rHtml = $(html);
				for (var i = 0; i < datas.length; i++) {
					var data = datas[i];
					data.isCheck = data.ticketState == "RECEIVED" || data.ticketState == "NORMAL";
					data.ticketName = toTicketStr(data.ticketState);
					data.ticketIndex = toTicketNumber(data.ticketState);
					var t = Handlebars.compile($("#dataItemTemplate").html());
					var h = $(t(data));
					h.data("data",data);
					$("#dataItems",rHtml).append(h);
				}
				$("#selectHPTList").html(rHtml);
			});
		},"json");
    }
});
$("#huanFaModal").validate({
	rules: {
		quanNum: {
			required: true
		}, quanNewMoney: {
			required: true,
			number:true,
			min:0
		},name: {
			required: true
		},time: {
			required: true
		}, zTime: {
			required: true
		}
	}, submitHandler:function(form){
		var data = $("#huanFaModal").data("data");
		var hptExchangeInfo = {};
//		/**所有者id*/
		hptExchangeInfo.ownerId = data.fmlItemId;
//		/**换发日期*/
		hptExchangeInfo.exchengeDate = $("#huanFaModal [name='time']").val();
//		/**领用证明*/
		hptExchangeInfo.evidencePath = $("#yuLanBtn").data("img");
//		/**老券id*/
		hptExchangeInfo.oldTicketId = data.hptId;
//		/**领用人*/
		hptExchangeInfo.gettingUser = $("#huanFaModal [name='name']").val();
		var housePuraseTicket = {};
//		/** 补贴金额 */
		housePuraseTicket.bonus = $("#huanFaModal [name='quanNewMoney']").val();
//		/** 制券日期 */
		housePuraseTicket.makeDate = $("#huanFaModal [name='zTime']").val();
//		/** 券号 */
		housePuraseTicket.ticketNumber = $("#huanFaModal [name='quanNum']").val();
//		/** 购房券状态 */
		housePuraseTicket.state = data.ticketIndex;
//		/** 购房券所有者id */
		housePuraseTicket.fmlItemId = data.fmlItemId;
//		/** 购房券所有者身份证 */
		housePuraseTicket.idNumber = data.idNumber;
//		/** 姓名 */
		housePuraseTicket.name = $("#huanFaModal [name='name']").val();
		
		var logData = {};
		logData.resideName = data.name;
		logData.resideIdNumber = data.idNumber;
		logData.oldTicketNumber = data.ticketNumber;
		logData.oldBonus = data.bonus;
		logData.oldMakeTime = data.makeTime;
		logData.newTicketNumber = housePuraseTicket.ticketNumber;
		logData.newBonus = housePuraseTicket.bonus;
		logData.newMakeTime = housePuraseTicket.makeDate;
		logData.name = hptExchangeInfo.gettingUser;
		logData.gettingDate = hptExchangeInfo.exchengeDate;
		logData.evidenceOfGetting = hptExchangeInfo.evidencePath;
		
		$.post("housePurchaseMansgement/hptExchangeAdd.do",{
			hptExchangeInfo:JSON.stringify(hptExchangeInfo),
			housePuraseTicket:JSON.stringify(housePuraseTicket)
		},function(data){
			actionFormate(data, true,function(){
				var template = Handlebars.compile($("#logItemTemplate").html());
				var logHtml = template(logData);
				operationLog("购房券换发","购房券换发",logHtml);
				var tr = $("#huanFaModal").data("tr");
				$("a",tr).remove();
				$("#huanFaModal").modal("hide");
			});
		},"json");
    }
});
$("#phonePaiZhaoModal").modal({
	show:false, 
	keyboard:false,
	backdrop:"static",
});
$("#phonePaiZhaoModal").on("hidden.bs.modal",function(){
	if($(this).data("btnState") == true){
		var imgData = $(this).data("imgData");
		$("#paiZhaoFileLoginState").css("display","inline");
		$("#zhaoBtn,#yuLanBtn,#paiZhaoFileCheckState").css("display","none");
		//提交数据
		$.post("share/saveFile.do",{
			baseSFFile:imgData
		},function(data){
			actionFormate(data, true, function(type, msg, data) {
				$("#yuLanBtn").data("img",data);
			});
			$("#paiZhaoFileLoginState").css("display","none");
			$("#zhaoBtn,#yuLanBtn,#paiZhaoFileCheckState").css("display","inline");
		},"json");
	}
});
function showHFModal(td){
	var tr = $(td).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#modalDataTemplate").html());
	var html = template(data);
	var rHtml = $(html);
	$('[data-plugin-datepicker]',rHtml).each(function() {
		var $this = $(this), opts = {};
		$this.themePluginDatePicker(opts);
	});
	$('[data-plugin-masked-input]',rHtml).each(function() {
		var $this = $(this), opts = {};

		var pluginOptions = $this.data('plugin-options');
		if (pluginOptions)
			opts = pluginOptions;
		$this.themePluginMaskedInput(opts);
	});
	$("#yuLanBtn",rHtml).click(function(){
		var img = $(this).data("img");
		if(!img) return;
		$.initShowImage([img]);
	});
	$("#upFile",rHtml).change(function(){
		var val = $(this).val();
		if(!val) return;
		$("#paiZhaoFileLoginState").css("display","inline");
		$("#zhaoBtn,#yuLanBtn,#paiZhaoFileCheckState,#upBtn").css("display","none");
		submitFile(this,{
			fileType:"EVIDENCE_FILE"
		},"json",function(data){
			actionFormate(data, true, function(type, msg, data) {
				data = data.substring(data.indexOf("/")+1);
				$("#yuLanBtn").data("img",data);
			});
			$("#paiZhaoFileLoginState").css("display","none");
			$("#zhaoBtn,#yuLanBtn,#paiZhaoFileCheckState,#upBtn").css("display","inline");
		},function(e){
			$("#paiZhaoFileLoginState").css("display","none");
			$("#zhaoBtn,#yuLanBtn,#paiZhaoFileCheckState,#upBtn").css("display","inline");
		});
	});
	$("#huanFaModal .modal-body").html(rHtml);
	$("#huanFaModal").data("tr",tr);
	$("#huanFaModal").data("data",data);
	$("#huanFaModal").modal("show");
}
function showProInfo(id){
	$.get("share/projectInfo.do",{
		id:id
	},function(html){
		$("#showProInfoArea").html(html);
		$("#showProInfoModal").modal("show");
	});
}
function paiZhao(){
	$.get("share/photographs.do",function(html){
		$("#phonePaiZhaoBody").html(html);
		$("#phonePaiZhaoModal").modal("show");
	});
}
function upFileZhaoPian(){
	$("#upFile").click();
}