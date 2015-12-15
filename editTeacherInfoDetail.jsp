<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ page import="custom.util.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<% TeacherBean validTeacher = (TeacherBean)request.getAttribute("validTeacher");
   Vector classes = (Vector)request.getAttribute("classes");
%>	   
	   

	   
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
$(document).ready(function(){	
	$("input.deleteClassButton").unbind("click").bind("click",function(){
		var CID = $.trim($(this).attr("CID"));
		window.location.href="process?action=deleteTeacherClass&TID=<%=validTeacher.getTeacherId()%>&CID="+CID;
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
<link rel="stylesheet" type="text/css" href="edit_teacher_detail.css">
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

	<h3 style="text-align:center">教师ID:<%=validTeacher.getTeacherId()%></h3>

	
    <form id="basicInfoForm" action="process?action=saveBasicTeacherInfo&TID=<%=validTeacher.getTeacherId()%>" method="post" class="form form-horizontal responsive">
		
		<div style="height:10px"></div>
	    <div style="text-align:center; margin:0 auto" class="row cl">
				<label class="form-label col-4">教师姓名：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text"  name="name" placeholder="John Armstrong" value="<%=validTeacher.getName()%>">
				</div>
				<div class="col-5"> </div>
		</div>
		<div style="height:10px"></div>
	    <div style="text-align:center; margin:0 auto" class="row cl">
				<label class="form-label col-4">教师电话：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text"  name="phone"  placeholder="18556322457" value="<%=validTeacher.getPhone()%>">
				</div>
				<div class="col-5"> </div>
		</div>
		<div class="row cl">
				<div class="col-10 col-offset-4">
					<input class="btn btn-primary" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
	</form>	

	
	<form id="passwordChangeForm" action="process?action=changeTeacherPassword&TID=<%=validTeacher.getTeacherId()%>" method="post" class="form form-horizontal responsive">
		
		<div style="height:10px"></div>
	    <div style="text-align:center; margin:0 auto" class="row cl">
				<label class="form-label col-4">教师新密码：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text"  name="newPassword">
				</div>
				<div class="col-5"> </div>
		</div>
		<div style="height:10px"></div>
	    <div style="text-align:center; margin:0 auto" class="row cl">
				<label class="form-label col-4">再次输入新密码：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text"  name="newPassword_again">
				</div>
				<div class="col-5"> </div>
		</div>
		<div class="row cl">
				<div class="col-10 col-offset-4">
					<input class="btn btn-primary" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
	</form>

	

<div style="overflow-y:auto; height:auto; width:80%; margin:0 auto">
		<h4 style="text-align:center">管理班级列表</h4>
	<table   class="table table-border table-bordered table-striped mt-20">			
		<tbody>		
			
			<tr class="text-c">
				<td>班级名称</td>
				<td>班级ID</td>
				<td>操作</td>
			</tr>
			
			<%for(int i = 0;i < classes.size();i++){
				ClassInfoBean tempCla = (ClassInfoBean)classes.elementAt(i);%>
			<tr class="text-c">
				<td><%=tempCla.getClassName()%></td>
				<td><%=tempCla.getClassId()%></td>
				<td><input class="btn btn-danger radius" CID="<%=tempCla.getClassId()%>" type="button" value="删除"onclick="window.location='process?action=deleteTeacherClass&TID=<%=validTeacher.getTeacherId()%>&CID=<%=tempCla.getClassId()%>'"></td>
			</tr>
			<%}%>	
			
		</tbody>		
	</table>
	</div>




	<form id="addCourseForm" action="process?action=addTeacherClass&TID=<%=validTeacher.getTeacherId()%>" method="post" class="form form-horizontal responsive">
		<h3 style="text-align:center">添加管理班级</h3>
		<div style="height:10px"></div>
	    <div style="text-align:center; margin:0 auto" class="row cl">
				<label class="form-label col-4">班级ID：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text"  name="CID">
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
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Teacher Basic Saved")){
				%>
		<script type="text/javascript">
			alert("修改成功");
		</script>
				<%}%>
							<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("NewPassword_again Not Right")){
				%>
		<script type="text/javascript">
			alert("两次输入密码不一致");
		</script>
				<%}%>
										<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Teacher Password Saved")){
				%>
		<script type="text/javascript">
			alert("修改密码成功");
		</script>
				<%}%>
												<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Class Deleted")){
				%>
		<script type="text/javascript">
			alert("删除管理班级成功");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Class Already Exist")){
				%>
		<script type="text/javascript">
			alert("课程已在管理中");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Class Added")){
				%>
		<script type="text/javascript">
			alert("课程添加成功");
		</script>
				<%}%>
								<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Class Not Exist")){
				%>
		<script type="text/javascript">
			alert("对应班级不存在");
		</script>
				<%}%>
					<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("New Teacher Added")){
				%>
		<script type="text/javascript">
			alert("添加新教师成功");
		</script>
				<%}%>
							
										
												
				
				
								
					


	
</body>