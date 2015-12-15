<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<% Vector classes = (Vector)request.getAttribute("classes");%>	 




	   
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
$(document).ready(function(){
	$("input.editButton").unbind("click").bind("click",function(){
		var CID = $.trim($(this).attr("CID"));
		window.location.href="process?action=editCourseInfoDetail&CID="+CID;
	});
	$("input.deleteButton").unbind("click").bind("click",function(){
		var CID = $.trim($(this).attr("CID"));
		window.location.href="process?action=deleteClass&CID="+CID;
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
<link rel="stylesheet" type="text/css" href="edit.css">
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
	<div style="overflow-y:auto; height:500px; width:80%;margin:0 auto">
	<table width="80%"  class="table table-border table-bordered table-striped mt-20">			
		<tbody >		
			<tr class="text-c">
				<td>课程名称</td>
				<td>课程ID</td>
				<td>任课老师</td>
				<td></td><!--按钮对齐-->
				<td></td><!--按钮对齐-->
			</tr>
			<%for(int i = 0;i < classes.size();i++){
				CourseBean tempCla = (CourseBean)classes.elementAt(i);%>
			<tr class="text-c">
				<td><%=tempCla.getName()%></td>
				<td><%=tempCla.getClassId()%></td>
				<td><%=tempCla.getTeacherName()%></td>
				<td><input id="editButton" class="btn btn-success radius" CID="<%=tempCla.getClassId()%>" type="button" value="修改" onclick="window.location='process?action=editCourseInfoDetail&CID=<%=tempCla.getClassId()%>'"></td>
				<td><input class="btn btn-danger radius" CID="<%=tempCla.getClassId()%>" type="button" value="删除" onclick="window.location='process?action=deleteClass&CID=<%=tempCla.getClassId()%>'"></td>
			</tr>
			<%}%>					
		</tbody>		
	</table>
	</div>


	<div style="height:10px"></div>
<div class="searchBar">
    <form id="searchForm" action="process?action=editClassInfoDetail" method="post">
       <input id="searchKeyword" name="CID" value="请输入课程ID" class="searchTxt" autocomplete="off" >
       <input id="searchBtn" name="searchBtn" type="submit" value="搜索" class="searchBtn" onclick="b_onclick()">
    </form>
</div>
</body>