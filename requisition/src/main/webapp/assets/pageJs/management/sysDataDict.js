loginHuKouType();loginSheBaoType();loginHuZhuType();showAddress();showPolicyType();
$("#addDictNameBtn").click(function(){
	var name = $("#addDictName").val();
	if(!name)return;
	var addUrl =$("#dictBody").attr("addUrl");
	$.post(addUrl,{
		name:name,
		entity:JSON.stringify({name:name})
	},function(data){
		actionFormate(data, true,function(type,msg,d){
			$("#addDictName").val("");
			var template = Handlebars.compile($("#dictItemTemplate").html());
			var html = $(template(d));
			html.data("data",d);
			$("#dictDataArea").append(html);
		});
	},"json");
});
$('#dictModal,#addAddressModal,#editAddressModal').modal({
	backdrop : "static",
	keyboard : false,	
	show : false
});
$('#dictModal').on("hide.bs.modal",function(){
	var resetDataFn = $('#dictModal').data("resetData");
	if(resetDataFn) resetDataFn();
});
$('#addAddressModal,#addPolicyTypeModal').on("hidden.bs.modal",function(){
	this.reset();
});
$("#addAddressModal").validate({
    rules: {
		name: {
			required: true
		}
	}, submitHandler:function(form){
		$.post("management/sysDataDictaddAddress.do",$(form).serialize(),function(data){
			actionFormate(data, true,function(type,msg,d){
				var dom = $("#addAddressModal").data("dom");
				if(dom){
					createTreeItem($(dom),d,"#addressItemTemplate");
				}else{
					createTreeItem($("#addressArea > ul"),d,"#addressItemTemplate");
				}
				$("#addAddressModal").modal("hide");
			});
		},"json");
    }
});
$("#addPolicyTypeModal").validate({//新增类型
    rules: {
		name: {
			required: true
		}
	}, submitHandler:function(form){
		var subData = {};
		subData.title = $("[name='name']",form).val();
		var pid = $("[name='pid']",form).val();
		if(pid){
			subData.pid = pid;
		}
		$.post(sendUrl.fileType_addType,subData,function(data){
			actionFormate(data, true,function(type,msg,d){
				var dom = $("#addPolicyTypeModal").data("dom");
				if(dom){
					createTreeItem($(dom),d,"#policyTypeItemTemplate");
				}else{
					createTreeItem($("#policyType > ul"),d,"#policyTypeItemTemplate");
				}
				$("#addPolicyTypeModal").modal("hide");
			});
		},"json");
    }
});
$("#editAddressModal").validate({
    rules: {
		name: {
			required: true
		}
	}, submitHandler:function(form){
		$.post("management/sysDataDicteditAddress.do",$(form).serialize(),function(data){
			actionFormate(data, true,function(type,msg,d){
				var dom = $("#editAddressModal").data("dom");
				dom.data("data",d);
				$("> span > span",dom).html(d.name);
				$("#editAddressArea").modal("hide");
			});
		},"json");
    }
});
$("#editPolicyTypeModal").validate({//修改类型
    rules: {
		name: {
			required: true
		}
	}, submitHandler:function(form){
		var subData = {};
		subData.id = $("[name='id']",form).val();
		subData.title = $("[name='name']",form).val();
		var pid = $("[name='pid']",form).val();
		if(pid){
			subData.pid = pid;
		}
		$.post(sendUrl.fileType_modify,{
			type:JSON.stringify(subData)
		},function(data){
			actionFormate(data, true,function(type,msg,d){
				var dom = $("#editPolicyTypeModal").data("dom");
				dom.data("data",d);
				$("> span > span",dom).html(subData.title);
				$("#editPolicyTypeModal").modal("hide");
			});
		},"json");
    }
});
$('.tree li:has(ul)').addClass('parent_li');
$('.tree li.parent_li > span').on('click', treeItemClick);
function treeItemClick(e){
    var children = $(this).parent('li.parent_li').find(' > ul > li');
    if (children.is(":visible")) {
        children.hide('fast');
        $(this).find(' > i').addClass('fa-plus-square').removeClass('fa-minus-square');
    } else {
        children.show('fast');
        $(this).find(' > i').addClass('fa-minus-square').removeClass('fa-plus-square');
    }
    e.stopPropagation();
}
function showAddress(){
	$.post("management/sysDataDictgetAddress.do",function(data){
		actionFormate(data, false,function(type,msg,d){
			var addressArr = [];
			initTreeDatas(null,addressArr,d);
			for ( var i in addressArr) {
				initTreeDoms($("#addressArea > ul"),addressArr[i],"#addressItemTemplate");
			}
			$('#addressArea li.parent_li > span').click();
		});
	},"json");
}
function showPolicyType(){//绑定数据
	$.get(sendUrl.fileType_getAll,function(data){
		actionFormate(data, false,function(type,msg,d){
			var policyTypeArr = [];
			initTreeDatas(null,policyTypeArr,d);
			for ( var i in policyTypeArr) {
				initTreeDoms($("#policyType > ul"),policyTypeArr[i],"#policyTypeItemTemplate");
			}
			$('#policyType li.parent_li > span').click();
		});
	},"json");
}
function editAddress(dom){
	var li = $(dom).closest("li");
	var data = li.data("data");
	var template = Handlebars.compile($("#editAddressTemplate").html());
	var html = $(template(data));
	$("#editAddressArea").html(html);
	$("#editAddressModal").data("dom",li);
	$("#editAddressModal").modal("show");
}
function editPolicyType(dom){
	var li = $(dom).closest("li");
	var data = li.data("data");
	var template = Handlebars.compile($("#editPolicyTypeTemplate").html());
	var html = $(template(data));
	$("#editPolicyTypeArea").html(html);
	$("#editPolicyTypeModal").data("dom",li);
	$("#editPolicyTypeModal").modal("show");
}
function addAddress(dom){
	if(dom){
		var li = $(dom).closest("li");
		var data = li.data("data");
		var id = data.id;
		if(id){
			$('#addAddressModal [name="pid"]').val(id);
		}
		$('#addAddressModal').data("dom",dom);
	}else{
		$('#addAddressModal [name="pid"]').val("");
		$('#addAddressModal').data("dom",null);
	}
	$('#addAddressModal').modal('show');
}
function addPolicyType(dom){
	if(dom){
		var li = $(dom).closest("li");
		var data = li.data("data");
		var id = data.id;
		if(id){
			$('#addPolicyTypeModal [name="pid"]').val(id);
		}
		$('#addPolicyTypeModal').data("dom",dom);
	}else{
		$('#addPolicyTypeModal [name="pid"]').val("");
		$('#addPolicyTypeModal').data("dom",null);
	}
	$('#addPolicyTypeModal').modal('show');
}
function deleteAddress(dom){
	if(!confirm("删除该地址将会造成地址下面子地址一并删除，确定要删除该地址？")) return;
	var li = $(dom).closest("li");
	var data = li.data("data");
	var arr = [];
	arr.push(data.id);
	$("li",li).each(function(){
		var id = $(this).data("data").id;
		arr.push(id);
	});
	$.post("management/sysDataDictdeleteAddress.do",{
		ids:JSON.stringify(arr)
	},function(data){
		actionFormate(data, true,function(type,msg,d){
			var ul = li.closest("ul");
			li.remove();
			var size = $("li",ul).size();
			if(size == 0){
				var parent = ul.parent();
				if(!parent.hasClass("tree")){
					parent.removeClass("parent_li");
					$("> span",parent).off("click",treeItemClick);
					ul.remove();
					$("span > i",parent).removeClass('fa-minus-square').removeClass('fa-plus-square');
				}
			}
		});
	},"json");
}
function deletePolicyType(dom){//删除类型
	if(!confirm("删除该类型将会造成类型下面子类型一并删除，确定要删除该类型？")) return;
	var li = $(dom).closest("li");
	var data = li.data("data");
	var arr = [];
	arr.push(data.id);
	$("li",li).each(function(){
		var id = $(this).data("data").id;
		arr.push(id);
	});
	$.post(sendUrl.fileType_delByIds,{
		ids:arr.join(",")
	},function(data){
		actionFormate(data, true,function(type,msg,d){
			var ul = li.closest("ul");
			li.remove();
			var size = $("li",ul).size();
			if(size == 0){
				var parent = ul.parent();
				if(!parent.hasClass("tree")){
					parent.removeClass("parent_li");
					$("> span",parent).off("click",treeItemClick);
					ul.remove();
					$("span > i",parent).removeClass('fa-minus-square').removeClass('fa-plus-square');
				}
			}
		});
	},"json");
}
function initTreeDatas(id,data,datas){
	for ( var i in datas) {
		var t  = datas[i];
		if(!id && !t.pid){
			data.push(t);
			initTreeDatas(t.id,t,datas);
		}else if(id == t.pid){
			if(!data.child) data.child = [];
			data.child.push(t);
			initTreeDatas(t.id,t,datas);
		}
	}
}
function initTreeDoms(dom,data,tempName){
	var li =  createTreeItem(dom,data,tempName);
	if(data.child && data.child.length > 0){
		for ( var i in data.child) {
			initTreeDoms(li,data.child[i],tempName);
		}
	}
}
function createTreeItem(dom,data,tempName){
	dom = $(dom);
	var template = Handlebars.compile($(tempName).html());
	var html = $(template(data));
	html.data("data",data);
	var li = null;
	if(dom[0].tagName.toLowerCase() == "ul"){
		dom.append(html);
		return html;
	} else if(dom[0].tagName.toLowerCase() == "li"){
		li = dom;
	} else {
		li = dom.closest("li");
	}
	if(li.hasClass("parent_li")){
		$("> ul",li).append(html);
	} else {
		li.addClass("parent_li");
		var ul = $("<ul>");
		ul.append(html);
		li.append(ul);
		$("> span",li).on("click",treeItemClick);
		$("> span i",li).addClass("fa-minus-square");
	}
	return html;
}
function editDictData(dom){
	var tr = $(dom).closest("tr");
	$(".dataVal",tr).prop("disabled",false);
	$(".editBtn",tr).css("display","none");
	$(".saveBtn",tr).css("display","initial");
	$(".editLable input",tr).val($(".nameLable",tr).html());
}
function saveDictData(dom){
	var tr = $(dom).closest("tr");
	var tdata = tr.data("data");
	var name = $(".dataVal",tr).val();
	if(tdata.name == name){
		cancleDictData(dom);
		return ;
	}
	var url = tr.closest(".modal-body").attr("saveUrl");
	$.post(url,{
		id:tdata.id,
		name:name,
		entity:JSON.stringify({name:name,id:tdata.id})
	},function(data){
		actionFormate(data, true,function(type,msg,d){
			tdata.name = name;
			cancleDictData(dom);
		});
	},"json");
}
function cancleDictData(dom){
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	$(".dataVal",tr).val(data.name);
	$(".dataVal",tr).prop("disabled",true);
	$(".editBtn",tr).css("display","initial");
	$(".saveBtn",tr).css("display","none");
}
function deleteDictData(dom){
	if(!confirm("是否需要删除？")) return;
	var tr = $(dom).closest("tr");
	var data = tr.data("data");
	var url = tr.closest(".modal-body").attr("deleteUrl");
	$.get(url,{
		id:data.id
	},function(data){
		actionFormate(data, true,function(type,msg,d){
			tr.remove();
		});
	},"json");
}
function loginHuKouType(){
	setTypes("management/sysDataDictgetAllHouseholdType.do","#householdType");
}
function loginSheBaoType(){
	setTypes("management/sysDataDictgetAllSocialsecurityType.do","#sheBaoType");
}
function loginHuZhuType(){
	setTypes("management/sysDataDictgetAllRelationshipType.do","#huZhuType");
}
function setTypes(url,dom){
	$.get(url,function(data){
		actionFormate(data, false,function(type,msg,d){
			$(dom).empty();
			$(dom).data("data",d);
			for ( var i in d) {
				var t = d[i];
				$(dom).append('<option value="'+t.id+'">'+t.name+'</option>');
			}
		});
	},"json");	
}
function showHuKouType(){
	$("#addDictName").val("");
	$("#dictModalTitle").html("管理户口性质");
	$("#dictBody")
	.attr("addUrl","management/sysDataDictaddHouseholdType.do")
	.attr("saveUrl","management/sysDataDicteditHouseholdType.do")
	.attr("deleteUrl","management/sysDataDictdeleteHouseholdType.do");
	$("#dictColName").html("户口性质名称");
	$("#dictDataArea").html("");
	var d = $("#householdType").data("data");
	for ( var i in d) {
		var tempD = d[i];
		var template = Handlebars.compile($("#dictItemTemplate").html());
		var html = $(template(tempD));
		html.data("data",tempD);
		$("#dictDataArea").append(html);
	}
	$("#dictModal").data("resetData",loginHuKouType);
	$("#dictModal").modal("show");
}
function showSheBaoType(){
	$("#addDictName").val("");
	$("#dictModalTitle").html("管理社保类型");
	$("#dictBody")
	.attr("addUrl","management/sysDataDictaddSocialsecurityType")
	.attr("saveUrl","management/sysDataDicteditSocialsecurityType")
	.attr("deleteUrl","management/sysDataDictdeleteSocialsecurityType");
	$("#dictColName").html("社保类型名称");
	$("#dictDataArea").html("");
	var d = $("#sheBaoType").data("data");
	for ( var i in d) {
		var tempD = d[i];
		var template = Handlebars.compile($("#dictItemTemplate").html());
		var html = $(template(tempD));
		html.data("data",tempD);
		$("#dictDataArea").append(html);
	}
	$("#dictModal").data("resetData",loginSheBaoType);
	$("#dictModal").modal("show");
}
function showHuZhuType(){
	$("#addDictName").val("");
	$("#dictModalTitle").html("与户主关系类型");
	$("#dictBody")
	.attr("addUrl","management/sysDataDictaddRelationshipType.do")
	.attr("saveUrl","management/sysDataDicteditRelationshipType.do")
	.attr("deleteUrl","management/sysDataDictdeleteRelationshipType.do");
	$("#dictColName").html("与户主关系名称");
	$("#dictDataArea").html("");
	var d = $("#huZhuType").data("data");
	for ( var i in d) {
		var tempD = d[i];
		var template = Handlebars.compile($("#dictItemTemplate").html());
		var html = $(template(tempD));
		html.data("data",tempD);
		$("#dictDataArea").append(html);
	}
	$("#dictModal").data("resetData",loginHuZhuType);
	$("#dictModal").modal("show");
}