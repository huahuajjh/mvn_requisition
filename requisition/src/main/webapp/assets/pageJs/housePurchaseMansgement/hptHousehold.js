var tableData = $.generateData({
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : "",
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		//data.proId = $("#queryPrName").val();
	},
	lastFn : function(data) {
		var tempData = actionFormate(data, false);
		$("#countArea").html(tempData.totalCount);
		return tempData;
	}
});
function showInfo(dom){
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#showInfoTemplate").html());
	var html = template(data);
	$("#showInfoArea").html(html);
	$("#showInfoModal").modal("show");
}