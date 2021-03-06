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
    <input type="hidden" id="projectName" value="">

	项目名称：
	<input type="text"  id="query" class="abc input-default" placeholder="" value="">
	&nbsp;&nbsp;
	<button type="button" class="btn btn-primary" onclick="query($(this))">查询</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" id="addnew">新增项目</button>

	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>序号</th>
				<th>项目Id</th>
				<th>项目名称</th>
				<th>科室</th>
				<th>项目价格</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="myTbody">
			<c:choose>
				<c:when test="${not empty requestScope.pageInfo.datasets.projectList}">
					<c:forEach items="${requestScope.pageInfo.datasets.projectList}" var="project" varStatus="st">
						<tr>
							<td>${st.count}</td>
							<td>${project.projectId}</td>
							<td>${project.projectName}</td>
							<td>${project.deptName}</td>
							<td>${project.projectPrice}</td>
							<td>
								<button type="button" class="btn btn-primary" onclick="delProject(${project.projectId})">删除</button>
								<button type="button" class="btn btn-primary" onclick="updateProject(${project.projectId})">修改项目</button>
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
		<button type="button" class="btn btn-primary" onclick="query($(this))">上一页</button>
		&nbsp;
		<button type="button" class="btn btn-primary" onclick="query($(this))">下一页</button>
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

			window.location.href = "${pageContext.request.contextPath}/admin/addProjectPage.action";
		});

	});
	// 删除项目
	function delProject(projectId)
	{
		window.alert(projectId);
		if (confirm("确定要删除吗？"))
		{
			$.ajax(
			{
				type : "post",
				url : '${pageContext.request.contextPath}/admin/delProject.action',
				dataType : "text",
				data : {"projectId" : projectId},
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
	// 跳转到更新项目页面
	function updateProject(projectId)
	{
		window.location.href = "${pageContext.request.contextPath}/admin/updateProjectPage.action?projectId="+ projectId;
	}
	
	// 接收后台端改变的页面信息数据
	function query(btn)
	{
		var currentPage = $('#currentPage').text();
		var totalPage = $('#totalPage').text();
		var btnv=btn.text();
		if(btnv=="下一页"){
			currentPage = ++currentPage;
			if(currentPage>totalPage){
			window.alert('抱歉没有下一页了');
			return;
			}
		}else if(btnv=="上一页"){
			currentPage = --currentPage;
			if(currentPage<1){
			window.alert('抱歉没有上一页了');
			return;
			}
		}else if(btnv=="查询"){
			currentPage=1;
		}
		$('#currentPage').val(currentPage);
		responseData();
	}
	
	function responseData(){
		$.ajax(
		{
			type : "post",
			url : "${pageContext.request.contextPath}/admin/projectListPaging.action",
			dataType : 'json',
			data :
			{
				'currentPage' : $('#currentPage').val(),
				'projectName' : $('#query').val()//查询条件
			},
			success : function(data)
			{
				if(data.totalNum == 0)
				{
					$('#myTbody').html("<td colspan='4' align='center'>暂无数据</td>");
					return;
				}
				
				var str = "";
				var dataList = data.datasets.projectList;
				for(var i = 0; i < dataList.length; i++) 
				{
				  	str += "<tr>";
					str += "<td>" + (i+1) + "</td>";
					str += "<td>" + dataList[i].projectId+ "</td>";
					str += "<td>" + dataList[i].projectName + "</td>";
					str += "<td>" + dataList[i].deptName+ "</td>";
					str += "<td>" + dataList[i].projectPrice + "</td>";
					str += "<td><button type='button' class='btn btn-primary' onclick=\"delProject('"+dataList[i].projectId+"')\">删除</button>&nbsp;<button type='button' class='btn btn-primary' onclick=\"updateProject("+dataList[i].projectId+")\">修改项目</button></td>";
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
</script>
</html>