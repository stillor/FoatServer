
<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ page import="custom.util.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<% ClassInfoBean validClass = (ClassInfoBean)request.getAttribute("validClass");
   Vector courses = (Vector)request.getAttribute("courses");
   Vector students = (Vector)request.getAttribute("students");
%>	   
	   




	   
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
$(document).ready(function(){	
	$("input.deleteCourseButton").unbind("click").bind("click",function(){
		var course = $.trim($(this).attr("course"));
		window.location.href="process?action=deleteClassCourse&CID=<%=validClass.getClassId()%>&course="+course;
	});	
	$("input.deleteStudentButton").unbind("click").bind("click",function(){
		var SID = $.trim($(this).attr("SID"));
		window.location.href="process?action=deleteClassStudent&CID=<%=validClass.getClassId()%>&SID="+SID;
	});
});
</script>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<script type="text/javascript" src="lib/PIE_IE678.js"></script>
<![endif]-->
<link href="static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="static/h-ui/css/style.css" rel="stylesheet" type="text/css" />
<link href="lib/icheck/icheck.css" rel="stylesheet" type="text/css" />
<link href="lib/bootstrapSwitch/bootstrapSwitch.css" rel="stylesheet" type="text/css" />
<link href="lib/font-awesome/font-awesome.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="edit_class_detail.css">
<!--[if IE 7]>
<link href="lib/font-awesome/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<link href="lib/iconfont/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
</head>

<body>	

	<div>
<nav class="mainnav cl">
  <ul class="cl">
     <li class="current"><a href="process?action=showPage&page=main_menu.html">FOAT</a></li>
    <li><a href="process?action=editCourse">课程修改</a></li>
    <li><a href="process?action=editClass">班级修改</a></li>
    <li><a href="process?action=editTeacher">老师修改</a></li>
    <li><a href="process?action=editStudent">学生修改</a></li>
  </ul>
</nav>
<div class="r"><a class="text" href="process?action=editBasic">欢迎您，管理员 |</a><a class="text" href="process?action=logout">注销</a> </div>

 </div>
<div class="clear"></div>

<h3 style="text-align:center">班级名称:<%=validClass.getClassName()%></h3>

<div style="overflow-y:auto; height:auto; width:80%; margin:0 auto">
		<h4 style="text-align:center">已有课程</h4>
	<table   class="table table-border table-bordered table-striped mt-20">			
		<tbody>		
			
			<tr class="text-c">
				<td>课程ID</td>
				<td>课程名称</td>
				<td>授课老师</td>
				<td>操作</td>
			</tr>
			<%for(int i = 0;i < courses.size();i++){
				CourseBean tempCour = (CourseBean)courses.elementAt(i);%>
			<tr class="text-c">
				<td><%=tempCour.getClassId()%></td>
				<td><%=tempCour.getName()%></td>
				<td><%=tempCour.getTeacherName()%></td>
				<td><input class="deleteCourseButton" course="<%=tempCour.getClassId()%>" type="button" value="删除" onclick="window.location='process?action=deleteClassCourse&CID=<%=validClass.getClassId()%>&course=<%=tempCour.getClassId()%>'"></td>
			</tr>
			<%}%>		
			
	
		</tbody>		
	</table>
	</div>


<form id="addCourseForm" action="process?action=addClassCourse&CID=<%=validClass.getClassId()%>" method="post" class="form form-horizontal responsive">
		<h3 style="text-align:center">添加所有课程</h3>
		<div style="height:10px"></div>
	    <div style="text-align:center; margin:0 auto" class="row cl">
				<label class="form-label col-4">课程ID：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text"  name="CourseId">
				</div>
				<div class="col-5"> </div>
		</div>
		
		<div class="row cl">
				<div class="col-10 col-offset-4">
					<input class="btn btn-primary" type="submit" value="&nbsp;&nbsp;添加&nbsp;&nbsp;">
				</div>
			</div>
	</form>


<div style="overflow-y:auto; height:auto; width:80%; margin:0 auto">
		<h4 style="text-align:center">班级学生</h4>
	<table   class="table table-border table-bordered table-striped mt-20">			
		<tbody>		
			
			<tr class="text-c">
				<td>学号</td>
				<td>姓名</td>
				<td>操作</td>
			</tr>
			<%for(int i = 0;i < students.size();i++){
				StudentBean tempStu = (StudentBean)students.elementAt(i);%>
			<tr class="text-c">
				<td><%=tempStu.getStudentId()%></td>				
				<td><%=tempStu.getName()%></td>				
				<td><input class="deleteStudentButton" SID="<%=tempStu.getStudentId()%>" type="button" value="删除" onclick="window.location='process?action=deleteClassStudent&CID=<%=validClass.getClassId()%>&SID=<%=tempStu.getStudentId()%>'"></td>
			</tr>
			<%}%>				
		
		</tbody>		
	</table>
	</div>


	<form id="addStudentForm" action="process?action=addClassStudent&CID=<%=validClass.getClassId()%>" method="post" class="form form-horizontal responsive">
		<h3 style="text-align:center">添加学生</h3>
		<div style="height:10px"></div>
	    <div style="text-align:center; margin:0 auto" class="row cl">
				<label class="form-label col-4">学号：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text"  name="SID">
				</div>
				<div class="col-5"> </div>
		</div>
		
		<div class="row cl">
				<div class="col-10 col-offset-4">
					<input class="btn btn-primary" type="submit" value="&nbsp;&nbsp;添加&nbsp;&nbsp;">
				</div>
			</div>
	</form>			
							
					
				
					<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("New Class Added")){
				%>
		<script type="text/javascript">
			alert("新班级添加成功");
		</script>
				<%}%>	
						<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Course Not Exist")){
				%>
		<script type="text/javascript">
			alert("所添加的课程并不存在");
		</script>
				<%}%>	
					<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Course Already Exist")){
				%>
		<script type="text/javascript">
			alert("所添加的课程已经存在");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Course Added")){
				%>
		<script type="text/javascript">
			alert("添加成功");
		</script>
				<%}%>
									<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Student Not Exist")){
				%>
		<script type="text/javascript">
			alert("所添加的学生并不存在");
		</script>
				<%}%>	
					<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Student Already Exist")){
				%>
		<script type="text/javascript">
			alert("所添加的学生已经存在");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Student Added")){
				%>
		<script type="text/javascript">
			alert("添加成功");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Course Deleted")){
				%>
		<script type="text/javascript">
			alert("删除成功");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Student Deleted")){
				%>
		<script type="text/javascript">
			alert("删除成功");
		</script>
				<%}%>
								
					
				
				
				
				
				
</body>