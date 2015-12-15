$(document).ready(function(){
	$("td").unbind('click').bind("click",function(){
		var searchStr = $.trim($(this).attr("SID"));
		window.location.href="process?action=checkIn&SID="+searchStr;
	});
	$("#editFormCancelButton").unbind('click').bind("click",function(){
		window.location.href="process?action=checkIn";
	});
});

