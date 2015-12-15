<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>
	<%
	Vector students = (Vector)request.getAttribute("students");
	CourseBean validClass = (CourseBean)request.getAttribute("validCourse");
	%>


<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT网页端签到</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
$(document).ready(function(){
	$("td").unbind('click').bind("click",function(){
		$("#attendStateRadio").attr("checked","");<!-- initialization-->
		$("#absentStateRadio").attr("checked","");
		$("#leaveStateRadio").attr("checked","");
		$("#editForm").attr("action","process?action=editStudentState&CID=<%= request.getParameter("CID") %>");

		var searchStr = $.trim($("#editForm").attr("action"));<!--set action value according to which one clicked-->
		$("#editForm").removeClass("hide");
		searchStr = searchStr +"&SID="+ $.trim($(this).attr("SID"));
		$("#editForm").attr("action",searchStr);
		
		var stateStr = $.trim($(this).attr("state"));<!--set state value according to which one clicked-->
		if(stateStr == "0") $("#attendStateRadio").attr("checked","checked");
		if(stateStr == "1") $("#absentStateRadio").attr("checked","checked");
		if(stateStr == "2") $("#leaveStateRadio").attr("checked","checked");		
		if(stateStr == "3") $("#lateStateRadio").attr("checked","checked");		
	});
	$("td.noStudent").unbind('click');	
	$("#editFormCancelButton").unbind('click').bind("click",function(){
		$("#editForm").addClass("hide");
	});
});
</script>
</head>

<body>
<table id="calendar" cellspacing="0">
			    <thead>			    	
			     	<tr class="year_show"><th colspan="7"><div class="title_show"><span id="year_show_year">课程：<%= validClass.getName() %></span></div></th></tr>
			    </thead>
			    <tbody class="month_show ">
				<%				
		for(int i = 0;i*6 < students.size(); i++){	
				%>
				<tr>
				<%for(int j = 0;j < 6;j++ ){
					if(j+i*6 < students.size()){
					StudentBean temp = (StudentBean) students.elementAt(i*6+j);	%>
					 <td  state=<%= temp.getState() %> SID=<%= temp.getStudentId() %>><%= temp.getName() %></td>
					 <%}else{%>
					 <td class="noStudent"> </td>
					 <%}%>					 
					 
					 

					 
				<%}%>				        
			      	</tr>		
				<%}%>	
			    </tbody>
		  	</table>						
<div id="addEvent"  >
<form class="editForm hide" id="editForm" action="process?action=editStudentState&CID=<%= request.getParameter("CID") %>" method="post">
<div class="stateType">
				<p>道勤情况</p>
				<input type="radio" id="attendStateRadio"  name="stateType" value="attend" checked=""><span>attend</span>
				<input type="radio" id="absentStateRadio" name="stateType" value="absent" checked="checked"><span>absent</span>
				<input type="radio" id="leaveStateRadio" name="stateType" value="leave"><span>leave</span>
				<input type="radio" id="lateStateRadio" name="stateType" value="late"><span>late</span>
			</div>
			<div class="eventButton">
				<input type="submit" name="submit" id="editFormSubmitButton" value="确认">
				<input id="editFormCancelButton" type="button" name="cancle" value="取消">
			</div>
</form>
</div>			

<div id="hide_mask"></div>
<body>