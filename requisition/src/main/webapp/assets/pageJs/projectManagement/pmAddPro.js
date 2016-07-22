$("#addProform").validate({
	rules : {
		approvalNumber : {
			//required : true
		},
		proName : {
			required : true
		},
		proTypeCode : {
			required : true
		},proCategory:{
			required : true
		}, requisitionArea : {
			required : true,
			number : true,
			maxlength : 10,
			min : 0
		},
		shouldRemoveBuildings : {
			required : true,
			maxlength : 10,
			digits : true,
			min : 0
		},
		shouldRemoveHouses : {
			required : true,
			maxlength : 10,
			digits : true,
			min : 0
		},
		shouldMovePopulation : {
			required : true,
			maxlength : 10,
			digits : true,
			min : 0
		},
		shouldRemoveLegalArea : {
			required : true,
			maxlength : 10,
			number : true,
			min : 0
		},
		shouldRemoveIllegalArea : {
			required : true,
			maxlength : 10,
			number : true,
			min : 0
		},
		shouldPayMoney : {
			required : true,
			maxlength : 15,
			number : true,
			min : 0
		},sixForwardPro:{
			required : true
		},addressOrder:{
			required : true
		}
	},
	submitHandler : function(form) {
		var subData = {};
		subData.proName = $("[name='proName']",form).val();//项目名称
		subData.proCategory = $("[name='proCategory']",form).val();//项目分类
		subData.approvalNumber = $("[name='approvalNumber']",form).val();//项目审批号
		subData.requisitionArea = $("[name='requisitionArea']",form).val();//征地面积
		subData.shouldRemoveBuildings = $("[name='shouldRemoveBuildings']",form).val();//应拆栋数
		subData.shouldRemoveHouses = $("[name='shouldRemoveHouses']",form).val();//应拆户数
		subData.shouldRemoveLegalArea = $("[name='shouldRemoveLegalArea']",form).val();//应拆合法总面积
		subData.shouldRemoveIllegalArea = $("[name='shouldRemoveIllegalArea']",form).val();//应拆违章总面积
		subData.shouldMovePopulation = $("[name='shouldMovePopulation']",form).val();//应动迁人口
		subData.shouldPayMoney = $("[name='shouldPayMoney']",form).val();//项目应付补偿款
		subData.proType = $("[name='proTypeCode']",form).val();//項目類型id
		subData.proTypeStr = $("[name='proTypeCode'] option:selected",form).html();//項目類型文本
		subData.sixForwardPro = $("[name='sixForwardPro']",form).val();//是否六前项目
	
		var addressOrder = $('[name="addressOrder"]',form).val();
		subData.address =  addressOrder;

		$.post("projectManagement/pmAddProAdd.do",{
			dataJson:JSON.stringify(subData)
		},function(data){
			actionFormate(data, true, function() {
				
				var template = Handlebars.compile($("#logItemTemplate").html());
				var logHtml = template(subData);
				operationLog("手工添加项目信息","手工添加项目信息",logHtml);
				
				$(form)[0].reset();
			});
		},"json");
	}
});
function deleteAddressItem(event){
	$(this).closest(".tag").remove();
	event.stopPropagation();
}
$("#filePath").change(function(){
	var val = $(this).val();
	var pos=val.lastIndexOf("\\");
	$("#fileName").html(val.substring(pos+1));
});
$("#upFile").click(function(){
	$("#filePath").click();
});
$("#upLoadeFile").click(function(){
	var filePath = $("#filePath").val();
	if(!filePath) return;
	$("#filePath").prop("disabled",true);
	$(this).prop("disabled",true);
	$("span",this).html("正在上传");
	$("#errorBtn > span").html(0);
	$("#bulletList").html("");
	var time = $("#time").val();
	var year = time.split("/")[0];
	var month = time.split("/")[1];
	submitFile($("#filePath")[0],{
		year:year,
		month:month,
	},"json",function(data){
		actionFormate(data, true,function(type,msg,data){
			var template = Handlebars.compile($("#logImportItemTemplate").html());
			var html = template({
				time: year + "/" +month ,
				list:data
			});
			operationLog("导入项目信息","导入项目信息",html);
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
	},"projectManagement/pmAddProUpFile.do");
});