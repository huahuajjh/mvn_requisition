$("#querySelectType").mSelect({
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
	},onHide:function(){
		$("#querySelectBtn").removeClass("active");
	}, onOpen:function(){
		$("#querySelectBtn").addClass("active");
	}, onClickItem:function(data,state){
		var nameArr = [];
		var datas = $("#querySelectType").mSelect().getDatas();
		for (var i = 0; i < datas.length; i++) {
			var d = datas[i];
			nameArr.push(d.title);
		}
		$("#querySelectBtn").data("datas",datas);
		$("#querySelectBtn > span").html(nameArr.join(","));
	}
});
$("#querySelectBtn").click(function(){
	$("#querySelectType").mSelect().toggleShow();
});
var tableData = $.generateData({
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : sendUrl.archiveFile_queryByFuzzy,
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		var title = $("#queryTitle").val();
		var typeData = $("#querySelectBtn").data("datas");
		var queryData = {};
		queryData.pageIndex = data.pageIndex;
		queryData.pageSize = data.pageNum;
		if(title){
			queryData.title = title;
		}
		if(title){
			queryData.title = title;
		}
		if(typeData){
			var ids = [];
			for (var i = 0; i < typeData.length; i++) {
				var d = typeData[i];
				ids.push(d.id);
			}
			queryData.typeIds = ids.join(",");
		}
		queryData.createId = getCookie("login");
		data.fuzzy = JSON.stringify(queryData);
	}, lastFn : function(data) {
		var tempData = actionFormate(data, false) || {datas:[],totalCount:0};
		$("#countArea").html(tempData.totalCount);
		return tempData;
	}
});
$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
});

$("#editInfoModal").submit(function(){
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
	$.post(sendUrl.archiveFile_modify,{
		file:JSON.stringify(data)
	},function(d){
		actionFormate(d, true,function(type,msg,d){
			var logTemplate = Handlebars.compile($("#logItemTemplate").html());
			var logHtml = logTemplate(data);
			operationLog("编辑档案文件","编辑档案文件",logHtml);
			
			tr.remove();
			var template = Handlebars.compile($("#entrytemplate").html());
			var html = $(template(data));
			html.data("data",data);
			$("#dataTbody").prepend(html);
			$("#editInfoModal").modal("hide");
		});
	},"json");
});
function queryRest(){
	$("#querySelectBtn").data("datas",null);
	$("#querySelectBtn > span").html("请选择");
	$("#queryTitle").val("");
	$("#querySelectType").mSelect().init();
}
function editInfo(dom){
	tr = $(dom).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#editInfoTemplate").html());
	var html = $(template(data));
	$("#editInfoArea").html(html);
	if(data.filePath){
		var path = data.filePath;
		var pathArr = path.split("|");
		for (var i = 0; i < pathArr.length; i++) {
			var pt = pathArr[i];
			var fileName = pt.substring(0, pt.indexOf("/"));
			var fileVal = pt.substring(pt.indexOf("/") + 1);
			var file = {};
			file.fileValue = pt;
			file.fileName = fileName;
			file.filePath = fileVal;
			var template = Handlebars.compile($("#fileItemTemplate").html());
			var h = $(template(file));
			h.data("data",file);
			$("#fileItems").append(h);
		}
	}
	if(data.fileTypeId){
		var typeIds = data.fileTypeId.split(",");
		var typeDatas = [];
		for (var i = 0; i < typeIds.length; i++) {
			var d = typeIds[i];
			typeDatas.push({
				id:d
			});
		}
		$("#selectBtn",html).data("datas",typeDatas);
	}
	$("#selectBtn",html).data("data",{
		title:data.typeStr
	});
	initEditDom(html,data);
	$("#editInfoModal").modal("show");
}
function initEditDom(dom,data){
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
				$("#selectBtn").data("datas",$("#selectType").mSelect().getDatas());
				$("#selectBtn").data("data",data);
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
	if(data.img){
		$("#yuLanBtn",dom).css("display","inline").data("img",data.img);
	}
	$("#yuLanBtn",dom).click(function(){
		var img = $(this).data("img");
		if(!img) return;
		$.initShowImage([img]);
	});
	$("#upFile",dom).change(function(){
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
}
function removeFile(dom){
	$(dom).closest(".form-control-static").remove();
}
function getData(){
	var data = {};
	data.id = tr.data("data").id;
	data.createDate = tr.data("data").createDate;
	data.title = $("#title").val();
	var typeIds = [];
	var selectDatas = $("#selectBtn").data("datas") || [];
	for (var i = 0; i < selectDatas.length; i++) {
		var d = selectDatas[i];
		typeIds.push(d.id);
	}
	data.fileTypeId = typeIds.join(",");
	data.typeStr = $("#selectBtn").data("data").title;
	var fileValArr = [];
	$("#fileItems > .form-control-static").each(function(){
		var data = $(this).data("data");
		fileValArr.push(data.fileValue);
	});
	data.filePath = fileValArr.join("|");
	data.article = $("[data-plugin-summernote]").code();
	data.img = $("#yuLanBtn").data("img");
	return data;
}
function deleteInfo(dom){
	if(!confirm("确定要删除？")) return;
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	$.post(sendUrl.archiveFile_delById,{
		id:data.id
	},function(data){
		actionFormate(data, true,function(type,msg,d){
			tr.remove();
		});
	},"json");
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