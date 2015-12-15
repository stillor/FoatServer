<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>
	<%
	Vector students = (Vector)request.getAttribute("students");
	CourseBean validClass = (CourseBean)request.getAttribute("validCourse");
	%>





<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="static/h-ui/css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="lib/bootstrapSwitch/bootstrapSwitch.css" rel="stylesheet" type="text/css" />
<link href="lib/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css" />
<!-- <link rel="stylesheet" type="text/css" href="style/calendar.css"> -->
<link rel="stylesheet" type="text/css" href="checkin.css">
<!--[if IE 7]>
<link href="lib/font-awesome/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<link href="lib/iconfont/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
	<title>FOAT网页端签到</title>

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
<div>
<nav class="mainnav cl">
  <ul class="cl">
    <li class="current"><a href="">FOAT</a></li>
  </ul>
</nav>



 </div>
<div class="clear"></div>

<div class="context1">
	<div class="title1">
		<b>课程：<%= validClass.getName() %></b>
		</div>
		<div class="tbody1">
    <table>
			    <tbody>
				
								<%				
		for(int i = 0;i*5 < students.size(); i++){	
				%>
				<tr>
				<%for(int j = 0;j < 5;j++ ){
					if(j+i*5 < students.size()){
					StudentBean temp = (StudentBean) students.elementAt(i*5+j);	%>
					 <td  state=<%= temp.getState() %> SID=<%= temp.getStudentId() %>><%= temp.getName() %></td>
					 <%}else{%>
					 <td class="noStudent"> </td>
					 <%}%>					 
				<%}%>				        
			      	</tr>		
				<%}%>	
			    </tbody>
		</table>
		</div>
</div>	


					
<div id="addEvent">
<form class="editForm hide" id="editForm" action="process?action=editStudentState&CID=<%= request.getParameter("CID") %>" method="post" >
    
            
         <div class="stateType">
           <div class="title2">
				    <b>道勤情况</b>
		       </div>
				   <div class="radio-box">

				    <input type="radio" id="attendStateRadio"  name="stateType" value="attend" checked=""><label for="attendStateRadio">正常</label>

				    <input type="radio" id="leaveStateRadio" name="stateType" value="leave"><label for="leaveStateRadio">请假</label>

				    <input type="radio" id="lateStateRadio" name="stateType" value="late"><label for="lateStateRadio">迟到</label>

				    <input type="radio" id="absentStateRadio" name="stateType" value="absent" checked="checked"><label for="absentStateRadio">缺席</label>
				   </div>
				   <div class="eventButton">
			      <div class="okay">
			      <input  class="btn btn-primary radius" type="submit" name="submit" id="editFormSubmitButton" value="确认"></div>
		 	      <div class="reset">
			      <input  class="btn btn-warning radius" id="editFormCancelButton" type="button" name="cancle" value="取消"></div>			
			     </div>
				 </div>
	 
</form>
</div>			

</body>
</html>

