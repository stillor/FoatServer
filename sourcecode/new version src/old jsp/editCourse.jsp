<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<% Vector classes = (Vector)request.getAttribute("classes");%>	   
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
$(document).ready(function(){
	$("input.editButton").unbind("click").bind("click",function(){
		var CID = $.trim($(this).attr("CID"));
		window.location.href="process?action=editCourseInfoDetail&CID="+CID;
	});
	$("input.deleteButton").unbind("click").bind("click",function(){
		var CID = $.trim($(this).attr("CID"));
		window.location.href="process?action=deleteCourse&CID="+CID;
	});
});
</script>
</head>

<body>
	<table>
		<thead>
			<tr><th>已有课程列表</th></tr>
			<tr>
				<td>课程名称</td>
				<td>课程ID</td>
				<td>任课老师</td>
				<td></td><!--按钮对齐-->
				<td></td><!--按钮对齐-->
			</tr>
		</thead>	
	</table>
	<div style="overflow-y:auto; height:200px; width:auto">
	<table>			
		<tbody>		
			<%for(int i = 0;i < classes.size();i++){
				CourseBean tempCla = (CourseBean)classes.elementAt(i);%>
			<tr>
				<td><%=tempCla.getName()%></td>
				<td><%=tempCla.getClassId()%></td>
				<td><%=tempCla.getTeacherName()%></td>
				<td><input id="editButton" class="editButton" CID="<%=tempCla.getClassId()%>" type="button" value="修改"></td>
				<td><input class="deleteButton" CID="<%=tempCla.getClassId()%>" type="button" value="删除"></td>
			</tr>
			<%}%>		
		</tbody>		
	</table>
	</div>
	<form id="searchForm" action="process?action=editCourseInfoDetail" method="post">
		<input name="CID" type="text">
		<input type="submit" value="搜索">
	</form>
</body>