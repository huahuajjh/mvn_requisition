summernoteDom("[data-plugin-summernote]");
$("#selectType").mSelect({
	url:sendUrl.fileType_queryByPid,
	type:"get",
	onBeforeRequest:function(data){
		data.pid = data.id;
	}, onAfterRequest:function(data){
		var dataArr = actionFormate(data, false) || [];
		for (var i = 0; i < dataArr.length; i++) {
			var d = dataArr[i];
			d.name = d.title;
		}
		return dataArr;
	}, onHide:function(){
		$("#selectBtn").removeClass("active");
	}, onOpen:function(){
		$("#selectBtn").addClass("active");
	}, onClickItem:function(data,state){
		if(!state){
			$("#selectBtn").data("data",data);
			$("#selectBtn").data("datas",$("#selectType").mSelect().getDatas());
			$("#selectBtn > span").html(data.name);
		}
	}
});
$("#selectBtn").click(function(){
	$("#selectType").mSelect().toggleShow();
});
submitFileStyle("#upLoadFile","EVIDENCE_FILE",function(data){
	actionFormate(data, true,function(type,msg,d){
		var file = {};
		file.fileValue = d;
		file.fileName = d.substring(0,d.indexOf("/"));
		file.filePath = d.substring(d.indexOf("/") + 1);
		var template = Handlebars.compile($("#fileItemTemplate").html());
		var html = $(template(file));
		html.data("data",file);
		$("#fileItems").append(html);
	});
});
$("#addZCFG").submit(function(){
	var data = getData();
	if(!data.title){
		$('#title').popover({
			content:"请输入标题",
			placement:"left"
		});
		$('#title').popover("show");
		setTimeout(function(){
			$('#title').popover("destroy");
		}, 1000);
	}
	if(!data.fileTypeId){
		$('#selectBtn').popover({
			content:"请选择类型",
			placement:"left"
		});
		$('#selectBtn').popover("show");
		setTimeout(function(){
			$('#selectBtn').popover("destroy");
		}, 1000);
	}
	if(!data.title || !data.fileTypeId) return;
	
	$.post(sendUrl.archiveFile_addFile,{
		file:JSON.stringify(data)
	},function(d){
		actionFormate(d, true,function(){
			var template = Handlebars.compile($("#logItemTemplate").html());
			var logHtml = template(data);
			operationLog("录入档案文件","录入档案文件",logHtml);
			restForm();
		});
	},"json");
	return false;
});
$("#yuLanBtn").click(function(){
	var img = $(this).data("img");
	if(!img) return;
	$.initShowImage([img]);
});
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
function removeFile(dom){
	$(dom).closest(".form-control-static").remove();
}
function restForm(){
	$("#title").val("");
	$("#fileItems").html("");
	$("[data-plugin-summernote]").code("");
	$("#selectBtn").data("data",null);
	$("#selectBtn > span").html("请选择");
	$("#selectType").mSelect().init();
	$("#yuLanBtn").data("img",null).css("display","none");
}
function getData(){
	var data = {};
	data.title = $("#title").val();
	var typeIds = [];
	var selectDatas = $("#selectBtn").data("datas") || [];
	for (var i = 0; i < selectDatas.length; i++) {
		var d = selectDatas[i];
		typeIds.push(d.id);
	}
	data.fileTypeId = typeIds.join(",");
	var d = $("#selectBtn").data("data") || {};
	data.typeStr = d.title;
	var fileValArr = [];
	$("#fileItems > .form-control-static").each(function(){
		var data = $(this).data("data");
		fileValArr.push(data.fileValue);
	});
	data.filePath = fileValArr.join("|");
	data.article = $("[data-plugin-summernote]").code();
	data.createId = getCookie("login");
	data.img = $("#yuLanBtn").data("img");
	return data;
}