function operationLog(action,content,data){
	var subData = {};
	$("#menuLeftArea > li").each(function(){
		var _This = $(this);
		if($("a[href='" + location.hash + "']",_This).size() > 0){
			subData.moudle = _This.data("name");
			subData.moudleId = _This.data("id");
		}
	});
	subData.action = action;
	subData.content = content;
	subData.accountId = getCookie("login");
	subData.name = getCookie("loginName");
	subData.data=data;
	$.post(sendUrl.operationRecordWS_addRecord,{
		record:JSON.stringify(subData)
	},function(){});
}
function getCookie(name) { 
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
    if(arr=document.cookie.match(reg))
        return unescape(arr[2]); 
    else 
        return null; 
} 