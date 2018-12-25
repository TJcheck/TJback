<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	<input type="hidden" id="companyName" value="">
	公司名称：
	<input type="text" name="queryCompanyName" id="queryCompanyName" class="abc input-default" placeholder="" value="">
	&nbsp;&nbsp;
	<button type="button" class="btn btn-primary" onclick="serach()">查询</button>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>序号</th>
				<th>公司名字</th>
				<th>已体检人数</th>
				<th>套餐名字</th>
				<th>套餐价格</th>
				<th>总价格</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="myTbody">
			<c:choose>
				<c:when test="${not empty requestScope.pageInfo.datasets.companyList}">
					<c:forEach items="${requestScope.pageInfo.datasets.companyList}" var="company" varStatus="st">
						<tr>
							<td>${st.count}</td>
							<td>${company.companyName}</td>
							<td>${company.peopleNum}</td>
							<td>${company.comboName}</td>
							<td>${company.comboCost}</td>
							<td>${company.allCost}</td>
							<td>
								<button type="button" class="btn btn-primary" onclick="checkComboInfo(${company.comboId})">套餐信息</button>
								<button type="button" class="btn btn-primary" onclick="checkpeopleInfo(${company.companyId})">人员信息</button>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7" align="center">暂无数据</td>
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
<script type="text/javascript">
//上翻页
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
// 接收后台端改变的页面信息数据
function responseData()
{
	$.ajax(
	{
		type : "post",
		url : "${pageContext.request.contextPath}/admin/queryOrderInfoPaging.action",
		dataType : 'json',
		data :
		{
			'currentPage' : $('#currentPage').val(),
			'companyName' : $('#companyName').val()
		},
		success : function(data)
		{
			if(data.totalNum == 0)
			{
				$('#myTbody').html("<td colspan='7' align='center'>暂无数据</td>");
				return;
			}
			
			var str = "";
			for(var i = 0; i < data.datasets.companyList.length; i++) 
			{
			  	str += "<tr>";
				str += "<td>" + (i+1) + "</td>";
				str += "<td>" + data.datasets.companyList[i].companyName+ "</td>";
				str += "<td>" + data.datasets.companyList[i].peopleNum+ "</td>";
				str += "<td>" + data.datasets.companyList[i].comboName + "</td>";
				str += "<td>" + data.datasets.companyList[i].comboCost + "</td>";
				str += "<td>" + data.datasets.companyList[i].allCost + "</td>";
				str += "<td><button type='button' class='btn btn-primary' onclick=\"checkComboInfo('"+data.datasets.companyList[i].comboId+"')\">套餐信息</button>&nbsp;<button type='button' class='btn btn-primary' onclick=\"checkpeopleInfo('"+data.datasets.companyList[i].companyId+"')\">人员信息</button></td>";
				str += "</tr>";
			}
			console.log(str); 
			$('#myTbody').html(str);
			// 让父复选框变为没有被选中
			$('#select1').prop("checked",false);
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
// 根据公司名查询公司
function serach()
{
	$("#companyName").val($("#queryCompanyName").val());
	$('#currentPage').val(1);
	responseData();
}
// 查看套餐信息
function checkComboInfo(comboId){
	window.location.href = "${pageContext.request.contextPath}/admin/queryCheckPro.action?comboId=" + comboId;
}
// 查勘人员信息
function checkpeopleInfo(companyId){
	window.location.href = "${pageContext.request.contextPath}/admin/queryExamineInfo.action?companyId=" + companyId;
}
</script>
</html>