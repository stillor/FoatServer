
<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>



<% Vector teachers = (Vector)request.getAttribute("teachers");%>	

	   
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
$(document).ready(function(){
	$("input.editButton").unbind("click").bind("click",function(){
		var TID = $.trim($(this).attr("TID"));
		window.location.href="process?action=editTeacherInfoDetail&TID="+TID;
	});
	$("input.deleteButton").unbind("click").bind("click",function(){
		var TID = $.trim($(this).attr("TID"));
		window.location.href="process?action=deleteTeacher&TID="+TID;
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
<link rel="stylesheet" type="text/css" href="editTeacher.css">
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
	<h2 style="text-align:center">已有教师列表</h2>
	<div style="overflow-y:auto; height:auto; width:80% ;margin:0 auto ">
	<table class="table table-border table-bordered table-striped mt-20">			
		<tbody>		
			<tr class="text-c">
				<td>教师ID</td>				
				<td>姓名</td>
				<td>电话号码</td>
				<td>操作</td>
				<td>操作</td>
			<%for(int i = 0;i < teachers.size();i++){
				TeacherBean tempTea = (TeacherBean)teachers.elementAt(i);%>
			<tr class="text-c">
				<td><%=tempTea.getTeacherId()%></td>
				<td><%=tempTea.getName()%></td>
				<td><%=tempTea.getPhone()%></td>
				<td><input id="editButton" class="btn btn-success radius" TID="<%=tempTea.getTeacherId()%>" type="button" value="修改" onclick="window.location='process?action=editTeacherInfoDetail&TID=<%=tempTea.getTeacherId()%>'"></td>
				<td><input class="btn btn-danger radius" TID="<%=tempTea.getTeacherId()%>" type="button" value="删除"  onclick="window.location='process?action=deleteTeacher&TID=<%=tempTea.getTeacherId()%>'"></td>
			</tr>
			<%}%>	
					
		</tbody>		
	</table>
	</div>

	<div style="height:10px"></div>
<div class="searchBar">
    <form id="searchForm" action="process?action=editTeacherInfoDetail" method="post">
       <input id="searchKeyword" name="TID" value="请输入搜索条件" class="searchTxt" autocomplete="off" >
       <input id="searchBtn" name="searchBtn" type="submit" value="搜索" class="searchBtn" onclick="b_onclick()">
    </form>
</div>

<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Delete Teacher Succeeded")){
				%>
		<script type="text/javascript">
			alert("删除教师成功");
		</script>
				<%}%>
				<%	
					if(request.getParameter("msg") != null && request.getParameter("msg").equals("Teacher Not Found")){
				%>
		<script type="text/javascript">
			alert("未找到对应的教师");
		</script>
				<%}%>

</body>