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
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
		</thead>
		<tbody id="myTbody">
			<c:choose>
				<c:when test="${not empty requestScope.proResult}">
					<tr>
						<td>体检号</td><td>${requestScope.proResult.examineId}</td>
					</tr>
					<tr>
						<td>项目</td><td>${requestScope.proResult.projectName}</td>
					</tr>
					<tr>
						<td>体检号</td><td>${requestScope.proResult.examineId}</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="button" class="btn btn-primary"
								onclick="updateResult(${project.projectId},${requestScope.examineId})">体检小结</button>
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
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
		</thead>
		<tbody id="myTbody2">
			<c:choose>
				<c:when test="${not empty requestScope.proResult}">
					<tr>
						<td>体检号</td><td>${requestScope.proResult.examineId}</td>
					</tr>
					<tr>
						<td>项目</td><td>${requestScope.proResult.projectName}</td>
					</tr>
					<tr>
						<td>体检号</td><td>${requestScope.proResult.examineId}</td>
					</tr>
					<tr>
						<td colspan="2">
							<button type="button" class="btn btn-primary"
								onclick="updateResult(${project.projectId},${requestScope.examineId})">体检小结</button>
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
	//更改
	function updateResult(projectId,examineId){
		
	}
</script>
</html>