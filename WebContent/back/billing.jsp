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
<script src="${pageContext.request.contextPath}/back/js/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/back/js/jquery-ui-zh.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/back/css/jquery-ui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/back/css/jquery-ui.structure.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/back/css/jquery-ui.theme.css">
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
	<input type="hidden" id="comboName" value=""> 项目名称：
	<input type="text" id="query" class="abc input-default" placeholder=""
		value=""> &nbsp;&nbsp;
	<button type="button" class="btn btn-primary" onclick="query($(this))">查询</button>
	&nbsp;&nbsp;
	<button type="button" class="btn btn-success" id="addnew">新增套餐</button>

	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>序号</th>
				<th>公司</th>
				<th>套餐</th>
				<th>团检人数</th>
				<th>团检金额</th>
				<th>预约状态</th>
				<th>预约时间</th>
				<th>体检时间</th>
				<th>结账状态</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody id="myTbody">
			<c:choose>
				<c:when
					test="${not empty requestScope.pageInfo.datasets.chargeList}">
					<c:forEach items="${requestScope.pageInfo.datasets.chargeList}"
						var="charge" varStatus="st">
						<tr>
							<td>${st.count}</td>
							<td>${charge.companyName}</td>
							<td>${charge.comboName}</td>
							<td>${charge.chargeNum}</td>
							<td>${charge.chargePrice}</td>
							<td>${charge.reservationParameter}</td>
							<td>${charge.reservationTime}</td>
							<td><input id="date" type="text"
								value="${charge.examineTime}" /></td>
							<td>${charge.chargeParameter}</td>
							<td><c:if test="${charge.reservationParameter=='预约'}">
									<button type="button" class="btn btn-primary"
										onclick="okCharge(${charge.chargeId})">确认</button>
									<button type="button" class="btn btn-primary"
										onclick="delComboId(${charge.chargeId})">取消</button>
								</c:if> <c:if test="${charge.reservationParameter=='已上传'}">
									<button type="button" class="btn btn-primary"
										onclick="uploadExcel('${charge.companyName}',${charge.companyId},${charge.chargeId},${charge.comboId})">导入</button>
									<button type="button" class="btn btn-primary"
										onclick="lookExcel('${charge.companyName}')">下载查看表格</button>
								</c:if>
								<button type="button" class="btn btn-primary"
									onclick="delComboId(${charge.chargeId})">详情</button></td>
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
		当前页： <span id="currentPage">${requestScope.pageInfo.currentPage}</span>
		总条数： <span id="totalNum">${requestScope.pageInfo.totalNum}</span> 总页：
		<span id="totalPage">${requestScope.pageInfo.totalPage}</span>
	</p>
</body>
<script type="text/javascript">
$('#date').datepicker({
	changeYear: true,
	showOtherMonths: true,
    selectOtherMonths: true,
	changeMonth: true,
	dateFormat: 'yy-mm-dd',
	minDate: 1
});
//接收后台端改变的页面信息数据
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
//ajax返回记账表信息
function responseData(){
	$.ajax(
	{
		type : "post",
		url : "${pageContext.request.contextPath}/medicalStation/chargeListPaging.action",
		dataType:"json",
		data :{currentPage : $('#currentPage').text(),comboName: $('#query').val()},
		success : function(data)
		{
			if(data.totalNum == 0)
			{
				$('#myTbody').html("<td colspan='4' align='center'>暂无数据</td>");
				return;
			}
			
			var str = "";
			var dataList = data.datasets.chargeList;
			for(var i = 0; i < dataList.length; i++) 
			{
			  	str += "<tr>";
				str += "<td>" + (i+1) + "</td>";
				str += "<td>" + dataList[i].companyName+ "</td>";
				str += "<td>" + dataList[i].comboName + "</td>";
				str += "<td>" + dataList[i].chargeNum+ "</td>";
				str += "<td>" + dataList[i].chargePrice + "</td>";
				str += "<td>" + dataList[i].reservationParameter + "</td>";
				str += "<td>" + dataList[i].reservationTime + "</td>";
				str += "<td>" + dataList[i].examineTime + "</td>";
				str += "<td>" + dataList[i].chargeParameter + "</td>";
				str+="<td>"
				if(dataList[i].reservationParameter=="预约"){
					str += "<button type='button' class='btn btn-primary' onclick=\"okCharge('"+dataList[i].chargeId+"')\">确认</button>&nbsp;<button type='button' class='btn btn-primary' onclick=\"clearCharge("+dataList[i].chargeId+")\">取消</button>";
				}
				if(dataList[i].reservationParameter=="已上传"){
					str += "<button type='button' class='btn btn-primary' onclick=\"uploadExcel('"+dataList[i].companyName+"',"+dataList[i].companyId+","+dataList[i].chargeId+","+dataList[i].comboId+")\">导入</button>&nbsp;<button type='button' class='btn btn-primary' onclick=\"lookExcel("+dataList[i].companyName+")\">下载查看表格</button>";
				}
				if(dataList[i].chargeParameter=="未付款"){
					str+="<button type='button' class='btn btn-primary' onclick=\"InfoCharge('"+dataList[i].chargeId+"')\">结账</button>";
				}
				if(dataList[i].chargeParameter=="已付款"){
					str+="<button type='button' class='btn btn-primary' onclick=\"InfoCharge('"+dataList[i].chargeId+"')\">打印</button>";
				}
				str+="<button type='button' class='btn btn-primary' onclick=\"InfoCharge('"+dataList[i].chargeId+"')\">详情</button>"
				str+="</td>";
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
//确认预约
function okCharge(chargeId){
	if($("#date").val()==""){
		alert("请设置体检日期");
	}else{
		$.ajax(
				{
					type : "post",
					url : '${pageContext.request.contextPath}/medicalStation/okAppointment.action',
					dataType : "text",
					data : {"chargeId" : chargeId,examineTime:$("#date").val()},
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
//下载查看表格
function lookExcel(companyName){
	window.location.href="${pageContext.request.contextPath}/uploadFile/"+companyName+".xlsx";
}
//导入Excel
function uploadExcel(companyName,companyId,chargeId,comboId){
	$.ajax(
			{
				type : "post",
				url : '${pageContext.request.contextPath}/file/readExcel.action',
				dataType : "text",
				data : {
					fileName : companyName, /* 文件名=公司名  */
					companyId : companyId, /* 公司ID */
					chargeId : chargeId,
					comboId : comboId /* 套餐ID */
					},
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
</script>
</html>