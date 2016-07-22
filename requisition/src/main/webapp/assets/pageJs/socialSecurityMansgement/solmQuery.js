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
	url : "socialSecurityMansgement/solmQueryList.do",
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		var queryPrName =  $("#queryPrName").val();
		if(queryPrName){
			data.queryProName = queryPrName;
		}
		data.streetId = $("#street").val();
		data.communityId = $("#community").val();
		data.zuId = $("#zu").val();
		data.idNumber = $("#idNumber").val();
		data.address = $("#queryAddressName").val();
	},
	lastFn : function(data) {
		var tempData = actionFormate(data, false);
		$("#countArea").html(tempData.totalCount);
		return tempData;
	}
});
$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
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
$.dropDownInput({
	inputId : "#queryAddressName",
	dropDownId : "#queryAddressDown",
	url : sendUrl.addrProvider_getAddr,
	templateId : "#queryAddressDownTemplate",
	valName:"fuzzy",
	selectVal:"this",
	urlType:"get",
	firstFn:function(data){
		data.code = 3
	},
	lastFn:function(data){
		return actionFormate(data,false);
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
$("#editIsSheBao [name='isSheBao']").change(function(){
	var thisVal = $(this).val();
	console.log(thisVal);
	if(thisVal == "true"){
		$("#editTime,#editSBType").prop("disabled",false);
	} else {
		$("#editTime,#editSBType").prop("disabled",true);
	}
});
$('#editSolmModal').modal({
	backdrop : "static",
	keyboard : false,
	show : false
});
$("#editSolmModal").validate({
	rules: {
		socialsecurityTypeId:{
			required : true
		}, socialsecurityDate:{
			required : true
		}, prisonTime:{
			digits:true
		}, medicalInsuranceMonth:{
			digits:true
		}, serveArmyTime:{
			digits:true
		}, endowmentInsuranceYear:{
			digits:true
		}
	}, submitHandler:function(form){
			var data = tr.data("data");
			var subData = {};
			subData.id = data.id;
			subData.fmlItemId = data.fmlItemId;
			subData.socialsecurityDate = $("[name='socialsecurityDate']",form).val();
			subData.socialsecurityTypeId = $("[name='socialsecurityTypeId']",form).val();
			subData.serveArmyTime = $("[name='serveArmyTime']",form).val() || 0;
			subData.endowmentInsuranceYear = $("[name='endowmentInsuranceYear']",form).val() || 0;
			subData.medicalInsuranceMonth = $("[name='medicalInsuranceMonth']",form).val() || 0;
			subData.joinWhichMedicalInsurance = $("[name='joinWhichMedicalInsurance']",form).val();
			subData.community = $("[name='community']",form).val();
			subData.prisonTime = $("[name='prisonTime']",form).val() || 0;
			$.post("socialSecurityMansgement/solmQueryEdit.do",{
				dataJson:JSON.stringify(subData)
			},function(d){
				actionFormate(d, true,function(type,msg,d){
					operationLog("修改社保信息","修改社保信息");
					tr.remove();
					data.ssDate = subData.socialsecurityDate;
					data.serveArmyTime = subData.serveArmyTime;
					data.endowmentInsuranceYear = subData.endowmentInsuranceYear;
					data.medicalInsuranceMonth = subData.medicalInsuranceMonth;
					data.joinWhichMedicalInsurance = subData.joinWhichMedicalInsurance;
					data.community = subData.community;
					data.prisonTime = subData.prisonTime;
					data.ssTypeId = subData.socialsecurityTypeId;
					data.ssTypeStr =  $("[name='socialsecurityTypeId'] option:selected",form).html();
					var template = Handlebars.compile($("#entrytemplate").html());
					var html = template(data);
					 var rHtml = $(html);
					 rHtml.data("data",data);
					$("#dataTbody").prepend(rHtml);
					$("#editSolmModal").modal("hide");
				 });
			},"json");
    }
});
var tr = null;
function showProInfo(id){
	$.get("share/projectInfo.do",{
		id:id
	},function(html){
		$("#showProInfoArea").html(html);
		$("#showProInfoModal").modal("show");
	});
}
function editData(dom){
	tr = $(dom).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#editDataTemplate").html());
	var html = template(data);
	var rHtml = $(html);
	$("[name='socialsecurityTypeId']",rHtml).val(data.ssTypeId);
	initDom(rHtml);
	$("#editInfoArea").html(rHtml);
	$("#editSolmModal").modal("show");
}
function initDom(dom){
	$('[data-plugin-datepicker]',dom).each(function() {
		var $this = $(this), opts = {};
		$this.themePluginDatePicker(opts);
	});
	$('[data-plugin-masked-input]',dom).each(function() {
		var $this = $(this), opts = {};

		var pluginOptions = $this.data('plugin-options');
		if (pluginOptions)
			opts = pluginOptions;

		$this.themePluginMaskedInput(opts);
	});
}