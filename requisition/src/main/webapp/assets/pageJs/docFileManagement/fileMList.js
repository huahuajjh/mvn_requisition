var pageIndex = 1;
showMenu();
$("#searchBtn").click(function(){
	queryByKeywords();
});
function showImg(img){
	if(!img){return;}
	$.initShowImage([img]);
}
function showMenu(){
	$.get(sendUrl.fileType_getAll,function(data){
		actionFormate(data, false, function(type, msg, datas) {
			var dataArr = [];
			initMenuDatas(null,dataArr,datas);
			for ( var i in dataArr) {
				initMenuDoms($("#menuArea"),dataArr[i]);
			}
			$(".menu ul li").menu();
		});
	},"json");
}
function initMenuDatas(id,data,datas){
	for ( var i in datas) {
		var t  = datas[i];
		if(!id && !t.pid){
			data.push(t);
			initMenuDatas(t.id,t,datas);
		}else if(id == t.pid){
			if(!data.child) data.child = [];
			data.child.push(t);
			initMenuDatas(t.id,t,datas);
		}
	}
}
function initMenuDoms(dom,data){
	var li =  createMenuItem(dom,data);
	if(data.child && data.child.length > 0){
		for ( var i in data.child) {
			initMenuDoms(li,data.child[i]);
		}
	}
}
function createMenuItem(dom,data){
	dom = $(dom);
	var template = Handlebars.compile($("#menuTemplate").html());
	var html = $(template(data));
	html.data("data",data);
	var li = null;
	if(dom[0].tagName.toLowerCase() == "ul"){
		$("> a",html).prepend('<i class="fa fa-angle-double-right"></i>');
		dom.append(html);
		return html;
	} else if(dom[0].tagName.toLowerCase() == "li"){
		li = dom;
	} else {
		li = dom.closest("li");
	}
	if($("> ul",li).size() > 0){
		$("> ul",li).append(html);
	} else {
		var ul = $("<ul>");
		ul.append(html);
		li.append(ul);
	}
	return html;
}
function menuClick(dom){
	var li = $(dom).closest("li");
	var data = li.data("data");
	if(data.child) return;
	$("#textTitle").data("typeId",data.id);
	queryByTypeId();
}
var goUrl = null;
function queryByTypeId(){
	goUrl = sendUrl.archiveFile_queryByTypeId;
	goPage(1);
}
function queryByKeywords(){
	goUrl = sendUrl.archiveFile_queryByKeywords;
	goPage(1);
}
function goPage(index){
	pageIndex = index;
	var typeId = $("#textTitle").data("typeId");
	var title = $("#textTitle").val();
	$.post(goUrl,{
		keywords:title,
		typeId:typeId,
		index:pageIndex,
		size:10
	},function(data){
		actionFormate(data, true, function(type, msg, data) {
			$("#pageIndex").html(pageIndex);
			$("#pageNum").html(Math.ceil(data.totalCount / 10));
			initNextPerBtn(index,Math.ceil(data.totalCount / 10));
			$("#infoList").html("");
			var array = data.datas || [];
			for (var i = 0; i < array.length; i++) {
				var d = array[i];
				var template = Handlebars.compile($("#listTemplate").html());
				var html = $(template(d));
				html.data("data",d);
				$("#infoList").append(html);
			}
		});
	},"json");
}
function showListInfo(dom){
	var li = $(dom).closest("li");
	var data = li.data("data");
	var template = Handlebars.compile($("#listInfoTemplate").html());
	if(data.filePath){
		var path = data.filePath;
		var pathArr = path.split("|");
		var filses = [];
		for (var i = 0; i < pathArr.length; i++) {
			var pt = pathArr[i];
			var fileName = pt.substring(0, pt.indexOf("/"));
			var fileVal = pt.substring(pt.indexOf("/") + 1);
			filses.push({
				fileName:fileName,
				fileVal:fileVal
			});
		}
		data.files = filses;
	}
	var html = $(template(data));
	$("#infoArea").html(html);
	$("#showMsgInfo").modal("show");
}
function initNextPerBtn(index,pageCount){
	if(index <= 1){
		$("#perBtn").prop("disabled",true);
	} else {
		$("#perBtn").prop("disabled",false);
	}
	if(index == pageCount){
		$("#nextBtn").prop("disabled",true);
	} else {
		$("#nextBtn").prop("disabled",false);
	}
}
function nextData(){
	pageIndex += 1;
	goPage(pageIndex);
}
function perData(){
	pageIndex -= 1;
	goPage(pageIndex);
}