<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ page import="custom.util.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<% TeacherBean validTeacher = (TeacherBean)request.getAttribute("validTeacher");
   Vector classes = (Vector)request.getAttribute("classes");
%>	   
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
$(document).ready(function(){	
	$("input.deleteClassButton").unbind("click").bind("click",function(){
		var CID = $.trim($(this).attr("CID"));
		window.location.href="process?action=deleteTeacherClass&TID=<%=validTeacher.getTeacherId()%>&CID="+CID;
	});	
});
</script>
</head>

<body>	
	<span>教师ID:<%=validTeacher.getTeacherId()%></span>
	<form id="basicInfoForm" action="process?action=saveBasicTeacherInfo&TID=<%=validTeacher.getTeacherId()%>" method="post"><!--基本信息-->			
	<span>教师姓名:</span><input name="name" type="text" value="<%=validTeacher.getName()%>">	
	<span>教师电话:</span><input name="phone" type="text" value="<%=validTeacher.getPhone()%>">	
	<input type="submit" value="提交">
	</form>
	
	
	<form id="passwordChangeForm" action="process?action=changeTeacherPassword&TID=<%=validTeacher.getTeacherId()%>" method="post"><!--基本信息-->		
	<span>教师新密码:</span><input name="newPassword" type="password" value="">	
	<span>再次输入新密码:</span><input name="newPassword_again" type="password" value="">	
	<input type="submit" value="提交">
	</form>
	
	
	<table><!--教师管理的所有班级-->
		<thead>
			<tr><th>管理班级列表</th></tr>
			<tr>
				<td>班级名称</td>
				<td>班级ID</td>
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
				<td><input class="deleteClassButton" CID="<%=tempCla.getClassId()%>" type="button" value="删除"></td>
			</tr>
			<%}%>		
		</tbody>		
	</table>
	</div>
	
	<form id="addCourseForm" action="process?action=addTeacherClass&TID=<%=validTeacher.getTeacherId()%>" method="post">
		<span>添加管理班级</span>
		<span>班级ID:</span><input name="CID" type="text">
		<input type="submit" value="添加">
	</form>
	
	
					<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Teacher Basic Saved")){
				%>
		<script type="text/javascript">
			alert("修改成功");
		</script>
				<%}%>
							<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("NewPassword_again Not Right")){
				%>
		<script type="text/javascript">
			alert("两次输入密码不一致");
		</script>
				<%}%>
										<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Teacher Password Saved")){
				%>
		<script type="text/javascript">
			alert("修改密码成功");
		</script>
				<%}%>
												<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Class Deleted")){
				%>
		<script type="text/javascript">
			alert("删除管理班级成功");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Class Already Exist")){
				%>
		<script type="text/javascript">
			alert("课程已在管理中");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Class Added")){
				%>
		<script type="text/javascript">
			alert("课程添加成功");
		</script>
				<%}%>
								<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Class Not Exist")){
				%>
		<script type="text/javascript">
			alert("对应班级不存在");
		</script>
				<%}%>
					<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("New Teacher Added")){
				%>
		<script type="text/javascript">
			alert("添加新教师成功");
		</script>
				<%}%>


	
</body>