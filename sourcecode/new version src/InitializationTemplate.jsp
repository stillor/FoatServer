<%@ page import="custom.util.*" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<%
	IniInfo iniInfo = (IniInfo)request.getParameter("iniInfo");
	%>	 
	
<!--手机终端初始化信息-->
<?xml version="1.0" encoding="utf-8"?>
<TeacherName><%= iniInfo.getTeacherName() %></TeacherName><!--登录老师的姓名-->
<AdminPhone><%= iniInfo.getAdminPhone() %></AdminPhone><!--管理员的联系方式-->
