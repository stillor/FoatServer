<%@ page import="custom.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>
	<%
	Vector classes = (Vector)request.getAttribute("classes");
	%>
{
	"Week":{
		"Time":"<%= Utility.getNowWeekSunday()%>",
		"Class":"<%=(String)request.getAttribute("class")%>"
	},
	"Days":{
		"Mon":[
			<%for(int i = 0;i < classes.size(); i++){	
			CourseBean temp = (CourseBean) classes.elementAt(i);
			System.out.println("temp.getClassId:"+temp.getClassId());
			if(Utility.DayOfWeek(temp.getClassId()) == 2){			
				%>
			{
				"Name":"<%= temp.getName()%>",
				"ID":"<%= temp.getClassId()%>",
				"TeacherName":"<%= temp.getTeacherName()%>",
				"StudentNumber":"<%= temp.getTotal()%>",
				"Attend":"<%= temp.getAttend()%>",
				"Leave":"<%= temp.getLeave()%>",
				"Absent":"<%= temp.getAbsent()%>",
				"Time":"<%= temp.getTime()%>"				
			}<%if(i!=classes.size()-1){%>
			,
				<%}}}%>		
		],
		"Tues":[
			<%for(int i = 0;i < classes.size(); i++){	
			CourseBean temp = (CourseBean) classes.elementAt(i);
			System.out.println("temp.getClassId:"+temp.getClassId());
			if(Utility.DayOfWeek(temp.getClassId()) == 3){			
				%>
			{
				"Name":"<%= temp.getName()%>",
				"ID":"<%= temp.getClassId()%>",
				"TeacherName":"<%= temp.getTeacherName()%>",
				"StudentNumber":"<%= temp.getTotal()%>",
				"Attend":"<%= temp.getAttend()%>",
				"Leave":"<%= temp.getLeave()%>",
				"Absent":"<%= temp.getAbsent()%>",
				"Time":"<%= temp.getTime()%>"				
			}<%if(i!=classes.size()-1){%>
			,
				<%}}}%>		
		],		
		"Wed":[
			<%for(int i = 0;i < classes.size(); i++){	
			CourseBean temp = (CourseBean) classes.elementAt(i);
			System.out.println("temp.getClassId:"+temp.getClassId());
			if(Utility.DayOfWeek(temp.getClassId()) == 4){			
				%>
			{
				"Name":"<%= temp.getName()%>",
				"ID":"<%= temp.getClassId()%>",
				"TeacherName":"<%= temp.getTeacherName()%>",
				"StudentNumber":"<%= temp.getTotal()%>",
				"Attend":"<%= temp.getAttend()%>",
				"Leave":"<%= temp.getLeave()%>",
				"Absent":"<%= temp.getAbsent()%>",
				"Time":"<%= temp.getTime()%>"				
			}<%if(i!=classes.size()-1){%>
			,
				<%}}}%>		
		],		
		"Thurs":[
			<%for(int i = 0;i < classes.size(); i++){	
			CourseBean temp = (CourseBean) classes.elementAt(i);
			System.out.println("temp.getClassId:"+temp.getClassId());
			if(Utility.DayOfWeek(temp.getClassId()) == 5){			
				%>
			{
				"Name":"<%= temp.getName()%>",
				"ID":"<%= temp.getClassId()%>",
				"TeacherName":"<%= temp.getTeacherName()%>",
				"StudentNumber":"<%= temp.getTotal()%>",
				"Attend":"<%= temp.getAttend()%>",
				"Leave":"<%= temp.getLeave()%>",
				"Absent":"<%= temp.getAbsent()%>",
				"Time":"<%= temp.getTime()%>"				
			}<%if(i!=classes.size()-1){%>
			,
				<%}}}%>		
		],		
		"Fri":[
			<%for(int i = 0;i < classes.size(); i++){	
			CourseBean temp = (CourseBean) classes.elementAt(i);
			System.out.println("temp.getClassId:"+temp.getClassId());
			if(Utility.DayOfWeek(temp.getClassId()) == 6){			
				%>
			{
				"Name":"<%= temp.getName()%>",
				"ID":"<%= temp.getClassId()%>",
				"TeacherName":"<%= temp.getTeacherName()%>",
				"StudentNumber":"<%= temp.getTotal()%>",
				"Attend":"<%= temp.getAttend()%>",
				"Leave":"<%= temp.getLeave()%>",
				"Absent":"<%= temp.getAbsent()%>",
				"Time":"<%= temp.getTime()%>"				
			}<%if(i!=classes.size()-1){%>
			,
				<%}}}%>		
		],
		"Sat":[
			<%for(int i = 0;i < classes.size(); i++){	
			CourseBean temp = (CourseBean) classes.elementAt(i);
			System.out.println("temp.getClassId:"+temp.getClassId());
			if(Utility.DayOfWeek(temp.getClassId()) == 7){			
				%>
			{
				"Name":"<%= temp.getName()%>",
				"ID":"<%= temp.getClassId()%>",
				"TeacherName":"<%= temp.getTeacherName()%>",
				"StudentNumber":"<%= temp.getTotal()%>",
				"Attend":"<%= temp.getAttend()%>",
				"Leave":"<%= temp.getLeave()%>",
				"Absent":"<%= temp.getAbsent()%>",
				"Time":"<%= temp.getTime()%>"				
			}<%if(i!=classes.size()-1){%>
			,
				<%}}}%>		
		],
		"Sun":[
			<%for(int i = 0;i < classes.size(); i++){	
			CourseBean temp = (CourseBean) classes.elementAt(i);
			System.out.println("temp.getClassId:"+temp.getClassId());
			if(Utility.DayOfWeek(temp.getClassId()) == 1){			
				%>
			{
				"Name":"<%= temp.getName()%>",
				"ID":"<%= temp.getClassId()%>",
				"TeacherName":"<%= temp.getTeacherName()%>",
				"StudentNumber":"<%= temp.getTotal()%>",
				"Attend":"<%= temp.getAttend()%>",
				"Leave":"<%= temp.getLeave()%>",
				"Absent":"<%= temp.getAbsent()%>",
				"Time":"<%= temp.getTime()%>"				
			}<%if(i!=classes.size()-1){%>
			,
				<%}}}%>		
		]
	}
}