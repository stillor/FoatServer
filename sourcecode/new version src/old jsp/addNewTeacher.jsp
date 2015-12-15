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
</script>
</head>

<body>
	<form id="addTeacherForm" action="process?action=addNewTeacher" method="post">
		<span>添加新教师账号</span>
		<span>ID:</span><input name="TID" type="text">
		<span>密码:</span><input name="password" type="password">
		<span>再次输入密码:</span><input name="password_again" type="password">
		<span>教师姓名:</span><input name="name" type="text">
		<span>教师电话:</span><input name="phone" type="text">
		<input type="submit" value="添加">
	</form>					
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Teacher ID Invalid")){
				%>
		<script type="text/javascript">
			alert("教师ID已被使用");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("New Teacher Added")){
				%>
		<script type="text/javascript">
			alert("添加新教师成功");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Input ID Or Password")){
				%>
		<script type="text/javascript">
			alert("请输入完全");
		</script>
				<%}%>
								<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Password_again Not Right")){
				%>
		<script type="text/javascript">
			alert("两次输入的密码不一致");
		</script>
				<%}%>
				

</body>