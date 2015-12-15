<%@ page import="custom.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>


	<%
	Vector classes = (Vector)request.getAttribute("classes");
	%>	   
	   
	   
<!--手机终端接收到的关于一周授课概况-->
<?xml version="1.0" encoding="utf-8"?>
<Week>
	<Time><%= Utility.getNowWeekSunday()%></Time><!--为一周的开始时间，即当周的周日时间--><!-- -->
	<Class><%=(String)request.getAttribute("class")%></Class>
</Week>
<Days>
	<Mon><%for(int i = 0;i < classes.size(); i++){	
			CourseBean temp = (CourseBean) classes.elementAt(i);
			System.out.println("temp.getClassId:"+temp.getClassId());
			if(Utility.DayOfWeek(temp.getClassId()) == 2){			
				%>
		<Class>
			<Name><%= temp.getName()%></Name>
			<ID><%= temp.getClassId()%></ID>
			<TeacherName><%= temp.getName()%></TeacherName>
			<StudentNumber><%= temp.getTotal()%></StudentNumber>
			<Attend><%= temp.getAttend()%></Attend>
			<Leave><%= temp.getLeave()%></Leave>
			<Absent><%= temp.getAbsent()%></Absent>
			<Time><%= temp.getTime()%></Time><!--课程的节次时间，格式为“开始节次_结束节次”-->
		</Class><%}}%>		
	</Mon>		
	<Tues><%for(int i = 0;i < classes.size(); i++){	
			CourseBean temp = (CourseBean) classes.elementAt(i);	
			if(Utility.DayOfWeek(temp.getClassId()) == 3){			
				%>
		<Class>
			<Name><%= temp.getName()%></Name>
			<ID><%= temp.getClassId()%></ID>
			<TeacherName><%= temp.getName()%></TeacherName>
			<StudentNumber><%= temp.getTotal()%></StudentNumber>
			<Attend><%= temp.getAttend()%></Attend>
			<Leave><%= temp.getLeave()%></Leave>
			<Absent><%= temp.getAbsent()%></Absent>
			<Time><%= temp.getTime()%></Time><!--课程的节次时间，格式为“开始节次_结束节次”-->
		</Class><%}}%>		
	</Tues>			
	<Wed><%for(int i = 0;i < classes.size(); i++){	
			CourseBean temp = (CourseBean) classes.elementAt(i);	
			if(Utility.DayOfWeek(temp.getClassId()) == 4){			
				%>
		<Class>
			<Name><%= temp.getName()%></Name>
			<ID><%= temp.getClassId()%></ID>
			<TeacherName><%= temp.getName()%></TeacherName>
			<StudentNumber><%= temp.getTotal()%></StudentNumber>
			<Attend><%= temp.getAttend()%></Attend>
			<Leave><%= temp.getLeave()%></Leave>
			<Absent><%= temp.getAbsent()%></Absent>
			<Time><%= temp.getTime()%></Time><!--课程的节次时间，格式为“开始节次_结束节次”-->
		</Class><%}}%>		
	</Wed>			
	<Thurs><%for(int i = 0;i < classes.size(); i++){	
			CourseBean temp = (CourseBean) classes.elementAt(i);	
			if(Utility.DayOfWeek(temp.getClassId()) == 5){			
				%>
		<Class>
			<Name><%= temp.getName()%></Name>
			<ID><%= temp.getClassId()%></ID>
			<TeacherName><%= temp.getName()%></TeacherName>
			<StudentNumber><%= temp.getTotal()%></StudentNumber>
			<Attend><%= temp.getAttend()%></Attend>
			<Leave><%= temp.getLeave()%></Leave>
			<Absent><%= temp.getAbsent()%></Absent>
			<Time><%= temp.getTime()%></Time><!--课程的节次时间，格式为“开始节次_结束节次”-->
		</Class><%}}%>		
	</Thurs>			
	<Fri><%for(int i = 0;i < classes.size(); i++){	
			CourseBean temp = (CourseBean) classes.elementAt(i);	
			if(Utility.DayOfWeek(temp.getClassId()) == 6){			
				%>
		<Class>
			<Name><%= temp.getName()%></Name>
			<ID><%= temp.getClassId()%></ID>
			<TeacherName><%= temp.getName()%></TeacherName>
			<StudentNumber><%= temp.getTotal()%></StudentNumber>
			<Attend><%= temp.getAttend()%></Attend>
			<Leave><%= temp.getLeave()%></Leave>
			<Absent><%= temp.getAbsent()%></Absent>
			<Time><%= temp.getTime()%></Time><!--课程的节次时间，格式为“开始节次_结束节次”-->
		</Class><%}}%>		
	</Fri>			
	<Sat><%for(int i = 0;i < classes.size(); i++){	
			CourseBean temp = (CourseBean) classes.elementAt(i);	
			if(Utility.DayOfWeek(temp.getClassId()) == 7){			
				%>
		<Class>
			<Name><%= temp.getName()%></Name>
			<ID><%= temp.getClassId()%></ID>
			<TeacherName><%= temp.getName()%></TeacherName>
			<StudentNumber><%= temp.getTotal()%></StudentNumber>
			<Attend><%= temp.getAttend()%></Attend>
			<Leave><%= temp.getLeave()%></Leave>
			<Absent><%= temp.getAbsent()%></Absent>
			<Time><%= temp.getTime()%></Time><!--课程的节次时间，格式为“开始节次_结束节次”-->
		</Class><%}}%>		
	</Sat>			
	<Sun><%for(int i = 0;i < classes.size(); i++){	
			CourseBean temp = (CourseBean) classes.elementAt(i);	
			if(Utility.DayOfWeek(temp.getClassId()) == 1){			
				%>
		<Class>
			<Name><%= temp.getName()%></Name>
			<ID><%= temp.getClassId()%></ID>
			<TeacherName><%= temp.getName()%></TeacherName>
			<StudentNumber><%= temp.getTotal()%></StudentNumber>
			<Attend><%= temp.getAttend()%></Attend>
			<Leave><%= temp.getLeave()%></Leave>
			<Absent><%= temp.getAbsent()%></Absent>
			<Time><%= temp.getTime()%></Time><!--课程的节次时间，格式为“开始节次_结束节次”-->
		</Class><%}}%>		
	</Sun>	
</Days>

