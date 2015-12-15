<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%
Vector classes = (Vector)request.getAttribute("classes");
%>
<!--手机终端初始化信息-->
<?xml version="1.0" encoding="utf-8"?>
<TeacherName><%= (String)request.getAttribute("teacherName")%></TeacherName><!--登录老师的姓名-->
<AdminPhone><%= (String)request.getAttribute("adminPhone") %></AdminPhone><!--管理员的联系方式-->
<Classes><!--所管班级列表-->
<%for(int i = 0;i<classes.size();i++){
ClassInfoBean tempCla = (ClassInfoBean)classes.elementAt(i);
%>
	<ClassName><%=tempCla.getClassName()%></ClassName>
<%}%>
</Classes>
<AbsentStudents><!--缺席学生列表-->
	<Student>
		<Name>任城</Name><!--姓名-->
		<ID>2131601066</ID><!--学号-->
		<Phone>18874563214</Phone><!--手机号-->
		<Email>123456789@qq.com</Email>		
		<Class>软件31</Class>
	</Student>
	<Student>
		<Name>冉天</Name><!--姓名-->
		<ID>2131601026</ID><!--学号-->
		<Phone>18874563217</Phone><!--手机号-->
		<Email>123455789@qq.com</Email>		
		<Class>软件31</Class>
	</Student>
</AbsentStudents>
<LeaveStudents><!--请假学生列表-->
	<Student>
		<Name>黄骅</Name><!--姓名-->
		<ID>2131601076</ID><!--学号-->
		<Phone>18874573214</Phone><!--手机号-->
		<Email>123456789@qq.com</Email>		
		<Class>软件31</Class>
	</Student>	
	<Student>
		<Name>程瑜</Name><!--姓名-->
		<ID>2131601086</ID><!--学号-->
		<Phone>18874543214</Phone><!--手机号-->
		<Email>123456789@qq.com</Email>		
		<Class>软件31</Class>
	</Student>
</LeaveStudents>