var mainHtml = getShareData("uploadRemoveInfo");
if(mainHtml){
	setShareData("uploadRemoveInfo",null);
	$(".main").html(mainHtml);
} else {
	setShareData("uploadRemoveInfo",$(".main > *"));
	initDom();
}
function initDom(){
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
	$("#yuLanBtn").click(function(){
		var img = $(this).attr("img");
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
				$("#yuLanBtn").attr("img",data);
			});
			$("#paiZhaoFileLoginState").css("display","none");
			$("#zhaoBtn,#yuLanBtn,#paiZhaoFileCheckState,#upBtn").css("display","inline");
		},function(e){
			$("#paiZhaoFileLoginState").css("display","none");
			$("#zhaoBtn,#yuLanBtn,#paiZhaoFileCheckState,#upBtn").css("display","inline");
		});
	});
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
					
					var template = Handlebars.compile($("#familyItemTemplate").html());
					var html = $(template(data));
					html.attr("data",JSON.stringify(data));
					$("#familyItems").append(html);
					var dom = $("#personInfoModal").data("dom");
					if(dom){
						dom.remove();
					}
					$("#personInfoModal").modal("hide");
					initFamilyNum();
					$("#showHuZhuName").html(getHuZhuMing());
				},function(type,msg,data){
					$("#personInfoModal").validate().showErrors({
						"idNumber" : msg
					});
				});
			});
		}
	});
	$("#addForm").validate({
		rules : {
			address:{
				required : true
			},
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
			pzqksm : {
				maxlength : 500
			},
			ndclfa : {
				maxlength : 140
			},
			lhhsyj : {
				maxlength : 140
			},
			bz : {
				maxlength : 140
			}
		},
		submitHandler : function(form) {
			var proDataId = $("#proData").attr("proId");
			var proDataName = $("#proData").attr("proName");
			var proData  = {id:proDataId,proName:proDataName};
			if(!proDataName || !proDataId){
				$("#subDataBtn").popover({
					content : "请选择所属项目",
					placement : "left"
				});
				$("#subDataBtn").popover("show");
				setTimeout(function() {
					$("#subDataBtn").popover("destroy");
				}, 1000);
				return;
			}
			if (!isSelectedHuZhu()) {
				$("#subDataBtn").popover({
					content : "请录入户主信息",
					placement : "left"
				});
				$("#subDataBtn").popover("show");
				setTimeout(function() {
					$("#subDataBtn").popover("destroy");
				}, 1000);
				return;
			}
			//录入项目信息
			var removeInfo = {};
			removeInfo.headName = getHuZhuMing();
			removeInfo.proId = proData.id;
			removeInfo.address= $("#address").val();
			removeInfo.fmlNumber = parseInt($("> .row","#familyItems").size());
			if($("#houseLegalArea").val()){
				removeInfo.houseLegalArea = parseFloat($("#houseLegalArea").val());
			}
			if($("#houseIllegalArea").val()){
				removeInfo.houseIllegalArea = parseFloat($("#houseIllegalArea").val());
			}
			removeInfo.satuationDesc = $("#satuationDesc").val();
			removeInfo.dealSolution = $("#dealSolution").val();
			removeInfo.unionSuggestion = $("#unionSuggestion").val();
			removeInfo.remark = $("#remark").val();
			removeInfo.proName = proData.proName;
			removeInfo.unionSuggestionPath = $("#lianheVal").val();
			removeInfo.image = $("#yuLanBtn").attr("img");
			var fileItems = "";
			$("input[name='fileItem']","#fileItems").each(function(){
				fileItems = fileItems + $(this).val() + "|";
			});
			removeInfo.houseImgPath = fileItems.substring(0, fileItems.length - 1);
			removeInfo.items = [];
			var huZhuIdNumber = "";
			var yiQianHus = [];
			$("> .row", "#familyItems").each(function(){
				var item = JSON.parse($(this).attr("data"));
				item.address = $("#address").val();
				item.proId = proData.id;
				item.proName = proData.proName;
				if(item.relationshipStr == "户主"){
					huZhuIdNumber = item.idNumber;
				}
				removeInfo.items.push(item);
				var yiQianHu = {};
				yiQianHu.name = item.name;
				yiQianHu.idNumber = item.idNumber;
				yiQianHu.birthday = item.birthday;
				yiQianHu.address = item.address;
				yiQianHus.push(yiQianHu);

			});
			$.post("projectManagement/uploadRemoveInfoAdd.do",{
				dataJson:JSON.stringify(removeInfo)
			},function(data){
				 actionFormate(data, true,function(){
						$.post(sendUrl.removedInfo_addBatch,{
							list:JSON.stringify(yiQianHus)
						},function(data){
							actionFormate(data, true,function(){
								 resestData();
							 });
						},"json");
					var template = Handlebars.compile($("#logItemTemplate").html());
					var logHtml = template(removeInfo);
					operationLog("手工添加拆迁户","手工添加拆迁户",logHtml);
				 });
			},"json");
		}
	});
	$("#filePath").change(function(){
		var val = $(this).val();
		var pos=val.lastIndexOf("\\");
		$("#fileName").html(val.substring(pos+1));
	});
	$("#upLoadeFile").click(function(){
		var filePath = $("#filePath").val();
		if(!filePath){
			return;
		}
		$("#filePath").prop("disabled",true);
		$(this).prop("disabled",true);
		$("span",this).html("正在上传");
		$("#errorBtn > span").html(0);
		$("#bulletList").html();
		submitFile($("#filePath")[0],{},"json",function(data){
			actionFormate(data, true,function(type,msg,data){
				var template = Handlebars.compile($("#logItemTemplate").html());
				var logHtml = template(data[0]);
				operationLog("导入拆迁户","导入拆迁户信息",logHtml);
				if(data && data[1]){
					$.post(sendUrl.removedInfo_addBatch,{
						list:JSON.stringify(data[1])
					},function(data){
						actionFormate(data, true);
					},"json");
				}
			},function(type,msg,data){
				if(data){
					var template = Handlebars.compile($("#errorItemTemplate").html());
					var html = $(template(data));
					$("#errorBtn > span").html(data.length);
					$("#bulletList").html(html);
				}
			});
			$("#filePath").val("").change().prop("disabled",false);
			$("#upLoadeFile").prop("disabled",false);
			$("span","#upLoadeFile").html("上传");
		},function(){
			$("#filePath").prop("disabled",false);
			$("#upLoadeFile").prop("disabled",false);
			$("span","#upLoadeFile").html("上传");
		},"projectManagement/uploadRemoveInfoUpFile.do");
	});
}
setProListModal("#showProInfoModal",function(data){
	if(data){
		$("#proData").attr("proId",data.id);
		$("#proData").attr("proName",data.proName);
		$("#proName").html(data.proName);
	}
});
$("#upFileExl").click(function(){
	$("#filePath").click();
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
function resestData() {
	setShareData("uploadRemoveInfo",null);
	$("#showHuZhuName").html("");
	$("#addForm")[0].reset();
	$("> li","#fileItems").remove();
	$("#proName").html("");
	$("#proData").attr("proId","");
	$("#proData").attr("proName","");
	$("> .row","#familyItems").remove();
	$("#yuLanBtn,#paiZhaoFileCheckState").css("display","none");
	$("#yuLanBtn").attr("img","");
	initFamilyNum();
}
function deleteFileItem(dom){
	$(dom).closest("li").remove();
}
function showImage(imgSrc){
	$.initShowImage([imgSrc]);
}
function isSelectedHuZhu(){
	var state = false;
	$("> .row","#familyItems").each(function(){
		var relationshipStr = JSON.parse($(this).attr("data")).relationshipStr;
		if(relationshipStr == "户主"){
			state = true;
		}
	});
	return state;
}
function getHuZhuMing(){
	var name = "";
	$("> .row","#familyItems").each(function(){
		var data = JSON.parse($(this).attr("data"));
		var relationshipStr = data.relationshipStr;
		if(relationshipStr == "户主"){
			name = data.name;
		}
	});
	return name;
}
function initFamilyNum(){
	$("#showHuZhuName").html(getHuZhuMing());
	$("[familyCount]").html($("> .row","#familyItems").size());
}
function deleteFimalyItem(dom){
	 $(dom).closest(".row").remove();
	 initFamilyNum();
}
function fimalyItem(dom){
	var data = {};
	if(dom){
		data = JSON.parse($(dom).closest(".row").attr("data"));
		$("#personInfoModal").data("dom",$(dom).closest(".row"));
	} else{
		$("#personInfoModal").data("dom",null);
	}
	var template = Handlebars.compile($("#fimalyItemEditTemplate").html());
	var html = $(template(data));
	$("[name='relationship']",html).val(data.relationshipId);
	$("[name='gender']",html).val(data.gender);
	$("[name='householdId']",html).val(data.householdId);
	$("[name='relationship']",html).val(data.relationshipId);
	$("[name='certificateType']",html).val(data.certificateType);
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
					$("#personInfoModal").validate().showErrors({
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
					$("#personInfoModal").validate().showErrors({
						"idNumber" : msg
					});
				});
			});
		}
	});
	$('[data-plugin-datepicker]',html).each(function() {
		var $this = $(this), opts = {};
		$this.themePluginDatePicker(opts);
	});
	$('[data-plugin-masked-input]',html).each(function() {
		var $this = $(this), opts = {};

		var pluginOptions = $this.data('plugin-options');
		if (pluginOptions)
			opts = pluginOptions;
		$this.themePluginMaskedInput(opts);
	});
	if(isSelectedHuZhu()){
		$('[name="relationship"] option',html).each(function(){
			if($(this).html() == "户主"){
				$(this).css("display","none");
			}
		});
	}else{
		$('[name="relationship"] option',html).css("display","block");
	}
	html.data("data",data);
	$("#fimalyItemInfoArea").html(html);
	$("#personInfoModal").modal("show");
}