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
		$.post("housePurchaseMansgement/hptUseAndCashGet.do",$("#queryData").serialize(),function(data){
			actionFormate(data, true,function(type,msg,datas){
				datas = datas || [];
				var template = Handlebars.compile($("#entrytemplate").html());
				var html = template();
				var rHtml = $(html);
				for (var i = 0; i < datas.length; i++) {
					var data = datas[i];
					data.isCheck = data.ticketState == "RECEIVED";
					data.ticketName = toTicketStr(data.ticketState);
					data.ticketIndex = toTicketNumber(data.ticketState);
					var t = Handlebars.compile($("#dataItemTemplate").html());
					var h = $(t(data));
					h.data("data",data);
					$("#dataItems",rHtml).append(h);
				}
				$("#goufangQuanShiYong").html(rHtml);
			});
		},"json");
    }
});

$("#useModal").validate({
	rules:{
		time:{
			required:true
		}, type:{
			required:true
		}
	}, submitHandler:function(form){
		var data = $("#useModal").data("data");
		var subData = {};
//		/**购房券所有者id*/
		subData.ownerId = data.fmlItemId;
//		/**购房券id*/
		subData.ticketId = data.hptId;
//		/**使用日期*/
		subData.usingDate = $("#useModal [name='time']").val();
//		/**使用类型*/
		subData.usingType= $("#useModal [name='type']").val();
//		/**使用去向*/
		subData.usingToWhere = $("#useModal [name='quXiang']").val();
//		/**情况说明*/
		subData.situationExplain = $("#useModal [name='shuoMing']").val();
//		/**相关凭证*/
		subData.evidencePath = $("#useModal [name='evidencePath']").val();
		//拍照
		subData.image = $("#yuLanBtn").data("img");
		
		var logData = {};
		logData.resideName = data.name;
		logData.resideIdNumber = data.idNumber;
		logData.ticketNumber = data.ticketNumber;
		logData.bonus = data.bonus;
		logData.makeTime = data.makeTime;
		logData.usingDate = subData.usingDate;
		logData.usingType = $("#useModal [name='type'] option:selected").html();;
		logData.usingToWhere = subData.usingToWhere;
		logData.situationExplain = subData.situationExplain;
		logData.evidencePath = subData.evidencePath;
		logData.image = subData.image;
		
		$.post("housePurchaseMansgement/hptUseAndCashAdd.do",{
			dataJson:JSON.stringify(subData)
		},function(data){
	      	actionFormate(data, true, function(type, msg, data) {
	    		var template = Handlebars.compile($("#logItemTemplate").html());
	    		var logHtml = template(logData);
	      		operationLog("购房券的使用与兑现","购房券的使用与兑现",logHtml);
	      		var tr = $("#useModal").data("tr");
	      		$("a",tr).remove();
	      		$("#useModal").modal("hide");
    		});
		},"json");
	}
});
function showUseModal(td){
	var tr = $(td).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#modalDatatemplate").html());
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
	$("#useModal").data("data",data);
	$("#useModal").data("tr",tr);
	$("#useModal .modal-body").html(rHtml);
	submitFileStyle("#upLoadFile","EVIDENCE_FILE",function(data){
		return actionFormate(data, true);
	});
	$("#useModal").modal("show");
}
function showProInfo(id){
	$.get("share/projectInfo.do",{
		id:id
	},function(html){
		$("#showProInfoArea").html(html);
		$("#showProInfoModal").modal("show");
	});
}
function upFileZhaoPian(){
	$("#upFile").click();
}
function paiZhao(){
	$.get("share/photographs.do",function(html){
		$("#phonePaiZhaoBody").html(html);
		$("#phonePaiZhaoModal").modal("show");
	});
}
$("#phonePaiZhaoModal").on("hidden.bs.modal",function(){
	if($(this).data("btnState") == true){
		var imgData = $(this).data("imgData");
		$("#paiZhaoFileLoginState").css("display","inline");
		$("#zhaoBtn,#yuLanBtn,#paiZhaoFileCheckState,#upBtn").css("display","none");
		$.post("share/saveFile.do",{
			baseSFFile:imgData
		},function(data){
			actionFormate(data, true, function(type, msg, data) {
				$("#yuLanBtn").data("img",data);
			});
			$("#paiZhaoFileLoginState").css("display","none");
			$("#zhaoBtn,#yuLanBtn,#paiZhaoFileCheckState,#upBtn").css("display","inline");
		},"json");
	}
});
$("#phonePaiZhaoModal").modal({
	show:false, 
	keyboard:false,
	backdrop:"static",
});