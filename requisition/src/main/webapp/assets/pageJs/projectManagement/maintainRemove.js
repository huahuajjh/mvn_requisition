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
	url : "projectManagement/maintainRemoveList.do",
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
		$.post("share/saveFile.do",{
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
			maxlength : 500
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
		subData.address = $("[name='address']",form).val();
		var fileItems = "";
		$("input[name='fileItem']","#fileItems").each(function(){
			fileItems = fileItems + $(this).val() + "|";
		});
		subData.houseImgPath = fileItems.substring(0, fileItems.length - 1);
		$.post("projectManagement/queryRemoveInfoEdit.do",{
			dataJson:JSON.stringify(subData)
		},function(data){
			 actionFormate(data, true,function(type,msg,d){
				 tr.remove();
				 var template = Handlebars.compile($("#entrytemplate").html());
				 var html = template(d);
				 var rHtml = $(html);
				 rHtml.data("data",d);
				var template = Handlebars.compile($("#logItemTemplate").html());
				var logHtml = template(d);
				 operationLog("修改拆迁户户信息","修改拆迁户户信息",logHtml);
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
	$.get("share/photographs.do",function(html){
		$("#phonePaiZhaoBody").html(html);
		$("#phonePaiZhaoModal").modal("show");
	});
}
function showAddFMLAndHPTModal(td){
	var tr = $(td).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#addFMLPersonTemplate").html());
	var html = $(template(data));
	$('[data-plugin-masked-input]',html).each(function() {
		var $this = $(this), opts = {};
		var pluginOptions = $this.data('plugin-options');
		if (pluginOptions)
			opts = pluginOptions;
		$this.themePluginMaskedInput(opts);
	});
	$('[data-plugin-datepicker]',html).each(function() {
		var $this = $(this), opts = {};
		$this.themePluginDatePicker(opts);
	});
	$("[name='relationship'] option",html).each(function(){
		if($(this).html() == "户主"){
			$(this).remove();
		}
	});
	$("[name='relationship']",html).change(function(){
		var val = $("option:selected",this).html();
		if(val == "其他"){
			$('[name="otherRelationship"]',html).prop("disabled",false);
		} else{
			$('[name="otherRelationship"]',html).prop("disabled",true).val("");
		}
	});
	$("[name='certificateType']",html).change(function(){
		 var val = $(this).val();
		 if(val){
			 $('[name="idNumber"]',html).prop("disabled",false);
		 } else {
			 $('[name="idNumber"]',html).prop("disabled",true).val("");
		 }
	});
	$('[name="idNumber"]',html).blur(function(){
		var val = $(this).val();
		if(val){
			var certificateTypeVal = $('[name="certificateType"]',html).val();
			if(certificateTypeVal == "idNumber"){
				if(!IdCardValidate(val)){
					$(html).validate().showErrors({
						"idNumber" : "输入的身份证格式不正确"
					});
					return;
				}
				$("[name='birthday']",html).val(getDataByIdCard(val));
				var sex = maleOrFemalByIdCard(val);
				$("[name='gender']",html).val(sex=="male"?0:1);
			}
			$.getJSON(sendUrl.fmlItem_idnumberExists,{
				idnumber:val
			},function(data){
				actionFormate(data, false,function(type,msg,data){},function(type,msg,data){
					$(html).validate().showErrors({
						"idNumber" : msg
					});
				});
			});
		}
	});
	$("#personInfoModal .modal-body").html(html);
	$("#personInfoModal").data("data",data);
	$("#personInfoModal").modal("show");
}
$("#personInfoModal").validate({
	rules:{
		name:{
			required : true
		},relationship:{
			required : true
		},idNumber:{
			required : true
		},birthday:{
			required : true
		},gender:{
			required : true
		}, householdId:{
			required : true
		},certificateType:{
			required : true
		}
	}, submitHandler:function(form){
		var huData = $("#personInfoModal").data("data");
		$.getJSON(sendUrl.fmlItem_idnumberExists,{
			idnumber:$("[name='idNumber']",form).val()
		},function(d){
			actionFormate(d, false,function(){
				var data = {};
				data.name = $("[name='name']",form).val();
				data.idNumber = $("[name='idNumber']",form).val();
				data.birthday = $("[name='birthday']",form).val();
				
				data.gender =  $("[name='gender']",form).val();
				data.genderStr =  $("[name='gender'] option:selected",form).html();
				if($("[name='onlyChildNumber']",form).val()){
					data.onlyChildNumber = $("[name='onlyChildNumber']",form).val();
				}
				data.half = $("[name='half']",form).prop("checked");
				
				data.relationshipId = $("[name='relationship']",form).val();
				data.relationshipStr = $("[name='relationship'] option:selected",form).html();

				data.householdId = $("[name='householdId']",form).val();
				data.householdStr = $("[name='householdId'] option:selected",form).html();

				data.educationLevel = $("[name='educationLevel']",form).val();
				data.currentEducationSituation = $("[name='currentEducationSituation']",form).val();
				data.farmingTime = $("[name='farmingTime']",form).val();
				data.serveArmySituation = $("[name='serveArmySituation']",form).val();
				data.tel = $("[name='tel']",form).val();
				data.userdSocialsecurity = $("[name='userdSocialsecurity']",form).prop("checked");
				data.remark = $("[name='remark']",form).val();
				
				data.certificateType = $("[name='certificateType']",form).val();
				
				data.otherRelationship = $("[name='otherRelationship']",form).val();
				
				data.streetId = huData.streetId;
				data.communityId = huData.communityId;
				data.groupId = huData.groupId;
				data.address = huData.address;
				data.proId = huData.proId;
				data.proName = huData.proName;
				data.fmlId = huData.id;
				
				data.headName = huData.name;
				data.headIdNumber = huData.idNumber;
				
				var yiQianHus = [];
				var yiQianHu = {};
				yiQianHu.name = data.name;
				yiQianHu.idNumber = data.idNumber;
				yiQianHu.birthday = data.birthday;
				yiQianHu.address = data.address;
				yiQianHu.streetId = data.streetId;
				yiQianHu.communityId = data.communityId;
				yiQianHus.push(yiQianHu);
				$.post("housePurchaseMansgement/hptAddAddFmlItem.do",{
					dataJson:JSON.stringify(data)
				},function(d){
					actionFormate(d, true,function(){
						$.post(sendUrl.removedInfo_addBatch,{
							list:JSON.stringify(yiQianHus)
						},function(d){
							actionFormate(d, true,function(){
								var template = Handlebars.compile($("#logFmlItemTemplate").html());
								var logHtml = template(data);
								 operationLog("手工添加人员信息","手工添加人员信息",logHtml);
							 });
						},"json");
						$("#personInfoModal").modal("hide");
					});
				},"json");
			},function(type,msg,data){
				$("#personInfoModal").validate().showErrors({
					"idNumber" : msg
				});
			});
		});
	}
});
function showItemList(dom){
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	$.post("projectManagement/queryRemoveInfoGetRemoveInfos.do",{
		id:data.id
	},function(data){
		 actionFormate(data,false,function(type,msg,d){
			 	d = d || [];
			 	$("#personMsgArea").html("");
			 	for (var i = 0; i < d.length; i++) {
			 		var t = d[i];
			 		var template = Handlebars.compile($("#itemOperationTemplate").html());
					var html = $(template(t));
					html.data("data",t);
					$("#personMsgArea").append(html);
				}
		 });
	},"json");
	$("#personMsgModal").modal("show");
}
function deleteFMLItem(dom){
	if(confirm("确定要删除该成员吗?")){
		var tr = $(dom).closest("tr");
		var data = tr.data("data");
		$.post("projectManagement/maintainRemoveDelete.do",{
			id:data.id
		},function(d){
			 actionFormate(d,true,function(type,msg,d){
					var template = Handlebars.compile($("#itemDeleteLogTemplate").html());
					var logHtml = template(data);
					operationLog("删除人员信息","删除人员信息",logHtml);
					tr.remove();
			 });
		},"json");
	}
}