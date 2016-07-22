function exportRemovedInfoExcel(){
	var vals = [];
	$("#selectExportModal input[type='checkbox']:not(:checked)").each(function(){
		vals.push($(this).val());
	});
	var model = exportModol();
	var checkModels = [];
	for (var i = 0; i < model.length; i++) {
		var d = model[i];
		var state = true;
		for (var j = 0; j < vals.length; j++) {
			var val = vals[j];
			if(val == d.id){
				state = false;
			}
		}
		if(state){
			checkModels.push(d);
		}
	}
	var heads = [{
		value:"已迁户系统数据格式",
		colspan:5,
		rowspan:0,
		x:0,
		y:0
	}];
	var keys = [];
	var x = 1;
	var y = 0;
	for (var i = 0; i < checkModels.length; i++) {
		var d = checkModels[i];
		var t = d.fn(x,y);
		y = t.y;
		for (var j = 0; j < t.celData.length; j++) {
			var c = t.celData[j];
			heads.push(c);
		}
		for (var j = 0; j < t.celKey.length; j++) {
			var c = t.celKey[j];
			keys.push({
				attrName:c,
				colIndex:keys.length
			});
		}
	}
	return {heads:heads,keys:keys};
}
function getQianHuNameHead(x,y){
	var celData = {
		value:"迁户人姓名",
		colspan:0,
		rowspan:0,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["name"]
	};
}
function getZhengCeHead(x,y){
	var celData = {
		value:"适用政策",
		colspan:0,
		rowspan:0,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["policy"]
	};
}
function getIdNumberHead(x,y){
	var celData = {
			value:"迁户人身份证",
			colspan:0,
			rowspan:0,
			x:x,
			y:y
		};
		return {
			y:y + 1,
			celData:[celData],
			celKey:["idNumber"]
		};
}
function getBirthdayHead(x,y){
	var celData = {
			value:"出生日期",
			colspan:0,
			rowspan:0,
			x:x,
			y:y
		};
		return {
			y:y + 1,
			celData:[celData],
			celKey:["birthday"]
		};
}
function getRemoveDateHead(x,y){
	var celData = {
			value:"拆迁日期",
			colspan:0,
			rowspan:0,
			x:x,
			y:y
		};
		return {
			y:y + 1,
			celData:[celData],
			celKey:["removeDate"]
		};
}
function getAddressHead(x,y){
	var celData = {
			value:"迁户人地址",
			colspan:0,
			rowspan:0,
			x:x,
			y:y
		};
		return {
			y:y + 1,
			celData:[celData],
			celKey:["address"]
		};
}
function exportModol(){
	return [{
		id:"0",
		fn:getQianHuNameHead
	},{
		id:"1",
		fn:getZhengCeHead
	},{
		id:"2",
		fn:getIdNumberHead
	},{
		id:"3",
		fn:getBirthdayHead
	},{
		id:"4",
		fn:getRemoveDateHead
	},{
		id:"5",
		fn:getAddressHead
	}];
}