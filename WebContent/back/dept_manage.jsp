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
    <input type="hidden" id="deptName" value="">

	科室名称：
	<input type="text" name="queryDeptName" id="queryDeptName" class="abc input-default" placeholder="" value="">
	&nbsp;&nbsp;
	<button type="button" class="btn btn-primary" onclick="serach()">查询</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" id="addnew">新增科室</button>

	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>序号</th>
				<th>科室id</th>
				<th>科室名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="myTbody">
			<c:choose>
				<c:when test="${not empty requestScope.pageInfo.datasets.deptList}">
					<c:forEach items="${requestScope.pageInfo.datasets.deptList}" var="dept" varStatus="st">
						<tr>
							<td>${st.count}</td>
							<td>${dept.deptId}</td>
							<td>${dept.deptName}</td>
							<td>
								<button type="button" class="btn btn-primary" onclick="delDept(${dept.deptId})">删除</button>
								<button type="button" class="btn btn-primary" onclick="updateDeptName('${dept.deptName}','${dept.deptId}')">修改科室名</button>
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
	// 添加科室
	$(function()
	{

		$('#addnew').click(function()
		{

			window.location.href = "${pageContext.request.contextPath}/admin/addDeptPage.action";
		});

	});
	// 删除科室
	function delDept(deptId)
	{
		if (confirm("确定要删除吗？"))
		{
			$.ajax(
			{
				type : "post",
				url : '${pageContext.request.contextPath}/admin/delDept.action',
				dataType : "text",
				data : {"deptId" : deptId},
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
	// 更新角色
	function updateDeptName(deptName,deptId)
	{
		window.location.href = "${pageContext.request.contextPath}/admin/updateDeptPage.action?deptName=" + deptName + "&deptId="+ deptId ;
	}
	
	// 接收后台端改变的页面信息数据
	function responseData()
	{
		$.ajax(
		{
			type : "post",
			url : "${pageContext.request.contextPath}/admin/deptListPaging.action",
			dataType : 'json',
			data :
			{
				'currentPage' : $('#currentPage').val(),
				'deptName' : $('#deptName').val()
			},
			success : function(data)
			{
				if(data.totalNum == 0)
				{
					$('#myTbody').html("<td colspan='4' align='center'>暂无数据</td>");
					return;
				}
				
				var str = "";
				for(var i = 0; i < data.datasets.deptList.length; i++) 
				{
				  	str += "<tr>";
					str += "<td>" + (i+1) + "</td>";
					str += "<td>" + data.datasets.deptList[i].deptId+ "</td>";
					str += "<td>" + data.datasets.deptList[i].deptName + "</td>";
					str += "<td><button type='button' class='btn btn-primary' onclick=\"delDept('"+data.datasets.deptList[i].deptId+"')\">删除</button>&nbsp;<button type='button' class='btn btn-primary' onclick=\"updateDeptName('"+data.datasets.deptList[i].deptName+"','"+data.datasets.deptList[i].deptId+"')\">修改科室名</button></td>";
					str += "</tr>";
				}
				console.log(str);
				$('#myTbody').html(str);
				// 设置当前页和总页信息
				$('#currentPage').val(data.currentPage);
				$('#totalPage').val(data.totalPage);
				$('#totalNum').val(data.totalNum);
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
			window.alert('已是最前页');
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
			window.alert('抱歉已是最后页');
			$('#currentPage').val($('#totalPage').val());
		} else
		{
			$('#currentPage').val(currentPage);
		}
		responseData();
	}
	// 根据科室名查询角色
	function serach()
	{
		$("#deptName").val($("#queryDeptName").val());
		$('#currentPage').val(1);
		responseData(); 
	}
</script>
</html>