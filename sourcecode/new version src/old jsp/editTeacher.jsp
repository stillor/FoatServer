<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<% Vector teachers = (Vector)request.getAttribute("teachers");%>	   
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
$(document).ready(function(){
	$("input.editButton").unbind("click").bind("click",function(){
		var TID = $.trim($(this).attr("TID"));
		window.location.href="process?action=editTeacherInfoDetail&TID="+TID;
	});
	$("input.deleteButton").unbind("click").bind("click",function(){
		var TID = $.trim($(this).attr("TID"));
		window.location.href="process?action=deleteTeacher&TID="+TID;
	});
});
</script>
</head>

<body>
	<table>
		<thead>
			<tr><th>已有教师列表</th></tr>
			<tr>
				<td>教师ID</td>				
				<td>姓名</td>
				<td>电话号码</td>
				<td></td><!--按钮对齐-->
				<td></td><!--按钮对齐-->
			</tr>
		</thead>	
	</table>
	<div style="overflow-y:auto; height:200px; width:auto">
	<table>			
		<tbody>		
			<%for(int i = 0;i < teachers.size();i++){
				TeacherBean tempTea = (TeacherBean)teachers.elementAt(i);%>
			<tr>
				<td><%=tempTea.getTeacherId()%></td>
				<td><%=tempTea.getName()%></td>
				<td><%=tempTea.getPhone()%></td>
				<td><input id="editButton" class="editButton" TID="<%=tempTea.getTeacherId()%>" type="button" value="修改"></td>
				<td><input class="deleteButton" TID="<%=tempTea.getTeacherId()%>" type="button" value="删除"></td>
			</tr>
			<%}%>		
		</tbody>		
	</table>
	</div>
	<form id="searchForm" action="process?action=editTeacherInfoDetail" method="post">
		<input name="TID" type="text">
		<input type="submit" value="搜索">
	</form>

	
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Delete Teacher Succeeded")){
				%>
		<script type="text/javascript">
			alert("删除教师成功");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Teacher Not Found")){
				%>
		<script type="text/javascript">
			alert("未找到对应的教师");
		</script>
				<%}%>

</body>