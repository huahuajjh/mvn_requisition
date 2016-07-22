var tableData = $.generateData({	
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : sendUrl.statistics_getData,
	urlType : "get",
	firstFn : function(data) {
		data.pageSize = 30;
		var startDate = $("#startDate").val();
		if(!startDate){
			startDate = "1900-1-1";
		}
		data.startDate = startDate;
		var endDate = $("#endDate").val();
		if(!endDate){
			endDate = "1900-1-1";
		}
		data.endDate = endDate;
	},
	lastFn : function(data) {
		var tempData = actionFormate(data, false);
		if(!tempData || !tempData.datas || !tempData.datas.length){
			tempData = {datas:[],totalCount:0};
		}
		for (var i = 0; i < tempData.datas.length; i++) {
			var d = tempData.datas[i];
			
			var template = Handlebars.compile($("#algorithmTemplate").html());
			var moneyUnitMsg = template({
				name:"拆迁人员",
				num:d.totalPopu,
				money:d.personalSubsidyStd,
				countMoney:d.totalPopu * d.personalSubsidyStd
			});
			d.moneyUnitMsg = moneyUnitMsg;
			var onlyChildMsg =  template({
				name:"独生子女",
				num:d.onlyChildCount,
				money:d.nonLrbStd,
				countMoney:d.onlyChildCount * d.nonLrbStd
			});
			var halfMsg =  template({
				name:"半边户",
				num:d.halfCount,
				money:d.nonLrbStd,
				countMoney:d.halfCount * d.nonLrbStd
			});
			var notHalfMsg =  template({
				name:"非半边户",
				num:d.totalPopu - d.halfCount,
				money:d.nonLrbStd,
				countMoney:(d.totalPopu - d.halfCount) * d.nonLrbStd
			});
			d.otherMoneyUnitMsg = onlyChildMsg + halfMsg + notHalfMsg;
		}
		return tempData;
	}
});
function getParentStartDate(){
	return $("#startDate").val();
}
function getParentEndDate(){
	return $("#endDate").val();
}
function showDaoChuWin(){
	$("#selectExportModal iframe")[0].contentWindow.setTime();
	$('#selectExportModal').modal('show');
}
$("#tableBody").scroll(function(){
	$("#tableHead").scrollLeft(this.scrollLeft);
});
//$('#content').niceScroll({
//    railvalign:"top",
//    cursorcolor: "#ccc",//#CC0071 光标颜色
//    cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0
//    touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备
//    cursorwidth: "10px", //像素光标的宽度
//    cursorborder: "0", // 	游标边框css定义
//    cursorborderradius: "5px",//以像素为光标边界半径
//    autohidemode: false //是否隐藏滚动条
//});