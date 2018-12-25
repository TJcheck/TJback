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
	<!-- 当前页信息、总页数信息 -->
	<input type="hidden" id="comboName" value=""> 体检人员名称：
	<input type="text" id="query" class="abc input-default" placeholder=""
		value=""> &nbsp;&nbsp;
	<button type="button" class="btn btn-primary" onclick="query($(this))">查询</button>
	<table class="table table-bordered table-hover definewidth m10" id="comboTable">
		<thead>
			<tr>
				<th>序号</th>
				<th>团检单位</th>
				<th>团检套餐</th>
				<th>体检人员号码</th>
				<th>名字</th>
				<th>性别</th>
				<th>年龄</th>
				<th>手机号</th>
				<th>体检日期</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="myTbody">
			<c:choose>
				<c:when test="${not empty requestScope.pageInfo.datasets.examineList}">
					<c:forEach items="${requestScope.pageInfo.datasets.examineList}"
						var="examine" varStatus="st">
						<tr>
							<td>${st.count}</td>
							<td>${examine.companyName}</td>
							<td>${examine.comboName}</td>
							<td>${examine.examineId}</td>
							<td>${examine.exName}</td>
							<td>${examine.exSex}</td>
							<td>${examine.exAge}</td>
							<td>${examine.exPhone}</td>
							<td>${examine.exDate}</td>
							<td>
								<button type="button" class="btn btn-primary"
									onclick="showExamine(${examine.examineId})">查看导检单</button>
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
		每页条数 <select id="onePage">
			<option value="5" selected>5</option>
			<option value="10">10</option>
			<option value="15">15</option>
		</select> &nbsp;&nbsp;
		<button type="button" class="btn btn-primary" onclick="query($(this))">刷新</button>
		<button type="button" class="btn btn-primary" onclick="query($(this))">上一页</button>
		&nbsp;&nbsp;
		<button type="button" class="btn btn-primary" onclick="query($(this))">下一页</button>
		当前页： <span id="currentPage">${requestScope.pageInfo.currentPage}</span>
		总条数： <span id="totalNum">${requestScope.pageInfo.totalNum}</span> 总页：
		<span id="totalPage">${requestScope.pageInfo.totalPage}</span>
	</p>
</body>
<script>
$(document).ready(function(){
	//条数变化
	$("#onePage").change(function(){
		responseData();
	});
});

	// 跳转到导检单详情页面
	function showExamine(examineId)
	{
		window.location.href = "${pageContext.request.contextPath}/medicalStation/examineDetails.action?examineId="+ examineId+"&&currentPage="+$("#currentPage").text();
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
		$('#currentPage').text(currentPage);
		responseData();
	}
	
	function responseData(){
		$.ajax(
		{
			type : "post",
			url : "${pageContext.request.contextPath}/medicalStation/examineListPaging.action",
			dataType : 'json',
			data :
			{
				'currentPage' : $('#currentPage').text(),
				'onePage' :$("#onePage").val(),
				'examineName' : $('#query').val()//查询条件
			},
			success : function(data)
			{
				if(data.totalNum == 0)
				{
					$('#myTbody').html("<td colspan='4' align='center'>暂无数据</td>");
					return;
				}
				var str = "";
				var dataList = data.datasets.examineList;
				for(var i = 0; i < dataList.length; i++) 
				{
				  	str += "<tr>";
					str += "<td>" + (i+1) + "</td>";
					str += "<td>" + dataList[i].companyName+ "</td>";
					str += "<td>" + dataList[i].comboName  + "</td>";
					str += "<td>" + dataList[i].examineId+ "</td>";
					str += "<td>" + dataList[i].exName  + "</td>";
					str += "<td>" + dataList[i].exSex + "</td>";
					str += "<td>" + dataList[i].exAge  + "</td>";
					str += "<td>" + dataList[i].exPhone  + "</td>";
					str += "<td>" + dataList[i].exDate   + "</td>";
					str += "<td><button type='button' class='btn btn-primary' onclick=\"showExamine("+dataList[i].examineId+")\">查看导检单</button></td>";
					str += "</tr>";
				}
				$('#myTbody').html(str);
				// 设置当前页和总页信息
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