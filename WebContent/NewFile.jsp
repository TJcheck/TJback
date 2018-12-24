<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="/webtest/back/js/jquery.min.js"></script>
<meta charset="UTF-8">
</head>
<body>
	<form name="Form2" action="/webtest/file/fileUpload.action"
		method="post" enctype="multipart/form-data">
		<h1>采用multipart提供的file.transfer方法上传文件</h1>
		<input type="file" name="file"> <input type="submit"
			value="upload" />
	</form>
	<a href="javascript:show()">显示条形码</a>
	<a href="/webtest/admin/adminMainPage.action">登录</a>
	

</body>

</html>