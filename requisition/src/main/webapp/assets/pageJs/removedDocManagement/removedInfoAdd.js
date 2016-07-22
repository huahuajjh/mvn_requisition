$("#addRemoveInfo").validate({
	rules: {
		name: {
		    required: true
		}, idNumber:{
			required: true
		}, birthday:{
			required: true
		}, removeDate:{
			required: true
		}, address:{
			required: true
		}
	},  submitHandler:function(form){
		var subData ={};
		subData.name = $("[name='name']",form).val();
		subData.idNumber = $("[name='idNumber']",form).val();
		subData.birthday = $("[name='birthday']",form).val();
		subData.removeDate = $("[name='removeDate']",form).val();
		subData.streetId = $("[name='street']",form).val();
		subData.communityId = $("[name='community']",form).val();
		//subData.groupId = $("[name='zu']",form).val();
		subData.address = $("[name='address']",form).val();
		
		subData.fitPolicy = $("[name='fitPolicy']",form).val();
		subData.createId = getCookie("login");

		$.post(sendUrl.removedInfo_add,{
			entity:JSON.stringify(subData)
		},function(data){
			actionFormate(data, true,function(){
				var template = Handlebars.compile($("#logItemTemplate").html());
				var logHtml = template(subData);
				operationLog("手工添加已拆迁户","手工添加已拆迁户",logHtml);
				$("#addRemoveInfo")[0].reset();
			});
		},"json");
    }
});
$("#filePath").change(function(){
	var val = $(this).val();
	var pos=val.lastIndexOf("\\");
	$("#fileName").html(val.substring(pos+1));
});
$("#upFileExl").click(function(){
	$("#filePath").click();
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
			operationLog("导入拆迁户","导入拆迁户信息",logHtml);
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
	},"removedDocManagement/removedInfoAddUpFile.do");
});