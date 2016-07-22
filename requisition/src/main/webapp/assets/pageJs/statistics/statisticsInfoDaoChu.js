function exportStatisticsExcel(){
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
		value:"住房货币补贴情况表",
		colspan:11,
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
function getProNameHead(x,y){
	var celData = {
		value:"项目名称",
		colspan:0,
		rowspan:1,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["proName"]
	};
}
function getTotalHead(x,y){
	var celData = {
		value:"合计",
		colspan:2,
		rowspan:0,
		x:x,
		y:y
	};
	var huShuData = {
		value:"户数",
		colspan:0,
		rowspan:0,
		x:x + 1,
		y:y
	};
	var renShuData = {
		value:"人数",
		colspan:0,
		rowspan:0,
		x:x + 1,
		y:y + 1
	};
	var jinErData = {
		value:"金额",
		colspan:0,
		rowspan:0,
		x:x + 1,
		y:y + 2
	};
	return {
		y:y + 3,
		celData:[celData,huShuData,renShuData,jinErData],
		celKey:["rcdFmlCount","rcdFmlItems","rcdAmount"]
	};
}
function getAlreadyHead(x,y){
	var celData = {
		value:"已领凭证",
		colspan:2,
		rowspan:0,
		x:x,
		y:y
	};
	var huShuData = {
		value:"户数",
		colspan:0,
		rowspan:0,
		x:x + 1,
		y:y
	};
	var renShuData = {
		value:"人数",
		colspan:0,
		rowspan:0,
		x:x + 1,
		y:y + 1
	};
	var jinErData = {
		value:"金额",
		colspan:0,
		rowspan:0,
		x:x + 1,
		y:y + 2
	};
	return {
		y:y + 3,
		celData:[celData,huShuData,renShuData,jinErData],
		celKey:["nrcdFmlCount","nrcdFmlItems","nrcdAmount"]
	};
}
function getSourceHead(x,y){
	var celData = {
		value:"资金来源",
		colspan:3,
		rowspan:0,
		x:x,
		y:y
	};
	var chuZiOneData = {
		value:"国土局出资单位名称",
		colspan:0,
		rowspan:0,
		x:x  + 1,
		y:y
	};
	var chuZiTwoData = {
			value:"国土局出资单位计算规则",
			colspan:0,
			rowspan:0,
			x:x + 1,
			y:y + 1
	};
	var chuZiThreeData = {
			value:"其他出资单位名称",
			colspan:0,
			rowspan:0,
			x:x + 1,
			y:y + 2
	};
	var chuZiFourData = {
			value:"其他出资单位计算规则",
			colspan:0,
			rowspan:0,
			x:x + 1,
			y:y + 3
	};
	return {
		y:y + 4,
		celData:[celData,chuZiOneData,chuZiTwoData,chuZiThreeData,chuZiFourData],
		celKey:["moneyUnit","landBureauAmountMsg","otherMoneyUnit","nLandBureauAmountMsg"]
	};
}
function exportModol(){
	return [{
		id:"0",
		fn:getProNameHead
	},{
		id:"1",
		fn:getTotalHead
	},{
		id:"2",
		fn:getAlreadyHead
	}, {
		id:"3",
		fn:getSourceHead
	}];
}