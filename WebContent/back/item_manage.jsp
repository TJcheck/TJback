<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <input type="hidden" id="itemName" value="">

	细项名称：
	<input type="text" name="queryItemName" id="queryItemName" class="abc input-default" placeholder="" value="">
	&nbsp;&nbsp;
	<button type="button" class="btn btn-primary" onclick="serach()">查询</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" id="addnew">新增细项</button>

	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>序号</th>
				<th>细项id</th>
				<th>细项名称</th>
				<th>单位</th>
				<th>下限值</th>
				<th>上限值</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="myTbody">
			<c:choose>
				<c:when test="${not empty requestScope.pageInfo.datasets.itemList}">
					<c:forEach items="${requestScope.pageInfo.datasets.itemList}" var="item" varStatus="st">
						<tr>
							<td>${st.count}</td>
							<td>${item.itemId}</td>
							<td>${item.itemName}</td>
							<td>${item.itemUnit}</td>
							<td>${item.itemLess}</td>
							<td>${item.itemHigh}</td>
							<td>
								<button type="button" class="btn btn-primary" onclick="delItem(${item.itemId})">删除</button>
								<button type="button" class="btn btn-primary" onclick="updateItem(${item.itemId})">修改细项名</button>
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
		</tbody>
	</table>
	<p align="center">
		<button type="button" class="btn btn-primary" onclick="previousPage()">上一页</button>
		&nbsp;
		<button type="button" class="btn btn-primary" onclick="nextPage()">下一页</button>
		当前页：
		<span id="currentPage">${requestScope.pageInfo.currentPage}</span>
		总条数：
		<span id="totalNum">${requestScope.pageInfo.totalNum}</span>
		总页：
		<span id="totalPage">${requestScope.pageInfo.totalPage}</span>
	</p>
</body>
<script>
	// 跳转到添加细项列表
	$(function()
	{

		$('#addnew').click(function()
		{

			window.location.href = "${pageContext.request.contextPath}/admin/addItemPage.action";
		});

	});
	// 删除细项
	function delItem(itemId)
	{
		if (confirm("确定要删除吗？"))
		{
			$.ajax(
			{
				type : "post",
				url : '${pageContext.request.contextPath}/admin/delItem.action',
				dataType : "text",
				data : {"itemId" : itemId},
				success : function(data)
				{
					if (data == "success")
					{
						alert("成功");
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
	// 跳转到更新细项页面
	function updateItem(itemId)
	{
		window.location.href = "${pageContext.request.contextPath}/admin/updateItemPage.action?itemId="+ itemId;
	}
	
	// 接收后台端改变的页面信息数据
	function responseData()
	{
		$.ajax(
		{
			type : "post",
			url : "${pageContext.request.contextPath}/admin/itemListPaging.action",
			dataType : 'json',
			data :
			{
				'currentPage' : $('#currentPage').val(),
				'itemName' : $('#itemName').val()//查询条件
			},
			success : function(data)
			{
				if(data.totalNum == 0)
				{
					$('#myTbody').html("<td colspan='4' align='center'>暂无数据</td>");
					return;
				}
				
				var str = "";
				for(var i = 0; i < data.datasets.itemList.length; i++) 
				{
				  	str += "<tr>";
					str += "<td>" + (i+1) + "</td>";
					str += "<td>" + data.datasets.itemList[i].itemId+ "</td>";
					str += "<td>" + data.datasets.itemList[i].itemName + "</td>";
					str += "<td>" + data.datasets.itemList[i].itemUnit+ "</td>";
					str += "<td>" + data.datasets.itemList[i].itemLess + "</td>";
					str += "<td>" + data.datasets.itemList[i].itemHigh+ "</td>";
					str += "<td><button type='button' class='btn btn-primary' onclick=\"delItem('"+data.datasets.itemList[i].itemId+"')\">删除</button>&nbsp;<button type='button' class='btn btn-primary' onclick=\"updateItem("+data.datasets.itemList[i].itemId+")\">修改细项</button></td>";
					str += "</tr>";
				}
				$('#myTbody').html(str);
				// 设置当前页和总页信息
				$('#currentPage').text(data.currentPage);
				$('#totalPage').text(data.totalPage);
				$('#totalNum').text(data.totalNum);
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
			window.alert('抱歉已是最前页');
			$('#currentPage').val(1);
		}else
		{
			$('#currentPage').val(currentPage);
		}
		responseData(); 
	}

	// 下翻页
	function nextPage()
	{
		var currentPage = $('#currentPage').text();
		var totalPage = $('#totalPage').text();
		if (++currentPage > totalPage)
		{
			window.alert('抱歉已是最后页');
			$('#currentPage').val(totalPage);
		} else
		{
			$('#currentPage').val(currentPage);
		}
		responseData();
	}
	// 根据细项名查询
	function serach()
	{
		$("#itemName").val($("#queryItemName").val());
		$('#currentPage').val(1);
		responseData(); 
	}
</script>
</html>