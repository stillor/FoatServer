<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<% Vector students = (Vector)request.getAttribute("students");%>	   
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
$(document).ready(function(){
	$("input.editButton").unbind("click").bind("click",function(){
		var SID = $.trim($(this).attr("SID"));
		window.location.href="process?action=editStudentInfoDetail&SID="+SID;
	});
	$("input.deleteButton").unbind("click").bind("click",function(){
		var SID = $.trim($(this).attr("SID"));
		window.location.href="process?action=deleteStudent&SID="+SID;
	});
});
</script>
</head>

<body>
	<table>
		<thead>
			<tr><th>所有学生列表</th></tr>
			<tr>
				<td>学号</td>
				<td>姓名</td>
				<td>联系方式</td>
				<td></td><!--按钮对齐-->
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
				<td><%=tempStu.getPhone()%></td>
				<td><input id="editButton" class="editButton" SID="<%=tempStu.getStudentId()%>" type="button" value="修改"></td>
				<td><input class="deleteButton" SID="<%=tempStu.getStudentId()%>" type="button" value="删除"></td>
			</tr>
			<%}%>		
		</tbody>		
	</table>
	</div>
	<form id="searchForm" action="process?action=editStudentInfoDetail" method="post">
		<input name="SID" type="text">
		<input type="submit" value="搜索">
	</form>
</body>