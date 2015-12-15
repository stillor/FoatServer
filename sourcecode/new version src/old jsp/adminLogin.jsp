<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/orataglib" prefix="ora" %>


<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT</title>
</head>
<body>
	<!--通用头-->
	<div id="head"/>
	<!--登陆页面主体部分-->
	<div id="content">
		<div class="adminLogin">
			<h1 class="loginHead">管理员登陆</h1>
			<form class="loginForm" action="process?action=adminAuthenticate" method="post">
			
				<% String origURL = request.getParameter("origURL"); %>
				<input type="hidden" name="origURL" 
					value="<%= origURL == null ? "" : origURL %>">
				<div class="inputPos">
					<input class="inputType adminName" type="text" name="adminName" 
						value="<ora:getCookieValue name="adminName"/>" />
					<input class="inputType password"  type="password" name="password"
						value="<ora:getCookieValue name="password"/>" />
						
				<%	
					if(request.getParameter("errorMsg") != null && request.getParameter("errorMsg").equals("Invalid Admin Name or Password")){
				%>
					<div id="admin_input_error">用户名或密码错误</div>
				<%}%>
																						
				</div>
				<div class="check">
					<input type="checkbox" name="rememberName" 
						<%= CookieUtils.isCookieSet("adminName", request) ? "checked" : "" %>/>
					<span>记住用户名</span>
					<input class="checkPwd" type="checkbox" name="rememberPSW" 
						<%= CookieUtils.isCookieSet("password", request) ? "checked" : "" %>/>
					<span>记住密码</span>
				</div>
				<input class="submit" type="submit" value="登陆" />
			</form>			
		</div>
	</div>
	<!--通用脚注-->
	<div id="footer"/>
</body>
</html>