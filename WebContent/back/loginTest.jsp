<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="button" onclick="adminMain();" value="测试动态菜单用">
</body>
<script>
	function adminMain()
	{
		window.location.href = "${pageContext.request.contextPath}/admin/adminMainPage.action";
	}
</script>
</html>