setHuListModal("#selectHuPerson",function(data){
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
$("#queryForm").validate({
	rules: {
		idNumber: {
			required: true
		}
	}, submitHandler:function(form){
		$.post("housePurchaseMansgement/hptSetGet.do",$("#queryForm").serialize(),function(data){
			actionFormate(data, true,function(type,msg,datas){
				var template = Handlebars.compile($("#entrytemplate").html());
				var html = template();
				var rHtml = $(html);
				$("#qDataArea").html(html);
				initSubData();
				for ( var i in datas) {
					var d = datas[i];
					d.isCheck = d.ticketState == 'NORMAL';
					d.ticketName = toTicketStr(d.ticketState);
					d.ticketIndex = toTicketNumber(d.ticketState);
					var t = Handlebars.compile($("#famItemTemplate").html());
					var h = t(d);
					var rH = $(h);
					rH.data("data",d);
					$("#famItems").append(rH);
				}
			});
		},"json");
    }
});
$("#lingQuModal").validate({
	rules: {
		name: {
			required: true
		}, idNumber: {
			required: true
		}, time: {
			required: true
		}
	}, submitHandler:function(form){
		var tempData = $("#lingQuModal").data("data");
		var datas = [];
		for ( var i in tempData.item) {
			var item = tempData.item[i];
			var data = {};
//			/**购房券id*/
			data.ticketId = item.hptId;
//			/**购房券所有者*/
			data.ownerId = item.fmlItemId;
//			/**领取凭证*/
			data.evidenceOfGetting = $("#yuLanBtn").data("img");
//			/**领用人姓名*/
			data.name = $("#lingQuModal [name='name']").val();
//			/**领用人身份证*/
			data.idNumber = $("#lingQuModal [name='idNumber']").val();
//			/**领用时间*/
			data.gettingDate = $("#lingQuModal [name='time']").val();
			//领取备注
			data.remark = $("#lingQuModal [name='remark']").val();
			
			data.resideName = item.name;
			data.resideIdNumber = item.idNumber;
			data.resideTicketNumber = item.ticketNumber;
			data.resideBonus = item.bonus;
			data.resideMakeTime = item.makeTime;
			
			datas.push(data);
		}
		$.post("housePurchaseMansgement/hptSetAdd.do",{
			dataJson:JSON.stringify(datas)
		},function(data){
			actionFormate(data, true, function(type, msg, data) {
				var template = Handlebars.compile($("#logItemTemplate").html());
				var logHtml = template(datas);
				operationLog("购房券户发放","购房券户发放",logHtml);
				$("#lingQuModal").modal("hide");
				$("#qDataArea").html("");
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
function initSubData(){
	$("#faFangSelectData").click(function(){
		var clickModal = {};
		clickModal.money = 0;
		clickModal.item = [];
		$("#qDataArea input[type='checkbox'][name='check']:not(:disabled):checked").each(function(){
			var data = $(this).closest("tr").data("data");
			if(data.relationship == "户主"){
				clickModal.huZhuName = data.name;
			}
			clickModal.money = clickModal.money + data.bonus;
			clickModal.item.push(data);
		});
		if(clickModal.item.length < 1){
			$(this).popover({
				content:"请选择需要发放的人员"
			});
			$(this).popover("show");
			setTimeout(function(){
				$("#faFangSelectData").popover('destroy');
			}, 1000);
			return;
		}
		$("#lingQuModal").data("data",clickModal);
		var t = Handlebars.compile($("#lingQuTemplate").html());
		var h = t(clickModal);
		var rH = $(h);
		$("#yuLanBtn",rH).click(function(){
			var img = $(this).data("img");
			if(!img) return;
			$.initShowImage([img]);
		});
		$('[data-plugin-masked-input]',rH).each(function() {
			var $this = $(this), opts = {};
			var pluginOptions = $this.data('plugin-options');
			if (pluginOptions)
				opts = pluginOptions;

			$this.themePluginMaskedInput(opts);
		});
		$('[data-plugin-datepicker]',rH).each(function() {
			var $this = $(this), opts = {};
			$this.themePluginDatePicker(opts);
		});
		$("#upFile",rH).change(function(){
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
		$("#lingQuArea").html(rH);
		$("#lingQuModal").modal("show");
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