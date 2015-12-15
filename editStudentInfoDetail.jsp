

<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ page import="custom.util.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<% StudentBean validStudent = (StudentBean)request.getAttribute("validStudent");
%>	   
	   



	   
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
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
<link rel="stylesheet" type="text/css" href="edit_student_detail.css">
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

 <form id="infoForm" action="process?action=saveStudentInfo&SID=<%=validStudent.getStudentId()%>" method="post" class="form form-horizontal responsive">
		
		<div style="height:10px"></div>
	    <div style="text-align:center; margin:0 auto" class="row cl">
				<label class="form-label col-4">学生姓名：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text"  name="name" placeholder="J" value="<%=validStudent.getName()%>">
				</div>
				<div class="col-5"> </div>
		</div>
		<div class="row cl" >
		    <label class="form-label col-4">学生性别：</label>
			<div class="formControls col-4" style="text-align:center"> 
				<span class="select-box">
				<select class="select" size="1" name="sex" datatype="*" nullmsg="请选择性别！">					
					<option value="M" <%if(validStudent.getSex().equals("M")){%>selected<%}%>>男</option>
					<option value="F" <%if(validStudent.getSex().equals("F")){%>selected<%}%>>女</option>
				</select>
				</span> </div>
			<div class="col-5"> </div>
		</div>
		<div style="height:10px"></div>
	    <div style="text-align:center; margin:0 auto" class="row cl">
				<label class="form-label col-4">电话号码：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text"  name="phone"  placeholder="12356789654" value="<%=validStudent.getPhone()%>">
				</div>
				<div class="col-5"> </div>
		</div>
		<div style="height:10px"></div>
	    <div style="text-align:center; margin:0 auto" class="row cl">
				<label class="form-label col-4">电子邮箱：</label>
				<div class="formControls col-4">
					<input type="text" class="input-text"  name="email"  placeholder="12546@qq.com" value="<%=validStudent.getEmail()%>">
				</div>
				<div class="col-5"> </div>
		</div>
		<div class="row cl">
				<div class="col-10 col-offset-4">
					<input class="btn btn-primary" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
	</form>

		
				
		<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Student Basic Saved")){
				%>
		<script type="text/javascript">
			alert("修改成功");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("New Student Added")){
				%>
		<script type="text/javascript">
			alert("添加成功");
		</script>
				<%}%>
				
		
</body>