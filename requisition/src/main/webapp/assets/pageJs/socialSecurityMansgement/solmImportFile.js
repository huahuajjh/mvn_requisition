setPersonListModal("#selectPerson",function(data){
	if(data){
		$("#idNumber").val(data.idNumber);
		initDom(data);
	}
});
$("#upFileExl").click(function(){
	$("#filePath").click();
});
$("#idNumberBtn").click(function() {
	var idNumber = $("#idNumber").val();
	if (!idNumber)
		return;
	$.post("transferAccountManagement/taImportFileGet.do", {
		idNumber : idNumber
	}, function(data) {
		actionFormate(data, true,
				function(type, msg, data) {
					initDom(data);
				});
	}, "json");
});
$("#addTalmportForm").validate({
	rules: {
		prisonTime:{
			digits:true
		}, medicalInsuranceMonth:{
			digits:true
		}, serveArmyTime:{
			digits:true
		}, endowmentInsuranceYear:{
			digits:true
		}
	}, submitHandler:function(form){
		if(!isPassValidate()) return;
		var subData = {};
		if($("#isSheBao input[name='isSheBao']:checked").val() == "true"){
			var data = $("#dataArea").data("data");
			subData.fmlItemId = data.id;
			subData.socialsecurityDate = $("#time").val();
			subData.community = $("[name='community']",form).val();
			subData.serveArmyTime = $("[name='serveArmyTime']",form).val() || 0;
			subData.endowmentInsuranceYear = $("[name='endowmentInsuranceYear']",form).val() || 0;
			subData.medicalInsuranceMonth = $("[name='medicalInsuranceMonth']",form).val() || 0;
			subData.joinWhichMedicalInsurance = $("[name='joinWhichMedicalInsurance']",form).val();
			subData.prisonTime = $("[name='prisonTime']",form).val() || 0;
			subData.ageRange = data.ageDuan;
			$.post("socialSecurityMansgement/solmImportFileAdd.do",{
				dataJson:JSON.stringify(subData)
			},function(d){
				 actionFormate(d, true,function(){
					 subData.name = data.name;
					 subData.idNumber = data.idNumber;
					 subData.gender = data.gender;
					 subData.birthday = data.birthday;
					 var template = Handlebars.compile($("#logItemTemplate").html());
					 var logHtml = template(subData);
					 operationLog("录入社保信息","录入社保信息",logHtml);
					 resestForm();
				 });
			},"json");
		} else {
			showMsg("录入成功","提示：");
			resestForm();
		}
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
function initDom(data){
	var template = Handlebars.compile($("#entrytemplate").html());
	var ageDuan = ageCalculate(data.birthday);
	data.ageDuan = ageDuan;
	var html = $(template(data));
	$("#dataArea").data("data",data);
	$("#dataArea").html(html);
	$("#isSheBao input[name='isSheBao']",html).change(function() {
		var thisVal = $(this).val();
		if (thisVal == "true") {
			enablingDom();
		} else {
			disabledDom();
		}
	});
	new bindingSelect({
		masterSelect:"#community",
		childSelect:"#zu",
		childDefalueVal:"请选择组",
		url:"share/address",
		afterFn:function(data){
			return actionFormate(data, false);
		}
	});
	new bindingSelect({
		masterSelect:"#street",
		childSelect:"#community",
		childDefalueVal:"请选择社区",
		url:"share/address",
		afterFn:function(data){
			return actionFormate(data, false);
		}
	});
	$('[data-plugin-datepicker]').each(function() {
		var $this = $(this), opts = {};
		$this.themePluginDatePicker(opts);
	});
	$('[data-plugin-masked-input]').each(function() {
		var $this = $(this), opts = {};
		var pluginOptions = $this.data('plugin-options');
		if (pluginOptions)
			opts = pluginOptions;

		$this.themePluginMaskedInput(opts);
	});
}
function showProInfo(id){
	$.get("share/projectInfo.do",{
		id:id
	},function(html){
		$("#showProInfoArea").html(html);
		$("#showProInfoModal").modal("show");
	});
}
function disabledDom() {
	$("input[type='text'],select","#dataArea").val("");
	$("input[type='text'],select","#dataArea").prop("disabled", true);
}
function enablingDom() {
	$("input[type='text'],select","#dataArea").prop("disabled", false);
}
function resestForm(){
	$("#dataArea").html("");
}
function isPassValidate() {
	var stateVal = $("#isSheBao input[name='isSheBao']:checked").val();
	if (!stateVal) {
		$("#isSheBao").popover({
			content : "请选择是否纳入社保",
			placement : "left"
		});
		$("#isSheBao").popover("show");
		setTimeout(function() {
			$("#isSheBao").popover("destroy");
		}, 1000);
		return false;
	} else if (stateVal == "true") {
		if (!$("#time").val()) {
			$("#time").popover({
				content : "请输入纳入社保时间",
				placement : "left"
			});
			$("#time").popover("show");
			setTimeout(function() {
				$("#time").popover("destroy");
			}, 1000);
		}
		if(!$("#time").val()){
			return false;
		}
	}
	return true;
}
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
			var template = Handlebars.compile($("#logImportItemTemplate").html());
			var logHtml = template(data);
			operationLog("导入社保信息","导入社保信息",logHtml);
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
	},"socialSecurityMansgement/solmImportFileUpFile.do");
});