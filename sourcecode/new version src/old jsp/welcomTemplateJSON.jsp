<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%
Vector classes = (Vector)request.getAttribute("classes");
%>
{
	"TeacherName":"<%= (String)request.getAttribute("teacherName")%>",
	"AdminPhone":"<%= (String)request.getAttribute("adminPhone") %>",
	"Classes":[
		<%for(int i = 0;i<classes.size();i++){
ClassInfoBean tempCla = (ClassInfoBean)classes.elementAt(i);
%>
			"<%=tempCla.getClassName()%>"
			<%if(i!=classes.size()-1){%>
			,
<%}}%>
	],
	"AbsentStudents":[
		{
			"Name":"任城",
			"ID":"2131601066",
			"Phone":"18874563214",
			"Email":"123456789@qq.com",	
			"Class":"软件31"
		},
		{
			"Name":"冉天",
			"ID":"2131601026",
			"Phone":"18874563217",
			"Email":"123455789@qq.com",
			"Class":"软件31"			
		}
	],	
	"LeaveStudents":[
		{
			"Name":"黄骅",
			"ID":"2131601076",
			"Phone":"18874573214",
			"Email":"123456789@qq.com",
			"Class":"软件31"
		},
		{
			"Name":"程瑜",
			"ID":"2131601086",
			"Phone":"18874543214",
			"Email":"123455789@qq.com",
			"Class":"软件31"
		}
	]
}


