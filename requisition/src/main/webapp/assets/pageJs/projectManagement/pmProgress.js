var tableData = $.generateData({
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : "projectManagement/pmProgressList.do",
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		var queryPrName =  $("#queryPrName").val();
		if(queryPrName){
			data.val = queryPrName;
		}
		data.annouceQueue = $("#queryPrJD").val();
		data.typeId = $("#queryProType").val();
		data.address = $("#queryAddressName").val();
	}, lastFn : function(data) {
		var tempData = actionFormate(data, false) || {datas:[],totalCount:0};
		$("#countArea").html(tempData.totalCount);
		return tempData;
	}
});

$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
});
$("#checkAllPro").change(function(){
	var check = $(this).prop("checked");
	$("input[name='checkPro']","#dataTbody").prop("checked",check);
});
$("#street").change( function() {
	var thisVal = $(this).val();
	$("#community").empty();
	$("#community").append('<option value="">所有社区</option>');
	if (!thisVal) return;
	$.post("share/address.do", {
		id : thisVal
	}, function(data) {
		var datas = actionFormate(data,false);
		for ( var d in datas) {
			$("#community").append( '<option value="' + datas[d].id + '">' + datas[d].name + '</option>');
		}
	}, "json");
});
$("button", '#addAnnounce .tab-head').click(function() {
	var actionPane = $(this).attr("toggle");
	var pane = $(actionPane);
	if (pane.size() < 1) {
		$(this).popover({
			content : "无法切换到该公告",
			placement : "top"
		});
		$(this).popover('show');
		var a = this;
		setTimeout(function() {
			$(a).popover('destroy');
		}, 500);
	} else {
		$("button", '#addAnnounce .tab-head').removeClass("active");
		$(this).addClass("active");
		$(".tab-pane", '#addAnnounce .tab-content').removeClass("active");
		pane.addClass("active");
	}
});
$("#removedBuildings").keyup(function(){
	var num = parseFloat($(this).val());
	var data = tr.data("data");
	var percentage = parseInt(num / data.shouldRemoveBuildings * 10000) * 1.00 / 100;
	var requisitionArea = parseInt((data.requisitionArea*percentage / 100 * 10000000)) / 10000000;
	if(requisitionArea){
		requisitionArea = requisitionArea.toFixed(2);
	}
	$("#removedLandArea").val(requisitionArea || "");
});
$('#announceAddInfoModal,#yueBaoAddModal').modal({
	backdrop : "static",
	keyboard : false,
	show : false
});
$("#yueBaoAddModal").on(
		'hidden.bs.modal',
		function(e) {
			$("#yueBaoAddModal")[0].reset();
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
// 查看详细
function showInfo(dom) {
	var tr = $(dom).closest("tr");
	var proname = tr.attr("proname");
	var approvalNumber = tr.attr("approvalNumber");
	var protype = tr.attr("protype");
	var address = tr.attr("address");
	var announceName = tr.attr("announceName");
	var proid = tr.attr("proid");
	var proInfo = tr.data("data");
	$("#proInfoName").html(proname);
	$("#proInfoNum").html(approvalNumber);
	$("#proInfoAddress").html(address);
	$("#proInfoType").html(protype);
	$("#announceName").html(announceName);
	$.post("projectManagement/pmProgressInfo.do", {
		proId : proid
	}, function(data) {
		var tempData = actionFormate(data, false) || [];
		var template = Handlebars.compile($("#entryInfoTemplate").html());
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
			var startDate = proInfo.startDate || "";
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
		var html = template(tempData);
		$("#showProMonthProces").html(html);
		template = Handlebars.compile($("#yueCountTemplate").html());
		html = template(countModel);
		$("#yurBaoHejiArea").html(html);
		$('#showYueBaoModal').modal('show');
	},"json");
}
function inputMonthProces(dom) {
	tr = $(dom).closest("tr");
	var proname = tr.attr("proname");
	var approvalNumber = tr.attr("approvalNumber");
	var protype = tr.attr("protype");
	var address = tr.attr("address");
	var startDate = tr.attr("startDate");
	if(startDate){
		$("#isSee").css("display","none");
		$("#monthStartTime").attr("name","");
	} else{
		$("#isSee").css("display","block");
		$("#monthStartTime").attr("name","startDate");
	}
	var proid = tr.attr("proid");
	$("#addMonthProcesId").val(proid);
	$("#addMonthProName").html(proname);
	$('#yueBaoAddModal').data('proName',proname);
	$('#yueBaoAddModal').modal('show');
}
function addAnnouncement(dom) {
	tr = $(dom).closest("tr");
	var proname = tr.attr("proname");
	var proid = tr.attr("proid");
	$("#addAnnouncementNmae").html(proname);
	$.post("projectManagement/pmProgressGetAnnouncement.do", {
		proId : proid
	}, function(data) {
		var tempData = actionFormate(data, false);
		tempData.sort(function(a,b){
			return a.sequence - b.sequence;
		});
		$("#addAnnouncementContent").empty();
		if (tempData.length > 0) {
			var template = Handlebars.compile($("#AddAnnounceInfoTemplateOne")
					.html());
			for (var i = 0; i < tempData.length; i++) {
				var models = tempData[i];
				var docPath = models.docPath;
				if(docPath){
					models.fileName = docPath.substring(0,docPath.indexOf("/"));
					models.filePath = docPath.substring(docPath.indexOf("/") + 1);
				}
				models.index = i + 1;
				var html = template(models);
				$("#addAnnouncementContent").append(html);
			}
		}
		if (tempData.length < 3) {
			var index = tempData.length + 1;
			var template = Handlebars.compile($("#AddAnnounceInfoTemplateTwo")
					.html());
			var html = template({
				index : index,
				proId:proid
			});
			$("#addAnnouncementContent").append(html);
		}
		var content =$(".tab-pane","#addAnnouncementContent");
		var last = content.last();
		last.addClass("active");
		$(".btn","#addAnnounce .tab-head").removeClass("active").eq(last.index("#addAnnouncementContent .tab-pane")).addClass("active");
		$('[data-plugin-masked-input]',"#addAnnouncementContent").each(function() {
			var $this = $(this), opts = {};
			var pluginOptions = $this.data('plugin-options');
			if (pluginOptions)
				opts = pluginOptions;

			$this.themePluginMaskedInput(opts);
		});
		$('[data-plugin-datepicker]',"#addAnnouncementContent").each(function() {
			var $this = $(this), opts = {};
			$this.themePluginDatePicker(opts);
		});
		initFormFile();
		initFormAnnounceForm();
		$('#announceAddInfoModal').data('proName',proname);
		$('#announceAddInfoModal').modal('show');
	},"json");
}
function initFormFile(){
	$(".upLoadFile").each(function(){
		submitFileStyle(this,"POLICY_FILE",function(data){
			return actionFormate(data, true);
		});
	});
}
function yueBaoManage(dom){
	var tr = $(dom).closest("tr");
	var dataV = tr.data("data");
	$.post("projectManagement/pmProgressInfo.do", {
		proId : dataV.id
	}, function(data) {
		var tempData = actionFormate(data, false)||[];
		$("#yueBaoListMsgArea").html("");
		for (var i = 0; i < tempData.length; i++) {
			var d = tempData[i];
			var template = Handlebars.compile($("#yueBaoListTemplate").html());
			var html = $(template(d));
			html.data("data",d);
			html.data("proData",dataV);
			$("#yueBaoListMsgArea").append(html);
		}
		$("#yueBaoModal").modal("show");
	},"json");
}
function editYueBaoManage(dom){
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	var proData = tr.data("proData");
	$("#editYueBaoModal").data("editData",data);
	$("#editYueBaoModal").data("editProData",proData);
	var template = Handlebars.compile($("#editYueBaoTemplate").html());
	var html = $(template(data));
	if(data.curMonthComplete == "是"){
		$("[name='isBenYueJieSuan']",html).prop("checked",true);
	}
	$("#editYueBaoArea").html(html);
	$("#editYueBaoModal").modal("show");
}
function showAnnouncementInfo(dom){
	var tr = $(dom).closest("tr");
	var proname = tr.attr("proname");
	var proid = tr.attr("proid");
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
$("#editYueBaoModal").validate({
	rules : {
		removedBuildings : {
			required : true,
			digits : true,
			maxlength : 10,
			min : 0
		},
		removedLandArea : {
			required : true,
			number : true,
			maxlength : 10,
			min : 0
		},
		rmovedHouses : {
			required : true,
			digits : true,
			maxlength : 10,
			min : 0
		},
		removedLegalArea : {
			required : true,
			number : true,
			maxlength : 10,
			min : 0
		},
		removedIllegalArea : {
			required : true,
			number : true,
			maxlength : 10,
			min : 0
		},
		movedPopulation : {
			required : true,
			maxlength : 10,
			digits : true,
			min : 0
		},
		paidMoney : {
			required : true,
			maxlength : 15,
			number : true,
			min : 0
		},
		yearDeadlineFile : {
			required : true,
			maxlength : 10,
			digits : true,
			min : 0
		},
		yearCourtExecute : {
			required : true,
			maxlength : 10,
			digits : true,
			min : 0
		},
		yearLegalRemoved : {
			required : true,
			maxlength : 10,
			digits : true,
			min : 0
		}
	},
	submitHandler : function(form) {
		var editData = $(form).data("editData");
		var editProData = $(form).data("editProData");
		var subData = {};
		subData.id = editData.id;
		subData.date = editData.date;
		subData.removedLandArea = $('[name="removedLandArea"]',form).val();
		subData.removedBuildings = $('[name="removedBuildings"]',form).val();
		subData.rmovedHouses = $('[name="rmovedHouses"]',form).val();
		subData.removedLegalArea = $('[name="removedLegalArea"]',form).val();
		subData.removedIllegalArea = $('[name="removedIllegalArea"]',form).val();
		subData.movedPopulation = $('[name="movedPopulation"]',form).val();
		subData.paidMoney = $('[name="paidMoney"]',form).val();
		subData.yearDeadlineFile = $('[name="yearDeadlineFile"]',form).val();
		subData.yearCourtExecute = $('[name="yearCourtExecute"]',form).val();
		subData.yearLegalRemoved = $('[name="yearLegalRemoved"]',form).val();
		subData.curMonthComplete = $('[name="isBenYueJieSuan"]:checked',form).val() == "true"?"是":"否";
		subData.remark = $('[name="remark"]',form).val();
		subData.proId = editData.proId;
		subData.startDate = editData.startDate;
		subData.proName = editProData.proName;
		
		$.post("projectManagement/pmProgressEditMouth.do",{
			dataJson:JSON.stringify(subData)
		},function(data){
			actionFormate(data, true, function(type,msg,data) {
				
				var template = Handlebars.compile($("#logYueBaoItemTemplate").html());
				var logHtml = template(subData);
				operationLog("修改项目月报","修改项目月报",logHtml);
				$(form).modal('hide');
			});
		},"json");
	}
});
$("#yueBaoAddModal").validate({
	rules : {
		year : {
			required : true
		},
		month : {
			required : true
		},
		removedBuildings : {
			required : true,
			digits : true,
			maxlength : 10,
			min : 0
		},
		removedLandArea : {
			required : true,
			number : true,
			maxlength : 10,
			min : 0
		},
		removedLegalArea : {
			required : true,
			number : true,
			maxlength : 10,
			min : 0
		},
		movedPopulation : {
			required : true,
			maxlength : 10,
			digits : true,
			min : 0
		},
		yearDeadlineFile : {
			required : true,
			maxlength : 10,
			digits : true,
			min : 0
		},
		yearCourtExecute : {
			required : true,
			maxlength : 10,
			digits : true,
			min : 0
		},
		yearLegalRemoved : {
			required : true,
			maxlength : 10,
			digits : true,
			min : 0
		},
		startDate:{
			required : true
		}
	},
	submitHandler : function(form) {
		var subData = {};
		var time = $("[name='time']",form).val();
		subData.date = time.split("/")[0] +"/" + time.split("/")[1] +"/01";
		subData.removedLandArea = $('[name="removedLandArea"]',form).val();
		subData.removedBuildings = $('[name="removedBuildings"]',form).val();
		subData.removedLegalArea = $('[name="removedLegalArea"]',form).val();
		subData.movedPopulation = $('[name="movedPopulation"]',form).val();
		subData.yearDeadlineFile = $('[name="yearDeadlineFile"]',form).val();
		subData.yearCourtExecute = $('[name="yearCourtExecute"]',form).val();
		subData.yearLegalRemoved = $('[name="yearLegalRemoved"]',form).val();
		subData.proId = tr.data("data").id;
		subData.startDate = $('[name="startDate"]',form).val();
		subData.curMonthComplete = $('[name="isBenYueJieSuan"]:checked',form).val() == "true"?"是":"否";
		subData.remark = $('[name="remark"]',form).val();
		subData.proName = $('#yueBaoAddModal').data('proName');
		
		$.post("projectManagement/pmProgressInputMouth.do",{
			dataJson:JSON.stringify(subData)
		},function(data){
			actionFormate(data, true, function(type,msg,data) {
				
				var template = Handlebars.compile($("#logYueBaoItemTemplate").html());
				var logHtml = template(subData);
				operationLog("录入项目月进度","录入项目月进度",logHtml);
				$(form).modal('hide');
				tr.remove();
				var template = Handlebars.compile($("#entrytemplate").html());
				var html = template(data);
				var rHtml = $(html);
				rHtml.data("data",data);
				$("#dataTbody").prepend(rHtml);
			});
		},"json");
	}
});
function initFormAnnounceForm(){
	$("#addAnnouncementContent > form").each(function(){
		$(this).validate({
			rules : {
				date : {
					required : true
				}
			},
			submitHandler : function(form) {
				var logData = {};
				logData.proName = $('#announceAddInfoModal').data('proName');
				logData.announce = $('#addAnnounce > .tab-head .active').html();
				logData.number = $("[name='number']",form).val();
				logData.date = $("[name='date']",form).val();
				logData.fdocVal = $("[name='fdocVal']",form).val();
				var template = Handlebars.compile($("#logGongGaoItemTemplate").html());
				var logHtml = template(logData);
				$.post($(form).attr("action"),$(form).serialize(),function(data){
					actionFormate(data, true, function(type,msg,data) {
						var formId = "#" + $(form).attr("id");
						var gongGaoName = $("#addAnnounce [toggle='"+formId+"']").html();
						if($(form).hasClass("addAnno")){
							$("#announceAddInfoModal").modal('hide');
							tr.remove();
							var template = Handlebars.compile($("#entrytemplate").html());
							var html = template(data);
							var rHtml = $(html);
							rHtml.data("data",data);
							$("#dataTbody").prepend(rHtml);
							operationLog("增加项目公告","增加项目公告",logHtml);
						} else {
							operationLog("修改项目公告","修改项目公告",logHtml);
						}
					});
				},"json");
			}
		});
	});
}
function daoChu(){
	var dateFirst = $("#daYinYearFirst").val() + "/" + $("#daYinMouthFirst").val() + "/01";
	var dateLast = $("#daYinYearLast").val() + "/" + $("#daYinMouthLast").val() + "/01";
	var d = exportProExcel();
	$("#daoChuHead").val(JSON.stringify(d.heads));
	$("#daoChuAttrModel").val(JSON.stringify(d.keys));
	$("#daYinData").val(JSON.stringify({
		startDate:dateFirst,
		endDate:dateLast
	}));
	$("#subDaoChu").submit();
}

function daYin(dom){
	var dataId = [];
	$("input[name='checkPro']:checked","#dataTbody").each(function(){
		var tr = $(this).closest("tr");
		var data = tr.data("data");
		dataId.push(data.id);
	});
	if(dataId.length == 0){
		$(dom).popover({
			content:"请选择需要导出的项目",
			placement:"top"
		});
		$(dom).popover("show");
		setTimeout(function(){
			$(dom).popover("destroy");	
		}, 1000);
		return;
	}
	$.post("projectManagement/pmProgressDaYin.do",{
		daYinIds:JSON.stringify(dataId).replace("[","").replace("]","").replace(/["]/g,"'")
	},function(data){
		actionFormate(data, true, function(type,msg,data) {
			data = data || [];
			var win = $("#daYin")[0].contentWindow;
			win.initDaYinHtml();
			for (var i = 0; i < data.length; i++) {
				var d = data[i];
				var announcements = d.announcements || [];
				for (var f = 0; f < announcements.length; f++) {
					var a = announcements[f];
					switch (a.sequence) {
					case 1:
						a.sequenceStr = "一公告";
						break;
					case 2:
						a.sequenceStr = "二公告";
						break;
					case 3:
						a.sequenceStr = "三公告";
						break;
					}
				}
				announcements.sort(function(a,b){return a.sequence>b.sequence?1:-1});
				var items = d.items || [];
				d.countModel = {
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
				for (var l = 0; l < items.length; l++) {
					var t = items[l];
					t.date = t.date.substring(0, t.date.lastIndexOf("/"));
					var startDate = d.startDate || "";
					if(startDate.substring(0, startDate.lastIndexOf("/")) == t.date){
						t.isStart = "√";
					}
					d.countModel.removedLandArea += t.removedLandArea || 0;
					d.countModel.removedBuildings += t.removedBuildings || 0;
					d.countModel.rmovedHouses += t.rmovedHouses || 0;
					d.countModel.removedLegalArea += t.removedLegalArea || 0;
					d.countModel.removedIllegalArea += t.removedIllegalArea || 0;
					d.countModel.movedPopulation += t.movedPopulation || 0;
					d.countModel.paidMoney += t.paidMoney || 0;
					d.countModel.yearDeadlineFile += t.yearDeadlineFile || 0;
					d.countModel.yearCourtExecute += t.yearCourtExecute || 0;
					d.countModel.yearLegalRemoved += t.yearLegalRemoved || 0;
				}
				var template = Handlebars.compile($("#daYinProYueBaoTemplate").html());
				var html = template(d);
				win.sendDaYinHtml(html);
			}
			console.log(d);
			win.printDom();
		});
	},"json");
}
