var tableData = $.generateData({
	pageArea : "#pageArea",
	dataAreaId : "#entrytemplate",
	dataArea : "#dataTbody",
	url : "registedInfoManagement/queryRegistedInfolist",
	firstFn : function(data) {
		data.pageNum = $("#dataPageCount").val();
		data.idNumber = $("#queryIdNumber").val();
		data.name = $("#queryName").val();
		data.streetId = $("#queryStreet").val();
		data.communityId = $("#queryCommunity").val();
		data.zuId = $("#queryZu").val();
	},
	lastFn : function(data) {
		var tempData = actionFormate(data, false);
		$("#countArea").html(tempData.totalCount);
		return tempData;
	}
});
$("#dataPageCount").change(function() {
	tableData.setPageNum(parseInt($(this).val()));
	tableData.refreshData();
});
$("#editInfoModal").validate({
	rules: {
		name: {
		    required: true
		}, idNumber:{
			required: true,
			minlength:18
		}, policy:{
			required: true
		}, address:{
			required: true
		}, isDemolition:{
			required: true
		}, isSetting:{
			required: true
		}, isZhuanHu:{
			required: true
		}, isSheBao:{
			required: true
		}, personState:{
			required: true
		}
	},  submitHandler:function(form){
		var data = $("#editInfoModal").data("data");
		var subData ={};
		subData.id = data.id;
		subData.name = $("[name='name']",form).val();
		subData.idNumber = $("[name='idNumber']",form).val();
		subData.policyStr = $("[name='policy']",form).val();
		subData.address = $("[name='address']",form).val();
		subData.isRemove = $("[name='isDemolition']",form).val() == "true";
		subData.isSetting = $("[name='isSetting']",form).val().val() == "true";
		subData.isTransfer = $("[name='isZhuanHu']",form).val().val() == "true";
		subData.isSocialSecurity = $("[name='isSheBao']",form).val().val() == "true";
		subData.userStateStr = $("[name='personState']",form).val();
		$.post("registedInfoManagement/queryRegistedInfoedit",{
			dataJson:JSON.stringify(subData)
		},function(data){
			actionFormate(data, true,function(type,msg,data){
				tr.remove();
				var template = Handlebars.compile($("#entrytemplate").html());
				var html = $(template(data));
				html.data("data",data);
				$("#dataTbody").prepend(html);
				$("#editInfoModal").modal("hide");
			});
		},"json");
    }
});
function deleteInfo(dom){
	if(!confirm("È·¶¨ÒªÉ¾³ý£¿")) return;
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	$.post("registedInfoManagement/queryRegistedInfodelete",{
		id:data.id
	},function(data){
		actionFormate(data, true,function(){
			tr.remove();
		});
	},"json");
}
function editInfo(dom){
	tr = $(dom).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#editModalTemplate").html());
	var html = $(template(data));
	$("#infoDataBody").html(html);
	$('#editInfoModal').data("data",data);
	$('#editInfoModal').modal('show');
	$("#selectType").mSelect({
		url:"share/docFileType",
		onHide:function(){
			$("#selectBtn").removeClass("active");
		}, onOpen:function(){
			$("#selectBtn").addClass("active");
		}, onClickItem:function(data,state){
			if(!state){
				$("#selectBtn").data("data",data);
				$("#selectBtn > span").html(data.name);
			}
		}
	});
	$("#selectBtn").click(function(){
		$("#selectType").mSelect().toggleShow();
	});
}