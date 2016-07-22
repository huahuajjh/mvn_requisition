function DropDownInput(options) {
	var dropDownOption = {
		inputId : null,// 输入框的id
		valName : "val",// 传输参数的名称
		dropDownId : null,// 下拉框的id
		url : null,// 发送的URL
		templateId : null,// 模板的编号
		firstFn : null,// 请求之前执行的方法
		lastFn : null,// 请求之后的方法
		processFn : null,// 渲染数据的时候执行
		urlType : "POST",// 请求方式
		upTime : 600,// 多长请求一次
		selectVal : "proName",
		itemClick : null
	};
	var timeOut = 0;
	dropDownOption = $.extend({}, dropDownOption, options);

	if (!isPass)
		return;

	function isPass() {
		return (dropDownOption.inputId && dropDownOption.valName
				&& dropDownOption.dropDownId && dropDownOption.url
				&& dropDownOption.templateId && dropDownOption.urlType);
	}

	function init() {
		var inputId = dropDownOption.inputId;
		$(inputId).keyup(function() {
			clearTimeout(timeOut);
			timeOut = dropTimeOut();
		});
	}
	$(dropDownOption.inputId).focusout(function(){
		setTimeout(function(){
			$(dropDownOption.dropDownId).css("display", "none");
		}, 300);
	});

	function dropTimeOut() {
		return setTimeout(
				function() {
					var inputId = dropDownOption.inputId;
					var urlType = dropDownOption.urlType;
					var url = dropDownOption.url;
					var valName = dropDownOption.valName;
					var urlData = {};
					urlData[valName] = $(inputId).val();
					if(!urlData[valName]){
						$(dropDownOption.dropDownId).css("display", "none");
						return;
					}
					var firstFn = dropDownOption.firstFn
					if ($.isFunction(firstFn)) {
						firstFn(urlData);
					}
					$.ajax({
								type : urlType,
								url : url,
								data : urlData,
								dataType : "json",
								success : function(returnData) {
									var operationData = returnData;
									var processFn = dropDownOption.processFn;
									var dropDownId = dropDownOption.dropDownId;
									var templateId = dropDownOption.templateId;
									var lastFn = dropDownOption.lastFn;
									var template = Handlebars.compile($(
											templateId).html());
									$(dropDownId).empty();
									if ($.isFunction(lastFn)) {
										operationData = lastFn(returnData);
									}
									if (operationData.length > 0) {
										$(dropDownId).css("display", "block");
									} else {
										$(dropDownId).css("display", "none");
									}
									for ( var data in operationData) {
										var tempData = operationData[data];
										if ($.isFunction(dropDownOption.processFn)) {
											tempData = dropDownOption
													.processFn(tempData);
										}
										var html = template(tempData);
										var item = $(html);
										item.data("data", tempData);
										item.click(function() {
													var thisData = $(this).data("data");
													var val = thisData[dropDownOption.selectVal];
													if(dropDownOption.selectVal == "this"){
														val = thisData;
													}
													if ($.isFunction(dropDownOption.itemClick)) {
														dropDownOption.itemClick(thisData);
													}
													$(dropDownOption.dropDownId).css("display", "none");
													$(dropDownOption.inputId).val(val);
												});
										$(dropDownId).append(item);
									}
								}
							});
				}, dropDownOption.upTime);
	}
	init();
}
$.extend({
	dropDownInput : function(options) {
		return new DropDownInput(options);
	}
});