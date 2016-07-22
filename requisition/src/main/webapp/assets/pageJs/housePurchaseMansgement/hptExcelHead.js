function exportHptExcel(){
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
		value:"购房券发放台账",
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
function getNameHead(x,y){
	var celData = {
		value:"姓名",
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
function getIdnumberHead(x,y){
	var celData = {
		value:"身份证",
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
function getProNameHead(x,y){
	var celData = {
			value:"所属项目",
			colspan:0,
			rowspan:0,
			x:x,
			y:y
		};
		return {
			y:y + 1,
			celData:[celData],
			celKey:["proName"]
		};
}
function getNumberHead(x,y){
	var celData = {
			value:"券号",
			colspan:0,
			rowspan:0,
			x:x,
			y:y
		};
		return {
			y:y + 1,
			celData:[celData],
			celKey:["ticketNumber"]
		};
}
function getMoneyDateHead(x,y){
	var celData = {
			value:"面额",
			colspan:0,
			rowspan:0,
			x:x,
			y:y
		};
		return {
			y:y + 1,
			celData:[celData],
			celKey:["bonus"]
		};
}
function getDateHead(x,y){
	var celData = {
			value:"领券时间",
			colspan:0,
			rowspan:0,
			x:x,
			y:y
		};
		return {
			y:y + 1,
			celData:[celData],
			celKey:["recevieTime"]
		};
}
function exportModol(){
	return [{
		id:"0",
		fn:getNameHead
	},{
		id:"1",
		fn:getIdnumberHead
	},{
		id:"2",
		fn:getProNameHead
	},{
		id:"3",
		fn:getNumberHead
	},{
		id:"4",
		fn:getMoneyDateHead
	},{
		id:"5",
		fn:getDateHead
	}];
}