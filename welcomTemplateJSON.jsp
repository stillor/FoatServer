<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%
Vector classes = (Vector)request.getAttribute("classes");
Vector absentStudents = (Vector)request.getAttribute("absentStudents");
Vector leaveStudents = (Vector)request.getAttribute("leaveStudents");
Vector lateStudents = (Vector)request.getAttribute("lateStudents");
%>
{
	"TeacherName":"<%= (String)request.getAttribute("teacherName")%>",
	"AdminPhone":"<%= (String)request.getAttribute("adminPhone") %>",
	"Classes":[
		<%for(int i = 0;i<classes.size();i++){
ClassInfoBean tempCla = (ClassInfoBean)classes.elementAt(i);
%>			{
				"ClassName":"<%=tempCla.getClassName()%>",
				"ClassID":"<%=tempCla.getClassId()%>"				
			}			
			<%if(i!=classes.size()-1){%>
			,
<%}}%>
	],
	"AbsentStudents":[
		<%for(int i = 0;i<absentStudents.size();i++){
StudentBean tempStu = (StudentBean)absentStudents.elementAt(i);
%>		
		{
			"Name":"<%=tempStu.getName()%>",
			"ID":"<%=tempStu.getStudentId()%>",
			"Phone":"<%=tempStu.getPhone()%>",
			"Email":"<%=tempStu.getEmail()%>",
			"Class":"<%=tempStu.getStudentClass()%>"			
		}
		<%if(i!=absentStudents.size()-1){%>
			,
<%}}%>
	],	
	"LeaveStudents":[
		<%for(int i = 0;i<leaveStudents.size();i++){
StudentBean tempStu = (StudentBean)leaveStudents.elementAt(i);
%>		
		{
			"Name":"<%=tempStu.getName()%>",
			"ID":"<%=tempStu.getStudentId()%>",
			"Phone":"<%=tempStu.getPhone()%>",
			"Email":"<%=tempStu.getEmail()%>",
			"Class":"<%=tempStu.getStudentClass()%>"			
		}
		<%if(i!=leaveStudents.size()-1){%>
			,
<%}}%>
	],
	"LateStudents":[
				<%for(int i = 0;i<lateStudents.size();i++){
StudentBean tempStu = (StudentBean)lateStudents.elementAt(i);
%>		
		{
			"Name":"<%=tempStu.getName()%>",
			"ID":"<%=tempStu.getStudentId()%>",
			"Phone":"<%=tempStu.getPhone()%>",
			"Email":"<%=tempStu.getEmail()%>",
			"Class":"<%=tempStu.getStudentClass()%>"			
		}
		<%if(i!=lateStudents.size()-1){%>
			,
<%}}%>
	]
	
}


