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
	url : "projectManagement/queryRemoveInfoList.do",
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		data.idNumber = $("#idNumber").val();
		var queryPrName =  $("#queryPrName").val();
		if(queryPrName){
			data.queryProName = queryPrName;
		}
		data.huZhuName = $("#huZhuName").val();
		data.streetId = $("#street").val();
		data.communityId = $("#community").val();
		data.zuId = $("#zu").val();
		data.address = $("#queryAddressName").val();
	},
	lastFn : function(data) {
		var tempData = actionFormate(data, false);
		if(!tempData){
			$("#countArea").html("0");
			return ;
		}
		$("#countArea").html(tempData.totalCount);
		return tempData;
	}
});
$("#checkAllRem").change(function(){
	var check = $(this).prop("checked");
	$("#dataTbody input[name='check']").prop("checked",check);
});
$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
});
$('#editFamilyInfoModal').modal({
	backdrop : "static",
	keyboard : false,
	show : false
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
$.dropDownInput({
	inputId : "#queryAddressName",
	dropDownId : "#queryAddressDown",
	url : sendUrl.addrProvider_getAddr,
	templateId : "#queryAddressDownTemplate",
	valName:"fuzzy",
	selectVal:"this",
	urlType:"get",
	firstFn:function(data){
		data.code = 2
	},
	lastFn:function(data){
		return actionFormate(data,false);
	}
});
$.dropDownInput({
	inputId : "#huZhuName",
	dropDownId : "#nameQueryPrDown",
	url : sendUrl.onekeyQuery_getFuzzy,
	urlType:"get",
	valName:"fuzzy",
	selectVal:"name",
	templateId : "#nameQueryPrDownTemplate",
	lastFn:function(data){
		return actionFormate(data,false);
	},itemClick:function(data){
		$("#huZhuName").data("data",data);
	}
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
				$("#yuLanBtn").attr("img",data);
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
function showProInfo(id){
	$.get("share/projectInfo.do",{
		id:id
	},function(html){
		$("#showProInfoArea").html(html);
		$("#showProInfoModal").modal("show");
	});
}
function deleteFileItem(dom){
	$(dom).closest("li").remove();
}
function showInfo(dom){
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	if(data.unionSuggestionPath){
		var path = data.unionSuggestionPath;
		data.unionSuggestionPathName = path.substring(0,path.indexOf("/"));
		data.unionSuggestionPathVal = path.substring(path.indexOf("/") + 1);
	}
	var template = Handlebars.compile($("#removeInfoTemplate").html());
	var html = template(data);
	var rHtml = $(html);
	var fileItem = [];
	var docPath = data.houseImgPath;
	if(docPath){
		var paths = docPath.split("|");
		for ( var p in paths) {
			var path = paths[p];
			fileItem.push(path.substring(path.indexOf("/") + 1));
		}
	}
	$("#showFileItem",rHtml).data("fileItem",fileItem);
	$("#showFileItem",rHtml).click(function(){
		var fileItem = $(this).data("fileItem");
		if(!fileItem || fileItem.length <= 0) return;
		$.initShowImage(fileItem);
	});
	$("#showImage",rHtml).data("fileItem",data.image);
	$("#showImage",rHtml).click(function(){
		var fileItem = $(this).data("fileItem");
		if(!fileItem) return;
		$.initShowImage([fileItem]);
	});
	$("#familyInfo").html(rHtml);
	$.post("projectManagement/pmProgressGet.do",{
		proId:data.proId
	},function(data){
		actionFormate(data,false,function(type,msg,d){
			var template = Handlebars.compile($("#proInfoEntrytemplate").html());
			var html = template(d);
			$("#proInfoShow").html(html);
		});
	},"json")
	$.post("projectManagement/queryRemoveInfoGetRemoveInfos.do",{
		id:data.id
	},function(data){
		 actionFormate(data,false,function(type,msg,d){
			 	d = d || [];
			 	for (var i = 0; i < d.length; i++) {
			 		var t = d[i];
			 		var template = Handlebars.compile($("#famitlyItemEntrytemplate").html());
					var html = $(template(t));
					html.data("data",t);
					$("#familyInfoItem").append(html);
				}
		 });
	},"json");
	$("#familyInfoModal").modal("show");
}
var tr = null;
function editInfo(dom){
	tr = $(dom).closest("tr");
	var data = tr.data("data");
	if(data.unionSuggestionPath){
		var path = data.unionSuggestionPath;
		data.unionSuggestionPathName = path.substring(0,path.indexOf("/"));
		data.unionSuggestionPathVal = path.substring(path.indexOf("/") + 1);
	}
	var template = Handlebars.compile($("#editFamilyInfoTemplate").html());
	var html = template(data);
	var rHtml = $(html);
	var docPath = data.houseImgPath;
	if(docPath){
		var fileDatas = docPath.split("|");
		for ( var i in fileDatas) {
			var f = fileDatas[i];
			var file = {};
			file.fileValue = f;
			file.fileName = f.substring(0,f.indexOf("/"));
			file.filePath = f.substring(f.indexOf("/") + 1);
			var temp = Handlebars.compile($("#fileItemTemplate").html());
			var h = temp(file);
			$("#fileItems",rHtml).append(h);
		}
	}
	$("#editStreet",rHtml).val(data.streetId);
	if(data.image){
		$("#yuLanBtn",rHtml).css("display","inline");
		$("#yuLanBtn",rHtml).attr("img",data.image);
	}
	$("#yuLanBtn",rHtml).click(function(){
		var img = $(this).attr("img");
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
				$("#yuLanBtn").attr("img",data);
			});
			$("#paiZhaoFileLoginState").css("display","none");
			$("#zhaoBtn,#yuLanBtn,#paiZhaoFileCheckState,#upBtn").css("display","inline");
		},function(e){
			$("#paiZhaoFileLoginState").css("display","none");
			$("#zhaoBtn,#yuLanBtn,#paiZhaoFileCheckState,#upBtn").css("display","inline");
		});
	});
	$("#editFamilyInfo").html(rHtml);
	submitFileStyle("#upLoadFile","HOUSE_IMG",function(data){
		actionFormate(data, true,function(type,msg,d){
			var file = {};
			file.fileValue = d;
			file.fileName = d.substring(0,d.indexOf("/"));
			file.filePath = d.substring(d.indexOf("/") + 1);
			var template = Handlebars.compile($("#fileItemTemplate").html());
			var html = template(file);
			$("#fileItems").append(html);
		});
	});
	submitFileStyle("#lHshFJDom","EVIDENCE_FILE",function(data){
		return actionFormate(data, true);
	});
	new bindingSelect({
		masterSelect:"#editCommunity",
		childSelect:"#editZu",
		childDefalueVal:"请选择组",
		childVal:data.groupId,
		url:"share/address",
		afterFn:function(data){
			return actionFormate(data, false);
		}
	});
	new bindingSelect({
		masterSelect:"#editStreet",
		childSelect:"#editCommunity",
		childDefalueVal:"请选择社区",
		childVal:data.communityId,
		url:"share/address",
		afterFn:function(data){
			return actionFormate(data, false);
		}
	});
	$("#editStreet",rHtml).change();
	$("#editFamilyInfoModal").modal("show");
}
function showPersonInfo(dom){
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#familyItemInfoEntrytemplate").html());
	var html = template(data);
	$("#showPesroMsgArea").html(html);
	$("#showPesroMsgModal").modal("show");
}
$("#editFamilyInfoModal").validate({
	rules : {
		houseLegalArea : {
			number : true,
			maxlength : 10,
			min : 0
		},
		houseIllegalArea : {
			number : true,
			maxlength : 10,
			min : 0
		},
		streetId : {
			required : true
		},
		communityId : {
			required : true
		},
		zuId : {
			required : true
		},
		satuationDesc : {
			maxlength : 140
		},
		dealSolution : {
			maxlength : 140
		},
		unionSuggestion : {
			maxlength : 140
		},
		remark : {
			maxlength : 140
		}
	},
	submitHandler : function(form) {
		var data = tr.data("data");
		var subData = {};
		subData.id = data.id;
		subData.headName = data.headName;
		subData.proId = data.proId;
		subData.headId = data.headId;
		subData.fmlNumber = data.fmlNumber;
		subData.houseLegalArea = $("[name='houseLegalArea']",form).val();
		subData.houseIllegalArea = $("[name='houseIllegalArea']",form).val();
		subData.streetId = $("[name='streetId']",form).val();
		subData.communityId = $("[name='communityId']",form).val();
		subData.groupId = $("[name='zuId']",form).val();
		subData.satuationDesc = $("[name='satuationDesc']",form).val();
		subData.dealSolution = $("[name='dealSolution']",form).val();
		subData.unionSuggestion = $("[name='unionSuggestion']",form).val();
		subData.remark = $("[name='remark']",form).val();
		subData.unionSuggestionPath = $("[name='unionSuggestionPath']",form).val();
		subData.image = $("#yuLanBtn").attr("img");
		subData.address = $("#editStreet option:selected").html() + $("#editCommunity option:selected").html() + $("#editZu option:selected").html();
		var fileItems = "";
		$("input[name='fileItem']","#fileItems").each(function(){
			fileItems = fileItems + $(this).val() + "|";
		});
		subData.houseImgPath = fileItems.substring(0, fileItems.length - 1);
		$.post("projectManagement/queryRemoveInfoEdit.do",{
			dataJson:JSON.stringify(subData)
		},function(data){
			 actionFormate(data, true,function(type,msg,d){
				 operationLog("修改拆迁户户信息","修改拆迁户户信息");
				 tr.remove();
				 var template = Handlebars.compile($("#entrytemplate").html());
				 var html = template(d);
				 var rHtml = $(html);
				 rHtml.data("data",d);
				 $("#dataTbody").prepend(rHtml);
				 $("#editFamilyInfoModal").modal("hide");
			 });
		},"json");
	}
});
function daYin(dom){
	var ids = [];
	$("#dataTbody input[name='check']:checked").each(function(){
		var data = $(this).closest("tr").data("data");
		ids.push(data.id);
	});
	if(ids.length == 0){
		$(dom).popover({
			content:"请选择需要导出的户信息",
			placement:"top"
		});
		$(dom).popover("show");
		setTimeout(function(){
			$(dom).popover("destroy");	
		}, 1000);
		return;
	}
	var idsVal = JSON.stringify(ids).replace("[","").replace("]","").replace(/["]/g,"'");
	$.post("projectManagement/queryRemoveInfoDaYin.do",{
		daYinIds:idsVal
	},function(data){
		actionFormate(data, true, function(type,msg,data) {
			data = data || [];
			var win = $("#daYin")[0].contentWindow;
			win.initDaYinHtml();
			for (var i = 0; i < data.length; i++) {
				var d = data[i];
				var template = Handlebars.compile($("#daYinTemplate").html());
				var html = template(d);
				win.sendDaYinHtml(html);
			}
			win.printDom();
		});
	},"json");
}
function addFamilyPerson(dom){
	tr = $(dom);
	$("#addRemoveModal")[0].resest();
	$("#addRemoveModal").modal("show");
}
function showImage(imgSrc){
	$.initShowImage([imgSrc]);
}
$("#addRemoveModal").validate({
	rules : {
		name : {
			required : true,
			maxlength : 5
		},
		relationshipId : {
			required : true
		},
		idNumber : {
			required : true,
			maxlength : 18
		},
		birthday : {
			required : true
		},
		gender : {
			required : true
		},
		householdId : {
			required : true
		}
	},
	submitHandler : function(form) {
		var data = tr.data("data");
		var familyItem = {};
		familyItem.headId = data.headId;
		familyItem.name = $("[name='name']",form).val();
		familyItem.idNumber = $("[name='idNumber']",form).val();
		familyItem.birthday = $("[name='birthday']",form).val();
		familyItem.gender = $("[name='gender']",form).val();
		familyItem.onlyChildNumber = $("[name='onlyChildNumber']",form).val();
		familyItem.half = $("[name='half']",form).prop("checked");
		familyItem.address = data.address;
		familyItem.relationshipStr = $("[name='relationshipId'] option:selected",form).html();
		familyItem.householdStr = $("[name='householdId'] option:selected",form).html();
		familyItem.streetId = data.streetId;
		familyItem.communityId = data.communityId;
		familyItem.relationshipId = $("[name='relationshipId']",form).val();
		familyItem.householdId = $("[name='householdId']",form).val();
		familyItem.proId = data.proId;
		familyItem.fmlId = data.id;
		familyItem.proName = data.proName;
		$.post("projectManagement/uploadRemoveInfoAdd.do",{
			dataJson:JSON.stringify(familyItem)
		},function(data){
			 actionFormate(data, true,function(type,msg,d){
				 tr.remove();
				 var template = Handlebars.compile($("#entrytemplate").html());
				 var html = template(d);
				 var rHtml = $(html);
				 $("#dataTbody").prepend(rHtml);
				 $("#editFamilyInfoModal").modal("hide");
			 });
		});
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