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
		<c:if
			test="${requestScope.projectType==20||requestScope.projectType==18}">
			<thead>
				<tr>
					<th>细项名</th>
					<th>结果</th>
					<th>计量单位</th>
					<th>下限值</th>
					<th>上限值</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty requestScope.itemResultList}">
						<c:forEach items="${requestScope.itemResultList}"
							var="itemResults">
							<tr>
								<td>${itemResults.itemName}</td>
								<td>${itemResults.itemResult}</td>
								<td>${itemResults.itemUnit}</td>
								<td>${itemResults.itemLess}</td>
								<td>${itemResults.itemHigh}</td>
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
					<td colspan="1">小结</td>
					<td colspan="4"><textarea id="summary" rows="10" cols="50"></textarea></td>
				</tr>
				<tr>
					<td colspan="5">
						<button type="button" class="btn btn-primary"
							onclick="updateResult(${requestScope.proResult.projectId},${requestScope.examineId})">保存</button>
						<button type="button" class="btn btn-primary"
							onclick="back(${requestScope.proResult.projectId},${requestScope.examineId})">返回</button>
					</td>
				</tr>
			</tbody>
		</c:if>
		<c:if test="${requestScope.projectType==19}">
			<thead>
				<tr>
					<th>细项名</th>
					<th>图片</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${not empty requestScope.itemResultList}">
						<c:forEach items="${requestScope.itemResultList}"
							var="itemResults">
							<tr>
								<td>${itemResults.itemName}</td>
								<td>${itemResults.imgAddress}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="5" align="center">暂无数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
				<tr>
					<td colspan="1">小结</td>
					<td colspan="4"><textarea id="summary" rows="10" cols="50"></textarea></td>
				</tr>
				<tr>
					<td colspan="5">
						<button type="button" class="btn btn-primary"
							onclick="updateResult(${requestScope.proResult.projectId},${requestScope.examineId})">保存</button>
						<button type="button" class="btn btn-primary"
							onclick="back(${requestScope.proResult.projectId},${requestScope.examineId})">返回</button>
					</td>
				</tr>
			</tbody>
		</c:if>
	</table>
</body>
<script>
	//更改
	function updateResult(projectId,examineId){
		var summary=$("#summary").val();
		var adminId=${sessionScope.adminLogin.adminId};
		var s="${requestScope.proResult.parameterName}";
		if(s!="已体检"){
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/doctor/updateResult.action", 
				dataType:"text",
				data:{projectId:projectId,examineId:examineId,adminId:adminId,summary:summary},
				success:function(data){
					if(data=="success"){
						window.alert("保存成功");
					}else{
						window.alert("保存失败");
					}
				},
				error : function(data){
					window.alert("与服务器失去连接！！");
				}
			});
		}else{
			alert("小结已保存");
		}
	}
	//返回
	function back(examineId){
		window.location.href="${pageContext.request.contextPath}/doctor/showProject.action?examineId="+examineId+"&comboId=${requestScope.comboId}";
	}
</script>
</html>