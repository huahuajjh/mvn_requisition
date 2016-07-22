$.dropDownInput({
	inputId : "#idNumberOrName",
	dropDownId : "#queryDown",
	url : sendUrl.onekeyQuery_getFuzzy,
	urlType:"get",
	valName:"fuzzy",
	selectVal:"valName",
	templateId : "#queryDownTemplate",
	lastFn:function(data){
		return actionFormate(data,false,function(type,msg,data){
			if(data){
				for (var i = 0; i < data.length; i++) {
					var d = data[i];
					d.valName = d.name + "-" + d.idNumber;
				}
			}
		}) || [];
	},itemClick:function(data){
		$("#idNumberOrName").val(data.name);
		$("#idNumberOrName").data("data",data);
	}
});
$("#upFileExl").click(function(){
	$("#filePath").click();
});
setPersonListModal("#selectPerson",function(data){
	if(data){
		$("#name").val(data.name);
		$("#idNumber").val(data.idNumber);
		initDom(data);
	}
});
$("#idNumberBtn").click(function() {
	var data = $("#idNumberOrName").data("data");
	if(!data) return;
	$.post("housePurchaseMansgement/hptAddGet.do", {
		idNumber : data.idNumber,
		name : data.name
	}, function(data) {
		actionFormate(data, true, function(type, msg, data) {
			initDom(data);
		},function(){
			resestData();
		});
	}, "json");
});
$("#addHPT").validate({
	rules: {
		money: {
			required: true,
			number:true,
			maxlength:15,
			min:0
		}, quanNumber: {
			required: true
		}
	}, submitHandler:function(form){
		var data = $("#showinfo").data("data");
        var subData = {};
        subData.bonus = $("#addHPT [name='money']").val();//补贴金额
        subData.makeDate = $("#addHPT [name='time']").val();//制券日期
        subData.ticketNumber = $("#addHPT [name='quanNumber']").val();//券号
        subData.fmlItemId = data.id;//购房券所有者id
        subData.idNumber = data.idNumber;//持有者身份证
        subData.name = data.name;//持有者姓名
        $.post("housePurchaseMansgement/hptAddAdd.do",{
        	dataJson:JSON.stringify(subData)
        },function(data){
        	actionFormate(data, true, function(type, msg, data) {
				var template = Handlebars.compile($("#logItemTemplate").html());
				var logHtml = template(subData);
        		operationLog("手工添加购房券信息","手工添加购房券信息",logHtml);
        		resestData();
    		});
        },"json");
    }
});
function initDom(data){
	var template = Handlebars.compile($("#showInfoTemplate").html());
	var html = template(data);
	var rHtml = $(html);
	$('[data-plugin-datepicker]',rHtml).each(function() {
		var $this = $(this), opts = {};
		$this.themePluginDatePicker(opts);
	});
	$('[data-plugin-masked-input]',rHtml).each(function() {
		var $this = $(this), opts = {};

		var pluginOptions = $this.data('plugin-options');
		if (pluginOptions)
			opts = pluginOptions;
		$this.themePluginMaskedInput(opts);
	});
	$("#showinfo").data("data",data);
	$("#showinfo").html(rHtml);
}
function resestData(){
	$("#showinfo").html("");
}
function showProInfo(id){
	$.get("share/projectInfo.do",{
		id:id
	},function(html){
		$("#showProInfoArea").html(html);
		$("#showProInfoModal").modal("show");
	});
}
$("#filePath").change(function(){
	var val = $(this).val();
	var pos=val.lastIndexOf("\\");
	$("#fileName").html(val.substring(pos+1));
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
	$("#errorModalBody").html();
	submitFile($("#filePath")[0],{},"json",function(data){
		actionFormate(data, true,function(type,msg,data){
			if(data[2].length > 0){
				var template = Handlebars.compile($("#logImportItemTemplate").html());
				var logHtml = template(data[2]);
				operationLog("导入购房券信息","导入购房券信息",logHtml);
			}
			var m = {
				notExistHPTCount:data[0].length,
				errorHPTCount:data[1].length
			};
			var template = Handlebars.compile($("#errorInfoTemplate").html());
			var html = $(template(m));
			$("#errorBtn > span").html(m.notExistHPTCount + m.errorHPTCount);
			$("#errorModalBody").html(html);
			for (var i = 0; i < data[0].length; i++) {
				var td = data[0][i];
				var t = Handlebars.compile($("#notExistHPTItemTemplate").html());
				var h = $(t( td));
				h.data("data",td);
				$("#notExistHPTBody",html).append(h);
			}
			for (var i = 0; i < data[1].length; i++) {
				var td = data[1][i];
				var t = Handlebars.compile($("#errorHPTItemTemplate").html());
				var h = $(t( td));
				$("#errorHPTBody",html).append(h);
			}
		},function(type,msg,data){
			if(data){
				var template = Handlebars.compile($("#errorItemTemplate").html());
				var html = $(template(data));
				$("#errorBtn > span").html(data.length);
				$("#errorModalBody").html(html);
			}
		});
		$("#filePath").val("").change().prop("disabled",false);
		$("#upLoadeFile").prop("disabled",false);
		$("span","#upLoadeFile").html("上传");
	},function(){
		$("#filePath").prop("disabled",false);
		$("#upLoadeFile").prop("disabled",false);
		$("span","#upLoadeFile").html("上传");
	},"housePurchaseMansgement/hptAddUpFile.do");
});
setHuListModal("#selectHuPerson",function(data){
	var id = $("#selectHuPerson").data("id");
	console.log(id);
	if(data){
		$(id).data("data",data).html(data.name + "-"+data.idNumber);
	}
});
function showModel(id){
	 $("#selectHuPerson").data("id",id).modal("show");
}
//-----------------------
$("[name='relationship'] option","#personInfoModal").each(function(){
	if($(this).html() == "户主"){
		$(this).remove();
	}
});
$("[name='relationship']","#personInfoModal").change(function(){
	var val = $("option:selected",this).html();
	if(val == "其他"){
		$('[name="otherRelationship"]',"#personInfoModal").prop("disabled",false);
	} else{
		$('[name="otherRelationship"]',"#personInfoModal").prop("disabled",true).val("");
	}
});
$("[name='certificateType']","#personInfoModal").change(function(){
	 var val = $(this).val();
	 if(val){
		 $('[name="idNumber"]',"#personInfoModal").prop("disabled",false);
	 } else {
		 $('[name="idNumber"]',"#personInfoModal").prop("disabled",true).val("");
	 }
});
$('[name="idNumber"]',"#personInfoModal").blur(function(){
	var val = $(this).val();
	if(val){
		var certificateTypeVal = $('[name="certificateType"]',"#personInfoModal").val();
		if(certificateTypeVal == "idNumber"){
			if(!IdCardValidate(val)){
				$("#personInfoModal").validate().showErrors({
					"idNumber" : "输入的身份证格式不正确"
				});
				return;
			}
			$("[name='birthday']","#personInfoModal").val(getDataByIdCard(val));
			var sex = maleOrFemalByIdCard(val);
			$("[name='gender']","#personInfoModal").val(sex=="male"?0:1);
		}
		$.getJSON(sendUrl.fmlItem_idnumberExists,{
			idnumber:val
		},function(data){
			actionFormate(data, false,function(type,msg,data){},function(type,msg,data){
				$("#personInfoModal").validate().showErrors({
					"idNumber" : msg
				});
			});
		});
	}
});
$("#personInfoModal").validate({
	rules:{
		name:{
			required : true
		},relationship:{
			required : true
		},idNumber:{
			required : true
		},birthday:{
			required : true
		},gender:{
			required : true
		}, householdId:{
			required : true
		},certificateType:{
			required : true
		}
	}, submitHandler:function(form){
		var huData = $("#selectedHuData").data("data");
		if(!huData){
			$("#showHuMsg").popover({
				content : "请选择户主信息"
			});
			$("#showHuMsg").popover("show");
			setTimeout(function() {
				$("#showHuMsg").popover("destroy");
			}, 1000);
			return;
		}
		$.getJSON(sendUrl.fmlItem_idnumberExists,{
			idnumber:$("[name='idNumber']",form).val()
		},function(d){
			actionFormate(d, false,function(){
				var data = {};
				data.name = $("[name='name']",form).val();
				data.idNumber = $("[name='idNumber']",form).val();
				data.birthday = $("[name='birthday']",form).val();
				
				data.gender =  $("[name='gender']",form).val();
				data.genderStr =  $("[name='gender'] option:selected",form).html();
				if($("[name='onlyChildNumber']",form).val()){
					data.onlyChildNumber = $("[name='onlyChildNumber']",form).val();
				}
				data.half = $("[name='half']",form).prop("checked");
				
				data.relationshipId = $("[name='relationship']",form).val();
				data.relationshipStr = $("[name='relationship'] option:selected",form).html();

				data.householdId = $("[name='householdId']",form).val();
				data.householdStr = $("[name='householdId'] option:selected",form).html();

				data.educationLevel = $("[name='educationLevel']",form).val();
				data.currentEducationSituation = $("[name='currentEducationSituation']",form).val();
				data.farmingTime = $("[name='farmingTime']",form).val();
				data.serveArmySituation = $("[name='serveArmySituation']",form).val();
				data.tel = $("[name='tel']",form).val();
				data.userdSocialsecurity = $("[name='userdSocialsecurity']",form).prop("checked");
				data.remark = $("[name='remark']",form).val();
				
				data.certificateType = $("[name='certificateType']",form).val();
				
				data.otherRelationship = $("[name='otherRelationship']",form).val();
				
				data.streetId = huData.streetId;
				data.communityId = huData.communityId;
				data.groupId = huData.groupId;
				data.address = huData.address;
				data.proId = huData.proId;
				data.proName = huData.proName;
				data.fmlId = huData.fmlId;
				
				data.headName = huData.name;
				data.headIdNumber = huData.idNumber;
				
				var yiQianHus = [];
				var yiQianHu = {};
				yiQianHu.name = data.name;
				yiQianHu.idNumber = data.idNumber;
				yiQianHu.birthday = data.birthday;
				yiQianHu.address = data.address;
				yiQianHu.streetId = data.streetId;
				yiQianHu.communityId = data.communityId;
				yiQianHus.push(yiQianHu);
				
				$.post("housePurchaseMansgement/hptAddAddFmlItem.do",{
					dataJson:JSON.stringify(data)
				},function(d){
					actionFormate(d, true,function(){
						$.post(sendUrl.removedInfo_addBatch,{
							list:JSON.stringify(yiQianHus)
						},function(d){
							actionFormate(d, true,function(){
								var template = Handlebars.compile($("#logFmlItemTemplate").html());
								var logHtml = template(data);
								 operationLog("手工添加人员信息","手工添加人员信息",logHtml);
							 });
						},"json");
						$("#selectedHuData").data("data",null).html("");
						$("[name='otherRelationship']",form).val("").prop("disabled",true);
						$("[name='idNumber']",form).val("").prop("disabled",true);
						$("#personInfoModal")[0].reset();
					});
				},"json");
				$("#personInfoModal").modal("hide");
			},function(type,msg,data){
				$("#personInfoModal").validate().showErrors({
					"idNumber" : msg
				});
			});
		});
	}
});
//-------------------
function ignorePerson(td){
	var tr = $(td).closest("tr");
	tr.attr("isComplete","true");
	$(".operation",tr).html("该人员已被忽视无法添加。");
	var count = $("#notExistHPTBody > tr:not([isComplete])").size();
	$("#notExistHPTCount").html("（" + count + "）");
}
function addPerson(td){
	var tr = $(td).closest("tr");
	var data = tr.data("data");
	$.getJSON(sendUrl.fmlItem_idnumberExists,{
		idnumber:data.idNumber
	},function(d){
		actionFormate(d, false,function(){
			showAddFMLAndHPTModal(td);
		},function(type,msg,data){
			showAddHPTModal(td);
		});
	});
}
$("#personHPTInfoModal").validate({
	rules:{
		name:{
			required : true
		},relationship:{
			required : true
		},idNumber:{
			required : true
		},birthday:{
			required : true
		},gender:{
			required : true
		}, householdId:{
			required : true
		},certificateType:{
			required : true
		},ticketNumber:{
			required : true
		},bonus:{
			required: true,
			number:true,
			maxlength:15,
			min:0
		}
	}, submitHandler:function(form){
		var huData = $("#selectedHuData2").data("data");
		if(!huData){
			$("#showHuMsg2").popover({
				content : "请选择户主信息"
			});
			$("#showHuMsg2").popover("show");
			setTimeout(function() {
				$("#showHuMsg2").popover("destroy");
			}, 1000);
			return;
		}
		$.getJSON(sendUrl.fmlItem_idnumberExists,{
			idnumber:$("[name='idNumber']",form).val()
		},function(d){
			actionFormate(d, false,function(){
				var data = {};
				data.name = $("[name='name']",form).val();
				data.idNumber = $("[name='idNumber']",form).val();
				data.birthday = $("[name='birthday']",form).val();
				
				data.gender =  $("[name='gender']",form).val();
				data.genderStr =  $("[name='gender'] option:selected",form).html();
				if($("[name='onlyChildNumber']",form).val()){
					data.onlyChildNumber = $("[name='onlyChildNumber']",form).val();
				}
				data.half = $("[name='half']",form).prop("checked");
				
				data.relationshipId = $("[name='relationship']",form).val();
				data.relationshipStr = $("[name='relationship'] option:selected",form).html();

				data.householdId = $("[name='householdId']",form).val();
				data.householdStr = $("[name='householdId'] option:selected",form).html();

				data.educationLevel = $("[name='educationLevel']",form).val();
				data.currentEducationSituation = $("[name='currentEducationSituation']",form).val();
				data.farmingTime = $("[name='farmingTime']",form).val();
				data.serveArmySituation = $("[name='serveArmySituation']",form).val();
				data.tel = $("[name='tel']",form).val();
				data.userdSocialsecurity = $("[name='userdSocialsecurity']",form).prop("checked");
				data.remark = $("[name='remark']",form).val();
				data.certificateType = $("[name='certificateType']",form).val();
				data.otherRelationship = $("[name='otherRelationship']",form).val();
				
				data.ticketNumber = $("[name='ticketNumber']",form).val();
				data.bonus = $("[name='bonus']",form).val();
				
				data.streetId = huData.streetId;
				data.communityId = huData.communityId;
				data.groupId = huData.groupId;
				data.address = huData.address;
				data.proId = huData.proId;
				data.proName = huData.proName;
				data.fmlId = huData.fmlId;
				
				data.headName = huData.name;
				data.headIdNumber = huData.idNumber;
				
				var yiQianHus = [];
				var yiQianHu = {};
				yiQianHu.name = data.name;
				yiQianHu.idNumber = data.idNumber;
				yiQianHu.birthday = data.birthday;
				yiQianHu.address = data.address;
				yiQianHu.streetId = data.streetId;
				yiQianHu.communityId = data.communityId;
				yiQianHus.push(yiQianHu);
				$.post("housePurchaseMansgement/hptAddAddFmlItemAndHpt.do",{
					dataJson:JSON.stringify(data)
				},function(d){
					actionFormate(d, true,function(){
						$.post(sendUrl.removedInfo_addBatch,{
							list:JSON.stringify(yiQianHus)
						},function(d){
							actionFormate(d, true,function(){
								var template = Handlebars.compile($("#logFmlItemAndHPTTemplate").html());
								var logHtml = template(data);
								$("#personHPTInfoModal").modal("hide");
							 	var tr = $("#personHPTInfoModal").data("tr");
								tr.attr("isComplete","true");
								$(".operation",tr).html("该人员已被添加到系统。");
								var count = $("#notExistHPTBody > tr:not([isComplete])").size();
								$("#notExistHPTCount").html("（" + count + "）");
								 operationLog("手工添加人员信息以及购房券信息","手工添加人员信息以及购房券信息",logHtml);
							 });
						},"json");
						$("#selectedHuData").data("data",null).html("");
						$("[name='otherRelationship']",form).val("").prop("disabled",true);
						$("[name='idNumber']",form).val("").prop("disabled",true);
						$("#personInfoModal")[0].reset();
					});
				},"json");
				$("#personInfoModal").modal("hide");
			},function(type,msg,data){
				$("#personInfoModal").validate().showErrors({
					"idNumber" : msg
				});
			});
		});
	}
});
function showAddFMLAndHPTModal(td){
	var tr = $(td).closest("tr");
	var data = tr.data("data");
	var template = Handlebars.compile($("#addFMLPersonTemplate").html());
	var html = $(template(data));
	$('[data-plugin-masked-input]',html).each(function() {
		var $this = $(this), opts = {};
		var pluginOptions = $this.data('plugin-options');
		if (pluginOptions)
			opts = pluginOptions;
		$this.themePluginMaskedInput(opts);
	});
	$('[data-plugin-datepicker]',html).each(function() {
		var $this = $(this), opts = {};
		$this.themePluginDatePicker(opts);
	});
	$("[name='relationship'] option",html).each(function(){
		if($(this).html() == "户主"){
			$(this).remove();
		} else if($(this).html() == data.relationship){
			$(this).prop("selected",true);
		}
	});
	$("[name='relationship']",html).change(function(){
		var val = $("option:selected",this).html();
		if(val == "其他"){
			$('[name="otherRelationship"]',html).prop("disabled",false);
		} else{
			$('[name="otherRelationship"]',html).prop("disabled",true).val("");
		}
	});
	if(!$("[name='relationship']",html).val()){
		$("[name='relationship'] option",html).each(function(){
			if($(this).html() == "其他"){
				$(this).prop("selected",true);
			}
		});
		$('[name="otherRelationship"]',html).val(data.relationship);
		$("[name='relationship']",html).change();
	}
	$("[name='certificateType']",html).change(function(){
		 var val = $(this).val();
		 if(val){
			 $('[name="idNumber"]',html).prop("disabled",false);
		 } else {
			 $('[name="idNumber"]',html).prop("disabled",true).val("");
		 }
	});
	$('[name="idNumber"]',html).blur(function(){
		var val = $(this).val();
		if(val){
			var certificateTypeVal = $('[name="certificateType"]',html).val();
			if(certificateTypeVal == "idNumber"){
				if(!IdCardValidate(val)){
					$(html).validate().showErrors({
						"idNumber" : "输入的身份证格式不正确"
					});
					return;
				}
				$("[name='birthday']",html).val(getDataByIdCard(val));
				var sex = maleOrFemalByIdCard(val);
				$("[name='gender']",html).val(sex=="male"?0:1);
			}
			$.getJSON(sendUrl.fmlItem_idnumberExists,{
				idnumber:val
			},function(data){
				actionFormate(data, false,function(type,msg,data){},function(type,msg,data){
					$(html).validate().showErrors({
						"idNumber" : msg
					});
				});
			});
		}
	});
	$("#personHPTInfoModal .modal-body").html(html);
	$("#personHPTInfoModal").data("tr",tr);
	$("#personHPTInfoModal").modal("show");
}
//------------------
function showAddHPTModal(td){
	var tr = $(td).closest("tr");
	var trData = tr.data("data");
	$.post("housePurchaseMansgement/hptAddGet.do", {
		idNumber : trData.idNumber
	}, function(data) {
		actionFormate(data, false, function(type, msg, data) {
			data.money = trData.money;
			data.ticketNumber = trData.ticketNumber;
			var template = Handlebars.compile($("#addHPTModalTemplate").html());
			var html = $(template(data));
			$('[data-plugin-masked-input]',html).each(function() {
				var $this = $(this), opts = {};
				var pluginOptions = $this.data('plugin-options');
				if (pluginOptions)
					opts = pluginOptions;
				$this.themePluginMaskedInput(opts);
			});
			$('[data-plugin-datepicker]',html).each(function() {
				var $this = $(this), opts = {};
				$this.themePluginDatePicker(opts);
			});
			$("#htpInfoModal .modal-body").html(html);
			$("#htpInfoModal").data("tr",tr);
			$("#htpInfoModal").data("data",data);
			$("#htpInfoModal").modal("show");
		});
	}, "json");
}
$("#htpInfoModal").validate({
	rules: {
		money: {
			required: true,
			number:true,
			maxlength:15,
			min:0
		}, quanNumber: {
			required: true
		}
	}, submitHandler:function(form){
		var data = $("#htpInfoModal").data("data");
        var subData = {};
        subData.bonus = $("#htpInfoModal [name='money']").val();//补贴金额
        subData.makeDate = $("#htpInfoModal [name='time']").val();//制券日期
        subData.ticketNumber = $("#htpInfoModal [name='quanNumber']").val();//券号
        subData.fmlItemId = data.id;//购房券所有者id
        subData.idNumber = data.idNumber;//持有者身份证
        subData.name = data.name;//持有者姓名
        $.post("housePurchaseMansgement/hptAddAdd.do",{
        	dataJson:JSON.stringify(subData)
        },function(data){
        	actionFormate(data, true, function(type, msg, data) {
				var template = Handlebars.compile($("#logItemTemplate").html());
				var logHtml = template(subData);
        		operationLog("手工添加购房券信息","手工添加购房券信息",logHtml);
        		$("#htpInfoModal").modal("hide");
    			var tr = $("#htpInfoModal").data("tr");
    			tr.attr("isComplete","true");
				$(".operation",tr).html("该人员已被添加到系统。");
				var count = $("#notExistHPTBody > tr:not([isComplete])").size();
				$("#notExistHPTCount").html("（" + count + "）");
    		});
        },"json");
    }
});