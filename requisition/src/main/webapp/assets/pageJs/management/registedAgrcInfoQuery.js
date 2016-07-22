function showProInfo(id){
	$.get("share/projectInfo",{
		id:id
	},function(html){
		$("#showProInfoArea").html(html);
		$("#showProInfoModal").modal("show");
	});
}
setProListModal("#selectProInfoModal");
$('#selectProInfoModal').on('hidden.bs.modal', function (e) {
	var selectData = $(this).data("selectData");
	if(selectData){
		$("#queryProName").val(selectData.name);
	}
});