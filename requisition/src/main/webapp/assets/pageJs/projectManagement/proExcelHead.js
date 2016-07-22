function exportProExcel(){
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
	var heads = [];
	var keys = [];
	var x = 2;
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
function getXuHaoHead(x,y){
	var celData = {
		value:"序号",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["num"]
	};
}
function getXiangMuMingChengHead(x,y){
	var celData = {
		value:"在拆项目名称",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["proName"]
	};
}
function getXiangMuTianDate(x,y){
	var celData = {
		value:"填报时间",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["date"]
	};
}
function getShenPiDanHaoHead(x,y){
	var celData = {
		value:"审批单号",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["approvalNumber"]
	};
}
function getMoneyUnitHead(x,y){
	var celData = {
		value:"国土局出资(单位名称)",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["moneyUnit"]
	};
}
function getOtherMoneyUnitHead(x,y){
	var celData = {
		value:"其他单位出资(单位名称)",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["otherMoneyUnit"]
	};
}
function getXiangMuFenLeiHead(x,y){
	var celData = {
		value:"项目分类",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["categoryStr"]
	};
}
function getXiangMuLeiXingHead(x,y){
	var celData = {
		value:"项目类型（用√标识）",
		colspan:1,
		rowspan:1,
		x:x,
		y:y
	};
	var jiChuSheShiData = {
		value:"基础设施",
		colspan:0,
		rowspan:1,
		x:x + 2,
		y:y
	};
	var qiTaData = {
			value:"其他",
			colspan:0,
			rowspan:1,
			x:x + 2,
			y:y + 1
	};
	return {
		y:y+2,
		celData:[celData,jiChuSheShiData,qiTaData],
		celKey:["proTypeStrInfra","proTypeStrOther"]
	};
}
function getShiFouBenYueQiDongHead(x,y){
	var celData = {
		value:"本月新启动项目（用√标识）",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["newStart"]
	};
}
function getShiFouBenYueWanChengJieSuanHead(x,y){
	var celData = {
		value:"是否为本月完成结算项目（用√标识）",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["curMonthComplete"]
	};
}
function getYongDiWeiZhiHead(x,y){
	var celData = {
		value:"用地位置",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["totalAddress"]
	};
}
function getYiGongGaoHead(x,y){
	var celData = {
		value:"一公告",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["announce1"]
	};
}
function getYiGongGaoNumberHead(x,y){
	var celData = {
		value:"一公告文号",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["announce1Number"]
	};
}
function getErGongGaoHead(x,y){
	var celData = {
		value:"二公告",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["announce2"]
	};
}
function getErGongGaoNumberHead(x,y){
	var celData = {
		value:"二公告文号",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["announce2Number"]
	};
}
function getSanGongGaoHead(x,y){
	var celData = {
		value:"三公告",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["announce3"]
	};
}
function getSanGongGaoNumberHead(x,y){
	var celData = {
		value:"三公告文号",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["announce3Number"]
	};
}
function getTengDiQingKuangHead(x,y){
	var celData = {
		value:"腾地情况",
		colspan:3,
		rowspan:0,
		x:x,
		y:y
	};
	var zhengDiMianJiData = {
			value:"征地面积",
			colspan:0,
			rowspan:2,
			x:x + 1,
			y:y
	};
	var yiTengDiData = {
			value:"已腾地",
			colspan:2,
			rowspan:1,
			x:x+1,
			y:y + 1
	};
	var leiJiData = {
			value:"累计",
			colspan:0,
			rowspan:0,
			x:x+3,
			y:y + 1
	};
	var benYueData = {
			value:"本月",
			colspan:0,
			rowspan:0,
			x:x+3,
			y:y + 2
	};
	var benNianData = {
			value:"本年",
			colspan:0,
			rowspan:0,
			x:x+3,
			y:y + 3
	};
	return {
		y:y + 4,
		celData:[celData,zhengDiMianJiData,yiTengDiData,leiJiData,benYueData,benNianData],
		celKey:["requisitionArea","requisitionLandAreaTotal","removedLandArea","requisitionLandAreaYear"]
	};
}
function getChaiChuFngWuQingKuangHead(x,y){
	var celData = {
		value:"拆除房屋情况",
		colspan:7,
		rowspan:0,
		x:x,
		y:y
	};
	var celData1 = {
			value:"应拆栋数（合法）",
			colspan:0,
			rowspan:1,
			x:x + 1,
			y:y
		};
	var celData2 = {
			value:"累计",
			colspan:0,
			rowspan:0,
			x:x + 3,
			y:y
		};
	var celData3 = {
			value:"已拆栋数  （合法）",
			colspan:2,
			rowspan:1,
			x:x + 1,
			y:y + 1
		};
	var celData4 = {
			value:"累计",
			colspan:0,
			rowspan:0,
			x:x + 3,
			y:y + 1
		};
	var celData5 = {
			value:"本月",
			colspan:0,
			rowspan:0,
			x:x + 3,
			y:y + 2
		};
	var celData6 = {
			value:"本年",
			colspan:0,
			rowspan:0,
			x:x + 3,
			y:y + 3
		};
	var celData7 = {
			value:"应拆总面积（合法）",
			colspan:0,
			rowspan:1,
			x:x + 1,
			y:y + 4
		};
	var celData8 = {
			value:"累计",
			colspan:0,
			rowspan:0,
			x:x + 3,
			y:y + 4
		};
	var celData9 = {
			value:"已拆面积（合法）",
			colspan:2,
			rowspan:1,
			x:x + 1,
			y:y + 5
		};
	var celData10 = {
			value:"累计",
			colspan:0,
			rowspan:0,
			x:x + 3,
			y:y + 5
		};
	var celData11 = {
			value:"本月",
			colspan:0,
			rowspan:0,
			x:x + 3,
			y:y + 6
		};
	var celData12 = {
			value:"本年",
			colspan:0,
			rowspan:0,
			x:x + 3,
			y:y + 7
		};
	return {
		y:y + 8,
		celData:[celData,celData1,celData2,celData3,celData4,celData5,celData6,celData7,celData8,celData9,celData10,celData11,celData12],
		celKey:["shouldRemoveBuildings","removedBuildingsLegalTotal","removedBuildings","removedBuildingsLegalYear","shouldRemoveLegalArea","removedAreaLegalTotal","removedLegalArea","removedAreaLegalYear"]
	};
}
function getAnZhiQingKuangHead(x,y){
	var celData = {
		value:"拆迁安置人口情况",
		colspan:3,
		rowspan:0,
		x:x,
		y:y
	};
	var celData1 = {
			value:"应动迁人口（包括非农、半边户、独生子女）",
			colspan:0,
			rowspan:2,
			x:x + 1,
			y:y
	};
	var celData2 = {
			value:"已动迁人口",
			colspan:2,
			rowspan:1,
			x:x+1,
			y:y + 1
	};
	var celData3 = {
			value:"累计",
			colspan:0,
			rowspan:0,
			x:x+3,
			y:y + 1
	};
	var celData4 = {
			value:"本月",
			colspan:0,
			rowspan:0,
			x:x+3,
			y:y + 2
	};
	var celData5 = {
			value:"本年",
			colspan:0,
			rowspan:0,
			x:x+3,
			y:y + 3
	};
	return {
		y:y + 4,
		celData:[celData,celData1,celData2,celData3,celData4,celData5],
		celKey:["shouldMovePopulation","removedPopulationTotal","movedPopulation","removedPopulationYear"]
	};
}
function getXiaDaTengDiShuHead(x,y){
	var celData = {
		value:"本年下达限期腾地决定书",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["yearDeadlineFile"]
	};
}
function getShenQingFaYuanZhiXingHead(x,y){
	var celData = {
		value:"本年申请法院执行",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["yearCourtExecute"]
	};
}
function getYiFaQiangZhiTengDiHead(x,y){
	var celData = {
		value:"本年依法实施强制腾地户数",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["yearLegalRemoved"]
	};
}
function getLiuQianXingMuHead(x,y){
	var celData = {
		value:"属“六前项目”请打√",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["sixForheadPro"]
	};
}
function getBeiZhuHead(x,y){
	var celData = {
		value:"备注",
		colspan:0,
		rowspan:3,
		x:x,
		y:y
	};
	return {
		y:y + 1,
		celData:[celData],
		celKey:["remark"]
	};
}
function exportModol(){
	return [{
		id:"0",
		fn:getXuHaoHead
	},{
		id:"1",
		fn:getXiangMuMingChengHead
	},{
		id:"2",
		fn:getXiangMuTianDate
	}, {
		id:"3",
		fn:getShenPiDanHaoHead
	},{
		id:"4",
		fn:getMoneyUnitHead
	},{
		id:"5",
		fn:getOtherMoneyUnitHead
	},{
		id:"6",
		fn:getXiangMuFenLeiHead
	},{
		id:"7",
		fn:getXiangMuLeiXingHead
	},{
		id:"8",
		fn:getShiFouBenYueQiDongHead
	},{
		id:"9",
		fn:getShiFouBenYueWanChengJieSuanHead
	},{
		id:"10",
		fn:getYongDiWeiZhiHead
	},{
		id:"11",
		fn:getYiGongGaoHead
	},{
		id:"12",
		fn:getYiGongGaoNumberHead
	},{
		id:"13",
		fn:getErGongGaoHead
	},{
		id:"14",
		fn:getErGongGaoNumberHead
	},{
		id:"15",
		fn:getSanGongGaoHead
	},{
		id:"16",
		fn:getSanGongGaoNumberHead
	},{
		id:"17",
		fn:getTengDiQingKuangHead
	},{
		id:"18",
		fn:getChaiChuFngWuQingKuangHead
	},{
		id:"19",
		fn:getAnZhiQingKuangHead
	},{
		id:"20",
		fn:getXiaDaTengDiShuHead
	},{
		id:"21",
		fn:getShenQingFaYuanZhiXingHead
	},{
		id:"22",
		fn:getYiFaQiangZhiTengDiHead
	},{
		id:"23",
		fn:getLiuQianXingMuHead
	},{
		id:"24",
		fn:getBeiZhuHead
	}];
}