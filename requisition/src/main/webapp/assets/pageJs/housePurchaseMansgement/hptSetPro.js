var tableData = $.generateData({
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : "housePurchaseMansgement/hptSetProList",
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		var queryPrName =  $("#queryPrName").val();
		if(queryPrName){
			data.queryProName = queryPrName;
		}
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
setProListModal("#selectProInfoModal",function(data){
	if(data){
		$("#queryPrName").data("data",data);
		$("#queryPrName").val(data.proName);
	}
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
function upFileZhaoPian(){
	$("#upFile").click();
}
function paiZhao(){
	$.get("share/photographs",function(html){
		$("#phonePaiZhaoBody").html(html);
		$("#phonePaiZhaoModal").modal("show");
	});
}
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
		$.post("share/saveFile",{
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
$("#faFangSelectData").click(function(){
	var clickModal = {};
	clickModal.money = 0;
	clickModal.item = [];
	$("#dataTbody input[type='checkbox'][name='check']:checked").each(function(){
		var data = $(this).closest("tr").data("data");
		clickModal.money = clickModal.money + data.bonus;
		clickModal.item.push(data);
	});
	if(clickModal.item.length < 1){
		$(this).popover({
			content:"请选择人员"
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
		$.post("housePurchaseMansgement/hptSetAdd",{
			dataJson:JSON.stringify(datas)
		},function(data){
			actionFormate(data, true, function(type, msg, data) {
				var template = Handlebars.compile($("#logItemTemplate").html());
				var logHtml = template(datas);
				operationLog("购房券户发放","购房券户发放",logHtml);
				$("#lingQuModal").modal("hide");
				tableData.goPage(1);
    		});
		},"json");
    }
});