<%@ page import="com.ora.jsp.util.*" contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/orataglib" prefix="ora" %>



<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
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
<link rel="stylesheet" type="text/css" href="adminLogin.css">
<!--[if IE 7]>
<link href="lib/font-awesome/font-awesome-ie7.min.css" rel="stylesheet" type="text/css" />
<![endif]-->
<link href="lib/iconfont/iconfont.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
	<title>FOAT</title>
</head>

<body>
	<!--通用头-->
<div>
<nav class="mainnav cl">
  <ul class="cl">
    <li class="current"><a href="#">FOAT</a></li>
  </ul>
</nav>
<div class="r"><a class="text">登陆</a> </div>

 </div>
<div class="clear"></div>
	

	<!--登陆页面主体部分-->
	<div class="content">
		
		<div id="slider-3" class="slider1">
	   <div class="slider">
		  <div class="bd">
			 <ul>
				<li><a href="#" target="_blank"><img src="GAKI/1.png" ></a></li>
				<li><a href="#" target="_blank"><img src="GAKI/2.png" ></a></li>
				<li><a href="#" target="_blank"><img src="GAKI/3.png" ></a></li>
			 </ul>
		  </div>
		  <ol class="hd cl dots">
			<li>1</li>
			<li>2</li>
			<li>3</li>
		  </ol>
	   </div>
    </div>
    
		<div class="adminLogin">
			<h1 class="loginHead" style="text-align:center">管理员登陆</h1>
			<div class="line"></div>
		<form class="form form-horizontal responsive" action="process?action=adminAuthenticate" method="post" onsubmit="return check();" margin:0 auto>
			
			
				<% String origURL = request.getParameter("origURL"); %>
				<input type="hidden" name="origURL" 
					value="<%= origURL == null ? "" : origURL %>">
				<div class="row cl" id="name">
				    <label class="form-label col-2" id="label_name">账号：</label>
				    <div class="formControls col-5" id="name_input">
					    <input class="input-text" type="text" name="adminName" id="adminName" value="<ora:getCookieValue name="adminName"/>" />
						<label for="checkbox-5" id="name_check" style="color: blue"></label>
					</div>
					<div class="col-5"></div>
			    </div>
			    <div class="row cl" id="pass">
                    <label class="form-label col-2" id="label_pass">密码：</label>
				<div class="formControls col-5" id="pass_input">
					<input class="input-text"  type="password" name="password" id="password" value="<ora:getCookieValue name="password"/>" />
						<label for="checkbox-5" id="pass_check" style="color: red"></label>
				</div>	
					<div class="col-5"> </div>																
				</div>

				<div class="row cl" id="checkbox">

				 <div class="check-box">
					<input type="checkbox" id="checkbox-5" name="rememberName" 
						<%= CookieUtils.isCookieSet("adminName", request) ? "checked" : "" %>/>
					<label for="checkbox-5">记住账号</label>
				 </div>

				 <div class="check-box">
					<input class="checkPwd" type="checkbox" name="rememberPSW" 
						<%= CookieUtils.isCookieSet("password", request) ? "checked" : "" %>/>
					<label for="checkbox-5">记住密码</label>
				 </div>

				</div>
				<input class="btn btn-primary radius" id="submit" type="submit" value="登陆" />
			</form>			
		</div>

	</div>
	


	<div class="footer1">
	<p class="footertext">.FOAT小组制作</p>
  </div> 

	<%	
					if(request.getParameter("errorMsg") != null && request.getParameter("errorMsg").equals("Invalid Admin Name or Password")){
				%>
						<script type="text/javascript">
							alert(用户名或密码错误);
							</script>
				<%}%>
  
  <script type="text/javascript" src="Lib/jquery.min.js"></script> 
  <script type="text/javascript" src="Lib/jquery.SuperSlide.min.js"></script>
  <script type="text/javascript">
  	$(function(){
	  jQuery("#slider-3 .slider").slide({mainCell:".bd ul",titCell:".hd li",trigger:"click",effect:"leftLoop",autoPlay:true,delayTime:700,interTime:7000,pnLoop:false,titOnClassName:"active"});
    });
  </script>
	<script type="text/javascript">

	// function check(){
 //    	var name=document.getElementById("adminName").value;
 //    	var pass=document.getElementById("password").value;
 //    	if(name != "" && pass != ""){
 //    		return true;
 //    	} else if (name == "" && pass != ""){
 //            document.getElementById("p_name").innerHTML="用户名不能为空";
 //            document.getElementById("adminName").style.backgroundColor='red'
 //            return false;
 //    	}else if(pass == "" && name !=""){
 //    		document.getElementById("p_pass").innerHTML="密码不能为空";
 //    		return false;
 //    	}else{
 //    		document.getElementById("p_name").innerHTML="用户名不能为空";
 //    		document.getElementById("p_pass").innerHTML="密码不能为空";
 //    		return false;

 //    	}
 //    }
	  
	  function check(){
	  	//alert("函数调用了");
	  	var name=document.getElementById("adminName").value;
	  	var pass=document.getElementById("password").value;
	  	//alert("name="+name+",pass="+pass);
	  	var okay=false;

	  	if(name == ""){
	  		document.getElementById("name_check").innerHTML="用户名不能为空";
	  		
	  	}
	  	if(pass == ""){
	  		document.getElementById("pass_check").innerHTML="密码不能为空";
	  		

	  	}
	  	if(pass != "" && name != "" ){
	  		okay=true;
	  	}

	  	return okay;
	  }
	  </script>
	<!--通用脚注-->
	
</body>
</html>