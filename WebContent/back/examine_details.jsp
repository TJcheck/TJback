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
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/jquery2.1.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/back/js/JsBarcode.all.min.js"></script>
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
<!--startprint-->
<div align="center" class="table table-bordered table-hover definewidth m10">
<table class="table table-bordered table-hover definewidth m10">
<tr><td colspan="2">体检日期:${details.exDate}</td></tr>
<tr><td>团体序号:${details.companyId}</td><td>体检号码:${details.examineId}</td></tr>
<tr><td>姓名:${details.exName}</td><td>性别:${details.exSex}</td></tr>
<tr><td>年龄:${details.exAge}</td><td>手机号:${details.exPhone}</td></tr>
<tr><td>打印日期:</td><td>条形码:<img alt="条码" src="${pageContext.request.contextPath}/medicalStation/barCode.action?keyCode=${details.examineId}"></img></td></tr>
</table>
<h2>体检项目列表:</h2>
<table class="table table-bordered table-hover definewidth m10">
<tr>
<th>科室</th>
<th>项目</th>
</tr>
<tbody>
<c:choose>
<c:when test="${not empty requestScope.dataList}">
<c:forEach items="${requestScope.dataList}" var="data" varStatus="st">
<tr>
<td>${data.deptName}</td>
<td>${data.projectName}</td>
</tr>
</c:forEach>
</c:when>
<c:otherwise></c:otherwise>
</c:choose>
</tbody>
</table>
<!--endprint-->
<!-- <a href="#" onClick="myprint();" ><span> 打  印</span></a><br> -->
<button type="button" class="btn btn-primary" name="backid"
onclick="myprint()">打印</button>
<button type="button" class="btn btn-success" name="backid"
onclick="back()">返回列表</button>
</div>
</body>
<script>
$(document).ready(function(){
	barCode();
});

//条形码生成
function barCode(){
	//生成条形码
	var barvalue= ${details.examineId};
	window.alert(barvalue);
	$("#bcode").JsBarcode(barvalue);//生成条形码
 /* 	JsBarcode("#bcode",barvalue, {//修改条形码样式
		  format: "pharmacode",
		  lineColor: "#0aa",
		  width:4,
		  height:40,
		//  displayValue: false  ///是否在条形码下方显示文字
		});  */
}
//打印
 function myprint(){ 
	  //直接调用浏览器打印功能
	   bdhtml=window.document.body.innerHTML; 
	   //定义打印区域起始字符，根据这个截取网页局部内容     
	   sprnstr="<!--startprint-->"; //打印区域开始的标记
	   eprnstr="<!--endprint-->";   //打印区域结束的标记  
	   prnhtml=bdhtml.substr(bdhtml.indexOf(sprnstr)+17);      
	   prnhtml=prnhtml.substring(0,prnhtml.indexOf(eprnstr));     
	   window.document.body.innerHTML=prnhtml;   
	   //开始打印
	   window.print();
	   //还原网页内容     
	   window.document.body.innerHTML=bdhtml; 
	}  
	
//返回列表
function back() {
	window.location.href = "${pageContext.request.contextPath}/medicalStation/examineManagerPage.action?currentPage="+${currentPage};
}

</script>
</html>