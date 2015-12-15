<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT服务端</title>
</head>
<body>
			<h1>FOAT服务端ver0.8，目前只实现以下指令</h1>
			<h1>XML格式response对应</h1>
			<h1>指令一(登录)：process?action=teacherAuthenticate&teacherName=John&password=123456</h1>			
			<h1>指令二(查看某课程的具体到课情况)：process?action=showCourse&CID=code10012015113009</h1>
			<h1>指令三（查看某班级当周课表）：process?action=showSchedule&CID=软件31</h1>
			<h1>JSON格式response对应</h1>
			<h1>指令一(登录)：process?action=teacherAuthenticateJSON&teacherName=John&password=123456</h1>			
			<h1>指令二(查看某课程的具体到课情况)：process?action=showCourseJSON&CID=code10012015113009</h1>
			<h1>指令三（查看某班级当周课表）：process?action=showScheduleJSON&CID=软件31</h1>
</body>
</html>