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
			<tr>
				<th>序号</th>
				<th>项目名称</th>
				<th>科室</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="myTbody">
			<c:choose>
				<c:when test="${not empty requestScope.projectList}">
					<c:forEach items="${requestScope.projectList}" var="project"
						varStatus="st">
						<tr>
							<td>${st.count}</td>
							<td>${project.projectName}</td>
							<td>${project.deptName}</td>
							<td>
								<button type="button" class="btn btn-primary"
									onclick="updateResult(${project.projectId},${requestScope.examineId},${project.projectType})">体检小结</button>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4" align="center">暂无数据</td>
					</tr>
				</c:otherwise>

			</c:choose>
			<tr>
				<td>
					<button type="button" class="btn btn-primary" onclick="back()">返回</button>
				</td>
			</tr>
		</tbody>
	</table>
</body>
<script>
	//更改小结
	function updateResult(projectId,examineId,projectType){
		window.location.href="${pageContext.request.contextPath}/doctor/showProResult.action?projectId="+projectId+"&examineId="+examineId+"&comboId="+${requestScope.comboId}+"&projectType="+projectType;
	}
	function back(){
		window.location.href="${pageContext.request.contextPath}/doctor/backInfo.action?examineId=${requestScope.examineId}";
	}
</script>
</html>