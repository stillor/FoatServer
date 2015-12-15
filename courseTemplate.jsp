<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.StudentBean"%>
<%@ taglib uri="/orataglib" prefix="ora" %>
<jsp:useBean id="validCourse" scope="request"
       class="custom.beans.CourseBean" />
	<%
	Vector students = (Vector)request.getAttribute("students");
	%>	   	   	   
<!--手机终端接收到的课程详细情况-->
<?xml version="1.0" encoding="utf-8"?>
<Class><!--本课程的基本信息-->
	<Name><%= validCourse.getName() %></Name><!--本课程的名称-->
	<ID><%= validCourse.getClassId() %></ID><!--本课程的ID-->
	<TeacherName><%= validCourse.getTeacherName() %></TeacherName><!--本课程的授课教师姓名-->
	<StudentNumber><%= validCourse.getTotal() %></StudentNumber><!--本课程的中人数-->
	<Attend><%= validCourse.getAttend() %></Attend><!--已签到人数-->
	<Leave><%= validCourse.getLeave() %></Leave><!--请假人数-->
	<Absent><%= validCourse.getAbsent() %></Absent><!--未签到人数，即旷课人数-->
</Class>
<Students><!--具体的学生信息--><%				
		for(int i = 0;i < students.size(); i++){	
			StudentBean temp = (StudentBean) students.elementAt(i);			
				%>
	<Student>
		<Name><%= temp.getName() %></Name><!--姓名-->
		<ID><%= temp.getStudentId() %></ID><!--学号-->
		<Phone><%= temp.getPhone() %></Phone><!--手机号-->
		<Email><%= temp.getEmail() %></Email>
		<State><%= temp.getState() %></State><!--状态，0表示已签到，1表示未签到/旷课，2表示请假,3表示迟到-->
	</Student><%}%>					
</Students>