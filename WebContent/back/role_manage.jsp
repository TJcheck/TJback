<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/back/css/style.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.sorted.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/ckform.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/common.js"></script>
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
	<form class="form-inline definewidth m20" action="index.html" method="get">
		角色名称：
		<input type="text" name="rolename" id="rolename" class="abc input-default" placeholder="" value="">
		&nbsp;&nbsp;
		<button type="submit" class="btn btn-primary">查询</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-success" id="addnew">新增角色</button>
	</form>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>序号</th>
				<th>角色id</th>
				<th>角色名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${not empty requestScope.roleList}">
					<c:forEach items="${requestScope.roleList}" var="role" varStatus="st">
						<tr>
							<td>${st.count}</td>
							<td>${role.roleId}</td>
							<td>${role.roleName}</td>
							<td>
								<button type="button" class="btn btn-primary" onclick="delRole(${role.roleId})">删除</button>
								<button type="button" class="btn btn-primary" onclick="updateRoleName('${role.roleName}','${role.roleId}')">修改角色名</button>
							</td>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</tbody>
	</table>
</body>
<script>
	// 添加角色
	$(function()
	{

		$('#addnew').click(function()
		{

			window.location.href = "${pageContext.request.contextPath}/admin/addRolePage.action";
		});

	});
	// 删除角色
	function delRole(roleId)
	{
		if (confirm("确定要删除吗？"))
		{
			$.ajax(
			{
				type : "post",
				url : '${pageContext.request.contextPath}/admin/delRole.action',
				dataType : "text",
				data : {"roleId" : roleId},
				success : function(data)
				{
					console.log(data);
					if (data == "success")
					{
						alert("成功");
						  window.location.href = "${pageContext.request.contextPath}/admin/roleManagerPage.action";
						 
					} else
					{
						alert("失败");
					}
				},
				error : function(data)
				{
					window.alert("与服务器失去连接");
				}

			});
		}

	}
	// 更新角色
	function updateRoleName(roleName,roleId)
	{
		window.location.href = "${pageContext.request.contextPath}/admin/updateRolePage.action?roleName=" + roleName + "&roleId="+ roleId ;
	}
</script>
</html>