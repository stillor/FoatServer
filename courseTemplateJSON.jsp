<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.StudentBean"%>
<%@ taglib uri="/orataglib" prefix="ora" %>
<jsp:useBean id="validCourse" scope="request"
       class="custom.beans.CourseBean" />
	<%
	Vector students = (Vector)request.getAttribute("students");
	%>	   	
{
	"Class":{
		"Name":"<%= validCourse.getName() %>",
		"ID":"<%= validCourse.getClassId() %>",
		"TeacherName":"<%= validCourse.getTeacherName() %>",
		"StudentNumber":"<%= validCourse.getTotal() %>",
		"Attend":"<%= validCourse.getAttend() %>",
		"Leave":"<%= validCourse.getLeave() %>",
		"Absent":"<%= validCourse.getAbsent() %>"
	},
	"Students":[<%				
		for(int i = 0;i < students.size(); i++){	
			StudentBean temp = (StudentBean) students.elementAt(i);			
				%>
		{
			"Name":"<%= temp.getName() %>",
			"ID":"<%= temp.getStudentId() %>",
			"Phone":"<%= temp.getPhone() %>",
			"Email":"<%= temp.getEmail() %>",
			"State":"<%= temp.getState() %>"
		}					
	<%if(i!=students.size()-1){%>
			,
		<%}}%>	
	]
}
