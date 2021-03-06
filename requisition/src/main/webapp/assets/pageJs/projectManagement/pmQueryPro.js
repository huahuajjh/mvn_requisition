var tableData = $.generateData({
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : "projectManagement/pmQueryProList.do",
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
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
		$("#countArea").html(tempData.totalCount);
		return tempData;
	}
});
$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
});
$("#street").change( function() {
	var thisVal = $(this).val();
	$("#community").empty();
	$("#community").append('<option value="">所有社区</option>');
	if (!thisVal) return;
	$.post("share/address", {
		id : thisVal
	}, function(data) {
		var datas = actionFormate(data,false);
		for ( var d in datas) {
			$("#community").append( '<option value="' + datas[d].id + '">' + datas[d].name + '</option>');
		}
	}, "json");
});
$('#editModal').modal({
	backdrop : "static",
	keyboard : false,
	show : false
});
function showInfo(dom) {
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	$("#proInfoName").html(data.proName);
	$("#proInfoApprovalNumber").html(data.approvalNumber);
	$("#proInfoAddress").html(data.totalAddress);
	$("#proInfoType").html(data.proTypeStr);
	$("#proInfoZdmj").html(data.requisitionArea);
	$("#proInfoYCDS").html(data.shouldRemoveBuildings);
	$("#proInfoYCHS").html(data.shouldRemoveHouses);
	$("#proInfoYDQRK").html(data.shouldMovePopulation);
	$("#proInfoHFMJ").html(data.shouldRemoveLegalArea);
	$("#proInfoWZMJ").html(data.shouldRemoveIllegalArea);
	$("#proInfoYBCK").html(data.shouldPayMoney);
	$("#startDate").html(data.startDate);
	$("#proInfoFL").html(data.categoryStr);
	$("#proInfoLQXM").html(data.sixForward);
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
			countModel.removedLandArea += d.removedLandArea || 0;
			countModel.removedBuildings += d.removedBuildings || 0;
			countModel.rmovedHouses += d.rmovedHouses || 0;
			countModel.removedLegalArea += d.removedLegalArea || 0;
			countModel.removedIllegalArea += d.removedIllegalArea || 0;
			countModel.movedPopulation += d.movedPopulation || 0;
			countModel.paidMoney += d.paidMoney || 0;
			countModel.yearDeadlineFile += d.yearDeadlineFile || 0;
			countModel.yearCourtExecute += d.yearCourtExecute || 0;
			countModel.yearLegalRemoved += d.yearLegalRemoved || 0;
		}
		var template = Handlebars.compile($("#entryInfoTemplate").html());
		var html = template(tempData);
		$("#showProMonthProces").html(html);
		template = Handlebars.compile($("#yueCountTemplate").html());
		html = template(countModel);
		$("#yurBaoHejiArea").html(html);
		$('#showYueBaoModal').modal('show');
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
		$("#annListInfo").html(html);
	},"json");
	$('#infoModal').modal('show');
}
$("#streetEdit").change( function() {
	var thisVal = $(this).val();
	$("#communityEdit").empty();
	$("#communityEdit").append('<option value="">请选择社区</option>');
	if (!thisVal) return;
	$.post("share/address", {
		id : thisVal
	}, function(data) {
		var datas = actionFormate(data,false);
		for ( var d in datas) {
			$("#communityEdit").append( '<option value="' + datas[d].id + '" '+
					(datas[d].id==editCommunityId?"selected":"")+'>' + datas[d].name + '</option>');
		}
		$("#communityEdit").change();
	}, "json");
});
$("#communityEdit").change(function(){
	var com = $("#communityEdit").val();
	var str = $("#streetEdit").val();
	var comHtml = $('option[value="'+com+'"]',"#communityEdit").html();
	var strHtml =  $('option[value="'+str+'"]',"#streetEdit").html();
	var val = strHtml + comHtml;
	console.log(val);
	$("#addressValEdit").val(val);
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
var editCommunityId = "";
var tr = null;
function editProjectInfo(dom){
	tr = $(dom).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#editProInfoTemplate").html());
	var html = $(template(data));
	initDom(html);
	$("[name='proTypeStr']",html).val(data.categoryStr);
	$("[name='sixPro'][value='"+data.sixForward+"']",html).prop("checked",true);
	var addressArr = (data.totalAddress || "").split(",");
	var streetIdArr = (data.streetId || "").split(",");
	var communityIdArr = (data.communityId || "").split(",");
	if(addressArr.length > streetIdArr.length){
		$('[name="otherAddress"]',html).val(addressArr[addressArr.length - 1]);
	}
	for (var i = 0; i < streetIdArr.length; i++) {
		var streetIdData =streetIdArr[i];
		var addressDataArr = [];
		addressDataArr.push({
			id:streetIdData,
			name:""
		});
		if(communityIdArr.length > 0 && communityIdArr.length > i){
			addressDataArr.push({
				id:communityIdArr[i],
				name:addressArr[i]
			});
		}
		var t = Handlebars.compile($("#addreddItemTemplate").html());
		var tempData = addressDataArr[addressDataArr.length-1];
		var h = $(t(tempData));
		h.data("data",tempData);
		h.data("datas",addressDataArr);
		$("> span",h).click(deleteAddressItem);
		$("#addressItems",html).append(h);
	}
	$('[name="proType"] > option[value="'+data.proType+'"]',html).prop("selected",true);
	$("#editProInfoArea").html(html);
	$('#editModal').modal('show');
}
$("#editModal").validate({
	rules : {
		approvalNumber : {
			required : true,
			maxlength : 20
		},
		proName : {
			required : true,
			maxlength : 20
		},
		proType : {
			required : true
		},
		requisitionArea : {
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
		}, proTypeStr:{
			required : true
		} ,sixPro:{
			required : true
		}
	},
	submitHandler : function(form) {
		var subData = {};
		subData.id = $("[name='proId']",form).val();
		subData.proName = $("[name='proName']",form).val();//项目名称
		subData.approvalNumber = $("[name='approvalNumber']",form).val();//项目审批号
		subData.categoryStr = $("[name='proTypeStr']",form).val();//项目分类
		subData.requisitionArea = $("[name='requisitionArea']",form).val();//征地面积
		subData.shouldRemoveBuildings = $("[name='shouldRemoveBuildings']",form).val();//应拆栋数
		subData.shouldRemoveHouses = $("[name='shouldRemoveHouses']",form).val();//应拆户数
		subData.shouldRemoveLegalArea = $("[name='shouldRemoveLegalArea']",form).val();//应拆合法总面积
		subData.shouldRemoveIllegalArea = $("[name='shouldRemoveIllegalArea']",form).val();//应拆违章总面积
		subData.shouldMovePopulation = $("[name='shouldMovePopulation']",form).val();//应动迁人口
		subData.shouldPayMoney = $("[name='shouldPayMoney']",form).val();//项目应付补偿款
		subData.proType = $("[name='proType']",form).val();//項目類型id
		subData.proTypeStr = $("[name='proType'] > option:selected",form).html();//項目類型文本
		subData.sixForward = $("[name='sixPro']:checked",form).val();//是否六前项目
		var address = [];
		var street = [];
		var community = [];
		$("> .tag","#addressItems").each(function(){
			var datas = $(this).data("datas");
			var addressVal = "";
			var d = datas[0];
			if(d){
				addressVal = addressVal + d.name;
				street.push(d.id);
			} 
			d = datas[1];
			if(d){
				addressVal = addressVal + d.name;
				community.push(d.id);
			}
			console.log(street.length);
			address.push(addressVal);
		});
		var addressOrder = $('[name="otherAddress"]',form).val();
		if(addressOrder){
			address.push(addressOrder);
		}
		subData.address =  address.join(",");//项目地址
		subData.streetId = street.join(",");//街道地址id组合
		subData.communityId = community.join(",");//社区地址id组合
		$.post("projectManagement/pmQueryProEdit.do",{
			dataJson:JSON.stringify(subData)
		},function(data){
			actionFormate(data, true, function(type,msg,rtData) {
				operationLog("修改项目信息","修改项目信息");
				tr.remove();
				var template = Handlebars.compile($("#entrytemplate").html());
				var html = template(rtData);
				var rtHtml = $(html).data("data",rtData);
				$("#dataTbody").prepend(rtHtml);
				$('#editModal').modal('hide');
			});
		},"json");
	}
});
function initDom(dom){
	$("#selectType",dom).mSelect({
		url:"share/address",
		onAfterRequest:function(data){
			return actionFormate(data, false,function(type,msg,data){
				for (var i = 0; i < data.length; i++) {
					var d = data[i];
					$("> .tag","#addressItems").each(function(){
						if($(this).data("data").id == d.id){
							d.isDisabled = true;
						}
					});
				}
			});
		}, onClickItem:function(data,state){
			var datas = $("#selectType").mSelect().getDatas();
			var tempState = false;
			if(!state){
				tempState = true;
			} else if(datas.length > 1){
				tempState = true;
			}
			if(tempState){
				var nameArr = [];
				for (var i = 0; i < datas.length; i++) {
					var d = datas[i];
					nameArr.push(d.name);
				}
				$("#selectType").mSelect().hide();
				$("#selectType").mSelect().init();
				var tempData = $.extend({}, data);
				tempData.name = nameArr.join("");
				var template = Handlebars.compile($("#addreddItemTemplate").html());
				var html = $(template(tempData));
				html.data("data",data);
				html.data("datas",datas);
				$("> span",html).click(deleteAddressItem);
				$("#addressItems").append(html);
				return false;
			}
		}
	});
	$("#addressItems",dom).click(function(){
		$("#selectType").mSelect().toggleShow();
	});
}
function deleteFileIItem(event){
	$(this).closest(".tag").remove();
	//event.stopPropagation();
}
function deleteAddressItem(event){
	$(this).closest(".tag").remove();
	//event.stopPropagation();
}