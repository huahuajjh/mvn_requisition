function GenerateData(options) {
	var generateOptions = {
		pageNum : 30,
		visiblePages : 5,
		prev : '<button type="button" class="btn btn-default"><i class="fa fa-angle-double-left"></i></button>',
		next : '<button type="button" class="btn btn-default"><i class="fa fa-angle-double-right"></i></button>',
		page : '<button type="button" class="btn btn-default">{{page}}</button>',
		activeClass : 'btn-primary',
		removeClass : 'btn-default',
		pageArea : null,
		dataAreaId : null,
		dataArea : null,
		firstFn : null,
		lastFn : null,
		processFn : null,
		domFn : null,
		url : null,
		urlType : "POST"
	};
	var pageCount = 100;
	var pageIndex = 1;
	var returnData = null;

	generateOptions = $.extend({}, generateOptions, options);

	function init() {
		var tempDom = $(generateOptions.pageArea).data("jqPaginator");
		if (!tempDom) {
			$.jqPaginator(generateOptions.pageArea, {
				pageSize : generateOptions.pageNum,
				totalCounts : pageCount,
				visiblePages : generateOptions.visiblePages,
				currentPage : pageIndex,
				prev : generateOptions.prev,
				next : generateOptions.next,
				page : generateOptions.page,
				onPageChange : function(num, type) {
					if (type == "change") {
						pageIndex = num;
						pageInit();
					}
				}
			});
		} else {
			tempDom.options.pageSize = generateOptions.pageNum;
			tempDom.options.totalCounts = pageCount;
			tempDom.options.totalPages = Math.ceil(pageCount
					/ generateOptions.pageNum);
			tempDom.options.visiblePages = generateOptions.visiblePages;
			tempDom.options.currentPage = pageIndex;
			tempDom.options.prev = generateOptions.prev;
			tempDom.options.next = generateOptions.next;
			tempDom.options.page = generateOptions.page;
			tempDom.render();
		}
	}

	function pageInit() {
		var urlData = {
			pageIndex : pageIndex,
			pageNum : generateOptions.pageNum
		};
		if ($.isFunction(generateOptions.firstFn)) {
			generateOptions.firstFn(urlData);
		}
		if (!generateOptions.dataAreaId || !generateOptions.dataArea) {
			return;
		}
		$.ajax({
			type : generateOptions.urlType,
			url : generateOptions.url,
			data : urlData,
			dataType : "json",
			success : function(returnData) {
				var operationData = returnData;
				if ($.isFunction(generateOptions.lastFn)) {
					operationData = generateOptions.lastFn(returnData);
				}
				var source = $(generateOptions.dataAreaId).html();
				var template = Handlebars.compile(source);
				$(generateOptions.dataArea).empty();
				for ( var data in operationData.datas) {
					var tempData = operationData.datas[data];
					if ($.isFunction(generateOptions.processFn)) {
						tempData = generateOptions.processFn(tempData);
					}
					var html = template(tempData);
					var htmlDom = $(html);
					htmlDom.data("data",tempData);
					if ($.isFunction(generateOptions.domFn)) {
						generateOptions.domFn(htmlDom);
					}
					$(generateOptions.dataArea).append(htmlDom);
				}
				pageCount = operationData.totalCount;
				init();
			}
		});
	}

	this.goPage = function(index) {
		pageIndex = index;
		pageInit();
	}

	this.refreshData = function() {
		pageInit();
	}

	this.setPageNum = function(num) {
		if (num > 0) {
			generateOptions.pageNum = num;
		}
	}
	pageInit();
}
$.extend({
	generateData : function(options) {
		return new GenerateData(options);
	}
});