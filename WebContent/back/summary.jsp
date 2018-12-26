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
		action="${pageContext.request.contextPath}/doctor/showProResult.action"
		method="post" enctype="multipart/form-data">
		<input type="file" name="file"> <input type="submit"
			value="识别条形码" />
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<tbody id="myTbody">
			<c:choose>
				<c:when test="${not empty requestScope.proResultList}">
					<c:forEach items="${requestScope.proResultList}" var="proResult">
						<tr>
							<th colspan="5">${proResult.projectName}</th>
							<th>${proResult.parameterName}</th>
						</tr>
						<tr>
							<th>细项名</th>
							<th>值</th>
							<th>单位</th>
							<th>下限值</th>
							<th>上限值</th>
							<th>图片</th>
						</tr>
						<c:forEach items="${requestScope.itemResultList}" var="itemResult">
							<c:if test="${proResult.projectId==itemResult.projectId}">
								<tr>
									<td>${itemResult.itemName}</td>
									<td>${itemResult.itemResult}</td>
									<td>${itemResult.itemUnit}</td>
									<td>${itemResult.itemLess}</td>
									<td>${itemResult.itemHigh}</td>
									<td><img alt="" src="${itemResult.imgAddress}"></td>
								</tr>
							</c:if>
						</c:forEach>
						<tr>
							<td>小结:</td>
							<td colspan="5">${proResult.summary}</td>
						</tr>
					</c:forEach>
					<tr>
						<td>总结</td>
						<td colspan="5"><textarea id="conclusion" rows="10" cols="50"></textarea></td>
					</tr>
					<tr>
						<td>建议</td>
						<td colspan="5"><textarea id="exSuggest" rows="10" cols="50"></textarea></td>
					</tr>
					<tr>
						<td>生活保健指导</td>
						<td colspan="5"><textarea id="exGuide" rows="10" cols="50"></textarea></td>
					</tr>
					<tr>
						<td colspan="6" align="center"><input type="button" class="btn btn-primary" onclick="save()"
							value="保存" /></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5" align="center">暂无数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</body>
<script>
	function save(){
		var examineId=${requestScope.examineId};
		var adminId=${sessionScope.adminLogin.adminId};
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/doctor/updateSummary.action",
			dataType:"text",
			data:{conclusion:$("#conclusion").val(),exSuggest:$("#exSuggest").val(),exGuide:$("#exGuide").val(),examineId:examineId,adminId:adminId},
			success:function(data)
			{
				if(data=="success"){
					alert("保存成功");
				}else{
					alert("保存失败");
				}
			},
			error:function(data)
			{
				window.alert("与服务器失去连接");
			}
		});
	}
</script>
</html>