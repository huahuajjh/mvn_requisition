setProListModal("#selectProInfoModal",function(data){
	if(data){
		$("#queryPrName").data("data",data);
		$("#queryPrName").val(data.proName);
	}
});
var tableData = $.generateData({
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : sendUrl.visits_queryByFuzzy,
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		var tempData = {};
		var queryPhone = $("#queryPhone").val();
		if(queryPhone){
			tempData.tel = queryPhone;
		}
		var queryName = $("#queryName").val();
		if(queryName){
			tempData.name = queryName;
		}
		if($("#queryPrName").val()){
			tempData.proName = $("#queryPrName").val()
		}
		tempData.indexPage = data.pageIndex;
		tempData.indexSize = data.pageNum;
		data.fuzzy = JSON.stringify(tempData);
	},
	lastFn : function(data) {
		var tempData = actionFormate(data, false) || {totalCount:0,datas:[]};
		$("#countArea").html(tempData.totalCount);
		return tempData;
	}
});
$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
});
$("#editInfoModal").validate({
	rules: {
		name: {
		    required: true
		}, phone:{
			required: true
		}, address:{
			required: true
		}, proName:{
			required: true
		}, time:{
			required: true
		}
	},  submitHandler:function(form){
		var data = $("#editInfoModal").data("data");
		var subData ={};
		subData.id = data.id;
		subData.visitProId = data.visitProId;
		subData.proName = data.proName;
		subData.visitTime = data.visitTime;
		subData.visitorName = $("[name='name']",form).val();
		subData.visitorTel = $("[name='phone']",form).val();
		subData.visitorAddr = $("[name='address']",form).val();
		subData.otherMsg = $("[name='otherMsg']",form).val();
		subData.visitMaterialPath = $("[name='fileVal']",form).val();
		subData.visitReason = $("[name='reasonMsg']",form).val();
		subData.img = $("#yuLanBtn").data("img");
		
		subData.visitsWay = $("[name='visitsWay']",form).val();
		
		$.post(sendUrl.visits_modify,{
			visits:JSON.stringify(subData)
		},function(d){
			actionFormate(d, true,function(){
				operationLog("修改上访上信息","修改上访上信息");
				var tr = $('#editInfoModal').data("dom");
				data.visitorName = subData.visitorName;
				data.visitorTel = subData.visitorTel;
				data.visitorAddr = subData.visitorAddr;
				data.otherMsg = subData.otherMsg;
				data.visitMaterialPath = subData.visitMaterialPath;
				data.visitReason = subData.visitReason;
				data.img = subData.img;
				data.visitsWay = subData.visitsWay;
				tr.remove();
				var template = Handlebars.compile($("#entrytemplate").html());
				var html = $(template(data));
				html.data("data",data);
				$("#dataTbody").prepend(html);
				$("#editInfoModal").modal("hide");
			});
		},"json");
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
function showProInfo(id){
	$.get("share/projectInfo",{
		id:id
	},function(html){
		$("#showProInfoArea").html(html);
		$("#showProInfoModal").modal("show");
	});
}
function deleteInfo(dom){
	if(!confirm("确定要删除？")) return;
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	$.post("messageManagement/visitQuerydelete",{
		id:data.id
	},function(data){
		actionFormate(data, true,function(){
			tr.remove();
		});
	},"json");
}
function editInfo(dom){
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	if(data.visitMaterialPath){
		var path = data.visitMaterialPath;
		data.visitMaterialPathName = path.substring(0,path.indexOf("/"));
	}
	var template = Handlebars.compile($("#editModalTemplate").html());
	var html = $(template(data));
	$("#infoDataBody").html(html);
	initDom(data,html);
	$('#editInfoModal').data("data",data);
	$('#editInfoModal').data("dom",tr);
	$('#editInfoModal').modal('show');
}
function showInfo(dom){
	var data = $(dom).closest("tr").data("data");
	if(data.visitMaterialPath){
		var path = data.visitMaterialPath;
		data.fileValue = path.substring(path.indexOf("/")+1);
		data.fileName = path.substring(0,path.indexOf("/"));
	}
	var template = Handlebars.compile($("#infoModalTemplate").html());
	var html = $(template(data));
	$("#showImage",html).data("img",data.image);
	$("#showImage",html).click(function(){
		var img = $(this).data("img");
		if(!img) return;
		$.initShowImage([img]);
	});
	$("#infoBody").html(html);
	$('#infoModal').modal('show');
}
function initDom(data,html){
	submitFileStyle("#upLoadFile","POLICY_FILE",function(data){
		return actionFormate(data, true);
	});
	$("#yuLanBtn").data("img",data.img);
	$("#upFile").change(function(){
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
	$("#yuLanBtn").click(function(){
		var img = $(this).data("img");
		if(!img) return;
		$.initShowImage([img]);
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
		$("#zhaoBtn,#yuLanBtn,#paiZhaoFileCheckState,#upBtn").css("display","none");
		$.post("share/saveFile",{
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
function upFileZhaoPian(){
	$("#upFile").click();
}
function paiZhao(){
	$.get("share/photographs",function(html){
		$("#phonePaiZhaoBody").html(html);
		$("#phonePaiZhaoModal").modal("show");
	});
}