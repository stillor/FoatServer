<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ page import="custom.util.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>
  
	   

	   
	   
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
</script>
</head>

<body>	
	<form id="basicInfoForm" action="process?action=saveBasicInfo" method="post"><!--基本信息-->		
	<span>管理员联系电话:</span><input name="adminPhone" type="text" value="<%=(String)request.getAttribute("adminPhone")%>">	
	<input type="submit" value="提交">
	</form>
	
	
	<form id="passwordChangeForm" action="process?action=changeAdminPassword" method="post"><!--基本信息-->		
	<span>原始密码:</span><input name="nowPassword" type="password" value="">	
	<span>新密码:</span><input name="newPassword" type="password" value="">	
	<span>再次输入新密码:</span><input name="newPassword_again" type="password" value="">	
	<input type="submit" value="提交">
	</form>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Basic Info Changed")){
				%>
		<script type="text/javascript">
			alert("修改成功");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Password Wrong")){
				%>
		<script type="text/javascript">
			alert("原始密码错误");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Password_Again Wrong")){
				%>
		<script type="text/javascript">
			alert("两次新密码不一致");
		</script>
				<%}%>
			<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Password Changed")){
				%>
		<script type="text/javascript">
			alert("修改密码成功");
		</script>
				<%}%>

</body>