setProListModal("#selectProInfoModal",function(data){
	if(data){
		$("#queryPrName").data("data",data);
		$("#queryPrName").val(data.proName);
		$("#queryBtn").click();
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
$("#queryBtn").click(function(){
	var data = $("#queryPrName").data("data");
	var name = $("#queryPrName").val();
	if(!data || data.proName != name){
		$("#queryPrName").popover({
			content : "请选择需要修改的项目",
			placement : "left"
		});
		$("#queryPrName").popover("show");
		setTimeout(function() {
			$("#queryPrName").popover("destroy");
		}, 1000);
		return;
	}
	$.post("housePurchaseMansgement/hptProjectMoneyUnitGet.do",{
		proId:data.id
	},function(d){
		actionFormate(d,true,function(type,msg,data){
			var template = Handlebars.compile($("#entrytemplate").html());
			var html = $(template(data));
			$("#dataArea").data("data",data);
			$("#dataArea").html(html);
		});
	},"json");
});
$("#dataArea").validate({
	rules: {
		moneyUnit: {
			required: true
		}, otherMoneyUnit: {
			required: true
		}
	}, submitHandler:function(form){
		var data = $("#dataArea").data("data");
		
		var moneyUnit = $("[name='moneyUnit']",form).val();
		var otherMoneyUnit = $("[name='otherMoneyUnit']",form).val();
		
		data.moneyUnit = moneyUnit;
		data.otherMoneyUnit = otherMoneyUnit;
		
		$.post("housePurchaseMansgement/hptProjectMoneyUnitEdit.do",{
			proId:data.id,
			moneyUnit:moneyUnit,
			otherMoneyUnit:otherMoneyUnit
		},function(d){
			actionFormate(d, true, function() {
				var template = Handlebars.compile($("#logItemTemplate").html());
				var logHtml = template(data);
				operationLog("录入项目出资单位","录入项目出资单位",logHtml);
				$("#dataArea").html("");
    		});
		},"json");
    }
});