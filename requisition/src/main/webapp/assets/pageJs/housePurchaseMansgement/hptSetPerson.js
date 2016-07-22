setPersonListModal("#selectPerson",function(data){
	if(data){
		$("#idNumber").val(data.idNumber);
		$("#queryBtn").click();
	}
});
$("#queryData").validate({
	rules:{
		idNumber:{
			required: true
		}
	}, submitHandler:function(form){
		$.post("housePurchaseMansgement/hptSetPersonGet.do",$("#queryData").serialize(),function(data){
			actionFormate(data, true,function(type,msg,datas){
				datas = datas || [];
				var template = Handlebars.compile($("#entrytemplate").html());
				var html = template();
				var rHtml = $(html);
				for (var i = 0; i < datas.length; i++) {
					var data = datas[i];
					data.isCheck = data.ticketState == "NORMAL";
					data.ticketName = toTicketStr(data.ticketState);
					data.ticketIndex = toTicketNumber(data.ticketState);
					var t = Handlebars.compile($("#dataItemTemplate").html());
					var h = $(t(data));
					h.data("data",data);
					$("#dataItems",rHtml).append(h);
				}
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
				$("#showQueryDataArea").data("data",datas);
				$("#showQueryDataArea").html(rHtml);
			});
		},"json");
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
$("#showQueryDataArea").validate({
	rules: {
		name: {
			required: true
		}, idNumber: {
			required: true
		}, time: {
			required: true
		}
	}, submitHandler:function(form){
		
		var data = $("#showQueryDataArea").data("data");
		
		var datas = [];
		$("#dataItems [name='check']:checked").each(function(){
			var data = $(this).closest("tr").data("data");
			
			var subData = {};
//			/**购房券id*/
			subData.ticketId = data.hptId;
//			/**购房券所有者*/
			subData.ownerId = data.fmlItemId;
//			/**领取凭证*/
			subData.evidenceOfGetting = $("#yuLanBtn").data("img");
//			/**领用人姓名*/
			subData.name = $("#showQueryDataArea [name='name']").val();
//			/**领用人身份证*/
			subData.idNumber = $("#showQueryDataArea [name='idNumber']").val();
//			/**领用时间*/
			subData.gettingDate = $("#showQueryDataArea [name='time']").val();
			//领取备注
			subData.remark = $("#showQueryDataArea [name='remark']").val();
			
			subData.resideName = data.name;
			subData.resideIdNumber = data.idNumber;
			subData.resideTicketNumber = data.ticketNumber;
			subData.resideBonus = data.bonus;
			subData.resideMakeTime = data.makeTime;
			
			datas.push(subData);
		});
		if(datas.length <= 0){
			$("#subBtn").popover({
				content : "请选择需要发放的购房券",
				placement : "left"
			});
			$("#subBtn").popover("show");
			setTimeout(function() {
				$("#subBtn").popover("destroy");
			}, 1000);
			return;
		}
		
		$.post("housePurchaseMansgement/hptSetAdd.do",{
			dataJson:JSON.stringify(datas)
		},function(data){
			actionFormate(data, true, function(type, msg, data) {
				var template = Handlebars.compile($("#logItemTemplate").html());
				var logHtml = template(datas);
				operationLog("购房券个人发放","购房券个人发放",logHtml);
				$("#showQueryDataArea").html("");
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
function showProInfo(id){
	$.get("share/projectInfo",{
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