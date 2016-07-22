$('#starTime,#endTime').datetimepicker({lang:'ch'});
$.get(sendUrl.res_getAllRes,function(data){
	actionFormate(data, false,function(type,msg,data){
		var template = Handlebars.compile($("#optionItemTemplate").html());
		var html = $(template(data));
		$("#moKuaiSelect").append(html);
	});
},"json");
var tableData = $.generateData({
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : sendUrl.operationRecordWS_queryPage,
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		var tempData = {};
		var starTime = $("#starTime").val();
		var endTime = $("#endTime").val();
		if(starTime && endTime){
			tempData.from = starTime;
			tempData.end = endTime;
		}
		var moKuaiSelect = $("#moKuaiSelect").val();
		if(moKuaiSelect){
			tempData.moudleId = moKuaiSelect;
		}
		tempData.pageIndex = data.pageIndex;
		tempData.pageSize = data.pageNum;
		data.queryModel = JSON.stringify(tempData);
	},
	lastFn : function(data) {
		var tempData = actionFormate(data, false) || {totalCount:0,datas:[]};
		$("#countArea").html(tempData.totalCount);
		return tempData;
	}
});
$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
});
var record = {
		moudle:"账号管理",
		action:"新增账号",
		content:"新增Admin账号",
		accountId:"51d4ebf1-e491-41eb-92a9-a5274f7df831",
		name:"Admin",
		moudleId:"51d4ebf1-e491-41eb-92a9-a5274f7df831"
	};
function showLogInfo(id){
	$.getJSON(sendUrl.operationRecordWS_queryData,{
		id:id
	},function(data){
		var html = actionFormate(data, false) || "";
		console.log(data);
		var template = Handlebars.compile($("#logEntrytemplate").html());
		var h = template(html);
		$("#logDataInfo").html(h);
		$('#showInfoModal').modal('show');
	});
}
//$.post("http://192.168.1.5:8080/requisition-land-ws/operationRecordWS/addRecord",{
//	record: JSON.stringify(record)
//},function(text){
//	console.log(text);
//});