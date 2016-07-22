var queryProtableData = $.generateData({
	pageArea : "#queryProPageArea",
	dataAreaId : "#queryProEntrytemplate",
	dataArea : "#queryProDataTbody",
	url : "projectManagement/pmQueryProList.do",
	firstFn : function(data) {
		data.pageNum = $("#queryProDataPageCount").val();
		var queryPrName =  $("#queryPrName").val();
		if(queryPrName){
				data.queryProName = queryPrName;
		}
		data.annouceQueue = $("#queryPrJD").val();
		data.typeId = $("#queryProType").val();
		data.address = $("#queryAddressName").val();
	},
	lastFn : function(data) {
		var tempData = actionFormate(data, false) || {datas:[],totalCount:0};
		$("#queryProCountArea").html(tempData.totalCount);
		return tempData;
	}
});
$("#queryProDataPageCount").change(function() {
	queryProtableData.setPageNum(parseInt($(this).val()));
	queryProtableData.refreshData();
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
$.dropDownInput({
	inputId : "#queryAddressName",
	dropDownId : "#queryAddressDown",
	url : sendUrl.addrProvider_getAddr,
	templateId : "#queryAddressDownTemplate",
	valName:"fuzzy",
	selectVal:"this",
	urlType:"get",
	firstFn:function(data){
		data.code = 1
	},
	lastFn:function(data){
		return actionFormate(data,false);
	}
});
new bindingSelect({
	masterSelect:"#queryProStreet",
	childSelect:"#queryProCommunity",
	childDefalueVal:"所有社区",
	url:"share/address.do",
	afterFn:function(data){
		return actionFormate(data, false);
	}
});
function showProInfoMsg(dom){
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#proInfoModalEntrytemplate").html());
	var html = template(data);
	$("#proInfoArea").html(html);
	initProDom(data);
	$("#showProInfoModal").modal("show");
}
function initProDom(data){
	$.post("projectManagement/pmProgressInfo.do", {
		proId : data.id
	}, function(dt) {
		var tempData = actionFormate(dt, false) || [];
		var countModel = {
				removedLandArea:0,
				removedBuildings:0,
				rmovedHouses:0,
				removedLegalArea:0,
				removedIllegalArea:0,
				movedPopulation:0,
				paidMoney:0,
				yearDeadlineFile:0,
				yearCourtExecute:0,
				yearLegalRemoved:0
		};
		for (var i = 0; i < tempData.length; i++) {
			var d = tempData[i];
			d.date = d.date.substring(0, d.date.lastIndexOf("/"));
			var startDate = data.startDate || "";
			if(startDate.substring(0, startDate.lastIndexOf("/")) == d.date){
				d.isStart = "√";
			}
			countModel.removedLandArea += d.removedLandArea;
			countModel.removedBuildings += d.removedBuildings;
			countModel.rmovedHouses += d.rmovedHouses;
			countModel.removedLegalArea += d.removedLegalArea;
			countModel.removedIllegalArea += d.removedIllegalArea;
			countModel.movedPopulation += d.movedPopulation;
			countModel.paidMoney += d.paidMoney;
			countModel.yearDeadlineFile += d.yearDeadlineFile;
			countModel.yearCourtExecute += d.yearCourtExecute;
			countModel.yearLegalRemoved += d.yearLegalRemoved;
		}
		var template = Handlebars.compile($("#entryInfoTemplate").html());
		var html = template(tempData);
		$("#showProMonthProces").html(html);
		template = Handlebars.compile($("#yueCountTemplate").html());
		html = template(countModel);
		$("#proYurBaoHejiArea").html(html);
	},"json");
	$.post("projectManagement/pmProgressGetAnnouncement.do", {
		proId : data.id
	}, function(data) {
		var tempData = actionFormate(data, false);
		for ( var d in tempData) {
			var t = tempData[d];
			var docPath = t.docPath;
			if(!docPath) continue;
			t.fileName = docPath.substring(0,docPath.indexOf("/"));
			t.filePath = docPath.substring(docPath.indexOf("/") + 1);
		}
		var template = Handlebars.compile($("#announceInfoTemplate").html());
		var html = template(tempData);
		$("#proInfoAnnListInfo").html(html);
	},"json");
	var huTableData = $.generateData({
		pageArea : "#huPageArea",
		dataAreaId : "#huEntrytemplate",
		dataArea : "#huDataTbody",
		url : "projectManagement/queryRemoveInfoList.do",
		firstFn : function(d) {
			d.pageNum = 10;
			d.proId = data.id;
		},
		lastFn : function(data) {
			var tempData = actionFormate(data, false);
			if(!tempData){
				$("#huCountArea").html("0");
				return ;
			}
			for (var i = 0; i < tempData.datas.length; i++) {
				var d = tempData.datas[i];
				if(d.unionSuggestionPath){
					var path = d.unionSuggestionPath;
					d.unionSuggestionPathName = path.substring(0,path.indexOf("/"));
					d.unionSuggestionPathVal = path.substring(path.indexOf("/") + 1);
				}
			}
			$("#huCountArea").html(tempData.totalCount);
			return tempData;
		}, domFn:function(dom){
			$('[data-toggle="tooltip"]',dom).tooltip();
		}
	});
}
function showHuImg(dom){
	var huInfoData = $(dom).closest("tr").data("data"); 
	var fileItem = [];
	var docPath = huInfoData.houseImgPath;
	if(docPath){
		var paths = docPath.split("|");
		for ( var p in paths) {
			var path = paths[p];
			fileItem.push(path.substring(path.indexOf("/") + 1));
		}
	}
	if(!fileItem || fileItem.length <= 0) return;
	$.initShowImage(fileItem);
}
function showHuGPImg(dom){
	var huInfoData = $(dom).closest("tr").data("data"); 
	var docPath = huInfoData.image;
	if(!docPath) return;
	$.initShowImage([docPath]);
}
function showHuPersons(dom){
	var huInfoData = $(dom).closest("tr").data("data");
	var tr = $(dom).closest("tr").next();
	$("#huDataTbody > tr:odd").not(tr).data("isDisplat",false).css("display","none");
	$("#huDataTbody > tr:not(:odd)").removeClass("bk-bg-very-light-gray");
	$("#huDataTbody > tr:not(:odd) .fa").removeClass("fa-minus-square").addClass("fa-plus-square");
	if(tr.data("isDisplat")){
		tr.css("display","none").data("isDisplat",false);
	} else {
		$(dom).closest("tr").addClass("bk-bg-very-light-gray");
		tr.css("display","table-row").data("isDisplat",true);
		$(".fa",dom).addClass("fa-minus-square").removeClass("fa-plus-square");
	}
	if(!tr.data("isInitData")){
		tr.data("isInitData",true);
		$(".huPersonDataTbody",tr).html("");
		$.get(sendUrl.fmlItem_getItems,{
			id:huInfoData.id
		},function(data){
			var tempData = actionFormate(data, false) || [];
			$("#personListArea").html("");
			for (var i = 0; i < tempData.length; i++) {
				var d = tempData[i];
				var template = Handlebars.compile($("#huPersonEntrytemplate").html());
				var html = $(template(d));
				html.data("data",d);
				$(".huPersonDataTbody",tr).append(html);
			}
		},"json");
	}
}
function showPersonOtherData(dom){
	var personInfoData = $(dom).closest("tr").data("data");
	var tr = $(dom).closest("tr").next();
	var huPersonDataTbody = tr.closest(".huPersonDataTbody");
	$("> tr:odd",huPersonDataTbody).not(tr).data("isDisplat",false).css("display","none");
	$("> tr:not(:odd)",huPersonDataTbody).removeClass("bk-bg-very-light-gray");
	$("> tr:not(:odd) .showBtn",huPersonDataTbody).removeClass("fa-minus-square").addClass("fa-plus-square");
	if(tr.data("isDisplat")){
		tr.css("display","none").data("isDisplat",false);
	} else {
		tr.css("display","table-row").data("isDisplat",true);
		$(dom).closest("tr").addClass("bk-bg-very-light-gray");
		$(".fa",dom).addClass("fa-minus-square").removeClass("fa-plus-square");
	}
	if(!tr.data("isInitData")){
		tr.data("isInitData",true);
		//初始化转户
		$.get(sendUrl.fmlItem_getTransferInfo,{
			id:personInfoData.id
		},function(d){
			var tempData = actionFormate(d, false) || {};
			tempData.name = personInfoData.name;
			var template = Handlebars.compile($("#zhuanHuMsgTemplate").html());
			var html = template(tempData);
			$(".showZhuanHuArea",tr).html(html);
		},"json");
		//初始化社保信息
		$.get(sendUrl.fmlItem_getSSInfo,{
			id:personInfoData.id
		},function(d){
			var tempData = actionFormate(d, false) || {};
			tempData.name = personInfoData.name;
			tempData.idNumber = personInfoData.idNumber;
			tempData.gender = personInfoData.gender;
			var template = Handlebars.compile($("#sheBaoMsgTemplate").html());
			var html = template(tempData);
			$(".perosonSheBaoInfoArea",tr).html(html);
		},"json");
		//初始化购房券信息
		$.get(sendUrl.fmlItem_getHptInfo,{
			id:personInfoData.id
		},function(d){
			var tempData = actionFormate(d, false) || {};
			var template = Handlebars.compile($("#gouFangQuanMsgTemplate").html());
			if(tempData.evidencePath){
				var path = tempData.evidencePath;
				tempData.evidencePathName = path.substring(0,path.indexOf("/"));
				tempData.evidencePathVal = path.substring(path.indexOf("/") + 1);
			}
			var html = template(tempData);
			$(".goufangquanInfoArea",tr).html(html);
		},"json");
	}
}
function showAnnouncementInfo(dom){
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	var proname = data.proName;
	var proid = data.id;
	$("#announceInfoName").html(proname);
	$.post("projectManagement/pmProgressGetAnnouncement.do",{proId:proid},function(data){
		var tempData = actionFormate(data, false);
		for ( var d in tempData) {
			var t = tempData[d];
			var docPath = t.docPath;
			if(docPath){
				t.fileName = docPath.substring(0,docPath.indexOf("/"));
				t.filePath = docPath.substring(docPath.indexOf("/") + 1);
			}
		}
		var template = Handlebars.compile($("#announceInfoTemplate").html());
		var html = template(tempData);
		$("#announceShowArea").html(html);
		$('#announceInfoModal').modal('show');
	},"json");
}