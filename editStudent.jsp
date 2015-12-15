<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="custom.beans.*"%>
<%@ taglib uri="/orataglib" prefix="ora" %>

<% Vector students = (Vector)request.getAttribute("students");%>	




	   
	   
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>FOAT课程管理</title>
	<link rel="stylesheet" type="text/css" href="style/calendar.css">
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript">
$(document).ready(function(){
	$("input.editButton").unbind("click").bind("click",function(){
		var SID = $.trim($(this).attr("SID"));
		window.location.href="process?action=editStudentInfoDetail&SID="+SID;
	});
	$("input.deleteButton").unbind("click").bind("click",function(){
		var SID = $.trim($(this).attr("SID"));
		window.location.href="process?action=deleteStudent&SID="+SID;
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
<link rel="stylesheet" type="text/css" href="editStudent.css">
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
	<h2 style="text-align:center">所有学生列表</h2>
	<div style="overflow-y:auto; height:auto; width:80%;margin:0 auto">
	<table class="table table-border table-bordered table-striped mt-20">			
		<tbody>		
			<tr class="text-c">
				<td>学号</td>
				<td>姓名</td>
				<td>联系方式</td>
				<td>操作</td>
				<td>操作</td>
			</tr>
			
			<%for(int i = 0;i < students.size();i++){
				StudentBean tempStu = (StudentBean)students.elementAt(i);%>
			<tr class="text-c">
				<td><%=tempStu.getStudentId()%></td>
				<td><%=tempStu.getName()%></td>
				<td><%=tempStu.getPhone()%></td><!-- btn btn-secondary radius disabled-->
												<!-- btn btn-danger radius disabled-->
				<td><input id="editButton" class=" btn btn-success radius" SID="<%=tempStu.getStudentId()%>" type="button" value="修改"  onclick="window.location='process?action=editStudentInfoDetail&SID=<%=tempStu.getStudentId()%>'"></td>
				<td><input class="btn btn-danger radius " SID="<%=tempStu.getStudentId()%>" type="button" value="删除"  onclick="window.location='process?action=deleteStudent&SID=<%=tempStu.getStudentId()%>'"></td>
			</tr>
			<%}%>	
			
		</tbody>		
	</table>
	</div>
	<div style="height:10px"></div>

	<div class="searchBar">
    <form id="searchForm" action="process?action=editStudentInfoDetail" method="post">
       <input id="searchKeyword" name="SID" value="请输入搜索条件" class="searchTxt" autocomplete="off" >
       <input id="searchBtn" name="searchBtn" type="submit" value="搜索" class="searchBtn" onclick="b_onclick()">
    </form>
</div>
</body>