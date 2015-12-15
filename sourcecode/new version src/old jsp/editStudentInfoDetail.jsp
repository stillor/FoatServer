<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ page import="custom.util.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<% StudentBean validStudent = (StudentBean)request.getAttribute("validStudent");
%>	   
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
</script>
</head>

<body>	
		<form id="infoForm" action="process?action=saveStudentInfo&SID=<%=validStudent.getStudentId()%>" method="post"><!--学生信息-->		
		<span>学生姓名:</span><input name="name" type="text" value="<%=validStudent.getName()%>">	
		<span>学生性别:</span><input name="sex" type="text" value="<%=validStudent.getSex()%>">	
		<span>电话号码:</span><input name="phone" type="text" value="<%=validStudent.getPhone()%>">	
		<span>电子邮箱:</span><input name="email" type="text" value="<%=validStudent.getEmail()%>">	
		<input type="submit" value="提交">
		</form>	
</body>