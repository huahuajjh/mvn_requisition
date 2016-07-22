function submitFile(fileDom,datas, dataType, successFn, errorFn,url) {
	var fd = new FormData();
	fd.append("file", fileDom.files[0]);
	for ( var i in datas) {
		fd.append(i, datas[i]);
	}
	$.ajax({
		url : url || "share/updateFile.do",
		type : "post",
		data : fd,
		dataType : dataType,
		processData : false,
		contentType : false,
		success : function(data) {
			if ($.isFunction(successFn))
				successFn(data);
		},
		error : function(error) {
			if ($.isFunction(errorFn))
				errorFn(error);
		}
	});
}
function submitFileStyle(dom,type, successFn, errorFn){
	var upLoadFile = $(dom);
	var showFileName = $(".showUpFileName",upLoadFile);
	var upFileOperation = $(".upFileOperation",upLoadFile);
	var upFileSelectBtn = $(".upFileSelectBtn",upLoadFile);
	var upFileVal = $(".upFileVal",upLoadFile);
	var upFileUpBtn = $(".upFileUpBtn",upLoadFile);
	var upFileLogin = $(".upFileLogin",upLoadFile);
	var upFileSuccess = $(".upFileSuccess",upLoadFile);
	var upFileError = $(".upFileError",upLoadFile);
	var upFileHideVal = $(".upFileHideVal",upLoadFile);
	upFileVal.change(function(){
		var v = $(this).val();
		var pos=v.lastIndexOf("\\");
		var name = v.substring(pos+1);
		showFileName.val(name);
	});
	upFileSelectBtn.click(function(){
		upFileVal.click();
	});
	var thisSuccessFn = successFn;
	var thisErrorFn = errorFn;
	upFileUpBtn.click(function(){
		if(!upFileVal.val())return;
		upFileSuccess.css("display","none");
		upFileLogin.css("display","inline-block");
		upFileError.css("display","none");
		upFileSelectBtn.prop("disabled",true);
		upFileUpBtn.prop("disabled",true);
		upFileVal.prop("disabled",true);
		submitFile(upFileVal[0],{
			fileType:type
		},"json",function(d){
			upFileSelectBtn.prop("disabled",false);
			upFileUpBtn.prop("disabled",false);
			upFileVal.prop("disabled",false);
			upFileSuccess.css("display","inline-block");
			upFileLogin.css("display","none");
			upFileError.css("display","none");
			var val = "";
			if($.isFunction(thisSuccessFn)){
				val = thisSuccessFn(d);
			}
			upFileHideVal.val(val);
		},function(e){
			upFileSelectBtn.prop("disabled",false);
			upFileUpBtn.prop("disabled",false);
			upFileVal.prop("disabled",false);
			upFileSuccess.css("display","none");
			upFileLogin.css("display","none");
			upFileError.css("display","inline-block");
			if($.isFunction(thisErrorFn)){
				thisErrorFn(e);
			}
		});
	});
}
$.extend({
	submitFile : function(url, fileDom, dataType, successFn, errorFn) {
		return submitFile(url, fileDom, dataType, successFn, errorFn);
	}
});