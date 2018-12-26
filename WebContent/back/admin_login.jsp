<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/messages_zh.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/back/css/matrix-login.css" />
<script src="${pageContext.request.contextPath}/back/js/md5.js" type="text/javascript"></script>
<link href="${pageContext.request.contextPath}/back/css/font-awesome.css" rel="stylesheet" />
<style type="text/css">
input {
	font-family: "Microsoft Yahei";
}

.control-label {
	color: #B2DFEE;
	padding-left: 4px;
}
</style>
<head>
<meta charset="UTF-8">
<title>用户登录</title>
<script type="text/javascript">
	$().ready(function()
	{
		$("#loginform").validate();
		$("#recoverform").validate();
	});
</script>
</head>
<body onkeydown="keydown()">
	<div id="loginbox">
		<div class="control-group normal_text">
			<h2 style="color: #B2DFEE; font-size: 28px;">健康体检团检系统平台</h2>
			<!--  <span style="font-size:14px;color:gray;">版权所有 &copy; iProg网络科技有限公司 2015-2018</span> -->
		</div>
		<form id="loginform" class="form-vertical">
			<input id="userPwd" name="userPwd" value="1" type="hidden">
			<div class="control-group">
				<label class="control-label">登陆账号</label>
				<div class="controls">
					<div class="main_input_box">
						<span class="add-on bg_lg">
							<i class="icon-user" style="font-size: 16px;"></i>
						</span>
						<input type="text" placeholder="用户名" name="userName" id="userName" required />
					</div>
				</div>
			</div>
			<div class="control-group2">
				<label class="control-label">登陆密码</label>
				<div class="controls">
					<div class="main_input_box">
						<span class="add-on bg_ly">
							<i class="icon-lock" style="font-size: 16px;"></i>
						</span>
						<input type="password" placeholder="密码" name="userPwd3" id="userPwd3" required minlength="3" maxlength="16" />
					</div>
				</div>
			</div>
			<div class="form-actions">
				<span class="pull-left">
					<a href="#" class="flip-link btn btn-info" id="to-recover">忘记密码？</a>
				</span>
				<span class="pull-right">
					<input type="button" id="checkBtn" onclick="checkLogin()" class="btn btn-success" style="width: 335px;" value=" 登&nbsp;&nbsp;&nbsp;&nbsp;录" />
				</span>
			</div>
			<div class="control-group normal_text">
				<div style="font-size: 14px; color: gray;">版权所有 &copy; 小城故事组 2018</div>
			</div>
		</form>


		<form id="recoverform" class="form-vertical" style="padding-top: 10px;">
			<label class="control-label">登陆账号</label>
			<div class="controls">
				<div class="main_input_box">
					<span class="add-on bg_lg">
						<i class="icon-user" style="font-size: 16px;"></i>
					</span>
					<input type="text" placeholder="用户名" name="userName1" id="userName1" required />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">新密码</label>
				<div class="controls">
					<div class="main_input_box">
						<span class="add-on bg_ly">
							<i class="icon-lock" style="font-size: 16px;"></i>
						</span>
						<input type="password" placeholder="新密码" name="newuserPwd" id="newuserPwd" required minlength="3" maxlength="16" />
					</div>
				</div>
			</div>
			<div class="control-group" style="padding-top: 0px;">
				<label class="control-label">确认新密码</label>
				<div class="controls">
					<div class="main_input_box">
						<span class="add-on bg_ly">
							<i class="icon-lock" style="font-size: 16px;"></i>
						</span>
						<input type="password" placeholder="新密码确认" name="newuserPwd2" id="newuserPwd2" equalTo="#newuserPwd" required minlength="3" maxlength="16" />
					</div>
				</div>
			</div>
			<div class="form-actions">
				<span class="pull-left">
					<a href="#" class="flip-link btn btn-success" id="to-login">&laquo; 返回登录</a>
				</span>
				<span class="pull-right">
					<a id="changePwd" onclick="resetPwd()" class="btn btn-info" style="width: 310px;">重置密码</a>
				</span>
			</div>
			<div class="control-group normal_text">
				<div style="font-size: 14px; color: gray;">版权所有 &copy; 小城故事组 2018</div>
			</div>
		</form>

	</div>

</body>
<script>
	function checkLogin()
	{
		debugger;
		var userPwd3 = hex_md5($('#userPwd3').val());//明文密码
		$('#userPwd').val(userPwd3);//提交的密码
		/* var userPwd=hex_md5($('#userPwd').val(userPwd3)); */
		console.log($('#userPwd').val());

		if ($("#loginform").valid())
		{
			$.ajax(
			{
				url : "${pageContext.request.contextPath}/admin/adminlogin.action",
				data : $('#loginform').serialize(),
				type : "post",
				success : function(date)
				{
					//根据后台返回前台的msg给提示信息加HTML
					if (date == 1)
					{
						alert("欢迎！！", "登录成功");
						window.location.href = "${pageContext.request.contextPath}/admin/adminMainPage.action";
					} else if (date == 2)
					{
						window.alert("该账户已被禁用");
					} else
					{
						window.alert("登录失败，请输入正确的登录信息")
					}
				}
			});
		}

	}

	/* function validatePwd(str){   
	 if(str.length!=0){    
	 reg=/^[A-Za-z]+[0-9]+[A-Za-z0-9]*|[0-9]+[A-Za-z]+[A-Za-z0-9]*$/;     
	 if(!reg.test(str)){    
	 return false;  
	 } else {
	 return true;
	 }   
	 } 
	 return false;    
	 } */

	function resetPwd()
	{
		var userName1 = $('#userName1').val();
		/* var newuserPwd = $('#newuserPwd').val(); */
		var newuserPwd2 = $('#newuserPwd2').val();

		var newuserPwd = hex_md5($('#newuserPwd').val());//加密
		/* var flag = validatePwd(newuserPwd); */

		/*  if (!flag || newuserPwd.length<6) {
		     alert("密码必须为英文字符和数字组合，且不能低于6位！");
		     return false;
		 }

		 if (newuserPwd != newuserPwd2) {
		     alert("密码和确认密码项不一致，请检查！");
		     return false;
		 } */
		if ($("#recoverform").valid())
		{
			$.ajax(
			{
				type : "post",
				url : "${pageContext.request.contextPath}/admin/updateAdminPwd.action",
				data :
				{
					userName : userName1,
					userPwd : newuserPwd
				},
				success : function(date)
				{
					//根据后台返回前台的msg给提示信息加HTML
					if (date == 1)
					{
						alert("重置成功");
						$("#recoverform").hide();
						$("#loginform").fadeIn();
					} else
					{
						window.alert("重置失败")
					}
				}
			});
		}
	}
	function keydown(e)
	{
		var e = e || event;
		var currKey = e.keyCode || e.which || e.charCode;
		if (currKey == 13)
		{
			checkLogin();
		}
	}

	$(document).ready(function()
	{

		var login = $('#loginform');
		var recover = $('#recoverform');
		var speed = 400;

		$('#to-recover').click(function()
		{
			$("#loginform").slideUp();
			$("#recoverform").fadeIn();
		});

		$('#to-login').click(function()
		{
			$("#recoverform").hide();
			$("#loginform").fadeIn();
		});
	});

	/* function showDialog(title,msg) {
	
	 $("#myModal").find(".modal-header h3").html(title);
	 $("#myModal").attr('class','modal');
	 $("#myModal").find(".modal-body p").html(msg);
	 setTimeout(function(){
	 $("#myModal").attr('class','modal hide');
	 },3000);
	 }
	 $("#modelClose").click(function(){
	 $("#myModal").attr('class','modal hide');
	 }); */
</script>
</html>
