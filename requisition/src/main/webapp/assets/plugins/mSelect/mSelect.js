(function ($) {
    function MSelect(dom, optional) {
        var option = {
            onMouseover: null,//鼠标进入
            onMouseout: null,//鼠标移出
            onBeforeRequest: null,//请求之前
            onAfterRequest: null,//请求之后
            onClickItem: null,//点击A
            onOpen:null,
            onHide:null,
            url: null,//请求的URL
            dataType: "json",//数据类型
            type: "POST"//请求方式
        };
        var isInit = false;
        var dataArr = [];
        var selectData = null;
        var _This = this;
        option = $.extend({}, option, optional);
        if(!$(dom).hasClass("m-select")){
        	$(dom).addClass("m-select");
        }
        $(dom).append('<div class="m-region"><ul class="m-tab"></ul><div class="m-tab-con"></div></div>');
        $(dom).mouseover(function () {
            if ($.isFunction(option.onMouseover)) {
                option.onMouseover();
            }
        }).mouseleave(function () {
            if ($.isFunction(option.onMouseout)) {
                option.onMouseout();
            }
            _This.hide();
        });
        this.toggleShow = function () {
            if ($(".m-region:hidden", dom).size() > 0) {
                _This.open();
            } else {
                _This.hide();
            }
        }

        this.isOpen = function(){
        	return $(".m-region:hidden", dom).size() == 0;
        }
        
        this.init = function () {
            dataArr = [];
            selectData = null;
            isInit = true;
            $(".m-tab > li,.m-region .m-tab-con", dom).remove();
            sendAndCreateTab();
        }

        this.open = function () {
            if (!isInit) {
                this.init();
            }
            if ($.isFunction(option.onOpen)) {
                option.onOpen();
            }
            $(".m-region", dom).css("display", "block");
        }

        this.hide = function () {
        	if ($.isFunction(option.onHide)) {
                option.onHide();
            }
            $(".m-region", dom).css("display", "none");
        }

        this.getDatas = function () {
            return dataArr;
        }

        this.getData = function () {
            return selectData;
        }

        function createTab(title, isSelect) {
            if (!title) { title = "请选择" }
            if (isSelect != false) { isSelect = true }
            var li = $("<li>");
            var tabCon = $('<div class="m-tab-con"></div>');
            if (isSelect) {
                $("> li", ".m-tab").removeClass("m-on");
                li.addClass("m-on");
                $(".m-tab-con", dom).css("display", "none");
                tabCon.css("display", "block");
            }
            li.html(title);
            li.click(tabTitleClick);
            $(".m-tab", dom).append(li);
            li.data("con", tabCon);
            $(".m-region", dom).append(tabCon);
            var index = $(".m-tab > li", dom).size() - 1;
            tabCon.data("index", index);
            li.data("index", index);
            return index;
        }
        function getTabAtIndex(index) {
            return $(".m-tab > li", dom).eq(index);
        }
        function removeTabAtIndex(index) {
            var lis = $(".m-tab > li:gt(" + index + ")", dom);
            lis.each(function () {
                var tCon = $(this).data("con");
                tCon.remove();
            });
            lis.remove();
            var li = getTabAtIndex(index);
            if (li.size() > 0) {
                var con = li.data("con");
                con.remove();
                li.remove();
            }
        }
        function initA(tempDom, datas) {
            $(tempDom).empty();
            for (var i = 0; i < datas.length; i++) {
                var tempData = datas[i];
                var a = $('<a title="" href="javascript:;"></a>');
                if(tempData.isDisabled){
                	a.addClass("disabled");
                }
                a.html(tempData.name);
                a.attr("title", tempData.name);
                a.data("data", tempData);
                $(tempDom).append(a);
                a.click(aClick);
            }
        }

        function sendAndCreateTab(id, successFn) {
            var data = {
                id: id
            };
            if ($.isFunction(option.onBeforeRequest)) {
                option.onBeforeRequest(data);
            }
            $.ajax({
                type: option.type,
                url: option.url,
                data: data,
                dataType: option.dataType,
                success: function (d) {
                    var datas = d;
                    if (id == 3) datas = [];
                    if ($.isFunction(option.onAfterRequest)) {
                        datas = option.onAfterRequest(d);
                    }
                    if ($.isFunction(successFn)) {
                        if (successFn(datas) == false) {
                            return;
                        }
                    }
                    var index = createTab();
                    var tab = getTabAtIndex(index);
                    initA(tab.data("con"), datas);
                }
            });
        }
        function aClick() {
            var a = $(this);
            var data = a.data("data");
            if(data.isDisabled){
            	return;
            }
            var tabIndex = a.closest(".m-tab-con").data("index");
            getTabAtIndex(tabIndex).html(data.name);
            removeTabAtIndex(tabIndex + 1);
            dataArr.splice(tabIndex, dataArr.length - tabIndex);
            dataArr.push(data);
            sendAndCreateTab(data.id, function (datas) {
                var state = true;
                if (!datas || !datas.length || datas.length == 0) {
                    selectData = data;
                    _This.hide();
                    state = false;
                }
                if ($.isFunction(option.onClickItem)) {
                    if(option.onClickItem(data, state) == false) return false;
                }
                return state;
            });
        }
        function tabTitleClick() {
            var con = $(this).data("con");
            $("> li", ".m-tab").removeClass("m-on");
            $(this).addClass("m-on");
            $(".m-tab-con", dom).css("display", "none");
            con.css("display", "block");
        }
    }

    $.fn.mSelect = function (optional) {
        var $this = $(this);
        var mSelect = $this.data('mSelect');
        if (!mSelect) {
        	$this.data('mSelect', new MSelect($this, optional));
            mSelect = $this.data('mSelect');
        }
        return mSelect;
    };
})(jQuery);