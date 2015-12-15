<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ page import="custom.util.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<% CourseBean validClass = (CourseBean)request.getAttribute("validCourse");%>	   
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
$(document).ready(function(){	
	$("input.deleteTimeButton").unbind("click").bind("click",function(){
		var time = $.trim($(this).attr("time"));
		window.location.href="process?action=deleteCourseTime&CID=<%=validClass.getClassId()%>&time="+time;
	});	
	$("input.deleteStudentButton").unbind("click").bind("click",function(){
		var SID = $.trim($(this).attr("SID"));
		window.location.href="process?action=deleteCourseStudent&CID=<%=validClass.getClassId()%>&SID="+SID;
	});
});
</script>
</head>

<body>	
	<span>课程ID:</span><span><%=validClass.getClassId()%></span>
	<span>课程名称:</span><span><%=validClass.getName()%></span>
	<form id="basicInfoForm" action="process?action=saveBasicCourseInfo&CID=<%=validClass.getClassId()%>" method="post"><!--基本信息-->		
		<span>授课老师ID:</span><input name="teacher_name" type="text" value="<%=validClass.getTeacherName()%>">	
		<input type="submit" value="提交">
	</form>

	
	<table><!--课程时间-->
		<thead>
			<tr><th>已有课程授课时间</th></tr>
			<tr>
				<td>日期</td>
				<td>开始课时</td>
				<td>结束课时</td>
				<td></td><!--按钮对齐-->
			</tr>
		</thead>	
	</table>
	<div style="overflow-y:auto; height:200px; width:auto">
	<table>			
		<tbody>		
			<%for(int i = 0;i*4 < validClass.getTime().length();i++){
				String tempStr = validClass.getTime().substring(0+i*4,3+i*4);%>
			<tr>
				<td><%=Utility.getDayOfWeek(tempStr.substring(0,1))%></td>
				
				<%if(tempStr.substring(1,2).equals("A")){%>
					<td>10</td>
				<%}else{%>
				<%if(tempStr.substring(1,2).equals("B")){%>
					<td>11</td>
				<%}else{%>
					<td><%=tempStr.substring(1,2)%></td>	
				<%}}%>		
				
				<%if(tempStr.substring(2,3).equals("A")){%>
					<td>10</td>
				<%}else{%>
				<%if(tempStr.substring(2,3).equals("B")){%>
					<td>11</td>
				<%}else{%>
					<td><%=tempStr.substring(2,3)%></td>
				<%}}%>
				<td><input class="deleteTimeButton" time="<%=tempStr%>" type="button" value="删除"></td>
			</tr>
			<%}%>		
		</tbody>		
	</table>
	</div>
	
	<form id="addTimeForm" action="process?action=addCourseTime&CID=<%=validClass.getClassId()%>" method="post">
		<span>添加课程时间</span>
		<span>日期:</span><select name="time1"><option value="1">星期日</option><option value="2">星期一</option><option value="3">星期二</option><option value="4">星期三</option><option value="5">星期四</option><option value="6">星期五</option><option value="7">星期六</option></select>
		<span>开始课时（1-11）:</span><input name="time2" type="text">
		<span>结束课时（1-11）:</span><input name="time3" type="text">	
		<input type="submit" value="添加">
	</form>
	
	
	
	

	<table><!--学生-->
		<thead>
			<tr><th>课程学生</th></tr>
			<tr>
				<td>学号</td>
				<td></td><!--按钮对齐-->
			</tr>
		</thead>	
	</table>
	<div style="overflow-y:auto; height:200px; width:auto">
	<table>			
		<tbody>		
			<%for(int i = 0;i*11 < validClass.getStudents().length();i++){
				String tempStr = validClass.getStudents().substring(0+i*11,10+i*11);%>
			<tr>
				<td><%=tempStr%></td>				
				<td><input class="deleteStudentButton" SID="<%=tempStr%>" type="button" value="删除"></td>
			</tr>
			<%}%>		
		</tbody>		
	</table>
	</div>
	
	<form id="addStudentForm" action="process?action=addCourseStudent&CID=<%=validClass.getClassId()%>" method="post">
		<span>添加学生</span>
		<span>学号:</span><input name="SID" type="text">
		<input type="submit" value="添加">
	</form>

	
</body>