<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ page import="custom.util.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<% CourseBean validClass = (CourseBean)request.getAttribute("validCourse");
Vector students = (Vector)request.getAttribute("students");
Vector times = Utility.arrayToVector(validClass.getTime().split(","));
if(validClass.getTime().equals(""))times=new Vector();
%>	     
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
$(document).ready(function(){	
	$("input.deleteTimeButton").unbind("click").bind("click",function(){
		var time = $.trim($(this).attr("time"));
		window.location.href="process?action=deleteCourseTime&CID=<%=validClass.getClassId()%>&time="+time;
	});	
	$("input.deleteStudentButton").unbind("click").bind("click",function(){
		var SID = $.trim($(this).attr("SID"));
		window.location.href="process?action=deleteCourseStudent&CID=<%=validClass.getClassId()%>&SID="+SID;
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
    <li class="current"><a href="#">FOAT</a></li>
    <li><a href="process?action=editCourse">课程修改</a></li>
    <li><a href="process?action=editClass">班级修改</a></li>
    <li><a href="process?action=editTeacher">老师修改</a></li>
    <li><a href="process?action=editStudent">学生修改</a></li>
  </ul>
</nav>
<div class="r"><a class="text" href="process?action=editBasic">欢迎您，管理员 |</a><a class="text" href="process?action=logout">注销</a> </div>

 </div>
<div class="clear"></div>
	<h2 style="text-align:center">课程ID:<%=validClass.getClassId()%></h2>
	<h2 style="text-align:center">课程名称:<%=validClass.getName()%></h2>
		<div class="line"></div>
	<div style="text-align:center">
	<form id="basicInfoForm" action="process?action=saveBasicCourseInfo&CID=<%=validClass.getClassId()%>" method="post" class="form form-horizontal responsive"><!--基本信息-->	
		<div style="text-align:center; margin:0 auto" class="row cl">
				<label class="form-label col-4">授课老师姓名：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text"  name="teacher_name" placeholder="John.xjtu" value="<%=validClass.getTeacherName()%>">
				</div>				
					<input class="btn btn-primary" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				<div class="col-5"> </div>
		</div>
	</form>
    </div>

	<div style="overflow-y:auto; height:200px; width:80%; margin:0 auto">
		<h4 style="text-align:center">已有课程授课时间</h4>
	<table   class="table table-border table-bordered table-striped mt-20">			
		<tbody>		
			
			<tr class="text-c">
				<td>日期</td>
				<td>开始课时</td>
				<td>结束课时</td>
				<td>操作</td>
			</tr>
			<%for(int i = 0;i<times.size();i++){
				String tempStr = (String)times.elementAt(i);%>
			<tr class="text-c">
				<td><%=Utility.getDayOfWeek(tempStr.substring(0,1))%></td>
				
				<%if(tempStr.substring(1,2).equals("A")){%>
					<td>10</td>
				<%}else{%>
				<%if(tempStr.substring(1,2).equals("B")){%>
					<td>11</td>
				<%}else{%>
					<td><%=tempStr.substring(1,2)%></td>	
				<%}}%>		
				
				<%if(tempStr.substring(2,3).equals("A")){%>
					<td>10</td>
				<%}else{%>
				<%if(tempStr.substring(2,3).equals("B")){%>
					<td>11</td>
				<%}else{%>
					<td><%=tempStr.substring(2,3)%></td>
				<%}}%>
				<td><input class="btn btn-danger radius" time="<%=tempStr%>" type="button" value="删除" onclick="window.location='process?action=deleteCourseTime&CID=<%=validClass.getClassId()%>&time=<%=tempStr%>'"></td>
			</tr>
			<%}%>						
		</tbody>		
	</table>
	</div>
	
	<form id="addTimeForm" action="process?action=addCourseTime&CID=<%=validClass.getClassId()%>" method="post" class="form form-horizontal responsive">
		<h2 style="text-align:center">添加课程时间</h2>
		<div class="line"></div>
		<div class="row cl" >
		    <label class="form-label col-4">日期：</label>
			<div class="formControls col-4" style="text-align:center"> 
				<span class="select-box">
				<select class="select" size="1" name="time1" datatype="*" nullmsg="请选择日期！">
					<option value="" selected>请选择日期</option>
					<option value="1">星期日</option>
					<option value="2">星期一</option>
					<option value="3">星期二</option>
					<option value="4">星期三</option>
					<option value="5">星期四</option>
					<option value="6">星期五</option>
					<option value="7">星期六</option>
				</select>
				</span> </div>
			<div class="col-5"> </div>
		</div>
		<div style="height:10px"></div>
	    <div style="text-align:center; margin:0 auto" class="row cl">
				<label class="form-label col-4">开始课时（1-11）：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text"  name="time2">
				</div>
				<div class="col-5"> </div>
		</div>
		<div style="height:10px"></div>
		<div style="text-align:center; margin:0 auto" class="row cl">
				<label class="form-label col-4">结束课时（1-11）：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text"  name="time3">
				</div>
				<div class="col-5"> </div>
		</div>
		<div class="row cl">
				<div class="col-10 col-offset-4">
					<input class="btn btn-primary" type="submit" value="&nbsp;&nbsp;添加&nbsp;&nbsp;">
				</div>
			</div>
	</form>
	

	<div style="overflow-y:auto; height:600px; width:80%; margin:0 auto" >
		<h4 style="text-align:center">课程学生</h4>
	<table width="80%"  class="table table-border table-bordered table-striped mt-20" >			
		<tbody>		
			<tr class="text-c">
				<th>学号</th>
				<td>姓名</td>
				<th>操作</th><!--按钮对齐-->
			</tr>
			<%for(int i = 0;i< students.size();i++){
				StudentBean tempStu = (StudentBean)students.elementAt(i);%>
			<tr class="text-c">
				<td><%=tempStu.getStudentId()%></td>				
				<td><%=tempStu.getName()%></td>				
				<td><input class="btn btn-danger radius" SID="<%=tempStu.getStudentId()%>" type="button" value="删除" onclick="window.location='process?action=deleteCourseStudent&CID=<%=validClass.getClassId()%>&SID=<%=tempStu.getStudentId()%>'"></td>
			</tr>
			<%}%>					
		</tbody>		
	</table>
	</div>
	
	<form id="addTimeForm" action="process?action=addCourseStudent&CID=<%=validClass.getClassId()%>" method="post" class="form form-horizontal responsive">
		<h2 style="text-align:center">添加学生</h2>
		<div class="line"></div>
	    <div style="text-align:center; margin:0 auto" class="row cl">
				<label class="form-label col-4">学号：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text"  name="SID" >
				</div>
					<input class="btn btn-primary" type="submit" value="&nbsp;&nbsp;添加&nbsp;&nbsp;">
				<div class="col-5"> </div>
		</div>
	</form>

<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("New Course Added")){
				%>
		<script type="text/javascript">
			alert("添加成功");
		</script>
				<%}%>	
		<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Basic Changed")){
				%>
		<script type="text/javascript">
			alert("修改成功");
		</script>
				<%}%>
			<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Time Deleted")){
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
			<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Input Time Completedly")){
				%>
		<script type="text/javascript">
			alert("请将时间输入完全");
		</script>
				<%}%>
						<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Time Already Exist")){
				%>
		<script type="text/javascript">
			alert("所添加的时间已经存在");
		</script>
				<%}%>
									<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Student Not Exist")){
				%>
		<script type="text/javascript">
			alert("所添加的学生不存在");
		</script>
				<%}%>
								<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("New Time Added")){
				%>
		<script type="text/javascript">
			alert("添加时间成功");
		</script>
				<%}%>
	
</body>