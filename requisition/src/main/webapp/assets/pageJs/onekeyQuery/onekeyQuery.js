$("#queryBtn").click(function(){
	var personData = $("#queryVal").data("data");
	if(!personData) return;
	$.get(sendUrl.onekeyQuery_getInfo,{
		id:personData.id
	},function(d){
		actionFormate(d, true,function(type,msg,data){
			if(data.unionSuggestionPath){
				var path = data.unionSuggestionPath;
				data.unionSuggestionPathName = path.substring(0,path.indexOf("/"));
				data.unionSuggestionPathVal = path.substring(path.indexOf("/") + 1);
			}
			var template = Handlebars.compile($("#entrytemplate").html());
			if(data.evidencePath){
				var path = data.evidencePath;
				data.evidencePathName = path.substring(0,path.indexOf("/"));
				data.evidencePathVal = path.substring(path.indexOf("/") + 1);
			}
			var html = $(template(data));
			$("#dataArea").html(html);
			var fileItem = [];
			var docPath = data.houseImgPath;
			if(docPath){
				var paths = docPath.split("|");
				for ( var p in paths) {
					var path = paths[p];
					fileItem.push(path.substring(path.indexOf("/") + 1));
				}
			}
			$("#showFileItem").data("fileItem",fileItem);
			$("#showFileItem").click(function(){
				var fileItem = $(this).data("fileItem");
				if(!fileItem || fileItem.length <= 0) return;
				$.initShowImage(fileItem);
			});
			
			$.get(sendUrl.fmlItem_getHptInfo,{
				id:personData.id
			},function(d){
				var tempData = actionFormate(d, false) || {};
				var template = Handlebars.compile($("#gouFangQuanMsgTemplate").html());
				if(tempData.evidencePath){
					var path = tempData.evidencePath;
					tempData.evidencePathName = path.substring(0,path.indexOf("/"));
					tempData.evidencePathVal = path.substring(path.indexOf("/") + 1);
				}
				var html = template(tempData);
				$("#showHPTArea").html(html);
			},"json");
		});
	},"json");
});
function showProInfo(id){
	$.get("share/projectInfo",{
		id:id
	},function(html){
		$("#showProInfoJArea").html(html);
		$("#showProInfoJModal").modal("show");
	});
}
$.dropDownInput({
	inputId : "#queryVal",
	dropDownId : "#queryDown",
	url : sendUrl.onekeyQuery_getFuzzy,
	urlType:"get",
	valName:"fuzzy",
	selectVal:"valName",
	templateId : "#queryDownTemplate",
	lastFn:function(data){
		return actionFormate(data,false,function(type,msg,data){
			if(data){
				for (var i = 0; i < data.length; i++) {
					var d = data[i];
					d.valName = d.name + "-" + d.idNumber;
				}
			}
		}) || [];
	},itemClick:function(data){
		$("#queryVal").val(data.name);
		$("#queryVal").data("data",data);
	}
});