$("#addRegistedInfo").validate({
	rules: {
		name: {
		    required: true
		}, idNumber:{
			required: true,
			minlength:18
		}, policyStr:{
			required: true
		}, isRemove:{
			required: true
		}, isSetting:{
			required: true
		}, isTransfer:{
			required: true
		}, isSocialSecurity:{
			required: true
		}, userStateStr:{
			required: true
		}, street:{
			required: true
		}, community:{
			required: true
		}, zu:{
			required: true
		}
	},  submitHandler:function(form){
		var subData ={};
		subData.name = $("[name='name']",form).val();
		subData.idNumber = $("[name='idNumber']",form).val();
		subData.policyStr = $("[name='policyStr']",form).val();
		subData.isRemove = $("[name='isRemove']",form).val() == "true";
		subData.isSetting = $("[name='isSetting']",form).val() == "true";
		subData.isTransfer = $("[name='isTransfer']",form).val() == "true";
		subData.isSocialSecurity = $("[name='isSocialSecurity']",form).val() == "true";
		subData.userStateStr = $("[name='userStateStr']",form).val();
		subData.streetId = $("[name='street']",form).val();
		subData.communityId = $("[name='community']",form).val();
		subData.zuId = $("[name='zu']",form).val();
		subData.address = $("[name='street'] option:selected",form).html();
		subData.address += $("[name='community'] option:selected",form).html();
		subData.address += $("[name='zu'] option:selected",form).html();
		subData.address += "," + $("[name='addressOther']",form).val();
		$.post("registedInfoManagement/addRegistedInfoadd",{
			dataJson:JSON.stringify(subData)
		},function(data){
			actionFormate(data, true,function(){
				$("#addRegistedInfo")[0].reset();
				$('#selectBtn').html("请选择");
				$("#selectType").mSelect().init();
			});
		},"json");
    }
});
new bindingSelect({
	masterSelect:"#community",
	childSelect:"#zu",
	childDefalueVal:"请选择组",
	url:"share/address",
	afterFn:function(data){
		return actionFormate(data, false);
	}
});
new bindingSelect({
	masterSelect:"#street",
	childSelect:"#community",
	childDefalueVal:"请选择社区",
	url:"share/address",
	afterFn:function(data){
		return actionFormate(data, false);
	}
});