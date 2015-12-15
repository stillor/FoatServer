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
		window.location.href="process?action=editClassInfoDetail&CID="+CID;
	});
	$("input.deleteButton").unbind("click").bind("click",function(){
		var CID = $.trim($(this).attr("CID"));
		window.location.href="process?action=deleteClass&CID="+CID;
	});
});
</script>
</head>

<body>
	<table>
		<thead>
			<tr><th>已有班级列表</th></tr>
			<tr>
				<td>班级名称</td>
				<td>班级ID</td>
				<td></td><!--按钮对齐-->
				<td></td><!--按钮对齐-->
			</tr>
		</thead>	
	</table>
	<div style="overflow-y:auto; height:200px; width:auto">
	<table>			
		<tbody>		
			<%for(int i = 0;i < classes.size();i++){
				ClassInfoBean tempCla = (ClassInfoBean)classes.elementAt(i);%>
			<tr>
				<td><%=tempCla.getClassName()%></td>
				<td><%=tempCla.getClassId()%></td>
				<td><input id="editButton" class="editButton" CID="<%=tempCla.getClassId()%>" type="button" value="修改"></td>
				<td><input class="deleteButton" CID="<%=tempCla.getClassId()%>" type="button" value="删除"></td>
			</tr>
			<%}%>		
		</tbody>		
	</table>
	</div>
	<form id="searchForm" action="process?action=editClassInfoDetail" method="post">
		<input name="CID" type="text">
		<input type="submit" value="搜索">
	</form>
	
		<form id="addClassForm" action="process?action=addNewClass" method="post">
		<span>添加新班级</span>
		<span>班级名称:</span><input name="className" type="text">
		<span>班级ID:</span><input name="CID" type="text">
		<input type="submit" value="添加">
	</form>
	
	
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Delete Class Succeeded")){
				%>
		<script type="text/javascript">
			alert("删除班级成功");
		</script>
				<%}%>
								<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Input ID Or Name")){
				%>
		<script type="text/javascript">
			alert("请输入完全");
		</script>
				<%}%>
								<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Class ID Invalid")){
				%>
		<script type="text/javascript">
			alert("班级ID已被使用");
		</script>
				<%}%>
								<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("New Class Added")){
				%>
		<script type="text/javascript">
			alert("新班级添加成功");
		</script>
				<%}%>
												<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Class Not Found")){
				%>
		<script type="text/javascript">
			alert("为查找到对应班级");
		</script>
				<%}%>
				
				
				
				
				
				
</body>