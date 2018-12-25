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
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.serializejson.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery.validate.min.js"></script>

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
	<form id="myForm" action="index.html" method="post" class="definewidth m20">
		<input type="hidden" id="parentSelected" value="${requestScope.menu.parentId}">
		<input type="hidden" id="menuId" name="menuId" value="${requestScope.menu.menuId}">
		<table class="table table-bordered table-hover m10">
			<tr>
				<td width="10%" class="tableleft">上级菜单</td>
				<td>
					<select id="parentId" name="parentId">
						<option value="">请选择</option>
						<option value="0">一级菜单</option>
						<c:forEach items="${requestScope.menuList}" var="menuTemp">
							<option value="${menuTemp.menuId}">${menuTemp.menuName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="tableleft">菜单名称</td>
				<td>
					<input type="text" name="menuName" value="${menu.menuName}" />
				</td>
			</tr>
			<tr>
				<td class="tableleft">菜单URL</td>
				<td>
					<input type="text" name="menuUrl" value="${menu.menuUrl}" />
				</td>
			</tr>
			<tr>
				<td class="tableleft"></td>
				<td>
					<button type="submit" class="btn btn-primary" >保存</button>
					&nbsp;&nbsp;
					<button type="button" class="btn btn-success" name="backid" id="backid" onclick="back();">返回列表</button>
				</td>
			</tr>
		</table>
	</form>
</body>
<script>
	$().ready(function()
	{
		$("#myForm").validate(
		{
			rules :
			{
				menuName : "required",
				menuUrl : "required",
				parentId : "required"
			},
			messages :
			{
				menuName : "请输入菜单名字",
				menuUrl : "请输入菜单链接",
				parentId : "请输入菜单父级菜单"
			}
		});
	});
	$.validator.setDefaults(
	{
		submitHandler : function()
		{
			updateMenu();
		}
	});
	$(function()
	{
		// 默认设置菜单上级菜单
		var selectValue = $("#parentSelected").val();
		$("#parentId").val(selectValue);
		if ($("#parentId").val() == 0)
		{
			$("#parentId").attr("disabled", "disabled");
		}
	});

	function updateMenu()
	{
		$.ajax(
		{
			type : "post",
			url : '${pageContext.request.contextPath}/admin/updateMenu.action',
			contentType : "application/json;charset=utf-8", //如果采用requestbody那么一定要加
			dataType : "text",
			data : JSON.stringify($('#myForm').serializeJSON()),
			success : function(redata)
			{
				if (redata == "success")
				{
					alert("修改成功");
				} else
				{
					alert("失败")
				}
			},
			error : function(data)
			{
				window.alert("与服务器失去连接");
			}

		});
	}
	function back()
	{
		window.location.href = "${pageContext.request.contextPath}/admin/menuManagePage.action"
	}
</script>
</html>
