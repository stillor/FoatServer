<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%
Vector classes = (Vector)request.getAttribute("classes");
Vector absentStudents = (Vector)request.getAttribute("absentStudents");
Vector leaveStudents = (Vector)request.getAttribute("leaveStudents");
Vector lateStudents = (Vector)request.getAttribute("lateStudents");
%>
<!--手机终端初始化信息-->
<?xml version="1.0" encoding="utf-8"?>
<TeacherName><%= (String)request.getAttribute("teacherName")%></TeacherName><!--登录老师的姓名-->
<AdminPhone><%= (String)request.getAttribute("adminPhone") %></AdminPhone><!--管理员的联系方式-->
<Classes><!--所管班级列表-->
<%for(int i = 0;i<classes.size();i++){
ClassInfoBean tempCla = (ClassInfoBean)classes.elementAt(i);
%>	<Class>
		<ClassName><%=tempCla.getClassName()%></ClassName>
		<ClassID><%=tempCla.getClassId()%></ClassID>
	</Class>
<%}%>
</Classes>
<AbsentStudents><!--缺席学生列表-->
<%for(int i = 0;i<absentStudents.size();i++){
StudentBean tempStu = (StudentBean)absentStudents.elementAt(i);
%>	<Student>
		<Name><%=tempStu.getName()%></Name><!--姓名-->
		<ID><%=tempStu.getStudentId()%></ID><!--学号-->
		<Phone><%=tempStu.getPhone()%></Phone><!--手机号-->
		<Email><%=tempStu.getEmail()%></Email>		
		<Class><%=tempStu.getStudentClass()%></Class>
	</Student>
<%}%>
</AbsentStudents>
<LeaveStudents><!--请假学生列表-->
<%for(int i = 0;i<leaveStudents.size();i++){
StudentBean tempStu = (StudentBean)leaveStudents.elementAt(i);
%>	<Student>
		<Name><%=tempStu.getName()%></Name><!--姓名-->
		<ID><%=tempStu.getStudentId()%></ID><!--学号-->
		<Phone><%=tempStu.getPhone()%></Phone><!--手机号-->
		<Email><%=tempStu.getEmail()%></Email>		
		<Class><%=tempStu.getStudentClass()%></Class>
	</Student>
<%}%>
</LeaveStudents>
<LateStudents><!--迟到学生列表-->
<%for(int i = 0;i<lateStudents.size();i++){
StudentBean tempStu = (StudentBean)lateStudents.elementAt(i);
%>	<Student>
		<Name><%=tempStu.getName()%></Name><!--姓名-->
		<ID><%=tempStu.getStudentId()%></ID><!--学号-->
		<Phone><%=tempStu.getPhone()%></Phone><!--手机号-->
		<Email><%=tempStu.getEmail()%></Email>		
		<Class><%=tempStu.getStudentClass()%></Class>
	</Student>
<%}%>
</LateStudents>