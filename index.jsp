<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT服务端</title>
</head>
<body>
			<h1>FOAT服务端ver0.85，目前只实现以下指令</h1>
			<h1>客户端</h1>
			<h2>XML格式response对应</h2>
			<h2>指令一(登录)：process?action=teacherAuthenticate&teacherName=John&password=123456</h2>			
			<h2>指令二(查看某课程的具体到课情况)：process?action=showCourse&CID=code10022015120903</h2>
			<h2>指令三（查看某班级当周课表）：process?action=showSchedule&CID=se31</h2>
			<h2>JSON格式response对应</h2>
			<h2>指令一(登录)：process?action=teacherAuthenticateJSON&teacherName=John&password=123456</h2>			
			<h2>指令二(查看某课程的具体到课情况)：process?action=showCourseJSON&CID=code10022015120903</h2>
			<h2>指令三（查看某班级当周课表）：process?action=showScheduleJSON&CID=se31</h2>
			<h2>无response的指令</h2>
			<h2>指令一(修改教师密码，需处于登录状态下)：process?action=teacherChangePassword&newPassword=654321</h2>	
			<h2>指令二(教师登出)：process?action=logout</h2>	
			
			<h1>管理员端</h1>
			<h2>指令一(登录)：adminLogin.jsp</h2>			
			<h2>指令二(登出)：process?action=logout</h2>
			<h2>指令三（添加新的班级/教师/学生/课程，目前测试情况下部分不需要登录即可访问）：process?action=addNewClass/Teacher/Student/Course</h2>
			<h2>指令四（修改班级/教师/学生/课程，目前测试情况下部分不需要登录即可访问）：process?action=editClass/Teacher/Student/Course</h2>
			<h2>指令五（修改基本信息）：process?action=editBasic</h2>
</body>
</html>