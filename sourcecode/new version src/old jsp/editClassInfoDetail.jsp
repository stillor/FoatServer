<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ page import="custom.util.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<% ClassInfoBean validClass = (ClassInfoBean)request.getAttribute("validClass");
   Vector courses = (Vector)request.getAttribute("courses");
   Vector students = (Vector)request.getAttribute("students");
%>	   
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
$(document).ready(function(){	
	$("input.deleteCourseButton").unbind("click").bind("click",function(){
		var course = $.trim($(this).attr("course"));
		window.location.href="process?action=deleteClassCourse&CID=<%=validClass.getClassId()%>&course="+course;
	});	
	$("input.deleteStudentButton").unbind("click").bind("click",function(){
		var SID = $.trim($(this).attr("SID"));
		window.location.href="process?action=deleteClassStudent&CID=<%=validClass.getClassId()%>&SID="+SID;
	});
});
</script>
</head>

<body>	
	<span>班级名称:</span><span><%=validClass.getClassId()%></span>

	<table><!--班级所有课程-->
		<thead>
			<tr><th>已有课程</th></tr>
			<tr>
				<td>课程ID</td>
				<td>课程名称</td>
				<td>授课老师</td>
				<td></td><!--按钮对齐-->
			</tr>
		</thead>	
	</table>
	<div style="overflow-y:auto; height:200px; width:auto">
	<table>			
		<tbody>		
			<%for(int i = 0;i < courses.size();i++){
				CourseBean tempCour = (CourseBean)courses.elementAt(i);%>
			<tr>
				<td><%=tempCour.getClassId()%></td>
				<td><%=tempCour.getName()%></td>
				<td><%=tempCour.getTeacherName()%></td>
				<td><input class="deleteCourseButton" course="<%=tempCour.getClassId()%>" type="button" value="删除"></td>
			</tr>
			<%}%>		
		</tbody>		
	</table>
	</div>
	
	<form id="addCourseForm" action="process?action=addClassCourse&CID=<%=validClass.getClassId()%>" method="post">
		<span>添加所有课程</span>
		<span>课程ID:</span><input name="CourseId" type="text">
		<input type="submit" value="添加">
	</form>
	
	
	
	

	<table><!--所有学生-->
		<thead>
			<tr><th>班级学生</th></tr>
			<tr>
				<td>学号</td>
				<td>姓名</td>
				<td></td><!--按钮对齐-->
			</tr>
		</thead>	
	</table>
	<div style="overflow-y:auto; height:200px; width:auto">
	<table>			
		<tbody>		
			<%for(int i = 0;i < students.size();i++){
				StudentBean tempStu = (StudentBean)students.elementAt(i);%>
			<tr>
				<td><%=tempStu.getStudentId()%></td>				
				<td><%=tempStu.getName()%></td>				
				<td><input class="deleteStudentButton" SID="<%=tempStu.getStudentId()%>" type="button" value="删除"></td>
			</tr>
			<%}%>		
		</tbody>		
	</table>
	</div>
	
	<form id="addStudentForm" action="process?action=addClassStudent&CID=<%=validClass.getClassId()%>" method="post">
		<span>添加学生</span>
		<span>学号:</span><input name="SID" type="text">
		<input type="submit" value="添加">
	</form>

	
</body>