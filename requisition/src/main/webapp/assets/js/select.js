function bindingSelect(optional){
	var option = {
			masterSelect:null,
			childSelect:null,
			childDefalueVal:"请选择",
			childVal:null,
			url:"",
			type:"POST",
			dataType:"json",
			beforeFn:null,
			afterFn:null,
			valName:"id",
			htmlName:"name",
	};
	option = $.extend({}, option, optional);
	$(option.masterSelect).data("bSelect",this);
	
	var _This = this;
	
	this.initChildValue = function(){
		var childSelect = $(option.childSelect);
		childSelect.empty();
		childSelect.append('<option value="">'+option.childDefalueVal+'</option>');
		var bSelect = childSelect.data("bSelect");
		if(bSelect){
			bSelect.initChildValue();
		}
	}
	
	$(option.masterSelect).change(function(){
		_This.initChildValue();
		var val = $(this).val();
		if(!val) return;
		var data = {
				id:val
		};
		if ($.isFunction(option.beforeFn)) {
			data = option.beforeFn(data) || data;
		}
		$.ajax({
			type : option.type,
			url : option.url,
			data : data,
			dataType : option.dataType,
			success : function(returnData) {
				if ($.isFunction(option.afterFn)) {
					returnData = option.afterFn(returnData) || returnData;
				}
				var childSelect = $(option.childSelect);
				for (var i = 0; i < returnData.length; i++) {
					var d = returnData[i];
					var valName = d[option.valName];
					var htmlName = d[option.htmlName];
					var selected = "";
					if(option.childVal){
						if(valName == option.childVal){
							selected = "selected";
						}
					}
					childSelect.append('<option value="'+valName+'" '+selected+'>'+htmlName+'</option>');
				}
				childSelect.change();
			}
		});
	});
}