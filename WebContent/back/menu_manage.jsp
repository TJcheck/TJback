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
	<!-- 当前页信息、总页数信息 -->
	<input type="hidden" value='${requestScope.pageInfo.currentPage}' id="currentPage">
	<input type="hidden" value='${requestScope.pageInfo.totalPage}' id="totalPage">
	<input type="hidden" value='${requestScope.pageInfo.totalNum}' id="totalNum">
	<input type="hidden" id="menuName" value="">



	菜单名称：
	<input type="text" name="queryMenuName" id="queryMenuName" class="abc input-default" placeholder="" value="">
	&nbsp;&nbsp;
	<button type="button" class="btn btn-primary" onclick="serach()">查询</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" id="addnew">新增菜单</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-danger" onclick="delAll();">批量删除</button>

	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>
					<input id="select1" type="checkbox" name="select1" onchange="changeStatus();">
				</th>
				<th>序号</th>
				<th>菜单名称</th>
				<th>菜单url</th>
				<th>菜单上级菜单</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="myTbody">
			<c:choose>
				<c:when test="${not empty requestScope.pageInfo.datasets.menuList}">
					<c:forEach items="${requestScope.pageInfo.datasets.menuList}" var="menu" varStatus="st">
						<tr>
							<td>
								<input type="checkbox" name="select" value="${menu.menuId}">
							</td>
							<td>${st.count}</td>
							<td>${menu.menuName}</td>
							<td>${menu.menuUrl}</td>
							<td>${menu.parentId}</td>
							<td>
								<button type="button" class="btn btn-primary" onclick="delMenu(${menu.menuId})">删除</button>
								<button type="button" class="btn btn-primary" onclick="updateMenu('${menu.menuId}')">修改菜单信息</button>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5" align="center">暂无数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<p align="center">
		<button type="button" class="btn btn-primary" onclick="previousPage();">上一页</button>
		&nbsp;
		<button type="button" class="btn btn-primary" onclick="nextPage();">下一页</button>
		当前页：
		<span id="cpage">${requestScope.pageInfo.currentPage}</span>
		总条数：
		<span id="num">${requestScope.pageInfo.totalNum}</span>
		总页：
		<span id="apage">${requestScope.pageInfo.totalPage}</span>
	</p>
</body>
<script>
	// 批量删除
	function delAll()
	{
		if (confirm("确定要删除吗？"))
		{
		var obj = $("input[name='select']");
		var	arr = [];
			for(k in obj)
			{
				if(obj[k].checked)
					arr.push(obj[k].value);
			}
			if (arr.length == 0)
				{
				   alert("请至少选择一个菜单");
				   return;
				}
			
		}
			
	}
	
	// 父复选框状态改变
	function changeStatus()
	{
		if($("#select1").prop('checked')){
			$("[name='select']").prop("checked",true);
		}else{
			$("[name='select']").prop("checked",false);
		}
	}
	// 添加菜单页面
	$(function()
	{

		$('#addnew').click(function()
		{

			window.location.href = "${pageContext.request.contextPath}/admin/addMenuPage.action";
		});

	});
	// 删除菜单
	function delMenu(menuId)
	{
		if (confirm("确定要删除吗？"))
		{
			$.ajax(
			{
				type : "post",
				url : '${pageContext.request.contextPath}/admin/delMenu.action',
				dataType : "text",
				data : {"menuId" : menuId},
				success : function(data)
				{
					console.log(data);
					if (data == "success")
					{
						alert("成功");
						  /* window.location.href = "${pageContext.request.contextPath}/admin/roleManagerPage.action"; */
						responseData();
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
	// 更新菜单
	function updateMenu(menuId)
	{
		window.location.href = "${pageContext.request.contextPath}/admin/updateMenuPage.action?menuId=" + menuId ;
	}
	
	// 接收后台端改变的页面信息数据
	function responseData()
	{
		$.ajax(
		{
			type : "post",
			url : "${pageContext.request.contextPath}/admin/menuListPaging.action",
			dataType : 'json',
			data :
			{
				'currentPage' : $('#currentPage').val(),
				'menuName' : $('#menuName').val()
			},
			success : function(data)
			{
				if(data.totalNum == 0)
				{
					$('#myTbody').html("<td colspan='4' align='center'>暂无数据</td>");
					return;
				}
				
				var str = "";
				for(var i = 0; i < data.datasets.menuList.length; i++) 
				{
				  	str += "<tr>";
					str += "<td>" + (i+1) + "</td>";
					str += "<td>" + data.datasets.menuList[i].menuName+ "</td>";
					str += "<td>" + data.datasets.menuList[i].menuUrl + "</td>";
					str += "<td>" + data.datasets.menuList[i].parentId + "</td>";
					str += "<td><button type='button' class='btn btn-primary' onclick=\"delMenu('"+data.datasets.menuList[i].menuId+"')\">删除</button>&nbsp;<button type='button' class='btn btn-primary' onclick=\"updateMenu('"+data.datasets.menuList[i].menuId+"')\">修改菜单信息</button></td>";
					str += "</tr>";
				}
				/* console.log(str); */
				$('#myTbody').html(str);
				// 设置当前页和总页信息
				$('#currentPage').val(data.currentPage);
				$('#totalPage').val(data.totalPage);
				$('#totalNum').val(data.totalNum);
				console.log($('#currentPage').val());
				console.log($('#totalPage').val());
				console.log($('#totalNum').val());
				// 改变span显示标记
				$('#cpage').html($('#currentPage').val());
				$('#apage').html($('#totalPage').val());
				$('#num').html($('#totalNum').val());
			},
			error : function(data)
			{
				window.alert("与服务器失去连接！！");
			}
		
		});
	}
	// 上翻页
	function previousPage()
	{
		
		var currentPage = $('#currentPage').val();
		if(--currentPage <= 0)
		{
			$('#currentPage').val(1);
		}else
		{
			$('#currentPage').val(currentPage)
		}
		responseData(); 
	}

	// 下翻页
	function nextPage()
	{
		var currentPage = $('#currentPage').val();
		if (++currentPage > $('#totalPage').val())
		{
			$('#currentPage').val($('#totalPage').val());
		} else
		{
			$('#currentPage').val(currentPage);
		}
		responseData();
	}
	// 根据角色名查询角色
	function serach()
	{
		$("#menuName").val($("#queryMenuName").val());
		$('#currentPage').val(1);
		responseData();
	}
</script>
</html>