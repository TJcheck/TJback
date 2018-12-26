<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back/css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/back/css/style.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/back/js/jquery.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/back/js/jquery.sorted.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/back/js/bootstrap.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/back/js/ckform.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/back/js/common.js"></script>
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}
</style>
</head>
<body>
	<form name="Form2"
		action="${pageContext.request.contextPath}/doctor/readBar.action"
		method="post" enctype="multipart/form-data">
		<input type="file" name="file"> <input type="submit"
			value="识别条形码" />
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>公司</th>
				<th>套餐</th>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>手机号码</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="myTbody">
			<c:choose>
				<c:when test="${not empty requestScope.examine}">
					<tr>
						<td>${requestScope.examine.companyName}</td>
						<td>${requestScope.examine.comboName}</td>
						<td>${requestScope.examine.exName}</td>
						<td>${requestScope.examine.exSex}</td>
						<td>${requestScope.examine.exAge}</td>
						<td>${requestScope.examine.exPhone}</td>
						<td>
							<button type="button" class="btn btn-primary"
								onclick="showProject(${requestScope.examine.examineId},${requestScope.examine.comboId})">显示体检项目</button>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4" align="center">暂无数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</body>
<script>
	//显示检查项目，传入体检ID和套餐ID
	function showProject(examineId,comboId){
		window.location.href="${pageContext.request.contextPath}/doctor/showProject.action?examineId="+examineId+"&comboId="+comboId;
	}
</script>
</html>