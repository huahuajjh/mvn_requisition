$("#organization").change(function() {
	var thisVal = $(this).val();
	$("#department").empty();
	$("#department").append('<option value="">请选择部门</option>');
	if (!thisVal)
		return;
	$.post("management/sysDeptManagementList.do", {
		orgId : thisVal
	}, function(data) {
		actionFormate(data, false,function(type,msg,datas){
			for ( var d in datas) {
				$("#department").append('<option value="' + datas[d].id + '">' + datas[d].name + '</option>');
			}
		});
	}, "json");
});
var tableData = $.generateData({
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : "",
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		$("#username").val();
		$("#name").val();
		$("#organization").val();
		$("#department").val();
	},
	lastFn : function(data) {
		var tempData = actionFormate(data, false);
		$("#accountCount").html(tempData.totalCount);
		return tempData;
	}
});
$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
});
function huiFu(dom){
	if(!confirm("是否恢复该人员？")) return;
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	$.post("",{
		id:data.id
	},function(data){
		actionFormate(data, true,function(type,msg,data){
			tr.remove();
		});
	},"json");
}